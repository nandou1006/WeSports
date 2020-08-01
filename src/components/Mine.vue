<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1 div-col"/>
    <div class="div2 div-col">
      <div class="div-row1"/>
      <div class="div-row1"/>
      <div class="div-row1" @click="myOrder"/>
      <div class="div-row1"/>
    </div>
    <div class="div3 div-col"/>
    <div class="div4 div-col">
      <div class="div-row" @click="clickReservation"/>
      <div class="div-row" @click="clickStore"/>
      <div class="div-row" @click="clickMine"/>
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
    },
    clickReservation () {
      this.$router.push({ path: '/' })
    },
    clickStore () {
      this.$router.push({ path: '/store' })
    },
    clickMine () {
      this.$router.push({ path: '/mine' })
    }
  }
}
</script>

<style scoped>
.container{
  height: 1436px;
}
.div1{
  height: 410px;
}
.div2{
  height: 160px;
  display: flex;
  justify-content: space-around;
}
.div3{
  height: 740px;
}
.div4{
  height: 126px;
  display: flex;
}
.div-row1{
  width: 187px;
}
.div-row{
  height: 126px;
  width: 250px;
}
.div-col{
}
</style>
