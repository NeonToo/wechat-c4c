<template>
    <div class="weui-msg">
        <div class="weui-msg__icon-area">
            <i class="weui-icon-success weui-icon_msg"></i>
        </div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">绑定结果: {{convertedRoleText}}用户</h2>
            <!--<p class="weui-msg__desc">您已注册成为{{queryObj.role}}用户</p>-->
            <p class="weui-msg__desc">请在五分钟后重新进入公众号获取更新后的菜单</p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a class="weui-btn weui-btn_primary" @click="confirmResult">确认</a>
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
    import querystring from 'querystring';
    import {mapMutations} from 'vuex';

    export default {
        created() {
            const that = this;

            this.queryObj = querystring.parse(location.search.split("?")[1]);
            this.$wx.config({
                originId: that.queryObj.originId,
                debug: true,
                jsApiList: ['closeWindow']
            });
        },
        data() {
            return {
                queryObj: {}
            };
        },
        computed: {
            convertedRoleText() {
                const userType = this.queryObj.type;

                switch (userType) {
                    case 'c4c':
                        return '内部员工';
                    case 'customer':
                        return '购买客户';
                    case 'supplier':
                        return '供应商';
                    default:
                        return userType;
                }
            }
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            confirmResult() {
                this.$wx.closeWindow();
            }
        }
    }
</script>