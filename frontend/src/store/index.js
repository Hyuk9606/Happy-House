import Vue from "vue";
import Vuex from "vuex";

import createPersistedState from "vuex-persistedstate";
import houseStore from "@/store/modules/houseStore.js";

Vue.use(Vuex);

import memberStore from "@/store/modules/memberStore.js";

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    houseStore,
    memberStore,
  },

  plugins: [
    createPersistedState({
      storage: sessionStorage,
      paths: ["memberStore"],
    }),
  ],
});
