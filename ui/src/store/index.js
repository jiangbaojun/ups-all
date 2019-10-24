import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import account from './modules/account'
import system from './modules/system'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    account,
    system
  },
  getters
})

export default store
