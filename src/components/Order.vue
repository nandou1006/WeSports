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
    }
  }
}
</script>

<style scoped>
.container{
  height: 792px;
  font-size: 20px;
  color: #405080;
}
.text{
  font-size: 16px;
}
.div1{
  height: 660px;
}
.div2{
  height: 60px;
  margin-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.checkbox{
  height: 30px;
  margin-left: 10px;
}
.div3{
  height: 62px;
  display: flex;
}
.price{
  height: 62px;
  width: 270px;
}
.pay{
  height: 62px;
  width: 144px;
}
.div-col{
}
</style>
