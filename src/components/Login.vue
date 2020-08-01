<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="top"/>
    <div class="middle">
      <div class="row">
        <input type="text" v-model="user">
      </div>
      <div class="row">
        <input type="text" v-model="password">
      </div>
      <div class="row" @click="handleLogin"/>
    </div>
    <div class="bottom"/>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/login.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      },
      user: 'nicai',
      password: '1234'
    }
  },
  methods: {
    handleLogin () {
      console.log(this.user, this.password)
    }
  }
}
</script>

<style scoped>
.container{
  height: 2872px;
  margin: 0 auto;
}
.div-col{
  width: 1500px;
}
.top{
  height: 900px;
}
.middle{
  height: 1000px;
  display: flex;
  flex-direction: column;
}
.row{
  height: 333px;
  margin: 150px 150px 0 150px;
}
input[type='text']{
  margin-top: 20px;
  height: 163px;
  width: 1200px;
  font-size: 80px;
}
</style>
