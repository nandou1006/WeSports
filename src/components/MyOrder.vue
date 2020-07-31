<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1"/>
    <div class="div2">
      <div class="blank"/>
      <div class="detail" @click="orderDetail"/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/myOrder.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      }
    }
  },
  methods: {
    orderDetail () {
      this.$router.push({ path: '/orderDetail' })
    }
  }
}
</script>

<style scoped>
.container{
  height: 792px;
}
.div1{
  height: 220px;
}
.div2{
  height: 25px;
  display: flex;
}
.blank{
  width: 330px;
}
.detail{
  width: 70px;
}
.div-col{
}
</style>
