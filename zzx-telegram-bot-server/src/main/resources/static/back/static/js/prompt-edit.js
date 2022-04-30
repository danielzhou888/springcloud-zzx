





layui.define(['common', 'api','form','layer','laytpl','upload'], function(exports){
	var common = layui.common;
	var api = layui.api;
	var layer = layui.layer;
	var laytpl = layui.laytpl;
	
	var form = layui.form();
	
	var publishEditor = null;
	
	var action = {
		
		init:function(){
			var id = common.getUrlParam("id");
			api.getPrompt({id:id},function(res){
				if(res.code == 0){
					laytpl($("#template").html()).render(res.data,function(html){
						$("#content-cnt").html(html);
						// action.editor(res.data.content);
						action.event();
					});
				} else {
					common.errorTip(res);
				}
			});
		},
		event:function(){
			form.render();
			
			//监听提交
			form.on('submit(promptEdit)', function(data) {
				var params = data.field;
				
				// params['prompt.html'] = html;

				api.editPrompt(params, function(res) {
					switch(res.code) {
						case 0:
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
							layer.msg("已存在", {shift: 6});
							break;
						default:
							layer.msg("保存失败", {shift: 6});
							break;
					}
				});
				return false;
			});
		}
	}
	
	action.init();
	
	exports('prompt-edit',{});
});