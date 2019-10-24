<template>
  <el-form-item>
    <el-select v-if="isGroup" @change="changeApp" :filterable="filterable" :multiple="multiple" collapse-tags v-model="selectedValue" placeholder="请选择应用" class="top-input">
      <el-option-group
        v-for="group in optionData"
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
    <el-select v-else @change="changeApp" :filterable="filterable" :multiple="multiple" collapse-tags v-model="selectedValue" placeholder="请选择应用" class="top-input">
      <el-option
        v-for="item in optionData"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
  </el-form-item>
</template>

<script>
import Fileter from "@/utils/filter"
import { Env, Constant } from '@/config'
export default {
  props: {
    value: {
      type: String,
      default: ""
    },
    type: {
      type: String,
      default: "10"
    },
    multiple: {
      type: Boolean,
      default: false
    },
    filterable: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    changeApp(){
      this.$emit("appChange",this.selectedValue)
    }
  },
  watch: {
    //监听外部调用传入值，改变选择值
    value(val){
      this.selectedValue = val
    }
  },
  data(){
    return {
      selectedValue: this.value,
      isGroup: false
    }
  },
  computed: {
    optionData() {
      let newOptions = []
      if(this.type=="01"){
        this.isGroup = true
        newOptions = [{
          value: Constant.allAppKey,
          label: "系统级应用"
        }]
      }else if(this.type=="10"){
        this.isGroup = false
        newOptions = [{
          value: "",
          label: "全部"
        }]
        return Fileter.addOption(this.$store.getters.appList, newOptions)
      }else if(this.type=="11"){
        this.isGroup = true
        newOptions = [{
          value: "",
          label: "全部"
        }, {
          value: Constant.allAppKey,
          label: "系统级应用"
        }]
      }else{
        this.isGroup = false
        return this.$store.getters.appList
      }
      return Fileter.addOptionGroup(this.$store.getters.appList, newOptions, "热门", "应用")
    }
  }
}
</script>

