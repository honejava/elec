<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>待我审批</title>
<LINK href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>

	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath }/workflow/elecApplicationFlowAction_myTaskHome.do"
		method="post" style="margin:0px;">
		<table cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<TR height=10>
				<td></td>
			</TR>
			<tr>
				<td class="ta_01" colspan=2 align="center"
					background="../images/b-info.gif"><font face="宋体" size="2"><strong>待我审批管理</strong>
				</font>
				</td>
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
									width="15"></TD>
								<TD class="DropShadow"
									background="${pageContext.request.contextPath }/images/cotNavGround.gif">待我审批列表</TD>
							</TR>
						</TABLE>
					</td>
					<td class="ta_01" align="right"><input
						style="font-size:12px; color:black; height=20;width=80"
						id="BT_Flush" type="button" value="刷新" name="BT_Flush"
						onclick="document.Form1.submit();">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="ta_01" align="left" bgColor="#f5fafe" colspan="2">
						说明：<br /> 1，这里列出的所有的表单状态都为"审批中"。<br>
						2，标题的格式为：{模板名称}_{申请人姓名}_{申请时间}。</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe" colspan="2">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">

							<tr
								style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
								<td align="center" width="30%" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">标题</td>
								<td align="center" width="25%" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">申请人</td>
								<td align="center" width="23%" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">申请日期</td>
								<td width="10%" align="center" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">审批处理</td>
								<td width="12%" align="center" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">查看流程记录</td>
							</tr>
							<s:if
								test="#request.myTaskList!=null&&#request.myTaskList.size()>0">
								<s:iterator value="#request.myTaskList">
									<tr onmouseover="this.style.backgroundColor = 'white'"
										onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="HEIGHT:22px" align="center" width="30%"><s:property
												value="elecApplicationTemplate.name" />;_ <s:property
												value="elecUser.userName" />_ <s:property value="applyTime" />
										</td>
										<td style="HEIGHT:22px" align="center" width="25%"><s:property
												value="elecUser.userName" />
										</td>
										<td style="HEIGHT:22px" align="center" width="23%"><s:property
												value="applyTime" />
										</td>
										<td style="HEIGHT:22px" align="center" width="10%"><a
											id="Form2_"
											href="${pageContext.request.contextPath }/workflow/elecApplicationFlowAction_flowApprove.do?id=<s:property value="applicationID"/>&approveID=<s:property value="approveID"/>">
												<img
												src="${pageContext.request.contextPath }/images/handle.gif"
												width="16" height="16" border="0" style="CURSOR:hand">
										</a>
										</td>
										<td style="HEIGHT:22px" align="center" width="12%"><a
											id="Form2_"
											href="${pageContext.request.contextPath }/workflow/elecApplicationFlowAction_flowApprovedHistory.do?id=<s:property value="applicationID"/>&approveID=<s:property value="approveID"/>">
												<img
												src="${pageContext.request.contextPath }/images/button_view.gif"
												border="0" style="CURSOR:hand"> </a>
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>你还没有任何审批项目</s:else>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>


</body>
</html>

