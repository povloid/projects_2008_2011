<%-- 
    Document   : sectionOp
    Created on : 30.11.2009, 9:55:29
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/stylesheet.css"
              type="text/css" />
        <title>Операции с секциями</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jspf/header.jsp"/>
        <h1>Работа с секцией</h1>
        <html:form action="/AOpSection">
            <html:hidden property="id" value="${param['id']}"/>
            <html:hidden property="op" value="${param['op']}"/>
            <html:hidden property="parentId" value="${param['parentId']}"/>
            <table align="center" width="80%" border="0" cellpadding="4" cellspacing="4" class="input-form">
                <thead>
                    <c:if test="${param['op']=='add'}">
                        <tr>
                            <th colspan="1" align="right" width="20%">
                                <img src="resources/images/icons/add_32.png" width="32" height="32" alt="add_32"/>
                            </th>
                            <th align="left">
                                <h2>Добавить секцию</h2>
                            </th>
                        </tr>
                    </c:if>
                    <c:if test="${param['op']=='edit'}">
                        <tr>
                            <th colspan="1" align="right" width="20%">
                                <img src="resources/images/icons/edit_32.png" width="32" height="32" alt="edit_32"/>
                            </th>
                            <th align="left">
                                <h2>Редактировать секцию</h2>
                            </th>
                        </tr>
                    </c:if>
                    <c:if test="${param['op']=='del'}">
                        <tr>
                            <th colspan="1" align="right" width="20%">
                                <img src="resources/images/icons/remov_32.png" width="32" height="32" alt="remov_32"/>
                            </th>
                            <th align="left">
                                <h2>Удалить секцию</h2>
                            </th>
                        </tr>
                    </c:if>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <html:errors/>
                        </td>
                    </tr>
                    <tr>
                        <td>Наименование:</td>
                        <td>
                            <html:text property="keyName" style="width: 100%" />
                        </td>
                    </tr>
                    <tr>
                        <td>Краткое описание:</td>
                        <td>
                            <html:textarea property="description" rows="10" style="width: 100%" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <html:submit value="Принять" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </html:form>
    </body>
</html>
