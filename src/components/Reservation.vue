<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="top div-col"/>
    <div class="div2 div-col" @click="clickB"/>
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
        backgroundImage: 'url(' + require('@/assets/reservation1.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      }
    }
  },
  methods: {
    clickB () {
      this.$router.push({ path: '/gymDetail' })
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
  height: 1426px;
  margin: 0 auto;
}
.top{
  height: 600px;
}
.div2{
  height: 220px;
}
.div3{
  height: 490px;
}
.div4{
  height: 126px;
  display: flex;
}
.div-row{
  height: 126px;
  width: 250px;
}
.div-col{
  width: 750px;
}
</style>
