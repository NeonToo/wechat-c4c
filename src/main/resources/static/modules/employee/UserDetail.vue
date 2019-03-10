<template>
    <div class="page">
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">系统URL</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="url" v-model="c4cUser.system.url"/>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">用户名</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="text" v-model="c4cUser.username"/>
                </div>
            </div>
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__hd">
                    <label class="weui-label">默认</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-switch" type="checkbox" v-model="c4cUser.isPrimary" :true-value="true"
                           :false-value="false">
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <button v-if="isModified" :disabled="isSubmitted ? 'disabled' : false" class="weui-btn weui-btn_primary"
                    :class="{'weui-btn_disabled': isSubmitted}" @click="updateUser">保存
            </button>
            <button class="weui-btn weui-btn_warn" @click="deleteUser">删除</button>
        </div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    import {mapActions} from 'vuex';

    export default {
        created() {
            this.isLoading(true);
            this.confirmModal(this.confirm);
            this.alertModal(this.alert);
            this.getUser();
        },
        data() {
            return {
                isModified: false,
                isSubmitted: false,
                c4cUser: {
                    system: {
                        url: ""
                    }
                },
                originUser: {},
                confirm: {
                    show: false,
                    title: "确认删除",
                    content: "确认删除？",
                    onConfirm: this.onConfirm,
                    onCancel: this.onCancel
                },
                alert: {
                    show: false,
                    title: "出错啦",
                    content: "保存失败",
                    onOk: this.onOk
                }
            }
        },
        watch: {
            'c4cUser': {
                deep: true,
                handler: function (newUser) {
                    if (!_.isEqual(newUser, this.originUser)) {
                        this.isModified = true;
                    } else {
                        this.isModified = false;
                    }
                }
            }
        },
        methods: {
            ...mapMutations([
                'isLoading', 'defaultUser', 'toastModal', 'confirmModal', 'alertModal', 'showToast', 'hideToast', 'showAlert', 'hideAlert', 'showConfirm', 'hideConfirm'
            ]),
            ...mapActions([
                'resetToast'
            ]),
            getUser() {
                const that = this;
                const c4cUserId = this.$route.params.id;
                const c4cUserFromRoute = this.$route.params.c4cUser;

                if (c4cUserFromRoute) {
                    this.c4cUser = c4cUserFromRoute;
                    this.originUser = _.cloneDeep(this.c4cUser);
                    this.isLoading(false);
                } else {
                    $.get(`users/${c4cUserId}`, null, $.noop(), "json")
                        .done(function (oUser) {
                            if (oUser) {
                                that.c4cUser = oUser;
                                that.originUser = _.cloneDeep(that.c4cUser);
                            }
                        })
                        .fail(function () {
                            that.showAlert();
                        })
                        .always(function () {
                            that.isLoading(false);
                        });
                }
            },
            updateUser() {
                const that = this;

                this.isSubmitted = true;
                this.isLoading(false);
                $.ajax({
                    method: "PUT",
                    url: `users/${this.c4cUser.id}`,
                    data: JSON.stringify(this.c4cUser),
                    dataType: "json",
                    contentType: "application/json"
                }).done(function (oUser) {
                    const toast = {
                        show: true,
                        icon: "weui-icon-success-no-circle",
                        content: "保存成功"
                    };
                    that.toastModal(toast);
                    that.c4cUser = oUser;
                    that.originUser = _.cloneDeep(that.c4cUser);

                    if (oUser.isPrimary) {
                        that.defaultUser(oUser);
                    }
                    setTimeout(function () {
                        that.resetToast();
                        that.hideToast();
                    }, 2000);
                }).fail(function () {
                    that.showAlert();
                }).always(function () {
                    that.isSubmitted = false;
                    that.isLoading(false);
                });
            },
            deleteUser() {
                this.showConfirm();
            },
            onConfirm() {
                const that = this;

                this.hideConfirm();
                this.isSubmitted = true;
                this.isLoading(false);

                $.ajax({
                    method: "DELETE",
                    url: "users/" + this.c4cUser.id
                }).done(function () {
                    const toast = {
                        show: true,
                        icon: "weui-icon-success-no-circle",
                        content: "删除成功"
                    };
                    that.toastModal(toast);
                    if (that.c4cUser.isPrimary) {
                        that.defaultUser({});
                    }
                    setTimeout(function () {
                        that.$router.replace({name: "users"});
                    }, 2000);
                }).fail(function () {
                    that.showAlert();
                }).always(function () {
                    that.isSubmitted = false;
                    that.isLoading(false);
                });
            },
            onCancel() {
                this.hideConfirm();
            },
            onOk() {
                this.hideAlert();
            }
        }
    }
</script>