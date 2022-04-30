


layui.define(['common','api','form','layer','laytpl'],function(exports){
	var common = layui.common;
	var api = layui.api;
	var laytpl = layui.laytpl;
	var $ = layui.jquery;
	var form = layui.form();
	var layer = layui.layer;
	var laytpl = layui.laytpl;
    var laydate = layui.laydate;

	//监听提交
  	form.on('submit(formDemo)', function(data){
  		let params = data.field;
  		params["techGroupName"] = $("#techGroupSelector").find("option:selected").text();
  		params["techGroupId"] = $("#techGroupSelector").find("option:selected").val();
		api.editKnowledgeBase(params,function(res){
			if(res.code == 0){
				layer.alert(res.msg, {
				  icon: 1,
				  time: 1000,
				  end:function(){
					location.href = 'knowledge-base.html';
				  }
			});
			} else {
				layer.msg(res.msg || res.code, {
				shift: 6
			});
			}
		});
		return false;
  	});

  	var action = {
  		getKnowledgeBase:function(){
  			var id = common.getUrlParam("id");
  			var params = {
  				id:id
  			};
  			api.getKnowledgeBase(params,function(res){
  				console.log(res);
  				if(res.code == 0){
					var techGroupId = res.data.techGroupId;
					laytpl($("#template").html()).render(res.data,function(html){
	    					$("#content-cnt").html(html);
	    					form.render();
	    				});
					// 设置技能组选中状态
					api.allTechnicalGroupType({},function(res){
						if(res.code == 0){
							laytpl($("#template-tech").html()).render(res,function(html){
								// 设置技能组选中状态
								html = $(html).val(techGroupId);
								$("#type-cnt-tech1").html(html);
								form.render();
								// $('#techGroupSelector').val(techGroupId);
							});
								// $('#techGroupSelector option[value='+techGroupId+']').prop("selected", "selected");

						} else {
							common.error(res);
						}
					});
	    			} else {
	    				layer.msg(res.msg || res.code, {
						shift: 6
						});
	    			}
  			});
  		},
        event:function(res){
			form.render();
        },
  	};

  	action.getKnowledgeBase();

	exports('knowledge-base-edit',{});
	
});