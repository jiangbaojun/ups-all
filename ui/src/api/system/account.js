import request from '@/utils/request'

export default {
  /**
   * 账户登录
   */
  login: (username, password) => {
    let params = {
      "acName": username,
      "password": password
    }
    return request({
      method: 'post',
      url: '/system/account/login',
      params
    });
  },
  /**
   * 账户登出
   */
  logout: (token) => {
    let params = {
      "token": token
    }
    return request({
      method: 'post',
      url: '/system/account/logout',
      params
    });
  },
  /**
   * 根据token获得用户
   */
  getAccountByToken: (token) => {
    let params = {
      "token": token
    }
    return request({
      method: 'post',
      url: '/system/account/getAccountByToken',
      params
    });
  },
  /**
   * 获得账户列表
   * @param params
   */
  list: (params) => {
    return request({
      method: 'post',
      url: '/system/account/list',
      params
    });
  },
  /**
   * 根据id获得账户
   * @param id
   */
  getAccountById: (id) => {
    let params = {
      "id": id
    }
    return request({
      method: 'post',
      url: '/system/account/getAccountById',
      params
    });
  },
  /**
   * 添加、编辑账户
   * @param params
   */
  addOrEdit: (params) => {
    return request({
      method: 'post',
      url: '/system/account/addOrEdit',
      params
    });
  },
  /**
   * 删除账户
   * @param params
   */
  delete: (params) => {
    return request({
      method: 'post',
      url: '/system/account/delete',
      params
    });
  }
}
