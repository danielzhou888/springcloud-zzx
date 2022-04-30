


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
				$("#black-container").html(html);
				// 监听事件
				$(".row-del").on("click",function(event){
					var id = $(event.target).attr("data");
					//询问框
					layer.confirm('是否确认移除黑名单？', {
					  btn: ['是','否'] //按钮
					}, function(){
					  api.delBlack({id:id},function(res){
					  	if(res.code == 0){
							layer.msg("移除成功");
							location.reload();
						} else {
							layer.msg(res.msg || res.code, {
								shift: 6
							});
						}
					  });
					}, function(){
					  location.href='black.html';
					});
				});
				$("#queryBtn").on("click", function (event) {
					action.display();
				});
			});
		},
		callback:function(params){
			api.showBlacklist(params,function(res){
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
			let mobileQuery = $("#mobileQuery").val();
			// 请求数据
			var params = {
				pageNumber:1,
				pageSize:10,
				mobile:mobileQuery
			};
			api.showBlacklist(params,function(res){
				if(res.code == 0){
					action.render(res);
					// 调用分页
					laypage({
						cont: 'list-pager',
						pages: res.data.totalPage, //得到总页数
						jump: function(conf,first) {
							if(first){
								return;
							}
							action.callback({
								pageNumber:conf.curr,
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

	exports('black',{});
	
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