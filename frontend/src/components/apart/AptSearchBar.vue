<template>
  <div>
    <b-row class="mb-2 mt-2">
      <b-col class="sm-2">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-2">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-2">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchApt"
        ></b-form-select>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
export default {
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  created() {
    // this.$store.dispatch("getSido");
    this.sidoList();
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  watch: {
    houses: function () {
      this.SET_DONG_LIST();
    },
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getApt"]),
    ...mapMutations(houseStore, [
      "CLEAR_GUGUN_LIST",
      "CLEAR_SIDO_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "SET_DETAIL_HOUSES",
      "CLEAR_APTINDONG_LIST",
      "SET_DONG_LIST",
    ]),
    sidoList() {
      this.CLEAR_SIDO_LIST();
      this.getSido();
    },
    gugunList() {
      // console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) {
        this.getGugun(this.sidoCode);
      }
    },
    dongList() {
      // console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) {
        // this.getApt(this.gugunCode);
        // this.getDong(this.gugunCode);
        this.getApt(this.gugunCode);
        // this.SET_DONG_LIST();
      }
    },
    searchApt() {
      //   if (this.gugunCode) this.getHouseList(this.gugunCode);
      // console.log("searchApt : ", this.dongCode);
      this.CLEAR_APTINDONG_LIST();
      this.SET_DETAIL_HOUSES(this.dongCode);
      //   this.CLEAR_HOUSE_LIST();
    },
  },
};
</script>

<style></style>
