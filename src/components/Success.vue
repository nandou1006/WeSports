<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1"/>
    <div class="div2">
      <div class="button-container">
        <div class="button" @click="goHome"/>
        <div class="button" @click="myOrder"/>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/success.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      }
    }
  },
  methods: {
    goHome () {
      this.$router.push({ path: '/' })
    },
    myOrder () {
      this.$router.push({ path: '/myOrder' })
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
}
.button-container{
  margin: 540px 0 716px 0;
  height: 90px;
  display: flex;
}
.button{
  height: 90px;
  width: 375px;
}
.div-col{
}
</style>
