<%-- 
    Document   : opPassword
    Created on : 19.05.2010, 11:05:20
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <title>Смена пароля</title>
        <s:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>

        <table border="0" width="100%">
            <tbody>
                <tr>
                    <td width="10%" align="right"><img src="../resources/images/privelegii.png" width="64" height="64" alt="srch_24" border="0"/></td>
                    <td><h1>Смена пароля</h1></td>
                </tr>
            </tbody>
        </table>


        <s:form name="passwordForm" action="chPassword" method="POST">
            <s:label label="Пороль по умолчанию - 123456"/>

            <s:password label="Пароль" name="password1" showPassword="true" />
            <s:password label="Подтверждение пароля" name="password2" showPassword="true" />

            <s:submit value="Применить"/>
        </s:form>

    </body>
</html>
