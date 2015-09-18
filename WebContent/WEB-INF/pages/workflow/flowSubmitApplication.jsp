<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
	<title>提交申请</title>
	<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
	<script type="text/javascript">
		function saveApplication(){
			var checkLogonName = $("#checkLogonName").val();
			if($.trim(checkLogonName) == ""){
				alert("请选择一个审核人！");
				$("#checkLogonName")[0].focus();
				return false;
			}
			var upload = $("#upload").val();    
			var extension = upload.substr(upload.indexOf("."));
			if(upload == ""){
				alert("请选择需要上传的文件！");
				return false; 
			}
			if(extension != ".doc" && extension != ".docx"){
				alert("上传的文件后缀名必须是doc格式或者是docx格式！");
				return false;
			}
			$("form:first").submit(); 
		}
	</script>
</head>
<body>
		<form name="Form1" action="workflow/elecApplicationFlowAction_saveApplication.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="elecApplication.elecApplicationTemplate.id" value="<s:property value="#request.applicationTemplate.id"/>" />
			<input type="hidden" name="elecApplication.elecApplicationTemplate.path" value="<s:property value="#request.applicationTemplate.path"/>" />
			<br>
			<table border="0" width="90%" cellspacing="1" cellpadding="0" align="center" bgcolor="#f5fafe">
				<tr>
					<td class="ta_01" align="left" bgColor="#ffffff" colspan="4">
						<font face="宋体" size="2">
							使用说明：<br />
								1，下载模板文件。<br />
								2，填写文档中的表单。<br />
								3，选择这个填写好的文件进行提交。<br />
								4，申请标题的格式：申请文件模板名称_申请人姓名_申请时间。<br />
								5，审核人是部门经理。<br />
								<br />
								说明2：提交表单后，就会按照 模板对应的流程 开始流转。
						</font>
					</td>
				</tr>
				<tr height=10>
					<td colspan=4></td>
				</tr>
				<tr>
					<td class="ta_01" align="center"
						background="${pageContext.request.contextPath }/images/b-info.gif"
						colspan="4">
						<font face="宋体" size="2"><strong>下载文档模板</strong>
						</font>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">下载文件</td>
			        <td class="ta_01" bgColor="#ffffff" colspan="3">
			        	<a id="saveElecApplication" style="text-decoration: underline" 
			        	href="${pageContext.request.contextPath }/workflow/elecApplicationTemplateAction_download.do?id=<s:property value="#request.applicationTemplate.id"/>" >
			        	
			        	[点击下载申请模板文件]</a>
			        	
			        </td>
				</tr>
				<tr height=10>
					<td colspan=4></td>
				</tr>
				<tr>
					<td class="ta_01" align="center"
						background="${pageContext.request.contextPath }/images/b-info.gif"
						colspan="4">
						<font face="宋体" size="2"><strong>申请信息</strong>
						</font>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">请选择审核人：<font color="#FF0000">*</font></td>
			        <td class="ta_01" bgColor="#ffffff" colspan="3">
			        <s:if test="#request.userList!=null&&#request.userList.size()>0">
			           <s:select list="#request.userList"
			                  headerKey="postID" headerValue="请选择"
			                  listKey="userID" listValue="userName"
			                  name="elecUser.userID" id="checkLogonName" cssStyle="width:155px"
			                  >
			        </s:select>
			        </s:if>
			        </td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">请选择填写好的文档：<font color="#FF0000">*</font></td>
			        <td class="ta_01" bgColor="#ffffff" colspan="3">
			        	<input type="file" name="upload" id="upload" class="InputStyle" style="width:450px;" /> 
			        </td>
				</tr>
				<tr height=10>
					<td colspan=4></td>
				</tr>
				<tr height=2>
					<td colspan=4
						background="${pageContext.request.contextPath }/images/b-info.gif"></td>
				</tr>
				<tr>
					<td align="center" colspan=4>
						<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"   onClick="saveApplication()">
						<INPUT type="button" name="Reset1" id="save" value="返回"
							onClick="javascript:history.go(-1);"
							style="width: 60px; font-size: 12px; color: black;">
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" value="<s:property value="#request.applicationTemplate.id"/>"/>
			
		</form>
</body>
</html>



