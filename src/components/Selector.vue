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
      rangeOptions: ['09:00-10:30', '10:30-12:00', '14:00-15:30', '15:30-17:00', '19:00-20:30', '20:30-22:00'],
      range: '09:00-10:30'
    }
  },
  methods: {
    submitOrder () {
      let that = this
      let timeObject = that.handleTime(that.date, that.range)
      let body = {
        userId: '3',
        companyId: '10',
        fieldId: '1',
        cost: '155',
        points: '50',
        startTime: timeObject.startTime,
        endTime: timeObject.endTime
      }
      that.$axios.post('/v1/order/create', body).then(res => {
        localStorage.setItem('orderId', res.data.data.orderId)
        that.$router.push({ path: '/order' })
      })
    },
    handleTime (date, range) {
      let rangeStart = range.substring(0, 5)
      let rangeEnd = range.substring(6)
      let res = {
        startTime: `${date}T${rangeStart}:00`,
        endTime: `${date}T${rangeEnd}:00`
      }
      return res
    }
  }
}
</script>

<style scoped>
.container{
  height: 1436px;
  font-size: 30px;
  color: #405080;
}
.div1{
  height: 220px;
}
.div2{
  height: 600px;
  display: flex;
}
.select-box{
  width: 300px;
  margin-left: 80px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
}
.div3{
  height: 510px;
}
.div4{
  height: 106px;
}
.div-col{
}
</style>
