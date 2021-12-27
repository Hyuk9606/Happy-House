import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common.js";
Vue.use(Vuex);

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    AptInDong: [],
    house: [],
    statuses: ["", ""],
    ache: 0,
    park: 0,
  },
  mutations: {
    SET_ACHE(state, data) {
      state.ache = data;
    },
    SET_PARK(state, data) {
      state.park = data;
    },

    SET_STATUS(state, data) {
      // console.log("듸이타", data);
      let status = [];
      for (let i = 0; i < state.statuses.length; i++) {
        status[i] = state.statuses[i];
      }
      if (data == "sick") {
        if (status[0] == "동물병원") {
          status[0] = "";
        } else {
          status[0] = "동물병원";
        }
      } else if (data == "park") {
        if (status[1] == "공원") {
          status[1] = "";
        } else {
          status[1] = "공원";
        }
      }
      // console.log("스틔타스", status);
      state.statuses = status;
    },
    SET_SIDO_LIST(state, data) {
      data.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, data) {
      data.forEach((gugun) => {
        state.guguns.push({ value: +gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state) {
      // data.forEach((dong) => {
      //   state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      // });
      let dong = "";
      state.houses.forEach((house) => {
        if (dong != house.법정동읍면동코드) {
          let concatCode =
            house.법정동시군구코드.toString() +
            house.법정동읍면동코드.toString();
          state.dongs.push({
            value: concatCode,
            text: house.법정동,
          });
          dong = house.법정동읍면동코드;
        }
      });
    },
    SET_HOUSE_LIST(state, data) {
      state.houses = data;
      // console.log(state.houses);
    },
    SET_DETAIL_HOUSES(state, data) {
      // console.log(data);
      let tempAptInDong = [];
      (async () => {
        await state.houses.forEach((house) => {
          let code =
            house.법정동시군구코드.toString() +
            house.법정동읍면동코드.toString();
          // console.log(code);
          if (code == data) {
            tempAptInDong.push(house);
          }
        });
        state.AptInDong = tempAptInDong;
      })();
      // console.log("아파트들!", state.AptInDong);
    },
    SET_HOUSE(state, data) {
      state.house.push(data);
    },
    CLEAR_HOUSE(state) {
      state.house = [];
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_HOUSE_LIST(state) {
      state.houses = [];
    },
    CLEAR_APTINDONG_LIST(state) {
      state.AptInDong = [];
    },
  },
  actions: {
    getSido({ commit }) {
      http
        .get(`/map/sido`)
        .then((response) => {
          // console.log(response.data);
          commit("SET_SIDO_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/map/gugun`, { params })
        .then((response) => {
          // console.log(commit, response.data);
          commit("SET_GUGUN_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getApt({ commit }, gugunCode) {
      const SERVICE_KEY = process.env.VUE_APP_SERVICE_KEY;
      const params = {
        // ServiceKey: decodeURIComponent(SERVICE_KEY),
        ServiceKey: SERVICE_KEY,
        pageNo: 1,
        numOfRows: 1000,
        LAWD_CD: gugunCode,
        DEAL_YMD: "202110",
      };
      http
        .get("/open/apt", { params })
        .then((response) => {
          // console.log(response);
          commit("SET_HOUSE_LIST", response.data.response.body.items.item);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    setAche({ commit }, data) {
      commit("SET_ACHE", data);
    },
    setPark({ commit }, data) {
      commit("SET_PARK", data);
    },
  },
};

export default houseStore;
