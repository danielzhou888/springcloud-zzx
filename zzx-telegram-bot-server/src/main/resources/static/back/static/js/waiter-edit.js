


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
    		api.editWaiter(data.field,function(res){
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
  	
  	
  	var action = {
  		getWaiter:function(){
  			var id = common.getUrlParam("id");
  			var params = {
  				id:id
  			}
  			api.getWaiter(params,function(res){
  				console.log(res);
  				if(res.code == 0){
	    				laytpl($("#template").html()).render(res.data,function(html){
	    					$("#content-cnt").html(html);
	    					form.render();
	    				});
	    			} else {
	    				layer.msg(res.msg || res.code, {
						shift: 6
					});
	    			}
  			});
  		},
        event:function(res){
            var start = {
                min: res.data.waiterStartTime
                ,max: '2099-06-16 23:59:59'
                ,istoday: false
                ,choose: function(datas){
                    end.min = datas; //开始日选好后，重置结束日的最小日期
                    end.start = datas //将结束日的初始值设定为开始日
                }
            };

            var end = {
                min: res.data.waiterEndTime
                ,max: '2099-06-16 23:59:59'
                ,istoday: false
                ,choose: function(datas){
                    start.max = datas; //结束日选好后，重置开始日的最大日期
                }
            };

            $("#waiter-start").on("click",function(){
                start.elem = this;
                laydate(start);
            });
            $("#waiter-end").on("click",function(){
                end.elem = this
                laydate(end);
            });
        }
  	}

  	action.getWaiter();
	
	exports('waiter-edit',{});
	
});