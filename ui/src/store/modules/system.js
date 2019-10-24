import apiApplication from "@/api/system/application"

const system = {
  state: {
    //有效应用列表
    appList: [],
    // 当前应用
    application: null,
    // 当前登录用户
    me: null
  },
  mutations: {
    SET_APPLICATION: (state, id) => {
      state.application = id
    },
    SET_APPLIST: (state, data) => {
      let appArr = []
      if(data && data.length>0){
        for(let i=0;i<data.length;i++){
          let item = data[i]
          appArr.push({"label":item.appName,"value":item.id})
        }
      }
      state.appList = appArr
    },
    SET_ME: (state, id) => {
      state.me = id
    }
  },
  actions: {
    SetAppList({ commit }, data) {
      commit('SET_APPLIST', data)
    },
    updateAppList({ commit }) {
      return new Promise((resolve, reject) => {
        apiApplication.getApplications().then(response => {
          commit('SET_APPLIST', response.data.rows)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    SetApplication({ commit }, id) {
      commit('SET_APPLICATION', id)
    },
    SetMe({ commit }, currentAccount) {
      commit('SET_ME', currentAccount)
    }
  }
}

export default system
