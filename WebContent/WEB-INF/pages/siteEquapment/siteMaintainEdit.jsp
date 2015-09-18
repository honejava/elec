<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>维护计划编辑</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" language="JavaScript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/pub.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/public.js"
	charset="gb2312"></script>

<Script language="javascript">
	function check_null() {

		var theForm = document.Form1;

		if (theForm.jctId.value == "0") {
			alert("请选择所属单位");
			theForm.jctId.focus();
			return false;
		}

		if (Trim(theForm.occurDate.value) == "") {
			alert("计划时间不能为空");
			theForm.occurDate.focus();
			return false;
		} else {
			if (!checkDatetime(theForm.occurDate.value)) {
				alert("请输入正确的计划时间");
				theForm.occurDate.focus();
				return;
			}
		}

		if (theForm.mainContent.value.length > 250) {

			alert("主要内容信息字符长度不能超过250");
			theForm.mainContent.focus();
			return false;
		}

		if (theForm.comment.value.length > 250) {

			alert("备注字符长度不能超过250");
			theForm.comment.focus();
			return false;
		}

		document.Form1.action = "station/elecPlanAction_update.do";
		document.Form1.submit();

		//window.setTimeout(refreshThisOpener('getRepairPlan.do'), 3000);
	}

	function refreshThisOpener(sHref) {

		opener.gotopage(sHref, "modifyplan");
		window.close();
	}

	function upload(planID) {
		var str = "station/elecFileUploadAction_up.do?elecPlan.planID=" + planID;
		OpenWindow('uploadInit', str, 800, 450);
	}
</script>
</head>

<body>
	<form name="Form1" method="post">
		<br>
		<table cellspacing="1" cellpadding="5" width="680" align="center"
			bgcolor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">

			<tr>
				<td colspan="4" class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>维护计划编辑</strong></font>
				</td>
			</tr>
			<tr>
				<input type="hidden" name="planID"
					value="<s:property value='#request.plan.planID'/>">
			<tr>
				<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01">所属单位：</td>
				<td class="ta_01" bgcolor="#ffffff"><s:select
						list="#request.jctList" name="jctID" id="jctId" cssClass="bg"
						headerKey="0" headerValue="全部" listKey="ddlCode"
						listValue="ddlName" value="%{#request.plan.jctID}"></s:select><font
					color="#FF0000">*</font></td>
				<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01">计划时间：</td>
				<td class="ta_01" bgcolor="#ffffff"><input name="occurDate"
					type="text" value="<s:property value='#request.plan.occurDate'/>"
					id="occurDate" onclick="WdatePicker()"> <font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3"><textarea
						name="mainContent" id="mainContent"
						style="WIDTH: 86%; height: 130"><s:property
							value="#request.plan.mainContent" /></textarea> &nbsp;&nbsp;<span
					id=PResolve style="DISPLAY: ''"><input id="btnContent"
						type="button" value=" 详细" name="btnContent"
						onclick="upload(<s:property
							value="#request.plan.planID" />);"
						style="font-size: 12px; color: black;"></span></td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3"><textarea
						name="comment" id="comment" style="WIDTH: 96%; height: 130"><s:property
							value="#request.plan.comment" /></textarea></td>
			</tr>
			<tr>
				<td class="ta_01" style="width: 100%" align="center"
					bgcolor="#f5fafe" colspan="4"><input type="button"
					name="BT_Submit" value="保存" id="BT_Submit"
					style="font-size: 12px; color: black;" onclick="check_null()">
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>&nbsp;

					<input style="font-size: 12px; color: black;" type="reset"
					value="关闭" id="Reset1" name="Reset1" onclick="window.close();">
					<span id="Label1"></span></td>
			</tr>
		</table>
		<br>
	</form>


</body>

</html>


