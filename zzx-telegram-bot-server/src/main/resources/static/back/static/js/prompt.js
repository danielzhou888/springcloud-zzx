
layui.define(['common','api','laytpl','laypage'],function(exports){
	var common = layui.common;
	var api = layui.api;
	var laytpl = layui.laytpl;
	var $ = layui.jquery;

	var laypage = layui.laypage;
		
	var pageSize = 10;

	var action = {
		
		render:function(res){
            action.loadTechGroupSelectors();
			laytpl($("#template").html()).render(res.data,function(html){
				console.log(res.data);
				$("#prompt-container").html(html);
				// 监听事件
				$(".row-del").on("click",function(event){
					var id = $(event.target).attr("data");
					//询问框
					layer.confirm('是否确认删除？', {
					  btn: ['是','否'] //按钮
					}, function(){
					  api.delPrompt({id:id},function(res){
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
					  location.href='prompt.html';
					});
				});
				$("#queryBtn").on("click", function (event) {
					action.display();
                });
				$("#addPrompt").on("click", function (event) {
					var techGroupId = $("#techGroupId option:selected").val();
                    location.href = "prompt-add.html?techGroupId="+techGroupId;
                });
			});
		},
		callback:function(params){
			api.showPrompt(params,function(res){
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

			var techGroupId = $("#techGroupId option:selected").val();
			// 请求数据
			var params = {
				pageNumber:1,
                pageSize:10,
                techGroupId: techGroupId
			};
			api.showPrompt(params,function(res){
				if(res.code == 0){
					action.render(res);
					// 调用分页
					laypage({
						cont: 'prompt-pager',
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
		loadTechGroupSelectors:function() {
			api.queryAllTechGroups({}, function (res) {
                    laytpl($("#template_tech_group_selector").html()).render(res,function(html){
                        $("#techGroupSelectors").html(html);
                        // form.render(res);
                    });
            });
		},
	};

	action.display();
	exports('prompt',{});
	
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

