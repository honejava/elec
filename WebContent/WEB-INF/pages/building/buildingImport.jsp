<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
  <head> 
    <title>导入文件</title> 
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
    <SCRIPT language="javascript">
       
     
    </SCRIPT>
    <script type="text/javascript">
    function checkExcel(){
    	var file=document.getElementById("file").value;
    	if(file==""){
    		alert("情选择上传文件");
    		return false;
    	}
    	return true;
    }
    </script>
  </head>
  
  <body>
    <s:form namespace="/building" action="elecBuildingAction_importData.do" method="post" enctype="multipart/form-data">
      <br>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
      	<tr>
      		<td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif" colspan="4">
				<font face="宋体" size="2"><strong>Excel文件数据导入</strong></font>
			</td>
		</tr>
		<tr>
          <td width="1%" height=30></td>
          <td width="20%"></td>
          <td width="78%"></td>
          <td width="1%"></td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td width="15%" align="center">请选择文件:</td>
          <td width="83%" align="left">
          <s:file name="file" id="file" cssStyle="width:365px"></s:file>
          </td>
          <td width="1%"></td>
        </tr>
        <tr height=50><td colspan=4 ></td></tr>
	    <tr height=2><td colspan=4 background="${pageContext.request.contextPath }/images/b-info.gif"></td></tr>
	    <tr height=10><td colspan=4 ></td></tr>
        <tr>
          <td align="center" colspan=4>
          	  <s:submit name="import" onclick="return checkExcel()" value="导入" cssStyle="width: 60px; font-size:12px; color:black; height=22"></s:submit>
	         <input style="width: 60px; font-size:12px; color:black; height=22" type="reset" value="关闭" id="Reset1" name="Reset1" onclick="window.close();"></td></tr>
          </td>
        </tr>
      </table>
    </s:form>
      <s:if test="#request.errorList!=null && #request.errorList.size()>0">
          <!-- 显示错误信息 -->
	      <table border="0" width="100%" cellspacing="0" cellpadding="0">
	      	<tr>
	      		<td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif" colspan="4">
					<font face="宋体" size="2"><strong>导入失败！错误信息！</strong></font>
				</td>
			</tr>
			<s:iterator value="#request.errorList">
	      		<tr>
	          		<td width="1%"></td>
	          		<td width="15%" align="center">错误信息:</td>
	          		<td width="83%" align="left">
	         		   <font color="red"><s:property/></font>
	          		</td>
	         		<td width="1%"></td>
	            </tr>
	        </s:iterator>
	
	      </table>
      </s:if>
      
  </body>
</html>
