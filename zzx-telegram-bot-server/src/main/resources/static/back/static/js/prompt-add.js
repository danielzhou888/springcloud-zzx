layui.define(['common', 'api','form','layer','laytpl'], function(exports){
	var common = layui.common;
	var api = layui.api;
	var layer = layui.layer;
	var laytpl = layui.laytpl;
	var form = layui.form();
	
	//监听提交
	form.on('submit(report)', function(data) {
		var params = data.field;
		api.addPrompt(params, function(res) {
			console.log(res);
			switch(res.code) {
				case 0:
					//window.open(res.data);
					//询问框
					layer.confirm('保存成功，立即查看？', {
					  btn: ['是','否'] //按钮
					}, function(){
					  // window.open(res.data);
					  location.href='prompt.html';
					}, function(){
					  location.href='prompt.html';
					});
					break;
				case 1002:
					layer.msg("失败", {shift: 6});
					break;
				case 1003:
					layer.msg(res.msg, {shift: 6});
					break;
				default:
					layer.msg("保存失败", {shift: 6});
					break;
			}
		});
		return false;
	});

    var action = {

        init: function () {
            var techGroupId = common.getUrlParam("techGroupId");
            $("#techGroupId").attr("value", techGroupId);
        },
    };
    action.init();
	exports('prompt-add',{});
});