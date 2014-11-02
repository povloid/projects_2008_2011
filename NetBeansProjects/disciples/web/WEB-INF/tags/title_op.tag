<%-- 
    Document   : title_op
    Created on : 01.08.2010, 11:48:00
    Author     : dev_sport
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%--<%@attribute name="id"%>--%>

<%@attribute name="caption"%>

<%-- any content can be specified here e.g.: --%>

    <s:if test="#request['id'] == 0">
        <title>Добавить <%=caption%></title>
    </s:if>
    <s:elseif test="#request['id'] > 0 && !deleteF">
        <title>Редактировать <%=caption%></title>
    </s:elseif>
    <s:elseif test="deleteF">
        <title>Удалить <%=caption%></title>
    </s:elseif>
   