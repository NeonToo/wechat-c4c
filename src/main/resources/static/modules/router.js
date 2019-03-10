import Vue from "vue";
import VueRouter from "vue-router";
import store from "./../store";
import setWechatTitle from './../utils/setWechatTitle';
import Error from "./msg-pages/Error";
import RoleRegister from './msg-pages/UserRoleRegister';
import UserRegisterResult from './msg-pages/RegisterResult';
import App from './App';
import Components from './components';

Vue.use(VueRouter);


let childRoutes = [];
let routes = [
    {
        path: '/',
        name: 'app',
        component: App,
        meta: {title: 'C4C Integration'},
        children: childRoutes
    },
    {
        path: '/error',
        name: 'error',
        component: Error,
        meta: {title: '出错啦'}
    },
    {
        path: '/role',
        name: 'role',
        component: RoleRegister,
        meta: {title: '用户注册'}
    },
    {
        path: '/result',
        name: 'result',
        component: UserRegisterResult,
        meta: {title: '注册结果'}
    },
    {
        path: "*",
        redirect: {
            name: 'error',
            params: {
                error: {
                    title: "出错啦",
                    description: "抱歉，找不到您需要的页面"
                }
            }
        }
    }
];

$.get({
    url: "wechat/users/user/configuration",
    dataType: "json",
    async: false
}).done(function (oConfig) {
    store.commit("userConfiguration", oConfig);
    if (!_.isEmpty(oConfig.routes)) {
        _.each(oConfig.routes, function (route) {
            const component = Components[route.componentName];
            const src = component.src;
            const newRoute = {
                path: route.path,
                name: route.name,
                component: require("./" + src),
                meta: component.meta
            };

            if(route.isChild) {
                childRoutes.push(newRoute);
            } else {
                routes.push(newRoute);
            }
        });
    }
}).fail(function () {

}).always(function () {

});

const router = new VueRouter({
    mode: "hash",
    base: __dirname,
    routes
});

router.afterEach(function (route) {
    store.dispatch("resetComponents");
    // setWechatTitle(route.meta.title);
});

export default router;

// const routes = [
//     {
//         path: '/',
//         name: 'app',
//         component: App,
//         meta: {title: 'C4C Integration'},
//         children: roleBasedRoutes
//     },
//     {
//         path: '/error',
//         name: 'error',
//         component: Error,
//         meta: {title: '出错啦'}
//     },
//     {
//         path: '/role',
//         name: 'role',
//         component: RoleRegister,
//         meta: {title: '用户注册'}
//     },
//     {
//         path: '/result',
//         name: 'result',
//         component: UserRegisterResult,
//         meta: {title: '注册结果'}
//     },
//     {
//         path: "*",
//         redirect: {
//             name: 'error',
//             params: {
//                 error: {
//                     title: "出错啦",
//                     description: "抱歉，找不到您需要的页面"
//                 }
//             }
//         }
//     }
// ];