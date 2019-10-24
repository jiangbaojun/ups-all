import request from '@/utils/request'
import { Env, Constant } from '@/config'

export default {

  /**
   * 用户列表
   */
  list: (params) => {
    return request({
      method: 'post',
      url: '/basic/user/list',
      params
    });
  },
}
