<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>设备检修记录</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/changePageBackUp.js"></script>
<script language="javascript">
	
function shareOnChange(mySelect)
{
if (mySelect.selectedIndex == 0)
    {
    PPassword.style.display = "none"
   
    }
else
    PPassword.style.display = ""
}


</script>

<SCRIPT type="text/javascript">
function ini(){
   document.all.useDate.focus();
}
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
	 if(window.opener)
	{
		document.Form1.action="equapment/elecRepairAction_update.do";
		document.Form1.submit();
		alert("修改成功！");
	    //window.opener.Pub.submitActionWithForm('Form1','toDeviceJ.do','F1');  
	}
	self.close();
}
</SCRIPT>
<SCRIPT type="text/javascript">
function submitrequest(action){

  eval("document.location='"+action+"'");
}
function returnMethod(){
	return check();
}
</SCRIPT>
</head>

<body onload="shareOnChange(document.Form1.isHaving)"
	onunload="submitrequest('clearSession.do?action=J')">

	<form name="Form1" method="post">
		<s:hidden name="repairID" value="%{#request.repair.repairID}" />
		<br>
		<table cellSpacing="1" cellPadding="5" width="680" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center"
						background="${pageContext.request.contextPath }/images/b-info.gif">
						<font face="宋体" size="2"><strong>设备检修查看</strong></font>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tbody>

								<tr>
									<td width="153" class="ta_01" align="center" bgColor="#f5fafe"
										height="22">所属单位：</td>
									<td class="ta_01" bgColor="#ffffff"><s:select
											list="#request.jctList" name="jctId" id="jctId" class="bg"
											disabled="true" listKey="ddlCode" listValue="ddlName"
											value="#request.repair.jctID"></s:select></td>
									<td width="100" class="ta_01" align="center" bgColor="#f5fafe"
										height="22">设备名称：</td>
									<td class="ta_01" bgColor="#ffffff"><font face="宋体"
										color="red"> <input name="devName" type="text"
											id="devName" size="19"
											value="<s:property value='#request.repair.devName'/>"
											disabled></font></td>
								</tr>
								<tr>
									<td width="153" class="ta_01" align="center" bgColor="#f5fafe"
										height="22">检修周期：</td>
									<td class="ta_01" bgColor="#ffffff" width="224"><input
										name="overhaulPeriod" type="text" id="TB_timeW" size="19"
										value="<s:property value='#request.repair.overhaulPeriod'/>
										<s:property value='#request.repair.opunit'/>"
										disabled></td>
									<td width="100" class="ta_01" align="center" bgColor="#f5fafe"
										height="22">使用日期：</td>
									<td class="ta_01" bgColor="#ffffff"><font face="宋体"
										color="red"> <input name="useDate" type="text"
											id="TB_timeW" size="19"
											value="<s:property value='#request.repair.useDate'/>"
											disabled></font></td>
								</tr>
								<tr>
									<td width="153" class="ta_01" align="center" bgcolor="#f5fafe"
										height="22">设备类型：</td>
									<td class="ta_01" bgcolor="#ffffff" width="247"><s:select
											list="#request.devTypeList" name="devType" id="devType"
											cssClass="bg" disabled="true" headerKey="0" headerValue="全部"
											listKey="ddlCode" listValue="ddlName"
											value="#request.repair.devType"></s:select></td>
									<td width="100" class="ta_01" align="center" bgColor="#ffffff"
										height="22"></td>
									<td class="ta_01" bgColor="#ffffff" width="224"></td>
								</tr>
								<tr>
									<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
										检修日期：</td>
									<td class="ta_01" bgcolor="#ffffff"><font face="宋体"
										color="red"> <input name="startDate" type="text"
											size="19" id="startDate" onclick="WdatePicker()"></font>&nbsp;<font
										color="#FF0000">*</font></td>


									<td class="ta_01" align="center" bgcolor="#ffffff" height="22"></td>
									<td class="ta_01" bgcolor="#ffffff"></td>


								</tr>
								<TR>
									<TD class="ta_01" align="center" bgColor="#f5fafe">检修记录：</TD>
									<TD class="ta_01" bgColor="#ffffff" colSpan="3"><select
										name="isHaving" id="isHaving" class="bg" style="width: 50"
										name=ifshare onChange=shareOnChange(this)>

											<option value="1">有</option>
											<option value="0" selected>无</option>
									</select> &nbsp;&nbsp;&nbsp;&nbsp; <span id=PPassword
										style="DISPLAY: none"> <input
											style="font-size: 12px; color: black;" id="BT_Add"
											type="button" value="详细" name="BT_Add"
											onClick="openWindowWithName('station/elecFileUploadAction_repairFile',800,450,'ECC');" />
									</span></TD>
								</TR>

								<TR>
									<TD class="ta_01" align="center" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
									<TD class="ta_01" bgColor="#ffffff" colSpan="3"><textarea
											name="comment" id="comment" style="WIDTH: 96%" rows="3"><s:property
												value="#request.repair.comment" /></textarea></TD>
								</TR>
								<tr>
									<td class="ta_01" align="center" bgColor="#f5fafe" height="22">记录描述：</td>
									<td height="22" colspan="3" bgColor="#FFFFFF" class="ta_01"><font
										face="宋体" color="red"> </font> <input name="devId"
										type="hidden" id="devId" class="bg" size="15"
										value="c51da3d47c814b488d4a4206c7a1703f"> <textarea
											name="record" id="record" style="WIDTH: 96%" rows="3"><s:property
												value="#request.repair.record" /></textarea></td>
								</tr>

								<tr>
									<td class="ta_01" style="WIDTH: 100%" align="center"
										bgColor="#f5fafe" colSpan="4"><INPUT
										style="font-size: 12px; color: black;" type="reset"
										value=" 关闭" ID="Reset1" NAME="Reset1"
										onClick="window.close();" /> <span id="Label1"></span></td>
								</tr>

							</tbody>

						</table>
					</td>
				</tr>

			</TBODY>
		</table>
	</form>
</body>
</HTML>