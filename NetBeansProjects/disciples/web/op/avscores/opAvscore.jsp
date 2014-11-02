<%-- 
    Document   : opAvscore
    Created on : 18.10.2010, 16:56:11
    Author     : dev_sport
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>

<s:set var="id" value="disciple.id"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <n:title_op caption="Средний бал"/>

        <s:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <div class="opForm">
            <n:body_title_op caption="средний бал"/>

            <s:form name="editForm" action="opAvscore" method="POST">
                <s:hidden name="disciple.id"/>
                <s:hidden name="deleteF"/>

                <tr>
                    <td class="label" align="right" valign="top">
                        Фото:
                    </td>
                    <td>
                        <img src="foto.action?id=${id}" width="150" height="200" alt="Нет фотографии" border="1"
                    </td>
                </tr>

                <s:label label="Фамилия" name="disciple.fname"/>
                <s:label label="Имя" name="disciple.iname"/>
                <s:label label="Отчество" name="disciple.oname"/>
                <s:label label="Родился" name="disciple.birthday" />
                <s:textfield label="Средний бал" name="disciple.avscore"
                             readonly="deleteF"/>

                <s:submit value="Применить" cssClass="aButton"/>
            </s:form>
        </div>
    </body>
</html>
<n:set_form_focus form="editForm" input="opSdep_sdep_keyName"/>