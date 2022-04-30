// 数据缓存
// 建议开发者选择mvvm框架来通过数据来驱动UI变化
var Cache = (function() {
  var Cache = function(argument) {
    this.friendslist = [];
    this.personlist = {};
    // 用户订阅的事件同步
    this.personSubscribes = {};
    this.teamlist = [];
    this.teamMembers = {};
    this.teamMap = {};
    this.msgs = {};
    this.sessions = [];
    this.blacklist = [];
    this.mutelist = [];
    this.sysMsgs = [];
    this.customSysMsgs = [];
    this.sysMsgCount = 0;
    this.sensitiveWord = [];
    this.knowledgeBase = [];
    this.sensitiveWordMap = {};
    this.knowledgeBaseMap = {};
  };

  /**
   * 根据account获取用户对象
   * @param account: 用户account
   */
  Cache.prototype.getUserById = function(account) {
    if (this.personlist[account]) {
      return this.personlist[account];
    }
    return false;
  };
  // 用户对象相关
  Cache.prototype.setPersonlist = function(list) {
    var item;
    for (var i = list.length - 1; i >= 0; i--) {
      item = list[i];
      this.personlist[item.account] = item;
    }
  };

  Cache.prototype.updateAvatar = function(url) {
    this.personlist[userUID].avatar = url;
  };
  Cache.prototype.updatePersonlist = function(list) {
    if (!this.personlist[list.account]) {
      this.personlist[list.account] = list;
    } else {
      for (var p in list) {
        this.personlist[list.account][p] = list[p];
      }
    }
  };

  Cache.prototype.getPersonlist = function() {
    return this.personlist;
  };

  /**
   * 好友相关
   */
  Cache.prototype.setFriends = function(list) {
    this.friendslist = list;
  };
  Cache.prototype.getFriends = function(list) {
    return this.friendslist;
  };
  // 获取好友备注名
  Cache.prototype.getFriendAlias = function(account) {
    for (var i = this.friendslist.length - 1; i >= 0; i--) {
      if (this.friendslist[i].account == account) {
        return this.friendslist[i].alias || '';
      }
    }
  };
  Cache.prototype.updateFriendAlias = function(account, alias) {
    for (var i = this.friendslist.length - 1; i >= 0; i--) {
      if (this.friendslist[i].account == account) {
        this.friendslist[i].alias = alias;
        return;
      }
    }
  };
  Cache.prototype.addFriend = function(list) {
    if (!this.isFriend(list.account)) {
      this.friendslist.push(list);
    }
  };
  Cache.prototype.removeFriend = function(account) {
    for (var i = this.friendslist.length - 1; i >= 0; i--) {
      if (this.friendslist[i].account == account) {
        this.friendslist.splice(i, 1);
      }
    }
  };
  Cache.prototype.getFriendslist = function() {
    var array = [];
    for (var i = 0; i < this.friendslist.length; i++) {
      array.push(this.getUserById(this.friendslist[i].account));
    }
    return array;
  };

  Cache.prototype.getFriendslistOnShow = function() {
    var array = [];
    for (var i = 0; i < this.friendslist.length; i++) {
      if (!this.inBlacklist(this.friendslist[i].account)) {
        array.push(this.getUserById(this.friendslist[i].account));
      }
    }
    return array;
  };

  Cache.prototype.isFriend = function(account) {
    for (var i = this.friendslist.length - 1; i >= 0; i--) {
      if (this.friendslist[i].account == account) {
        return true;
      }
    }
    return false;
  };

  /**
   * 设置会话列表
   * @param {Array} sessions 会话对象
   */
  Cache.prototype.setSessions = function(sessions) {
    this.sessions = sessions;
  };

  /**
   * 获取当前会话
   * @return {Array} 会话集合
   */
  Cache.prototype.getSessions = function() {
	  var list = new Array();
	  var sessionList = this.sessions;
	  if(null != distributionList && distributionList.length > 0) {
		  for(var i = 0; i < distributionList.length; i++) {
			  var currSession = distributionList[i];
			  var sessionCheck = false;
			  if(null != sessionList && sessionList.length > 0) {
				  for (var j = sessionList.length - 1; j >= 0; j--) {
					  var session = sessionList[j];
					  if(currSession.id == session.id) {
						  sessionCheck = true;
						  list.push(session);
						  break;
					  }
				  }
			  }
			  if(!sessionCheck) {
				  list.push(currSession);
			  }
		  }
	  }
	  return list;
    //return this.sessions;
  };

  /**
   * 获取指定会话
   * @return {Array} 会话集合
   */
  Cache.prototype.findSession = function(id) {
    for (var i = this.sessions.length - 1; i >= 0; i--) {
      if (this.sessions[i].id === id) {
        return this.sessions[i];
      }
    }
    return false;
  };

  Cache.prototype.addMsgs = function(msgs) {
    var item, user;
    if (!$.isArray(msgs)) {
      this.addMsg(msgs);
      return;
    }
    for (var i = 0; i < msgs.length; i++) {
      if (msgs[i].scene === 'team') {
        user = msgs[i].to;
        if (!this.msgs['team-' + user]) {
          this.msgs['team-' + user] = [];
        }
        this.msgs['team-' + user].push(msgs[i]);
      } else {
        user = msgs[i].from === userUID ? msgs[i].to : msgs[i].from;
        if (!this.msgs['p2p-' + user]) {
          this.msgs['p2p-' + user] = [];
        }
        this.msgs['p2p-' + user].push(msgs[i]);
      }
    }
  };
  Cache.prototype.addMsg = function(msg) {
    var user;
    if (msg.scene === 'team') {
      user = 'team-' + msg.to;
      if (!this.msgs[user]) {
        this.msgs[user] = [];
      }
      this.msgs[user].push(msg);
    } else {
      user = 'p2p-' + (msg.from === userUID ? msg.to : msg.from);
      if (!this.msgs[user]) {
        this.msgs[user] = [];
      }
      this.msgs[user].push(msg);
    }
  };
  Cache.prototype.addMsgsByReverse = function(msgs, reset) {
    var item;
    var user;
    var cacheSession = {};
    for (var i = 0; i < msgs.length; i++) {
      if (msgs[i].scene === 'team') {
        user = msgs[i].to;
        var sessionId = msgs[i].scene + '-' + user
        if (reset && (!cacheSession[sessionId])) {
          this.msgs[sessionId] = []
        }
        if (!this.msgs[sessionId]) {
          this.msgs[sessionId] = [];
        }
        this.msgs[sessionId].unshift(msgs[i]);
        cacheSession[sessionId] = true;
      } else {
        user = msgs[i].from === userUID ? msgs[i].to : msgs[i].from;
        var sessionId = msgs[i].scene + '-' + user
        if (reset && (!cacheSession[sessionId])) {
          this.msgs[sessionId] = []
        }
        if (!this.msgs[sessionId]) {
          this.msgs[sessionId] = [];
        }
        this.msgs[sessionId].unshift(msgs[i]);
        cacheSession[sessionId] = true;
      }
    }
    for (var sid in cacheSession) {
      this.msgs[sid] = this.msgs[sid].sort(function(a, b) {
        return a.time - b.time;
      });
    }
  };
  //查消息 session-id idClient
  Cache.prototype.findMsg = function(sid, cid) {
    var list = this.msgs[sid];
    for (var i = list.length - 1; i >= 0; i--) {
      if (list[i].idClient === cid) {
        return list[i];
      }
    }
    return false;
  };
  //设置消息用于重发状态变化 session-id idClient 消息
  Cache.prototype.setMsg = function(sid, cid, msg) {
    var list = this.msgs[sid];
    for (var i = list.length - 1; i >= 0; i--) {
      if (list[i].idClient === cid) {
        list.splice(i, 1);
        list.push(msg);
        return;
      }
    }
  };
  //回撤消息,回撤的消息用tip替换
  Cache.prototype.backoutMsg = function(sid, cid, msg) {
    var list = this.msgs[sid];
    if (!list) {
      this.msgs[sid] = [msg];
      return;
    }
    for (var i = list.length - 1; i >= 0; i--) {
      if (list[i].idClient === cid) {
        list[i] = msg;
        return;
      }
    }
    this.msgs[sid].push(msg);
    this.msgs[sid].sort(function(a,b){
			return a.time - b.time;
		});
  };
  //系统消息
  Cache.prototype.setSysMsgs = function(data) {
    this.sysMsgs = data;
  };
  Cache.prototype.getSysMsgs = function(data) {
    return this.sysMsgs;
  };
  //自定义系统消息
  Cache.prototype.addCustomSysMsgs = function(data) {
    for (var i = 0; i < data.length; i++) {
      this.customSysMsgs.push(data[i]);
    }
  };
  Cache.prototype.deleteCustomSysMsgs = function() {
    this.customSysMsgs = [];
  };
  Cache.prototype.getCustomSysMsgs = function(data) {
    return this.customSysMsgs;
  };
  // 系统消息计数
  Cache.prototype.getSysMsgCount = function(value) {
    return this.sysMsgCount;
  };
  Cache.prototype.setSysMsgCount = function(value) {
    this.sysMsgCount = value;
  };
  Cache.prototype.addSysMsgCount = function(value) {
    this.sysMsgCount = this.sysMsgCount + value;
  };
  // /**
  //  * 删除漫游消息/历史消息
  //  * @param {String} to 需移除的消息对象标识
  //  */
  // Cache.prototype.rmMsgs = function(to) {
  //  if($.type(to) === "string"){
  //    if(!!this.msgs["p2p-"+to]){
  //      delete this.msgs["p2p-"+to];
  //    }
  //  }else{
  //    if(!!this.msgs["team-"+to]){
  //      delete this.msgs["team-"+to];
  //    }
  //  }
  // };

  /**
   * 获取漫游/历史消息
   * @return {Array}
   */

  Cache.prototype.getMsgs = function(id) {
    if (!!this.msgs[id]) {
      return this.msgs[id];
    } else {
      return [];
    }
  };

  Cache.prototype.dealTeamMsgReceipts = function(msgSymbol, callback) {
    callback = callback || function() {};
    if (typeof msgSymbol === 'string') {
      var msgs = this.msgs[msgSymbol] || [];
    } else {
      var sessionId = msgSymbol.sessionId;
      var msgs = this.msgs[sessionId] || [];
    }
    var teamMsgNeedReceipts = [];
    var teamMsgMap = {};
    var teamMsgReads = [];
    for (var i = 0, l = msgs.length; i < l; ++i) {
      var message = msgs[i];
      if (message.idServer) {
        teamMsgMap[message.idServer] = message;
      }
      if (
        message.scene === 'team' &&
        message.needMsgReceipt &&
        message.idServer
      ) {
        if (message.flow === 'in' && !message.hasSendReceipt) {
          teamMsgNeedReceipts.push({
            teamId: message.target,
            idServer: message.idServer
          });
          message.hasSendReceipt = true;
        } else if (message.flow === 'out') {
          teamMsgReads.push({
            teamId: message.target,
            idServer: message.idServer
          });
        }
      }
    }
    // 发送已读回执并处理回调
    if (teamMsgNeedReceipts.length > 0) {
      nim.sendTeamMsgReceipt({
        teamMsgReceipts: teamMsgNeedReceipts,
        done: function sendTeamMsgReceiptDone(error, obj, content) {
          if (error) {
            console.error('发送群已读错误', error)
            return
          }
          var failedCnt = content.teamMsgReceipts;
          if (failedCnt) {
            for (var i = 0; i < failedCnt.length; i++) {
              teamMsgMap[failedCnt[i].idServer].hasSendReceipt = false;
            }
          }
        }
      });
    }
    if (teamMsgReads.length > 0) {
      nim.getTeamMsgReads({
        teamMsgReceipts: teamMsgReads,
        done: function getTeamMsgReadsDone(error, obj, content) {
          if (error) {
            console.error('获取群已读错误', error)
            callback()
            return
          }
          if (content.teamMsgReceipts) {
            for (var i = 0; i < content.teamMsgReceipts.length; i++) {
              var cur = content.teamMsgReceipts[i];
              teamMsgMap[cur.idServer].teamMsgUnread = cur.unread;
              teamMsgMap[cur.idServer].teamMsgRead = cur.read;
            }
          }
          callback();
        }
      });
    } else {
      callback();
    }
  };

  /**
   * 根据映射名来获取消息对象集合 如"p2p-iostest"
   * @param  {String} name 名字
   * @return {Array}
   */
  Cache.prototype.getMsgsByUser = function(name) {
    return this.msgs[name] || [];
  };
  /**
   * 离线消息处理
   * @param {Array} msgs
   */
  Cache.prototype.addOfflineMsgs = function(msgs) {
    for (var i = msgs.length - 1; i >= 0; i--) {
      if (
        /text|image|file|audio|video|geo|custom|notification|deleteMsg/i.test(
          msgs[i].type
        )
      ) {
        this.addMsgs(msgs[i]);
      } else {
        continue;
      }
    }
  };

  /**
   * 初始化群列表
   * @param {array} list
   */
  Cache.prototype.setTeamList = function(list) {
    var item;
    for (var i = list.length - 1; i >= 0; i--) {
      item = list[i];
      this.teamMap[item.teamId] = item;
    }
    this.teamlist = list;
  };

  Cache.prototype.addTeam = function(team) {
    if (!this.hasTeam(team.teamId)) {
      this.teamMap[team.teamId] = team;
      this.teamlist.push(team);
    }
  };
  Cache.prototype.hasTeam = function(id) {
    var item;
    for (var i = this.teamlist.length - 1; i >= 0; i--) {
      item = this.teamlist[i];
      if (item.teamId === id) {
        return true;
      }
    }
    return false;
  };

  /**
   * 获取群列表
   */
  Cache.prototype.getTeamlist = function() {
    return this.teamlist;
  };

  /**
   * 获取群对象
   */
  Cache.prototype.getTeamMap = function() {
    return this.teamMap;
  };
  Cache.prototype.addTeamMap = function(data) {
    for (var i = data.length - 1; i >= 0; i--) {
      item = data[i];
      this.teamMap[item.teamId] = item;
    }
  };
  /**
   * 根据群id获取群对象
   */
  Cache.prototype.getTeamById = function(teamId) {
    if (this.hasTeam(teamId)) {
      return this.teamMap[teamId];
    }
    return null;
  };
  Cache.prototype.getTeamMapById = function(teamId) {
    return this.teamMap[teamId] || null;
  };

  /**
   * 根据群id删除群
   */
  Cache.prototype.removeTeamById = function(id) {
    for (var i in this.teamlist) {
      if (this.teamlist[i].teamId === id) {
        this.teamlist.splice(i, 1);
        break;
      }
    }
  };

  /**
   * 更变群名
   */
  Cache.prototype.updateTeam = function(teamId, obj) {
    for (var p in obj) {
      this.teamMap[teamId][p] = obj[p];
    }
    for (var i in this.teamlist) {
      if (this.teamlist[i].teamId === teamId) {
        for (var p in obj) {
          this.teamlist[i][p] = obj[p];
        }
        break;
      }
    }
  };
  Cache.prototype.setTeamMembers = function(id, list) {
    this.teamMembers[id] = list;
  };
  Cache.prototype.addTeamMembers = function(id, array) {
    if (!this.teamMembers[id]) {
      return;
    }
    for (var i = array.length - 1; i >= 0; i--) {
      this.teamMembers[id].members.push(array[i]);
    }
  };
  Cache.prototype.removeTeamMembers = function(id, array) {
    var obj = this.teamMembers[id],
      account;
    if (obj) {
      for (var j = array.length - 1; j >= 0; j--) {
        account = array[j];
        for (var i = obj.members.length - 1; i >= 0; i--) {
          if (obj.members[i].account === account) {
            obj.members.splice(i, 1);
            break;
          }
        }
      }
    }
  };
  Cache.prototype.getTeamMembers = function(id) {
    return this.teamMembers[id];
  };
  Cache.prototype.getTeamMemberInfo = function(account, id) {
    var obj = this.teamMembers[id];
    if (obj && obj.members) {
      for (var i = obj.members.length - 1; i >= 0; i--) {
        if (obj.members[i].account === account) {
          return obj.members[i];
        }
      }
    }
    return false;
  };
  Cache.prototype.isTeamManager = function(account, id) {
    var obj = this.teamMembers[id];
    if (obj) {
      for (var i = obj.members.length - 1; i >= 0; i--) {
        if (
          obj.members[i].account === account &&
          (obj.members[i].type === 'owner' || obj.members[i].type === 'manager')
        ) {
          return true;
        }
      }
    }
    return false;
  };
  Cache.prototype.updateTeamMemberMute = function(id, account, mute) {
    var obj = this.teamMembers[id];
    if (obj) {
      for (var i = obj.members.length - 1; i >= 0; i--) {
        if (obj.members[i].account === account) {
          obj.members[i].mute = mute;
          return;
        }
      }
    }
  };
  Cache.prototype.setMutelist = function(data) {
    this.mutelist = data;
  };

  Cache.prototype.getMutelist = function(data) {
    return this.mutelist;
  };

  Cache.prototype.inMutelist = function(account) {
    for (var i = this.mutelist.length - 1; i >= 0; i--) {
      if (this.mutelist[i].account == account) {
        return true;
      }
    }
    return false;
  };

  Cache.prototype.addToMutelist = function(data) {
    this.mutelist.push(data);
  };
  Cache.prototype.removeFromMutelist = function(account) {
    for (var i = this.mutelist.length - 1; i >= 0; i--) {
      if (this.mutelist[i].account == account) {
        this.mutelist.splice(i, 1);
        return true;
      }
    }
    return false;
  };
  Cache.prototype.setBlacklist = function(data) {
    this.blacklist = data;
  };
  Cache.prototype.getBlacklist = function() {
    return this.blacklist;
  };
  Cache.prototype.inBlacklist = function(account) {
    for (var i = this.blacklist.length - 1; i >= 0; i--) {
      if (this.blacklist[i].account == account) {
        return true;
      }
    }
    return false;
  };
  Cache.prototype.addToBlacklist = function(data) {
    this.blacklist.push(data);
  };
  Cache.prototype.removeFromBlacklist = function(account) {
    for (var i = this.blacklist.length - 1; i >= 0; i--) {
      if (this.blacklist[i].account == account) {
        this.blacklist.splice(i, 1);
        return true;
      }
    }
    return false;
  };
  // 更新用户多端同步订阅状态
  Cache.prototype.updatePersonSubscribe = function(data) {
    if (data.account) {
      var account = data.account;
      this.personSubscribes[account] = this.personSubscribes[account] || {};
      this.personSubscribes[account][data.type] = {
        account: data.account,
        type: data.type,
        value: data.value,
        custom: data.custom,
        clientType: data.clientType,
        serverConfig: data.serverConfig,
        time: data.time,
        // 多端状态
        multiPortStatus: ''
      };
      var tempData = this.personSubscribes[account][data.type];

      function getMultiPortStatus(customType, custom) {
        var netState = {
          0: '',
          1: 'Wifi',
          2: 'WWAN',
          3: '2G',
          4: '3G',
          5: '4G'
        };
        var onlineState = {
          0: '在线',
          1: '忙碌',
          2: '离开'
        };
        var multiPortStatus = '';
        var custom = custom || {};
        if (customType !== 0) {
          // 有serverConfig.online属性，已被赋值端名称
          custom = custom[customType];
        } else if (custom[4]) {
          custom = custom[4];
          multiPortStatus = '电脑';
        } else if (custom[2]) {
          custom = custom[2];
          multiPortStatus = 'iOS';
        } else if (custom[1]) {
          custom = custom[1];
          multiPortStatus = 'Android';
        } else if (custom[16]) {
          custom = custom[16];
          multiPortStatus = 'Web';
        } else if (custom[64]) {
          custom = custom[64];
          multiPortStatus = 'Mac';
        }
        if (custom) {
          custom = JSON.parse(custom);
          if (typeof custom['net_state'] === 'number') {
            var tempNetState = netState[custom['net_state']];
            if (tempNetState) {
              multiPortStatus += '-' + tempNetState;
            }
          }
          if (typeof custom['online_state'] === 'number') {
            multiPortStatus += onlineState[custom['online_state']];
          } else {
            multiPortStatus += '在线';
          }
        }
        return multiPortStatus;
      }

      // demo自定义多端登录同步事件
      if (+data.type === 1) {
        if (
          +data.value === 1 ||
          +data.value === 2 ||
          +data.value === 3 ||
          +data.value === 10001
        ) {
          var serverConfig = JSON.parse(tempData.serverConfig);
          var customType = 0;
          tempData.multiPortStatus = '';
          // 优先判断serverConfig字段
          if (serverConfig.online) {
            if (serverConfig.online.indexOf(4) >= 0) {
              tempData.multiPortStatus = '电脑';
              customType = 4;
            } else if (serverConfig.online.indexOf(2) >= 0) {
              tempData.multiPortStatus = 'iOS';
              customType = 2;
            } else if (serverConfig.online.indexOf(1) >= 0) {
              tempData.multiPortStatus = 'Android';
              customType = 1;
            } else if (serverConfig.online.indexOf(16) >= 0) {
              tempData.multiPortStatus = 'Web';
              customType = 16;
            } else if (serverConfig.online.indexOf(64) >= 0) {
              tempData.multiPortStatus = 'Mac';
              customType = 64;
            }
          }
          if (tempData.custom && Object.keys(tempData.custom).length > 0) {
            var portStatus = getMultiPortStatus(customType, tempData.custom);
            // 如果serverConfig里有属性而custom里没有对应属性值
            if (tempData.multiPortStatus !== '' && portStatus === '') {
              tempData.multiPortStatus += '在线';
            } else {
              tempData.multiPortStatus += portStatus;
            }
          } else if (customType !== 0) {
            tempData.multiPortStatus += '在线';
          } else {
            tempData.multiPortStatus = '离线';
          }
        }
      }
      return true;
    }
    return false;
  };
  // 获取特定账号的订阅状态
  Cache.prototype.getMultiPortStatus = function(account) {
    if (this.personSubscribes[account] && this.personSubscribes[account][1]) {
      return this.personSubscribes[account][1].multiPortStatus;
    }
    return '';
  };
  // 获取用户的订阅关系
  Cache.prototype.getPersonSubscribes = function() {
    return this.personSubscribes;
  };

  /** 敏感词 */
  Cache.prototype.addSensitiveWord = function(data) {
    for (var i = 0; i < data.length; i++) {
      this.sensitiveWord.push(data[i]);
    }
  };
  Cache.prototype.getSensitiveWord = function() {
    return this.sensitiveWord;
  };
  Cache.prototype.clearSensitiveWord = function() {
    this.sensitiveWord = [];
  };
  Cache.prototype.getSensitiveWordMap = function() {
    return this.sensitiveWordMap;
  };
  Cache.prototype.setSensitiveWordMap = function(data) {
    this.sensitiveWordMap = data;
  };
  /**
   * 构建敏感词map
   * @param sensitiveWord
   */
  Cache.prototype.makeSensitiveWordMap = function (sensitiveWord) {
    let result = new Map();
    for (let i = 0; i < sensitiveWord.length; i++) {
      let map = result;
      let sw = sensitiveWord[i];
      for(let j = 0; j < sw.length; j++) {
        let word = sw.charAt(j);
        if (map.hasOwnProperty(word)) {
          map = map[word];
        } else {
          if (map["laster"] == true) {
            map["laster"] = false;
          }
          let tempMap = new Map();
          tempMap["laster"] = true;
          map[word] = tempMap;
          map = map[word];
        }
      }
    }
    this.sensitiveWordMap = result;
  };
  /**
   * 检查敏感词是否存在
   */
  Cache.prototype.checkSensitiveWord = function(text, index) {
   let currentMap = this.sensitiveWordMap;
   let flag = false;
   let wordNum = 0;
   let sensitiveWd = '';
   for (let i = index; i < text.length; i++) {
     let word = text.charAt(i);
     currentMap = currentMap[word];
     if (currentMap != null) {
       wordNum++;
       sensitiveWd += word;
       if (currentMap["laster"] == true) {
         flag = true;
         break;
       }
     } else {
       break;
     }
   }
   if (wordNum < 2) {
     flag = false;
   }
   return {flag, sensitiveWd};
  };
  /**
   * 判断文本是否存在敏感词
   * @param text
   */
  Cache.prototype.filterSensitiveWord = function (text) {
    let matchResult = {flag: false, sensitiveWd: ''};
    let textTrim = text.replace(/[^\u4e00-\u9fa5\u0030-\u0039\u0061-\u007a\u0041-\u005a]+/g, '');
    for (let i = 0; i < textTrim.length; i++) {
      matchResult = this.checkSensitiveWord(textTrim, i);
      if (matchResult.flag) {
        console.log("输入内容包含敏感词："+matchResult.sensitiveWd);
        break;
      }
    }
    return matchResult;
  };
  /** 敏感词 */

  /** 知识库 */
  Cache.prototype.addKnowledgeBase = function(data) {
    for (var i = 0; i < data.length; i++) {
      this.knowledgeBase.push(data[i]);
    }
  };
  Cache.prototype.getKnowledgeBase = function() {
    return this.knowledgeBase;
  };
  Cache.prototype.clearKnowledgeBase = function() {
    this.knowledgeBase = [];
  };
  /**
   * 构建知识库map
   * @param knowledgeBase
   */
  Cache.prototype.makeKnowledgeBaseMap = function (knowledgeBase) {
    let result = new Map();
    for (let i = 0; i < knowledgeBase.length; i++) {
      let map = result;
      let sw = knowledgeBase[i];
      for(let j = 0; j < sw.length; j++) {
        let word = sw.charAt(j);
        if (map.hasOwnProperty(word)) {
          map = map[word];
        } else {
          if (map["laster"] == true) {
            map["laster"] = false;
          }
          let tempMap = new Map();
          tempMap["laster"] = true;
          map[word] = tempMap;
          map = map[word];
        }
      }
    }
    this.knowledgeBaseMap = result;
  };
  /**
   * 检查敏感词是否存在
   */
  Cache.prototype.checkKnowledgeBase = function(text, index) {
    let currentMap = this.knowledgeBaseMap;
    let flag = false;
    let wordNum = 0;
    let knowledgeBaseWd = '';
    for (let i = index; i < text.length; i++) {
      let word = text.charAt(i);
      currentMap = currentMap[word];
      if (currentMap != null) {
        wordNum++;
        knowledgeBaseWd += word;
        if (currentMap["laster"] == true) {
          flag = true;
          break;
        }
      } else {
        break;
      }
    }
    if (wordNum < 2) {
      flag = false;
    }
    return {flag, knowledgeBaseWd};
  };
  /**
   * 判断文本是否存在敏感词
   * @param text
   */
  Cache.prototype.filterKnowledgeBaseWord = function (text) {
    let matchResult = {flag: false, knowledgeBaseWd: ''};
    let textTrim = text.replace(/[^\u4e00-\u9fa5\u0030-\u0039\u0061-\u007a\u0041-\u005a]+/g, '');
    for (let i = 0; i < textTrim.length; i++) {
      matchResult = this.checkKnowledgeBase(textTrim, i);
      if (matchResult.flag) {
        console.log("输入内容包含知识库："+matchResult.knowledgeBaseWd);
        break;
      }
    }
    return matchResult;
  };
  /** 知识库 */
  return Cache;
})();
