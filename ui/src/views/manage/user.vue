<template>
  <div class="app-container">
    <el-container>
      <el-main>
        <el-form label-position="right" :inline="true" ref="form" :model="searchForm" label-width="90px">
          <el-form-item label="应用">
            <app-select @appChange="changeApp"></app-select>
          </el-form-item>
          <el-form-item label="用户代码">
            <el-input v-model="searchForm.userName" @keyup.enter.native="search"/>
          </el-form-item>
          <el-form-item label="用户名称">
            <el-input v-model="searchForm.name" @keyup.enter.native="search"/>
          </el-form-item>
          <el-form-item class="form-search-btn">
            <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            <el-button v-if="isSystemAccount" type="primary" icon="el-icon-edit" @click="handleAdd">添加</el-button>
          </el-form-item>
        </el-form>
        <table id="table"></table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import Api from "@/api/basic/user"
  import Fileter from "@/utils/filter"
  import TableUtil from "@/utils/tableUtil"
  import AppSelect from "@/components/form/appSelect"
  import { isSystemAccount, isSuperAdmin, isAdmin, hasAdminRole } from "@/utils/auth"

  export default {
    components: {AppSelect},
    data() {
      return {
        searchForm: {
          appId: '',
          name: '',
          userName: ''
        },
        tableSelector: "#table"
      }
    },
    computed: {
      hasAdmin() {
        return hasAdminRole()
      },
      isAdmin() {
        return isAdmin()
      },
      isSuperAdmin() {
        return isSuperAdmin()
      },
      isSystemAccount() {
        return isSystemAccount()
      }
    },
    mounted(){
      this.$nextTick(function () {
        this.initTable();
      })
    },
    methods: {
      changeApp(val){
        this.searchForm.appId = val
        this.search()
      },
      search(){
        $(this.tableSelector).bootstrapTable("refresh")
      },
      initTable() {
        TableUtil.initTable(this.tableSelector,{
          api: Api.list,
          method: 'post',
          params: this.searchForm,
          sortName: "name",
          sortOrder: "asc",
          columns : [
            {checkbox: true},
            {title:"id", field:"id", visible:false},
            {title:"用户代码", field:"userName",sortable:true},
            {title:"用户名称", field:"name",sortable:true},
            {title:"所属应用", field:"appId",sortable:true, formatter:this.applicationShowFormat},
            {title:"状态", field:"isAvailable",sortable:true,formatter: this.isAvailableFormat},
            {title:"操作人", field:"optName"},
            {title:"操作时间", field:"optDate"},
            {title:'操作', field :'_opt', formatter:this.format }
          ]
        })
        //添加操作按钮事件
        $(this.tableSelector).on("click", ".opt_edit", this.handleEdit)
        $(this.tableSelector).on("click", ".opt_delete", this.handleDelete)
        $(this.tableSelector).on("click", ".opt_disable", this.handleDisable)
        $(this.tableSelector).on("click", ".opt_enable", this.handleEnable)
        $(this.tableSelector).on("click", ".opt_relation", this.handleOrgRelation)
      },
      format(value, row, index){
        let optDiv = $("<div></div>")
        //分组按钮
        let groupBtn = $("<div class=\"btn-group\"><button type=\"button\" class=\"btn btn-default btn-sm space-right-mini dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">更多... <span class=\"caret\"></span></button><ul class=\"dropdown-menu\"></ul></div>");
        let ul= groupBtn.find(".dropdown-menu")
        ul.append("<li><a data-id='"+row.id+"' class='opt_edit' href='javascript:void(0)'>编辑</a></li>")
        ul.append("<li><a data-id='"+row.id+"' class='opt_relation' href='javascript:void(0)'>组织关系</a></li>")
        optDiv.append(groupBtn)
        //直接按钮
        optDiv.append("<button data-id='"+row.id+"' type='button' class='opt_delete btn btn-danger btn-sm space-right-mini'>删除</button>")
        optDiv.append("<button data-id='"+row.id+"' type='button' class='opt_disable btn btn-warning btn-sm space-right-mini'>禁用</button>")
        optDiv.append("<button data-id='"+row.id+"' type='button' class='opt_enable btn btn-success btn-sm space-right-mini'>启用</button>")
        return optDiv.html()
      },
      applicationShowFormat(value, row, index) {
        return Fileter.appidToName(value)
      },
      isAvailableFormat(value, row, index) {
        if(value==1){
          return "<span class='label label-success'>启用</span>"
        }else{
          return "<span class='label label-warning'>禁用</span>"
        }
      },
      getSelectedId(e){
        let id= $(e.target).data("id")
        if(!id){
          let selections = $(this.tableSelector).bootstrapTable('getSelections')
          if(selections && selections.length==1 && selections[0]){
            id = selections[0]["id"]
          }
        }
        return id;
      },
      handleAdd(e){
        console.log(this.getSelectedId(e))
      },
      handleEdit(e){
        console.log(this.getSelectedId(e))
      },
      handleDelete(e){
        console.log(this.getSelectedId(e))
      },
      handleDisable(e){
        console.log(this.getSelectedId(e))
      },
      handleEnable(e){
        console.log(this.getSelectedId(e))
      },
      handleOrgRelation(e){
        console.log(this.getSelectedId(e))
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
