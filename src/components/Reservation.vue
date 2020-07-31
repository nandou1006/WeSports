<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1 div-col"/>
    <div class="div2 div-col" @click="handleClick"/>
    <div class="div3 div-col"/>
    <div class="div4 div-col">
      <div class="tab1"/>
      <div class="tab2"/>
      <div class="tab3"/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/1.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      }
    }
  },
  methods: {
    handleClick () {
      console.log('click')
    }
  }
}
</script>

<style scoped>
.container{
  height: 1334px;
}
.div1{
  height: 654px;
}
.div2{
  height: 212px;
}
.div3{
  height: 630px;
}
.div4{
  height: 127px;
  display: flex;
}
.div-col{
  width: 734px;
}
</style>
