/**
 * Created by I326950 on 3/27/2017.
 */
module.exports = function(title) {
    document.title = title;
    let mobile = navigator.userAgent.toLowerCase();
    if (/iphone|ipad|ipod/.test(mobile)) {
        let iframe = document.createElement('iframe');
        iframe.style.display = 'none';
        iframe.src = 'favicon.ico';
        let iframeCallback = function () {
            setTimeout(function () {
                $(iframe).off('load', iframeCallback);
                document.body.removeChild(iframe);
            }, 0);
        };

        $(iframe).load(iframeCallback);
        document.body.appendChild(iframe);
    }
};