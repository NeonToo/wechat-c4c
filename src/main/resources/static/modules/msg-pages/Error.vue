<template>
    <div class="weui-msg">
        <div class="weui-msg__icon-area">
            <i class="weui-icon-warn weui-icon_msg"></i>
        </div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">{{error.title}}</h2>
            <p class="weui-msg__desc">{{error.description}}</p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a  class="weui-btn weui-btn_default" @click="goBack">返回上一页</a>
            </p>
        </div>
        <div class="weui-msg__extra-area">
            <div class="weui-footer">
                <p class="weui-footer__links">
                    <a href="https://www.sap.com/index.html" class="weui-footer__link">SAP主页</a>
                </p>
                <p class="weui-footer__text">Copyright &copy; SAP Hybris Cloud for Customer</p>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                error: {
                    title: "出错啦",
                    description: "抱歉，找不到您需要的页面"
                }
            };
        },
        created() {
            const errorParam = this.$route.params.error;

            if(errorParam) {
                this.error = errorParam;
            }
        },
        methods: {
            goBack() {
                this.$router.go(-1);
            }
        }
    }
</script>