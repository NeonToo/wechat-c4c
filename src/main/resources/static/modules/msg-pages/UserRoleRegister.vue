<template>
    <div class="weui-msg">
        <div class="weui-msg__icon-area">
            <i class="weui-icon-info weui-icon_msg"></i>
        </div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">{{title}}</h2>
            <p class="weui-msg__desc">{{description}}</p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a class="weui-btn weui-btn_primary" @click="registerUserTag">确认</a>
                <a class="weui-btn weui-btn_default" @click="cancelRegister">取消</a>
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
            const queryObj = querystring.parse(location.search.split("?")[1]);
            const userType = queryObj.type;

            this.queryObj = queryObj;

            switch (userType){
                case 'c4c':
                    this.title = '内部员工';
                    this.description = '已有C4C系统用户权限，否则无法获取系统数据';
                    break;
                case 'customer':
                    this.title = '购买客户';
                    this.description = '注册成为购买客户';
                        break;
                case 'supplier':
                    this.title = '供应商';
                    this.description = '注册成为供应商';
                    break;
                default:

            }

            this.$wx.config({
                originId: that.queryObj.originId,
                debug: true,
                jsApiList: ['closeWindow']
            });
        },
        data() {
            return {
                title: '',
                description: '',
                queryObj: {}
            };
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            registerUserTag() {
                const that = this;

                this.isLoading(true);
                $.ajax({
                    url: "wechat/users/user/tag",
                    method: "PUT",
                    data: JSON.stringify(this.queryObj),
                    dataType: "json",
                    contentType: "application/json"
                }).done(function () {
                    location.href = `${location.href.split("?")[0]}?originId=${that.queryObj.originId}&type=${that.queryObj.type}#/result`;
//                    that.$router.push({name: 'result', query: {originId: that.queryObj.originId ,role: that.queryObj.isC4CUser}});
                }).fail(function () {

                }).always(function () {
                    that.isLoading(false);
                });

            },
            cancelRegister() {
                this.$wx.closeWindow();
            }
        }
    }
</script>