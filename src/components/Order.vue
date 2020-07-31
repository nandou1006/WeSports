<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1 div-col"/>
    <div class="div2 div-col">
      <input type="checkbox" v-model="authorize" id="authorize">
      <input type="checkbox" v-model="protocol" id="protocol">
    </div>
    <div class="div3 div-col">
      <div class="price"/>
      <div class="pay" @click="clickPay"/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/confirmOrder.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      },
      authorize: false,
      protocol: false
    }
  },
  methods: {
    clickPay () {
      console.log('支付成功')
    }
  }
}
</script>

<style scoped>
.container{
  height: 792px;
}
.div1{
  height: 660px;
}
.div2{
  height: 70px;
}
.div3{
  height: 62px;
  display: flex;
}
.div-col{
}
</style>
