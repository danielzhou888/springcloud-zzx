


layui.define(['common','api','form','layer'],function(exports){
	var common = layui.common;
	var api = layui.api;
	var laytpl = layui.laytpl;
	var $ = layui.jquery;
	var form = layui.form();
	var layer = layui.layer;

	//监听提交
  	form.on('submit(formDemo)', function(data){
			var id = common.getUrlParam("id");
			var params = data.field;
			params['id'] = id;
    		api.waiterBindTechnicalGroup(params,function(res){
    			if(res.code == 0){
    				layer.alert(res.msg, {
			          icon: 1,
			          time: 1000,
			          end:function(){
			          	location.href = 'waiter.html';
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


    api.allTechnicalGroupType({},function(res){
        if(res.code == 0){
            laytpl($("#template").html()).render(res,function(html){
				let technicalGroupId = common.getUrlParam("technicalGroupId");
				// 设置技能组选中状态
				html = $(html).val(technicalGroupId);
                $("#type-cnt").html(html);
                form.render();
            });
        } else {
            common.error(res);
        }


    });
	exports('waiter-add-technical-group',{});
	
});