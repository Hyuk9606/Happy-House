<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light static-top">
    <div class="container-fluid">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/happyCatTree.png"
            class="d-inline-block align-middle"
            width="70"
            alt="Kitten"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <h4>
            <li class="nav-item mr-3 ml-3">
              <router-link to="/"><b-icon icon="house"></b-icon>홈</router-link>
            </li>
          </h4>
          <h4>
            <li class="nav-item mr-3 ml-3">
              <router-link to="/intro"
                ><b-icon icon="file-text"></b-icon>소개</router-link
              >
            </li>
          </h4>

          <h4>
            <li class="nav-item mr-3 ml-3">
              <router-link to="/apart"
                ><b-icon icon="building"></b-icon>아파트 매매</router-link
              >
            </li>
          </h4>
          <h4>
            <li class="nav-item dropdown mr-3 ml-3">
              <router-link :to="{ name: 'Board' }" class="link">
                <b-icon icon="question-circle"></b-icon>질의응답
              </router-link>
            </li>
          </h4>

          <h4>
            <li class="nav-item dropdown mr-3 ml-3">
              <router-link :to="{ name: 'Bori' }" class="link">
                <b-icon icon="camera"></b-icon>사진첩
              </router-link>
            </li>
          </h4>
        </ul>
      </div>

      <b-navbar-nav class="ml-auto" v-if="userInfo">
        <b-nav-item class="align-self-center"
          ><b-avatar
            variant="primary"
            v-text="userInfo ? userInfo.userId.charAt(0).toUpperCase() : ''"
          ></b-avatar
          >{{ userInfo.userName }}({{ userInfo.userId }})님
          환영합니다.</b-nav-item
        >
        <b-nav-item class="align-self-center"
          ><router-link :to="{ name: 'MyPage' }" class="link align-self-center"
            >내정보보기</router-link
          ></b-nav-item
        >
        <b-nav-item
          class="link align-self-center"
          @click.prevent="onClickLogout"
          >로그아웃</b-nav-item
        >
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto" v-else>
        <b-nav-item-dropdown right>
          <template #button-content>
            <b-icon icon="people" font-scale="2"></b-icon>
          </template>
          <b-dropdown-item href="#"
            ><router-link :to="{ name: 'SignUp' }" class="link"
              ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
            ></b-dropdown-item
          >
          <b-dropdown-item href="#"
            ><router-link :to="{ name: 'SignIn' }" class="link"
              ><b-icon icon="key"></b-icon> 로그인</router-link
            ></b-dropdown-item
          >
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </div>
  </nav>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style></style>
