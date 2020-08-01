<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1"/>
    <div class="div2">
      <div class="order"/>
      <div class="operate">
        <div class="blank"/>
        <div class="detail-button" @click="orderDetail"/>
      </div>
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
  height: 1436px;
}
.div1{
  height: 90px;
}
.div2{
  margin-bottom: 10px;
  height: 380px;
}
.order{
  height: 310px;
}
.operate{
  height: 40px;
  display: flex;
}
.blank{
  width: 600px;
  height: 40px;
  margin: 0;
}
.detail-button{
  width: 130px;
  height: 40px;
  margin: 0;
}
.div-col{
  width: 750px;
}
</style>
