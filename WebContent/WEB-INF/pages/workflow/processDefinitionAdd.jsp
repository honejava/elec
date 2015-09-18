<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>部署流程定义</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function deployProcess() {
		var upload = $("#upload").val();
		var extension = upload.substr(upload.indexOf("."));
		if (upload == "") {
			alert("请选择需要上传的文件！");
			return false;
		}
		if (extension != ".zip") {
			alert("上传的文件后缀名必须是.zip 格式！");
			return false;
		}
		$("form:first").submit();
	}
</script>
</head>
<body>
	<form name="Form1"
		action="${pageContext.request.contextPath }/workflow/elecProcessDefinitionAction_save.do"
		method="post" enctype="multipart/form-data">
		<br>
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif"
					colspan="4"><font face="宋体" size="2"><strong>部署流程定义</strong>
				</font>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="left" bgColor="#f5fafe" colspan="4"><font
					face="宋体" size="2"> 说明：只接受zip扩展名的文件。 </font>
				</td>
			</tr>
			<tr>
				<td width="80%" align="left">请选择流程定义文档(zip格式):</td>
			</tr>
			<tr>
				<td width="80%"><input type="file" name="upload" id="upload"
					style="width:450px;" /> *</td>
				<td width="1%"></td>
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
					onClick="deployProcess()"> <INPUT type="button"
					name="Reset1" id="save" value="关闭"
					onClick="opener.location.reload(); window.close();"
					style="width: 60px; font-size: 12px; color: black;">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
