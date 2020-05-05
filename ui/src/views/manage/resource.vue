<template>
  <div class="app-container">
    <el-container>
      <el-main>
        <table id="table"></table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import api from "@/api/basic/resource"
  import TableUtil from "@/utils/tableUtil"
  export default {
    data() {
      return {
        searchForm: {
          id: '',
          appName: ''
        }
      }
    },
    mounted(){
      this.initTable();
    },
    methods: {
      initTable() {
        TableUtil.initTable("#table",{
          url: api.listUrl(),         //请求后台的URL（*）
          method: 'post',                      //请求方式（*）
          toolbar: '#toolbar',                //工具按钮用哪个容器
          sortName: "asc",                   //默认排序字段
          sortOrder: "asc",                   //默认排序方式
          columns : [
            {checkbox: true},
            // {title:"id", field:"id"},
            // {title:"父级id", field:"pid"},
            // {title:"应用id", field:"appId"},
            // {title:"资源名称", field:"resName"},
            // {title:"资源编码", field:"resCode"},
            // {title:"资源类型，1目录 2模块 3按钮", field:"resType"},
            // {title:"资源地址", field:"resUrl"},
            // {title:"资源图标", field:"resIcon"},
            // {title:"资源在同级目录内的排序序号，1开始", field:"resOrder"},
            // {title:"资源描述", field:"remark"},
            // {title:"是否有效，1有效，0无效", field:"isAvailable"},
            // {title:"操作人", field:"optName"},
            // {title:"操作时间", field:"optDate"},

            {title:'工号', field:'id', visible:false, sortable:true},
            {title:'姓名', field:'name', sortable:true},
            {title:'年龄', field:'age'},
            {title:'入职日期', field:'birthday', align:'center',sortable:true},
            {title:'操作', field :'_opt', formatter: this.format }
          ]
        })
      },
      format(value, row, index){
        let opt = "<a href='javascript:void(0)'>添加  </a>"+
          "<a href='javascript:void(0)'>编辑  </a>"+
          "<a href='javascript:void(0)'>删除</a>";
        return opt
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
