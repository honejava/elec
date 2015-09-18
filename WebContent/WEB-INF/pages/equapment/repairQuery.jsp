<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<head>
<link href="${pageContext.request.contextPath }/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath }/script/public.js"
	charset="gb2312"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/pub.js"
	charset="gb2312"></script>

<script language="javascript">
	function ViewDetail(strUrl) {
		OpenWindow('设备检修历史记录', strUrl, 800, 450);
	}

	function EditDetail(strUrl) {
		OpenWindow('设备检修历史记录', strUrl, 800, 450);
	}
</script>

<title>设备检修历史记录</title>
</head>
<body>
	<BR>
	<form name="Form1" method="post">

		<table cellSpacing="1" cellPadding="5" width="600" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">

			<tr>
				<td class="ta_01" align="center"
					background="${pageContext.request.contextPath }/images/b-info.gif"
					colspan="8"><font face="宋体" size="2"> <strong>设备检修历史记录
					</strong>
				</font></td>
			</tr>
			<tr>
				<td class="ta_01" align="center" colspan=8 bgcolor="#f5fafe">
					<table id="tblSubEngin" cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td align="center" width="15%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">设备名</td>
							<td align="center" width="11%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">检修日期</td>
							<td align="center" width="11%" height=22
								background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>

							<td align="center" width="8%"
								background="${pageContext.request.contextPath }/images/tablehead.jpg"
								height=21>编辑</td>
						</tr>
						<s:iterator value="#request.repairList">
							<tr>
								<td style="CURSOR: hand;" align=center width=15%><a
									href="javascript:;"
									onClick="ViewDetail('equapment/elecEquapmentAction_view.do?equapmentID=<s:property value="equapmentID"/>')"
									class="cl_01"> UPS123</a></td>
								<td style="CURSOR: hand;" align=center width=11%><s:date
										name="startDate" format="yyyy-MM-dd" /></td>
								<td style="CURSOR: hand;" align=center width=11%><s:property
										value="jctID" /></td>
								<td style="CURSOR: hand;" align=center width=8%><a
									href="javascript:;"
									onClick="EditDetail('equapment/elecRepairAction_edit.do?repairID=<s:property value="repairID"/>')"
									class="cl_01">编辑</a></td>
							</tr>
						</s:iterator>






					</table>
				</td>
			</tr>

			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgcolor="#f5fafe" colspan="8"><input
					style="font-size: 12px; color: black;" type="reset" value=" 关闭"
					id="Reset1" name="Reset1" onclick="window.close();"></td>
			</tr>

		</table>
	</form>

</body>

</html>
