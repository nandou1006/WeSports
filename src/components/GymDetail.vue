<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="top-bar" @click="back"/>
    <div class="div1 div-col"/>
    <div class="div2 div-col" @click="selectTime"/>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/gymDetail.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      }
    }
  },
  methods: {
    selectTime () {
      this.$router.push({ path: '/select' })
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
.top-bar{
  height: 90px;
}
.div1{
  height: 570px;
}
.div2{
  height: 80px;
}
.div-col{
}
</style>
