


layui.define(['common','api','laytpl','laypage','layer'],function(exports){
	var common = layui.common;
	var api = layui.api;
	var laytpl = layui.laytpl;
	var $ = layui.jquery;
	var layer = layui.layer;
	var laypage = layui.laypage;

	var action = {

		// render:function(res) {
		// 	table.set({
		// 		headers: request.getHeaders(),
		// 		parseData: function(res) {
		// 			return {
		// 				"code": res.code,
		// 				"msg": res.msg,
		// 				"data": res.data
		// 			};
		// 		},
		// 		response: {
		// 			statusCode: 100000
		// 		},
		// 		text: {
		// 			none: ''
		// 		}
		// 	});
		// },

		render:function(res){
			laytpl($("#template").html()).render(res.data,function(html){
				$("#knowledge-base-container").html(html);

				// 监听事件
				$(".row-del").on("click",function(event){
					var id = $(event.target).attr("data");
					//询问框
					layer.confirm('是否确认删除？', {
						btn: ['是','否'] //按钮
					}, function(){
						api.delKnowledgeBase({id:id},function(res){
							if(res.code == 0){
								layer.alert("删除成功", {
									icon: 1,
									time: 1000,
									end:function(){
										location.reload();
									}
								});
							} else {
								layer.msg(res.msg || res.code, {
									shift: 6
								});
							}
						});
					}, function(){
						//location.href='technical-group.html';
					});
				});
				$("#queryBtn").on("click", function (event) {
					action.display();
				});
			});
		},

		callback:function(params){
			api.showKnowledgeBase(params,function(res){
					console.log(res);
					if(res.code == 0){

						action.render(res);
					} else {
						layer.msg(res.msg || res.code, {
							shift: 6
						});
					}
				}
			);
		},

		display:function(){
			let contentQuery = $("#contentQuery").val();
			// 请求数据
			var params = {
				pageNumber:1,
				pageSize:10,
				contentLike: contentQuery
			};
			api.showKnowledgeBase(params,function(res){
				console.log(res);
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
								pageSize:10
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
	exports('knowledge-base',{});

});