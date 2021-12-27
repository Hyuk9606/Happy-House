<template>
  <b-container class="bv-example-row mt-3" style="height: 705px">
    <b-row>
      <b-col></b-col>
      <b-col cols="7">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="비밀번호:" label-for="userPass">
              <b-form-input
                type="password"
                id="userPass"
                v-model="user.userPass"
                required
                placeholder="변경하실 비밀번호를 입력해주세요"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호 확인:" label-for="userpassCheck">
              <b-form-input
                type="password"
                id="userpassCheck"
                v-model="user.userpassCheck"
                required
                placeholder="비밀번호를 다시 입력해주세요"
              ></b-form-input>
            </b-form-group>

            <b-button
              block
              type="button"
              variant="primary"
              class="m-1"
              @click="change"
              >비밀번호 변경</b-button
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

// const memberStore = "memberStore";

export default {
  name: "find",
  data() {
    return {
      user: {
        // userId:'',
        userPass: "",
        // userName:'',
        userpassCheck: "",
        // userEmail:'',
        // userPhone:'',
      },
    };
  },
  methods: {
    change() {
      let msg = "비밀번호 수정 실패";

      if (this.user.userPass == this.user.userpassCheck) {
        http
          .put(`/user/pass`, {
            userId: this.$route.params.userId,
            userPass: this.user.userPass,
            userName: this.$route.params.userName,
            userEmail: this.$route.params.userEmail,
            userPhone: this.$route.params.userPhone,
          })
          .then(({ data }) => {
            console.log(data);
            if (data == "success") {
              msg = "비밀번호 수정 성공";
              this.$router.push({ name: "Home" });
            }
          })
          .catch((error) => console.log(error))
          .finally(() => {
            alert(msg);
          });
      } else {
        msg = "비밀번호가 다릅니다";
        alert(msg);
      }
    },
  },
};
</script>

<style></style>
