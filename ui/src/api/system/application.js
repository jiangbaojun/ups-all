import request from '@/utils/request'

export default {
  /**
   * 获得应用列表
   * @param params
   */
  list: (params) => {
    return request({
      method: 'post',
      url: '/system/application/list',
      params
    });
  },
  /**
   * 根据条件获得应用有效列表
   * @param params
   */
  getApplications: (params) => {
    return request({
      method: 'post',
      url: '/system/application/getApplications',
      params
    });
  },
  /**
   * 添加、编辑应用
   * @param params
   */
  addOrEdit: (params) => {
    return request({
      method: 'post',
      url: '/system/application/addOrEdit',
      params
    });
  },
  /**
   * 删除应用
   * @param params
   */
  delete: (params) => {
    return request({
      method: 'post',
      url: '/system/application/delete',
      params
    });
  }
}
