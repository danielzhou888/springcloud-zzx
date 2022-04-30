


layui.define(['common','api','laytpl','laypage'],function(exports){
	var common = layui.common;
	var api = layui.api;
	var laytpl = layui.laytpl;
	var $ = layui.jquery;
	
	var laypage = layui.laypage;
		
	var pageSize = 10;
	
	var action = {
		
		render:function(res){
			laytpl($("#template").html()).render(res.data,function(html){
				console.log(res.data);
				$("#technical-group-container").html(html);
				// 监听事件
				$(".row-del").on("click",function(event){
					var id = $(event.target).attr("data");
					//询问框
					layer.confirm('是否确认删除？', {
					  btn: ['是','否'] //按钮
					}, function(){
					  api.delTechnicalGroup({id:id},function(res){
					  	if(res.code == 0){
							layer.msg("删除成功");
							location.reload();
						} else {
							layer.msg(res.msg || res.code, {
								shift: 6
							});
						}
					  });
					}, function(){
					  location.href='technical-group.html';
					});
				});
			});
		},
		callback:function(params){
			api.showTechnicalGroup(params,function(res){
				console.log(res);
				if(res.code == 0){
					action.render(res);
				} else {
					layer.msg(res.msg || res.code, {
						shift: 6
					});
				}
			});
		},
		
		display:function(){
			// 请求数据
			var params = {
				pageNum:1,
				pageSize:10
			};
			api.showTechnicalGroup(params,function(res){
				if(res.code == 0){
					action.render(res);
					// 调用分页
					laypage({
						cont: 'technical-group-pager',
						pages: res.data.totalPage, //得到总页数
						jump: function(conf,first) {
							if(first){
								return;
							}
							action.callback({
								pageNum:conf.curr,
								pageSize:pageSize || 10
							});
						}
					});
				} else {
					layer.msg(res.msg || res.code, {
						shift: 6
					});
				}
			});
		},
	};
	
	action.display();

	exports('technical-group',{});
	
});

function formatDateTime(inputTime) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
};