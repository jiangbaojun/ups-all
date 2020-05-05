module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint',
    sourceType: 'module'
  },
  env: {
    browser: true,
    node: true,
    es6: true,
    jquery: true
  },
  extends: ['plugin:vue/recommended', 'eslint:recommended'],

  // add your custom rules here
  //it is base on https://github.com/vuejs/eslint-config-vue
  rules: {
    // 允许声明未使用变量
    "no-unused-vars": 'off',
    'vue/html-self-closing': 'off',
    "linebreak-debugger": 'off',
    "linebreak-style": 'off', // 强制使用一致的换行风格
    "no-console":"off",//在这禁止掉console报错检查
    "no-irregular-whitespace":"off"//这禁止掉 空格报错检查
  }
}

