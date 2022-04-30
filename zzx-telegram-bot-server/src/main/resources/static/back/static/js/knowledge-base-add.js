





layui.define(['common', 'api','form','layer','laytpl'], function(exports){
	var common = layui.common;
	var api = layui.api;
	var layer = layui.layer;
	var $ = layui.jquery;
	var laytpl = layui.laytpl;
	var form = layui.form();
	
	//监听提交
	form.on('submit(formDemo)', function(data) {
		var params = data.field;
		params["techGroupName"] = $('#techGroupSelector').find("option:selected").text();
		api.addKnowledgeBase(params, function(res) {
			console.log(res);
			switch(res.code) {
				case 0:
					layer.alert(res.msg, {
						icon: 1,
						time: 1000,
						end:function(){
							location.href = 'knowledge-base.html';
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

	api.allTechnicalGroupType({},function(res){
		if(res.code == 0){
			laytpl($("#template-tech").html()).render(res,function(html){
				$("#type-cnt-tech").html(html);
				form.render();
			});
		} else {
			common.error(res);
		}
	});
	
	exports('knowledge-base-add',{});
});