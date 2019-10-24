<template>
  <div class="app-container">
    <el-container>
      <el-main>
        <el-form label-position="right" :inline="true" ref="form" :model="searchForm" label-width="90px">
          <el-form-item label="应用">
            <app-select :value="searchForm.appId" @appChange="changeApp"></app-select>
          </el-form-item>
          <el-form-item label="插件名称">
            <el-input v-model="searchForm.plugName" @keyup.enter.native="search"/>
          </el-form-item>
          <el-form-item class="form-search-btn">
            <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            <el-button v-if="isSystemAccount" type="primary" icon="el-icon-edit" @click="handleAdd">添加</el-button>
          </el-form-item>
        </el-form>
        <table id="table"></table>
      </el-main>
    </el-container>
    <!--添加、编辑表单-->
    <el-dialog :title="handleDialogTitle" :visible.sync="handleDialogVisible">
      <el-form :rules="rules" label-position="top" label-width="100px" :inline="false" ref="handleForm" :model="handleForm">
        <el-form-item v-show="false" v-if="isAddId" label="插件ID" prop="id">
          <el-input v-model="handleForm.id" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="插件名称" prop="plugName">
          <el-input :disabled="isNameDisable" v-model="handleForm.plugName" placeholder="请填写插件名称"></el-input>
        </el-form-item>
        <el-form-item label="所属应用" prop="appId">
          <app-select :value="handleForm.appId" @appChange="changeEditApp" type="00"></app-select>
        </el-form-item>
        <el-form-item label="插件类型" prop="plugType">
          <el-select v-model="handleForm.plugType" placeholder="请选择插件类型" class="top-input">
            <el-option label="单行文本" value="1"></el-option>
            <el-option label="多行文本" value="2"></el-option>
            <el-option label="日期框" value="3"></el-option>
            <el-option label="单选框" value="4"></el-option>
            <el-option label="复选框" value="5"></el-option>
            <el-option label="下拉框" value="6"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="isAvailable">
          <el-select @change="changeStatus" v-model="handleForm.isAvailable" placeholder="请选择状态" class="top-input">
            <el-option label="启用" value=1></el-option>
            <el-option label="禁用" value=0></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="execAddOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
    <!--查件值设置-->
    <el-dialog title="插件数据配置" :visible.sync="handleDynamicDialogVisible" width="480px">
      <el-form :inline="true" :model="dynamicForm" ref="dynamicForm" label-width="80px" class="demo-dynamic">
        <el-form-item v-show="false" label="插件ID" prop="plugId">
          <el-input v-model="dynamicForm.plugId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item
          v-for="(dataItem, index) in dynamicForm.plugData"
          :label="'数据项' + (index+1)"
          :key="dataItem.id"
          :prop="'plugData.'+(index+1)"
          :rules="[{required: true, message: '数据项key和value都不能为空', trigger: 'blur', validator: validateItem}]"
        >
          <el-input style="width:120px;margin-right:5px" class="plug-item name" v-model="dataItem.plugDataKey" placeholder="数据项key"></el-input>
          <el-input style="width:120px;margin-right:5px" class="plug-item value" v-model="dataItem.plugDataValue" placeholder="数据项value"></el-input>
          <el-button @click.prevent="removeItem(dataItem)">删除</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="addItem">新增数据项</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleDynamicDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="savePluginData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import Api from "@/api/extension/plugin"
  import DataApi from "@/api/extension/pluginData"
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
          plugName: ''
        },
        dynamicForm: {
          plugId:null,
          plugData: [{
            plugDataKey: null,
            plugDataValue: null,
            id: Date.now()
          }]
        },
        tableSelector: "#table",
        handleDialogVisible: false,
        handleDynamicDialogVisible: false,
        isNameDisable: false,
        isAddId: false,
        handleDialogTitle: "",
        handleForm: {
          isAdd: "1",  //当前是添加还是编辑。1添加0编辑
          id: "",
          appId: "",
          plugType: "",
          plugName: "",
          isAvailable: ""
        },
        rules:{
          appId: [{ required: true, message: '请选择所属应用', trigger: 'change' }],
          isAvailable: [{ required: true, message: '请选择状态', trigger: 'change' }],
          plugType: [{ required: true, message: '请选择插件名称', trigger: 'blur' }],
          plugName: [{ required: true, message: '请填写插件名称', trigger: 'blur' }]
        },
        validateItem: (rule, value, callback)=>{
          let flag = true
          $("label[for='"+rule.field+"']").next(".el-form-item__content").find(".plug-item input").each((index,ele) =>{
            let val = $(ele).val()
            if(!val || val==""){
              flag = false
            }
          })
          if(!flag){
            callback(new Error(rule.message));
          }
          callback();
        }
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
      changeEditApp(val){
        this.handleForm.appId = val
      },
      search(){
        $(this.tableSelector).bootstrapTable("refresh")
      },
      initTable() {
        TableUtil.initTable(this.tableSelector,{
          api: Api.list,
          method: 'post',
          params: this.searchForm,
          sortName: "plugName",
          sortOrder: "asc",
          columns : [
            {checkbox: true},
            {title:"id", field:"id", visible:false},
            {title:"插件类型", field:"plugType",sortable:true, formatter:this.typeShowFormat},
            {title:"插件名称", field:"plugName",sortable:true},
            {title:"所属应用", field:"appId",sortable:true, formatter:this.applicationShowFormat},
            {title:"状态", field:"isAvailable",sortable:true,formatter: this.isAvailableFormat},
            {title:"操作人", field:"optName_name"},
            {title:"操作时间", field:"optDate"},
            {title:'操作', field :'_opt', formatter:this.format }
          ]
        })
        //添加操作按钮事件
        $(this.tableSelector).on("click", ".opt_edit", this.handleEdit)
        $(this.tableSelector).on("click", ".opt_delete", this.handleDelete)
        $(this.tableSelector).on("click", ".opt_disable", this.handleDisable)
        $(this.tableSelector).on("click", ".opt_enable", this.handleEnable)
        $(this.tableSelector).on("click", ".opt_plugData", this.handlePlugData)
      },
      format(value, row, index){
        let optDiv = $("<div></div>")
        //分组按钮
        let groupBtn = $("<div class=\"btn-group\"><button type=\"button\" class=\"btn btn-default btn-sm space-right-mini dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">更多... <span class=\"caret\"></span></button><ul class=\"dropdown-menu\"></ul></div>");
        let ul= groupBtn.find(".dropdown-menu")
        ul.append("<li><a data-index='"+index+"' data-id='"+row.id+"' class='opt_edit' href='javascript:void(0)'>编辑</a></li>")
        if(row.plugType==4 ||row.plugType==5 || row.plugType==6){
          ul.append("<li><a data-index='"+index+"' data-id='"+row.id+"' class='opt_plugData' href='javascript:void(0)'>数据配置</a></li>")
        }
        optDiv.append(groupBtn)
        //直接按钮
        optDiv.append("<button data-index='"+index+"' data-id='"+row.id+"' type='button' class='opt_delete btn btn-danger btn-sm space-right-mini'>删除</button>")
        optDiv.append("<button data-index='"+index+"' data-id='"+row.id+"' type='button' class='opt_disable btn btn-warning btn-sm space-right-mini'>禁用</button>")
        optDiv.append("<button data-index='"+index+"' data-id='"+row.id+"' type='button' class='opt_enable btn btn-success btn-sm space-right-mini'>启用</button>")
        optDiv.data("row",row);
        return optDiv.html()
      },
      typeShowFormat(value, row, index) {
        if(value==1){
          return "单行文本"
        }else if(value==2){
          return "多行文本"
        }else if(value==3){
          return "日期框"
        }else if(value==4){
          return "单选框"
        }else if(value==5){
          return "复选框"
        }else if(value==6){
          return "下拉框"
        }else{
          return "未知类型"
        }
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
      /**
       * 状态改变
       */
      changeStatus(state){

      },
      getSelectedId(e){
        let id= $(e.target).data("id")
        if(!id){
          // let selections = $(this.tableSelector).bootstrapTable('getSelections')
          // if(selections && selections.length==1 && selections[0]){
          //   id = selections[0]["id"]
          // }
          return null
        }
        return id;
      },
      getSelectedRow(e){
        let row= $(this.tableSelector).bootstrapTable("getData")[$(e.target).data("index")]
        if(!row){
         return {}
        }
        return row;
      },
      handleAdd(){
        this.handleDialogVisible = true
        this.handleDialogTitle = "添加插件"
        this.isAddId = false
        this.isNameDisable = false
        this.handleForm.isAdd = "1"
        this.$nextTick(()=>{
          this.$refs.handleForm.resetFields()
        })
      },
      handleEdit(e){
        Api.getPlugInById(this.getSelectedId(e)).then(res => {
          let data = res.data
          this.handleForm = Object.assign({},data)
        })
        this.handleDialogVisible = true
        this.handleDialogTitle = "编辑插件"
        this.isAddId = true
        this.isNameDisable = true
        this.handleForm.isAdd = "0"
      },
      execAddOrUpdate(e){
        this.$refs['handleForm'].validate((valid) => {
          if (valid) {
            Api.addOrEdit(this.handleForm).then(response => {
              let data = response.data
              if(data.flag==1){
                this.handleDialogVisible = false
                this.search()
              }
            })
          } else {
            this.$message({
              message: '请检查您填写的信息',
              duration:1000,
              type: 'warning'
            });
            return false
          }
        })
      },
      handleDelete(e){
        this.$confirm('此操作将删除该插件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let row = this.getSelectedRow(e)
          Api.delete(row.id).then(response => {
            let data = response.data
            if(data.flag==1){
              this.search()
            }
          })
        }).catch(() => {});
      },
      handleDisable(e){
        let row = this.getSelectedRow(e)
        if(0==row.isAvailable){
          this.$message({
            showClose: true,
            message: "该账户已经被禁用！",
            type: 'warning'
          });
          return
        }
        Api.disable(row.id).then(response => {
          let data = response.data
          if(data.flag==1){
            this.search()
          }
        })
      },
      handleEnable(e){
        let row = this.getSelectedRow(e)
        if(1==row.isAvailable){
          this.$message({
            showClose: true,
            message: "该账户已经被启用！",
            type: 'warning'
          });
          return
        }
        Api.enable(row.id).then(response => {
          let data = response.data
          if(data.flag==1){
            this.search()
          }
        })
      },
      addItem() {
        this.dynamicForm.plugData.push({
          plugDataKey: null,
          plugDataValue: null,
          id: Date.now()
        });
      },
      removeItem(item) {
        let index = this.dynamicForm.plugData.indexOf(item)
        if (index !== -1) {
          this.dynamicForm.plugData.splice(index, 1)
        }
      },
      handlePlugData(e){
        this.dynamicForm.plugId = null
        let row = this.getSelectedRow(e)
        DataApi.list(row.id).then(response => {
          let data = response.data
          this.dynamicForm.plugId = row.id
          this.dynamicForm.plugData = data
          this.handleDynamicDialogVisible = true
        })
      },
      savePluginData(){
        this.$refs["dynamicForm"].validate((valid) => {
          if (valid) {
            let params = {}
            params.plugId = this.dynamicForm.plugId
            params.list = encodeURIComponent(JSON.stringify(this.dynamicForm.plugData))
            DataApi.save(params).then(response => {
              let data = response.data
              if(data.flag==1){
                this.handleDynamicDialogVisible = false
              }
            })
          } else {
            this.$message({
              message: '请检查您填写的信息',
              duration:1000,
              type: 'warning'
            });
            return false;
          }
        })
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
