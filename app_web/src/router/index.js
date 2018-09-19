import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Process from '../components/Process.vue'
import User from '../components/User.vue'

Vue.use(Router)

let router = new Router({
  // export default new Router({
  routes: [
    {
      path: '/',
      redirect: {
        name: 'login'
      }
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/process',
      name: 'process',
      component: Process
    },
    {
      path: '/user',
      name: 'user',
      component: User
    }
  ]
});

router.beforeEach((to, from, next) => {
  debugger;
  // if (from && (from.path === '/login' || from.path === '/')) {
  if (from && from.path === '/') {
    next();
  } else {

    console.log(to);
    console.log(from);
    console.log(localStorage);
    console.log(sessionStorage);
    debugger;
    next();

  }

  // if (to.matched.some(record => record.meta.requiresAuth)) {
  //   if (localStorage.getItem('jwt') == null) {
  //     next({
  //       path: '/login',
  //       params: { nextUrl: to.fullPath }
  //     })
  //   } else {
  //     let user = JSON.parse(localStorage.getItem('user'))
  //     if (to.matched.some(record => record.meta.is_admin)) {
  //       if (user.is_admin == 1) {
  //         next()
  //       }
  //       else {
  //         next({ name: 'userboard' })
  //       }
  //     } else {
  //       next()
  //     }
  //   }
  // } else if (to.matched.some(record => record.meta.guest)) {
  //   if (localStorage.getItem('jwt') == null) {
  //     next()
  //   }
  //   else {
  //     next({ name: 'userboard' })
  //   }
  // } else {
  //   next()
  //   // }
})

export default router