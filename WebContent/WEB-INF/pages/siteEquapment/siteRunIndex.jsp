<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>运行情况</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/pub.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
	var stations = new Array();
	function init() {

		stations[0] = "3|4028d09318ee4f450118ee559e9d0001|站点名称1|站点代号1|安装地点"
				.split("|");

		stations[1] = "9|4028d09318ee4f450118ee5616ab0002|站点名称|站点名称|站点名称"
				.split("|");

		stations[2] = "13|4028d09318ee4f450118ee56afaf0003|站点名称01|站点名称01|站点名称"
				.split("|");

		stations[3] = "13|4028d09318ee4f450118ee570cd30004|站点名称02|站点名称02|站点名称"
				.split("|");

		stations[4] = "13|4028d09318ee4f450118ee57b0370005|站点名称03|站点名称03|站点名称"
				.split("|");

		stations[5] = "13|4028d09318ee4f450118eeca14070006|站点名称07|站点名称07|"
				.split("|");

		stations[6] = "13|4028d09318ee4f450118eeca984a0007|站点名称08|站点名称08|"
				.split("|");

		stations[7] = "13|4028d09318ee4f450118eecb83180008|站点名称09|站点名称09|"
				.split("|");

		stations[8] = "13|4028d09318ee4f450118eece8f1f0009|站点名称10|站点名称10|"
				.split("|");

		stations[9] = "13|4028d09318ee4f450118eecf5221000a|站点名称11|站点名称11|"
				.split("|");

		stations[10] = "13|4028d09318ee4f450118eecfb82e000b|站点名称12|站点名称12|"
				.split("|");

		stations[11] = "13|4028d09318ee4f450118eed03daa000c|站点名称13|站点名称13|"
				.split("|");

		stations[12] = "13|4028d09318ee4f450118eed08bab000d|站点名称14|站点名称14|"
				.split("|");

		stations[13] = "13|4028d09318ee4f450118eed0e44a000e|站点名称15|站点名称15|"
				.split("|");

		stations[14] = "13|4028d09318ee4f450118eed12858000f|站点名称16|站点名称16|"
				.split("|");

		stations[15] = "13|4028d09318ee4f450118eed16e890010|站点名称17|站点名称17|"
				.split("|");

		stations[16] = "13|4028d09318ee4f450118eed1be010011|站点名称18|站点名称18|"
				.split("|");

		stations[17] = "13|4028d09318ee4f450118eed20ec10012|站点名称19|站点名称19|"
				.split("|");

		stations[18] = "13|4028d09318ee4f450118eed24e890013|站点名称20|站点名称20|"
				.split("|");

		stations[19] = "13|4028d09318ee4f450118eed2a94b0014|站点名称21|站点名称21|"
				.split("|");

		stations[20] = "13|4028d09318ee4f450118eed2ebc30015|站点名称22|站点名称22|"
				.split("|");

		stations[21] = "12|4028d0931a519a19011a519e52e30001|maradona|maradona|maradona"
				.split("|");

		stations[22] = "12|4028d0931a519a19011a51a1f8a90002|二期|二期|二期"
				.split("|");

		stations[23] = "13|7db3f136-e16e-4cff-9506-f7959b1a25ac|站点名称06|站点名称06|北京通讯器材厂5"
				.split("|");

		stations[24] = "13|91e63bc0-102f-4314-9959-cc5ad357f15b|站点名称04|站点名称04|北京通讯器材厂3"
				.split("|");

		stations[25] = "12|9c609010-7da8-4557-8709-d23791c0c0af|zhandian|***遥控站11111|北京通讯器材厂1"
				.split("|");

		stations[26] = "4|9fd2a7ff-2f0b-452c-a92c-b0ba24f82a34|宁化|S36P16|北京通讯器材厂6"
				.split("|");

		stations[27] = "4|b4dfde70-dbee-42c3-b7a8-9ce31e6375ed|宁德|S36P15|北京通讯器材厂7"
				.split("|");

		stations[28] = "13|ccf50e4b-69d2-41f4-b4a2-14befd4962af|站点名称05|站点名称05|北京通讯器材厂4"
				.split("|");

		stations[29] = "12|e63c9ecb-5465-4ec7-97da-08d16d0f2f39|zhandian21|***遥控站21|北京通讯器材厂2"
				.split("|");

		getStation(document.Form1.jctId);

		var nowdate = new Date();
		var obj1 = document.Form1.sbYearFrom;
		var obj2 = document.Form1.sbYearTo;
		for (i = 2000; i <= nowdate.getFullYear(); i++) {
			obj1.options.add(new Option(i, i));
			obj2.options.add(new Option(i, i));
		}

	}

	function getStation(para) {

		var obj = document.Form1.stationName;
		var mypara = para.value;

		/* for (i = obj.length; i > 0; i--) {
			obj.remove(i);
		} */

		for (i = 0; i < stations.length; i++) {
			if (stations[i][0] == mypara) {

				obj.options.add(new Option(stations[i][2], stations[i][2]));
			}
		}
	}
	function gotopage(path, where) {

		var page = document.Form2.pageNO;

		//如果传过来的参数不在下列之中，则还是回到当前页(修改和删除完之后)

		if (where == "next") {
			page.value = document.Form2.nextpageNO.value;

		} else if (where == "prev") {
			page.value = document.Form2.prevpageNO.value;
		} else if (where == "end") {

			page.value = document.Form2.sumPage.value;
		} else if (where == "start") {
			page.value = 1;
		} else if (where == "go") {

			var theForm = document.Form2;

			if (Trim(theForm.goPage.value) == "") {
				alert("请输入页数");
				theForm.goPage.focus();
				return false;
			}

			if (!checkNumber(theForm.goPage)) {
				alert("请输入正确页数(数字)");
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

		document.Form1.ifnewquery.value = "0"; //不是新的查询,标志用于业务处理类

		document.Form1.pageNO.value = document.Form2.pageNO.value;
		//document.Form1.querysql.value = document.Form2.querysql.value;
		//document.Form1.sqlbugfrom.value = document.Form2.sqlbugfrom.value;
		//document.Form1.sqlbugto.value = document.Form2.sqlbugto.value;
		//document.Form1.pageSize.value = document.Form2.pageSize.value;

		Pub.submitActionWithForm('Form2', path, 'Form1');
	}

	function gotoquery(path) {

		var theform = document.Form1;

		if (theform.sbMonthFrom.value != "0" && theform.sbYearFrom.value == "0") {
			alert("请选择上报年");
			return;
		}
		if (theform.sbMonthTo.value != "0" && theform.sbYearTo.value == "0") {
			alert("请选择上报年");
			return;
		}
		document.Form1.ifnewquery.value = "1"; //新的查询应去第一页 ，用新的查询条件，标志给业务处理类用 
		document.Form1.pageNO.value = 1;
		document.Form1.pageSize.value = 10;
		Pub.submitActionWithForm('Form2', path, 'Form1');

	}

	function exeportExcel() {
		var path = "station/elecBugAction_exportExcel.do?elecStation.jctID="
				+ document.getElementById("jctId").value
				+ "&elecStation.stationName="
				+ document.getElementById("stationName").value
				+ "&sbMonthFrom=" + document.Form1.sbMonthFrom.value
				+ "&sbMonthTo=" + document.Form1.sbMonthTo.value
				+ "&sbYearFrom=" + document.Form1.sbYearFrom.value
				+ "&sbYearTo=" + document.Form1.sbYearTo.value + "&bugType="
				+ document.Form1.bugType.value + "&bugTimeFrom="
				+ document.Form1.bugTimeFrom.value + "&bugTimeTo="
				+ document.Form1.bugTimeTo.value + "&elecStation.stationCode="
				+ document.getElementById("stationCode").value
				+ "&elecStation.stationType="
				+ document.getElementById("stationType").value;
		openWindow(path, '600', '400');
	}

	function reflash() {
		gotoquery('getRunInfo.do');
	}
	function showBugLength() {
		var path = "station/elecBugAction_showRunTime.do";
		openWindow(path, '250', '160');

	}
</script>
</head>

<body onload="init()">
	<form name="Form1" method="post" id="Form1" style="margin: 0px;">
		<s:hidden name="initPage" value="1" />
		<table cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<tr>
				<td class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>运行情况</strong></font>
				</td>
			</tr>
			<TR height=10>
				<td></td>
			</TR>
			<input type="hidden" name="initflag" value="0">
			<input type="hidden" name="pageNO" value="">
			<input type="hidden" name="querysql" value="">
			<input type="hidden" name="sqlbugfrom" value="">
			<input type="hidden" name="sqlbugto" value="">
			<input type="hidden" name="pageSize" value="">
			<input type="hidden" name="ifnewquery">
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="ta_01" align="center" bgcolor="#f5fafe" height="22"
								width="100">所属单位：</td>
							<td class="ta_01"><s:select list="#request.jctList"
									name="elecStation.jctID" id="jctId" cssStyle="width: 275px"
									onchange="getStation(this)" headerKey="0" headerValue="全部"
									listKey="ddlCode" listValue="ddlName"></s:select></td>
							<td class="ta_01" align="center" height="22" width="100">站点名称：</td>
							<td class="ta_01" width="77"><s:select
									list="#request.stationList" name="elecStation.stationName"
									id="stationName" cssStyle="width: 155px" headerKey="0"
									headerValue="全部"></s:select></td>
						</tr>
						<tr>

							<td class="ta_01" align="center" height="22" width="100">查询时间：</td>
							<td class="ta_01"><select name="sbYearFrom" id="sbYearFrom">
									<option value="0">全部</option>
							</select>年 <s:select
									list="#{'0':'全部','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12'}"
									listKey="key" listValue="value" name="sbMonthFrom"
									id="sbMonthFrom"></s:select>月 ~ <select name="sbYearTo"
								id="sbYearTo">
									<option value="0">全部</option>

							</select>年 <s:select
									list="#{'0':'全部','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12'}"
									listKey="key" listValue="value" value="0" name="sbMonthTo"
									id="sbMonthTo"></s:select>月</td>

							<td class="ta_01" align="center" height="22" width="100">故障类型：
							</td>
							<td class="ta_01"><s:select list="#request.bugTypeList"
									name="bugType" id="bugtype" cssStyle="width: 155px"
									headerKey="0" headerValue="全部" listKey="ddlCode"
									listValue="ddlName"></s:select></td>
						</tr>
						<tr>
							<td class="ta_01" align="center" height="22" width="100">故障时间：</td>
							<td class="ta_01" width="300"><input name="bugTimeFrom"
								type="text" id="bugTimeFrom" size="16"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
								~ <input name="bugTimeTo" type="text" id="bugTimeTo" size="16"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							</td>
							<td height="22" align="center" class="ta_01" width="100">站点代号：</td>
							<td class="ta_01"><input name="elecStation.stationCode"
								type="text" id="stationCode" size="20"></td>
						</tr>

						<tr>
							<td class="ta_01" align="center" height="22" width="100">站点类别：</td>
							<td class="ta_01" width="300"><s:select
									list="#request.stationTypeList" name="elecStation.stationType"
									id="stationType" cssStyle="width: 155px" headerKey="0"
									headerValue="全部" listKey="ddlCode" listValue="ddlName"></s:select></td>
							<td height="22" align="center" class="ta_01" width="100"></td>
							<td class="ta_01"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>

	<br>
	<form name="Form2" method="post" id="Form2" style="margin: 0px;">

		<table cellspacing="0" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<TR>
				<TD align="center"
					background="${pageContext.request.contextPath }/images/cotNavGround.gif"
					width=25><img
					src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
				<TD class="DropShadow"
					background="${pageContext.request.contextPath }/images/cotNavGround.gif"
					width=80>运行情况列表</TD>
				<td class="ta_01" align="right"><input type="button"
					name="bugLength" style="font-size: 12px; color: black;"
					value="汇总故障时长" id="bugLength" onclick="showBugLength()"> <input
					type="button" name="Search" style="font-size: 12px; color: black;"
					value="查询" id="Search"
					onclick="gotoquery('station/elecBugAction_home.do')"> <input
					type="button" name="excelimport"
					style="font-size: 12px; color: black;" value="导入" id="excelimport"
					onclick="openWindow('station/elecBugAction_importPage.do','600','400')">
					<input type="button" name="excelExport"
					style="font-size: 12px; color: black;" value="导出" id="excelExport"
					onClick="exeportExcel()"> <input type="button"
					name="setexcelExport" style="font-size: 12px; color: black;"
					value="导出设置" id="setexcelExport"
					onClick="openWindow('system/elecExportFieldsAction_setExportExcel.do?belongTo=4-2','600','400')">
			</TR>
		</table>


		<table cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td align="center" width="8%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
							<td align="center" width="10%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>
							<td align="center" width="11%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">站点名称</td>
							<td align="center" width="11%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">故障类型</td>
							<td align="center" width="15%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">故障时间</td>
							<td align="center" width="11%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">故障时长(h)</td>
							<td align="center" width="11%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">恢复情况</td>

							<td width="6%" align="center" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>

						</tr>
						<s:iterator value="#request.bugList" status="item">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td align="center" width="8%"><s:property
										value="#item.count" /></td>
								<td align="center" width="10%"><s:property
										value="elecStation.jctID" /></td>
								<td align="center" width="11%" style="HEIGHT: 22px"><a
									href="#"
									onclick="openWindow('station/elecStationAction_view.do?pageNO=${page.pageNo }&stationID=<s:property value="elecStation.stationID"/>','700','500');"
									class="cl_01"><s:property value="elecStation.stationName" /></a></td>
								<td align="center" width="11%"><s:property value="bugType" /></td>
								<td align="center" width="15%"><s:date name="occurDate"
										format="yyyy-MM-dd" />~ <s:date name="resolveDate"
										format="yyyy-MM-dd" /></td>
								<td align="center" width="11%"><s:property value="hour" /></td>
								<td align="center" width="11%"><s:property value="comment" /></td>


								<td align="center" style="HEIGHT: 22px"><a href="#"
									onclick="openWindow('station/elecBugAction_edit.do?bugID=<s:property value="bugID"/>','700','500');">
										<img src="${pageContext.request.contextPath }/images/edit.gif"
										border="0" style="CURSOR: hand" />
								</a></td>

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
											onclick="gotopage('station/elecBugAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopage('station/elecBugAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
								</s:else>
								<s:if test="#request.page.lastPage">
									<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
									<td width="8%" align="center">末页</td>
								</s:if>
								<s:else>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopage('station/elecBugAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
									<td width="8%" align="center"><u><a href="#"
											onclick="gotopage('station/elecBugAction_home.do','end')">末页</a></u></td>
								</s:else>
								<td width="6%" align="center">第<s:property
										value="%{#request.page.pageNo}" />页
								</td>
								<td width="6%" align="center">共<s:property
										value="%{#request.page.sumPage}" />页
								</td>
								<td width="21%" align="right">至第<input size="1" type="text"
									name="goPage">页 <u><a href="#"
										onclick="gotopage('station/elecBugAction_home.do','go')">确定</a></u></td>

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
