<%-- 
    Document   : addPerson
    Created on : 28.01.2010, 10:44:41
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<%

            String webUserName = request.getRemoteUser();


            String op = request.getParameter("op");
            String caption = "";
            String iconSrc = "resources/images/icons/add_32.png";

            //if (op.equals("add_box")) {
            //    caption = "Добавить новый документ в группу";
            //    iconSrc = "resources/images/icons/add_32.png";
            //} else if (op.equals("edit_box")) {
            //    caption = "Редактировать документ";
            //    iconSrc = "resources/images/icons/edit_32.png";
            //} else if (op.equals("del_box")) {
            //    caption = "Удалить документ";
            //    iconSrc = "resources/images/icons/remov_32.png";
            // }


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/stylesheet.css" type="text/css" />
        <title>Добавление </title>
    </head>
    <body>
        <jsp:include page="../blocks/header.jsp"/>
        <h1>Добавить</h1>

        <html:form  action="/AAddPerson" enctype="multipart/form-data">
            <table align="center" width="90%" border="1" cellpadding="4" cellspacing="4" class="input-form">
                <thead>
                    <tr>
                        <th valign="top">
                            <img src="<%=iconSrc%>" width="32" height="32" alt=""/>
                            Название
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><!-- Вывод ошибок -->
                            <html:errors/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Открытый блок
                            <table border="1" width="100%">
                                <thead>
                                    <tr>
                                        <th width="30%"></th>
                                        <th>
                                  
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Фотография</td>
                                        <td>
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Ф.И.О.</td>
                                        <td>
                                            <html:text property="fio"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Контакты</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Занимаемая должность</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Официальная биография</td>
                                        <td>
                                            <html:textarea property="officialBiography"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <%
                    %>
                    <%
                    %>
                    <!-- Условие вывода закрытого блока -->
                    <c:if test="${1==1}">
                        <tr>
                            <td>
                                Закрытый блок
                            </td>
                        </tr>
                    </c:if>
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
