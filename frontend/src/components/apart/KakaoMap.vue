<template>
  <div>
    <map-navigation />
    <div id="map" style="width: 100; height: 700px; margin: auto"></div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";
import axios from "axios";
import mapNavigation from "./layout/mapNavigation.vue";
export default {
  components: { mapNavigation },
  name: "KakaoMap",
  data() {
    return {
      map: null,
      markers: [],
      coords: [],
      placeMarkers: [],
      placeCoords: [],
      stat: "",
      infowindow: null,
      isOk: false,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        process.env.VUE_APP_KAKAO_SERVICE_KEY +
        "&libraries=services";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(houseStore, ["AptInDong", "statuses"]),
  },
  watch: {
    AptInDong: function () {
      this.getMarkerOnList();
    },
    isOk: function () {
      // this.deleteMarker(this.markers);
      this.initMap();
      this.setAche(0);
      this.setPark(0);
      this.displayMarker(this.coords);
      this.map.setCenter(
        new kakao.maps.LatLng(this.coords[0][0], this.coords[0][1])
      );
      this.map.setLevel(3);
    },
    statuses: function () {
      // console.log("카카오맵스틔따스", this.statuses, this.statuses.length);
      // console.log(this.placeMarkers.length);
      for (let i = 0; i < this.statuses.length; i++) {
        this.placeCoords = [];
        if (this.statuses[i] == "") continue;
        else {
          // console.log("실행");
          this.placeSearch(this.statuses[i]);
        }
      }
      // console.log("플레이스", this.placeCoords.length);
      this.map.setLevel(6);
    },
  },
  methods: {
    ...mapActions(houseStore, ["setAche", "setPark"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 8,
      };
      this.map = new kakao.maps.Map(container, options);
    },
    deleteMarker(markers) {
      if (markers.length > 0) {
        markers.forEach((marker) => marker.setMap(null));
      }
      markers = [];
    },

    displayMarker(markerPositions) {
      // console.log(markerPositions);

      markerPositions.forEach((el) => {
        // 마커를 생성합니다
        const marker = new kakao.maps.Marker({
          //마커가 표시 될 지도
          map: this.map,
          //마커가 표시 될 위치
          position: new kakao.maps.LatLng(el[0], el[1]),
          clickable: true,
        });
        // 마커에 표시할 인포윈도우를 생성합니다
        let address =
          "https://map.kakao.com/link/map/" + el[2] + "," + el[0] + "," + el[1];
        let path =
          "https://map.kakao.com/link/to/" + el[2] + "," + el[0] + "," + el[1];
        // console.log(address);
        var iwContent =
          '<div style=" width:250px; text-align:center "><div><h5><strong>' +
          el[2] +
          "</strong></h5></div><div>" +
          el[3] +
          '</div><div class="pb-2 pt-1">' +
          "<a href=" +
          address +
          '><img width="25" height="25" src="https://play-lh.googleusercontent.com/Nvrf8Z89_3S8H6YnOLgyAbe-PSSeCZnJDA8zv7LY04hEvi8atTgp_fmQ5RZ591Qpxh5G=s180-rw"/></a>' +
          "<a href=" +
          path +
          '> <img width="25" height="25" src="https://play-lh.googleusercontent.com/tmKbM_kM31bRZoU1SkKw2LB30CFdm-MZYP0XBxlJzhYuJfbulrMi_P88VOxuZszXhPk=s180-rw"/></a></div>';

        var infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
          removable: true,
        });
        kakao.maps.event.addListener(marker, "click", () => {
          // 마커 위에 인포윈도우를 표시합니다
          infowindow.open(this.map, marker);
        });
      });
    },
    displayPlaceMarker(markerPositions) {
      // console.log("displayPlace", markerPositions);
      // if (this.markers.length > 0) {
      //   this.markers.forEach((marker) => marker.setMap(null));
      // }
      var icon = new kakao.maps.MarkerImage(
        "https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png",
        new kakao.maps.Size(31, 35),
        {
          shape: "poly",
          coords:
            "16,0,20,2,24,6,26,10,26,16,23,22,17,25,14,35,13,35,9,25,6,24,2,20,0,16,0,10,2,6,6,2,10,0",
        }
      );

      markerPositions.forEach((el) => {
        // 마커를 생성합니다
        const marker = new kakao.maps.Marker({
          //마커가 표시 될 지도
          map: this.map,
          //마커가 표시 될 위치
          position: new kakao.maps.LatLng(el[0], el[1]),
          image: icon,
          clickable: true,
        });
        // 마커에 표시할 인포윈도우를 생성합니다
        let address =
          "https://map.kakao.com/link/map/" + el[2] + "," + el[0] + "," + el[1];
        let path =
          "https://map.kakao.com/link/to/" + el[2] + "," + el[0] + "," + el[1];
        // console.log(address);
        var iwContent =
          '<div style=" width:250px; text-align:center "><div><h5><strong>' +
          el[2] +
          "</strong></h5></div><div>" +
          el[3] +
          '</div><div class="pb-2 pt-1">' +
          "<a href=" +
          address +
          '><img width="25" height="25" src="https://play-lh.googleusercontent.com/Nvrf8Z89_3S8H6YnOLgyAbe-PSSeCZnJDA8zv7LY04hEvi8atTgp_fmQ5RZ591Qpxh5G=s180-rw"/></a>' +
          "<a href=" +
          path +
          '> <img width="25" height="25" src="https://play-lh.googleusercontent.com/tmKbM_kM31bRZoU1SkKw2LB30CFdm-MZYP0XBxlJzhYuJfbulrMi_P88VOxuZszXhPk=s180-rw"/></a></div>';

        var infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
          removable: true,
        });
        kakao.maps.event.addListener(marker, "click", () => {
          // 마커 위에 인포윈도우를 표시합니다
          infowindow.open(this.map, marker);
        });
      });
    },

    getMarkerOnList() {
      this.coords = [];

      (async () => {
        for (let i = 0; i <= this.AptInDong.length; i++) {
          if (i == this.AptInDong.length) {
            this.isOk = !this.isOk;
            break;
          }
          let query =
            this.AptInDong[i].법정동.trim() + " " + this.AptInDong[i].지번;

          let ret = await axios({
            methods: "get",
            url:
              "https://dapi.kakao.com/v2/local/search/address.json?query=" +
              encodeURIComponent(query),
            headers: {
              Authorization:
                "KakaoAK " + process.env.VUE_APP_KAKAO_REST_API_KEY,
            },
          });
          // console.log(ret.data.documents[0]);

          let coordinate = [];
          coordinate[0] = parseFloat(ret.data.documents[0].y);
          coordinate[1] = parseFloat(ret.data.documents[0].x);
          coordinate[2] = this.AptInDong[i].아파트;
          coordinate[3] = ret.data.documents[0].road_address.address_name;
          if (coordinate) {
            this.coords.push(coordinate);
            this.$set(this.AptInDong[i], "coordinate", coordinate);
          }
          // console.log(coordinate);
        }
      })();
    },
    placeSearch(query) {
      // this.placeCoords = [];
      var ps = new kakao.maps.services.Places();
      let ret = null,
        ret2 = null;
      (async () => {
        // let count = 0;
        ret = await new Promise((resolve) => {
          ps.keywordSearch(
            query,
            (data, status) => {
              // if (query == "동물병원") {
              //   this.searchAmount[0]++;
              // } else if (query == "공원") {
              //   this.searchAmount[1]++;
              // }
              resolve(data.length);
              if (status === kakao.maps.services.Status.OK) {
                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다

                // var bounds = new kakao.maps.LatLngBounds();
                // (async () => {
                data.forEach((res) => {
                  let coord = [];
                  coord[0] = parseFloat(res.y);
                  coord[1] = parseFloat(res.x);
                  coord[2] = res.place_name;
                  coord[3] = res.road_address_name;
                  this.placeCoords.push(coord);
                });

                this.displayPlaceMarker(this.placeCoords);
              }
            },
            { location: this.map.getCenter(), radius: 3000 }
          );
        });
        console.log("ret", ret);
        ret2 = await new Promise((resolve) => {
          ps.keywordSearch(
            query,
            (data, status) => {
              resolve(data.length);
              console.log("ret2222", data.length);
              if (status === kakao.maps.services.Status.OK) {
                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다

                // var bounds = new kakao.maps.LatLngBounds();
                // (async () => {
                data.forEach((res) => {
                  let coord = [];
                  coord[0] = parseFloat(res.y);
                  coord[1] = parseFloat(res.x);
                  coord[2] = res.place_name;
                  coord[3] = res.road_address_name;
                  this.placeCoords.push(coord);
                });

                this.displayPlaceMarker(this.placeCoords);
              }
            },
            { location: this.map.getCenter(), page: 2, radius: 3000 }
          );
        });

        if (!ret) ret = 0;
        if (!ret2) ret2 = 0;
        if (query == "동물병원") {
          this.setAche(ret + ret2);
        } else if (query == "공원") {
          this.setPark(ret + ret2);
        }
        // resolve(this.searchAmount);

        // console.log("ret3", ret3);
        // this.setPlaceAmount(ret3);
      })();
      // console.log("으에에에에에엥ㄱ", this.searchAmount);

      // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
button {
  border-color: white;
}
</style>
