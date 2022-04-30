/**
 * 叮当在线客服 API
 * 所有异步请求的地址
 */

layui.define(['laytpl', 'laypage', 'layer'],function(exports) {
	var $ = layui.jquery;
	var laypage = layui.laypage;
	var layer = layui.layer;
	var laytpl = layui.laytpl;

    var hostAndPort = document.location.host;
    var hostAddress = hostAndPort.substr(0,hostAndPort.length - 4);

    var BASE_PREFIX="http://"+ hostAddress + "8080/";
	
	// var BASE_PREFIX = "http://localhost:8080/";
	// var BASE_PREFIX = "http://localhost:8080/im-kefu";
	// var BASE_PREFIX = "http://localhost:8080/admin";

	var pageSize = 10;
	
	var api = {
		/**
		 * 检查是否登录的地址
		 */
		CHECK_LOGIN_URL:BASE_PREFIX + "/checkLogin",
		/**
		 * 登录地址
		 */
		LOGIN_URL:BASE_PREFIX + "/login",
		/**
		 * 退出地址
		 */
		LOGOUT_URL:BASE_PREFIX + "/logout",

		WAITER_SHOW_URL:BASE_PREFIX + "/waiter/listPages",
		WAITER_ALL_URL:BASE_PREFIX + "/waiter/all",
		WAITER_GET_URL:BASE_PREFIX + "/waiter/get",
		WAITER_ADD_URL:BASE_PREFIX + "/waiter/add",
		WAITER_BIND_TECHNICAL_GROUP_URL:BASE_PREFIX + "/waiter/bindTechnicalGroup",
		WAITER_EDIT_URL:BASE_PREFIX + "/waiter/edit",
		WAITER_DEL_URL:BASE_PREFIX + "/waiter/del",
		
        ALL_TECHNICAL_GROUP_TYPE_URL:BASE_PREFIX + "/technical_group/allTypes",
		TECHNICAL_GROUP_SHOW_URL:BASE_PREFIX + "/technical_group/list",
		TECHNICAL_GROUP_ADD_URL:BASE_PREFIX + "/technical_group/add",
		TECHNICAL_GROUP_GET_URL:BASE_PREFIX + "/technical_group/get",
		TECHNICAL_GROUP_EDIT_URL:BASE_PREFIX + "/technical_group/edit",
		TECHNICAL_GROUP_DEL_URL:BASE_PREFIX + "/technical_group/delele",

		PROMPT_SHOW_URL:BASE_PREFIX + "/prompt/list",
		PROMPT_ADD_URL:BASE_PREFIX + "/prompt/add",
		PROMPT_GET_URL:BASE_PREFIX + "/prompt/get",
		PROMPT_EDIT_URL:BASE_PREFIX + "/prompt/edit",
		PROMPT_DEL_URL:BASE_PREFIX + "/prompt/delete",

		BLACK_SHOW_URL:BASE_PREFIX + "/black/1.0.0/list",
		BLACK_ADD_URL:BASE_PREFIX + "/black/1.0.0/add",
		BLACK_ADD_BY_USER_ID_URL:BASE_PREFIX + "/black/1.0.0/addByUserId",
		BLACK_GET_URL:BASE_PREFIX + "/black/1.0.0/get",
		BLACK_EDIT_URL:BASE_PREFIX + "/black/1.0.0/edit",
		BLACK_DEL_URL:BASE_PREFIX + "/black/1.0.0/delete",

		USER_SHOW_URL:BASE_PREFIX + "/user/1.0.0/list",
		USER_ADD_URL:BASE_PREFIX + "/user/1.0.0/add",
		USER_GET_URL:BASE_PREFIX + "/user/1.0.0/get",
		USER_EDIT_URL:BASE_PREFIX + "/user/1.0.0/edit",
		USER_DEL_URL:BASE_PREFIX + "/user/1.0.0/delete",

		SENSITIVE_WORD_SHOW_URL:BASE_PREFIX + "/sensitiveWord/1.0.0/list",
		SENSITIVE_WORD_ADD_URL:BASE_PREFIX + "/sensitiveWord/1.0.0/add",
		SENSITIVE_WORD_GET_URL:BASE_PREFIX + "/sensitiveWord/1.0.0/get",
		SENSITIVE_WORD_EDIT_URL:BASE_PREFIX + "/sensitiveWord/1.0.0/edit",
		SENSITIVE_WORD_DEL_URL:BASE_PREFIX + "/sensitiveWord/1.0.0/delete",

		KNOWLEDGE_BASE_SHOW_URL:BASE_PREFIX + "/knowledgeBase/1.0.0/list",
		KNOWLEDGE_BASE_ADD_URL:BASE_PREFIX + "/knowledgeBase/1.0.0/add",
		KNOWLEDGE_BASE_GET_URL:BASE_PREFIX + "/knowledgeBase/1.0.0/get",
		KNOWLEDGE_BASE_EDIT_URL:BASE_PREFIX + "/knowledgeBase/1.0.0/edit",
		KNOWLEDGE_BASE_DEL_URL:BASE_PREFIX + "/knowledgeBase/1.0.0/delete",

		PASSWORD_URL:BASE_PREFIX + "/password",

        ORDER_GET_URL:BASE_PREFIX + "/order/getOrder",

	}

	var action = {
		//Ajax
		ajax: function(url, data, success, options,async) {
            data = JSON.stringify(data);
			var that = this;
			options = options || {};
			data = data || {};
			return $.ajax({
				type: options.type || 'post',
                contentType : 'application/json',
				// dataType: options.dataType || 'json',
				dataType: 'json',
				data: data,
				url: url,
				async:async || true,
				success: success || function(){
					layer.msg("success回调函数不能为空");
				},
				error: function(e) {
					options.error || layer.msg('请求异常，请重试', {
						shift: 6
					});
				}
			});
		},
		/**
		 * 异步Ajax
		 * @param {Object} url
		 * @param {Object} data
		 * @param {Object} success
		 * @param {Object} options
		 */
		doAjax: function(url, data, success, options) {
			action.ajax(url,data,success,options,true);
		},

		/**
		 * 同步Ajax
		 * @param {Object} url
		 * @param {Object} data
		 * @param {Object} success
		 * @param {Object} options
		 */
		doSyncAjax:function(url,data,success,options){
			action.ajax(url,data,success,options,false);
		},

		checkLogin:function(success,error) {
			var options = {
				error:error
			};
			// action.doSyncAjax(api.CHECK_LOGIN_URL,{},success,options);
		},

		login:function(params,success){
			action.doAjax(api.LOGIN_URL,params,success);
		},
		logout:function(params,success){
			action.doAjax(api.LOGOUT_URL,params,success);
		},

		showWaiter:function(params,success){
			action.doAjax(api.WAITER_SHOW_URL,params,success);
		},
		getWaiter:function(params,success){
			action.doAjax(api.WAITER_GET_URL,params,success);
		},
		allWaiter:function(params,success){
			action.doAjax(api.WAITER_ALL_URL,params,success);
		},
		addWaiter:function(params,success){
			action.doAjax(api.WAITER_ADD_URL,params,success);
		},
		waiterBindTechnicalGroup:function(params,success){
			action.doAjax(api.WAITER_BIND_TECHNICAL_GROUP_URL,params,success);
		},
		editWaiter:function(params,success){
			action.doAjax(api.WAITER_EDIT_URL,params,success);
		},
		delWaiter:function(params,success){
			action.doAjax(api.WAITER_DEL_URL,params,success);
		},

        allTechnicalGroupType:function(params,success){
			action.doAjax(api.ALL_TECHNICAL_GROUP_TYPE_URL,params,success);
		},

		showNotice:function(params,success){
			action.doAjax(api.NOTICE_SHOW_URL,params,success);
		},

		showTechnicalGroup:function(params,success){
			action.doAjax(api.TECHNICAL_GROUP_SHOW_URL,params,success);
		},
        getTechnicalGroup:function(params,success){
			action.doAjax(api.TECHNICAL_GROUP_GET_URL,params,success);
		},

		addTechnicalGroup:function(params,success){
			action.doAjax(api.TECHNICAL_GROUP_ADD_URL,params,success);
		},
		editTechnicalGroup:function(params,success){
			action.doAjax(api.TECHNICAL_GROUP_EDIT_URL,params,success);
		},
		delTechnicalGroup:function(params,success){
			action.doAjax(api.TECHNICAL_GROUP_DEL_URL,params,success);
		},
		password:function(params,success){
			action.doAjax(api.PASSWORD_URL,params,success);
		},
        getOrder:function(params,success){
            action.doAjax(api.ORDER_GET_URL,params,success);
        },
        showPrompt:function(params,success){
			action.doAjax(api.PROMPT_SHOW_URL,params,success);
		},
        getPrompt:function(params,success){
            action.doAjax(api.PROMPT_GET_URL,params,success);
        },
        editPrompt:function(params,success){
            action.doAjax(api.PROMPT_EDIT_URL,params,success);
        },
        addPrompt:function(params,success){
            action.doAjax(api.PROMPT_ADD_URL,params,success);
        },
        delPrompt:function(params,success){
            action.doAjax(api.PROMPT_DEL_URL,params,success);
        },
        queryAllTechGroups:function(params,success){
            action.doAjax(api.ALL_TECHNICAL_GROUP_TYPE_URL,params,success);
        },
		showBlacklist:function(params,success){
			action.doAjax(api.BLACK_SHOW_URL,params,success);
		},
		addToBlackByUserId:function(params,success){
			action.doAjax(api.BLACK_ADD_BY_USER_ID_URL,params,success);
		},
		delBlack:function(params,success){
			action.doAjax(api.BLACK_DEL_URL,params,success);
		},
		showUser:function(params,success){
			action.doAjax(api.USER_SHOW_URL,params,success);
		},
		delUser:function(params,success){
			action.doAjax(api.USER_DEL_URL,params,success);
		},
		showSensitiveWord:function(params,success){
			action.doAjax(api.SENSITIVE_WORD_SHOW_URL,params,success);
		},
		getSensitiveWord:function(params,success){
			action.doAjax(api.SENSITIVE_WORD_GET_URL,params,success);
		},
		editSensitiveWord:function(params,success){
			action.doAjax(api.SENSITIVE_WORD_EDIT_URL,params,success);
		},
		addSensitiveWord:function(params,success){
			action.doAjax(api.SENSITIVE_WORD_ADD_URL,params,success);
		},
		delSensitiveWord:function(params,success){
			action.doAjax(api.SENSITIVE_WORD_DEL_URL,params,success);
		},
		showKnowledgeBase:function(params,success){
			action.doAjax(api.KNOWLEDGE_BASE_SHOW_URL,params,success);
		},
		getKnowledgeBase:function(params,success){
			action.doAjax(api.KNOWLEDGE_BASE_GET_URL,params,success);
		},
		editKnowledgeBase:function(params,success){
			action.doAjax(api.KNOWLEDGE_BASE_EDIT_URL,params,success);
		},
		addKnowledgeBase:function(params,success){
			action.doAjax(api.KNOWLEDGE_BASE_ADD_URL,params,success);
		},
		delKnowledgeBase:function(params,success){
			action.doAjax(api.KNOWLEDGE_BASE_DEL_URL,params,success);
		},

	}
	exports('api', action);
});