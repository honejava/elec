<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/pub.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/validate.js"></script>
<script language="javascript">
	var stations = new Array();

	function init() {

		stations[0] = "3|4028d09318ee4f450118ee559e9d0001|站点名称1".split("|");

		stations[1] = "9|4028d09318ee4f450118ee5616ab0002|站点名称".split("|");

		stations[2] = "13|4028d09318ee4f450118ee56afaf0003|站点名称01".split("|");

		stations[3] = "13|4028d09318ee4f450118ee570cd30004|站点名称02".split("|");

		stations[4] = "13|4028d09318ee4f450118ee57b0370005|站点名称03".split("|");

		stations[5] = "13|4028d09318ee4f450118eeca14070006|站点名称07".split("|");

		stations[6] = "13|4028d09318ee4f450118eeca984a0007|站点名称08".split("|");

		stations[7] = "13|4028d09318ee4f450118eecb83180008|站点名称09".split("|");

		stations[8] = "13|4028d09318ee4f450118eece8f1f0009|站点名称10".split("|");

		stations[9] = "13|4028d09318ee4f450118eecf5221000a|站点名称11".split("|");

		stations[10] = "13|4028d09318ee4f450118eecfb82e000b|站点名称12".split("|");

		stations[11] = "13|4028d09318ee4f450118eed03daa000c|站点名称13".split("|");

		stations[12] = "13|4028d09318ee4f450118eed08bab000d|站点名称14".split("|");

		stations[13] = "13|4028d09318ee4f450118eed0e44a000e|站点名称15".split("|");

		stations[14] = "13|4028d09318ee4f450118eed12858000f|站点名称16".split("|");

		stations[15] = "13|4028d09318ee4f450118eed16e890010|站点名称17".split("|");

		stations[16] = "13|4028d09318ee4f450118eed1be010011|站点名称18".split("|");

		stations[17] = "13|4028d09318ee4f450118eed20ec10012|站点名称19".split("|");

		stations[18] = "13|4028d09318ee4f450118eed24e890013|站点名称20".split("|");

		stations[19] = "13|4028d09318ee4f450118eed2a94b0014|站点名称21".split("|");

		stations[20] = "13|4028d09318ee4f450118eed2ebc30015|站点名称22".split("|");

		stations[21] = "12|4028d0931a519a19011a519e52e30001|maradona"
				.split("|");

		stations[22] = "12|4028d0931a519a19011a51a1f8a90002|二期".split("|");

		stations[23] = "13|7db3f136-e16e-4cff-9506-f7959b1a25ac|站点名称06"
				.split("|");

		stations[24] = "13|91e63bc0-102f-4314-9959-cc5ad357f15b|站点名称04"
				.split("|");

		stations[25] = "12|9c609010-7da8-4557-8709-d23791c0c0af|zhandian"
				.split("|");

		stations[26] = "4|9fd2a7ff-2f0b-452c-a92c-b0ba24f82a34|宁化".split("|");

		stations[27] = "4|b4dfde70-dbee-42c3-b7a8-9ce31e6375ed|宁德".split("|");

		stations[28] = "13|ccf50e4b-69d2-41f4-b4a2-14befd4962af|站点名称05"
				.split("|");

		stations[29] = "12|e63c9ecb-5465-4ec7-97da-08d16d0f2f39|zhandian21"
				.split("|");

		getStation(document.Form1.jct);
	}

	function getStation(para) {

		var obj = document.Form1.stationName;
		var mypara = para.value;

		for (i = obj.length; i > 0; i--) {
			obj.options.remove(i);
		}

		for (i = 0; i < stations.length; i++) {
			if (stations[i][0] == mypara) {

				obj.options.add(new Option(stations[i][2], stations[i][2]));
			}
		}

	}

	function gotopage(path, where) {

		var page = document.Form2.pageNO;

		document.Form1.addnewflag.value = 0; //不一定是去最后一页

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

		//新增站点时，应去最后一页，在此做标记

		if (where == "addnewstation") {
			document.Form1.addnewflag.value = 1;
		}

		document.Form1.pageNO.value = document.Form2.pageNO.value;
		/* document.Form1.ifnewquery.value = "0";
		document.Form1.querysql.value = document.Form2.querysql.value;
		document.F orm1.pageSize.value = document.Form2.pageSize.value;*/
		Pub.submitActionWithForm('Form2', path, 'Form1');

	}

	function gotoquery(path) {

		document.Form1.ifnewquery.value = "1"; //新的查询应去第一页 ，用新的查询条件，标志给业务处理类用
		document.Form1.addnewflag.value = 0; //不去最后一页，标志给pageInfo类用
		document.Form1.pageNO.value = 1;
		document.Form1.pageSize.value == 10;
		Pub.submitActionWithForm('Form2', path, 'Form1');

	}

	function toImportInfoPage() {

		var path = "toImportInfoPage.do?querysql="
				+ document.Form2.querysql.value + "&sqlbugfrom="
				+ document.Form2.sqlbugfrom.value + "&sqlbugto="
				+ document.Form2.sqlbugto.value;

		openWindow(path, '600', '400');
	}
	function exeportExcel() {
		//将所有查询参数放在request中

		//对站点名称和站点代码单独处理
		var stationCode = "";
		var stationName = ""
		if (document.Form1.stationName.value != null) {
			stationName = document.Form1.stationName.value;
		}
		if (document.Form1.stationCode.value != null
				&& document.Form1.stationCode.value != "") {
			stationCode =document.Form1.stationCode.value;

		}
		var path = "station/elecStationAction_exportExcel.do?jctID=" + document.Form1.jctId.value
				+ "&stationCode=" + stationCode + "&stationType="
				+ document.Form1.stationType.value + "&contactType="
				+ document.Form1.contactType.value + "&stationName="
				+ stationName;
		openWindow(path, '600', '400');
	}
	function isChinese(str) {
		var badChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		badChar += "abcdefghijklmnopqrstuvwxyz";
		badChar += "0123456789";
		badChar += " " + "　";//半角与全角空格 
		badChar += "`~!@#$%^&()-_=+]\\\\|:;\"\\\'<,>?/"; //不包含*或.的英文符号 
		afterChar = "0123456789";

		var end = "@end@"; //奇数字符乱码装饰符   

		if ("" == str) {
			return "";
		}
		for ( var i = 0; i < str.length; i++) {
			var c = str.charAt(i);//字符串str中的字符 
			if (i < (str.length - 1)) {
				var Ac = str.charAt(i + 1);//字符后的一个字符
			}
			if (badChar.indexOf(c) <= -1
					&& (afterChar.indexOf(Ac) > -1 || Ac == '*' || Ac == '.')) { //前边一个是汉字字符，后边紧跟着一个数字或*或.
				var afterStr = str.substr(i + 1);
				var beforeStr = str.substring(0, i + 1);
				str = beforeStr + end + afterStr; //中间加一个@end@
				i = i + 5;
			}
		}
		return str + end;
	}
	function reflash() {
		gotoquery('stationInfo.do');
	}

	function returnMethod(obj) {
		return confirm("确定要删除  "+obj+"？");
	}
</script>
<table cellspacing="0" cellpadding="0" width="90%" align="center"
	bgcolor="#f5fafe" border="0">
	<TR>
		<TD align="center"
			background="${pageContext.request.contextPath }/images/cotNavGround.gif"
			width=25><img
			src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
		<TD class="DropShadow"
			background="${pageContext.request.contextPath }/images/cotNavGround.gif"
			width=80>站点信息列表</TD>
		<td class="ta_01" align="right"><input type="button"
			name="BT_Search" style="font-size: 12px; color: black;" value="查询"
			id="BT_Search"
			onclick="gotoquery('station/elecStationAction_home.do')"> <input
			style="font-size: 12px; color: black;" id="BT_Add" type="button"
			value="添加" name="BT_Add"
			onclick="openWindow('station/elecStationAction_add.do','700','350')" />

			<input type="button" name="excelimport"
			style="font-size: 12px; color: black;" value="导入" id="excelimport"
			onclick="openWindow('station/elecStationAction_importPage.do','600','400')">

			<input type="button" name="excelExport"
			style="font-size: 12px; color: black;" value="导出" id="excelExport"
			onClick="exeportExcel()"> <input type="button"
			name="setexcelExport" style="font-size: 12px; color: black;"
			value="导出设置" id="setexcelExport"
			onClick="openWindow('system/elecExportFieldsAction_setExportExcel.do?belongTo=4-1','600','400')">
			<input style="font-size: 12px; color: black;" id="BT_chart"
			type="button" value="图形" name="BT_chart"
			onclick="openWindow('${pageContext.request.contextPath }/station/elecStationAction_report.do','600','400')">
		</td>
	</TR>
</table>
<table cellspacing="1" cellpadding="0" width="90%" align="center"
	bgcolor="#f5fafe" border="0">
	<tr>
		<td class="ta_01" align="center" bgcolor="#f5fafe">
			<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray"
				border="1" id="DataGrid1"
				style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
				<tr
					style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
					<td align="center" width="5%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
					<td align="center" width="10%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>

					<td align="center" width="10%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">归属地</td>

					<td align="center" width="10%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">站点代号</td>
					<td align="center" width="13%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">站点名称</td>
					<td align="center" width="10%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">站点类别</td>
					<td align="center" width="10%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">通讯方式</td>
					<td align="center" width="10%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">安装地点</td>

					<td width="7%" align="center" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
					<td width="7%" align="center" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>

				</tr>
				<s:iterator value="#request.stationList" status="item">
					<tr onmouseover="this.style.backgroundColor = 'white'"
						onmouseout="this.style.backgroundColor = '#F5FAFE';">

						<td align="center" width="5%">&nbsp;<s:property
								value="#item.count" /></td>
						<td align="center" width="10%"><s:property value="jctID" /></td>

						<td align="center" width="10%"><s:property
								value="attributionGround" /></td>

						<td align="center" width="10%"><s:property
								value="stationCode" /></td>
						<td align="center" width="13%" style="HEIGHT: 22px"><a
							href="#"
							onclick="openWindow('station/elecStationAction_view.do?stationID=<s:property value="stationID"/>','700','350');"
							class="cl_01"><s:property value="stationName" /></a></td>
						<td align="center" width="10%"><s:property
								value="stationType" /></td>
						<td align="center" width="10%"><s:property
								value="contactType" /></td>
						<td align="center" width="10%"><s:property
								value="produceHome" /></td>

						<td align="center" style="HEIGHT: 22px"><a href="#"
							onclick="openWindow('${pageContext.request.contextPath }/station/elecStationAction_edit.do?stationID=<s:property value="stationID"/>','700','350');">
								<img src="${pageContext.request.contextPath }/images/edit.gif"
								border="0" style="CURSOR: hand">
						</a></td>
						<td align="center" style="HEIGHT: 22px"><a
							href="${pageContext.request.contextPath }/station/elecStationAction_delete.do?pageNO=${page.pageNo }&stationID=<s:property value='stationID'/>"
							onclick="return returnMethod('<s:property value='stationName'/>')">
								<img src="${pageContext.request.contextPath }/images/delete.gif"
								width="15" height="14" border="0" style="CURSOR: hand">
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
									onclick="gotopage('station/elecStationAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
							<td width="10%" align="center"><u><a href="#"
									onclick="gotopage('station/elecStationAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
						</s:else>
						<s:if test="#request.page.lastPage">
							<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
							<td width="8%" align="center">末页</td>
						</s:if>
						<s:else>
							<td width="10%" align="center"><u><a href="#"
									onclick="gotopage('station/elecStationAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
							<td width="8%" align="center"><u><a href="#"
									onclick="gotopage('station/elecStationAction_home.do','end')">末页</a></u></td>
						</s:else>
						<td width="6%" align="center">第<s:property
								value="%{#request.page.pageNo}" />页
						</td>
						<td width="6%" align="center">共<s:property
								value="%{#request.page.sumPage}" />页
						</td>
						<td width="21%" align="right">至第<input size="1" type="text"
							name="goPage">页 <u><a href="#"
								onclick="gotopage('station/elecStationAction_home.do','go')">确定</a></u></td>

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