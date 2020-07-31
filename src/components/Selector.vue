<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1 div-col"/>
    <div class="div2 div-col"/>
    <div class="div3 div-col"/>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/choose1.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      }
    }
  },
  methods: {
  }
}
</script>

<style scoped>
.container{
  height: 1623px;
}
.div1{
  height: 200px;
}
.div2{
  height: 550px;
}
.div3{
}
.div-col{
  width: 734px;
}
</style>
