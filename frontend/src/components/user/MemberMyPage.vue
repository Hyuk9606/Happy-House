<template>
  <div class="container" style="height: 710px">
    <div class="row text-center">
      <h3 id="pagename" class="mt-5">회원 정보</h3>
    </div>
    <!-- 프로필 사진 -->
    <form>
      <input type="hidden" name="action" value="userUpdate" />
      <div class="row mt-3 justify-content-center">
        <div class="input-group input-group-lg mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="userId">아이디</span>
          </div>
          <input
            type="text"
            id="input-userId"
            name="userId"
            class="form-control bg-white"
            readonly
            v-model="userInfo.userId"
          />
        </div>
        <div class="input-group input-group-lg mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="userPass">비밀번호</span>
          </div>
          <input
            type="password"
            id="input-userPass"
            name="userPass"
            class="form-control bg-white"
            v-model="user.userPass"
            placeholder="비밀번호를 입력하세요"
            aria-label="userPass"
          />
        </div>
        <div class="input-group input-group-lg mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="username">이름</span>
          </div>
          <input
            type="text"
            id="input-username"
            name="userName"
            class="form-control bg-white"
            v-model="user.userName"
          />
        </div>
        <div class="input-group input-group-lg mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="email">이메일</span>
          </div>
          <input
            type="email"
            id="input-email"
            name="userEmail"
            class="form-control bg-white"
            v-model="user.userEmail"
            aria-label="E-mail"
          />
        </div>
        <div class="input-group input-group-lg mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="phone">전화번호</span>
          </div>
          <input
            type="tel"
            id="input-phone"
            name="userPhone"
            class="form-control bg-white"
            v-model="user.userPhone"
            aria-label="Phone number"
          />
        </div>
      </div>
      <div class="row">
        <b-button
          type="button"
          id="useredit"
          class="col-md-1 mr-3 offset-md-9 btn btn-success"
          @click="userUpdate"
          >회원수정</b-button
        >
        <b-button
          type="button"
          id="userdelete"
          class="col-md-1 ml-5 ms-6 btn btn-danger"
          @click="userDelete"
          >회원삭제</b-button
        >
      </div>
    </form>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import http from "@/util/http-common";

const memberStore = "memberStore";

export default {
  data() {
    console.log("data set");
    return {
      user: {},
    };
  },
  props: {
    type: { type: String },
  },
  name: "MyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  created() {
    console.log("created", this.userInfo);
    this.getuser(this.userInfo);
  },

  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    getuser(user) {
      console.log("get user", this.userInfo);
      //this.user = user;
      this.user.userName = user.userName;
      this.user.userEmail = user.userEmail;
      this.user.userPhone = user.userPhone;
      this.user.userId = this.userInfo.userId;
      console.log("this.uer", this.user);
    },
    userUpdate() {
      let msg = "수정 실패";

      http
        .put(`/user`, {
          userId: this.userInfo.userId,
          userPass: this.user.userPass,
          userName: this.user.userName,
          userEmail: this.user.userEmail,
          userPhone: this.user.userPhone,
        })
        .then(({ data }) => {
          if (data == "success") {
            msg = "수정 성공";
            console.log(this.user);
            this.SET_USER_INFO(this.user);
            this.$router.push({ name: "Home" });
          }
        })
        .catch((error) => console.log(error))
        .finally(() => {
          alert(msg);
        });
    },

    userDelete() {
      let msg = "삭제 실패";
      http
        .delete(`/user/${this.userInfo.userId}`, {})
        .then(({ data }) => {
          if (data == "success") {
            msg = "삭제 성공";
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
            // if (this.$route.path != "/") this.$router.push({ name: "Home" });
          }
        })
        .finally(() => {
          this.$router.push({ name: "Home" });
          alert(msg);
        });
    },
  },
};
</script>

<style></style>
