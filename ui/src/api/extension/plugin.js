import request from '@/utils/request'
import { Env, Constant } from '@/config'

export default {

  /**
   * 插件列表
   */
  list: (params) => {
    return request({
      method: 'post',
      url: '/extension/plug/list',
      params
    });
  },
  /**
   * 根据id查找插件
   */
  getPlugInById: (id) => {
    let params = {
      "id": id
    }
    return request({
      method: 'post',
      url: '/extension/plug/getPlugInById',
      params
    });
  },
  /**
   * 添加、编辑插件
   */
  addOrEdit: (params) => {
    return request({
      method: 'post',
      url: '/extension/plug/addOrEdit',
      params
    });
  },
  /**
   * 禁用插件
   */
  disable: (id) => {
    let params = {
      "id": id
    }
    return request({
      method: 'post',
      url: '/extension/plug/disable',
      params
    });
  },
  /**
   * 启用插件
   */
  enable: (id) => {
    let params = {
      "id": id
    }
    return request({
      method: 'post',
      url: '/extension/plug/enable',
      params
    });
  },
  /**
   * 删除插件
   */
  delete: (id) => {
    let params = {
      "id": id
    }
    return request({
      method: 'post',
      url: '/extension/plug/delete',
      params
    });
  },
}
