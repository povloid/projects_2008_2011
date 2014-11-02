<%-- 
    Document   : opSdep
    Created on : 15.08.2010, 21:18:43
    Author     : dev_sport
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>

<s:set var="id" value="sdep.id"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <n:title_op caption="спортивное отделение"/>

        <s:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <div class="opForm">
            <n:body_title_op caption="спортивное отделение"/>

            <s:form name="editForm" action="opSdep" method="POST">
                <s:hidden name="sdep.id"/>
                <s:hidden name="deleteF"/>

                <s:textfield label="Наименование" name="sdep.keyName" size="100" readonly="deleteF"/>
                <s:textarea label="Описание" name="sdep.description" cols="100" readonly="deleteF" rows="5"/>

                <s:submit value="Применить" cssClass="aButton"/>
            </s:form>
        </div>
    </body>
</html>
<n:set_form_focus form="editForm" input="opSdep_sdep_keyName"/>
