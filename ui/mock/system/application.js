import Mock from 'mockjs'

export default {
  list: () => {
    const rows = Mock.mock({
      'rows|30': [{
        "id": "@string('upper',32)",
        "appName": "@ctitle()"+"系统",
        "remark": "@csentence()",
        "isAvailable|1": ['0', '1'],
        "optName": '@cname',
        "optDate": '@datetime'
      }]
    })
    return rows
  }
}
