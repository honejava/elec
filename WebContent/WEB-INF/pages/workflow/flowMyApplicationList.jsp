<%@ page language="java" pageEncoding="utf-8"%>
<%@  taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>我的申请查询</title>
<LINK href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>

	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath }/workflow/elecApplicationFlowAction_myApplicationHome.do"
		method="post" style="margin:0px;">
		<table cellspacing="1" cellpadding="0" width="90%" align="center"
			bgcolor="#f5fafe" border="0">
			<TR height=10>
				<td></td>
			</TR>
			<tr>
				<td class="ta_01" colspan=4 align="center"
					background="../images/b-info.gif"><font face="宋体" size="2"><strong>我的申请查询管理</strong>
				</font></td>

			</tr>

			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					申请模板：</td>
				<td class="ta_01"><s:if
						test="#request.applicationTemplatelist!=null&&#request.applicationTemplatelist.size()>0">
						<s:select list="#request.applicationTemplatelist"
							name="id" id="applicationTemplateID"
							cssStyle="width:155px" headerKey="" headerValue="查看全部模板"
							listKey="id" listValue="name"></s:select>
					</s:if></td>
				<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					审核状态：</td>
				<td class="ta_01">
				<s:if test="#request.systemDDLList!=null&&#request.systemDDLList.size()>0">
				<s:select
				             list="#request.systemDDLList" 
				             name="status" id="status"
				             headerKey="" headerValue="查看全部状态"
				             listKey="ddlName" listValue="ddlName"
					         cssStyle="width:155px">
						</s:select></s:if></td>
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
									background="${pageContext.request.contextPath }/images/cotNavGround.gif">待我审批列表</TD>
							</TR>
						</TABLE></td>
					<td class="ta_01" align="right"><input
						style="font-size:12px; color:black; height=20;width=80"
						id="BT_Flush" type="button" value="查询" name="BT_Flush"
						onclick="document.Form1.submit();">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="ta_01" align="left" bgColor="#f5fafe" colspan="2">
						说明：<br /> 1，排序是：按申请时间降序排列（最后的申请在最前面）。<br> <!--
						1，对于退回的表单，可以执行“修改后再次提交”与“删除”的操作。<br />&nbsp;&nbsp;
						   其他状态表单（正在审批的或是审批完成的）则没有这两个操作。<br />
						   2，删除退回的申请文档后，此文档相关的审批信息也要同时删除，对应的流程也终止了。<br />
						   --></td>
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
									background="${pageContext.request.contextPath }/images/tablehead.jpg">当前状态</td>
								<td width="12%" align="center" height=22
									background="${pageContext.request.contextPath }/images/tablehead.jpg">查看流程记录</td>
							</tr>
							<s:if
								test="#request.applicationList!=null&&#request.applicationList.size()>0">
								<s:iterator value="#request.applicationList">
									<tr onmouseover="this.style.backgroundColor = 'white'"
										onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="HEIGHT:22px" align="center" width="30%"><s:property
												value="elecApplicationTemplate.name" /> <s:property
												value="elecUser.userName" /> <s:property value="applyTime" />
										</td>
										<td style="HEIGHT:22px" align="center" width="25%"><s:property
												value="elecUser.userName" /></td>
										<td style="HEIGHT:22px" align="center" width="23%"><s:property
												value="applyTime" /></td>
										<td style="HEIGHT:22px" align="center" width="10%"><s:property
												value="status" /></td>
										<td style="HEIGHT:22px" align="center" width="12%"><a
											id="Form2_"
											href="${pageContext.request.contextPath }/workflow/elecApplicationFlowAction_flowApprovedHistory.do?id=<s:property value="applicationID"/>"><img
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


