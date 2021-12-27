<template>
    <b-container fluid>
        <b-row >
            <b-col cols="2"></b-col>
            <b-col cols="8">
                
                <div class="d-flex justify-content-center" style="height: 760px">
                    <b-img :src="url" style="height: 100%"></b-img>
                </div>
                <div class="d-flex justify-content-center">
                    <b-button-group class="mx-1 mt-2">
                        <b-button variant="outline-primary" @click="pre">이전</b-button>
                        <b-button variant="outline-primary" @click="list">목록</b-button>
                        <b-button variant="outline-primary" @click="next">다음</b-button>
                    </b-button-group>
                </div>
            </b-col>
            <b-col ></b-col>
        </b-row>
    </b-container>
</template>

<script>
export default {
    data() {
        console.log(this.$route.params);
        return {
            url: '',
            num: '',
            number: '',
        }
    },
    mounted() {
        this.url = this.$route.params.url;
        this.num = this.$route.params.num;
        this.number = this.$route.params.number;
    },
    methods: {
        pre() {
            // console.log(this.number);
            if(this.number > 1) {
                this.url = 'https://github.com/Hyuk9606/MyImage/blob/main/bori_' + (this.number = this.number-1) + '.jpg?raw=true';
                console.log(this.number);
            }
            else {
                let msg = "처음 이미지 입니다."
                alert(msg);
            }
        },
        list() {
            this.$router.push({name: "Bori"})
        },
        next() {
            if(this.number < this.num) {
                this.url = 'https://github.com/Hyuk9606/MyImage/blob/main/bori_' + (this.number = this.number+1) + '.jpg?raw=true';
                console.log(this.number);
            }
            else {
                let msg = "마지막 이미지 입니다."
                alert(msg);
            }
        },
    },
}
</script>

<style>

</style>