<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1" @click="back"/>
    <div class="div2">
      <div class="button-container">
        <div class="button" @click="refund"/>
        <div class="button" @click="home"/>
      </div>
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
      this.$router.push({ path: '/' })
    },
    back () {
      history.back()
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
  height: 1346px;
  display: flex;
}
.button-container{
  height: 70px;
  width: 750px;
  margin: 1130px 0 146px 0;
  display: flex;
}
.button{
  width: 375px;
  height: 70px;
}
.div-col{
}
</style>
