<template>
  <div>
    <header
      class="masthead"
      style="background-image: url('../assets/bg-masthead.jpg')"
    >
      <div class="container-fluid position-relative">
        <div class="row justify-content-center img-intro">
          <div
            class="col-xl-9"
            style="background-image: url('../assets/bg-masthead.jpg')"
          >
            <div class="text-center text-white">
              <h1 class="mb-5">아파트 거래 정보를 쉽게 찾아보세요!</h1>
              <form
                class="form-subscribe"
                id="contactForm"
                data-sb-form-api-token="API_TOKEN"
              >
                <div class="d-none" id="submitSuccessMessage">
                  <div class="text-center mb-3">
                    <div class="fw-bolder">Form submission successful!</div>
                    <p>To activate this form, sign up at</p>
                    <a
                      class="text-white"
                      href="https://startbootstrap.com/solution/contact-forms"
                      >https://startbootstrap.com/solution/contact-forms</a
                    >
                  </div>
                </div>
                <div class="d-none" id="submitErrorMessage">
                  <div class="text-center text-danger mb-3">
                    Error sending message!
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </header>
    <section class="showcase">
      <div class="container-fluid p-0">
        <div class="row g-0">
          <div class="col-lg-6 order-lg-2 text-white showcase-img sc1"></div>
          <div class="col-lg-6 order-lg-1 my-auto showcase-text">
            <h2 class="underline-sky p-2">편리한 UI</h2>
            <p class="lead mb-0">
              반응형 웹으로 모바일이나 테블릿 모두 편하게 검색하고 사용하실 수
              있습니다.
            </p>
          </div>
        </div>
        <div class="row g-0">
          <div class="col-lg-6 text-white showcase-img sc2"></div>
          <div class="col-lg-6 my-auto showcase-text">
            <h2 class="underline-green p-2">빠른 응대 및 처리</h2>
            <p class="lead mb-0">
              발생하는 에러에 대해 빠르게 접수받고, 처리하여 최상의 기능을
              제공합니다.
            </p>
          </div>
        </div>
        <div class="row g-0">
          <div class="col-lg-6 order-lg-2 text-white showcase-img sc3"></div>
          <div class="col-lg-6 order-lg-1 my-auto showcase-text">
            <h2 class="underline-hotpink p-2">귀여운 보리</h2>
            <p class="lead mb-0">귀여운 보리의 사진을 수십장 제공합니다.</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.sc1 {
  background-image: url("../assets/bg-showcase-1.jpg");
}
.sc2 {
  background-image: url("../assets/bg-showcase-2.jpg");
}
.sc3 {
  background-image: url("../assets/bg-showcase-3.jpg");
}
.img-intro {
  background-image: url("../assets/bg-masthead.jpg");
  background-size: contain;
  width: 2100px;
  height: 800px;
}
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(243, 201, 201, 0) 70%,
    rgba(253, 0, 177, 0.295) 30%
  );
}
.underline-green {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(219, 253, 0, 0.394) 30%
  );
}
.underline-sky {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(0, 223, 253, 0.425) 30%
  );
}
</style>
