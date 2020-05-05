import Cookies from 'js-cookie'
import { Env, Constant } from '@/config'
import store from '../store'

const TokenKey = 'vue_admin_template_token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function isSystemAccount() {
  let roles = store.getters.roles
  if(roles && roles.includes(Constant.SystemAccount)){
    return true
  }
  return false
}
export function isAdmin() {
  let roles = store.getters.roles
  if(roles && roles.includes(Constant.AdminRoleKey)){
    return true
  }
  return false
}

export function isSuperAdmin() {
  let roles = store.getters.roles
  if(roles && roles.includes(Constant.AdminRoleKey) && roles.includes(Constant.SAdminRoleKey)){
    return true
  }
  return false
}

export function hasAdminRole() {
  let roles = store.getters.roles
  if(roles && (roles.includes(Constant.AdminRoleKey) || roles.includes(Constant.SAdminRoleKey))){
    return true
  }
  return false
}
