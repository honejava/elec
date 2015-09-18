<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>流转记录</title>
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
	<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <script type="text/javascript">
	  
    </script> 
</head>
<body>
	<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
		<TR height=10><td></td></TR>
		<TR height="10">
			<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
				<img style="position: absolute; left: 0px; top: 0px;" src="${pageContext.request.contextPath }/workflow/elecApplicationFlowAction_processImage.do?deploymentId=${deploymentId}" />
				<s:if test="#request.coordList">
					<s:iterator value="#request.coordList" var="c">
						${c.x},${c.y},${c.width},${c.height}
						<div class="activeNode" style="position: absolute; left:${c.x}px; top:${c.y}px; width:${c.width}px; height:${c.height}px"></div>
					</s:iterator>
				</s:if>
			</td>
		</TR>
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>			
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>	
		<TR height=100><td></td></TR>
    </table>
    
</body>
</html>
