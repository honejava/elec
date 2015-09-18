<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
	<title>审批处理</title>
	<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
	<script type="text/javascript">
		function saveInfo(flag){
			$("#approval").val(flag);
			//用来判断审核人的下拉菜单是否被隐藏
			var tr = $("#trCheck").css("display");
			//tr=none，此时说明trCheck被隐藏，如果被隐藏，表示【请选择下一步】是to 结束，是最后一个流程，此时不需要指定审核人，否则需要指定审核人
			if(tr != "none"){
				//审核人
				var checkLogonName = $("#checkLogonName").val();
				if($.trim(checkLogonName) == ""){
					alert("请选择一个审核人！");
					$("#checkLogonName")[0].focus();
					return false;
				}
			}
			//审核意见
			var comment = $("#comment").val();
			if($.trim(comment) == ""){
				alert("请选择审核意见！");
				$("#comment")[0].focus();
				return false;
			}
			$("#approval").value=flag;
			
			$("form:first").submit(); 		 
		}
		function checkUser(){
			var value = $("#outcome").val();
			if(value == "to 结束"){
				$("#trCheck").css("display","none");
			}
			else{
				$("#trCheck").css("display","");
			}
		}
		$().ready(function(){
			checkUser();
		});

	</script>
</head>
<body>
		<form name="Form1" action="workflow/elecApplicationFlowAction_approve.do" method="post">
			<input type="hidden" name="applicationID" value="<s:property value="#request.application.applicationID"/>"/>
	    	<input type="hidden" name="approveID" value="<s:property value="approveID"/>" />
	    	<input type="hidden" name="approval"  id="approval"/>
			<br>
			<table border="0" width="90%" cellspacing="1" cellpadding="0" align="center" bgcolor="#f5fafe">
				<tr>
					<td class="ta_01" align="center"
						background="${pageContext.request.contextPath }/images/b-info.gif"
						colspan="4">
						<font face="宋体" size="2"><strong>审批处理</strong>
						</font>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="left" bgColor="#ffffff" colspan="4">
						<font face="宋体" size="2">
							说明：<br />
								1，同意：本次审批通过，流程继续执行。如果所有的环节都通过，则表单的最终状态为：已通过。<br />
								2，不同意：本次审批未通过，流程结束，不再继续执行。表单的最终状态为：未通过。<br />
						</font>
					</td>
				</tr>
				<tr height=10>
					<td colspan=4></td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01" width="20%">下载申请文件</td>
			        <td class="ta_01" bgColor="#ffffff" colspan="3">
			        	<a id="downloadApplication" href="${pageContext.request.contextPath }/workflow/elecApplicationFlowAction_download.do?id=<s:property value="#request.application.applicationID"/>" style="text-decoration: underline">
			        	[点击下载申请文件]</a>
			        </td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01" width="20%">请选择下一步：<font color="#FF0000">*</font></td>
			        <td class="ta_01" bgColor="#ffffff" colspan="3">
			        <s:if test="#request.outcomes!=null&&#request.outcomes.size()>0">
			        <s:select list="#request.outcomes"
			                  name="outcome" id="outcome" 
			                  cssStyle="width:155px" onchange="checkUser()"
			        ></s:select>
			        </s:if>
			        </td>
				</tr>
				<tr id="trCheck">
					<td align="center" bgColor="#f5fafe" class="ta_01" width="20%">请选择审核人：<font color="#FF0000">*</font></td>
			        <td class="ta_01" bgColor="#ffffff" colspan="3">
			        <s:if test="#request.userList!=null&&#request.userList.size()>0">
				        <s:select list="#request.userList"
				                  cssStyle="width:155px" 
				                  name="elecUser.userID" id="checkLogonName"
				                  headerKey="" headerValue="请选择"
				                  listKey="userID" listValue="userName"
				                  >
				        </s:select>
			        </s:if>
	        		</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01" width="20%">审批意见：<font color="#FF0000">*</font></td>
			        <td class="ta_01" bgColor="#ffffff" colspan="3">
			        	<textarea name="comment" cols="52" rows="4" id="comment" style="WIDTH:95%"></textarea>
			        </td>
				</tr>
				<tr height=2>
					<td colspan=4
						background="${pageContext.request.contextPath }/images/b-info.gif"></td>
				</tr>
				<tr height=10>
					<td colspan=4></td>
				</tr>
				<tr>
					<td align="center" colspan=4>
						<input type="button" name="BT_Submit" value="同意"  style="font-size:12px; color:black; height=22;width=55"  onClick="saveInfo(true)">
						<input type="button" name="BT_Submit" value="不同意"  style="font-size:12px; color:black; height=22;width=55"  onClick="saveInfo(false)">
						<INPUT type="button" name="Reset1" id="save" value="返回"
							onClick="javascript:history.go(-1);"
							style="width: 60px; font-size: 12px; color: black;">
					</td>
				</tr>
			</table>
		</form>

</body>
</html>