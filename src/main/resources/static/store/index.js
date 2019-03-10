import Vue from "vue";
import Vuex from "vuex";
import getters from "./getters";
import mutations from "./mutations";
import actions from "./actions";

Vue.use(Vuex);

const state = {
    userConfiguration: {},
    currentIndex: 0,
    isLoading: false,
    defaultUser: {},
    toast: {
        show: false,
        icon: "weui-loading",
        content: "加载中"
    },
    confirm: {
        show: false,
        title: "确认操作",
        content: "确认删除？",
        onConfirm: function () {

        },
        onCancel: function () {

        }
    },
    alert: {
        show: false,
        title: "出错啦",
        content: "保存失败",
        onOk: function () {

        }
    },
    originToast: {
        show: false,
        icon: "weui-loading",
        content: "加载中"
    },
    originConfirm: {
        show: false,
        title: "确认操作",
        content: "",
        onConfirm: function () {

        },
        onCancel: function () {

        }
    },
    originAlert: {
        show: false,
        title: "出错啦",
        content: "",
        onOk: function () {

        }
    }
};

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions
});