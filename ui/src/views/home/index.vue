<template>
  <div class="dashboard-container">
    {{app}}
    {{account}}
    <div class="dashboard-text">name: {{ name }}</div>
    <div class="dashboard-text">roles: {{ roles }}</div>
    <div class="dashboard-text">token: <span>{{ account.token }}</span></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { mapState } from 'vuex'

export default {
  name: 'Home',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ]),
    ...mapState({
      app:state=>state.app,
      account:state=>state.account
    })
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
