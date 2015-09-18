<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>添加用户</title>
<LINK href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/validate.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/showText.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/limitedTextarea.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>

<Script language="javascript">
	function check_null() {

		var theForm = document.Form1;

		if (Trim(theForm.logonName.value) == "") {
			alert("登录名不能为空");
			theForm.logonName.focus();
			return false;
		}
		if (Trim(theForm.userName.value) == "") {
			alert("用户姓名不能为空");
			theForm.userName.focus();
			return false;
		}
		if (theForm.sexID.value == "") {
			alert("请选择性别");
			theForm.sexID.focus();
			return false;
		}
		if (theForm.jctID.value == "") {
			alert("请选择所属单位");
			theForm.jctID.focus();
			return false;
		}
		if (Trim(theForm.onDutyDate.value) == "") {
			alert("入职时间不能为空");
			theForm.onDutyDate.focus();
			return false;
		}
		if (theForm.postID.value == "") {
			alert("请选择职位");
			theForm.postID.focus();
			return false;
		}
		if (theForm.logonPwd.value != theForm.passwordconfirm.value) {

			alert("两次输入密码不一致，请重新输入");
			return;
		}
		if (checkNull(theForm.contactTel)) {
			if (!checkPhone(theForm.contactTel.value)) {
				alert("请输入正确的电话号码");
				theForm.contactTel.focus();
				return false;
			}
		}

		if (checkNull(theForm.mobile)) {
			if (!checkMobilPhone(theForm.mobile.value)) {
				alert("请输入正确的手机号码");
				theForm.mobile.focus();
				return false;
			}
		}

		if (checkNull(theForm.email)) {
			if (!checkEmail(theForm.email.value)) {
				alert("请输入正确的EMail");
				theForm.email.focus();
				return false;
			}
		}

		if (theForm.remark.value.length > 250) {

			alert("备注字符长度不能超过250");
			theForm.remark.focus();
			return false;
		}
		var isDutySelect = document.getElementById("isDuty");
		//选择[是]
		if (isDutySelect.options[0].selected) {
			if (theForm.onDutyDate.value == "") {
				alert("该用户属于在职人员，请填写入职时间");
				theForm.onDutyDate.focus();
				return false;
			}
		}
		//选择[否]
		if (isDutySelect.options[1].selected) {
			alert("不允许新增用户操作，选择离职！");
			theForm.isDuty.focus();
			return false;
		}
		document.Form1.action = "${pageContext.request.contextPath }/system/elecUserAction_save.do";
		document.Form1.submit();
	}
	function checkTextAreaLen() {
		var remark = new Bs_LimitedTextarea('remark', 250);
		remark.infolineCssStyle = "font-family:arial; font-size:11px; color:gray;";
		remark.draw();
	}
	window.onload = function() {
		checkTextAreaLen();
	}
	//ajax的二级联动，使用选择的所属单位，查询该所属单位下对应的单位名称列表
	function findJctUnit(o) {
		//货物所属单位的文本内容
		var jct = $(o).find("option:selected").text();
		$
				.post(
						"${pageContext.request.contextPath }/system/elecUserAction_findJctUnit.do",
						{
							"jctID" : jct
						}, function(data, textStatus) {
							//先删除单位名称的下拉菜单，但是请选择要留下
							$("#jctUnitID option").remove();
							if (data != null && data.length > 0) {
								for ( var i = 0; i < data.length; i++) {
									var ddlCode = data[i].ddlCode;
									var ddlName = data[i].ddlName;
									//添加到单位名称的下拉菜单中
									var $option = $("<option></option>");
									$option.attr("value", ddlCode);
									$option.text(ddlName);
									$("#jctUnitID").append($option);
								}
							}
						});

	}

	/**校验登录名是否出现重复*/
	function checkUser(o) {
		//alert(o.value);//dom的写法
		//alert($(o).val());//jquery的写法
		var logonName = $(o).val();
		//以登录名作为查询条件，查询该登录名是否在数据库表中存在记录
		$
				.post(
						"${pageContext.request.contextPath }/system/elecUserAction_checkUser.do",
						{
							"logonName" : logonName
						}, function(data) {
							if (data == 1) {
								$("#check").html(
										"<font color='red'>登录名不能为空</font>");
								$(o)[0].focus();
								$("#BT_Submit").attr("disabled", "none");
							} else if (data == 2) {
								$("#check").html(
										"<font color='red'>登录名已经存在</font>");
								$(o)[0].focus();
								$("#BT_Submit").attr("disabled", "none");
							} else {
								$("#check").html(
										"<font color='green'>登录名可以使用</font>");
								$("#BT_Submit").attr("disabled", "");
							}
						});
	}
</script>
</head>

<body>

	<form name="Form1" method="post">
		<br>
		<table cellSpacing="1" cellPadding="5" width="680" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">

			<tr>
				<td class="ta_01" align="center" colSpan="4"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>添加用户</strong></font>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff"><input name="logonName"
					type="text" maxlength="25" id="logonName" size="20"
					onblur="checkUser(this);">
					<div id="check"></div></td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff"><input name="userName"
					type="text" maxlength="25" id="userName" size="20"></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">性别：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff"><s:select
						list="#request.sexlist" listKey="ddlCode" listValue="ddlName"
						headerKey="" headerValue="" name="sexID" id="sexID"
						cssStyle="width:155px"></s:select></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">职位：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff"><s:select
						list="#request.postlist" listKey="ddlCode" listValue="ddlName"
						headerKey="" headerValue="" name="postID" id="postID"
						cssStyle="width:155px"></s:select></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">所属单位：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff"><s:select
						list="#request.jctlist" listKey="ddlCode" listValue="ddlName"
						headerKey="" headerValue="" name="jctID" id="jctID"
						cssStyle="width:155px" onchange="findJctUnit(this)"></s:select>
				</td>
				<td align="center" bgColor="#f5fafe" class="ta_01">单位名称：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff"><select id="jctUnitID"
					name="jctUnitID" style="width: 155px"></select></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">密码：</td>
				<td class="ta_01" bgColor="#ffffff"><input name="logonPwd"
					id="logonPwd" type="password" maxlength="25" size="22"></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
				<td class="ta_01" bgColor="#ffffff"><input
					name="passwordconfirm" type="password" maxlength="25" size="22">
				</td>
			</tr>

			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
				<td class="ta_01" bgColor="#ffffff"><input name="birthday"
					id="birthday" type="text" maxlength="50" size="20"
					onClick="WdatePicker()"></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
				<td class="ta_01" bgColor="#ffffff"><input name="address"
					type="text" maxlength="50" size="20"></td>
			</tr>

			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
				<td class="ta_01" bgColor="#ffffff"><input name="contactTel"
					type="text" maxlength="25" size="20"></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">手机：</td>
				<td class="ta_01" bgColor="#ffffff"><input name="mobile"
					type="text" maxlength="25" size="20"></td>
			</tr>

			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
				<td class="ta_01" bgColor="#ffffff"><input name="email"
					type="text" maxlength="50" size="20"></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
				<td class="ta_01" bgColor="#ffffff"><s:select
						list="#request.isDutylist" listKey="ddlCode" listValue="ddlName"
						name="isDuty" id="isDuty" cssStyle="width:155px"></s:select>
				</td>
			</tr>

			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">入职日期：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff"><input name="onDutyDate"
					id="onDutyDate" type="text" maxlength="50" size="20"
					onClick="WdatePicker()"></td>
				<td align="center" bgColor="#ffffff" class="ta_01"></td>
				<td class="ta_01" bgColor="#ffffff"></td>
			</tr>

			<TR>
				<TD class="ta_01" align="center" bgColor="#f5fafe">备注：</TD>
				<TD class="ta_01" bgColor="#ffffff" colSpan="3"><textarea
						name="remark" style="WIDTH: 95%" rows="4" cols="52"></textarea></TD>
			</TR>
			<TR>
				<td align="center" colSpan="4" class="sep1"></td>
			</TR>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4"><input type="button"
					id="BT_Submit" name="BT_Submit" value="保存"
					style="font-size: 12px; color: black;" onClick="check_null()">
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <input
					style="font-size: 12px; color: black;" type="button" value="关闭"
					name="Reset1" onClick="window.close()"></td>
			</tr>
		</table>
	</form>

</body>
</html>
