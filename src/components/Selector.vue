<template>
  <div class="container div-col" :style="backgroundImg">
    <div class="div1 div-col"/>
    <div class="div2 div-col">
      <div class="select-box">
        <div v-for="item in dateOptions" :key="item">
          <input type="radio" name="date" id="item" :value="item" v-model="date">&nbsp;{{ item }}
        </div>
      </div>
      <div class="select-box">
        <div v-for="item in rangeOptions" :key="item">
          <input type="radio" name="range" id="item" :value="item" v-model="range">&nbsp;{{ item }}
        </div>
      </div>
    </div>
    <div class="div3 div-col"/>
    <div class="div4 div-col" @click="submitOrder"/>
  </div>
</template>

<script>
export default {
  data () {
    return {
      backgroundImg: {
        backgroundImage: 'url(' + require('@/assets/select.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '100% 100%',
        backgroundPosition: 'center center'
      },
      dateOptions: ['2020-08-03', '2020-08-04', '2020-08-05', '2020-08-06', '2020-08-07', '2020-08-08'],
      date: '2020-08-03',
      rangeOptions: ['9:30-10:30', '10:30-11:30', '13:30-14:30', '14:30-15:30', '15:30-16:30', '16:30-17:30'],
      range: '9:30-10:30'
    }
  },
  methods: {
    submitOrder () {
      console.log(this.date, this.range)
      this.$router.push({ path: '/order' })
    }
  }
}
</script>

<style scoped>
.container{
  height: 792px;
  font-size: 20px;
}
.div1{
  height: 88px;
}
.div2{
  height: 400px;
  display: flex;
}
.select-box{
  width: 250px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.div3{
  height: 194px;
}
.div4{
  height: 60px;
}
.div-col{
}
</style>
