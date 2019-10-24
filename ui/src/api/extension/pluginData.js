import request from '@/utils/request'
import { Env, Constant } from '@/config'

export default {

  /**
   * 插件数据列表
   */
  list: (plugId) => {
    let params = {
      "plugId": plugId
    }
    return request({
      method: 'post',
      url: '/extension/plug/data/list',
      params
    });
  },

  /**
   * 保存插件数据
   */
  save: (params) => {
    return request({
      method: 'post',
      url: '/extension/plug/data/save',
      params
    });
  }
}
