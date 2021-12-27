<template>
  <b-container fluid>
    <!-- <div style="height: 50px"> -->
    <!-- <map-navigation /> -->
    <!-- </div> -->
    <b-row>
      <b-col cols="8">
        <!-- <div style="width: 100; height: 700px; margin: auto"> -->
        <kakao-map />
        <!-- </div> -->
      </b-col>
      <b-col>
        <apt-search-bar />
        <apt-list />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import KakaoMap from "../components/apart/KakaoMap.vue";
import AptSearchBar from "../components/apart/AptSearchBar.vue";
import AptList from "../components/apart/AptList.vue";
// import MapNavigation from "../components/apart/layout/mapNavigation.vue";

export default {
  name: "HouseSearchBar",
  components: {
    KakaoMap,
    AptSearchBar,
    AptList,
    // MapNavigation,
  },
};
</script>

<style></style>
