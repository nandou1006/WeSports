<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1"/>
    <div class="div2">
      <div class="refundButton" @click="refund"/>
      <div class="homeButton" @click="home"/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/orderDetail.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      }
    }
  },
  methods: {
    refund () {
      console.log('refund')
    },
    home () {
      this.$router.push({ path: '/home' })
    }
  }
}
</script>

<style scoped>
.container{
  height: 1436px;
}
.div1{
  height: 670;
}
.div2{
  height: 40px;
  display: flex;
}
.refundButton{
  width: ;
}
.div-col{
}
</style>
