import Vue from "vue";
import WxJsSDK from "./../plugins/wx-js-sdk";
import router from './router';
import store from "./../store";
import Loading from "./../components/loading/Loading";
import Toast from "./../components/toast/Toast";
import Confirm from "./../components/confirm/Confirm";
import Alert from "./../components/alert/Alert";
import {mapState} from 'vuex';
// css import
import style from './../css/style.css';

Vue.use(WxJsSDK);

const app = new Vue({
    name: 'app',
    store,
    router,
    components: {
        Loading,
        Toast,
        Confirm,
        Alert
    },
    data: {
        transitionDirection: "slide-left"
    },
    created() {
        $("#loading").remove();
    },
    computed: mapState([
        'isLoading', 'toast', 'confirm', 'alert'
    ]),
    watch: {
        '$route'(to, from) {
            const toDepth = to.path.split('/').length;
            const fromDepth = from.path.split('/').length;

            this.transitionDirection = toDepth < fromDepth || to.path === "/" ? 'slide-right' : 'slide-left';
        }
    }
}).$mount('#app');