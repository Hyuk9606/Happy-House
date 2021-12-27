<template>
  <b-container class="bv-example-row mt-3 text-center" style="height: 760px">
    <div>
      <h3 class="underline-hotpink">
        <b-icon icon="journals"></b-icon> 질의응답
      </h3>
      <router-view></router-view>
    </div>
  </b-container>
</template>
<script>
export default {
  name: "Board",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(219, 253, 0, 0.394) 30%
  );
}
</style>
