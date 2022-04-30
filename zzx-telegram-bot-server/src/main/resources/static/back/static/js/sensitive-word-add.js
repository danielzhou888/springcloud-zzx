





layui.define(['common', 'api','form','layer','laytpl'], function(exports){
	var common = layui.common;
	var api = layui.api;
	var layer = layui.layer;
	var laytpl = layui.laytpl;
	var form = layui.form();
	
	//监听提交
	form.on('submit(formDemo)', function(data) {
		var params = data.field;

		api.addSensitiveWord(params, function(res) {
			console.log(res);
			switch(res.code) {
				case 0:
					layer.alert(res.msg, {
						icon: 1,
						time: 1000,
						end:function(){
							location.href = 'sensitive-word.html';
						}
					});
					break;
				case 1002:
					layer.msg("失败", {shift: 6});
					break;
				case 1003:
					layer.msg("已存在", {shift: 6});
					break;
				default:
					layer.msg("保存失败", {shift: 6});
					break;
			}
		});
		return false;
	});
	
	exports('sensitive-word-add',{});
});