


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fn"%>
<html>

<head>
<title>监测台建筑管理</title>

<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/pub.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/validate.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/changePageBackUp.js"
	charset="gb2312"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>

<script language="javascript">
	function gotopages(path, where) {
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

		if (where == "addNewJctBuilds") {
			document.Form1.addnewflag.value = 0;
		}

		document.Form1.ifnewquery.value = "0";

		var ss = document.Form2.pageNO.value;
		document.Form1.pageNO.value = ss;
		document.getElementById("pageNO").value = ss;
		//document.Form1.querysql.value = document.Form2.querysql.value;
		//document.Form1.pageSize.value = document.Form2.pageSize.value;
		Pub.submitActionWithForm('Form2', path, 'Form1');

	}

	function gotoquerys(path) {
		if (Trim(document.Form1.buildArea.value) != "") {
			if (!checkNumber(document.Form1.buildArea)) {
				alert("建筑面积请输入数字");
				document.Form1.buildArea.focus();
				return false;
			}
		}
		Pub.submitActionWithForm('Form2', path, 'Form1');

	}
	 function returnMethod(obj){
		 return confirm("你确定要删除  "+obj+"吗？");
	 }
	 function exeportExcel(){
		  //所属单位
		  var jctID=document.getElementById("jctId").value;
		  //建筑名称
		  var buildName=document.getElementById("buildName").value;
		  //建筑类型
		    var buildType=document.getElementById("buildType").value;
		  //简直面积
		    var buildArea=document.getElementById("buildArea").value;
		  
	      var path="building/elecBuildingAction_exportExcel.do?jctID="+jctID+"&buildName="+buildName+"&buildType="+buildType+"&buildArea="+buildArea;  
	      openWindow(path,'600','400');
	 }
</script>
</head>

<body>

	<form name="Form1" id="Form1" style="margin: 0px;">
		<s:hidden name="pageNO"></s:hidden>
		<!-- 表示：如果initPage==null：跳转到userIndex.jsp,如果initPage==1：表示跳转到userList.jsp -->
		<s:hidden name="initPage" value="1"></s:hidden>
		<input type="hidden" name="initflag" value="0"> <input
			type="hidden" name="addnewflag" value="0"> <input
			type="hidden" name="querysql" value=""> <input type="hidden"
			name="pageSize" value=""> <input type="hidden"
			name="ifnewquery">
		<table cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<tr>
				<td class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>监测台建筑管理</strong></font>
				</td>
			</tr>
			<TR height=10>
				<td></td>
			</TR>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">

						<tr>
							<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
								所属单位：</td>
							<td class="ta_01"><s:select list="#request.jctList"
									name="jctID" id="jctId" cssStyle="width: 160px"
									listKey="ddlCode" listValue="ddlName" headerKey="0"
									headerValue="全部">
								</s:select></td>

							<td width="100" class="ta_01" align="center" bgcolor="#f5fafe"
								height="22">建筑名称：</td>
							<td class="ta_01"><input name="buildName" type="text"
								id="buildName" size="21"><font face="宋体" color="red">
							</font></td>
						</tr>
						<tr>
							<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
								建筑类型：</td>
							<td class="ta_01"><font face="宋体" color="red"> <s:select
										list="#request.typeList" name="buildType" id="buildType"
										cssStyle="width: 160px" listKey="ddlCode" listValue="ddlName"
										headerKey="0" headerValue="全部">
									</s:select>
							</font></td>

							<td width="100" class="ta_01" align="center" bgcolor="#f5fafe"
								height="22">建筑面积：</td>
							<td class="ta_01"><input name="buildArea" type="text"
								id="buildArea" size="21"> m<sup>2</sup></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>

	<br>
	<form name="Form2" id="Form2"
		action="building/elecBuildingAction_home.do" style="margin: 0px;">
		<table cellspacing="0" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<TR>
				<TD align="center"
					background="${pageContext.request.contextPath }/images/cotNavGround.gif"
					width=25><img
					src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
				<TD class="DropShadow"
					background="${pageContext.request.contextPath }/images/cotNavGround.gif"
					width=100>房屋建筑信息列表</TD>
				<td class="ta_01" align="right" bgcolor="#ffffff"><input
					type="button" name="BT_Search" value="查询"
					style="font-size: 12px; color: black;" id="BT_Search"
					onclick="gotoquerys('building/elecBuildingAction_home.do');">

					<input id="BT_Add" type="button" value="添加" name="BT_Add"
					style="font-size: 12px; color: black;"
					onclick="openWindow('building/elecBuildingAction_add.do',700,350);">

					<input type="button" name="excelExport" value="导出"
					style="font-size: 12px; color: black;" id="excelExport"
					onClick="exeportExcel();"> <input type="button"
					name="setexcelExport" value="导出设置"
					style="font-size: 12px; color: black;" id="setexcelExport"
					onClick="openWindow('system/elecExportFieldsAction_setExportExcel.do?belongTo=3-1','600','400')">

					<input style="font-size: 12px; color: black;" id="BT_Import"
					type="button" value="导入" name="BT_Import"
					onclick="openWindow('${pageContext.request.contextPath }/building/elecBuildingAction_importPage.do','600','400')">

					<input style="font-size: 12px; color: black;" id="BT_chart"
					type="button" value="图形" name="BT_chart"
					onclick="openWindow('${pageContext.request.contextPath }/building/elecBuildingAction_report.do','600','400')">
				</td>
			</TR>
		</TABLE>
		<table cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td align="center" width="6%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
							<td align="center" width="14%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">建筑名称</td>
							<td align="center" width="9%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>
							<td align="center" width="7%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">层数</td>
							<td align="center" width="10%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">造价(万元)</td>
							<td align="center" width="11%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">面积(m<sup>2</sup>)
							</td>
							<td align="center" width="13%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">使用时间</td>
							<td align="center" width="13%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">大修时间</td>

							<td width="6%" align="center" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
							<td width="6%" align="center" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>

						</tr>



						<s:iterator value="#request.buildList">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">

								<td align="center" width="6%"><s:property
										value="buildingID" /></td>
								<td align="center" width="14%" style="HEIGHT: 22px"><a
									href="#"
									onclick="openWindow('building/elecBuildingAction_view.do?buildingID=<s:property value='buildingID'/>','700','350');"
									class="cl_01"> <s:property value="buildName" /></a></td>
								<td align="center" width="9%"><s:property value="jctID" /></td>
								<td align="center" width="7%"><s:property
										value="buildLayer" /></td>
								<td align="center" width="10%"><s:property
										value="buildExpense" /></td>
								<td align="center" width="11%"><s:property
										value="buildArea" /></td>
								<td align="center" width="13%"><s:date name="useDate"
										format="yyyy-MM-dd" /></td>
								<td align="center" width="13%"><s:date
										name="extendBuildDate" format="yyyy-MM-dd" /></td>

								<td align="center" style="HEIGHT: 22px"><a href="#"
									onclick="openWindow('building/elecBuildingAction_edit.do?buildingID=<s:property value='buildingID'/>','700','350');">
										<img src="${pageContext.request.contextPath }/images/edit.gif"
										border="0" style="CURSOR: hand">
								</a></td>
								<td align="center" style="HEIGHT: 21px"><a
									href="building/elecBuildingAction_delete.do?pageNO=${page.pageNo }&buildingID=<s:property value='buildingID'/>"
									onclick="return returnMethod('<s:property value='buildName'/>')">
										<img
										src="${pageContext.request.contextPath }/images/delete.gif"
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
											onclick="gotopages('building/elecBuildingAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopages('building/elecBuildingAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
								</s:else>
								<s:if test="#request.page.lastPage">
									<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
									<td width="8%" align="center">末页</td>
								</s:if>
								<s:else>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopages('building/elecBuildingAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
									<td width="8%" align="center"><u><a href="#"
											onclick="gotopages('building/elecBuildingAction_home.do','end')">末页</a></u></td>
								</s:else>
								<td width="6%" align="center">第<s:property
										value="%{#request.page.pageNo}" />页
								</td>
								<td width="6%" align="center">共<s:property
										value="%{#request.page.sumPage}" />页
								</td>
								<td width="21%" align="right">至第<input size="1" type="text"
									name="goPage">页 <u><a href="#"
										onclick="gotopages('building/elecBuildingAction_home.do','go')">确定</a></u></td>

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
