<template>
  <b-container fluid class="img-home">
    <b-row style="height: 650px">
      <b-col cols="3"></b-col>
      <b-col cols="6">
        <img src="../assets/myhouse.png" height="95%" />
      </b-col>
      <b-col cols="3"></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "Main",
  data() {
    return {
      number: 1,
    };
  },
  methods: {
    incr() {
      this.number++;
    },
  },
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.img-home {
  background-image: url("../assets/forest.jpg");
  background-size: contain;
  width: 2000px;
  height: 850px;
}
</style>
