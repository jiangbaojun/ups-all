import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

import Layout from '../views/layout/Layout'

export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },
  {
    path: '/home',
    component: Layout,
    hidden: true,
    children: [{
        path: '/',
        name: 'homeIndex',
        component: () => import('@/views/home/index')
    }]
  },
  {
    path: '/',
    component: Layout,
    redirect: '/system',
    name: 'main',
    hidden: true
  },
  {
    path: '/manage',
    component: Layout,
    redirect: '/manage/user',
    name: 'management',
    meta: { title: '基础管理', icon: 'fa fa-home' },
    children: [
      {
        path: 'user',
        name: 'manageUser',
        component: () => import('@/views/manage/user'),
        meta: { title: '用户管理', icon: 'fa fa-user-o' }
      },
      {
        path: 'resource',
        name: 'manageResource',
        component: () => import('@/views/manage/resource'),
        meta: { title: '资源管理', icon: 'fa fa-sun-o' }
      },
      {
        path: 'role',
        name: 'manageRole',
        component: () => import('@/views/manage/role'),
        meta: { title: '角色管理', icon: 'fa fa-vcard-o' }
      },
      {
        path: 'organization',
        name: 'manageOrganization',
        component: () => import('@/views/manage/organization'),
        meta: { title: '组织管理', icon: 'fa fa-users' }
      }
    ]
  },
  {
    path: '/authorization',
    component: Layout,
    redirect: '/authorization/user',
    name: 'authorization',
    meta: { title: '授权管理', icon: 'fa fa-lock' },
    children: [
      {
        path: 'user',
        name: 'authorizationUser',
        component: () => import('@/views/authorization/user'),
        meta: { title: '用户授权', icon: 'fa fa-user-circle' }
      },
      {
        path: 'role',
        name: 'authorizationRole',
        component: () => import('@/views/authorization/role'),
        meta: { title: '角色授权', icon: 'fa fa-vcard' }
      },
      {
        path: 'organization',
        name: 'authorizationOrganization',
        component: () => import('@/views/authorization/organization'),
        meta: { title: '组织授权', icon: 'fa fa-user-md' }
      }
    ]
  },
  {
    path: '/extension',
    component: Layout,
    redirect: '/extension/plugin',
    name: 'extension',
    meta: { title: '扩展配置', icon: 'fa fa-th-large' },
    children: [
      {
        path: 'plugin',
        name: 'extensionPlugin',
        component: () => import('@/views/extension/plugin'),
        meta: { title: '插件扩展', icon: 'fa fa-plug' }
      },
      {
        path: 'attribute',
        name: 'extensionAttr',
        component: () => import('@/views/extension/attr'),
        meta: { title: '属性扩展', icon: 'fa fa-indent' }
      },
      {
        path: 'relation',
        name: 'extensionRelation',
        component: () => import('@/views/extension/relation'),
        meta: { title: '属性关联配置', icon: 'fa fa-chain' }
      }
      // {
      //   path: 'attribute',
      //   redirect: '/extension/attribute/user',
      //   name: 'extensionAttr',
      //   meta: { title: '属性扩展', icon: 'fa fa-indent'},
      //   children: [
      //     {
      //       path: 'user',
      //       component: () => import('@/views/extension/attr/user'),
      //       name: 'extensionAttrUser',
      //       meta: { title: '用户属性', icon: 'fa fa-list-alt' }
      //     },
      //     {
      //       path: 'organization',
      //       component: () => import('@/views/extension/attr/organization'),
      //       name: 'extensionAttrOrganization',
      //       meta: { title: '组织属性', icon: 'fa fa-align-left'}
      //     }
      //   ]
      // }
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/account',
    name: 'system',
    meta: { title: '系统管理', icon: 'setting' },
    children: [
      {
        path: 'account',
        name: 'systemAccount',
        component: () => import('@/views/system/account'),
        meta: { title: '账户管理', icon: 'fa fa-id-card' }
      },
      {
        path: 'application',
        name: 'systemApplication',
        component: () => import('@/views/system/application'),
        meta: { title: '应用管理', icon: 'fa fa-sitemap' }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
