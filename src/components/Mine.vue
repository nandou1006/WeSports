<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1 div-col"/>
    <div class="div2 div-col">
      <div class="div-row"/>
      <div class="div-row"/>
      <div class="div-row" @click="myOrder"/>
      <div class="div-row"/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/Mine.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      }
    }
  },
  methods: {
    myOrder () {
      this.$router.push({ path: '/myOrder' })
    }
  }
}
</script>

<style scoped>
.container{
  height: 1334px;
}
.div1{
  height: 418px;
}
.div2{
  height: 162px;
  display: flex;
  justify-content: space-around;
}
.div-row{
  width: 180px;
}
.div-col{
  width: 734px;
}
</style>
