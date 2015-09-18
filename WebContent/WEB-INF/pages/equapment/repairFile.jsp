<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
<HEAD>
<title>上传文件</title>
<meta name="GENERATOR" Content="Microsoft Visual Studio .NET 7.1">
<meta name="CODE_LANGUAGE" Content="C#">
<meta name="vs_defaultClientScript" content="JavaScript">
<meta name="vs_targetSchema"
	content="http://schemas.microsoft.com/intellisense/ie5">
<LINK href="../css/Style.css" type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/public.js"
	charset="gb2312"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"
	charset="gb2312"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type='text/javascript'
	src="${pageContext.request.contextPath }/script/validate.js"
	charset="gb2312"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/pub.js"
	charset="gb2312"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/GB2312Codec.js"
	charset="gb2312"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/changePageBackUp.js"
	charset="gb2312"></script>
<script language="javascript">
	function addFileList() {
		//debugger ;					   	  

		var tbl = document.getElementById("files");
		fileName = tbl.value;
		if (fileName == "") {
			alert("请选择要上传的文件路径！");
			document.getElementById("files").focus();
			return false;
		}

		if (overWriteFile("files", "DataGrid1")) {
			document.Form1.submit();
		} else
			return false;
	}

	function overWriteFile(tabName1, tabName2) {
		var tbl = document.getElementById(tabName1);
		var tb2 = document.getElementById(tabName2);
		var fileName1, fileName2;

		//检查"添加上传文件列表"与"已上传文件列表"中是否有重名文件			
		fileName1 = tbl.value; //准备上传的完整文件路径
		fileName1 = fileName1.substr(fileName1.lastIndexOf("\\") + 1); //文件名

		if (fileName1.lastIndexOf("'") != -1) {
			alert("上传文件名带有'错误字符'");
			return false;
		}
		for (j = 1; j < tb2.rows.length; j++) {
			fileName2 = tb2.rows[j].cells[0].children.item(0).innerText; //已经上传的文件名
			if (Trim(fileName1) == Trim(fileName2)) //存在重名文件
			{
				if (confirm("待上传的文件\"" + fileName1 + "\"已经存在，是否覆盖"))
					return false;
				else
					return false;
			} else
				continue;
		}
		return true;
	}

	function getUrl(url, fileUploadID) {

		var strUrl = url + "?fileUploadID=" + fileUploadID;
		OpenWindow('ggg', strUrl, 800, 450);
	}

	function setDelfile(filename) {
		document.Form1.delfilename.value = filename;
	}
	function returnMethod() {
		return confirm("确认要删除吗？");
	}
</script>

</HEAD>
<body>

	<br>
	<form name="Form1"
		action="${pageContext.request.contextPath }/station/elecFileUploadAction_repairUpload.do"
		method="post" enctype="multipart/form-data">
		<s:hidden name="repairID" value="%{#request.repair.repairID}" />
		<table cellSpacing="1" cellPadding="0" width="700" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" background="../images/b-info.gif"
						colspan=3><font face="宋体" size="2"><strong>文件上传管理</strong></font>
					</td>
				</tr>
				<tr height=10>
					<td colspan=3></td>
				</tr>
				<tr>
					<td colspan=3>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">

							<tr>
								<td width="100" class="ta_01" align="center" bgColor="#ffffff"
									height="22">&nbsp;&nbsp; 选择文件：</td>
								<td width="100" class="ta_01" align="right" bgColor="#ffffff"
									height="22"><INPUT type="file" name="files" id="files"></td>
								<td class="ta_01" align="right" bgColor="#ffffff" colspan=1>
									<INPUT type="button" name="BT_Search" value=" 添加"
									id="BT_Search" onclick="addFileList();"
									style="font-size: 12px; color: black;">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr height=10>
					<td colspan=3 bgcolor="#ffffff"></td>
				</tr>
				<tr>
					<TD align="center" background="../images/cotNavGround.gif"><img
						src="../images/yin.gif" width="15"></TD>
					<TD class="DropShadow" align="left"
						background="../images/cotNavGround.gif" width=100>上传文件列表</TD>
					<TD width="80%"></TD>
				</tr>

				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>

						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">

							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="left" width="71%"
									background="../images/tablehead.jpg" height=20>文件名称</td>

								<td align="center" width="28%"
									background="../images/tablehead.jpg" height=20>删除</td>

							</tr>
							<s:iterator value="#request.fileUploadList">
								<tr onMouseOver="this.style.backgroundColor = 'white'"
									onMouseOut="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="left" width="71%">
										<a style="color: blue" href="javascript:;"
										onclick="getUrl('station/elecFileUploadAction_repairDownload.do', '<s:property value="fileUploadID"/>')">
											<s:property value="fileFileName" />
									</td>
									<td align="center"><a
										href="atation/elecFileUploadAction_repairDelete.do?fileUploadID=<s:property value="fileUploadID"/>"
										onclick="return returnMethod()"> <img
											src="../images/delete.gif" width="15" height="14" border="0"
											style="CURSOR: hand">
									</a></td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgcolor="#f5fafe" colspan="3"><font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</font>&nbsp;<input type="reset" value=" 上传" id="close" name="close"
						onClick="window.close();" style="font-size: 12px; color: black;">
						<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>&nbsp;<input
						type="reset" value=" 关闭" id="Reset1" name="Reset1"
						onClick="window.close();" style="font-size: 12px; color: black;">
					</td>
				</tr>

			</TBODY>
		</table>
	</form>
</body>
</HTML>