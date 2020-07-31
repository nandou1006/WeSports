<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1 div-col"/>
    <div class="div2 div-col" @click="clickB"/>
    <div class="div3 div-col"/>
    <div class="div4 div-col">
      <div class="tab1 div-row" @click="clickReservation"/>
      <div class="tab2 div-row" @click="clickStore"/>
      <div class="tab3 div-row" @click="clickMine"/>
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
  height: 792px;
}
.div1{
  height: 330px;
}
.div2{
  height: 130px;
}
.div3{
  height: 260px;
}
.div4{
  height: 72px;
  display: flex;
}
.div-row{
  height: 72px;
  width: 138px;
}
.div-col{
}
</style>
