import { login, logout, getInfo } from '@/api/login'
import { Env, Constant } from '@/config'
import loginApi from "@/api/system/account"
import { getToken, setToken, removeToken } from '@/utils/auth'

const account = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [11]
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    }
  },

  actions: {
    // 登录
    Login({ dispatch, commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        loginApi.login(username, userInfo.password).then(response => {
          const data = response.data
          if(data && data.result==1){
            //设置权限
            dispatch("setAuth", data.account)
            //设置token
            setToken(data.token)
            commit('SET_TOKEN', data.token)
            resolve(response)
          }else{
            reject(data.msg)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    /**
     * 设置权限
     */
    setAuth({ commit }, ac){
      if(ac){
        //设置权限
        let roles = []
        if(ac.isAdmin==1){
          //管理员权限
          roles.push(Constant.AdminRoleKey)
        }
        if(ac.appId==Constant.allAppKey){
          //系统级账户
          roles.push(Constant.SystemAccount)
          if(ac.isAdmin==1){
            //超级管理员（系统级账户管理员）
            roles.push(Constant.SAdminRoleKey)
          }
        }
        commit('SET_ROLES', roles)
      }
    },
    // 获取用户信息
    GetInfo({ dispatch, commit, state }) {
      return new Promise((resolve, reject) => {
        loginApi.getAccountByToken(state.token).then(response => {
          const data = response.data
          if(data){
            //设置权限
            dispatch("setAuth", data)
            //设置账户信息
            commit('SET_NAME', data.acName)
            commit('SET_AVATAR', "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
            resolve(response)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        loginApi.logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resolve()
      })
    }
  }
}

export default account
