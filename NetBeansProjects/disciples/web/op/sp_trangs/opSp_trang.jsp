<%-- 
    Document   : opSp_trang
    Created on : 14.09.2010, 9:55:39
    Author     : dev_sport
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>

<s:set var="id" value="spTrang.id"/>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <n:title_op caption="спортивную подготовку"/>

        <s:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <div class="opForm">
            <n:body_title_op caption="спортивную подготовку"/>

            <s:form name="editForm" action="opSpTrang" method="POST">
                <s:hidden name="spTrang.id"/>
                <s:hidden name="deleteF"/>

                <s:select listKey="id" listValue="value"
                          tooltip="Спортивное отделение" label="Спортивное отделение"
                          list="sdeps" name="spTrang.sdepId" />
                <s:checkbox label="Специализированная подготовка" name="spTrang.specialized" readonly="deleteF"/>
                <s:textfield label="Наименование" name="spTrang.keyName" size="100" readonly="deleteF"/>
                <s:textarea label="Описание" name="spTrang.description" cols="100" readonly="deleteF"/>

                <s:submit value="Применить" cssClass="aButton"/>
            </s:form>
        </div>
    </body>
</html>
<n:set_form_focus form="editForm" input="opSpTrang_spTrang_keyName"/>