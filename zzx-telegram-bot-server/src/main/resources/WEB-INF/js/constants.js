var regExpList = {
		account: /^[A-Za-z0-9]{5,20}$/,
		nickName: /^[A-Za-z0-9\u4e00-\u9fa5]{2,12}$/,
		password: /^[a-zA-Z0-9_]{6,12}$/,
		phoneNumber: /^1\d{10}$/,
		email: /(^\s*)\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*(\s*$)/
	};