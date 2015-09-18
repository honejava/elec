<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<HTML>
<HEAD>
<TITLE>国家电力监测中心设备资源管理系统</TITLE>
<LINK href="${pageContext.request.contextPath }/css/MainPage.css"
	type="text/css" rel="stylesheet">
<LINK href="${pageContext.request.contextPath }/css/buttonstyle.css"
	type="text/css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="../css/buttonstyle.css">


</HEAD>
<BODY bgcolor="#FFFFFF" onload="showTimer()"
	background="${pageContext.request.contextPath }/images/back1.jpg">
	<Form name="form1" method="POST">
		<table border="0" width="100%" id="table1" height="100%"
			cellspacing="0" cellpadding="0">
			<tr>
				<td align="center">
					<table border="0" width="60%" id="table2" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="49" height="45"><img border="0"
								src="${pageContext.request.contextPath }/images/build.gif"
								width="185" height="180">
							</td>
							<td style="word-break:break-all" align="center"><font
								face="黑体" size="6" color="red"> <!--  <b>非法操作！系统将在5秒中内跳转到登录页面</b><br> -->
									<b><s:fielderror />
								</b> </font></td>
						</tr>
						<tr>
							<td width="39" height="34"></td>
							<td style="word-break:break-all" align="center"><font
								face="黑体" size="3" color="red">
									<div id="timer"
										style="color:#999;font-size:20pt;text-align:center">
										<s:if test="%{#request.errorMsg==null}">
											<p>对不起，系统发生了未知的错误，请查看日志</p>
										</s:if>
										<s:else>
											<p>${requestScope.errorMsg}</p>
										</s:else>

									</div> </font></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</Form>
</BODY>