<%-- 
    Document   : body_title_op
    Created on : 01.08.2010, 12:38:44
    Author     : dev_sport
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@attribute name="caption"%>



<table border="0" width="100%">
    <tbody>
        <tr>
            <s:if test="#request['id'] == 0">
                <td width="10%" align="right"><img src="../resources/images/icons/add_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                <td><h1>Добавить <%=caption%></h1></td>
            </s:if>
            <s:elseif test="#request['id'] > 0 && !deleteF">
                <td width="10%" align="right"><img src="../resources/images/icons/edit_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                <td><h1>Редактировать <%=caption%></h1></td>
            </s:elseif>
            <s:elseif test="deleteF">
                <td width="10%" align="right"><img src="../resources/images/icons/remov_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                <td><h1>Удалить <%=caption%></h1></td>
            </s:elseif>
        </tr>
    </tbody>
</table>

