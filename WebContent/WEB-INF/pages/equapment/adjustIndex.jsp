<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<title>设备校准管理</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet" />
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/pub.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/changePageBackUp.js"></script>

<script language="javascript">
function gotopage(path, where) {
	var page = document.Form1.pageNO;
	//如果传过来的参数不在下列之中，则还是回到当前页(修改和删除完之后)
	if (where == "next") {
		page.value = document.Form1.nextpageNO.value;
	} else if (where == "prev") {
		page.value = document.Form1.prevpageNO.value;
	} else if (where == "end") {

		page.value = document.Form1.sumPage.value;
	} else if (where == "start") {
		page.value = 1;
	} else if (where == "go") {

		var theForm = document.Form1;
		if (Trim(theForm.goPage.value) == "") {
			alert("请输入页数");
			theForm.goPage.focus();
			return false;
		}
		var objgo = parseInt(theForm.goPage.value);
		var objsum = parseInt(theForm.sumPage.value);
		if (objgo<=0||objgo>objsum) {
			alert("不存在此页，请重新输入页数");
			theForm.goPage.focus();
			return false;
		}
		page.value = theForm.goPage.value;
	}
	document.F1.pageNO.value = document.Form1.pageNO.value;
	Pub.submitActionWithForm('Form1', path, 'F1');
}
	function searchdeviceX() {
		//changeformvalue();
		Pub.submitActionWithForm('Form1', 'equapment/elecAdjustAction_home.do', 'F1');
	}

	function returnMethod() {
		return confirm("确认要删除吗？");
	}
	function exportExcel() {
		//所属单位
		var jctID = document.getElementById("jctId").value;
		//设备名称
		var devName = document.getElementById("devName").value;
		devName = encodeURI(devName, "UTF-8");
		//校准状态
		var isHaving = document.getElementById("apstate").value;
		//设备类型
		var devType = document.getElementById("devType").value;
		openWindow(
				'${pageContext.request.contextPath }/equapment/elecAdjustAction_exportExcel.do?devName='
						+ devName
						+ '&jctID='
						+ jctID
						+ '&devType='
						+ devType
						+ '&isHaving='
						+ isHaving
						, '700', '400');
	}
</script>
<style type="text/css">
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
</style>
</HEAD>
<body>

	<form name="F1" method="post" id="F1" style="margin: 0px;">

		<s:hidden name="initPage" value="1" />
		<input type="hidden" name="pageNO" value="">
		<table cellSpacing="0" cellPadding="0" width="90%" align="center"
			bgColor="#f5fafe" border="0">
			<tr>
				<td class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					&nbsp;<a href="equapment/elecAdjustAction_home.do" class="cl_01"><font
						face="宋体" size="2"><strong>校准管理</strong></font></a> |<a
					href="equapment/elecRepairAction_home.do" class="cl_01"><font
						face="宋体" size="2">检修管理</font></a>
				</td>
			</tr>
			<TR height=10>
				<td></td>
			</TR>
			<tr>
				<td colspan="2">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td width=15% class="ta_01" align="center" bgColor="#f5fafe"
								height="22">所属单位：</td>
							<td width=35% class="ta_01"><s:select
									list="#request.jctList" name="jctID" id="jctId" cssClass="bg"
									headerKey="0" headerValue="全部" listKey="ddlCode"
									listValue="ddlName"></s:select></td>
							<td width=15% class="ta_01" align="center" bgColor="#f5fafe"
								height="22">设备名称：</td>
							<td width=35% class="ta_01"><input name="devName"
								type="text" id="devName" size="19" value=""></td>
						</tr>
						<tr>
							<td width=15% class="ta_01" align="center" bgColor="#f5fafe"
								height="22">校准状态：</td>
							<td width=35% class="ta_01"><s:select
									list="#request.isHavingList" name="isHaving" id="apstate"
									cssClass="bg" onChange="shareOnChange(this)" headerKey="0"
									headerValue="全部" listKey="ddlCode" listValue="ddlName"></s:select>
							</td>
							<td width=15% class="ta_01" align="center" bgcolor="#f5fafe"
								height="22">设备类型：</td>
							<td class="ta_01"><s:select list="#request.devTypeList"
									name="devType" id="devType" cssClass="bg" headerKey="0"
									headerValue="全部" listKey="ddlCode" listValue="ddlName"></s:select>
							</td>
						</tr>

						<tr id=PPassword style="DISPLAY: none">
							<td width=15% class="ta_01" align="center" bgColor="#f5fafe"
								height="22">校准日期：</td>
							<td width=35% class="ta_01"><input name="startDatef"
								type="text" id="startDatef" size="10" onclick="WdatePicker()"
								value=""> ~ <input name="startDatet" type="text"
								id="startDatet" size="10" onclick="WdatePicker()" value="">
							</td>
							<td width=15% class="ta_01" align="center" height="22"></td>
							<td class="ta_01"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<br>
	<form name="Form1" method="post" id="Form1" style="margin: 0px;">
		<table cellSpacing="0" cellPadding="0" width="90%" align="center"
			bgColor="#f5fafe" border="0">

			<TR>
				<TD align="center"
					background="${pageContext.request.contextPath }/images/cotNavGround.gif"
					width=25><img
					src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
				<TD class="DropShadow"
					background="${pageContext.request.contextPath }/images/cotNavGround.gif"
					width=80>校准记录列表</TD>
				<td align="right" class="ta_01"><input type="button"
					name="BT_Search" value="查询" id="BT_Search"
					onclick="searchdeviceX()" style="font-size: 12px; color: black;">

					<input style="font-size: 12px; color: black;" id="BT_Export"
					type="button" value="导出" name="BT_Export" onclick="exportExcel()">&nbsp;&nbsp;
					<input name="BT_Add" type="button"
					style="font-size: 12px; color: black;" id="BT_Add"
					onClick="openWindow('equapment/elecAdjustAction_adjustMoreAdd.do',800,450,'添加');"
					value="批量添加" /> <input style="font-size: 12px; color: black;"
					id="BT_Export" type="button" value="导出设置" name="BT_Export"
					onclick="openWindow('${pageContext.request.contextPath }/system/elecExportFieldsAction_setExportExcel.do?belongTo=1-2','700','400')">&nbsp;&nbsp;
				</td>
			</TR>
		</TABLE>

		<table cellSpacing="0" cellPadding="0" width="90%" align="center"
			bgColor="#f5fafe" border="0">
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td align="center" width="5%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
							<td align="center" width="23%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">设备名</td>
							<td align="center" width="9%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">校准周期</td>
							<td align="center" width="9%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">使用日期</td>
							<td align="center" width="9%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">校准状态</td>
							<td align="center" width="11%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">校准日期</td>
							<td align="center" width="13%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>

							<td width="16%" align="center" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">校准记录维护</td>

						</tr>

						<s:iterator value="#request.adjustList" status="item">
							<tr onMouseOver="this.style.backgroundColor = 'white'"
								onMouseOut="this.style.backgroundColor = '#F5FAFE';">
								<td align="center" width="5%"><s:property
										value="#item.count" /></td>
								<td align="center"><a href="javascript:;"
									onClick="openWindow('equapment/elecEquapmentAction_view.do?equapmentID=<s:property value='adjustID' />',800,450,'设备校准周期添加');"
									class="cl_01"> <s:property value="devName" /></a></td>
								<td align="center"><s:property value="adjustPeriod" /> <s:property
										value="apunit" /></td>
								<td align="center"><s:date name="useDate"
										format="yyyy-MM-dd" /></td>
								<td align="center"><s:property value="isHaving" /></td>
								<td align="center" width="11%"><s:date name="startDate"
										format="yyyy-MM-dd" /></td>
								<td align="center" style="HEIGHT: 22px"><s:property
										value="jctID" /></td>


								<td align="center" style="HEIGHT: 22px"><a href="javascript:"
									onclick="openWindow('equapment/elecAdjustAction_add.do?adjustID=<s:property value='adjustID'/>',800,450,'添加');"
									class="cl_01">添加 </a><a href=""
									onClick="openWindow('equapment/elecAdjustAction_edit.do?adjustID=<s:property value='adjustID' />',800,450,'设备校准周期添加');"
									class="cl_01">修改</a> <a onclick="return returnMethod()"
									href="javascript:Pub.submitActionWithForm('Form1','equapment/elecAdjustAction_delete.do?adjustID=<s:property value='adjustID' />','F1')"
									class="cl_01">删除</a> <a href="javascript:;"
									onClick="openWindow('equapment/elecAdjustAction_view.do?adjustID=<s:property value='adjustID' />',650,300,'设备校准历史记录');"
									class="cl_01">查看</a></td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>


			<tr>
				<td width="100%" height="1" colspan="2">
					<table border="0" width="100%" cellspacing="0" cellpadding="0">
						<s:if test="#request.page!=null">
							<tr>
								<td width="15%" align="left">总记录数：<s:property
										value="%{#request.page.totalResult}" />条
								</td>
								<td width="14%" align="right"></td>
								<s:if test="#request.page.firstPage">
									<td width="8%" align="center">首页&nbsp;&nbsp;|</td>
									<td width="10%" align="center">上一页&nbsp;&nbsp;&nbsp;|</td>
								</s:if>
								<s:else>
									<td width="8%" align="center"><u><a href="#"
											onclick="gotopage('equapment/elecAdjustAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopage('equapment/elecAdjustAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
								</s:else>
								<s:if test="#request.page.lastPage">
									<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
									<td width="8%" align="center">末页</td>
								</s:if>
								<s:else>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopage('equapment/elecAdjustAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
									<td width="8%" align="center"><u><a href="#"
											onclick="gotopage('equapment/elecAdjustAction_home.do','end')">末页</a></u></td>
								</s:else>
								<td width="6%" align="center">第<s:property
										value="%{#request.page.pageNo}" />页
								</td>
								<td width="6%" align="center">共<s:property
										value="%{#request.page.sumPage}" />页
								</td>
								<td width="21%" align="right">至第<input size="1" type="text"
									name="goPage">页 <u><a href="#"
										onclick="gotopage('equapment/elecAdjustAction_home.do','go')">确定</a></u></td>

								<td><input type="hidden" name="pageNO"
									value="${page.pageNo }"></td>
								<td><input type="hidden" name="prevpageNO"
									value="${page.pageNo-1 }"></td>
								<td><input type="hidden" name="nextpageNO"
									value="${page.pageNo+1 }"></td>
								<td><input type="hidden" name="sumPage"
									value="${page.sumPage }"></td>
							</tr>
						</s:if>

					</table>
				</td>
			</tr>


		</table>
	</form>
</body>
</HTML>