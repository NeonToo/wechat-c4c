/**
 * Created by I326950 on 3/24/2017.
 */
import wx from "weixin-js-sdk";
import _ from "lodash";
import $ from "jquery";

let wxObj = _.cloneDeep(wx);

wxObj.config = function ({originId = '', debug = false, jsApiList = []}) {

    $.get("wechat/jssdk/sign/" + originId + "?url=" + encodeURIComponent(location.href.split("#")[0]))
        .done(function (oSignature) {
            wx.config($.extend(oSignature, {
                debug: debug,
                jsApiList: jsApiList
            }));
        })
        .fail(function() {
            console.error("sign for jssdk failed");
            alert("sign for jssdk failed");
        });
};

const plugin = {
    install(Vue) {
        // instance
        Vue.prototype.$wx = wxObj;

        // Global
        Vue.wx = wxObj;
    },
    $wx: wxObj
};

export default plugin;