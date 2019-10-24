import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'
import { Env, Constant } from '@/config'

// 创建axios实例
const service = axios.create({
  baseURL: Env.BASE_API, // api 的 base_url
  timeout: 5000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(
  config => {
    //处理分页参数
    if(config.params && config.params.tablePageInfo){
      let pageInfo = config.params.tablePageInfo
      config.params['tablePageInfo'] = undefined
      config.params['page_currentPageNum'] = pageInfo.current
      config.params['page_size'] = pageInfo.size
      if(pageInfo.prop && pageInfo.order){
        if(pageInfo.order == 'ascending'){
          config.params['page_sort'] = pageInfo.prop+" asc"
        }else if(pageInfo.order == 'descending'){
          config.params['page_sort'] = pageInfo.prop+" desc"
        }
      }
    }
    //添加当前选择的appId(暂不添加全局应用，已在Navbar.vue中注释)
    /*if(store.getters.application){
      if(!config.params){
        config.params = {}
      }
      config.params['currentAppId'] = store.getters.application
    }*/
    //处理鉴权token
    let token = getToken()
    if (store.getters.token && token) {
      config.headers['X-Token'] = token // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    if(res && res.flag){
      const flag = res.flag
      if(flag==0){
        MessageBox.alert(res.msg, '错误提示', {type: 'error',dangerouslyUseHTMLString: true});
        return Promise.reject(res.msg)
      }else if(flag==2){
        MessageBox.alert(res.msg, '警告提示', {type: 'warning',dangerouslyUseHTMLString: true});
        return response;
      }else if(flag==3){
        //异常提示
        let ex = res.msg
        let redirectLogin = ex.redirectLogin
        let exMsg = "";
        if(ex){
          exMsg += "<strong>编码：</strong>"+ex.code
          exMsg += "<br/>"
          exMsg += "<strong>原因：</strong>"+ex.description
        }
        //出现需要退出重新登录的异常
        if(redirectLogin==1){
          MessageBox.confirm(
            exMsg,
            '请退出',
            {
              confirmButtonText: '重新登录',
              cancelButtonText: '取消',
              dangerouslyUseHTMLString: true,
              type: 'warning'
            }
          ).then(() => {
            store.dispatch('FedLogOut').then(() => {
              location.reload() // 为了重新实例化vue-router对象 避免bug
            })
          })
          return Promise.reject(ex.description)
        }
        let exLevel = ex.level
        if(exLevel==1){
          //错误异常
          MessageBox.alert(exMsg, '异常错误', {
            type: 'error',
            dangerouslyUseHTMLString: true
          });
          return Promise.reject(res.msg)
        }else{
          //其余认为是警告异常，不影响正常业务执行
          exMsg = "<div style='color:#ff9d24;font-weight: bold'>本次操作已完成，但是出现如下异常，请您知晓！</div><br/>"+exMsg
          Message({
            showClose: true,
            message: exMsg,
            dangerouslyUseHTMLString: true,
            duration: 0,
            type: 'warning'
          });
          //修改结果标志为1（成功），便于业务模块使用
          response.data.flag = 1;
        }
        return response;
      }else if(flag==1){
        //成功提示
        //MessageBox.alert(res.msg, '提示信息', {type: 'info'});
        Message({
          showClose: true,
          message: res.msg,
          type: 'success'
        });
        return response;
      }
    }
    return response
  },
  error => {
    //处理登录失效
    if (error.response && error.response.status == 401) {
      MessageBox.confirm(
        '你已被登出，可以取消继续留在该页面，或者重新登录',
        '确定登出',
        {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        store.dispatch('FedLogOut').then(() => {
          location.reload() // 为了重新实例化vue-router对象 避免bug
        })
      })
      return Promise.reject('error')
    }
    //处理普通错误
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
