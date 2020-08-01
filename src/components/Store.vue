<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1 div-col"/>
    <div class="div2 div-col">
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
        backgroundImage: 'url(' + require('@/assets/store.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      }
    }
  },
  methods: {
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
.div-col{
}
.div1{
  height: 1310px;
}
.div2{
  height: 126px;
  display: flex;
}
.div-row{
  height: 126px;
  width: 250px;
}
</style>
