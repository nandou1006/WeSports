<template>
  <div class="container div-col" :style="backgroundImg">
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
  }
}
</script>

<style scoped>
.container{
  height: 1334px;
}
.div-col{
  width: 734px;
}
</style>