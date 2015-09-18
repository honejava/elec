<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<TITLE>故障时长</TITLE>
<LINK href="${pageContext.request.contextPath }/css/MainPage.css"
	type="text/css" rel="stylesheet">
<LINK href="${pageContext.request.contextPath }/css/buttonstyle.css"
	type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/buttonstyle.css">
</HEAD>
<BODY bgcolor="#f5fafe">
	<table border="0" width="100%" id="table1" height="100" cellspacing="0"
		cellpadding="0">
		<tr>
			<td align=center>
				<table border="0" width="60%" id="table2" cellspacing="0"
					cellpadding="0">
					<tr>
						<td style="word-break: break-all"><font face="黑体" size="4">
								故障时长为： <br> ${sumHour }小时
						</font></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</BODY>
</html>