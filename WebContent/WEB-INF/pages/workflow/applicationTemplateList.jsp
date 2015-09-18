<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>模板管理</title>
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
	<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <script type="text/javascript">
		function returnMethod(name){
			return confirm("你确定要删除  "+name+" 模板吗？");
		}
    </script> 
</head>
<body>

	<form id="Form1" name="Form1" action="${pageContext.request.contextPath }/workflow/elecApplicationTemplateAction_home.do" method="post" style="margin:0px;"> 
		<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
			<TR height=10><td></td></TR>
			<tr>
				<td class="ta_01" colspan=2 align="center" background="../images/b-info.gif">
					<font face="宋体" size="2"><strong>模板管理</strong></font>
				</td>
				
			</tr>
	    </table>	
	</form>


	<form id="Form2" name="Form2" action="" method="post">
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<TR height=10><td></td></TR>			
				<tr>
				  	<td>
		                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
							<TR>
								<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
								<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">模板列表</TD>
							</TR>
			             </TABLE>
                    </td>
					<td class="ta_01" align="right">
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Flush" type="button" value="刷新" name="BT_Flush" 
						 onclick="document.Form1.submit();">&nbsp;&nbsp;
					    <input style="font-size:12px; color:black; height=20;width=120" id="BT_Add" type="button" value="新增模板" name="BT_Add" 
						 onclick="openWindow('${pageContext.request.contextPath }/workflow/elecApplicationTemplateAction_add.do','700','400')">
					</td>
				</tr>
				<tr>
				  	<td class="ta_01" align="left" bgColor="#f5fafe" colspan="2">
		                说明：<br />
						1，删除时，相应的文件也被删除。<br />
						2，下载时，文件名默认为：{表单模板名称}.doc。<br />
                    </td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe" colspan="2">			
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							    <td align="center" width="35%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">模板名称</td>
								<td align="center" width="35%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">流程定义的Key值</td>
								<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">修改</td>
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">下载</td>
							</tr>
							<s:iterator value="#request.applicationTemplateList">
				              <tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="HEIGHT:22px" align="center" width="35%">
											<s:property value="name"></s:property>
										</td>
										<td style="HEIGHT:22px" align="center" width="35%">
											<s:property value="processDefinitionKey"></s:property>
										</td>
										<td style="HEIGHT:22px" align="center" width="10%">
											<a href="${pageContext.request.contextPath }/workflow/elecApplicationTemplateAction_delete.do?id=<s:property value="id"/>" onclick="return returnMethod('<s:property value="name"/>')">
											<img src="${pageContext.request.contextPath }/images/delete.gif" width="16" height="16" border="0" style="CURSOR:hand"></a>												
										</td>
										<td style="HEIGHT:22px" align="center" width="10%">
											<a href="#" onclick="openWindow('${pageContext.request.contextPath }/workflow/elecApplicationTemplateAction_edit.do?id=<s:property value="id"/>','700','400');">
											<img src="${pageContext.request.contextPath }/images/edit.gif" width="16" height="16" border="0" style="CURSOR:hand"></a>												
										</td>
										<td style="HEIGHT:22px" align="center" width="10%">
											<a href="#" onclick="openWindow('${pageContext.request.contextPath }/workflow/elecApplicationTemplateAction_download.do?id=<s:property value="id"/>','700','400');">
											<img src="${pageContext.request.contextPath }/images/attach.gif" width="16" height="16" border="0" style="CURSOR:hand"></a>												
										</td>
										</tr>
				            </s:iterator>
						</table>		
					</td>
				</tr>     
			</TBODY>
		</table>
	</form>


</body>
</html>
	