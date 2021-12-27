<template>
  <nav class="navbar navbar-expand-lg static-top mt-2">
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <h5>
            <li class="nav-item mr-3 ml-3">
              <strong>
                <div type="button" class="colorr" id="sick" @click="abc">
                  콜록콜록
                </div>
              </strong>
            </li>
          </h5>
          <h5>
            <li class="nav-item mr-3 ml-3">
              <strong>
                <div type="button" class="colorr" id="park" @click="abc">
                  산책가자
                </div>
              </strong>
            </li>
          </h5>
        </ul>
      </div>
      <div class="d-flex flex-row-reverse bd-highlight">
        <ul class="list-group list-group-horizontal">
          <li class="list-group-item">
            주변 동물병원 :<strong> {{ ache }}</strong>
          </li>
          <li class="list-group-item">
            주변 공원 : <strong>{{ park }}</strong>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapState, mapMutations } from "vuex";
const houseStore = "houseStore";
export default {
  computed: {
    ...mapState(houseStore, ["searchAmount", "ache", "park"]),
  },
  watch: {
    searchAmount: {
      function() {
        console.log("으악 으아아악");
      },
      deep: true,
    },
  },
  methods: {
    ...mapMutations(houseStore, ["SET_STATUS"]),
    abc(event) {
      console.log("클릭클릭", event.target.id);
      event.target.style.color = "red";
      this.SET_STATUS(event.target.id);
    },
  },
};
</script>

<style scoped>
.colorr {
  color: green;
}
.navbar {
  background-color: white;
}
</style>
