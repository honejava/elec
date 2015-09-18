<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>审批流程管理</title>
<LINK href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function returnMethod(name, key, id) {
		var flag = window.confirm("你确定要删除  " + name + " 吗？");
		if (flag) {
			key=encodeURI(key);
			$("#" + id).attr("href",
					"elecProcessDefinitionAction_delete.do?key=" + key);
			return true;
		} else {
			return false;
		}
	}

	function openFunction(id) {
		id=encodeURI(id);
		openWindow(
				'${pageContext.request.contextPath }/workflow/elecProcessDefinitionAction_downloadProcessImage.do?id='
						+ id, '700', '400');
	}
</script>
</head>
<body>

	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath }/workflow/elecProcessDefinitionAction_home.do"
		method="post" style="margin:0px;">
		<table cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<TR height=10>
				<td></td>
			</TR>
			<tr>
				<td class="ta_01" colspan=2 align="center"
					background="../images/b-info.gif"><font face="宋体" size="2"><strong>审批流程管理</strong>
				</font></td>

			</tr>
		</table>
	</form>
	<form id="Form2" name="Form2" action="" method="post">
		<table cellSpacing="1" cellPadding="0" width="90%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<TR height=10>
					<td></td>
				</TR>
				<tr>
					<td>
						<TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
							<TR>
								<TD align="center"
									background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img
									src="${pageContext.request.contextPath }/images/yin.gif"
									width="15">
								</TD>
								<TD class="DropShadow"
									background="${pageContext.request.contextPath }/images/cotNavGround.gif">审批流程列表</TD>
							</TR>
						</TABLE></td>
					<td class="ta_01" align="right"><input
						style="font-size:12px; color:black; height=20;width=80"
						id="BT_Flush" type="button" value="刷新" name="BT_Flush"
						onclick="document.Form1.submit();">&nbsp;&nbsp; <input
						style="font-size:12px; color:black; height=20;width=120"
						id="BT_Add" type="button" value="部署流程定义文档" name="BT_Add"
						onclick="openWindow('${pageContext.request.contextPath }/workflow/elecProcessDefinitionAction_add.do','700','400')">
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="left" bgColor="#f5fafe" colspan="2">
						说明：1，列表显示的是所有流程定义（不同Key）的最新版本。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;2，删除流程定义时，此Key的所有版本的流程定义都会被删除。<br />
						&nbsp;&nbsp;&nbsp;&nbsp;3，查看流程图时显示的这个最新版本的流程定义的图片。</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe" colspan="2">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr
								style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
								<td align="center" width="30%" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">流程名称</td>
								<td align="center" width="30%" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">最新版本</td>
								<td align="center" width="20%" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">说明</td>
								<td width="10%" align="center" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
								<td width="10%" align="center" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">查看流程图</td>
							</tr>
							<s:if
								test="#request.processDefinitionList!=null&&#request.processDefinitionList.size()>0">
								<s:iterator value="#request.processDefinitionList">
									<tr onmouseover="this.style.backgroundColor = 'white'"
										onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="HEIGHT:22px" align="center" width="30%"><s:property
												value="name" /></td>
										<td style="HEIGHT:22px" align="center" width="30%"><s:property
												value="id" /></td>
										<td style="HEIGHT:22px" align="center" width="20%"><s:property
												value="description" /></td>
										<td style="HEIGHT:22px" align="center" width="10%"><a
											id="delete" href="#"
											onclick="returnMethod('<s:property value="name"/>','<s:property value="name"/>','delete')">
												<img
												src="${pageContext.request.contextPath }/images/delete.gif"
												width="16" height="16" border="0" style="CURSOR:hand">
										</a></td>
										<td style="HEIGHT:22px" align="center" width="10%"><a
											href="#"
											onclick="openFunction('<s:property value="id"/>')">
												<img
												src="${pageContext.request.contextPath }/images/button_view.gif"
												border="0" style="CURSOR:hand">
										</a></td>
									</tr>
								</s:iterator>
							</s:if>
						</table></td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>
