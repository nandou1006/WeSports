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
      user: 'test',
      password: 'test'
    }
  },
  methods: {
    handleLogin () {
      let that = this
      that.$axios.post('/login', {
        username: that.user,
        password: that.password,
        groupId: 'U'
      }).then(res => {
        console.log(res.data.data.token)
        that.$router.push({ path: '/' })
      })
    }
  }
}
</script>

<style scoped>
.container{
  height: 1436px;
  margin: 0 auto;
}
.div-col{
  width: 750px;
}
.top{
  height: 450px;
}
.middle{
  height: 500px;
  display: flex;
  flex-direction: column;
}
.row{
  height: 166px;
  margin: 75px 75px 0 75px;
}
input[type='text']{
  margin-top: 10px;
  height: 91px;
  width: 600px;
  font-size: 40px;
}
</style>
