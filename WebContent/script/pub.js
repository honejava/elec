function Pub(){ } 

Pub.submit=function(formindex) {
			
  				document.forms[formindex].submit();
  				
}

Pub.formsubmitAction=function(formindex,action) {
            
			document.forms[formindex].action=action;
  				document.forms[formindex].submit();
  				
}


Pub.submitform=function(formname){
   

   var theform =document.forms[formname];
   theform.submit();
}

Pub.reset=function(formindex){
				document.forms[formindex].reset();
}


// //////////////////////////////////////////////////////////////////////////////
Pub.checkAll=function(parent, itemName)
{
  
  var parentbox = document.getElementsByName(itemName);
  
  for (var i=0; i<parentbox.length; i++){
  
  
   parentbox[i].checked = parent.checked;
   
   }
}
Pub.checkItem=function (child, allName)
{
  var all = document.getElementsByName(allName)[0];
  if(!child.checked) all.checked = false;
  else
  {
    var childbox = document.getElementsByName(child.name);
    for (var i=0; i<childbox.length; i++)
     if(!childbox[i].checked) return;
    all.checked = true;
  }
}

Pub.getAllCheckItemValue=function(storename,removevalue){


var obj_all = document.getElementsByTagName("INPUT");
var all_value="";
for(i=0;i<obj_all.length;i++){
if(obj_all[i].type=="checkbox")
   if(obj_all[i].checked ){
      if(obj_all[i].value!=removevalue)
      all_value=all_value+obj_all[i].value+",";
   }
      
   
}
var obj = document.getElementById(storename);
obj.value=all_value;

}

Pub.getAllCheckItemValueWithRemove=function(storename,removevalue){


var obj_all = document.getElementsByTagName("INPUT");
var all_value="";
for(i=0;i<obj_all.length;i++){
if(obj_all[i].type=="checkbox")
   if(obj_all[i].checked ){
      if(obj_all[i].value.indexOf("NULL")==-1){ 
      if(obj_all[i].value!=removevalue)
      all_value=all_value+obj_all[i].value+",";
   }
      }
   
}
var obj = document.getElementById(storename);
obj.value=all_value;

}

// /////////////////////////////////////////////////////////////////////////////////////////////////////
Pub.copyRole=function(paramname,action){

var obj_all = document.getElementsByTagName("INPUT");
var value="";
for(i=0;i<obj_all.length;i++){
if(obj_all[i].type=="radio")
   if(obj_all[i].checked ){
       
        value = obj_all[i].value;
      }
   
}

 if(value==""){
 return false;}
  document.forms[0].action=action+"?"+paramname+"="+value;
  document.forms[0].submit();

}
// /////////////////////////////////////////////////////////////////////////////////////////////////////

// //////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * 创建Ajax核心对象，ajax引擎XMLHttpRequest对象
 */
Pub.newXMLHttpRequest=function newXMLHttpRequest() {
  var xmlreq = false;
  if (window.XMLHttpRequest) {
    xmlreq = new XMLHttpRequest();
  } else if (window.ActiveXObject) {
     
    try {
      
      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e1) {
       
      try {
      
        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
          
        alert(e2);
      }
    }
  }
  return xmlreq;
}



/**
 * 
 * @param req：ajax引擎
 * @param eleid：表单Form2的名称
 * @param responseXmlHandler：Pub.handleResponse函数
 * @returns {Function}
 */
Pub.getReadyStateHandler =function getReadyStateHandler(req, eleid,responseXmlHandler) {
  
  return function () {
    /**
	 * req.readyState表示客户端和服务器端的连接状态 req.readyState==0：ajax完成调用，初始状态
	 * req.readyState==1：open方法调用，send方法没有调用 req.readyState==2：send方法调用完成
	 * req.readyState==3：服务器开始向客户端响应数据
	 * req.readyState==4：响应完成，只有req.readyState状态等于4，客户端才能获取服务器端响应的结果
	 */
    if (req.readyState == 4) {
       /**
		 * req.status:状态码 404：路径问题 500：服务器异常 200：正常，只用正常状态，客户端才能获取服务器端响应的结果
		 */
      if (req.status == 200) {
          /**
			 * 服务器端响应的结果 req.responseText：文本（json格式） req.responseXML：XML数据格式
			 */
        responseXmlHandler(req.responseText,eleid);
 
      } else {
        
        alert("HTTP error: "+req.status);
        return false;
      }
    }
  }
}





Pub.getStatisticReadyStateHandler =function (req, eleid,responseXmlHandler,noteId) {
  alert(req.readyState);
  return function () {
    if (req.readyState == 4) {
       
      if (req.status == 200) {
          
        responseXmlHandler(req.responseText,eleid);
 
      } else {
        
        alert("HTTP error: "+req.status);
        return false;
      }
    }else{
   
     var ele11 =document.getElementById(noteId);
    ele11.innerHTML="Loading Data";
    }
  }
}

/**
 * 
 * @param data：服务器响应的结果
 * @param eleid：表单Form2的名称
 */
Pub.handleResponse= function handleResponse(data,eleid){

      var ele =document.getElementById(eleid);
      
      ele.innerHTML = data;
    
}


Pub.submitAction=function(domId,action){

  var req = Pub.newXMLHttpRequest();
  
  var handlerFunction = Pub.getReadyStateHandler(req, domId,Pub.handleResponse);
  req.onreadystatechange = handlerFunction;
  req.open("POST", action, false);
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

   
  req.send(null);

}




Pub.submitActionWithNote=function(domId,action,noteId){


  var req = Pub.newXMLHttpRequest();
  
  var handlerFunction = Pub.getStatisticReadyStateHandler(req, domId,Pub.handleResponse,noteId);
  req.onreadystatechange = handlerFunction;
  req.open("POST", action, true);
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  req.send(null);

}



/*******************************************************************************
 * 组织发送给服务器的参数，传递表单Form1中元素的值
 * 
 * @param sForm：表示表单Form1的名称
 * @returns {String}：传递给服务器的参数格式：name=zhangsan&age=23
 */
Pub.getParams2Str=function getParams2Str(sForm){

 var strDiv="";
      
 try {
    var objForm=document.forms[sForm];
  if (!objForm)
    return strDiv;
  var elt,sName,sValue;
  for (var fld = 0; fld < objForm.elements.length; fld++) {
      elt = objForm.elements[fld];    
      sName=elt.name;
      sValue=""+elt.value;
      if(fld==objForm.elements.length-1)
          strDiv=strDiv + sName+"="+sValue+"";
       else   
          strDiv=strDiv + sName+"="+sValue+"&";
   }


  }
  catch (ex) {
    return strDiv;
	}
 
   return strDiv;
}

function doContents(){
    if (xmlhttp.readyState < 4){
        alert("a");
    }
    else if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
        alert("b");
    }
}

/**
 * 
 * domId:表单Form2的名称 action:URL连接 sForm:表单Form1的名称
 */
Pub.submitActionWithForm=function(domId,action,sForm){
  /** 第一步：创建ajax引擎核心对象 */
  var req = Pub.newXMLHttpRequest();
  /**
	 * 第二步：创建事件处理函数，用来监听客户端和服务器端的连接状态 Pub.getReadyStateHandler(req,
	 * domId,Pub.handleResponse);
	 */
  var handlerFunction = Pub.getReadyStateHandler(req, domId,Pub.handleResponse);
  req.onreadystatechange = handlerFunction;
  /** 第三步：open方法打开一个连接 */
  req.open("POST", action, true);
  // 如果是post请求，需要添加请求头，否则客户端将无法通过send方法传递参数
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
  /** 第四步：使用send()方法向服务器发送数据，发送数据的格式name=zhangsan&age=23 */
  // 组织发送给服务器的参数，传递表单Form1中元素的值
  var str = Pub.getParams2Str(sForm); 
  req.send(str);
}

Pub.submitActionWithFormGet=function(domId,action,sForm){
	  
	  var req = Pub.newXMLHttpRequest();
	  
	  var handlerFunction = Pub.getReadyStateHandler(req, domId,Pub.handleResponse);
	  req.onreadystatechange = handlerFunction;
	  
	  req.open("Get", action, true);
	  req.send(null);
}

