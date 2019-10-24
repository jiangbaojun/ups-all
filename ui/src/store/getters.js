const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.account.token,
  avatar: state => state.account.avatar,
  name: state => state.account.name,
  roles: state => state.account.roles,
  appList: state => state.system.appList,
  application: state => state.system.application,
  me: state => state.system.me
}
export default getters
