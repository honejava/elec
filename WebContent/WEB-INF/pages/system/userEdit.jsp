<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
   <title>编辑用户</title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
   <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/showText.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/limitedTextarea.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
   <Script language="javascript">
    
	function check_null(){
	
		var theForm=document.Form1;
	    
	  
	    if(Trim(theForm.userName.value)=="")
		{
			alert("用户姓名不能为空");
			theForm.userName.focus();
			return false;
		}
		if(theForm.sexID.value=="")
		{
			alert("请选择性别");
			theForm.sexID.focus();
			return false;
		}
	    if(theForm.jctID.value=="")
		{
			alert("请选择所属单位");
			theForm.jctID.focus();
			return false;
		}
	    if(Trim(theForm.onDutyDate.value)=="")
		{
			alert("入职时间不能为空");
			theForm.onDutyDate.focus();
			return false;
		}
	    if(theForm.postID.value=="")
		{
			alert("请选择职位");
			theForm.postID.focus();
			return false;
		}
        if(theForm.logonPwd.value!=theForm.passwordconfirm.value){
		
		  alert("两次输入密码不一致，请重新输入");
		  return;
		}
		if(checkNull(theForm.contactTel)){
         if(!checkPhone(theForm.contactTel.value))
		  {
			alert("请输入正确的电话号码");
			theForm.contactTel.focus();
			return false;
		  }
		}
		
	    if(checkNull(theForm.mobile)){
         if(!checkMobilPhone(theForm.mobile.value))
		  {
			alert("请输入正确的手机号码");
			theForm.mobile.focus();
			return false;
		  }
		}
		
	   if(checkNull(theForm.email))	{
         if(!checkEmail(theForm.email.value))
		 {
			alert("请输入正确的EMail");
			theForm.email.focus();
			return false;
		 }
	   }
		
	   if(theForm.remark.value.length>250){
     
        	alert("备注字符长度不能超过250");
			theForm.remark.focus();
			return false; 
       }
	   //如果是否在职选择是，则入职时间必须要填写，如果是否在职选择否，则离职日期必须要填写
	   var isDutySelect = document.getElementById("isDuty");
	   //选择[是]
	   if(isDutySelect.options[0].selected){
		   if(theForm.onDutyDate.value==""){
			   alert("该用户属于在职人员，请填写入职时间");
			   theForm.onDutyDate.focus();
			   return false; 
		   }
	   }
	   //选择[否]
	   else{
		   if(theForm.offDutyDate.value==""){
			   alert("该用户属于离职人员，请填写离职时间");
			   theForm.offDutyDate.focus();
			   return false; 
		   }
	   }
	   
	   document.Form1.action="${pageContext.request.contextPath }/system/elecUserAction_save.do";
	   document.Form1.submit();
	  	
	}
	function checkTextAreaLen(){
  		var remark = new Bs_LimitedTextarea('remark', 250); 
  		remark.infolineCssStyle = "font-family:arial; font-size:11px; color:gray;";
  		remark.draw();	
    }
    window.onload=function(){
		checkTextAreaLen();
    }
    
    /**如果选择离职时间，则【是否在职】默认选择"否"，如果没有选择离职时间，则【是否在职】默认选择"是"*/
    function checkIsDuty(o){   
 	   var offDutyDate = o.value;
 	   var isDutySelect = document.getElementById("isDuty");
 	   if(offDutyDate!=""){
 		   isDutySelect.options[1].selected = true; //否
 	   }
 	   else{
 		   isDutySelect.options[0].selected = true; //是
 	   }
    }
    
    //ajax的二级联动，使用选择的所属单位，查询该所属单位下对应的单位名称列表
    function findJctUnit(o){
    	//货物所属单位的文本内容
    	var jct = $(o).find("option:selected").text();
    	$.post("elecUserAction_findJctUnit.do",{"jctID":jct},function(data,textStatus){
	   	    //先删除单位名称的下拉菜单，但是请选择要留下
	   	    $("#jctUnitID option").remove();
	        if(data!=null && data.length>0){
	            for(var i=0;i<data.length;i++){
	   		       	var ddlCode = data[i].ddlCode;
	   		       	var ddlName = data[i].ddlName;
	   		       	//添加到单位名称的下拉菜单中
	   		       	var $option = $("<option></option>");
	   		       	$option.attr("value",ddlCode);
	   		       	$option.text(ddlName);
	   		       	$("#jctUnitID").append($option);
	   	        }
	        }
        });
    	
    }
	
</script>
</head>

  
 <body>
    <form name="Form1" method="post">	
    <br>
    
    <table cellSpacing="1" cellPadding="5" width="680" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

	 <tr>
		<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
		 <font face="宋体" size="2"><strong>
		<s:if test="viewflag==null">编辑用户</s:if> 
		<s:else>查看用户</s:else>
		 </strong></font>
		</td>
    </tr>
    <s:hidden name="userID"/>
     <tr>
         <td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：
         <td class="ta_01" bgColor="#ffffff">
            <s:textfield name="logonName" maxlength="25" id="logonName"  size="20" readonly="true"></s:textfield>
         <font color="#FF0000">*</font></td>
         <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：
		 <td class="ta_01" bgColor="#ffffff">
		  <s:textfield name="userName" maxlength="25" id="userName"  size="20"></s:textfield>
         <font color="#FF0000">*</font></td>
    </tr>
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">性别：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.sexlist" 
			          listKey="ddlCode" listValue="ddlName"
			          headerKey="" headerValue=""
			          name="sexID" id="sexID" cssStyle="width:155px"
			          ></s:select>
			<font color="#FF0000">*</font>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">职位：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.postlist"
			          listKey="ddlCode" listValue="ddlName"
			          headerKey="" headerValue=""
			          name="postID" id="postID" cssStyle="width:155px"
			          ></s:select>
			<font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">所属单位：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.jctlist" 
			          listKey="ddlCode" listValue="ddlName"
			          headerKey="" headerValue=""
			          name="jctID" id="jctID" cssStyle="width:155px"
			          onchange="findJctUnit(this)"
			          ></s:select>
			<font color="#FF0000">*</font>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">单位名称：</td>
		<td class="ta_01" bgColor="#ffffff">
		<s:select list="#request.jctUnitlist" 
			          listKey="ddlCode" listValue="ddlName"
			          headerKey="jctUnitID" headerValue=""
			          name="jctUnitID" id="jctUnitID" cssStyle="width:155px"
			          ></s:select>
			<font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">密码：</td>
		<td class="ta_01" bgColor="#ffffff">
		<s:password name="logonPwd" id="logonPwd" maxlength="25" showPassword="true" size="22"></s:password>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
		<td class="ta_01" bgColor="#ffffff">
			<input name="passwordconfirm" type="password" value="<s:property value="logonPwd"/>" maxlength="25" size="22">
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
		<td class="ta_01" bgColor="#ffffff">
		 <s:date name="birthday" format="yyyy-MM-dd" var="b"/>
		 <s:textfield name="birthday" value="%{b}" maxlength="50"  size="20"  onClick="WdatePicker()"></s:textfield>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
		<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="address" maxlength="50"  size="20"></s:textfield>
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
		<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="contactTel" maxlength="25"  size="20"></s:textfield>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">手机：</td>
		<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="mobile" maxlength="25"  size="20"></s:textfield>
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
		<td class="ta_01" bgColor="#ffffff">
		<s:textfield name="email" maxlength="50"  size="20"></s:textfield>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.isDutylist" 
		          listKey="ddlCode" listValue="ddlName"
		          headerKey="" headerValue=""
		          name="isDuty" id="isDuty" cssStyle="width:155px"
		          ></s:select>
		</td>
	</tr>
	
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">入职日期：</td>
		<td class="ta_01" bgColor="#ffffff">
		<s:date name="onDutyDate" format="yyyy-MM-dd" var="on"/>
		<s:textfield name="onDutyDate" value="%{on}" id="onDutyDate" maxlength="50"  size="20" onClick="WdatePicker()"></s:textfield>
			<font color="#FF0000">*</font>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">离职日期：</td>
		<s:date name="onDutyDate" format="yyyy-MM-dd" var="off"/>
		<td class="ta_01" bgColor="#ffffff">
		   <s:textfield name="offDutyDate" value="%{off}" id="offDutyDate" maxlength="50"  size="20" onClick="WdatePicker()"></s:textfield>
		</td>
	</tr>
	<TR>
		<TD class="ta_01" align="center" bgColor="#f5fafe">备注：</TD>
		<TD class="ta_01" bgColor="#ffffff" colSpan="3">
		 <s:textfield name="remark" cssStyle="WIDTH:92%;"  rows="4" cols="52" ></s:textfield>
		</TD>
		</TR>
		<TR>
		<td  align="center"  colSpan="4"  class="sep1"></td>
	</TR>
	<tr>
		<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
		<s:if test="viewflag==null">
			<input type="button" id="BT_Submit" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"  onClick="check_null()">
		</s:if>    
		    <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
		    <input style="font-size:12px; color:black; height=22;width=55" type="button" value="关闭"  name="Reset1"  onClick="window.close()">
	    </td>
	</tr>
</table>　
</form>

</body>
</html>
