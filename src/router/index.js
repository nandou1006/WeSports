import Vue from 'vue'
import Router from 'vue-router'
import Reservation from '@/components/Reservation'
import GymDetail from '@/components/GymDetail'
import Selector from '@/components/Selector'
import Order from '@/components/Order'
import Mine from '@/components/Mine'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Reservation',
      component: Reservation
    },
    {
      path: '/gymDetail',
      name: 'GymDetail',
      component: GymDetail
    },
    {
      path: '/select',
      name: 'Selector',
      component: Selector
    },
    {
      path: '/order',
      name: Order,
      component: Order
    },
    {
      path: '/mine',
      name: Mine,
      component: Mine
    }
  ]
})
