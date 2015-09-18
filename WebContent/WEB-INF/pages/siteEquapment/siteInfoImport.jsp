

<%@ page language="java" pageEncoding="UTF-8"%>



<html lang="zh-CN">
<head>
<title>导入文件</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<SCRIPT language="javascript">
	function successInit() {

		var msg = "null";

		if (msg != "null" && msg != "") {
			//alert(msg);
			//window.close ();
		}

	}
</SCRIPT>

</head>

<body onload="successInit()">
	<form name="importForm" method="post"
		action="station/elecStationAction_importData.do"
		enctype="multipart/form-data">
		<br>
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif"
					colspan=4><font face="宋体" size="2"><strong>Excel文件数据导入</strong></font>
				</td>
			</tr>
			<tr>
				<td width="1%" height=30></td>
				<td width="20%"></td>
				<td width="78%"></td>
				<td width="1%"></td>
			</tr>
			<tr>
				<td width="1%"></td>
				<td width="15%" align="center">请选择文件:</td>
				<td width="83%" align="left"><input type="file" name="file"
					value="" style="width: 365px"></td>
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
				<td align="center" colspan=4><input type="submit" name="import"
					value="导入" style="width: 60px; font-size: 12px; color: black;">
					<INPUT type="button" name="Reset1" id="save" value="关闭"
					onClick="window.opener.reflash(); window.close();"
					style="width: 60px; font-size: 12px; color: black;"></td>
			</tr>
		</table>

		<TABLE cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#ffffff" border="0">

		</TABLE>
	</form>
</body>
</html>
