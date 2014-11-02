<%-- 
    Document   : opPh_ch
    Created on : 12.09.2010, 17:16:33
    Author     : dev_sport
--%>

<%@page import="disciples.model.PhCh"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="disciples.dao.DAOImpl"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>



<s:set var="id" value="phCh.id"/>
<s:set var="discipleId" value="phCh.discipleId"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <n:title_op caption="физическую характеристику"/>

        <s:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <form name="TO_PHCH_LIST" action="ph_chs.action">
            <input type="submit" value="<-Назад" class="aButton"/>
        </form>
        <s:if test="#request['id'] == 0">
            <div class="datagrid">
                <table border="0">
                    <caption align="top">
                        <h2>Персональные данные</h2>
                    </caption>
                    <thead>
                        <tr>
                            <th class="tblheader" width="10%"></th>
                            <th class="tblheader" ></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td >Фото:</td>
                            <td ><img src="foto.action?id=${disciple.id}" width="150" height="200" alt="Нет фотографии" border="1"
                                      />                        
                            </td>
                        </tr>
                        <tr>
                            <td >Фамилия:</td>
                            <td >${disciple.fname}</td>
                        </tr>
                        <tr>
                            <td >Имя</td>
                            <td >${disciple.iname}</td>
                        </tr>
                        <tr>
                            <td >Отчество</td>
                            <td >${disciple.oname}</td>
                        </tr>
                        <tr>
                            <td >Родился</td>
                            <td >${disciple.birthday}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="datagrid">
                <table width="100%" border="0px">
                    <caption align="top">
                        <h2>История физических характеристик</h2>
                    </caption>
                    <thead>
                        <tr>
                            <th class="tblheader" width="5%">№</th>
                            <th class="tblheader" >Дата заполнения</th>
                            <th class="tblheader" >Рост (см.)</th>
                            <th class="tblheader" >Вес (кг.)</th>
                            <th class="tblheader" >Описание</th>
                            <th class="tblheader" width="10%" >Действие</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int discipleId = (Integer) request.getAttribute("discipleId");
                            if (discipleId == 0) {
                                discipleId = Integer.parseInt(request.getParameter("disciple_id"));
                            }
                        %>
                        <n:result_cycle css1="tbltd1" css2="tbltd2"
                                        url_edit="editPhCh.action" url_del="delPhCh.action"
                                        columns="id;cdate;height;weight;description"
                                        get_pars_from_query="disciple_id"
                        result="<%=DAOImpl.getPhChsHist(discipleId)%>">
                        </n:result_cycle>
                    </tbody>
                </table>
            </div>
            <hr>

        </s:if>

        <div class="opForm">
            <n:body_title_op caption="физическую характеристику"/>
            <s:form name="editForm" action="opPhCh" method="POST">
                <s:hidden name="deleteF"/>
                <s:hidden name="phCh.id"/>
                <s:hidden name="phCh.discipleId"/>
                <s:label label="Фамилия" name="disciple.fname"/>
                <s:label label="Имя" name="disciple.iname"/>
                <s:label label="Отчество" name="disciple.oname"/>
                <s:label label="Родился" name="disciple.birthday" />
                <s:textfield label="Рост" name="phCh.height"
                             value="%{getText('{0,number,#0}',{phCh.height})}" readonly="deleteF"/>
                <s:textfield label="Вес" name="phCh.weight"
                             value="%{getText('{0,number,#0}',{phCh.weight})}" readonly="deleteF"/>
                <s:textarea label="Описание" name="phCh.description" cols="100" readonly="deleteF"/>
                <s:submit value="Применить" cssClass="aButton"/>
                <s:submit value="Назад" action="ph_chs" cssClass="aButton"/>
            </s:form>
        </div>
    </body>
</html>
<n:set_form_focus form="editForm" input="opPhCh_phCh_height"/>
