import store from '../store'
import { Env, Constant } from '@/config'

export default {
  /**
   * 应用id映射名称
   */
  appidToName: (appId) => {
    if(appId==Constant.allAppKey){
      return "系统级应用"
    }
    let appList = store.getters.appList
    if(appList!=null && appList.length>0){
      for(let i=0;i<appList.length;i++){
        let item = appList[i]
        if(item.value==appId){
          return item.label
        }
      }
    }
  },
  /**
   * 添加下拉分组option
   */
  addOptionGroup: (options,newOptions,newLabel,oldLabel) => {
    let result = []
    let newObj = {"label": newLabel,"options":[]}
    let oldObj = {"label": oldLabel,"options":[]}
    if(newOptions && newOptions.length>0){
      for(let index in newOptions){
        newObj.options.push(newOptions[index])
      }
      result.push(newObj)
    }
    if(options && options.length>0){
      for(let index in options){
        oldObj.options.push(options[index])
      }
      result.push(oldObj)
    }
    return result
  },
  /**
   * 添加下拉options
   */
  addOption: (options,newOptions) => {
    let result = []
    if(newOptions && newOptions.length>0){
      for(let index in newOptions){
        result.push(newOptions[index])
      }
    }
    if(options && options.length>0){
      for(let index in options){
        result.push(options[index])
      }
    }
    return result
  }
}
