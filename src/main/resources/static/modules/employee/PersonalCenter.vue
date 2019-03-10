<template>
    <div class="page" style="padding: 10px 0">
        <div class="weui-panel">
            <div class="weui-panel__bd">
                <router-link to="" class="weui-media-box weui-media-box_appmsg">
                    <div class="weui-media-box__hd">
                        <img class="weui-media-box__thumb img-circle" :src="weChatUser.headImgUrl" alt="img">
                    </div>
                    <div class="weui-media-box__bd">
                        <h4 class="weui-media-box__title">{{weChatUser.nickName}}</h4>
                        <div class="weui-media-box__desc">
                            <p>{{weChatUser.province}} {{weChatUser.city}}</p>
                        </div>
                    </div>
                    <div class="weui-cell__ft">
                        <i class="weui-icon-download" @click="updateWeChatUser"></i>
                    </div>
                </router-link>
            </div>
        </div>
        <div class="weui-panel">
            <div class="weui-panel__bd">
                <div class="weui-media-box weui-media-box_small-appmsg">
                    <div class="weui-cells">
                        <router-link :to="{name: 'users'}" class="weui-cell weui-cell_access">
                            <div class="weui-cell__hd">
                                <img src="./../../img/crm.png">
                            </div>
                            <div class="weui-cell__bd weui-cell_primary">
                                <p>我的C4C</p>
                            </div>
                            <span class="weui-cell__ft"></span>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>
        <div class="weui-panel">
            <div class="weui-panel__bd">
                <div class="weui-media-box weui-media-box_small-appmsg">
                    <div class="weui-cells">
                        <router-link :to="{name: 'orders'}" class="weui-cell weui-cell_access">
                            <div class="weui-cell__hd">
                                <img src="./../../img/order-blue.png">
                            </div>
                            <div class="weui-cell__bd weui-cell_primary">
                                <p>销售订单</p>
                            </div>
                            <span class="weui-cell__ft"></span>
                        </router-link>
                        <router-link :to="{name: 'activities'}" class="weui-cell weui-cell_access">
                            <div class="weui-cell__hd">
                                <img src="./../../img/status.png">
                            </div>
                            <div class="weui-cell__bd weui-cell_primary">
                                <p>销售活动</p>
                            </div>
                            <span class="weui-cell__ft"></span>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import path from "path";
    import {mapMutations} from 'vuex';
    import defaultHeadImg from './../../img/default_head_img.jpg';

    export default {
        created() {
            this.$store.commit("currentIndex", 3);
            this.isLoading(true);
            this.loadUserInfo();
        },
        data() {
            return {
                weChatUser: {
                    headImgUrl: defaultHeadImg,
                    nickName: "UNKNOWN"
                }
            };
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            loadUserInfo() {
                const that = this;

                $.get("wechat/users/user")
                    .done(function (oWeChatUser) {
                        if (oWeChatUser) {
                            that.weChatUser = oWeChatUser;
                        }
                    }, "json")
                    .fail(function () {

                    })
                    .always(function () {
                        that.isLoading(false);
                    });
            },
            updateWeChatUser() {
                const that = this;

                $.get("wechat/users/user", {force: true}, $.noop(), "json")
                    .done(function (oWeChatUser) {
                        if (oWeChatUser) {
                            that.weChatUser = oWeChatUser;
                        }
                    }, "json")
                    .fail(function () {

                    })
                    .always(function () {
                        that.isLoading(false);
                    });
            }
        }
    }
</script>