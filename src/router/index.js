import Vue from 'vue'
import Router from 'vue-router'
import Reservation from '@/components/Reservation'
import GymDetail from '@/components/GymDetail'
import Selector from '@/components/Selector'
import Order from '@/components/Order'
import Mine from '@/components/Mine'
import MyOrder from '@/components/MyOrder'
import OrderDetail from '@/components/OrderDetail'
import Success from '@/components/Success'
import Store from '@/components/Store'

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
      path: '/success',
      name: Success,
      component: Success
    },
    {
      path: '/store',
      name: Store,
      component: Store
    },
    {
      path: '/mine',
      name: Mine,
      component: Mine
    },
    {
      path: '/myOrder',
      name: MyOrder,
      component: MyOrder
    },
    {
      path: '/orderDetail',
      name: OrderDetail,
      component: OrderDetail
    }
  ]
})
