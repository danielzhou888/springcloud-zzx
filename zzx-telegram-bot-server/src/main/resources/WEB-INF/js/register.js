var register = {
	init: function() {
		this.initNode();
		this.addEvent();
	},
	initNode: function() {
		//初始化节点
		this.$waiterUsername = $('#waiter_username');
		this.$waiterNickname = $('#waiter_nickname');
		this.$waiterPassword = $('#waiter_password');
		this.$errorMsg = $('#errorMsg');
		this.$submit = $('#submit');
	},
	addEvent: function() {
		//绑定事件
		var that = this;
		this.$submit.on('click', this.validate.bind(this));
		$(document).on('keydown', function(e) {
			var ev = e || window.event;
			if (ev.keyCode === 13) {
				that.validate();
			}
		});
	},
	validate: function() {
		this.$errorMsg.addClass('hide');
		var waiterUsername = $.trim(this.$waiterUsername.val()),
	  	  	waiterPassword = this.$waiterPassword.val(),
	  	  	waiterNickname = $.trim(this.$waiterNickname.val()),
	  	  	errorMsg = '';
		if (waiterUsername.length == 0) {
			errorMsg = '帐号不能为空';
		} else if (waiterNickname.length == 0) {
			errorMsg = '昵称不能为空';
		} else if (waiterPassword.length == 0) {
			errorMsg = '密码不能为空';
		} else if(!regExpList.account.test(waiterUsername)) {
			errorMsg = '帐号格式不正确';
		} else if(!regExpList.password.test(waiterPassword)) {
			errorMsg = '密码格式不正确';
		} else if(!regExpList.nickName.test(waiterNickname)) {
			errorMsg = '昵称格式不正确';
		}
		if(errorMsg.length > 0) {
			this.$errorMsg.html(errorMsg).removeClass('hide');
			return;
		}
		this.$submit.html('注册中...').attr('disabled', 'disabled');
		var waiterGender = $('input:radio[name="waiter_gender"]:checked').val();
		var waiterType = $('input:radio[name="waiter_type"]:checked').val();
		this.doRegister(waiterUsername, waiterPassword, waiterNickname, waiterGender, waiterType);
	},
	doRegister: function(waiterUsername, waiterPassword, waiterNickname, waiterGender, waiterType) {
		var that = this;
		var params = {
				username: waiterUsername,
				password: waiterPassword,
				nickname: waiterNickname,
				gender: waiterGender,
				type: waiterType
		};
		$.ajax({
			url: '/register',
			type: 'POST',
			data: params,
			success: function(result) {
				if(result.code == 0) {
					alert('注册成功');
					window.location.href = './login.html';
				} else {
					that.$errorMsg.html(result.msg).removeClass('hide');
					that.$submit.html('注册').removeAttr('disabled');
				}
			},
			error: function(xhr, options, error) {
				that.$errorMsg.html('注册异常').removeClass('hide');
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
			browser.safari = true;
			browser.appname = 'safari';
			browser.version = RegExp.$2;
		}
		return browser.appname + ' ' + browser.version;
	}
};

register.init();