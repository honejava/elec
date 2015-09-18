<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>站点维护计划</title>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/pub.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/validate.js"></script>
<Script type="text/javascript" language="javascript">
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
		
		//新增维护计划后，因为是倒序排列，所以应去第一页，在此做标记

		if (where == "addnewplan") {
			page.value = 1;
		}

		document.Form1.ifnewquery.value = "0"; //不是新的查询,标志用于业务处理类
		document.Form1.pageNO.value = document.Form2.pageNO.value;

		Pub.submitActionWithForm('Form2', path, 'Form1');
	}

	function gotoquery(path) {

		var theForm = document.Form1;

		if (checkNull(theForm.startdate)) {

			if (!checkDatetime(theForm.startdate.value)) {
				alert("请输入正确的计划时间");
				theForm.startdate.focus();
				return;
			}
		}

		if (checkNull(theForm.enddate)) {

			if (!checkDatetime(theForm.enddate.value)) {
				alert("请输入正确的计划时间");
				theForm.enddate.focus();
				return;
			}
		}

		document.Form1.ifnewquery.value = "1"; //新的查询应去第一页 ，用新的查询条件，标志给业务处理类用 
		document.Form1.pageNO.value = 1;
		document.Form1.pageSize.value = 10;
		Pub.submitActionWithForm('Form2', path, 'Form1');

	}

	function returnMethod(obj) {
		return confirm("你确定要删除  planID="+obj+"  的维护计划？");
	}
</script>
</head>
<body>

	<form name="Form1" method="post" id="Form1" style="margin: 0px;">
		<s:hidden name="initPage" value="1" />
		<table cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<tr>
				<td class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif">
					&nbsp;<a href="station/elecPlanAction_home.do" class="cl_01"><font
						face="宋体" size="2"><strong>维护计划</strong></font></a> | <a
					href="station/elecStateAction_home.do" class="cl_01"><font
						face="宋体" size="2">维护情况</font></a>
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
								height="22">&nbsp;&nbsp;&nbsp; 所属单位：</td>
							<td class="ta_01" bgcolor="#f5fafe"><s:select
									list="#request.jctList" name="jctID" id="jct" headerKey="0"
									headerValue="全部" listKey="ddlCode" listValue="ddlName"></s:select>
							</td>
							<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
								计划时间：</td>
							<td class="ta_01" width="300" bgcolor="#f5fafe"><input
								name="startdate" type="text" id="startdate" size="10"
								onclick="WdatePicker()"> ~ <input name="enddate"
								type="text" id="enddate" size="10" onclick="WdatePicker()"></td>
						</tr>
						<input type="hidden" name="initflag" value="0">
						<input type="hidden" name="pageNO" value="">
						<input type="hidden" name="querysql" value="">
						<input type="hidden" name="pageSize" value="">
						<input type="hidden" name="ifnewquery">
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
					width=80>维护计划列表</TD>
				<td class="ta_01" align="right" bgcolor="#f5fafe"><input
					type="button" name="BT_Search"
					style="font-size: 12px; color: black;" value="查询" id="BT_Search"
					onclick="gotoquery('station/elecPlanAction_home.do')"> <input
					id="BT_Add" style="font-size: 12px; color: black;" type="button"
					value="添加" name="BT_Add"
					onclick="openWindow('station/elecPlanAction_add.do','800','450');" /></td>
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
							<td align="center" width="10%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
							<td align="center" width="35%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>
							<td align="center" width="35%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">时间</td>

							<td align="center" width="10%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
							<td align="center" width="10%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>

						</tr>

						<s:iterator value="#request.planList" status="item">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td align="center" width="10%">&nbsp;<s:property
										value="#item.count" /></td>
								<td align="center" width="35%" style="HEIGHT: 22px"><s:property
										value="jctID" /></td>
								<td align="center" width="35%"><a href="#"
									onclick="openWindow('station/elecPlanAction_view.do?planID=<s:property value='planID' />',800,450)"
									class="cl_01"> <s:date name="occurDate" format="yyyy-MM-dd" /></td>

								<td align="center" width="10%" style="HEIGHT: 22px"><a
									href="#"
									onclick="openWindow('station/elecPlanAction_edit.do?planID=<s:property value='planID' />',800,450)">
										<img src="${pageContext.request.contextPath }/images/edit.gif"
										border="0" style="CURSOR: hand" />
								</a></td>
								<td align="center" width="10%" style="HEIGHT: 22px"><a
									href="station/elecPlanAction_delete.do?planID=<s:property value='planID' />"
									onclick="return returnMethod('<s:property value='planID' />')">
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
											onclick="gotopage('station/elecPlanAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopage('station/elecPlanAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
								</s:else>
								<s:if test="#request.page.lastPage">
									<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
									<td width="8%" align="center">末页</td>
								</s:if>
								<s:else>
									<td width="10%" align="center"><u><a href="#"
											onclick="gotopage('station/elecPlanAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
									<td width="8%" align="center"><u><a href="#"
											onclick="gotopage('station/elecPlanAction_home.do','end')">末页</a></u></td>
								</s:else>
								<td width="6%" align="center">第<s:property
										value="%{#request.page.pageNo}" />页
								</td>
								<td width="6%" align="center">共<s:property
										value="%{#request.page.sumPage}" />页
								</td>
								<td width="21%" align="right">至第<input size="1" type="text"
									name="goPage">页 <u><a href="#"
										onclick="gotopage('station/elecPlanAction_home.do','go')">确定</a></u></td>

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
