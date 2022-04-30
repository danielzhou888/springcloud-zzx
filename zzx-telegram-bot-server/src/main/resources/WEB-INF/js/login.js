var Login = {
	init: function() {
		this.initNode();
		this.showNotice();
		this.initAnimation();
		this.addEvent();
	},
	initNode: function() {
		//初始化节点
		this.$account = $('#j-account');
		this.$pwd = $('#j-secret');
		this.$errorMsg = $('#j-errorMsg');
		this.$loginBtn = $('#j-loginBtn');
    	this.$footer = $('#footer');
	},
	initAnimation: function() {
		//添加动画
		var $wrapper = $('#j-wrapper'),
			wrapperClass = $wrapper.attr('class');
		$wrapper.addClass('fadeInDown animated')
			.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
				function() {
					$(this).removeClass().addClass(wrapperClass);
				}
			);
	},
	/**
	 * 如果浏览器非IE10,Chrome, FireFox, Safari, Opera的话，显示提示
	 */
	showNotice: function() {
		var browser = this.getBrowser(),
			temp = browser.split(' '),
			appname = temp[0],
			version = temp[1];
		if (['msie', 'firefox', 'opera', 'safari', 'chrome'].contains(appname)) {
			if (appname == 'msie' && version < 10) {
				this.$footer.find('p').removeClass('hide');
			}
		} else {
			this.$footer.find('p').removeClass('hide');
		}
	},
  addEvent: function() {
    // 绑定事件
    var that = this;
    this.$loginBtn.on('click', this.validate.bind(this));
    $(document).on('keydown', function(e) {
      var ev = e || window.event;
      if (ev.keyCode === 13) {
        that.validate();
      }
    });
  },

  validate: function() {
	this.$errorMsg.addClass('hide');
    // 登录验证
    var username = $.trim(this.$account.val()),
    	password = this.$pwd.val(),
      	errorMsg = '';
    if (username.length === 0) {
    	errorMsg = '帐号不能为空';
    } else if (!password || password.length < 6) {
    	errorMsg = '密码长度至少6位';
    }
    if(errorMsg.length > 0) {
    	this.$errorMsg.html(errorMsg).removeClass('hide');
    	return;
    }
    this.$loginBtn.html('登录中...').attr('disabled', 'disabled');
    this.doLogin(username, password);
  },
  doLogin: function(username, password) {
	  var that = this;
	  $.ajax({
			url: '/login',
			type: 'POST',
			data: {
				username: username,
				password: MD5(password)
			},
			success: function(result) {
				if(result.code == 0) {
					var waiter = result.data;
					setCookie('uid', waiter.accid);
					setCookie('sdktoken', waiter.token);
					setCookie('waiterId', waiter.waiterId);
					updateWaiterStatus(waiter.waiterId, 1);
					window.location.href = './main.html';
					// 加载敏感词到本地localStorage
					loadSensitiveWordToCache();
				} else {
					that.$errorMsg.html(result.msg).removeClass('hide');
					that.$loginBtn.html('登录').removeAttr('disabled');
				}
			},
			error: function(xhr, options, error) {
				that.$errorMsg.html('登录异常').removeClass('hide');
			}
		});
  },

  /**
   * 获取浏览器的名称和版本号信息
   */
  getBrowser: function() {
    var browser = {
        msie: false,
        firefox: false,
        opera: false,
        safari: false,
        chrome: false,
        netscape: false,
        appname: 'unknown',
        version: 0
      },
      ua = window.navigator.userAgent.toLowerCase();
    if (/(msie|firefox|opera|chrome|netscape)\D+(\d[\d.]*)/.test(ua)) {
      browser[RegExp.$1] = true;
      browser.appname = RegExp.$1;
      browser.version = RegExp.$2;
    } else if (/version\D+(\d[\d.]*).*safari/.test(ua)) {
      // safari
      browser.safari = true;
      browser.appname = 'safari';
      browser.version = RegExp.$2;
    }
    return browser.appname + ' ' + browser.version;
  }
};
Login.init();
