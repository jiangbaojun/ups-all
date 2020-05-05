<template>
  <div class="app-container">
    <el-container>
      <el-main>
        <el-form label-position="right" :inline="true" ref="form" :model="searchForm" label-width="90px">
          <el-form-item label="应用ID">
            <el-input v-model="searchForm.id"/>
          </el-form-item>
          <el-form-item label="应用名称">
            <el-input v-model="searchForm.appName" @keyup.enter.native="search"/>
          </el-form-item>
          <el-form-item class="form-search-btn">
            <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            <el-button v-if="isSuperAdmin" type="primary" icon="el-icon-edit" @click="handleAdd">添加</el-button>
          </el-form-item>
        </el-form>
        <el-table
          border
          fit
          highlight-current-row
          element-loading-text="Loading"
          :default-sort="{prop:'appName',order:'ascending'}"
          v-loading="listLoading"
          :data="list"
          @sort-change="sortChange">
          <el-table-column label="序号" width="50" align="center">
              <template slot-scope="scope">
              {{ scope.$index + 1}}
            </template>
          </el-table-column>
          <el-table-column sortable prop="id" label="应用ID">
            <template slot-scope="scope">
              <span>{{ scope.row.id }}</span>
            </template>
          </el-table-column>
          <el-table-column sortable prop="appName" label="应用名称">
            <template slot-scope="scope">
              <span>{{ scope.row.appName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remark" label="备注">
            <template slot-scope="scope">
              <span>{{ scope.row.remark }}</span>
            </template>
          </el-table-column>
          <el-table-column sortable prop="isAvailable" label="状态" width="80" align="center">
            <template slot-scope="scope">
              <el-tag :type="scope.row.isAvailable | statusFilter">{{ scope.row.isAvailable | statusShowFilter }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="optName_name" label="操作人" width="100" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.optName_name }}</span>
            </template>
          </el-table-column>
          <el-table-column sortable prop="optDate" label="操作日期" width="180" align="center">
            <template slot-scope="scope">
              <i class="el-icon-time"/>
              <span>{{ scope.row.optDate }}</span>
            </template>
          </el-table-column>
          <el-table-column v-if="hasAdmin" label="操作" align="center" width="300">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button v-if="isSuperAdmin" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
              <el-button v-if="isSuperAdmin" type="warning" size="mini" @click="handleForbidOrAble(scope.row,0)">禁用</el-button>
              <el-button type="success" size="mini" @click="handleForbidOrAble(scope.row,1)">启用</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          :current-page.sync ="tablePage.current"
          :page-sizes.sync="tablePage.pages"
          :page-size.sync="tablePage.size"
          :total.sync="tablePage.total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange">
        </el-pagination>
      </el-main>
    </el-container>
    <!--添加、编辑表单-->
    <el-dialog :title="handleDialogTitle" label-width="100px" :visible.sync="handleDialogVisible">
      <el-form :rules="rules" label-position="top" :inline="false" ref="handleForm" :model="handleForm">
        <el-form-item v-show="false" v-if="isAddId" label="应用ID" prop="id">
          <el-input v-model="handleForm.id" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="应用名称" prop="appName">
          <el-input :disabled="isNameDisable" v-model="handleForm.appName" placeholder="请填写应用名称"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="isAvailable">
          <el-select @change="changeStatus" v-model="handleForm.isAvailable" placeholder="请选择状态" class="top-input">
            <el-option label="启用" value=1></el-option>
            <el-option label="禁用" value=0></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark" >
          <el-input v-model="handleForm.remark"
                    placeholder="这里填写应用备注"
                    type="textarea"
                    :autosize="{ minRows: 3, maxRows: 16}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="execAddOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import Api from "@/api/system/application"
  import { isSystemAccount, isSuperAdmin, isAdmin, hasAdminRole } from "@/utils/auth"
  export default {
    filters: {
      statusShowFilter(status) {
        const statusMap = {
          "1": '启用',
          "0": '禁用'
        }
        return statusMap[status]
      },
      statusFilter(status) {
        const statusMap = {
          "1": 'success',
          "0": 'warning'
        }
        return statusMap[status]
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
    data() {
      return {
        searchForm: {
          id: '',
          appName: ''
        },
        tablePage: {
          current: 1,
          pages: [5, 10, 20, 30, 50, 100],
          size: 10,
          total: 0,
          prop: null,
          order: null
        },
        list: null,
        listLoading: true,
        handleDialogVisible: false,
        isNameDisable: false,
        isAddId: false,
        handleDialogTitle: "",
        handleForm: {
          isAdd: "1",  //当前是添加还是编辑。1添加0编辑
          id: "",
          appName: "",
          remark: "",
          isAvailable: ""
        },
        rules:{
          appName: [{ required: true, message: '请填写应用名称', trigger: 'blur' }],
          isAvailable: [{ required: true, message: '请选择状态', trigger: 'change' }]
        }
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      /**
       * 搜索
       */
      search(){
        this.fetchData()
      },
      /**
       * 获取表格数据
       */
      fetchData() {
        this.listLoading = true
        const param = {}
        param.tablePageInfo = this.tablePage
        Object.assign(param,this.searchForm)
        Api.list(param).then(response => {
          let data = response.data
          this.tablePage.total = data.total || data.rows.length
          this.list = data.rows
          this.listLoading = false
        })
      },
      handleSizeChange(val) {
        this.fetchData();
      },
      handleCurrentChange (val){
        this.fetchData();
      },
      sortChange(column) {
        if(column.column){
          this.tablePage.prop = column.prop || column.column.prop;
          this.tablePage.order = column.order || column.column.order;
        }else{
          this.tablePage.prop = null;
          this.tablePage.order = null;
        }
        this.fetchData();
      },
      /**
       * 弹出添加应用
       */
      handleAdd(){
        this.handleDialogVisible = true
        this.handleDialogTitle = "添加应用"
        this.isAddId = false
        this.isNameDisable = false
        this.handleForm.isAdd = "1"
        this.$nextTick(()=>{
          this.$refs.handleForm.resetFields()
        })
      },
      /**
       * 弹出编辑应用
       */
      handleEdit(row){
        this.handleDialogVisible = true
        this.handleDialogTitle = "编辑应用"
        this.isAddId = true
        this.isNameDisable = true
        this.handleForm.isAdd = "0"
        this.handleForm = Object.assign({},row)
      },
      /**
       * 执行添加或编辑应用
       */
      execAddOrUpdate(){
        this.$refs['handleForm'].validate((valid) => {
          if (valid) {
            Api.addOrEdit(this.handleForm).then(response => {
              let data = response.data
              if(data.flag==1){
                this.handleDialogVisible = false
                this.fetchData()
                //更新选择应用列表
                this.$store.dispatch('updateAppList')
              }
            })
          } else {
            this.$message({
              message: '请检查您填写的信息',
              duration:1000,
              type: 'warning'
            })
            return false
          }
        })
      },
      /**
       * 状态改变
       */
      changeStatus(val){
        if(this.$store.getters.application==this.handleForm.id && val==0){
          this.$message({
            showClose: true,
            message: '不可以禁用当前选择的应用！',
            type: 'warning'
          })
          this.handleForm.isAvailable = "1"
        }
      },
      /**
       * 禁用应用
       */
      handleForbidOrAble(row,state){
        if(state==row.isAvailable){
          let msg = "该应用已经被禁用！"
          if(state==1){
            msg = "该应用已经被启用！"
          }
          this.$message({
            showClose: true,
            message: msg,
            type: 'warning'
          });
          return
        }
        if(this.$store.getters.application==row.id && state==0){
          this.$alert("您当前已经选择了该应用，不可以禁用当前选择的应用！", '警告信息', {
            type: 'warning'
          });
          return
        }
        let params = {}
        params.id = row.id
        params.isAvailable = state
        Api.addOrEdit(params).then(response => {
          let data = response.data
          if(data.flag==1){
            this.fetchData()
            //更新选择应用列表
            this.$store.dispatch('updateAppList')
          }
        })
      },
      /**
       * 删除应用
       */
      handleDelete(row){
        if(this.$store.getters.application==row.id){
          this.$alert("您不可以删除当前选择的应用！", '警告信息', {
            type: 'warning'
          });
          return
        }
        this.$confirm('此操作将删除该应用, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          Api.delete(row).then(response => {
            let data = response.data
            if(data.flag==1){
              this.fetchData()
              //更新选择应用列表
              this.$store.dispatch('updateAppList')
            }
          })
        }).catch(() => {});
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
