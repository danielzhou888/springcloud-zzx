/** 
 * 选择/添加成员的弹框组件
 * 用途：
 * 1. 群列表中添加朋友进群
 * 2. 多人音视频中选择群成员开启音视频
 * 
 * @param {object} option 开启弹框的属性配置
 * @param {string} option.type 打开弹窗的类型，不同类型打开不同的模板: 'list-select'(默认值) / 'list'
 * @param {num} option.limit 成员上限限制, 可以不传, 默认0
 * @param {boolean} option.isCompleteList 回调数据是否是完整的数据，即：完整就是所有选中的（包含初始传进来的selectedlist） / 还是当前手动选中的!, 可以不传, 默认false
 * @param {Array} option.list 待选择的成员列表
 * @param {string} option.selectedlist 已选择的成员列表
 * @param {object} option.env 执行环境，如果有的话，回调时需要重新绑定环境
 * @param {object} option.yx 缓存yx实例
 * @param {fn} option.cbConfirm 确认回调
 * @param {fn} option.cbCancel 取消回调
 */

YX.fn.dialog = {
		open4: function (option) {
			this.cbConfirm = option.cbConfirm || function () { };
			this.cbCancel = option.cbCancel || function () { };
			var $dialog = this.$dialog = $('#dialogTeamContainer'), that = this;
			that.yx = option.yx;
	        that.env = option.env || that;
			that.load4(option.list);
			this.isOpen = true;
			if (that.isInited) return;
			$dialog.on('click', '.j-close', function (e) {
	            e.preventDefault();
	            that.cbCancel.call(that.env);
	            that.close();
	        });
			$dialog.on('click', '.j-confirm', function (e) {
	            e.preventDefault();
	            return that.cbConfirm.call(that.env);
	        });
			that.isInited = true;
		},
		open3: function (option) {
			this.cbConfirm = option.cbConfirm || function () { };
			this.cbCancel = option.cbCancel || function () { };
			var $dialog = this.$dialog = $('#dialogTeamContainer'), that = this;
			that.yx = option.yx;
	        that.env = option.env || that;
			that.load3(option.list);
			this.isOpen = true;
			if (that.isInited) return;
			$dialog.on('click', '.j-close', function (e) {
	            e.preventDefault();
	            that.cbCancel.call(that.env);
	            that.close();
	        });
			$dialog.on('click', '.j-confirm', function (e) {
	            e.preventDefault();
	            return that.cbConfirm.call(that.env);
	        });
			that.isInited = true;
		},
	open2: function (option) {
		this.cbConfirm = option.cbConfirm || function () { };
		this.cbCancel = option.cbCancel || function () { };
		var $dialog = this.$dialog = $('#dialogTeamContainer'), that = this;
		that.yx = option.yx;
        that.env = option.env || that;
		that.load2(option.list);
		this.isOpen = true;
		if (that.isInited) return;
		$dialog.on('click', '.j-close', function (e) {
            e.preventDefault();
            that.cbCancel.call(that.env);
            that.close();
        });
		$dialog.on('click', '.j-confirm', function (e) {
            e.preventDefault();
            return that.cbConfirm.call(that.env);
        });
		that.isInited = true;
	},
    open: function (option) {
        this.limit = option.limit || 0;
        this.isCompleteList = option.isCompleteList || false;
        this.cbConfirm = option.cbConfirm || function () { };
        this.cbCancel = option.cbCancel || function () { };
        var $dialog = this.$dialog = $('#dialogTeamContainer'), that = this;
        this.type = option.type || 'list-select';
        that.yx = option.yx;
        that.env = option.env || that;
        that.selectedlist = {};
        that.load(option.list, option.selectedlist);
        that.selectedNum = 0;
        this.isOpen = true;
        // 事件绑定一次就行了
        if (that.isInited) return;

        $dialog.on('click', '.user-list li', function (e) {
            that.selectUser.call(that, e);
        });
        $dialog.on('click', '.j-close', function (e) {
            e.preventDefault();
            that.cbCancel.call(that.env);
            that.close();
        })
        $dialog.on('click', '.j-confirm', function (e) {
            e.preventDefault();
            if ($(this).hasClass('disabled')) return;
            that.close();
            if (!that.isCompleteList) {
                return that.cbConfirm.call(that.env, that.selectedlist);
            }
            var tmpList = {};
            $dialog.find('#addUserList li.selected').each(function (index, item) {
                item = $(item).data('account');
                tmpList[item] = item;
            });
            return that.cbConfirm.call(that.env, tmpList);
        });

        that.isInited = true;

    },
    load4: function (list) {
    	var that = this;
        var dialog = that.$dialog;
        dialog.load('./transferWaiter.html', function () {
        	dialog.removeClass('hide');
        	var rp = '<div style="padding:5px;">药店：<select class="pharmacy_id" id="product_list_pharmacy_id">';
        	rp += '<option value="0" selected="selected">请选择</option>';
        	if(null != list && list.length > 0) {
        		for (var i = 0, len = list.length; i < len; ++i) {
                    var pharmacy = list[i];
                    if(pharmacy.pharmacyType == 1 && pharmacy.pharmacyModel == 1) {
                    	rp += '<option value="' + pharmacy.id + '">' + pharmacy.name + '</option>';
                    }
        		}
        	}
        	rp += '</select>药品名称：<input type="text" class="product_name" id="product_list_name"/>';
        	rp += '<button class="btn btn-ok radius4px" onclick="queryProductList()">查询</button></div>';
        	rp += '<div id="product_list_div" style="height:420px;overflow-y:auto;overflow-x:hidden;">' + showProductList(true, 0, '', 1, 10, null) + '</div>';
        	$('#transfer_waiter_div').html(rp);
        	$('#waiterConfirm').text('推荐');
        	$('#waiterConfirmTitle').text('推荐商品列表');
		});
    },
    load3: function (list) {
    	var that = this;
        var dialog = that.$dialog;
        dialog.load('./transferWaiter.html', function () {
        	dialog.removeClass('hide');
        	if(null != list && list.length > 0) {
        		var tw = '<div style="padding:0px 15px 15px;"><table>';
        		for (var i = 0, l = list.length; i < l; ++i) {
                    var waiter = list[i];
                    tw += '<tr><td style="padding-top:15px;">';
                    tw += '<input name="r_take_user_id" type="radio" value="' + waiter.userId + '"/>';
                    tw += '</td><td style="padding-top:15px;padding-left:10px;">';
                    tw += waiter.teamName;
                    tw += '</td></tr>';
            	}
        		tw += '</table></div>';
        		$('#transfer_waiter_div').html(tw);
        	}
        	$('#waiterConfirm').text('接待');
        	$('#waiterConfirmTitle').text('排队中的用户列表');
		});
    },
    load2: function (list) {
    	var that = this;
        var dialog = that.$dialog;
        dialog.load('./transferWaiter.html', function () {
        	dialog.removeClass('hide');
        	if(null != list && list.length > 0) {
        		var tw = '<div style="padding:0px 15px 15px;"><table>';
        		var twCheck = false;
        		for (var i = 0, l = list.length; i < l; ++i) {
                    var waiter = list[i];
                    if(waiter.id == waiterId) {
                    	continue;
                    }
                    twCheck = true;
                    tw += '<tr><td style="padding-top:15px;">';
                    tw += '<input name="r_transfer_waiter" type="radio" value="' + waiter.id + '"/>';
                    tw += '</td><td style="padding-top:15px;padding-left:10px;">';
                    tw += waiter.nickname + '&nbsp;●&nbsp;' + waiter.genderTip;
                    tw += '&nbsp;●&nbsp;' + waiter.typeTip;
                    tw += '</td></tr>';
            	}
        		tw += '</table></div>';
        		if(twCheck) {
        			$('#transfer_waiter_div').html(tw);
        		}
        	}
        	$('#waiterConfirm').text('转接');
        	$('#waiterConfirmTitle').text('可接待的客服列表');
		});
    },
    /** dom渲染流程 */
    load: function (list, selectedlist) {
        var that = this;
        var dialog = that.$dialog;
        var fname = that.fname = that.type === 'list-select' ? 'teamMember' : 'speakBan';

        dialog.load('./' + fname + '.html', function () {
            if ($("#devices")) {
                $("#devices").addClass('hide')
            }
            dialog.removeClass('hide')
            that.yx.$mask.removeClass('hide')
            var $addIcon = $('#userList .first'),
                $addUserUl = $('#addUserList ul'),
                tmpHtml = '',
                members = []

            // 所有成员
            for (var i = 0, l = list.length; i < l; ++i) {

                var tmp = list[i];

                /** 奇怪的数据略过 */
                if (tmp.constructor !== Object) continue;
                /** 自己略过 */
                if (tmp.account === that.yx.accid) continue;

                var tmpUser = that.yx.cache.getUserById(tmp.account);
                tmp.avatar = tmpUser.avatar;
                tmp.name = tmp.nickInTeam;
                // tmp.nick = getNick(tmp.account);
                // tmp.nick = tmp.nick === tmp.account ? "" : tmp.nick;
                tmpHtml += appUI['build' + fname + 'UI'](tmp);
            }
            $addUserUl.html(tmpHtml);

            /** 禁言UI，放开按钮 */
            if (fname === 'speakBan') {
                dialog.find('.j-confirm').removeClass('disabled')
            }

            if (selectedlist) {
                that.loadSelected(selectedlist);
            }
        });
    },
    /**
     *  已选中的进行加载
     * 
     * @param {any} members object或者array都可以
     */
    loadSelected: function (members) {
        var fname = this.fname;

        if (!members) return;

        /** 对象转数组 */
        if (members.constructor === Object) {
            var arr = [];
            for (var i in members) {
                arr.push({
                    account: i
                });
            }
            members = arr;
        }

        /** 如果禁言，UI处理不一样 */
        if (fname === 'speakBan' && members.length > 0) {
            var $dialog = this.$dialog;
            for (var i = 0; i < members.length; i++) {
                var account = members[i].account
                $dialog.find('[data-account="' + account + '"]').addClass('selected')
                $dialog.find('[data-account="' + account + '"] i').addClass('cur')
            }
            // enable和disable按钮
            return;
        }

        /** 已选的ui */
        $('#addedUserNum').text(this.limit ? 0 + "/8" : 0)

        var $addUserUl = $('#addUserList ul');
        for (var i = 0; i < members.length; i++) {
            var account = members[i].account
            $addUserUl.find('[data-account="' + account + '"] i').addClass('cur2')
        }
    },
    /** 成员选择事件 */
    selectUser: function (e) {
        var that = this;
        // that.selectedNum = that.selectedNum || 0;
        var $this = $(e.target).closest('li'),
            $checkIcon = $this.find('i'),
            $addedUserNum = $('#addedUserNum'),
            $addedUserListUl = $('#addedUserList ul'),
            account = $this.data('account'),
            name = $this.data('nick'),
            icon = $this.data('icon'),
            addedNum = that.selectedNum
        // 不能被选择的人不响应事件
        if (!$checkIcon.hasClass('cur2')) {

            var str = '<li data-account="' + account + '" data-account="' + name + '" data-icon="' + icon + '"><img src="' + getAvatar(icon) + '" width="56" height="56"/><p class="name">' + name + '</p></li>'
            if (!$checkIcon.hasClass('cur')) {

                // 人数上限
                if (that.limit && addedNum >= that.limit) {
                    that.yx.myNetcall.showTip('人数已达上限', 2000);
                    return;
                }

                that.selectedlist[account] = name
                $addedUserListUl.append(str)
                addedNum++
            } else {
                delete that.selectedlist[account]
                $addedUserListUl.find('[data-account=' + account + ']').remove()
                addedNum--
            }

            $checkIcon.toggleClass('cur')
            $this.toggleClass('selected')
            $addedUserNum.text(that.limit ? addedNum + "/8" : addedNum)
            that.selectedNum = addedNum

            if (that.fname === 'speakBan') return;
            // enable和disable按钮
            that.$dialog.find('.j-confirm').toggleClass('disabled', addedNum <= 0)
        }

    },
    /** 关闭弹框 */
    close: function () {
        this.isOpen = false;
        this.$dialog && this.$dialog.addClass('hide')
        this.yx && this.yx.$mask && this.yx.$mask.addClass('hide')
    }
}

function queryProductList() {
	var pharmacyId = $('#product_list_pharmacy_id').val();
	if(pharmacyId == 0) {
		alert('请选择药店');
		return;
	}
	var productName = $('#product_list_name').val();
	doProductList(pharmacyId, productName, 1, 10);
}

function doProductList(pharmacyId, productName, pageNo, pageSize) {
	$.ajax({
		type: 'POST',
		async: false,
		url: '/queryProductList',
		dataType: 'json',
		data: {
			pharmacyId: pharmacyId,
			productName: productName,
			pageNo: pageNo,
			pageSize: pageSize
		},
		success: function(result) {
			if(result.code != 0) {
				alert('查询药店商品失败');
				return;
			}
			var p = showProductList(false, pharmacyId, productName, pageNo, pageSize, result.data);
			$('#product_list_div').html(p);
		},
		error: function(xhr, options, error) {
			alert('查询药店商品异常');
		}
	});
}

function showProductList(isInited, pharmacyId, productName, pageNo, pageSize, result) {
	var p = '<table border="1" bordercolor="#a0c6e5" ';
	p += 'style="border-collapse:collapse;word-wrap:break-word;word-break:break-all;">';
	p += '<tr><td width="25" align="center" style="padding:5px 0px 5px;"></td>';
	p += '<td width="200" align="center" style="padding:5px 0px 5px;">药品名称</td>';
	p += '<td width="100" align="center" style="padding:5px 0px 5px;">库存数量</td>';
	p += '<td width="100" align="center" style="padding:5px 0px 5px;">药品售价</td>';
	p += '<td width="150" align="center" style="padding:5px 0px 5px;">规格</td>';
	p += '<td width="100" align="center" style="padding:5px 0px 5px;">OTC类型</td></tr>';
	if(!isInited) {
		var list = result.rows;
		if(null != list && list.length > 0) {
			for (var i = 0, len = list.length; i < len; ++i) {
                var product = list[i];
                p += '<tr><td align="center" style="padding:5px 0px 5px;">';
                p += '<input name="r_recommend_goods_id" type="radio" value="' + product.goodsId + '"/></td>';
                p += '<td style="padding:5px 0px 5px;">' + product.frontName + '</td>';
                p += '<td style="padding:5px 0px 5px;">' + product.goodsQuantity + '</td>';
                p += '<td style="padding:5px 0px 5px;">' + product.goodsPrice / 100 + '</td>';
                p += '<td style="padding:5px 0px 5px;">' + product.packageSpecifications + '</td>';
                p += '<td style="padding:5px 0px 5px;">' + getOtcMark(product.otcMark) + '</td></tr>';
			}
		}
		var totalPage = Math.floor((result.total + pageSize - 1) / pageSize);
		if(totalPage > 1) {
			p += '<tr><td colspan="6" align="right" style="padding:5px 10px 5px;">';
			if(pageNo > 1 && totalPage > 1) {
				p += '<a style="color:#e4393c;cursor:pointer;padding-left:25px;" ';
				p += 'onclick="doProductList(' + pharmacyId + ',\'' + productName + '\',' + (pageNo - 1) + ',' + pageSize + ')">上一页</a>';
			}
			if(pageNo < totalPage) {
				p += '<a style="color:#e4393c;cursor:pointer;padding-left:25px;" ';
				p += 'onclick="doProductList(' + pharmacyId + ',\'' + productName + '\',' + (pageNo + 1) + ',' + pageSize + ')">下一页</a>';
			}
			p += '<span style="color:#e4393c;padding-left:25px;">' + pageNo + '/' + totalPage + '</span>';
			p += '</td></tr>';
		}
	}
	p += '</table>';
	return p;
}

function getOtcMark(value) {
	if(value == 0) {
		return '单轨';
	} else if(value == 1) {
		return 'OTC甲类';
	} else if(value == 2) {
		return 'OTC乙类';
	} else if(value == 3) {
		return '双轨';
	} else if(value == 99) {
		return '其它';
	}
	return '';
}