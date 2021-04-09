/**
 * 获取部门下拉列表的js文件
 */
// 步骤一： 创建浏览器的操作对象
var xmlhttp; // 全局变量
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}

// 步骤二：js 的触发函数
function check() {
	// 获取浏览器的操作对象
	xmlhttp = createXMLHttpRequest();
	// 开始浏览器操作设置
	//onreadystatechange判断浏览器状态值和http请求值   callBackFunc回调函数
	xmlhttp.onreadystatechange = callBackFunc;// 此处，函数名称不写()

	//请求路径
	url="DeptServlet?act=getAllDept";	
	xmlhttp.open("post", url, true);//true异步加载    open设置参数
	xmlhttp.send();//send发送参数
}

// 步骤三：回调函数，当浏览器状态值发生改变的时候，触发的函数
function callBackFunc() {
	//readyState状态值  status响应值
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		/*alert("可以干事业了！！！！！！！！！！！！！！");*/
		// 步骤3-1 ：接受json文件
		var ss = xmlhttp.responseText;// responseXML 是接受xml 文件的格式的，一般用不到
		// 步骤3-2 ：解析接收的json数据格式,解析是固定格式
		//如果传递的是普通的字符串，不需要解析，如果后台传递的是对象，就需要解析
		/*var strObj = eval("(" + ss + ")");*/
		/*alert(strObj);*/	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		//将数据想尽一切办法，放入到select 中去
		var dept = document.getElementById("getDept");//获取到了<select>标签
		//拆分字符串
		var str = ss.split("!");//使用字符串的split 方法，指定! 作为字符串数字的拆分符号
//		alert(str);
		for(var i =0;i<str.length-1;i++){
			/*alert(i);*/
			//dom 创建option 标签
			var option = document.createElement("option");//<option></option>
			//将option 标签，添加到 select 下
			dept.options.add(option);//<select><option></option></select>
			var sa = str[i].split(",");
			option.value=sa[0];
			option.innerHTML=sa[1];
		}
	}
}
//-------------第一个 下拉菜单结束-------------------------

//第一个下拉选择的触发函数
function getDeptInfo(){
	//获取下拉选择的部门的ID
	var deptValue = document.getElementById("getDept").value;
//	alert(deptValue);
	//使用ajax 向后台传递，根据部门编号，找到部门下的人员信息
	// 获取浏览器的操作对象
	xmlhttp = createXMLHttpRequest();
	// 开始浏览器操作设置
	xmlhttp.onreadystatechange = callBackFunc2;// 此处，需要换一个函数名，因为不能与上面的函数名重复

	//请求路径
	url="DeptServlet?act=getEmpByDid&did="+deptValue;
	
	xmlhttp.open("post", url, true);
	xmlhttp.send();
}

function callBackFunc2(){
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//		alert("可以开始你的大业了！！！！！");
		//接收json 数据
		var ss= xmlhttp.responseText;
		//将数据想尽一切办法，放入到select 中去
		var emp = document.getElementById("empInfo");//获取到了<select>标签
		clearElement("empInfo");
		//拆分字符串
		var str = ss.split("!");//使用字符串的split 方法，指定! 作为字符串数字的拆分符号
//		alert(str);
		for(var i =0;i<str.length-1;i++){
			/*alert(i);*/
			//dom 创建option 标签
			var option = document.createElement("option");//<option></option>
			//将option 标签，添加到 select 下
			emp.options.add(option);//<select><option></option></select>
			var sa = str[i].split(",");
			option.value=sa[0];
			option.innerHTML=sa[1];
		}
	}
}
//清除指定id下的内容，以dom子节点的方式移除掉
function clearElement(element) {
	var ele=document.getElementById(element);//获取指定的id
	if (ele.childNodes.length>0) {
		while (ele.childNodes.length>0) {
			ele.removeChild(ele.childNodes[0]);
		}
	} 
}











