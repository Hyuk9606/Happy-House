<template>
  <div id="app">
    <navi-bar />
    <router-view />
    <foot-bar />
  </div>
</template>

<script>
import NaviBar from "./components/layout/NaviBar.vue";
import FootBar from "./components/layout/footer.vue";

export default {
  name: "App",
  components: {
    NaviBar,
    FootBar,
  },
};
</script>

<style>
@import "./assets/styles.css";
a:hover {
  text-decoration: none;
  font-weight: bold;
}

a.router-link-exact-active {
  color: #42b983;
}
</style>
