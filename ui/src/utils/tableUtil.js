import { Env, Constant } from '@/config'

export default {
  /**
   * 初始化bt表格
   */
  initTable: (target, options) => {
    let defaultOptions = {
      cache: false,
      ajax: function(response){
        //全局ajax做拦截
        options.api(options.params).then(res => {
          response.success(res.data)
        }).catch(res => {
          response.error(res)
        })
      },
      queryParams : function(params) {	  //上传服务器的参数
        var temp = {
          //每页显示数量
          page_size: params.limit,
          //当前页码
          page_currentPageNum: (params.offset / params.limit) + 1,
          //排序
          page_sort: params.sort+" "+params.order
        };
        //处理自定义参数
        if(options.params){
          for(let key in options.params){
            if(!options.params[key] || options.params[key]==""){
              options.params[key]=null
            }
          }
        }else{
          options.params = {}
        }
        return Object.assign(options.params,temp);
      },
      singleSelect: true,           //单选
      pagination: true,             //是否显示分页（*）
      sortable: true,               //是否启用排序
      onlyInfoPagination:false,			//是否仅仅显示分页信息，不显示分页导航
      sidePagination: "server",     //分页方式：client客户端分页，server服务端分页（*）
      pageNumber:1,                 //初始化加载第一页，默认第一页
      pageSize: 10,                 //每页的记录行数（*）
      pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
      // search: true,                 //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
      // strictSearch: false,          //精确搜索
      // searchOnEnterKey: true,				//回车触发search，否则自动
      showColumns: true,                  //是否显示所有的列
      showRefresh: true,                  //是否显示刷新按钮
      minimumCountColumns: 2,             //最少允许的列数
      clickToSelect: true,                //是否启用点击选中行
//        height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
      uniqueId: "id",                 //每一行的唯一标识，一般为主键列
      showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
      cardView: false,                    //是否显示详细视图
      detailView: false,                   //是否显示父子表
      columns : []
    }
    let initOptions = Object.assign({},defaultOptions,options)
    $(target).bootstrapTable(initOptions)
  }
}
