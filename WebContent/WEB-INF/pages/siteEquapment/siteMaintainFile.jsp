<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
<HEAD>
<title>上传文件</title>
<LINK href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
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
	function init() {

		var jinduFlag = document.Form1.jindu.value;
		if (jinduFlag == "jindu") {
			PPassword.style.display = "";
			PPassword1.style.display = "";
		}

		var nowdate = new Date();
		var obj1 = document.Form1.proYear;

		for (i = 2000; i <= nowdate.getFullYear(); i++) {
			obj1.options.add(new Option(i, i));
		}

		if (document.Form1.proYear.value != "0"
				&& document.Form1.proMonth.value != "0"
				&& document.Form1.proWeek.value != "0") {
			document.Form1.proMonth.disabled = false;
			document.Form1.proWeek.disabled = false;
		}
	}

	function addFile() {
		alert(opener.document.Form1.fn.value);
		document.Form1.action = "addlist.do?fn="
				+ opener.document.Form1.fn.value;
		document.Form1.submit();
	}

	function insertRows() {

		var tempRow = 0;
		var tbl = document.getElementById("filesTbl");
		tempRow = tbl.rows.length;
		var Rows = tbl.rows;//类似数组的Rows 
		var newRow = tbl.insertRow(tbl.rows.length);//插入新的一行 
		var Cells = newRow.cells;//类似数组的Cells 
		for (i = 0; i < 3; i++)//每行的3列数据 
		{
			var newCell = Rows[newRow.rowIndex].insertCell(Cells.length);
			newCell.align = "center";
			switch (i) {
			case 0:
				newCell.innerHTML = "" + tempRow + "";
				break;
			case 1:
				newCell.innerHTML = "<input name=\"files"
						+ "\"  type=\"file\" id=\"" + tempRow
						+ "\" size=\"45\" onblur='checkItem(this)'>";
				break;
			case 2:
				newCell.innerHTML = "<a href='javascript:delTableRow(\""
						+ tempRow
						+ "\")'><img src=\"${pageContext.request.contextPath }/images/delete.gif\" width=15 height=14 border=0 style=CURSOR:hand></a>";
				break;
			}
		}
	}

	function delTableRow(rowNum) {

		var tbl = document.getElementById("filesTbl");

		if (tbl.rows.length > rowNum) {

			tbl.deleteRow(rowNum);

			for (i = rowNum; i < tbl.rows.length; i++) {
				tbl.rows[i].cells[0].innerHTML = i;
				tbl.rows[i].cells[2].innerHTML = "<a href='javascript:delTableRow(\""
						+ i
						+ "\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>";
			}
		}
	}

	function addFileList() {
		if (document.Form1.jindu.value == "jindu") {
			if (document.Form1.proYear.value == "0") {
				alert("请选择投资年份！");
				return false;
			}
			if (document.Form1.proMonth.value == "0") {
				alert("请选择投资月份！");
				return false;
			}
			if (document.Form1.proWeek.value == "0") {
				alert("请选择投资周！");
				return false;
			}

		}
		var tbl = document.getElementById("filesTbl");
		for (i = 1; i < tbl.rows.length; i++) {
			var filePath = tbl.rows[i].cells[1].children.item(0).value;
			if (filePath == "") {
				alert("请选择第" + i + "行的文件路径！");
				return false;
			}
		}
		var planID=document.getElementById("planID").value;
		if(planID==null||planID==""){
			alert("抱歉，你不可以上传文件");
			return;
		}
		if (overWriteFile("filesTbl", "filesTb2")) {
			document.Form1.action = "station/elecFileUploadAction_load.do";
			document.Form1.submit();
		} else
			return false;

	}

	function overWriteFile(tabName1, tabName2) {
		var tbl = document.getElementById(tabName1);
		var tb2 = document.getElementById(tabName2);
		var fileName1, fileName2;

		//1.检查"添加上传文件列表"中的文件是否有重名

		for (i = 1; i < tbl.rows.length; i++) {
			fileName1 = tbl.rows[i].cells[1].children.item(0).value; //准备上传的完整文件路径

			fileName1 = fileName1.substr(fileName1.lastIndexOf("\\") + 1); //文件名

			if (fileName1.lastIndexOf("'") != -1) {
				alert("上传文件名带有'错误字符'");
				return false;
			}
			for (j = i + 1; j < tbl.rows.length; j++) {
				fileName2 = tbl.rows[j].cells[1].children.item(0).value;
				fileName2 = fileName2.substr(fileName2.lastIndexOf("\\") + 1);

				if (Trim(fileName1) == Trim(fileName2)) {
					alert("添加上传文件列表中存在与\"" + fileName1 + "\"的重名文件");
					return false;
				}
			}
		}

		//2.检查"添加上传文件列表"与"已上传文件列表"中是否有重名文件			
		for (i = 1; i < tbl.rows.length; i++) {
			fileName1 = tbl.rows[i].cells[1].children.item(0).value; //准备上传的完整文件路径

			fileName1 = fileName1.substr(fileName1.lastIndexOf("\\") + 1); //文件名

			for (j = 1; j < tb2.rows.length; j++) {
				fileName2 = tb2.rows[j].cells[1].children.item(0).innerText; //已经上传的文件名
				if (fileName1 == fileName2) //存在重名文件
				{
					if (confirm("待上传的文件\"" + fileName1 + "\"已经存在，是否覆盖"))
						return false;
					else
						return false;
				} else
					continue;
			}
		}

		return true;
	}

	function search() {
		if (document.Form1.proYear.value != "0"
				&& document.Form1.proMonth.value == "0") {
			alert("请选择投资月份！");
			return false;
		}
		if (document.Form1.proMonth.value != "0"
				&& document.Form1.proWeek.value == "0") {
			alert("请选择投资周！");
			return false;
		}
		document.Form1.action = "visualizeJinduSearch.do";
		document.Form1.submit();
	}

	function clearMonthWeek() {
		document.Form1.proMonth.disabled = false;
		if (document.Form1.proYear.value == "0") {
			document.Form1.proMonth.value = "0";
			document.Form1.proWeek.value = "0";
			document.Form1.proMonth.disabled = true;
			document.Form1.proWeek.disabled = true;
		}
	}

	function clearWeek() {
		document.Form1.proWeek.disabled = false;
		if (document.Form1.proMonth.value == "0") {
			document.Form1.proWeek.value = "0";
			document.Form1.proWeek.disabled = true;
		}
	}

	function delFile(strUrl) {
		if (confirm('确认要删除？')) {
			return true;
		}
		return false;
	}

	function getUrl(fileUploadID) {
		strUrl = "station/elecFileUploadAction_vw.do?fileUploadID=" + fileUploadID;
		OpenWindow('ggg', strUrl, 800, 450);
	}

	function setDelfile(filename) {
		document.Form1.delfilename.value = filename;
	}
</script>
</HEAD>
<body onload="init()">
	<br>
	<form name="Form1" method="post" enctype="multipart/form-data">
		<s:hidden name="elecPlan.planID" id="planID"
			value="%{#request.plan.planID}" />
		<table cellSpacing="0" cellPadding="0" width="700" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center"
						background="${pageContext.request.contextPath }/images/b-info.gif"
						colspan=3><font face="宋体" size="2"><strong>文件上传管理</strong></font>
					</td>
				</tr>
				<tr height=10>
					<td colspan=3></td>
				</tr>
				<tr>
					<td colspan=3><input type="hidden" name="jindu" value="">
						<input type="hidden" name="projId" value=""> <span
						id=PPassword1 style="DISPLAY: none">
							<table cellspacing="0" width=700 cellpadding="1"
								bordercolor="gray" border="0" id="filesTb3">
								<tr style="FONT-SIZE: 12pt; HEIGHT: 25px;">
									<td class="ta_01" colspan=2>投资时间选择： <select name="proYear"
										id="proYear" onchange="clearMonthWeek()">
											<option value="0"></option>
									</select>年 <select name="proMonth" id="proMonth" onchange="clearWeek()"
										disabled>
											<option value="0"></option>
											<option value="01">1</option>
											<option value="02">2</option>
											<option value="03">3</option>
											<option value="04">4</option>
											<option value="05">5</option>
											<option value="06">6</option>
											<option value="07">7</option>
											<option value="08">8</option>
											<option value="09">9</option>
											<option value="10">10</option>
											<option value="11">11</option>
											<option value="12">12</option>
									</select>月 <select name="proWeek" id="proWeek" disabled>
											<option value="0"></option>
											<option value="1">第一周</option>
											<option value="2">第二周</option>
											<option value="3">第三周</option>
											<option value="4">第四周</option>
									</select>&nbsp;<font color="#FF0000">*</font>

									</td>
								</tr>
								<tr>
									<TD height=10></TD>
								</tr>
							</table>
					</span></td>
				</tr>

				<tr>

					<TD align="center"
						background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img
						src="${pageContext.request.contextPath }/images/yin.gif"
						width="15"></TD>
					<TD class="DropShadow" align="left"
						background="${pageContext.request.contextPath }/images/cotNavGround.gif"
						width=100>添加上传文件列表</TD>


					<TD width="80%" align="right"><input type="button"
						name="BT_Add" value="添加" id="BT_Add" onclick="insertRows();"
						style="font-size: 12px; color: black;"> <span id=PPassword
						style="DISPLAY: none"> <input type="button" name="BT_Add"
							value="查询" id="BT_Add" onclick="search()"
							style="font-size: 12px; color: black;">
					</span> <input type="button" name="BT_Submit" value="上传" id="BT_Submit"
						onclick="addFileList();" style="font-size: 12px; color: black;">

						<input type="button" value="关闭" onClick="window.close();"
						style="font-size: 12px; color: black;"></TD>
				</tr>

				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="filesTbl"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">

							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td class="ta_01" align="center" width="20%"
									background="${pageContext.request.contextPath }/images/tablehead.jpg"
									height=20>编号</td>

								<td class="ta_01" align="center" width="60%"
									background="${pageContext.request.contextPath }/images/tablehead.jpg"
									height=20>选择待上传文件</td>
								<td class="ta_01" align="center" width="20%"
									background="${pageContext.request.contextPath }/images/tablehead.jpg"
									height=20>删除</td>
							</tr>


						</table>
					</td>
				</tr>

				<tr height=10>
					<td colspan=3 bgcolor="#ffffff"></td>
				</tr>
				<tr>
					<TD align="center"
						background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img
						src="${pageContext.request.contextPath }/images/yin.gif"
						width="15"></TD>
					<TD class="DropShadow" align="left"
						background="${pageContext.request.contextPath }/images/cotNavGround.gif"
						width=100>已上传文件列表</TD>
					<TD width="80%"></TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="filesTb2"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">

							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td class="ta_01" align="center" width="20%"
									background="${pageContext.request.contextPath }/images/tablehead.jpg"
									height=20>编号</td>
								<td class="ta_01" align="center" width="60%"
									background="${pageContext.request.contextPath }/images/tablehead.jpg"
									height=20>已上传文件</td>

								<td class="ta_01" align="center" width="20%"
									background="${pageContext.request.contextPath }/images/tablehead.jpg"
									height=20>删除</td>
							</tr>
							<s:iterator value="#request.fileUploadList" status="item">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td class="ta_01" align="center" width="20%"><s:property
											value="#item.count" /></td>
									<td class="ta_01" align="center" width="60%"><a
										href="javascript:;"
										onclick="getUrl('<s:property
											value="fileUploadID" />')"><s:property
												value="fileFileName" /></a></td>
									<td align="center" style="HEIGHT: 21px"><a
										href="station/elecFileUploadAction_del.do?fileUploadID=<s:property
											value='fileUploadID' />"
										onclick="return delFile()"> <img
											src="../images/delete.gif" width="15" height="14" border="0"
											style="CURSOR: hand">
									</a></td>
								</tr>
							</s:iterator>
						</table> <input type="hidden" name="delfilename">
					</td>
				</tr>

			</TBODY>
		</table>
	</form>
</body>
</HTML>

