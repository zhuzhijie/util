//V0.1 js工具类
//朱志杰 QQ:695520848

function Util(){};

/**根据子iframe id，得到此子iframe的document。
*fatherWindow 父iframe 的window对象。
*iframeId 子iframe对象id
*例如从当前子iframe得到另外一个子iframe id为'top'的对象,
*getIframeDoc(window.parent,'top')。
*目前已经测试兼容 IE firefox google
*/
Util.getIframeDoc = function(fatherWindow,iframeId){
	if(navigator.appName == "Microsoft Internet Explorer"){
		if(parseInt(this.getIEVersion())<9){
			return fatherWindow.frames(iframeId).document;
		}else{
			var docu=fatherWindow.document.getElementById(iframeId).contentDocument;
			if(typeof(docu)!= "undefined"){
				return docu;
			}else{
				//目前已知为绿色浏览器。
				return fatherWindow.frames(iframeId).document;
			}
		}
	}
	return fatherWindow.document.getElementById(iframeId).contentDocument || fatherWindow.document.frames[iframeId].document;
};

/**
 *校验字符串是否为空
 * @param str 是否传
 * @returns boolean 为null或者trim后为空字符串返回false，否则true。
 */
Util.isEmpty = function(str) {
	if (null == str || "" == str.trim()) {
		return true;
	} else {
		return false;
	}
};

/**
 * 检查表单是否有修改
 * @param formId 表单id
 * @param exceptObjArray 不校验的控件id数组。
 * @returns boolean 表单不存在或者没发生变化false，否则true。
 */
Util.isFormChanged = function(formId,exceptObjArray) {
	var fm=document.getElementById(formId);
	if(fm==null){
		return false;
	}
	//首先检查select
	var selectObjs=fm.getElementsByTagName("SELECT");
	for(var i=0;i<selectObjs.length;i++)
	{
		//判断是否为不校验
		if(selectObjs[i].id.isInArray(exceptObjArray)){
			continue;
		}
		for(var j=1;j<selectObjs[i].length;j++)
		{
			if(selectObjs[i].options[j].defaultSelected!=selectObjs[i].options[j].selected){
				return true;
			}
		}
	}
	//input校验
	var inputObjs=fm.getElementsByTagName("INPUT");
	for(var i=0;i<inputObjs.length;i++)
	{
		//判断是否为不校验
		if(inputObjs[i].id.isInArray(exceptObjArray)){
			continue;
		}
		if((inputObjs[i].type.toUpperCase()=="TEXT")&&(inputObjs[i].defaultValue!=inputObjs[i].value)){
			return true;
		} else if(((inputObjs[i].type.toUpperCase()=="RADIO")||(inputObjs[i].type.toUpperCase()=="CHECKBOX"))
				&&(inputObjs[i].defaultChecked!=inputObjs[i].checked)) {
			return true;
		}
	}

	var textareaObjs=fm.getElementsByTagName("TEXTAREA");//For Textarea Obj
	for(var i=0;i<textareaObjs.length;i++)
	{
		//判断是否为不校验
		if(textareaObjs[i].id.isInArray(exceptObjArray)){
			continue;
		}
		if(textareaObjs[i].defaultValue!=textareaObjs[i].value){
			return true;
		}
	}
	return false;
};

/**
 *	校验是否全是数字
 * @param str 是否传
 * @returns boolean 是true，否则false。
 */
Util.isDigit = function(str) {
	var patrn = /^\d+$/;
	return patrn.test(str);
};

/**
 *校验是否是整数
 * @param str 是否传
 * @returns boolean 是true，否则false。
 */
Util.isInteger = function(str) {
	var patrn = /^([+-]?)(\d+)$/;
	return patrn.test(str);
};

/**
 *	校验是否为正整数
 * @param str 是否传
 * @returns boolean 是true，否则false。
 */
Util.isPlusInteger = function(str) {
	var patrn = /^([+]?)(\d+)$/;
	return patrn.test(str);
};

/**
 *	校验是否为负整数
 * @param str 是否传
 * @returns boolean 是true，否则false。
 */
Util.isMinusInteger = function(str) {
	var patrn = /^-(\d+)$/;
	return patrn.test(str);
};

/**
 *	校验是否为浮点数
 * @param str 是否传
 * @returns boolean 是true，否则false。
 */
Util.isFloat = function(str) {
	var patrn = /^([+-]?)\d*\.\d+$/;
	return patrn.test(str);
};

/**
 *	校验是否为正浮点数
 * @param str 是否传
 * @returns boolean 是true，否则false。
 */
Util.isPlusFloat = function(str) {
	var patrn = /^([+]?)\d*\.\d+$/;
	return patrn.test(str);
};

/**
 *	校验是否为负浮点数
 * @param str 是否传
 * @returns boolean 是true，否则false。
 */
Util.isMinusFloat = function(str) {
	var patrn = /^-\d*\.\d+$/;
	return patrn.test(str);
};

/**
 *	校验是否仅中文
 * @param str 是否传
 * @returns boolean 是true，否则false。
 */
Util.isChinese = function(str) {
	var patrn = /[\u4E00-\u9FA5\uF900-\uFA2D]+$/;
	return patrn.test(str);
};

/**
 *	校验是否仅ACSII字符
 * @param str 是否传
 * @returns boolean 是true，否则false。
 */
Util.isAcsii = function(str) {
	var patrn = /^[\x00-\xFF]+$/;
	return patrn.test(str);
};

/* 以下为对Date进行扩展 */

/**
 * 功能：对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2
 * 个占位符， 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 例子： (new
 * Date()).toString("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 (new
 * Date()).toString("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 *
 * @param fmt
 *            格式化
 * @returns String
 */
Date.prototype.toString = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
		// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
	if (new RegExp("(" + k + ")").test(fmt))
		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};

/**
 * 功能：判断日期是否和当前date对象在同一天。
 *
 * @author 朱志杰 QQ：862990787
 * @param oneDate
 *            比较的日期
 * @returns boolean 如果在返回true，否则返回false。
 */
Date.prototype.isSameDay = function(oneDate) {
	if ( typeof (oneDate) == "undefined" || !( oneDate instanceof Date)) {
		return false;
	}
	var my = this.toString("yyyy-MM-dd");
	var one = oneDate.toString("yyyy-MM-dd");

	if (my == one) {
		return true;
	} else {
		return false;
	}
};

/**
 * 功能：得到当月有多少天。
 *
 * @author 朱志杰 QQ：862990787 Jul 2, 2013 4:59:41 PM
 * @returns int
 */
Date.prototype.daysNumOfMonth = function() {
	return new Date(this.getFullYear(), this.getMonth() + 1, 0).getDate();
};

/**
 * 时间间隙类,用来计算两个时间差
 */
function Timespan() {
	// 对应的毫秒数
	this.time = 0;

	/**
	 * 间隙为多少天
	 */
	this.totalDays = function() {
		return Math.floor(this.totalHours() / 24);
	};

	/**
	 * 间隙为多少小时
	 */
	this.totalHours = function() {
		return Math.floor(this.totalMinutes() / 60);
	};

	/**
	 * 间隙为多少分钟
	 */
	this.totalMinutes = function() {
		return Math.floor(this.totalSeconds() / 60);
	};

	/**
	 * 间隙为多少秒
	 */
	this.totalSeconds = function() {
		return Math.floor(this.time / 1000);
	};

	/**
	 * 间隙为多少毫秒
	 */
	this.totalMilliseconds = function() {
		return this.time;
	};

}

/**
 * 功能：得到两个时间的差。如果传过来的不是Date对象，直接返回null。
 *
 * @author 朱志杰 QQ：862990787 Jul 2, 2013 4:59:41 PM
 * @param date
 *            另一个日期
 * @returns Timespan
 */
Date.prototype.substract = function(date) {
	if ( typeof (oneDate) == "undefined" || !( oneDate instanceof Date)) {
		return null;
	}
	var timespan = new Timespan();
	timespan.time = this.getTime() - date.getTime();
	return timespan;
};

/**
 * 功能：得到当天的第一秒的时间。
 *
 * @author 朱志杰 QQ：862990787 May 29, 2013 11:26:27 AM
 * @returns Date
 */
Date.prototype.dayStart = function() {
	var newDate = new Date(this.getTime());
	newDate.setHours(0);
	newDate.setMinutes(0);
	newDate.setSeconds(0);
	newDate.setMilliseconds(0);
	return newDate;
};

/**
 * 功能：得到当月的第一秒的时间。
 *
 * @author 朱志杰 QQ：862990787 May 29, 2013 11:26:27 AM
 * @returns Date
 */
Date.prototype.monthStart = function() {
	var newDate = this.dayStart();
	newDate.setDate(1);
	return newDate;
};

/**
 * 功能：得到当月的最后一秒的时间。
 *
 * @author 朱志杰 QQ：862990787 May 29, 2013 11:26:27 AM
 * @returns Date
 */
Date.prototype.monthEnd = function() {
	var newDate = this.dayEnd();
	newDate.setDate(this.daysNumOfMonth());
	return newDate;
};

/**
 * 功能：得到当天的最后一秒的时间。
 *
 * @author 朱志杰 QQ：862990787 May 29, 2013 11:26:27 AM
 * @returns Date
 */
Date.prototype.dayEnd = function() {
	var newDate = new Date(this.getTime());
	newDate.setHours(23);
	newDate.setMinutes(59);
	newDate.setSeconds(59);
	return newDate;
};

/**
 * 功能：将日期转换成长日期字符串 例如：2009-09-09 01:01:01
 *
 * @author 朱志杰 QQ：862990787 May 29, 2013 11:26:27 AM
 */
Date.prototype.toLongDate = function() {
	return this.toString("yyyy-MM-dd hh:mm:ss");
};

/**
 * 功能：得到某个时间的时间戳yyyyMMddHHmmss。
 *
 * @author 朱志杰 QQ：862990787 May 29, 2013 11:26:27 AM
 */
Date.prototype.toTimeStamp = function() {
	return this.toString("yyyyMMddhhmmss");
};

/**
 * 功能：将日期转换成短日期字符串,例如：2009-09-09。
 *
 * @author 朱志杰 QQ：862990787 May 29, 2013 11:26:27 AM
 */
Date.prototype.toShortDate = function() {
	return this.toString("yyyy-MM-dd");
};

/* 以下为字符串扩展 */
//
/**
 * 功能：查看该字符串是否是数字串
 *
 * @author 朱志杰 QQ：862990787
 * @returns 是返回true，否则false
 */
String.prototype.isAllNumber = function() {
	for (var i = 0; i < this.length; i++) {
		if (this.charAt(i) < '0' || this.charAt(i) > '9') {
			return false;
		}
	}
	return true;
};

/**
 * 校验此字符串的值是否在strArray中存在
 * @param strArray 字符串数组。
 * @returns {Boolean} 存在true，否则false
 */
String.prototype.isInArray = function(strArray) {
	if(strArray==null || strArray.length==0){
		return false;
	}
	for (var i = 0; i < strArray.length; i++) {
		if(this==strArray[i]){
			return true;
		}
	}
	return false;
};

/**
 * 功能：将该字符串反序排列
 *
 * @author 朱志杰 QQ：862990787
 * @returns String
 */
String.prototype.reverse = function() {
	var aStr = "";
	for (var i = this.length - 1; i >= 0; i--) {
		aStr = aStr.concat(this.charAt(i));
	}
	return aStr;
};

/**
 * 功能：去掉字符串两侧的空格
 *
 * @author 朱志杰 QQ：862990787
 * @returns String
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * 功能：检查该字符串是否以某个字符串开始
 *
 * @param aStr
 *            开始字符串
 * @author 朱志杰 QQ：862990787
 * @returns 是返回true，否则false
 */
String.prototype.startsWith = function(aStr) {
	if (aStr.length > this.length) {
		return false;
	}
	return (this.indexOf(aStr) == 0) ? true : false;
};

/**
 * 功能：将指定的位置的字符设置为另外指定的字符或字符串.索引无效将直接返回不做任何处理！
 *
 * @param sIndex
 *            开始索引
 * @param aStr
 *            替换字符串
 * @author 朱志杰 QQ：862990787
 * @returns String
 */
String.prototype.setCharAt = function(sIndex, aStr) {
	if (sIndex < 0 || sIndex > this.length - 1) {
		return this.valueOf();
	}
	return this.substring(0, sIndex) + aStr + this.substring(sIndex + 1);
};

/**
 * 功能：将指定的字符串插入到指定的位置后面!索引无效将直接追加到字符串的末尾.
 *
 * @param ofset
 *            偏移量
 * @param aStr
 *            被插入的字符串
 * @author 朱志杰 QQ：862990787
 * @returns String
 */
String.prototype.insert = function(ofset, aStr) {
	if (ofset < 0 || ofset >= this.length - 1) {
		return this.append(aStr);
	}
	return this.substring(0, ofset + 1) + aStr + this.substring(ofset + 1);
};

/**
 * 功能：比较两个字符串是否相等，不区分大小写!
 *
 * @param aStr
 *            比较字符串
 * @author 朱志杰 QQ：862990787
 * @returns 相同返回true，否则false
 */
String.prototype.equalsIgnoreCase = function(aStr) {
	if (this.length != aStr.length) {
		return false;
	} else {
		var tmp1 = this.toLowerCase();
		var tmp2 = aStr.toLowerCase();
		return tmp1.equals(tmp2);
	}
};

/**
 * 功能：检查字符串是否以某个字符串(aStr)结尾
 *
 * @param aStr
 *            结尾字符串
 * @author 朱志杰 QQ：862990787
 * @returns 是返回true，否则false
 */
String.prototype.endsWith = function(aStr) {
	if (aStr.length > this.length)
		return false;
	return (this.lastIndexOf(aStr) == (this.length - aStr.length)) ? true : false;
};

/**
 * 功能：删除指定索引间的字符串.sIndex和eIndex所在的字符不被删除！
 *
 * @param sIndex
 *            开始索引
 * @param eIndex
 *            结束索引
 * @author 朱志杰 QQ：862990787
 * @returns String
 */
String.prototype.deleteString = function(sIndex, eIndex) {
	if (sIndex == eIndex) {
		return this.deleteCharAt(sIndex);
	} else {
		if (sIndex > eIndex) {
			var tIndex = eIndex;
			eIndex = sIndex;
			sIndex = tIndex;
		}
		if (sIndex < 0)
			sIndex = 0;
		if (eIndex > this.length - 1)
			eIndex = this.length - 1;
		return this.substring(0, sIndex + 1) + this.substring(eIndex, this.length);
	}
};

/**
 * 功能：删除指定索引位置的字符，索引无效将不删除任何字符。
 *
 * @param sIndex
 *            开始索引
 * @author 朱志杰 QQ：862990787
 * @returns String
 */
String.prototype.deleteCharAt = function(sIndex) {
	if (sIndex < 0 || sIndex >= this.length) {
		return this.valueOf();
	} else if (sIndex == 0) {
		return this.substring(1, this.length);
	} else if (sIndex == this.length - 1) {
		return this.substring(0, this.length - 1);
	} else {
		return this.substring(0, sIndex) + this.substring(sIndex + 1);
	}
};

/**
 * 功能：判断对象是否在数组中存在。
 * @param obj 对象
 * @author 朱志杰 QQ：862990787
 * @returns int 在数组中的序号，如果不存在返回-1
 */
Array.prototype.indexOf = function(obj) {
	for(var i=0 ; i<this.length ; i++){
		if(this[i]==obj){
			return i;
		}
	}
	return -1;
};
