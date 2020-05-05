<template>
  <div class="navbar">
    <!--sidebar切换按钮-->
    <hamburger :toggle-click="toggleSideBar" :is-active="sidebar.opened" class="hamburger-container"/>
    <!--面包屑-->
    <breadcrumb />
    <!--全局应用选择-->
    <!--<el-select @change="selectApp" class="select-app" v-model="value" placeholder="请选择应用">
      <el-option-group
        v-for="group in options"
        :key="group.label"
        :label="group.label">
        <el-option
          v-for="item in group.options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-option-group>
    </el-select>-->
    <!--头像及下拉-->
    <el-dropdown class="avatar-container" trigger="click">
      <div class="avatar-wrapper">
        <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
        <i class="el-icon-caret-bottom"/>
      </div>
      <el-dropdown-menu slot="dropdown" class="user-dropdown">
        <router-link class="inlineBlock" to="/home">
          <el-dropdown-item>
            Home
          </el-dropdown-item>
        </router-link>
        <el-dropdown-item divided>
          <span style="display:block;" @click="logout">LogOut</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import { Env, Constant } from '@/config'
import Fileter from "@/utils/filter"

export default {
  data() {
    return {
      value: ""
    }
  },
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ]),
    options() {
      let newOptions = []
      if(this.$store.getters.me && this.$store.getters.me.appId) {
        if (this.$store.getters.me.appId == Constant.allAppKey) {
          newOptions = [{
            value:"",
            label:"全部"
          }]
        }
      }
      return Fileter.addOptionGroup(this.$store.getters.appList,newOptions,"热门","应用")
    }
  },
  mounted(){
    this.$nextTick(function () {
      //选择当前账户所属应用
      if(this.$store.getters.me && this.$store.getters.me.appId){
        let appId = this.$store.getters.me.appId
        if(appId==Constant.allAppKey){
          this.value = ""
        }else {
          this.value = appId
          this.$store.dispatch('SetApplication',appId)
        }
      }
    })
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('ToggleSideBar')
    },
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
      })
    },
    selectApp(id) {
      this.$store.dispatch('SetApplication',id)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  box-shadow: 0 1px 3px 0 rgba(0,0,0,.12), 0 0 3px 0 rgba(0,0,0,.04);
  .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 10px;
  }
  .screenfull {
    position: absolute;
    right: 90px;
    top: 16px;
    color: red;
  }
  .select-app{
    height: 50px;
    display: inline-block;
    position: absolute;
    right: 120px;
  }
  .avatar-container {
    height: 50px;
    display: inline-block;
    position: absolute;
    right: 35px;
    .avatar-wrapper {
      cursor: pointer;
      margin-top: 5px;
      position: relative;
      line-height: initial;
      .user-avatar {
        width: 40px;
        height: 40px;
        border-radius: 10px;
      }
      .el-icon-caret-bottom {
        position: absolute;
        right: -20px;
        top: 25px;
        font-size: 12px;
      }
    }
  }
}
</style>

