import Mock from 'mockjs'

export default {
  list: () => {
    const rows = Mock.mock({
      'rows|30': [{
        'id|+1': 1,
        'status|1': ['published', 'draft', 'deleted'],
        name: '@name',
        birthday: '@date',
        update_time: '@datetime',
        age: '@integer(10, 80)'
      }]
    })
    return rows
  }
}
