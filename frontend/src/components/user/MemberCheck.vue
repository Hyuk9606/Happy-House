<template>
  <b-container class="bv-example-row mt-3" style="height: 705px">
    <b-row>
      <b-col></b-col>
      <b-col cols="7">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userId">
              <b-form-input
                id="userId"
                v-model="user.userId"
                required
                placeholder="아이디 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="userEmail">
              <b-form-input
                type="Email"
                id="userEmail"
                v-model="user.userEmail"
                required
                placeholder="이메일 입력...."
              ></b-form-input>
            </b-form-group>
            <b-row>
              <b-col>
                <b-button
                  block
                  type="button"
                  variant="primary"
                  class="m-1"
                  @click="find"
                  >확인</b-button
                >
              </b-col>
              <b-col>
                <b-button
                  block
                  type="button"
                  variant="danger"
                  class="m-1"
                  @click="goLogin"
                  >취소</b-button
                >
              </b-col></b-row
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "MemberCheck",
  data() {
    return {
      user: {
        userId: "",
        userPass: "",
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
    goLogin() {
      this.$router.push({ name: "SignIn" });
    },
    find() {
      let msg = "등록 되지 않은 회원입니다.";
      http.get(`/user/check/${this.user.userId}`, {}).then(({ data }) => {
        if (data.userEmail == this.user.userEmail) {
          // console.log(data.userId);
          this.$router.push({
            name: "Find",
            params: {
              userId: data.userId,
              userPass: data.userPass,
              userName: data.userName,
              userEmail: data.userEmail,
              userPhone: data.userPhone,
            },
          });
          // console.log("zzz");
        } else {
          alert(msg);
        }
        // console.log(data);
      });
    },
  },
};
</script>

<style></style>
