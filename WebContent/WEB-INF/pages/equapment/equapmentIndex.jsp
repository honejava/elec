<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<head>
<title>仪器设备管理</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet" />
<script language="javascript"
	src="${pageContext.request.contextPath }/script/public.js"
	charset="gb2312"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/pub.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/changePageBackUp.js"></script>

<SCRIPT type="text/javascript">
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

		//新增站点时，应去最后一页，在此做标记
		if (where == "addnewstation") {
			document.Form1.addnewflag.value = 1;
		}
		document.F1.pageNO.value = document.Form1.pageNO.value;
		Pub.submitActionWithForm('Form1', path, 'F1');

	}
	function gotosearchDevice() {
		Pub.submitActionWithForm('Form1',
				'equapment/elecEquapmentAction_home.do', 'F1');
	}
	function exportExcel() {
		//所属单位
		var jctID = document.getElementById("jctId").value;
		//设备名称
		var devName = document.getElementById("devName").value;
		devName = encodeURI(devName, "UTF-8");
		//设备类型
		var devType = document.getElementById("devType").value;
		//使用日期
		var useDatef = document.getElementById("useDatef").value;
		var useDatet = document.getElementById("useDatet").value;
		//设备状态
		var devState = document.getElementById("devState").value;
		//计划时间
		var planDatef = document.getElementById("planDatef").value;
		var planDatet = document.getElementById("planDatet").value;

		//使用日期
		var devType = document.getElementById("devType").value;
		openWindow(
				'${pageContext.request.contextPath }/equapment/elecEquapmentAction_exportExcel.do?devName='
						+ devName
						+ '&jctID='
						+ jctID
						+ '&devType='
						+ devType
						+ '&useDatef='
						+ useDatef
						+ '&useDatet='
						+ useDatet
						+ '&devState='
						+ devState
						+ '&planDatef='
						+ planDatef
						+ '&planDatet=' + planDatet, '700', '400');
	}
</SCRIPT>
</head>

<body>
	<form name="F1" method="post" id="F1" style="margin: 0px;">
		<s:hidden name="initPage" value="1" />
		<input type="hidden" name="typeView" value=""> <input
			type="hidden" name="direction" value=""> <input type="hidden"
			name="pageNO" value=""> <input type="hidden" name="sumPage"
			value=""> <input type="hidden" name="lastRecordIndex"
			value=""> <input type="hidden" name="changeFlag" value="">
		<input type="hidden" name="goPage" value=""> <input
			type="hidden" name="plantodev" value="">


		<table cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<tr>
				<td class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>仪器设备管理</strong></font>
				</td>
			</tr>
			<TR height=10>
				<td></td>
			</TR>

			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td width="100" class="ta_01" align="center" bgcolor="#f5fafe"
								height="22">所属单位：</td>
							<td class="ta_01"><s:select list="#request.jctList"
									name="jctID" id="jctId" cssClass="bg"
									onChange="JctSelectChange(this)" headerKey="0" headerValue="全部"
									listKey="ddlCode" listValue="ddlName"></s:select></td>
							<td width="100" class="ta_01" align="center" bgcolor="#f5fafe"
								height="22">设备名称：</td>
							<td class="ta_01"><input name="devName" type="text"
								id="devName" size="28" value="" /></td>
						</tr>
						<tr>
							<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">设备类型：</td>
							<td class="ta_01" width="300"><s:select
									list="#request.devTypeList" name="devType" id="devType"
									cssClass="bg" onChange="DevTypeSelectChange(this)"
									headerKey="0" headerValue="全部" listKey="ddlCode"
									listValue="ddlName"></s:select> &nbsp;&nbsp;&nbsp;&nbsp; <span
								id="devTuzhi" style="DISPLAY: none"> <input id="btnTuzhi"
									type="button" value=" 详细" name="btnTuzhi"
									onclick="upload('5');" style="font-size: 12px; color: black;">
							</span></td>

							<td width="100" class="ta_01" align="center" bgcolor="#f5fafe"
								height="22">使用日期：</td>
							<td class="ta_01" width="247"><input name="useDatef"
								type="text" id="useDatef" size="10" onclick="WdatePicker()">
								~ <input name="useDatet" type="text" id="useDatet" size="10"
								onclick="WdatePicker()"></td>
						</tr>
						<tr>
							<td width="100" class="ta_01" align="center" bgcolor="#f5fafe"
								height="22">设备状态：</td>
							<td class="ta_01" align="left"><font face="宋体" color="red">
									<s:select list="#request.devStateList" name="devState"
										id="devState" cssClass="bg" headerKey="0" headerValue="全部"
										listKey="ddlCode" listValue="ddlName"></s:select>
							</font></td>
							<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">计划时间：
							</td>
							<td class="ta_01" width="300"><input name="planDatef"
								type="text" id="planDatef" size="10" value=""
								onclick="WdatePicker()"> ~ <input name="planDatet"
								type="text" id="planDatet" size="10" value=""
								onclick="WdatePicker()"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<br>
	<form name="Form1" method="post" id="Form1" style="margin: 0px;">
		<table cellpadding="0" cellspacing="0" border="0" width="90%"
			align="center">
			<TR>
				<TD align="center"
					background="${pageContext.request.contextPath }/images/cotNavGround.gif"
					width="25"><img
					src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
				<TD class="DropShadow"
					background="${pageContext.request.contextPath }/images/cotNavGround.gif"
					width="80">设备信息列表</TD>
				<td align="right" class="ta_01"><INPUT name="BT_Search"
					type="button" id="BT_Search" value="查询"
					style="font-size: 12px; color: black;" onclick="gotosearchDevice()">
					<input style="font-size: 12px; color: black;" id="BT_Export"
					type="button" value="导出" name="BT_Export" onclick="exportExcel()">&nbsp;&nbsp;
					<input style="font-size: 12px; color: black;" id="BT_Export"
					type="button" value="导出设置" name="BT_Export"
					onclick="openWindow('${pageContext.request.contextPath }/system/elecExportFieldsAction_setExportExcel.do?belongTo=1-1','700','400')">&nbsp;&nbsp;
















				
			</TR>
		</TABLE>
		<table cellpadding="0" cellspacing="0" border="0" width="90%"
			align="center">
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td width="11%" align="center" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
							<td width="15%" align="center" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">名称</td>
							<td width="14%" align="center" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">规格型号</td>
							<td align="center" width="8%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">设备状态</td>
							<td align="center" width="10%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">使用日期</td>
							<td align="center" width="10%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">检修周期</td>
							<td align="center" width="9%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>


							<td width="6%" align="center" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
							<td width="6%" align="center" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>

						</tr>


						<s:iterator value="#request.equapmentList" status="item">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td align="center"><s:property value="#item.count" /></td>
								<td align="center"><a href="javascript:;"
									onClick="openWindow('equapment/elecEquapmentAction_view.do?equapmentID=<s:property value="equapmentID" />',800,450,'设备详细信息');"
									class="cl_01"> <s:property value="devName" /></a></td>
								<td align="left"><s:property value="specType" /></td>
								<td align="center" width="8%"><s:property value="devState" /></td>
								<td align="center" width="10%"><s:date name="useDate"
										format="yyyy-MM-dd" /></td>
								<td align="center" width="10%"><s:property
										value="overhaulPeriod" /> <s:property value="opunit" /></td>
								<td align="center" width="9%" style="HEIGHT: 22px"><s:property
										value="jctID" /></td>
								<td align="center" style="HEIGHT: 22px"><a
									href="javascript:;"
									onClick="openWindow('equapment/elecEquapmentAction_edit.do?equapmentID=<s:property value="equapmentID" />',800,450,'设备详细信息');">
										<IMG src="${pageContext.request.contextPath }/images/edit.gif"
										style="CURSOR: hand" border="0">
								</a></td>
								<td align="center" style="HEIGHT: 22px"><a
									href="javascript:Pub.submitActionWithForm('Form1','equapment/elecEquapmentAction_delete.do?equapmentID=<s:property value="equapmentID" />','F1')"
									onclick="returnMethod()"> <IMG
										src="${pageContext.request.contextPath }/images/delete.gif"
										style="CURSOR: hand" border="0"></a></td>
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
											onclick="gotopage('equapment/elecEquapmentAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopage('equapment/elecEquapmentAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
								</s:else>
								<s:if test="#request.page.lastPage">
									<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
									<td width="8%" align="center">末页</td>
								</s:if>
								<s:else>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopage('equapment/elecEquapmentAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
									<td width="8%" align="center"><u><a href="#"
											onclick="gotopage('equapment/elecEquapmentAction_home.do','end')">末页</a></u></td>
								</s:else>
								<td width="6%" align="center">第<s:property
										value="%{#request.page.pageNo}" />页
								</td>
								<td width="6%" align="center">共<s:property
										value="%{#request.page.sumPage}" />页
								</td>
								<td width="21%" align="right">至第<input size="1" type="text"
									name="goPage">页 <u><a href="#"
										onclick="gotopage('equapment/elecEquapmentAction_home.do','go')">确定</a></u></td>

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

</html>

