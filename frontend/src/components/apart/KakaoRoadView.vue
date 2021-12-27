<template>
  <div id="roadview" style="width: 100%; height: 425px"></div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState("houseStore", ["house"]),
  },

  mounted() {
    if (window.kakao) {
      //   let coord = [33.450701, 126.570667];
      this.initRoadView(this.house[0].coordinate);
    } else {
      const script = document.createElement("script");
      console.log(script);
      /* global kakao */
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_SERVICE_KEY;
      document.head.appendChild(script);
    }
  },
  methods: {
    initRoadView(coord) {
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

      var position = new kakao.maps.LatLng(coord[0], coord[1]);
      //   console.log(this.house.coordinate[0], this.house.coordinate[1]);
      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
  },
};
</script>

<style></style>
