<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1 div-col"/>
    <div class="div2 div-col">
      <div class="checkbox">
        <input type="checkbox" v-model="authorize" id="authorize"><span class="text">授权健康信息调用</span>
      </div>
      <div class="checkbox">
        <input type="checkbox" v-model="protocol" id="protocol"><span class="text">本人已阅读并同意《退款规定及场馆入场须知》</span>
      </div>
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
      console.log(this.authorize, this.protocol)
      this.$router.push({ path: '/success' })
    }
  }
}
</script>

<style scoped>
.container{
  height: 1436px;
  font-size: 40px;
  color: #405080;
}
.text{
  font-size: 30px;
}
.div1{
  height: 1190px;
}
.div2{
  height: 130px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.checkbox{
  height: 60px;
  margin-left: 50px;
}
.div3{
  height: 106px;
  display: flex;
}
.price{
  height: 106px;
  width: 500px;
}
.pay{
  height: 106px;
  width: 250px;
}
.div-col{
}
</style>
