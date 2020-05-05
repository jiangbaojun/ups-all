<template>
  <div class="app-container">
    <el-container>
      <el-main>
        <el-form label-position="right" :inline="true" ref="form" :model="searchForm" label-width="90px">
          <el-form-item label="应用">
            <el-select @change="search" v-model="searchForm.appId" placeholder="请选择应用">
              <el-option-group
                v-for="group in searchOptions"
                :key="group.label"
                :label="group.label">
                <el-option
                  v-for="item in group.options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-option-group>
            </el-select>
          </el-form-item>
          <el-form-item label="账户名称">
            <el-input v-model="searchForm.acName" @keyup.enter.native="search"/>
          </el-form-item>
          <el-form-item class="form-search-btn">
            <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            <el-button v-if="hasAdmin" type="primary" icon="el-icon-edit" @click="handleAdd">添加</el-button>
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
          <el-table-column sortable prop="appId" label="所属应用">
            <template slot-scope="scope">
              <span>{{ scope.row.appId | applicationShowFilter}}</span>
            </template>
          </el-table-column>
          <el-table-column sortable prop="acName" label="账户名称" width="110">
            <template slot-scope="scope">
              <span>{{ scope.row.acName }}</span>
            </template>
          </el-table-column>
          <el-table-column sortable prop="isAdmin" label="管理员" width="90" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.isAdmin | adminShowFilter }}</span>
            </template>
          </el-table-column>
          <el-table-column sortable prop="isAvailable" label="状态" width="80" align="center">
            <template slot-scope="scope">
              <el-tag :type="scope.row.isAvailable | statusFilter">{{ scope.row.isAvailable | statusShowFilter }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="optName_name" label="操作人" width="90" align="center">
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
              <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
              <el-button type="warning" size="mini" @click="handleForbidOrAble(scope.row,0)">禁用</el-button>
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
        <el-form-item v-show="false" v-if="isAddId" label="账户ID" prop="id">
          <el-input v-model="handleForm.id" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="账户名称" prop="acName">
          <el-input :disabled="isNameDisable" v-model="handleForm.acName" placeholder="请填写账户名称"></el-input>
        </el-form-item>
        <el-form-item label="所属应用" prop="appId">
          <el-select class="top-input" v-model="handleForm.appId" placeholder="请选择应用">
            <el-option-group
              v-for="group in operateOptions"
              :key="group.label"
              :label="group.label">
              <el-option
                v-for="item in group.options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label="账户密码" prop="password">
          <el-input
            type="password"
            v-model="handleForm.password"
            placeholder="请填写账户密码"
            auto-complete="on"
            show-password />
        </el-form-item>
        <el-form-item label="状态" prop="isAvailable">
          <el-select @change="changeStatus" v-model="handleForm.isAvailable" placeholder="请选择状态" class="top-input">
            <el-option label="启用" value=1></el-option>
            <el-option label="禁用" value=0></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="管理员" prop="isAdmin">
          <el-select v-model="handleForm.isAdmin" placeholder="是否设为管理员" class="top-input">
            <el-option label="是" value=1></el-option>
            <el-option label="否" value=0></el-option>
          </el-select>
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
  import api from "@/api/system/account"
  import Fileter from "@/utils/filter"
  import { Env, Constant } from '@/config'
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
      adminShowFilter(status) {
        const statusMap = {
          "1": '是',
          "0": '否'
        }
        return statusMap[status]
      },
      statusFilter(status) {
        const statusMap = {
          "1": 'success',
          "0": 'warning'
        }
        return statusMap[status]
      },
      applicationShowFilter(appId) {
        return Fileter.appidToName(appId)
      }
    },
    data() {
      return {
        searchForm: {
          appId: '',
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
        isAddId: false,
        isNameDisable: false,
        handleDialogTitle: "",
        handleForm: {
          isAdd: "1",  //当前是添加还是编辑。1添加0编辑
          id: "",
          appId: "",
          acName: "",
          password: "",
          isAdmin: "",
          isAvailable: ""
        },
        rules:{
          acName: [{ required: true, message: '请填写账户名称', trigger: 'blur' }],
          password: [{ min: 3, max: 15, message: '账户密码长度3-15位', trigger: 'blur' }],
          appId: [{ required: true, message: '请选择账户所属应用', trigger: 'change' }],
          isAdmin: [{ required: true, message: '请选择是否设置为管理员', trigger: 'change' }],
          isAvailable: [{ required: true, message: '请选择状态', trigger: 'change' }]
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
      },
      searchOptions() {
        let newOptions = []
        if(this.isSystemAccount){
          newOptions = [{
            value:"",
            label:"全部"
          },{
            value:Constant.allAppKey,
            label:"系统级应用"
          }]
        }
        return Fileter.addOptionGroup(this.$store.getters.appList,newOptions,"热门","应用")
      },
      operateOptions() {
        let newOptions = []
        if(this.isSystemAccount){
          newOptions = [{
            value:Constant.allAppKey,
            label:"系统级应用"
          }]
        }
        return Fileter.addOptionGroup(this.$store.getters.appList,newOptions,"热门","应用")
      }
    },
    mounted() {
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
        api.list(param).then(res => {
          let data = res.data
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
       * 状态改变
       */
      changeStatus(state){
        if(this.$store.getters.me.id==this.handleForm.id && state==0){
          this.$message({
            showClose: true,
            message: '请不要在编辑过程中禁用您自己的账户！',
            type: 'warning'
          })
          this.handleForm.isAvailable = "1"
        }
      },
      /**
       * 弹出添加账户
       */
      handleAdd(){
        this.handleDialogVisible = true
        this.handleDialogTitle = "添加账户"
        this.isAddId = false
        this.isNameDisable = false
        this.handleForm.isAdd = "1"
        this.$nextTick(()=>{
          this.$refs.handleForm.resetFields()
        })
      },
      /**
       * 弹出编辑账户
       */
      handleEdit(row){
        api.getAccountById(row.id).then(res => {
          let data = res.data
          this.handleForm = Object.assign({},data)
        })
        this.handleDialogVisible = true
        this.handleDialogTitle = "编辑账户"
        this.isAddId = true
        this.isNameDisable = true
        this.handleForm.isAdd = "0"
      },
      /**
       * 执行添加或编辑账户
       */
      execAddOrUpdate(){
        this.$refs['handleForm'].validate((valid) => {
          if (valid) {
            api.addOrEdit(this.handleForm).then(response => {
              let data = response.data
              if(data.flag==1){
                this.handleDialogVisible = false
                this.fetchData()
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
       * 禁用、启用账户
       */
      handleForbidOrAble(row,state){
        if(state==row.isAvailable){
          let msg = "该账户已经被禁用！"
          if(state==1){
            msg = "该账户已经被启用！"
          }
          this.$message({
            showClose: true,
            message: msg,
            type: 'warning'
          });
          return
        }
        if(this.$store.getters.me.id==row.id && state==0){
          this.$confirm('如果您禁用了自己的账户，本次禁用操作成功后，您将会自动退出, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$confirm('您确认要禁用自己的账户?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.execForbidOrAble(row,state,1)
            }).catch(() => {});
          }).catch(() => {});
          return
        }
        this.execForbidOrAble(row,state,0)
      },
      /**
       * 执行禁用、启用账户
       */
      execForbidOrAble(row,state,logoutFlag){
        let params = {}
        params.id = row.id
        params.isAvailable = state
        api.addOrEdit(params).then(response => {
          let data = response.data
          if(logoutFlag==1){
            this.$store.dispatch('LogOut').then(() => {
              location.reload() // 为了重新实例化vue-router对象 避免bug
            })
          }
          if(data.flag==1){
            this.fetchData()
            //更新账户列表
            this.$store.dispatch('updateAppList')
          }
        })
      },
      /**
       * 删除账户
       */
      handleDelete(row){
        if(this.$store.getters.me.id==row.id){
          this.$alert("您不可以删除自己的账户！", '警告信息', {
            type: 'warning'
          });
          return
        }
        this.$confirm('此操作将删除该账户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          api.delete(row).then(response => {
            let data = response.data
            if(data.flag==1){
              this.fetchData()
            }
          })
        }).catch(() => {});
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
