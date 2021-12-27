<template>
  <div style="overflow: scroll; height: 690px">
    <b-table
      :items="AptInDong"
      :fields="fields"
      select-mode="single"
      selectable
      @row-clicked="onRowSelect"
    >
    </b-table>
    <b-modal id="bv-modal-example" size="lg" no-close-on-backdrop hide-footer>
      <template #modal-header><h3>상세보기</h3></template>
      <kakao-road-view />
      <div>
        <b-table stacked :items="house" :fields="houseFields"></b-table>
      </div>
      <b-button class="mt-3" block @click="closeModal">Close Me</b-button>
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import KakaoRoadView from "./KakaoRoadView.vue";

const houseStore = "houseStore";
export default {
  components: { KakaoRoadView },
  data() {
    return {
      fields: ["아파트", "거래금액"],
      houseFields: ["아파트", "거래금액", "층", "법정동", "지번"],
      allOpenRows: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["AptInDong", "house"]),
  },
  created() {
    this.allOpenRows = [];
  },
  methods: {
    ...mapMutations(houseStore, ["SET_HOUSE", "CLEAR_HOUSE"]),
    onRowSelect(item) {
      // console.log("아이템", item);
      this.SET_HOUSE(item);
      this.$bvModal.show("bv-modal-example");
    },
    closeModal() {
      this.CLEAR_HOUSE();

      this.$bvModal.hide("bv-modal-example");
    },
  },
};
</script>

<style></style>
