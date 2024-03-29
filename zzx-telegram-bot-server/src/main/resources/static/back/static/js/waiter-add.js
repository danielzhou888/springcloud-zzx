


layui.define(['common','api','form','layer'],function(exports){
	var common = layui.common;
	var api = layui.api;
	var laytpl = layui.laytpl;
	var $ = layui.jquery;
	var form = layui.form();
	var layer = layui.layer;
    var laydate = layui.laydate;
    var now = laydate.now();
    alert(now);
	
	
	//监听提交
  	form.on('submit(formDemo)', function(data){
  		    console.log(data.field);
    		api.addWaiter(data.field,function(res){
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
  	
  	api.allType({},function(res){
  		if(res.code == 0){
  			laytpl($("#template").html()).render(res,function(html){
				$("#type-cnt").html(html);
				form.render();
			});
  		} else {
  			common.error(res);
  		}
  	});


	exports('waiter-add',{});
	
});