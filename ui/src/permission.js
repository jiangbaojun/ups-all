import router from './router'
import store from './store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import {Message, MessageBox} from 'element-ui'
import { getToken } from '@/utils/auth' // getToken from cookie
import apiApplication from "@/api/system/application"

NProgress.configure({ showSpinner: false })// NProgress configuration

const whiteList = ['/login'] // 不重定向白名单
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) {
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
    } else {
      // 拉取用户信息，验证当前token是否有效
      store.dispatch('GetInfo').then(res => {
        //设置当前用户信息
        store.dispatch('SetMe',res.data)
        //如果applist没有初始化，进行初始化
        let appList = store.getters.appList
        if(appList==null || appList.length==0) {
          store.dispatch('updateAppList').then(response => {
            next()
          })
        }else{
          next()
        }
      }).catch((err) => {
        MessageBox.confirm(
          "鉴权失败,请重新登录",
          '请退出',
          {
            confirmButtonText: '重新登录',
            dangerouslyUseHTMLString: true,
            type: 'warning'
          }
        ).then(() => {
          store.dispatch('FedLogOut').then(() => {
            Message.error(err || '鉴权失败请重新登录')
            next({ path: '/' })
          })
        })
      })
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next(`/login?redirect=${to.path}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
