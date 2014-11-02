<%-- 
    Document   : opRole
    Created on : 17.05.2010, 15:02:55
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />

        <s:if test="role.id == 0">
            <title>Добавить роль</title>
        </s:if>
        <s:elseif test="role.id > 0 && !deleteF">
            <title>Редактирование роли</title>
        </s:elseif>
        <s:elseif test="deleteF">
            <title>Удаление роли</title>
        </s:elseif>

        <s:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>

        <table border="0" width="100%">
            <tbody>
                <tr>
                    <s:if test="role.id == 0">
                        <td width="10%" align="right"><img src="../resources/images/icons/add_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                        <td><h1>Добавить роли</h1></td>
                    </s:if>
                    <s:elseif test="role.id > 0 && !deleteF">
                        <td width="10%" align="right"><img src="../resources/images/icons/edit_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                        <td><h1>Редактирование роли</h1></td>
                    </s:elseif>
                    <s:elseif test="deleteF">
                        <td width="10%" align="right"><img src="../resources/images/icons/remov_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                        <td><h1>Удаление роли</h1></td>
                    </s:elseif>
                </tr>
            </tbody>
        </table>


        <s:form name="editForm" action="opRole" method="POST">
            <s:hidden name="role.id"/>
            <s:hidden name="deleteF"/>

            <s:textfield label="Пользователь" name="role.keyName" size="100" readonly="deleteF"/>
            <s:textarea label="Описание" name="role.description" cols="100" readonly="deleteF"/>

            <s:submit value="Применить"/>
        </s:form>
        
    </body>
</html>
