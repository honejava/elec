<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<head>
<title>批量添加设备检修周期</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet" />
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/changePageBackUp.js"></script>
<style type="text/css">
<!--
.STYLE1 {
	color: #FFFFFF
}

.STYLE2 {
	margin: 1px;
	padding: 2px 4px 2px 10px;
	border: 1px solid #8AA2CC;
	background-attachment: fixed;
	background-image:
		url(${pageContext.request.contextPath }/images/button_view.gif);
	background-repeat: no-repeat;
	background-position: left center;
	cursor: hand;
	text-align: right;
	left: 10px;
	top: 10px;
	right: 0px;
	bottom: 10px;
	clip: rect(10px, 10px, 10px, 10px);
	height: 20px;
	background-color: #DAE6FF;
}

.STYLE3 {
	margin: 1px;
	padding: 2px 4px 2px 10px;
	border: 1px solid #8AA2CC;
	background-attachment: fixed;
	background-image:
		url(${pageContext.request.contextPath }/images/button_add.gif);
	background-repeat: no-repeat;
	background-position: left center;
	cursor: hand;
	text-align: right;
	left: 10px;
	top: 10px;
	right: 0px;
	bottom: 10px;
	clip: rect(10px, 10px, 10px, 10px);
	height: 20px;
	background-color: #DAE6FF;
}
-->
</style>
<script language="javascript">
function shareOnChange(mySelect)
{
if (mySelect.selectedIndex == 0)
    {
    PPassword.style.display = ""
    }
else
    PPassword.style.display = "none"
}

function CheckOthers(form)
{

	for (var i=0;i<dtype.Form1.elements.length;i++)
	{
	
		var e = dtype.Form1.elements[i];
			if (e.checked==false)
			{
				e.checked = true;
			}
			else
			{
				e.checked = false;
			}
			e.checked = false;
	}
}

function CheckAll(form)
{

	for (var i=0;i<dtype.Form1.elements.length;i++)
	{
		
		var e = dtype.Form1.elements[i];
        e.checked = true;
	}
}
</SCRIPT>
<script language="javascript">
 function check(){
 
         var theForm = document.forms[0];
         
          if(Trim(theForm.startDate.value)==""){
	
	    	alert("请输入校准日期");
			theForm.startDate.focus();
			return false;
	    }
         if(theForm.comment.value.length>250){
     
        	alert("备注字符长度不能超过250");
			theForm.comment.focus();
			return false; 
        }
        if(theForm.record.value.length>250){
     
        	alert("记录描述字符长度不能超过250");
			theForm.record.focus();
			return false; 
        }
     var id="";
	 for (var i=0;i<dtype.Form1.elements.length;i++)
	 {
		
        var e = dtype.Form1.elements[i];
	    if (dtype.Form1.elements[i].checked==true)
	      {
			id=id+dtype.Form1.elements[i].value+",";
		  }
	
	}
	  document.Form1.jctID.value=id;
	  if(id!=""){
		document.Form1.action="equapment/elecRepairAction_moreUpdate.do";
		document.Form1.submit();
		alert("保存成功！");
	  //  window.opener.jsJumpToBeginJ();//去第1页
	 }
	//window.close();

}

  function getdevTypeChange(dt){
  
      document.all.dtype.src="equapment/elecRepairAction_repairMoreAddList.do?devType="+dt.value;
  }

  function returnMethod(){
	  return check(dtype.form);
  }
</SCRIPT>

<SCRIPT type="text/javascript">
function submitrequest(action){

  eval("document.location='"+action+"'");

}

</SCRIPT>
</head>

<body ms_positioning="GridLayout"
	onunload="submitrequest('clearSession.do?action=J')">

	<form name="Form1" method="post">
		<input type="hidden" name="jctID" id="devId"> &nbsp;

		<table cellSpacing="1" cellPadding="5" width="680" align="center"
			bgColor="#f5fafe" style="border: 1px solid #8ba7e3" border="0">


			<tr>
				<td class="ta_01" colSpan="2" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>设备检修批量添加</strong></font>
				</td>
			</tr>

			<tr>
				<td width="30%" height="100%">
					<fieldset style="padding: 2; width: 335px; height: 296px">
						<legend>请选择设备</legend>
						<table border="0" width="100%" id="table5">
							<tr>
								<td align="center" bgColor="#f5fafe" class="ta_01"
									style="width: 28%">设备类型：</td>
								<td align="left" bgColor="#f5fafe" class="ta_01"
									style="width: 64%"><s:select list="#request.devTypeList"
										name="devType" id="devType" cssClass="bg" headerKey="0"
										headerValue="全部" listKey="ddlCode" listValue="ddlName"
										onchange="getdevTypeChange(this)"></s:select></td>
							</tr>
							<tr>
								<td colspan="2" align="right" bgColor="#f5fafe" class="ta_01">
									<input type="button" name="chkall"
									style="font-size: 12px; color: #000000; background-color: #f5fafe; border: 0px;"
									10"" value="全选" onClick="CheckAll(this.form)"> |&nbsp;
									<input type="button"
									style="font-size: 12px; color: #000000; background-color: #f5fafe; border: 1px;"
									18"" name="chkOthers" value="取消"
									onClick="CheckOthers(this.form)">
								</td>
							</tr>
							<tr>
								<td colspan="2"><IFRAME
										src="equapment/elecRepairAction_repairMoreAddList.do"
										id="dtype" frameBorder=0 width=335 scrolling=auto height=220></IFRAME></td>
							</tr>
						</table>
					</fieldset>
				</td>
				<td width=70% height="293">
					<table cellSpacing="1" width=100% cellPadding="5" height="320"
						align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3"
						border="0">
						<tr>
							<td align="center" bgcolor="#f5fafe" class="ta_01"></td>
							<td bgColor="#f5fafe" class="ta_01"></td>
						</tr>
						<tr>
							<td align="center" bgcolor="#f5fafe" class="ta_01">检修时间：</td>
							<td bgColor="#f5fafe" class="ta_01"><input name="startDate"
								type="text" class="bg" id="startDate" onclick="WdatePicker()"
								size="10" readonly>&nbsp;<font color="#FF0000">*</font></td>
						</tr>

						<tr>
							<td align="center" bgcolor="#f5fafe" class="ta_01">有无记录：</td>
							<td bgColor="#f5fafe" class="ta_01"><select name="isHaving"
								id="isHaving" class="bg" style="width: 50" name=ifshare
								onChange=shareOnChange(this)>
									<option value="1">有</option>
									<option value="0" selected>无</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp; <span id=PPassword style="DISPLAY: none">
									<input style="font-size: 12px; color: black;" id="BT_Add"
									type="button" value="详细" name="BT_Add"
									onClick="openWindowWithName('equapment/elecRepairAction_repairQuery.do',800,450,'EOC');" />
							</span></td>
						</tr>
						<tr>
							<td align="center" bgcolor="#f5fafe" class="ta_01">检修记录：</td>
							<td bgColor="#f5fafe" class="ta_01"><textarea name="record"
									id="record" style="WIDTH: 96%" rows="3" cols="20"></textarea></td>
						</tr>
						<tr>
							<td align="center" bgcolor="#f5fafe" class="ta_01">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								注：</td>
							<td bgColor="#f5fafe" class="ta_01"><textarea name="comment"
									id="comment" style="WIDTH: 96%" rows="3" cols="20"></textarea></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td height="15" colspan="2" align="center" bgColor="#f5fafe"
					class="ta_01" style="width: 100%"><input type="button"
					name="BT_Submit22" value="保存" id="BT_Submit22"
					style="font-size: 12px; color: black;" onclick="returnMethod()">&nbsp;&nbsp;
					<INPUT style="font-size: 12px; color: black;" type="reset"
					value=" 关闭" ID="Reset1" NAME="Reset1" onClick="window.close();" />
				</td>
			</tr>
		</table>

		<br>
	</form>
</body>
</html>