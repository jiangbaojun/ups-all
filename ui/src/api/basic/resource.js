import request from '@/utils/request'
import { Env, Constant } from '@/config'

export default {

  /**
   * 资源列表url
   */
  listUrl: () => {
    return Env.BASE_API+"/basic/resource/list"
  }
}
