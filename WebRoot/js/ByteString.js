function ByteString() {

	//根据子iframe id，得到此子iframe的document。
	//fatherWindow 父iframe 的window对象。
	//iframeId 子iframe对象id
	//例如从当前子iframe得到另外一个子iframe id为'top'的对象,
	// getIframeDoc(window.parent,'top')。
	//目前已经测试兼容 IE firefox google
	this.getIframeDoc=function(fatherWindow,iframeId){
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
	
	//返回IE浏览器版本 ，如:6 7 8 9 10。
	//不是IE浏览器时返回-1 
	this.getIEVersion=function(){
		var agent=navigator.userAgent;
		if(agent.indexOf("MSIE")>0)  
		{   
			agent=agent.substr(agent.indexOf('MSIE ')+5);
			return agent.substr(0,agent.indexOf('.'));
		}else  
		{  
			//不是IE浏览器
			return "-1";
		}  
	};
	
	//用来清除input里面的value。
	//当obj里面的value和objValue相同时，清除obj的值
	this.clearValue=function(obj,objValue){
		if(obj.value==objValue){
			obj.value="";
		}
	};
	//判断时间是否符合格式 12:23:32 符合返回true，不符合返回false
	this.CheckTime=function(str){
		var timeFormat = /^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/
		return timeFormat.test(str) ? true : false ;
	};
	//返回时间年月日 格式：2010-03-09
	this.GetShortDate=function(myDate){
		//对于日月不够10的，前面加0
		var myMonth=myDate.getMonth()+1;
		if(parseInt(myMonth)<10){
			myMonth='0'+myMonth;
		}
		var myDateDay=myDate.getDate();
		if(parseInt(myDateDay)<10){
			myDateDay='0'+myDateDay;
		}
		return myDate.getFullYear()+'-'+myMonth+'-'+myDateDay;	
	};
	
	//用于ajax更新一个容器
	//requestUrl 请求后台的url，divId 将要更新的div id
	this.Updater=function(requestUrl,divId){
		//判断div是否存在
		if($(divId)==null){
			alert('容器不存在！');
			return false ;
		}
		$(divId).innerHTML='正在更新...';
		//创建ajax对象
		var xmlHttp;
		try
		{
		   	// Firefox, Opera 8.0+, Safari
		    xmlHttp=new XMLHttpRequest();
		}
		catch(e)
		{
		  // Internet Explorer
		  try
	      {
	      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	      }
		  catch (e)
		  {
		      try
		         {
		         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		         }
		      catch (e)
		         {
		         alert("您的浏览器不支持AJAX！");
		         return false;
		         }
		   }
		}
	    xmlHttp.onreadystatechange=function()
	      {
	      if(xmlHttp.readyState==4)
	        {
	         $(divId).innerHTML=xmlHttp.responseText;
	        }
	      }
	    xmlHttp.open("GET",requestUrl,true);
	    xmlHttp.send(null);
	};
	
	//用于ajax读取后调用一个函数
	//requestUrl 请求后台的url，funName 完成后调用的函数名称
	this.Requester=function(requestUrl,funName){
		//funName是否是函数
		if(typeof funName !== 'function'){
			alert('函数'+funName+'不存在！');
			return false ;
		}
		//创建ajax对象
		var xmlHttp;
		try
		{
		   	// Firefox, Opera 8.0+, Safari
		    xmlHttp=new XMLHttpRequest();
		}
		catch(e)
		{
		  // Internet Explorer
		  try
	      {
	      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	      }
		  catch (e)
		  {
		      try
		         {
		         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		         }
		      catch (e)
		         {
		         alert("您的浏览器不支持AJAX！");
		         return false;
		         }
		   }
		}
	    xmlHttp.onreadystatechange=function()
	      {
	      if(xmlHttp.readyState==4)
	        {
	         funName(xmlHttp.responseText);
	        }
	      }
	    xmlHttp.open("GET",requestUrl,true);
	    xmlHttp.send(null);
	};
	
	//相当于document.getElementById() objId 
	this.$=function(objId){
		return document.getElementById(objId);
	};
	//计算地球两点之间距离的辅助函数
	this.rad=function(d){
		 return d * Math.PI / 180.0;
	};
	
	//计算两个经纬度之间的距离 多少km
	//latOne 第一个点纬度,lonOne 第一个点经度,latTwo 第二个点纬度,lonTwo 第二个点经度
	this.GetDistance=function(latOne,lonOne,latTwo,lonTwo){
		 //地球半径 6378.137 公里
		var EARTH_RADIUS = 6378.137;
		var radLat1 = this.rad(latOne);
	   var radLat2 = this.rad(latTwo);
	   var a = radLat1 - radLat2;
	   var b = this.rad(lonOne) - this.rad(lonTwo);
	   var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
	   Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;
	   return s;
	};
	
	//读取cookie
	//cookieName cookie属性名称
	//如果存在这个cookie返回这个cookie，否则返回"-1"
	this.getCookie = function(cookieName){
		var cookieStr = document.cookie; //取 cookie 字符串
		if (cookieStr == ""){
		return "-1"; //没有取到 cookie 字符串，返回默认值
		} 
		//alert(cookieStr);
		//将各个 cookie 分隔开，并存为数组，多个 cookie 之间用分号加空隔隔开
		var cookieValue = cookieStr.split("; ");
		for (var i=0; i<cookieValue.length; i++){
			var cookieAll=cookieValue[i];
			var cookieTempName=cookieAll.substring(0,cookieAll.indexOf('='));
			//alert(cookieTempName);
			if(cookieName==cookieTempName){
				return cookieAll.substring(cookieAll.indexOf('=')+1);
			}
		}
		return "-1";	 
	};
	//放入一个到2020年过期的cookie
	//cookieName cookie名称
	//cookieValue cookie值
	//如果客户端cookie不可写 返回FALSE
	//如果正确写入了cookie返回TRUE
	this.addCookie = function(cookieName,cookieValue) {
		var the_date = new Date("December 31, 2020");
		var expiresDate = the_date.toGMTString();
		document.cookie = cookieName+"=" + escape(cookieValue) + "; expires=" + expiresDate; 
		return true ;
	};
	//检查客户端cookie是否打开，如果打开返回TRUE 否则返回FALSE
	this.getCookieStatus=function() {
	    var status =false;
	    var cookieStr = "wb_check=kcehc_bw";
	    document.cookie = cookieStr;
	    if (document.cookie.indexOf(cookieStr) > -1) {
	        status = true;
	        var date = new Date();
	        date.setTime(date.getTime() - 1000);
	        document.cookie = cookieStr + "; expires=" + date.toGMTString();
	    }
	    return status;
	};
	
	//判断是不是六位数字，用来判断是不是为手机号码密码。
	this.checkPhonePwd = function(str) {
		if (/^\d{6}$/.test(str))
			return true;
		else
			return false;
	};
	
	//判断是不是为正整数
	this.checkThanZero = function(str) {
		if (/^[0-9]*[1-9][0-9]*$/.test(str))
			return true;
		else
			return false;
	};
	// 对字符串进行trim
	this.trim = function(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	};
	/**
	 * 判断字符状态，如果字符是数字返回1，大写字母返回2，小写字母返回3，特殊字符返回4
	 */
	this.charMode = function(iN) {
		if (iN >= 48 && iN <= 57) // 数字
			return 1;
		if (iN >= 65 && iN <= 90) // 大写字母
			return 2;
		if (iN >= 97 && iN <= 122) // 小写
			return 3;
		else
			return 4; // 特殊字符
	};
	/**
	 * 计算字符串的字符数，注：一个中文为两个字符
	 */
	this.getStrLen = function(Obj) {
		var nCNLenth = 0;
		var nLenth = Obj.length;
		for ( var i = 0; i < nLenth; i++) {
			if (Obj.charCodeAt(i) > 255) {
				nCNLenth += 2;
			} else {
				nCNLenth++;
			}
		}
		return nCNLenth;
	};
	/**
	 * 判断是不是为数字，如果mun,max都为数字，num <= max，并且num > 0返回TRUE，否则返回FALSE
	 */
	this.checkNumber = function(num, max) {
		if (/^[0-9]+$/.test(num) && num <= max && num > 0)
			return true;
		else
			return false;
	};
	// 判断是否为数字和字母组合的一种或者两种 如果出现除数字字母外的其余字符返回false 否则返回true
	this.checkEnglishAndNumber = function(str) {
		if (/^[A-Za-z0-9]+$/.test(str))
			return true;
		else
			return false;
	};
	/**
	 * 是否为联通G网用户 是返回 true 否则返回 false
	 */
	this.isGNet = function(str) {
		if (str.length != 11)
			return false;
		if (/^13[0-2][0-9]{8}|15[5-6][0-9]{8}|18[6][0-9]{8}$/.test(str))
			return true;
		else
			return false;
	};
	// 判断是字符串是不是整数，是返回true 否则返回false
	this.checkInteger = function(str) {
		if (/^-?\d+$/.test(str))
			return true;
		else
			return false;
	};
	// 判断字符串是否为Email地址，是返回true 否则返回false
	this.checkEmail = function(str) {
		if (/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str))
			return true;
		else
			return false;
	};
	/**
	 * 判断是否为1901（含1901）到现在的某一年，是返回TRUE，否则返回FALSE
	 */
	this.checkYear = function(year) {
		if (year == "")
			return false;
		if (!this.checkNumber(year, new Date().getFullYear())
				|| parseInt(year) < 1901)
			return false;
		return true;
	};
	/**
	 * 判断是否为1-12的某一月，是返回TRUE，否则返回FALSE
	 */
	this.checkMonth = function(month) {
		if (month == "")
			return false;
		if (!this.checkNumber(month, 12))
			return false;
		return true;
	};
	/**
	 * 检查从1901 到今年的某天是否存在（如果是今年的某天不一定已经过去） 存在返回FALSE 不存在返回TRUE
	 */
	this.checkDay = function(year, month, day) {
		if (!this.checkYear(year)) {
			return false;
		}
		if (!this.checkMonth(month)) {
			return false;
		}
		var maxday = 31;
		switch (parseInt(month)) {
		case 1:
			maxday = 31
			break
		case 2:
			if (isLeapYear(year))
				maxday = 29
			else
				maxday = 28
			break
		case 3:
			maxday = 31
			break
		case 4:
			maxday = 30
			break
		case 5:
			maxday = 31
			break
		case 6:
			maxday = 30
			break
		case 7:
			maxday = 31
			break
		case 8:
			maxday = 31
			break
		case 9:
			maxday = 30
			break
		case 10:
			maxday = 31
			break
		case 11:
			maxday = 30
			break
		case 12:
			maxday = 31
			break
		default:
			maxday = 31;
		}
		if (!this.checkNumber(day, maxday))
			return false;
		return true;
	};
	/**
	 * 判断是否为手机号码
	 */
	this.checkMobile = function(mobile) {
		if (mobile == "")
			return false;
		if (/^13\d{9}$/.test(mobile) | /^15\d{9}$/.test(mobile)
				| /^18\d{9}$/.test(mobile) )
			return true;
		return false;
	};
	/**
	 * 判断是否为G网手机，即联通非CDMA手机号码
	 */
	this.isGNet = function(mobile) {
		if (mobile == "")
			return false;
		if (/^13[0-2][0-9]{8}|15[5-6][0-9]{8}|18[6][0-9]{8}$/.test(mobile))
			return true;
		return false;
	};
	/**
	 * 判断是否为C网手机，CDMA手机号码
	 */
	this.isCNet = function(mobile) {
		if (mobile == "")
			return false;
		if (/^13[3][0-9]{8}|15[3][0-9]{8}|18[9][0-9]{8}|18[0][0-9]{8}$/.test(mobile))
			return true;
		return false;
	};
	/**
	 * 判断是否为移动号码
	 */
	this.isMobileNet = function(mobile) {
		if (mobile == "")
			return false;
		if (/^13[4-9][0-9]{8}|178[0-9][0-9]{8}||15[0124789][0-9]{8}|18[23487][0-9]{8}|14[7][0-9]{8}$/.test(mobile)){
			return true;
		}
			
		
		return this.isNumberAttr(mobile,'10086');
	};
	
	/**
	 * 三网号码归属地判断
	 * 
	 */
	this.isNumberAttr = function(mobile,type) {
		if (mobile == "")
			return false;
		var ff = false;

		if(type == '10086'){
			$.ajax({
				type: "POST",    
				async:false,
				url: "../common/ajaxGetYunyings?phoneNumber="+mobile+"&type="+type,
				dataType: "text",
				complete:function(XMLHttpRequest, textStatus){
					var vt = bs.trim(XMLHttpRequest.responseText);
					//alert(vt);
					if(vt.charAt(0) == '1'){
						ff = true;
					}else{
						ff = false;
					}
			}});
			if(ff){ // 检查到号码段  不再继续检查归属地
				return ff;
			}
		}
		
		
		
		$.ajax({
			type: "POST",    
			async:false,
			url: "../common/ajaxNumberAttri?phoneNumber="+mobile+"&type="+type,
			dataType: "text",
			complete:function(XMLHttpRequest, textStatus){
				var vt = bs.trim(XMLHttpRequest.responseText);
				//alert(vt);
				if(vt.charAt(0) == 1){
					ff = true;
				}else{
					ff = false;
				}
		}});
		return ff;
	};
	
	// 判断闰年
	this.isLeapYear = function(year) {
		return (0 == year % 4 && ((year % 100 != 0) || (year % 400 == 0)));
	};
	//检查str是否包含一些特殊字符,包含返回false，不包含返回true
	this.checkUnUseLetter=function(str){
		var unUserLetters=new Array('^',',',';','!','<','>','-');
		for(var i=0 ; i<unUserLetters.length ; i+=1){
			//如果包含，返回false
			if(-1!=str.indexOf(unUserLetters[i])){
				return false ;
			}
		}
		//都不包含，返回true
		return true ;
	}
	//使Id为objId的下拉框中option值为optionVal的项选中，如果没有这一项，不对select进行任何操作
	this.makeOptionSelected=function(objId,optionVal)
	{
		var sel=document.getElementById(objId);
		for(var i=0;i<sel.length;i++)   
		{
			if(sel.options[i].value==optionVal)
			{
				sel.options[i].selected="selected";
				break ;
			}
		}
	};
	//星期几 将1转换为一，7转换为日
	this.converNumToWord=function(num)
	{
		switch (parseInt(num)) {
		case 1:
			return '一';
		case 2:
			return '二';
		case 3:
			return '三';
		case 4:
			return '四';
		case 5:
			return '五';
		case 6:
			return '六';
		case 7:
			return '日';
		default:
			return '一';
		}
	};
}