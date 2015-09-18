<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<title>维护情况</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/pub.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/public.js"
	charset="gb2312"></script>
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
		var obj = document.Form1.sbYear;
		for (i = 2000; i <= nowdate.getFullYear(); i++) {
			obj.options.add(new Option(i + "年", i));
		}

	}

	function getStation(para) {
		var obj = document.getElementById("stationID");
		var mypara = para.value;
		for (i = 0; i < stations.length; i++) {
			if (stations[i][0] == mypara) {

				obj.options.add(new Option(stations[i][2], stations[i][1]));
			}
		}
	}

	function check_null() {

		var theForm = document.Form1;

		if (theForm.sbYear.value == "0") {
			alert("请选择上报年月");
			theForm.sbYear.focus();
			return false;
		}
		if (theForm.sbMonth.value == "0") {
			alert("请选择上报年月");
			theForm.sbMonth.focus();
			return false;
		}
		if (theForm.jctId.value == "0") {
			alert("请选择所属单位");
			theForm.jctId.focus();
			return false;
		}

		if (theForm.bugType.value == "0") {
			alert("请选择故障类型");
			theForm.bugType.focus();
			return false;
		}
		if (Trim(theForm.occurDate.value) == "") {
			alert("请输入故障发生时间");
			theForm.occurDate.focus();
			return false;
		}
		if (theForm.bugDescribe.value.length > 250) {
			alert("故障描述不能超过250个汉字！");
			theForm.bugDescribe.focus();
			return false;
		}
		if (theForm.resolveMethod.value.length > 250) {
			alert("处理方法不能超过250个汉字！");
			theForm.resolveMethod.focus();
			return false;
		}
		if (theForm.bugReason.value.length > 250) {
			alert("故障原因不能超过250个汉字！");
			theForm.bugReason.focus();
			return false;
		}
		if (theForm.comment.value.length > 250) {
			alert("备注不能超过250个汉字！");
			theForm.comment.focus();
			return false;
		}
		document.Form1.action = "station/elecBugAction_update.do";
		document.Form1.submit();

		/* if (document.Form1.runflag.value == "1") { //刷新运行情况	  
			window.setTimeout(refreshThisOpener('getRunInfo.do'), 3000);
		} else { //刷新维护情况
			window.setTimeout(refreshThisOpener('getRepairInfo.do'), 3000);
		} */

	}

	function refreshThisOpener(sHref) {
		opener.gotopage(sHref, "editRepairInfo");
		//window.close ();
	}

	function upload(bugID) {
		var str = "${pageContext.request.contextPath }/station/elecFileUploadAction_detail.do?elecBug.bugID="
				+ bugID;

		OpenWindow('uploadInit', str, 800, 450);
	}
</script>
</head>

<body onload="init()">

	<form name="Form1" method="post" id="Form1">
		<s:hidden name="bugID" value="%{#request.bug.bugID}" />
		<s:hidden name="elecStation.stationID"
			value="%{#request.bug.elecStation.stationID}" />
		<br>
		<table cellspacing="1" cellpadding="5" width="580" align="center"
			bgcolor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">

			<tr>
				<td colspan="4" class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>故障情况录入</strong></font>
				</td>
			</tr>
			<tr>
				<input name="bugid" type="hidden" id="bugid" value="348">
				<input name="runflag" type="hidden" id="bugid" value="1">

				<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01">上报年月：</td>
				<td class="ta_01" bgcolor="#ffffff"><select name="sbYear"
					id="sbYear" style="WIDTH: 76px">
						<option value="0">请选择</option>
				</select> <s:select
						list="#{'0':'全部','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12'}"
						listKey="key" listValue="value" name="sbMonth" id="sbMonth"
						cssStyle="WIDTH: 76px" value="#request.bug.sbMonth"></s:select> <font
					color="#FF0000">*</font></td>
				<td width="18%" align="center" bgcolor="#ffffff" class="ta_01"></td>
				<td class="ta_01" bgcolor="#ffffff"></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01">所属单位：</td>
				<td class="ta_01" bgcolor="#ffffff"><s:select
						list="#request.jctList" name="elecStation.jctID" id="jctId"
						cssStyle="WIDTH: 155px" listKey="ddlCode" listValue="ddlName"
						disabled="true" value="%{#request.bug.elecStation.jctID}"></s:select>
					<font color="#FF0000">*</font></td>
				<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01">站点名称：</td>
				<td class="ta_01" bgcolor="#ffffff"><s:select
						list="#request.stationList" name="elecStation.stationName"
						id="stationName" cssStyle="width: 155px" headerKey="0"
						value="#request.bug.elecStation.stationName" listKey="stationID"
						listValue="stationName" headerValue="全部"></s:select> <font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01">故障类型：</td>
				<td class="ta_01" bgcolor="#ffffff"><s:select
						list="#request.bugTypeList" name="bugType" id="bugtype"
						cssStyle="width: 155px" listKey="ddlCode" listValue="ddlName"
						value="#request.bug.bugType"></s:select> <font color="#FF0000">*</font></td>
				<td height="22" align="center" bgColor="#f5fafe" class="ta_01">站点代号：</td>
				<td class="ta_01" bgColor="#ffffff"><input
					name="elecStation.stationCode" disabled="true"
					value="<s:property value='#request.bug.elecStation.stationCode'/>"
					type="text" id="stationCode" size=20></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01">发生时间：</td>
				<td class="ta_01" bgcolor="#ffffff"><input name="occurDate"
					type="text" id="occurDate" size=20
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="<s:property value='#request.bug.occurDate'/>"> <font
					color="#FF0000">*</font></td>
				<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01">生产厂家：</td>
				<td class="ta_01" bgcolor="#ffffff"><input name="produceHome"
					value="<s:property value='#request.bug.produceHome'/>" type="text"
					size=20 maxlength="25" id="produceHome"></td>

			</tr>
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">故障描述：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3"><s:textarea
						name="bugDescribe" id="bugDescribe" cssStyle="WIDTH: 96%" rows="3"
						value="%{#request.bug.bugDescribe}"></s:textarea></td>
			</tr>
			<tr>
				<td colspan="4" class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>故障处理</strong></font>
				</td>
			</tr>

			<tr>
				<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01"
					height="37">处理时间：</td>
				<td class="ta_01" bgcolor="#ffffff" height="37"><input
					name="resolveDate" type="text" id="resolveDate" size="20"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="<s:property value='#request.bug.resolveDate'/>"></td>
				<td width="18%" align="center" bgcolor="#ffffff" height="37"></td>
				<td class="ta_01" bgcolor="#ffffff" height="37"></td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">处理方法：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3"><s:textarea
						name="resolveMethod" id="resolveMethod" cssStyle="WIDTH: 86%"
						rows="3" value="%{#request.bug.resolveMethod}"></s:textarea>
					&nbsp;&nbsp;<span id=PResolve style="DISPLAY: ''"><input
						id="btnResolve" type="button" value=" 详细" name="btnResolve"
						onclick="upload('<s:property value="#request.bug.bugID"/>'
						);"
						style="font-size: 12px; color: black;"></span></td>
			</tr>

			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">故障原因：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3"><s:textarea
						name="bugReason" id="bugReason" cssStyle="WIDTH: 96%" rows="3"
						value="%{#request.bug.bugReason}"></s:textarea></td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					注：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3"><s:textarea
						name="comment" id="comment" cssStyle="WIDTH: 96%" rows="3"
						value="%{#request.bug.comment}"></s:textarea></td>
				<font color="#FF0000">*</font>
			</tr>
			<tr>
				<td class="ta_01" style="width: 100%" align="center"
					bgcolor="#f5fafe" colspan="4"><input type="button"
					name="BT_Submit" value="保存" id="BT_Submit"
					style="font-size: 12px; color: black;" onclick="check_null();">
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>&nbsp;

					<input style="font-size: 12px; color: black;" type="reset"
					value="关闭" id="Reset1" name="Reset1" onclick="window.close();">
					<span id="Label1"></span></td>
			</tr>
		</table>
		<br>
	</form>

</body>
</html>