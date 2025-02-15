/*
 * @Author: 消息逻辑
 */

'use strict';

YX.fn.message = function() {
  this.$sendBtn = $('#sendBtn');
  this.$endChatBtn = $('#endChatBtn');
  this.$messageText = $('#messageText');
  // var t = this.cache;
  // this.$messageText.keyup(function(t) {
  //   // 检索知识库
  //   let text = $('#messageText').val().trim();
  //   // var map = this.cache.filterSensitiveWord(text);
  //   // console.log(JSON.stringify(map));
  //   if (text == '') {
  //     return;
  //   }
  //   var knowledgeBase = t.cache.getKnowledgeBase();
  //   getList(knowledgeBase);
  // });
  this.$chooseFileBtn = $('#chooseFileBtn');
  this.$fileInput = $('#uploadFile');
  this.$toRecord = $('#toRecord');
  this.$recordTimeBox = $('#toRecordBox')
  this.$recordTimeDuration = $('#toRecordTime')
  this.$cancelRecord = $('#toRecordCancle')
  //this.$showNetcallAudioLink = $('#showNetcallAudioLink')
  //this.$showNetcallVideoLink = $('#showNetcallVideoLink')
  this.$showWhiteboard = $('#showWhiteboard')
  try {
    this.audioContext = new Recorder.AudioContext;
  } catch (e) {
    console.log(e);
  }
  YX.fn.recorder = null;
  YX.fn.recordTimeout = '';
  YX.fn.recordTime = 0;
  this.$sendBtn.on('click', this.sendTextMessage.bind(this));
  this.$endChatBtn.on('click', this.endChat.bind(this));
  this.$messageText.on('keydown', this.inputMessage.bind(this));
  this.$messageText.on('keyup', this.searchKnowledgeBase.bind(this));
  this.$chooseFileBtn.on('click', 'a', this.chooseFile.bind(this));
  this.$fileInput.on('change', this.uploadFile.bind(this));
  this.$toRecord.on('click', this.recordAudio.bind(this));
  this.$cancelRecord.on('click', this.cancelRecordAudio.bind(this));
  //this.$showNetcallAudioLink.on('click', this.stopRecordAndAudio.bind(this));
  //this.$showNetcallVideoLink.on('click', this.stopRecordAndAudio.bind(this));
  this.$showWhiteboard.on('click', this.stopRecordAndAudio.bind(this));
  //消息重发
  this.$chatContent.delegate('.j-resend', 'click', this.doResend.bind(this));
  //语音播发
  this.$chatContent.delegate('.j-mbox', 'click', this.playAudio);

  //聊天面板右键菜单
  $.contextMenu({
    selector: '.j-msg',
    callback: function(key, options) {
      if (key === 'delete') {
        var id = options.$trigger.parent().data('id');
        var msg = this.cache.findMsg(this.crtSession, id);
        if (!msg || options.$trigger.hasClass('j-msg')) {}
        if (msg.flow !== 'out' && msg.scene === 'p2p') {
          alert('点对点场景，只能撤回自己发的消息');
          return;
        }
        if (
          !this.cache.isCurSessionTeamManager &&
          msg.flow !== 'out' &&
          msg.scene === 'team'
        ) {
          alert('群会话场景，非管理员不能撤回别人发的消息');
          return;
        }
        options.$trigger.removeClass('j-msg');
        this.nim.deleteMsg({
          msg: msg,
          done: function(err) {
            options.$trigger.addClass('j-msg');
            if (err) {
              if (err.code === 508) {
                alert('发送时间超过2分钟的消息，不能被撤回');
              } else {
                alert(err.message || '操作失败');
              }
            } else {
              msg.opeAccount = userUID;
              this.backoutMsg(id, {
                msg: msg
              });
            }
          }.bind(this)
        });
      }
    }.bind(this),
    items: {
      delete: {
        name: '撤回',
        icon: 'delete'
      }
    }
  });

  //表情贴图模块
  this.initEmoji();

};

/**
 * 处理收到的消息
 * @param  {Object} msg
 * @return
 */
YX.fn.doMsg = function(msg) {
  var that = this,
    who = msg.to === userUID ? msg.from : msg.to,
    updateContentUI = function() {
      //如果当前消息对象的会话面板打开
      if (that.crtSessionAccount === who) {
        that.sendMsgRead(who, msg.scene);
        that.cache.dealTeamMsgReceipts(msg, function() {
          var msgHtml = appUI.updateChatContentUI(msg, that.cache);
          that.$chatContent.find('.no-msg').remove();
          that.$chatContent.append(msgHtml).scrollTop(99999);
        })
      }
    };
  //非群通知消息处理
  if (/text|image|file|audio|video|geo|custom|tip|robot/i.test(msg.type)) {
    this.cache.addMsgs(msg);
    var account = msg.scene === 'p2p' ? who : msg.from;
    //用户信息本地没有缓存，需存储
    if (!this.cache.getUserById(account)) {
      this.mysdk.getUser(account, function(err, data) {
        if (!err) {
          that.cache.updatePersonlist(data);
          updateContentUI();
        }
      });
    } else {
      this.buildSessions();
      updateContentUI();
    }
  } else {
    // 群消息处理
    this.messageHandler(msg, updateContentUI);
  }
};
/*****************************************************************
 * emoji模块
 ****************************************************************/
YX.fn.initEmoji = function() {
  this.$showEmoji = $('#showEmoji');
  this.$showEmoji.on('click', this.showEmoji.bind(this));
  var that = this,
    emojiConfig = {
      emojiList: emojiList, //普通表情
      pinupList: pinupList, //贴图
      width: 500,
      height: 300,
      imgpath: './images/',
      callback: function(result) {
        that.cbShowEmoji(result);
      }
    };
  this.$emNode = new CEmojiEngine($('#emojiTag')[0], emojiConfig);
  this.$emNode._$hide();
};
/**
 * 选择表情回调
 * @param  {objcet} result 点击表情/贴图返回的数据
 */
YX.fn.cbShowEmoji = function(result) {
  if (!!result) {
    var scene = this.crtSessionType,
      to = this.crtSessionAccount;
    // 贴图，发送自定义消息体
    if (result.type === 'pinup') {
      var index = Number(result.emoji) + 1;
      var content = {
        type: 3,
        data: {
          catalog: result.category,
          chartlet: result.category + '0' + (index >= 10 ? index : '0' + index)
        }
      };
      this.mysdk.sendCustomMessage(
        scene,
        to,
        content,
        this.sendMsgDone.bind(this)
      );
    } else {
      // 表情，内容直接加到输入框
      this.$messageText[0].value = this.$messageText[0].value + result.emoji;
    }
  }
};

YX.fn.showEmoji = function() {
  this.$emNode._$show();
};
/*************************************************************************
 * 发送消息逻辑
 *
 ************************************************************************/
YX.fn.uploadFile = function() {
  var that = this,
    scene = this.crtSessionType,
    to = this.crtSessionAccount,
    fileInput = this.$fileInput.get(0);
  if (fileInput.files[0].size == 0) {
    alert('不能传空文件');
    return;
  }
  this.mysdk.sendFileMessage(scene, to, fileInput, this.sendMsgDone.bind(this));
};

YX.fn.chooseFile = function() {
  this.$fileInput.click();
};

YX.fn.endChat = function() {
	var that = this;
	$.post('/endChat', {waiterId: waiterId, teamId: that.crtSessionAccount}, function (result) {
		if(result.code != 0) {
			console.log('结束会话失败');
			return;
		}
		var data = result.data;
		that.updateDistributionList(data.sessionList, data.teamId);
	});
}

YX.fn.sendTextMessage = function() {
  var self = this;
  if (self.$toRecord.hasClass('recording') || self.$toRecord.hasClass('recorded')) {
    if (YX.fn.recordTime < 2) {
      alert('语音消息最短2s')
      return
    }
    self.stopRecordAudio()
    self.sendRecordAudio()
    return
  }
  var scene = this.crtSessionType,
    to = this.crtSessionAccount,
    text = this.$messageText.val().trim();
  if (!!to && !!text) {
    if (text.length > 500) {
      alert('消息长度最大为500字符');
    } else if (text.length === 0) {
      return;
    } else if (this.cache.filterSensitiveWord(text).flag) {
      // 命中敏感词
      alert("内容包括敏感词语");
      return;
    } else {
      var options = {
        scene: scene || 'p2p',
        to: to,
        text: text,
        done: this.sendMsgDone.bind(this)
      };
      // 客户端反垃圾检查
      var ret = nim.filterClientAntispam({
        content: text
      });

      switch (ret.type) {
        case 0:
          // console.log('没有命中反垃圾词库', ret.result);
          break;
        case 1:
          // console.log('已对特殊字符做了过滤', ret.result);
          options.text = ret.result;
          break;
        case 2:
          // console.log('建议拒绝发送', ret.result);
          this.mysdk.sendTipMsg({
            scene: scene,
            to: to,
            tip: '命中敏感词，拒绝发送'
          });
          return;
        case 3:
          // console.log('建议服务器处理反垃圾，发消息带上字段clientAntiSpam';
          options.clientAntiSpam = true;
          break;
      }
      if (
        this.crtSessionType === 'team' &&
        this.crtSessionTeamType === 'advanced'
      ) {
        if ($('#needTeamMsgReceipt') && $('#needTeamMsgReceipt')[0].checked) {
          options.needMsgReceipt = true;
        }
      }
      this.nim.sendText(options);
    }
  }
};

YX.fn.sendRecordAudio = function() {
  var self = this
  YX.fn.recorder.exportWAV(function(blob) {
    self.$toRecord.addClass('uploading');
    self.nim.sendFile({
      scene: self.crtSessionType,
      to: self.crtSessionAccount,
      type: 'audio',
      blob: blob,
      uploadprogress: function(obj) {
        console.log('文件总大小: ' + obj.total + 'bytes');
        console.log('已经上传的大小: ' + obj.loaded + 'bytes');
        console.log('上传进度: ' + obj.percentage);
        console.log('上传进度文本: ' + obj.percentageText);
        if (obj.percentage === 100) {
          self.$toRecord.removeClass('uploading');
          self.$toRecord.removeClass('recorded');
        }
      },
      done: self.sendMsgDone.bind(self)
    });
  });
  self.cancelRecordAudio()
}

YX.fn.stopRecordAndAudio = function () {
  YX.fn.stopRecordAudio()
  YX.fn.stopPlayAudio()
}
YX.fn.recordAudio = function() {
  YX.fn.stopPlayAudio()
  var self = this
  if (location.protocol === 'http:') {
    alert('请使用https协议');
    return
  }
  if (!self.audioContext) {
    alert('当前浏览器不支持录音!');
    return
  }
  if (!self.$toRecord.hasClass('recording') && !self.$toRecord.hasClass('recorded')) {
    if (YX.fn.recorder) {
      YX.fn.recorder.clear();
      YX.fn.recorder.record();
      self.showRecorderTime()
    } else {
      Recorder.mediaDevices.getUserMedia({
        audio: true
      }).then(function(stream) {
        var input = self.audioContext.createMediaStreamSource(stream);
        YX.fn.recorder = new Recorder(input);
        YX.fn.recorder.record();
        if (~self.audioContext.state.indexOf('suspend')) {
          self.audioContext.resume().then(function () {
            YX.fn.recorder.record();
            self.showRecorderTime()
            console.log('audioContext suspend state resume');
          })
        } else {
          self.showRecorderTime()
        }
      }).catch(function(err) {
        alert('您没有可用的麦克风输入设备')
        self.$toRecord.addClass('disabled')
        console.log('No live audio input: ' + err, err.name + ": " + err.message);
      });
    }
  }
}
YX.fn.showRecorderTime = function () {
  var self = this
  if (YX.fn.recorder) {
    self.$recordTimeBox.show()
    self.$toRecord.addClass('recording');
    YX.fn.recordTime = 0;
    YX.fn.recordTimeout = setTimeout(self.recordTimeRun.bind(self), 1000)
  }
}
YX.fn.recordTimeRun = function () {
  var self = this
  YX.fn.recordTimeout = setTimeout(self.recordTimeRun.bind(self), 1000)
  YX.fn.recordTime++;
  if (YX.fn.recordTime >= 60) {
    clearTimeout(YX.fn.recordTimeout)
    self.stopRecordAudio()
  }
  self.$recordTimeDuration.html('00:' + (YX.fn.recordTime > 9 ? YX.fn.recordTime : '0' + YX.fn.recordTime))
}
YX.fn.stopRecordAudio = function() {
  var $toRecord = $('#toRecord')
  var isRecording = $toRecord.hasClass('recording');
  if (isRecording) {
    $toRecord.removeClass('recording');
    $toRecord.addClass('recorded');
    if (YX.fn.recorder) {
      clearTimeout(YX.fn.recordTimeout)
      YX.fn.recorder.stop();
    }
  }
}

YX.fn.cancelRecordAudio = function () {
  var $toRecord = $('#toRecord')
  var isRecording = $toRecord.hasClass('recording') || $toRecord.hasClass('recorded');
  var $recordTimeBox = $('#toRecordBox')
  var $recordTimeDuration = $('#toRecordTime')
  if (isRecording && YX.fn.recorder) {
    clearTimeout(YX.fn.recordTimeout)
    YX.fn.recorder.stop();
    YX.fn.recorder.clear();
    $recordTimeBox.hide()
    $toRecord.removeClass('recording');
    $toRecord.removeClass('recorded');
    $recordTimeDuration.html('00:00')
    YX.fn.recordTime = 0
  }
}
/**
 * 发送消息完毕后的回调
 * @param error：消息发送失败的原因
 * @param msg：消息主体，类型分为文本、文件、图片、地理位置、语音、视频、自定义消息，通知等
 */
YX.fn.sendMsgDone = function(error, msg) {
  if (error && error.code === 7101) {
    alert('被拉黑');
    msg.blacked = true;
  }
  this.cache.addMsgs(msg);
  if (msg.type === 'text') {
    this.$messageText.val('');
  }
  this.$chatContent.find('.no-msg').remove();
  this.cache.dealTeamMsgReceipts(msg, function() {
    var msgHtml = appUI.updateChatContentUI(msg, this.cache);
    this.$chatContent.append(msgHtml).scrollTop(99999);
    $('#uploadForm')
      .get(0)
      .reset();
  }.bind(this))
};

YX.fn.inputMessage = function(e) {
  var ev = e || window.event;
  if ($.trim(this.$messageText.val()).length > 0) {
    if (ev.keyCode === 13 && ev.ctrlKey) {
      this.$messageText.val(this.$messageText.val() + '\r\n');
    } else if (ev.keyCode === 13 && !ev.ctrlKey) {
      this.sendTextMessage();
    }
  }
};

// 模糊检索知识库
YX.fn.searchLikeKnowledge = function(text) {
  var knowledgeBase = this.cache.getKnowledgeBase();
  var arr = [];
  for (var i = 0; i < knowledgeBase.length; i++) {
    if (knowledgeBase[i].indexOf(text) >= 0) {
      arr.push(knowledgeBase[i]);
    }
  }
  return arr;
};

// 检索知识库
YX.fn.searchKnowledgeBase = function(e) {
    // 检索知识库
    let text = $('#messageText').val().trim();
    // var map = this.cache.filterSensitiveWord(text);
    // console.log(JSON.stringify(map));
    if (text == '') {
      return;
    }
    var knowledgeBase = this.searchLikeKnowledge(text);
    getList(knowledgeBase);

  /*----------------- 获取关键词描述 ---------------------*/
  function getList(data) {
    var $input = $('#messageText');
    // var $submit = $('#submit');
    var $div = $('#description');
    writeSpans();

    /*----------------- 对关键词进行写入---------------------*/
    function writeSpans() {
      if (data == null || data == undefined || data == '' || data.length == undefined || data.length == 0) {
        return;
      }
      let len2 = data.length;
      if (len2 == 0) {
        getStyle('hide');
      } else {
        getStyle('show');
      }
      // 写入span标签
      let spans = '';
      for (let i = 0; i < len2; i++) {
        spans += '<span class="spanClass">' + data[i] + '</span>';
        // spans += '<span style="display:block; white-space: nowrap; text-overflow: ellipsis; overflow: hidden;">' + data[i].title + '</span>';
        // spans += "<span>" + data[i].title + "</span><br style='width: 0.00000001%;height: 0.000000000000001%'></br>"
      }

      $div.html(spans);
      $div.animate({
        height: ($div.children().height() + 1) * len2 //关键词下滑效果
      }, 100);
      eventEmitter(data);
    }

    /*----------------- 对事件进行处理 ---------------------*/
    function eventEmitter(data) {
      let length = data.length;
      // let length = len;
      // 点击提交
      $div.children().click(function() {
        $input.val($(this).html());
        getStyle('hide');
        $input.focus();
        // $submit.submit();
      });

      // 点击隐藏
      // $submit.focus(function() {
      // 	getStyle('hide');
      // });
      $div.mouseleave(function() {
        getStyle('hide');
      });

      var numspan = 0; //指定选择候选词
      $input.keydown(function(event) {
        // 回车提交
        if (event.which == 13) {
          getStyle('hide');
        }
        // 下
        if (event.which == 40) {
          if (numspan == length)
            numspan = 0;
          for (var i = 0; i < length; i++) {
            if (numspan == i) {
              active(i);
            } else {
              defaut(i);
            }
          }
          $input.val($div.children().eq(numspan).html());
          numspan++;
        }
        // 上
        if (event.which == 38) {
          numspan--;
          if (numspan == length)
            numspan = 0;
          for (var i = 0; i < length; i++) {
            if (numspan == i) {
              active(i);
            } else {
              defaut(i);
            }
          }
          $input.val($div.children().eq(numspan).html())
        };

      });
      $div.children().mouseover(function() {
        numspan = $(this).index();
        // alert(numspan);
        for (var i = 0; i < length; i++) {
          if (numspan == i) {
            active(i);
          } else {
            defaut(i);
          }
        }
        $input.val($div.children().eq(numspan).html());
      });

    }

    /*----------------- 列表显隐 ---------------------*/
    function getStyle(str) {
      switch (str) {
        case 'show':
          show();
          break;
        case 'hide':
          hide();
          break;
        default:
          break;
      }
    }

    function show() {
      $div.css({
        "display": "block"
      });
    }

    function changeInterface(interfaceId, num) {
      if (interfaceId == 0) {
        $("#isInterface_no_"+num).prop("checked", true);
        $("#isInterface_yes_"+num).prop("checked", false);
      } else {
        $("#isInterface_no_"+num).prop("checked", false);
        $("#isInterface_yes_"+num).prop("checked", true);
      }
    }

    function hide() {
      $div.animate({
        height: 0
      }, 10, function() {
        $div.css({
          "display": "none",
          "height": "auto"
        });
        $div.empty(); //清空盒子内容
      });
    }

    function active(i) {
      $div.children().eq(i).css({
        "background-color": "rgba(0,0,0,0.3)"
      });
    }

    function defaut(i) {
      $div.children().eq(i).css({
        "background-color": "rgba(255,255,255,0.3)"
      });
    }
  };
};

// 重发
YX.fn.doResend = function(evt) {
  var $node;
  if (evt.target.tagName.toLowerCase() === 'span') {
    $node = $(evt.target);
  } else {
    $node = $(evt.target.parentNode);
  }
  var sessionId = $node.data('session');
  var idClient = $node.data('id');
  var msg = this.cache.findMsg(sessionId, idClient);
  this.mysdk.resendMsg(
    msg,
    function(err, data) {
      if (err) {
        alert(err.message || '发送失败');
      } else {
        this.cache.setMsg(sessionId, idClient, data);
        var msgHtml = appUI.buildChatContentUI(sessionId, this.cache);
        this.$chatContent.html(msgHtml).scrollTop(99999);
        $('#uploadForm')
          .get(0)
          .reset();
      }
    }.bind(this)
  );
};

YX.fn.getChatMessageList = function(account, pageNo, pageSize) {
	var that = this;
	$.post('/getChatMessageList', {pageNo: pageNo, pageSize: pageSize, teamId: account}, function (result) {
		that.showChatMessageList(pageNo, pageSize, result);
	});
};

YX.fn.getHistoryMsgs2 = function(scene, account, pageNo, pageSize) {
	this.$chatContent.html('');
	var that = this,
		param = {
            scene: scene,
            to: account,
            lastMsgId: 0,
            limit: 20,
            reverse: false,
            done: that.cbCloudMsg2.bind(that)
        };
    that.mysdk.getHistoryMsgs(param);
};

YX.fn.showChatMessageList = function (pageNo, pageSize, result) {
	var msgHtml = '';
	$('div[id^="chat_msg_page_"]').remove();
	this.$chatContent.unbind();

    console.log("encrypt: "+result);

    result = result.replace(/\"/g, "");
    result = result.trim();
    result = decryptAes(result);

    result = result.replace(/\\/g, "");
    result = result.replace(/\"{/g, "{");
    result = result.replace(/\}"/g, "}");
    result = JSON.parse(result);
	if(result) {
		var data = result.data;
		console.log("data : " + data);
		if(null == data.list) {
			msgHtml = '<div class="no-msg tc" id="chat_msg_page_0"><span class="radius5px">没有更早的聊天记录</span></div>';
		} else {
			var tipHtml = '';
			if(pageNo >= data.totalPage) {
				tipHtml ='<div class="no-msg tc" id="chat_msg_page_20"><span class="radius5px">没有更早的聊天记录</span></div>';
			} else {
				tipHtml = '<div class="u-status tc" id="chat_msg_page_more" data-pageSize="' + pageSize + '" data-page="' + (pageNo + 1) + '"><span class="radius5px"><a class="chat-loadMore" style="color:#b52b27;">加载更多记录' + '</a></span></div>';
				this.$chatContent.delegate('.chat-loadMore', 'click', this.loadMoreChatMessage.bind(this));
			}
			msgHtml = tipHtml + appUI.buildChatContentUI3(data.list);
		}
	} else {
		msgHtml = '<div class="no-msg tc"><span class="radius5px">获取聊天记录异常</span></div>';
	}
	$(msgHtml).prependTo(this.$chatContent);
	this.$chatContent.scrollTop(9999);
};

/**
 * aes解密
 * @param result
 * @returns {string}
 */
/*function decryptAes(result) {
    var key = CryptoJS.enc.Utf8.parse("0123456789abcdef");
    var encryptedData = result;
    var encryptedHexStr = CryptoJS.enc.Hex.parse(encryptedData);
    var encryptedBase64Str = CryptoJS.enc.Base64.stringify(encryptedHexStr);
    var decryptedData = CryptoJS.AES.decrypt(encryptedBase64Str, key, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    // var decryptedData = CryptoJS.AES.decrypt(encryptedBase64Str, key);
    var decryptedStr = decryptedData.toString(CryptoJS.enc.Utf8);
    console.log("解密后:"+decryptedStr);
    return decryptedStr;
}*/

function decryptAes(result) {
    var key = CryptoJS.enc.Utf8.parse("0123456789abcdef");
    var encryptedData = result;
    var encryptedHexStr = CryptoJS.enc.Hex.parse(encryptedData);
    var encryptedBase64Str = CryptoJS.enc.Base64.stringify(encryptedHexStr);
    var decryptedData = CryptoJS.AES.decrypt(encryptedBase64Str, key, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    // var decryptedData = CryptoJS.AES.decrypt(encryptedBase64Str, key);
    var decryptedStr = decryptedData.toString(CryptoJS.enc.Utf8);
    console.log("解密后:"+decryptedStr);
    return decryptedStr;
}

YX.fn.cbCloudMsg2 = function (error, obj) {
	var msgHtml = '';
	$('div[id^="chat_msg_list_"]').remove();
	if (!error) {
		if (obj.msgs.length === 0) {
			msgHtml = '<div class="no-msg tc" id="chat_msg_list_0"><span class="radius5px">没有更早的聊天记录</span></div>';
        } else {
        	var tipHtml = '';
        	if(obj.msgs.length < 20) {
        		tipHtml ='<div class="no-msg tc" id="chat_msg_list_20"><span class="radius5px">没有更早的聊天记录</span></div>';
        	} else {
        		tipHtml = '<div class="u-status tc" id="chat_msg_list_more"><span class="radius5px"><a class="chat-loadMore" style="color:#b52b27;">加载更多记录</a></span></div>';
        		this.$chatContent.delegate('.chat-loadMore', 'click', this.loadMoreCloudMsg2.bind(this));
        	}
        	msgHtml = tipHtml + appUI.buildChatContentUI2(obj.msgs);
        }
	} else {
		msgHtml = '<div class="no-msg tc"><span class="radius5px">获取历史消息失败</span></div>';
	}
	$(msgHtml).prependTo(this.$chatContent);
    this.$chatContent.scrollTop(9999);
};

YX.fn.loadMoreChatMessage = function() {
	var lastPage = $("#chat_msg_page_more");
	var pageNo = parseInt(lastPage.attr('data-page'));
	var pageSize = parseInt(lastPage.attr('data-pageSize'));
	this.getChatMessageList(this.crtSessionAccount, pageNo, pageSize);
}

YX.fn.loadMoreCloudMsg2 = function () {
	var id = this.crtSessionAccount,
    scene = this.crtSessionType,
    lastItem = $("#chatContent .item").first(),
    endTime = parseInt(lastItem.attr('data-time')),
    lastMsgId = parseInt(lastItem.attr('data-idServer')),
    param ={
        scene: scene,
        to: id,
        beginTime: 0,
        endTime: endTime,
        lastMsgId: lastMsgId,
        limit: 20,
        reverse: false,
        done: this.cbCloudMsg2.bind(this)
    };
	this.mysdk.getHistoryMsgs(param);
};

/************************************************************
 * 获取当前会话消息
 * @return {void}
 *************************************************************/
YX.fn.getHistoryMsgs = function(scene, account) {
  var id = scene + '-' + account;
  var sessions = this.cache.findSession(id);
  var msgs = this.cache.getMsgs(id);
  //标记已读回执
  this.sendMsgRead(account, scene);
  if (!!sessions) {
    // if (sessions.unread >= msgs.length) {
    // var end = msgs.length > 0 ? msgs[0].time : false;
    // }
    // if (sessions.lastMsg) {
    //   var end = sessions.lastMsg.time || false
    // }
    var end = false
    this.mysdk.getLocalMsgs(id, end, this.getLocalMsgsDone.bind(this));
    return;
    // }
  }
  this.doChatUI(id);
};
//拿到历史消息后聊天面板UI呈现
YX.fn.doChatUI = function(id) {
  this.cache.dealTeamMsgReceipts(id, function() {
    var temp = appUI.buildChatContentUI(id, this.cache);
    this.$chatContent.html(temp);
    this.$chatContent.scrollTop(9999);
    //已读回执UI处理
    this.markMsgRead(id);
  }.bind(this));
};

YX.fn.getLocalMsgsDone = function(err, data) {
  if (!err) {
    var reset = true
    this.cache.addMsgsByReverse(data.msgs, true);
    var id = data.sessionId;
    var array = getAllAccount(data.msgs);
    var that = this;
    this.checkUserInfo(array, function() {
      that.doChatUI(id);
    });
  } else {
    alert('获取历史消息失败');
  }
};

YX.fn.isContainSensitive = function(text) {
  var sensitiveWords = this.cache.getSensitiveWord();

  var $toRecord = $('#toRecord')
  var isRecording = $toRecord.hasClass('recording');
  if (isRecording) {
    $toRecord.removeClass('recording');
    $toRecord.addClass('recorded');
    if (YX.fn.recorder) {
      clearTimeout(YX.fn.recordTimeout)
      YX.fn.recorder.stop();
    }
  }
}

//检查用户信息有木有本地缓存 没的话就去拿拿好后在执行回调
YX.fn.checkUserInfo = function(array, callback) {
  var arr = [];
  var that = this;
  for (var i = array.length - 1; i >= 0; i--) {
    if (!this.cache.getUserById(array[i])) {
      arr.push(array[i]);
    }
  }
  if (arr.length > 0) {
    this.mysdk.getUsers(arr, function(error, data) {
      if (!error) {
        that.cache.setPersonlist(data);
        callback();
      } else {
        alert('获取用户信息失败');
      }
    });
  } else {
    callback();
  }
};
//发送已读回执
YX.fn.sendMsgRead = function(account, scene) {
  if (scene === 'p2p') {
    var id = scene + '-' + account;
    var sessions = this.cache.findSession(id);
    this.mysdk.sendMsgReceipt(sessions.lastMsg, function(err, data) {
      if (err) {
        console.log(err);
      }
    });
  }
};
//UI上标记消息已读
YX.fn.markMsgRead = function(id) {
  if (!id || this.crtSession !== id) {
    return;
  }
  var msgs = this.cache.getMsgs(id);
  for (var i = msgs.length - 1; i >= 0; i--) {
    var message = msgs[i];
    // 目前不支持群已读回执
    if (message.scene === 'team') {
      return;
    }
    if (message.type !== 'tip' && window.nim.isMsgRemoteRead(message)) {
      $('.item.item-me.read').removeClass('read');
      $('#' + message.idClient).addClass('read');
      break;
    }
  }
};
//撤回消息
YX.fn.backoutMsg = function(id, data) {
  var msg = data ? data.msg : this.cache.findMsg(this.crtSession, id);
  var to = msg.target;
  var session = msg.sessionId;
  var opeAccount = msg.opeAccount || msg.from;
  var opeNick = getNick(opeAccount);
  if (msg.scene === 'team') {
    var teamId = msg.to || this.crtSessionAccount;
    var teamInfo = this.cache.getTeamById(teamId);
    if (teamInfo && opeAccount !== msg.from) {
      if (teamInfo.owner === opeAccount) {
        opeNick = '群主' + opeNick;
      } else if (teamInfo.type === 'advanced') {
        opeNick = '管理员' + opeNick;
      }
    }
  }

  this.nim.sendTipMsg({
    isLocal: true,
    idClient: msg.idClient || null,
    scene: msg.scene,
    to: to,
    tip: (userUID === opeAccount ? '你' : opeNick) + '撤回了一条消息',
    time: msg.time,
    done: function(err, data) {
      if (!err) {
        this.cache.backoutMsg(session, id, data);
        if (this.crtSession === session) {
          var msgHtml = appUI.buildChatContentUI(this.crtSession, this.cache);
          this.$chatContent.html(msgHtml).scrollTop(99999);
          //已读回执UI处理
          this.markMsgRead(this.crtSession);
        }
      } else {
        alert('操作失败');
      }
    }.bind(this)
  });
};

/*********************************多人音视频模块********************************* */
/** 发送群视频tip消息
 * @param {object} option
 * @param {string} option.teamId 群id
 * @param {string} option.account 发送群视频的uid
 * @param {string} option.message tip消息
 */
YX.fn.sendTeamNetCallTip = function(option) {
  var tmpUser = this.cache.getTeamMemberInfo(option.account, option.teamId);
  option.nick = tmpUser.nickInTeam || getNick(option.account);

  option.isLocal = option.isLocal === undefined ? true : option.isLocal;
  /** 远程 先禁掉 */
  this.nim.sendTipMsg({
    isLocal: option.isLocal,
    scene: 'team',
    to: option.teamId,
    tip: getNick(option.nick) + option.message,
    time: Date.now(),
    isPushable: false,
    isHistoryable: false,
    isRoamingable: false,
    done: function(err, data) {
      // err && console.log(err)
      // this.buildSessions();
      // var msgHtml = appUI.buildChatContentUI(this.crtSession, this.cache)
      this.cache.addMsgs(data);
      var msgHtml = appUI.updateChatContentUI(data, this.cache);
      this.$chatContent.append(msgHtml).scrollTop(99999);
    }.bind(this)
  });
};

/** 对列表用户进行点对点发送自定义系统通知
 * @param {Array} list
 * @param {object} option
 * @param {string} option.caller 主叫人
 * @param {string} option.type 视频还是音频, 如果为空，则取消呼叫!
 * @param {string} option.list 被呼叫uid的列表
 * @param {string} option.teamId 群id
 * @param {string} option.channelName 房间id
 */
YX.fn.sendCustomMessage = function(option) {
  var that = this;
  option.list = option.list || [];

  var tmpUser = this.cache.getTeamMemberInfo(option.caller, option.teamId);
  option.nick = tmpUser.nickInTeam || getNick(option.caller);

  option.list.forEach(function(uid) {
    // this.mysdk.sendCustomMessage('p2p', item, content, this.sendMsgDone.bind(this))
    that.nim.sendCustomSysMsg({
      scene: 'p2p',
      to: uid,
      enablePushNick: false,
      content: JSON.stringify({
        id: 3,
        members: option.list,
        teamId: option.teamId,
        room: option.channelName,
        type: option.type
      }),
      isPushable: true,
      sendToOnlineUsersOnly: false,
      apnsText: option.nick + '正在呼叫您',
      done: function(error, msg) {
        console.log(msg);
      }
    });
  });
};
