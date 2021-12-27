<template>
  <b-container class="bv-example-row mt-3 text-center" fluid>
    <h2 class="underline-hotpink p-2">
      <b-icon icon="camera"></b-icon> 사진첩
    </h2>

    <b-row>
      <b-col sm="1"></b-col>
      <b-col>
        <div>
          <b-card-group>
            <div v-for="item in num" :key="item">
              <!-- <b-card :img-src="'https://github.com/Hyuk9606/MyImage/blob/main/bori_' + item + '.jpg?raw=true'" img-alt="Image" img-top style="max-width: 20rem;"></b-card> -->
              <!-- <b-img :src="'https://github.com/Hyuk9606/MyImage/blob/main/bori_' + item + '.jpg?raw=true'" width="150" fluid alt="Responsive image"></b-img> -->

              <div class="p-2">
                <!-- <router-link> -->
                <b-img
                  thumbnail
                  fluid
                  :src="
                    'https://github.com/Hyuk9606/MyImage/blob/main/bori_' +
                    item +
                    '.jpg?raw=true'
                  "
                  alt="Image 1"
                  width="300"
                  @click="
                    (number = item),
                      boriImg(
                        'https://github.com/Hyuk9606/MyImage/blob/main/bori_' +
                          item +
                          '.jpg?raw=true'
                      )
                  "
                ></b-img>
                <!-- </router-link> -->
              </div>
            </div>
          </b-card-group>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
export default {
  name: "Bori",
  data() {
    return {
      num: 30,
      number: "",
    };
  },
  methods: {
    boriImg(event) {
      // console.log(this.number);
      this.$router.push({
        name: "BoriDetail",
        params: { url: event, number: this.number, num: this.num },
      });
    },
  },
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(243, 201, 201, 0) 70%,
    rgba(253, 0, 177, 0.295) 30%
  );
}
</style>
