<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table cellpadding="0" cellspacing="0" border="0" width="90%"
	align="center">
	<TR>
		<TD align="center"
			background="${pageContext.request.contextPath }/images/cotNavGround.gif"
			width="25"><img
			src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
		<TD class="DropShadow"
			background="${pageContext.request.contextPath }/images/cotNavGround.gif"
			width="80">设备信息列表</TD>
		<td align="right" class="ta_01"><INPUT name="BT_Search"
			type="button" id="BT_Search" value="查询"
			style="font-size: 12px; color: black;" onclick="gotosearchDevice()">

			<input name="BT_export" type="button" " id="BT_export" value="导出"
			style="font-size: 12px; color: black;"
			onClick="openWindow('exportSarDevice.do','600','400')"> <input
			type="button" name="setexcelExport"
			style="font-size: 12px; color: black;" value="导出设置"
			id="setexcelExport"
			onClick="openWindow('equapmentExport.jsp','600','400')"></td>
	</TR>
</TABLE>
<table cellpadding="0" cellspacing="0" border="0" width="90%"
	align="center">
	<tr>
		<td class="ta_01" align="center" bgcolor="#f5fafe">
			<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray"
				border="1" id="DataGrid1"
				style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
				<tr
					style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
					<td width="11%" align="center" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
					<td width="15%" align="center" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">名称</td>
					<td width="14%" align="center" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">规格型号</td>
					<td align="center" width="8%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">设备状态</td>
					<td align="center" width="10%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">使用日期</td>
					<td align="center" width="10%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">检修周期</td>
					<td align="center" width="9%" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>


					<td width="6%" align="center" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
					<td width="6%" align="center" height=22
						background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>

				</tr>


				<s:iterator value="#request.equapmentList" status="item">
					<tr onmouseover="this.style.backgroundColor = 'white'"
						onmouseout="this.style.backgroundColor = '#F5FAFE';">
						<td align="center"><s:property value="#item.count" /></td>
						<td align="center"><a href="javascript:;"
							onClick="openWindow('equapment/elecEquapmentAction_view.do?equapmentID=<s:property value="equapmentID" />',800,450,'设备详细信息');"
							class="cl_01"> <s:property value="devName" /></a></td>
						<td align="left"><s:property value="specType" /></td>
						<td align="center" width="8%"><s:property value="devState" /></td>
						<td align="center" width="10%"><s:date name="useDate"
								format="yyyy-MM-dd" /></td>
						<td align="center" width="10%"><s:property
								value="overhaulPeriod" /> <s:property value="opunit" /></td>
						<td align="center" width="9%" style="HEIGHT: 22px"><s:property
								value="jctID" /></td>
						<td align="center" style="HEIGHT: 22px"><a
							href="javascript:;"
							onClick="openWindow('equapment/elecEquapmentAction_edit.do?equapmentID=<s:property value="equapmentID" />',800,450,'设备详细信息');">
								<IMG src="${pageContext.request.contextPath }/images/edit.gif"
								style="CURSOR: hand" border="0">
						</a></td>
						<td align="center" style="HEIGHT: 22px"><a
							href="javascript:Pub.submitActionWithForm('Form1','equapment/elecEquapmentAction_delete.do?equapmentID=<s:property value="equapmentID" />','F1')"
							onclick="returnMethod()"> <IMG
								src="${pageContext.request.contextPath }/images/delete.gif"
								style="CURSOR: hand" border="0"></a></td>
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
									onclick="gotopage('equapment/elecEquapmentAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
							<td width="10%" align="center"><u><a href="#"
									onclick="gotopage('equapment/elecEquapmentAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
						</s:else>
						<s:if test="#request.page.lastPage">
							<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
							<td width="8%" align="center">末页</td>
						</s:if>
						<s:else>
							<td width="10%" align="center"><u><a href="#"
									onclick="gotopage('equapment/elecEquapmentAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
							<td width="8%" align="center"><u><a href="#"
									onclick="gotopage('equapment/elecEquapmentAction_home.do','end')">末页</a></u></td>
						</s:else>
						<td width="6%" align="center">第<s:property
								value="%{#request.page.pageNo}" />页
						</td>
						<td width="6%" align="center">共<s:property
								value="%{#request.page.sumPage}" />页
						</td>
						<td width="21%" align="right">至第<input size="1" type="text"
							name="goPage">页 <u><a href="#"
								onclick="gotopage('equapment/elecEquapmentAction_home.do','go')">确定</a></u></td>

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