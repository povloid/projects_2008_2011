<%-- 
    Document   : opCategory
    Created on : 14.09.2010, 16:11:29
    Author     : dev_sport
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>

<s:set var="id" value="category.id"/>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <n:title_op caption="спортивную категорию"/>

        <s:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <div class="opForm">
            <n:body_title_op caption="спортивную категорию"/>

            <s:form name="editForm" action="opCategory" method="POST">
                <s:hidden name="category.id"/>
                <s:hidden name="deleteF"/>
                <s:select listKey="id" listValue="value"
                          tooltip="Спортивная подготовка" label="Спортивная подготовка"
                          list="sdeps" name="category.sdepId" />
                <s:checkbox label="Специализированная подготовка" name="category.specialized" readonly="deleteF"/>
                <s:textfield label="Наименование" name="category.keyName" size="100" readonly="deleteF"/>
                <s:textarea label="Описание" name="category.description" cols="100" readonly="deleteF" rows="5"/>
                <s:select listKey="id" listValue="value"
                          tooltip="Мера" label="Мера"
                          list="measures" name="category.measureId" />

                <s:submit value="Применить" cssClass="aButton"/>
            </s:form>
        </div>
    </body>
</html>
<n:set_form_focus form="editForm" input="opCategory_category_keyName"/>