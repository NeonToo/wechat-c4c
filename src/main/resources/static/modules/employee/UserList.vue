<template>
    <div class="page">
        <main>
            <div class="weui-panel">
                <div class="weui-panel__hd">共{{c4cUsers.length}}个已绑定系统</div>
                <div class="weui-panel__bd">
                    <div v-for="user in c4cUsers" class="weui-media-box weui-media-box_small-appmsg">
                        <div class="weui-cells">
                            <scroll-cell :hasLink="true"
                                         :to="{ name: 'userDetail', params: { id: user.id, c4cUser: user }}">
                                <div class="weui-cell">
                                    <div class="weui-cell__bd weui-cell_primary">
                                        <p>{{user.username}}</p>
                                        <p class="font-info">{{user.system.url}}</p>
                                    </div>
                                    <i v-if="user.isPrimary" class="weui-icon-success"></i>
                                    <i v-else class="weui-cell__ft"></i>
                                </div>
                                <button v-if="user.isPrimary" slot="scroll-area" type="button"
                                        class="scroll-btn scroll-btn_default"
                                        @click="changeDefaultStatus(user.id)">取消默认
                                </button>
                                <button v-else slot="scroll-area" type="button"
                                        class="scroll-btn scroll-btn_default"
                                        @click="changeDefaultStatus(user.id)">设为默认
                                </button>
                                <button slot="scroll-area" type="button" class="scroll-btn scroll-btn_warn"
                                        @click="deleteUser(user)">删除
                                </button>
                            </scroll-cell>
                        </div>
                    </div>
                </div>
                <div class="weui-panel__ft">
                    <router-link :to="{name: 'user'}" class="weui-cell weui-cell_access">
                        <div class="weui-cell__hd">
                            <i class="weui-icon-add-circle"></i>
                        </div>
                        <div class="weui-cell__bd">新增绑定</div>
                        <span class="weui-cell__ft"></span>
                    </router-link>
                </div>
            </div>
        </main>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    import {mapActions} from 'vuex';
    import ScrollCell from './../../components/scroll-cell/ScrollCell';

    export default {
        components: {
            ScrollCell
        },
        created() {
            this.isLoading(true);
            this.confirmModal(this.confirm);
            this.alertModal(this.alert);
            this.loadUsers();
        },
        data() {
            return {
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
                    content: "操作失败",
                    onOk: this.onOk
                },
                c4cUsers: [],
                c4cUserToDelete: {}
            };
        },
        methods: {
            ...mapMutations([
                'isLoading', 'defaultUser', 'toastModal', 'showToast', 'hideToast', 'confirmModal', 'showConfirm', 'hideConfirm', 'alertModal', 'showAlert', 'hideAlert'
            ]),
            ...mapActions([
                'resetToast'
            ]),
            loadUsers() {
                const that = this;

                $.get("users", null, $.noop(), "json")
                    .done(function (aC4CUsers) {
                        if (aC4CUsers) {
                            that.c4cUsers = aC4CUsers;
                        }
                    })
                    .fail(function () {

                    })
                    .always(function () {
                        that.isLoading(false);
                    });
            },
            changeDefaultStatus(userId) {
                const that = this;

                this.isLoading(true);

                $.ajax({
                    method: "PATCH",
                    url: `users/${userId}`
                }).done(function (oUser) {
                    const toast = {
                        show: true,
                        icon: "weui-icon-success-no-circle",
                        content: "操作成功"
                    };
                    that.toastModal(toast);

                    if (oUser.isPrimary) {
                        that.defaultUser(oUser);
                    }

                    setTimeout(function () {
                        that.$router.go(0);
                    }, 2000);
                }).fail(function () {
                    that.showAlert();
                }).always(function () {
                    that.isLoading(false);
                });
            },
            deleteUser(user) {
                this.c4cUserToDelete = user;
                this.showConfirm();
            },
            onConfirm() {
                const that = this;

                this.hideConfirm();
                this.isLoading(true);

                $.ajax({
                    method: "DELETE",
                    url: `users/${this.c4cUserToDelete.id}`
                }).done(function () {
                    const toast = {
                        show: true,
                        icon: "weui-icon-success-no-circle",
                        content: "删除成功"
                    };
                    that.toastModal(toast);
                    if (that.c4cUserToDelete.isPrimary) {
                        that.defaultUser({});
                    }

                    setTimeout(function () {
                        that.resetToast();
                        that.isLoading(false);
                        that.loadUsers();
                    }, 1000);
                }).fail(function () {
                    that.showAlert();
                }).always(function () {
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