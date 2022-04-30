/**
 * 主要业务逻辑相关
 */
var userUID = readCookie("uid")
var distributionList = null;
var waiterId = readCookie("waiterId");
closeBrowser();

function closeBrowser() {
	var browser = {
		firefox: false,
		chrome: false,
		version: 0
	    },
	    ua = window.navigator.userAgent.toLowerCase();
	if (/(msie|firefox|opera|chrome|netscape)\D+(\d[\d.]*)/.test(ua)) {
	      browser[RegExp.$1] = true;
	      browser.version = RegExp.$2;
	}
	if(browser.firefox) {
		$(window).on("beforeunload", function() {
			updateWaiterStatus(waiterId, 0);
            return '';
        });
	} else {
		$(window).on('unload', function(e){
			updateWaiterStatus(waiterId, 0);
		});
	}
}

/**
 * 实例化
 * @see module/base/js
 */
var yunXin
// 等待私有化配置请求完毕
if (CONFIG.usePrivateEnv === 1) {
 function waitPrivateConf() {
   if (CONFIG.privateConf || CONFIG.usePrivateEnv === 2) {
     yunXin = new YX(userUID)
   } else {
     setTimeout(waitPrivateConf, 1000)
   }
 }
 waitPrivateConf()
} else {
  yunXin = new YX(userUID)
}
