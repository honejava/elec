<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>设备校准记录编辑</title>

<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet" />
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/changePageBackUp.js"></script>
<script language="javascript">
<!--
	function shareOnChange(mySelect) {
		if (mySelect.selectedIndex == 0) {
			PPassword.style.display = "none"
		} else
			PPassword.style.display = ""
	}
</script>

<SCRIPT type="text/javascript">
function ini(){
   document.Form1.isHaving == 1;
   PPassword.style.display = ""
}
function check(){
     
     var theForm = document.forms[0];
   	if (theForm.comment.value.length >250)
		{
		    alert("备注不能超过250个汉字！");
		    theForm.comment.focus();
		    return false;
		}
		if (theForm.record.value.length >250)
		{
		    alert("记录描述不能超过250个汉字！");
		    theForm.record.focus();
		    return false;
		}
	 if(window.opener)
	{
		document.Form1.action="updateDeviceX.do";
		document.Form1.submit();
		alert("保存成功！");
	    //window.opener.Pub.submitActionWithForm('Form1','toDeviceX.do','F1');   
	  
	}
	window.close();
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
	onunload="submitrequest('clearSession.do?action=X')">
	<form name="Form1" method="post" action="#">
		<br>

		<table cellSpacing="1" cellPadding="5" width="680" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center"
						background="${pageContext.request.contextPath }/images/b-info.gif">
						<font face="宋体" size="2"><strong>设备校准记录编辑</strong></font>
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
											list="#request.jctList" name="jctID" id="jctId" cssClass="bg"
											listKey="ddlCode" listValue="ddlName" disabled="true"
											value="#request.adjust.jctID"></s:select></td>
									<td width="100" class="ta_01" align="center" bgColor="#f5fafe"
										height="22">设备名称：</td>
									<td class="ta_01" bgColor="#ffffff"><font face="宋体"
										color="red"> <input name="devName" type="text"
											id="devName" size="19"
											value="<s:property value='#request.adjust.devName'/>"
											disabled></font></td>
								</tr>
								<tr>
									<td width="153" class="ta_01" align="center" bgColor="#f5fafe"
										height="22">校准周期：</td>
									<td class="ta_01" bgColor="#ffffff" width="224"><input
										name="seqId" type="hidden" id="seqId" size="19" value="7313">
										<input name="devId" type="hidden" id="devId" size="19"
										value="e11c8381ec0f436b992ba0e44dd33cb0"> <input
										name="apunit" type="hidden" id="apunit" size="19" value="月">
										<input name="deviceobject" type="hidden" id="deviceobject"
										size="19" value="1"> <input name="TB_timeW"
										type="text" id="TB_timeW" size="19"
										value="<s:property value='#request.adjust.adjustPeriod'/><s:property value='#request.adjust.apunit'/>"
										disabled></td>
									<td width="100" class="ta_01" align="center" bgColor="#f5fafe"
										height="22">使用日期：</td>
									<td class="ta_01" bgColor="#ffffff"><font face="宋体"
										color="red"> <input name="useDate" type="text"
											id="useDate" size="19"
											value="<s:property value='#request.adjust.useDate'/>"
											disabled></font></td>
								</tr>
								<tr>
									<td width="153" class="ta_01" align="center" bgcolor="#f5fafe"
										height="22">设备类型：</td>
									<td class="ta_01" bgcolor="#ffffff" width="247"><font
										face="宋体" color="red"> <s:select
												list="#request.devTypeList" name="devType" id="devType"
												cssClass="bg" disabled="true" listKey="ddlCode"
												listValue="ddlName"></s:select>
									</font></td>
									<td width="100" class="ta_01" align="center" bgColor="#f5fafe"
										height="22">校准状态：</td>
									<td class="ta_01" bgColor="#ffffff" width="224"><s:select
											list="#request.isHavingList" name="isHaving" id="apstate"
											cssClass="bg" listKey="ddlCode" listValue="ddlName"></s:select></td>
								</tr>
								<tr>
									<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
										校准日期：</td>
									<td class="ta_01" bgcolor="#ffffff"><font face="宋体"
										color="red"> <input disabled name="startDate"
											type="text" size="19" id="startDate" disabled
											value="<s:property value='#request.adjust.startDate'/>"></font>&nbsp;<font
										color="#FF0000">*</font></td>
									<td class="ta_01" align="center" bgcolor="#ffffff" height="22"></td>
									<td class="ta_01" bgcolor="#ffffff"></td>
								</tr>
								<TR>
									<TD class="ta_01" align="center" bgColor="#f5fafe">校准记录：</TD>
									<TD class="ta_01" bgColor="#ffffff" colSpan="3"><select
										name="isHaving" id="isHaving" class="bg" style="width: 50"
										onChange=shareOnChange(this)>
											<option value="0">无</option>
											<option value="1" selected>有</option>
									</select> &nbsp;&nbsp;&nbsp;&nbsp; <span id=PPassword
										style="DISPLAY: none"> <input
											style="font-size: 12px; color: black;" id="BT_Add"
											type="button" value="详细" name="BT_Add"
											onClick="openWindowWithName('${pageContext.request.contextPath }/station/elecFileUploadAction_adjustFile.do?adjustID=<s:property value='#request.adjust.adjustID' />',800,450,'ECC');" />
									</span></TD>
								</TR>

								<TR>
									<TD class="ta_01" align="center" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
									<TD class="ta_01" bgColor="#ffffff" colSpan="3"><textarea
											name="comment" id="comment" style="WIDTH: 96%" rows="3"><s:property
												value='#request.adjust.comment' /></textarea></TD>
								</TR>
								<tr>
									<td class="ta_01" align="center" bgColor="#f5fafe" height="22">记录描述：</td>
									<td height="22" colspan="3" bgColor="#FFFFFF" class="ta_01"><font
										face="宋体" color="red"> </font> <textarea name="record"
											id="record" style="WIDTH: 96%" rows="3"><s:property
												value='#request.adjust.record' /></textarea></td>
								</tr>

								<tr>
									<td class="ta_01" style="WIDTH: 100%" align="center"
										bgColor="#f5fafe" colSpan="4"><INPUT
										style="font-size: 12px; color: black;" type="reset" value="关闭"
										ID="Reset1" NAME="Reset1" onClick="window.close();" /> <span
										id="Label1"></span></td>
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