<%-- 
    Document   : opDisciple
    Created on : 08.09.2010, 16:27:27
    Author     : dev_sport
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>

<s:set var="id" value="disciple.id"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../resources/stylesheet.css"
          type="text/css" />
    <n:title_op caption="ученика"/>

    <s:head/>
    <sx:head extraLocales="en-us,ru-ru" />
</head>
<body>
    <jsp:include page="/header.jsp"/>

    <div class="opForm">
        <n:body_title_op caption="ученика"/>

        
        
            
        
            
        



        <s:form name="editForm" action="opDisciple" method="POST" enctype="multipart/form-data" >
            <s:hidden name="disciple.id"/>
            <s:hidden name="deleteF"/>

            <s:textfield label="фамилия" name="disciple.fname" size="70" readonly="deleteF"/>
            <s:textfield label="Имя" name="disciple.iname" size="70" readonly="deleteF"/>
            <s:textfield label="Отчество" name="disciple.oname" size="70" readonly="deleteF"/>
            
            <sx:datetimepicker tooltip="Пример 20.02.2002"
                               label="Родился" name="disciple.birthday" displayFormat="dd.MM.yyyy"/>
            <sx:datetimepicker tooltip="Пример 20.02.2002"
                               label="Дата поступления" name="disciple.bdate" displayFormat="dd.MM.yyyy"/>
            <sx:datetimepicker tooltip="Пример 20.02.2002"
                               label="Дата окончания" name="disciple.edate" displayFormat="dd.MM.yyyy"/>
            
            
            <s:file name="upload" label="Фото" />

            <s:select listKey="id" listValue="value"
                      tooltip="Спортивное отделение" label="Спортивное отделение"
                      list="sdeps" name="disciple.sdepId" />

            <s:select listKey="id" listValue="value"
                      tooltip="Спортивный класс" label="Спортивный класс"
                      list="sclasses" name="disciple.sclassId" />

            <s:textarea label="Сведения о родителях" name="disciple.parents" rows="5" style="width: 100%" readonly="deleteF"/>
            <s:textarea label="Увлечения" name="disciple.hobbi" rows="5" style="width: 100%" readonly="deleteF"/>
            <s:textarea label="Характеристика" name="disciple.performance" rows="5" style="width: 100%" readonly="deleteF"/>
            <s:textarea label="Прочее" name="disciple.description" rows="5" style="width: 100%" readonly="deleteF"/>

            <s:submit value="Применить" cssClass="aButton"/>
        </s:form>
    </div>
</body>
</html>
<n:set_form_focus form="editForm" input="opDisciple_disciple_fname"/>
