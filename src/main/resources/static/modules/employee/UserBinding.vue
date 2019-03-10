<template>
    <div class="page">
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">https://</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="text" v-model.trim="c4cUser.system.url"
                           placeholder="请输入系统URL">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">用户名</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="text" v-model="c4cUser.username" placeholder="请输入用户名">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">密码</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="password" v-model="c4cUser.password" placeholder="请输入密码">
                </div>
            </div>
        </div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">设为默认</div>
                <div class="weui-cell__ft">
                    <input class="weui-switch" type="checkbox" v-model="c4cUser.isPrimary">
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <button :disabled="isSubmitted ? 'disabled': false" class="weui-btn weui-btn_primary" @click="bindSystem">
                确定
            </button>
        </div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    const Base64 = require('js-base64').Base64;

    export default {
        created() {
            this.alertModal(this.alert);
        },
        data() {
            return {
                alert: {
                    show: false,
                    title: "出错啦",
                    content: "绑定失败",
                    onOk: this.onOk
                },
                isSubmitted: false,
                c4cUser: {
                    isPrimary: false,
                    system: {
                        url: ""
                    }
                }
            };
        },
        methods: {
            ...mapMutations([
                'isLoading', 'defaultUser', 'toastModal', 'alertModal', 'showToast', 'hideToast', 'showAlert', 'hideAlert'
            ]),
            bindSystem() {
                const that = this;
                let c4cUser = _.cloneDeep(this.c4cUser);

                c4cUser.password = Base64.encode(`${this.c4cUser.username}:${this.c4cUser.password}`);
                c4cUser.system.url = `https://${this.c4cUser.system.url}`;

                this.isSubmitted = true;
                this.isLoading(true);

                $.ajax({
                    method: "POST",
                    url: "users",
                    data: JSON.stringify(c4cUser),
                    dataType: "json",
                    contentType: "application/json"
                }).done(function (oC4CUser) {
                    const toast = {
                        show: true,
                        icon: "weui-icon-success-no-circle",
                        content: "绑定成功"
                    };
                    that.toastModal(toast);

                    if (oC4CUser.isPrimary) {
                        that.defaultUser(oC4CUser);
                    }
                    setTimeout(function () {
                        that.$router.push({name: "users"});
                    }, 2000);
                }).fail(function () {

                }).always(function () {
                    that.isSubmitted = false;
                    that.isLoading(false);
                });
            },
            onOk() {
                this.hideAlert();
            }
        }
    }
</script>