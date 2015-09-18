<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>新增申请模板</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function saveTemplate() {
		var name = $("#name").val();
		if ($.trim(name) == "") {
			alert("模板名称不能为空！");
			$("#name")[0].focus();
			return false;
		}
		var processDefinitionKey = $("#processDefinitionKey").val();
		if ($.trim(processDefinitionKey) == "") {
			alert("请选择一个流程定义的key值！");
			$("#processDefinitionKey")[0].focus();
			return false;
		}
		var upload = $("#upload").val();
		var extension = upload.substr(upload.indexOf("."));
		if (upload == "") {
			alert("请选择需要上传的文件！");
			return false;
		}
		if (extension != ".doc" && extension != ".docx") {
			alert("上传的文件后缀名必须是doc格式或者是docx格式！");
			return false;
		}
		$("form:first").submit();
	}
</script>
</head>
<body>
	<form name="Form1"
		action="workflow/elecApplicationTemplateAction_save.do" method="post"
		enctype="multipart/form-data">
		<br>
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif"
					colspan="4"><font face="宋体" size="2"><strong>文档模板信息</strong>
				</font>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="left" bgColor="#ffffff" colspan="4"><font
					face="宋体" size="2"> 说明：<br /> 1，模板文件是doc扩展名的文件（Word文档）。<br />
						2，如果是添加：必须要选择模板文件。<br /> 3，如果是修改：只是在 需要更新模板文件时 才选择一个文件。 </font>
				</td>
			</tr>
			<tr height=10>
				<td colspan=4></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">模板名称：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff" colspan="3"><input
					type="text" name="name" size="20" value="" id="name" />
				</td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">流程定义的Key值：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff" colspan="3"><s:if
						test="#request.ProcessDefinitionList!=null&&#request.ProcessDefinitionList.size()>0">
						<s:select list="#request.ProcessDefinitionList"
							name="processDefinitionKey" id="processDefinitionKey"
							headerKey="" headerValue="请选择" listKey="name" listValue="name"
							cssStyle="width:155px"></s:select>
					</s:if>
				</td>
			</tr>

			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">请选择模板文件(doc格式)：<font
					color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff" colspan="3"><input
					type="file" name="upload" id="upload" style="width:450px;" />
				</td>
			</tr>
			<tr height=50>
				<td colspan=4></td>
			</tr>
			<tr height=2>
				<td colspan=4
					background="${pageContext.request.contextPath }/images/b-info.gif"></td>
			</tr>
			<tr height=10>
				<td colspan=4></td>
			</tr>
			<tr>
				<td align="center" colspan=4><input type="button"
					name="BT_Submit" value="保存"
					style="font-size:12px; color:black; height=22;width=55"
					onClick="saveTemplate()"> <INPUT type="button"
					name="Reset1" id="save" value="关闭"
					onClick="opener.location.reload(); window.close();"
					style="width: 60px; font-size: 12px; color: black;">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
