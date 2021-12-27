<template>
  <div class="container" style="height: 710px">
    <div class="row text-center">
      <h3 id="pagename" class="mt-5">회원 가입</h3>
    </div>
    <!-- 프로필 사진 -->
    <div class="row mt-4 justify-content-center">
      <b-form @submit.prevent="Register">
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="ID">ID</span>
          </div>
          <b-form-input
            id="userId"
            v-model="user.userId"
            type="text"
            required
            placeholder="UserID"
          ></b-form-input>
          <b-button variant="outline-primary" @click="check"
            >ID 중복체크</b-button
          >
          <!-- <input type="text" id="userId" name="userId" class="form-control bg-white" placeholder="UserID" aria-label="Username"> -->
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="pass">PASSWORD</span>
          </div>
          <b-form-input
            id="userPass"
            v-model="user.userPass"
            type="password"
            required
            placeholder="PASSWORD"
          ></b-form-input>
          <!-- <input type="password" id="userPass" name="userPass" class="form-control bg-white" placeholder="PASSWORD" aria-label="Username"> -->
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="passCheck">PWD Check</span>
          </div>
          <b-form-input
            id="userPassCheck"
            v-model="user.userpassCheck"
            type="password"
            required
            placeholder="PASSWORD Check"
          ></b-form-input>
          <!-- <input type="password" id="userPassCheck" name="userpassCheck" class="form-control bg-white" placeholder="PASSWORD Check" aria-label="Username"> -->
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="username">이름</span>
          </div>
          <b-form-input
            id="userName"
            v-model="user.userName"
            type="text"
            required
            placeholder="Username"
          ></b-form-input>
          <!-- <input type="text" id="userName" name="userName" class="form-control bg-white" placeholder="Username" aria-label="Username"> -->
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="email">이메일</span>
          </div>
          <b-form-input
            id="userEmail"
            v-model="user.userEmail"
            type="email"
            required
            placeholder="E-mail"
          ></b-form-input>
          <!-- <input type="email" id="userEmail" name="userEmail" class="form-control bg-white" placeholder="E-mail" aria-label="E-mail"> -->
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="phone">전화번호</span>
          </div>
          <b-form-input
            id="userPhone"
            v-model="user.userPhone"
            type="text"
            required
            placeholder="Phone number"
          ></b-form-input>
          <!-- <input type="tel" id="userPhone" name="userPhone" class="form-control bg-white" placeholder="Phone number" aria-label="Phone number"> -->
        </div>
        <div>
          <b-button type="submit" id="registerBtn" class="ms-5 btn btn-success"
            >가 입</b-button
          >
          <b-button type="reset" variant="danger" class="ms-5">취소</b-button>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "MemberJoin",
  data() {
    return {
      user: {
        userId: "",
        userPass: "",
        userpassCheck: "",
        userName: "",
        userEmail: "",
        userPhone: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  methods: {
    Register() {
      let msg = "회원가입 실패";
      if (this.user.userPass === this.user.userpassCheck) {
        http
          .post("/user/register", {
            userId: this.user.userId,
            userPass: this.user.userPass,
            userName: this.user.userName,
            userEmail: this.user.userEmail,
            userPhone: this.user.userPhone,
          })
          .then(({ data }) => {
            if (data === "success") {
              msg = "등록 완료";
            }
            alert(msg);
            this.movelogin();
          });
      } else {
        alert(msg);
      }
    },
    check() {
      let msg = "사용할 수 없는 아이디 입니다";
      http
        .get(`/user/check/${this.user.userId}`, {})
        .then(({ data }) => {
          console.log(data);
          if (!data) {
            msg = "사용 가능한 아이디 입니다.";
          }
        })
        .finally(() => alert(msg));
    },

    movelogin() {
      this.$router.push({ name: "SignIn" });
    },
  },
};
</script>

<style></style>
