<template>
  <el-container id="box">
    {{ date | formaDate }}
  </el-container>
</template>
<script>
//创建一个函数来增加月日时小于10在前面加0
var padaDate = function(value){
  return value<10 ? '0'+value : value;
};
export default {
  name: 'Timeshow',
  data() {
    return {
      date: new Date(), //实时时间
    };
  },
  watch: {

  },
  computed: {},
  filters:{
    //设置一个函数来进行过滤
    formaDate:function(value){
      //创建一个时间日期对象
      var date = new Date();
      var year = date.getFullYear();      //存储年
      var month = padaDate(date.getMonth()+1);    //存储月份
      var day = padaDate(date.getDate());         //存储日期
      var hours = padaDate(date.getHours());      //存储时
      var minutes = padaDate(date.getMinutes());  //存储分
      var seconds = padaDate(date.getSeconds());  //存储秒
      //返回格式化后的日期
      return year+'年'+month+'月'+day+'日'+hours+'时'+minutes+'分'+seconds+'秒';
    }
  },
  methods: {

  },
  created() {

  },
  mounted() {
    //创建定时器更新最新的时间
    var _this = this;
    this.timeId = setInterval(function() {
      _this.sday =dayjs().format('YYYY-MM-DD HH:mm:ss');
    }, 1000);
    this.initmap();
  },
  beforeDestroy: function() {
    //实例销毁前青出于定时器
    if (this.timeId) {
      clearInterval(this.timeId);
    }
  }
};
</script>
<style lang="scss" scoped>

</style>
