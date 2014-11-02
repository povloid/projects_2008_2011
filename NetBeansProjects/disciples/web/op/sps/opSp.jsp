<%-- 
    Document   : opSp
    Created on : 18.10.2010, 9:51:56
    Author     : dev_sport
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="disciples.model.Sp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="disciples.dao.DAOImpl"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<s:set var="id" value="sp.id"/>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <n:title_op caption="Спортивное достижение"/>

        <s:head/>
        <sx:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <form name="TO_SPS_LIST" action="sps.action">
            <input type="submit" value="<-Назад" class="aButton" />
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
                        <h2>История спортивных достижений</h2>
                    </caption>
                    <thead>
                        <tr>
                            <th class="tblheader" width="5%">№</th>
                            <th class="tblheader" >Дата заполнения</th>
                            <th class="tblheader" >Описание</th>
                            <th class="tblheader" width="10%" >Действие</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int discipleId = (Integer) request.getAttribute("sp.discipleId");
                            if (discipleId == 0) {
                                discipleId = Integer.parseInt(request.getParameter("disciple_id"));
                            }
                        %>
                        <n:result_cycle css1="tbltd1" css2="tbltd2"
                                        url_edit="editSp.action" url_del="delSp.action"
                                        columns="id;cdate;description"
                                        get_pars_from_query="disciple_id"
                        result="<%=DAOImpl.getDisciplesSpsHistory(discipleId)%>">
                        </n:result_cycle>
                    </tbody>
                </table>
            </div>
            <hr>

        </s:if>

        <div class="opForm">
            <n:body_title_op caption="спортивное достижение"/>
            <s:form name="editForm" action="opSp" method="POST">
                <s:hidden name="deleteF"/>
                <s:hidden name="sp.id"/>
                <s:hidden name="sp.discipleId"/>
                <s:label label="Фамилия" name="disciple.fname"/>
                <s:label label="Имя" name="disciple.iname"/>
                <s:label label="Отчество" name="disciple.oname"/>
                <s:label label="Родился" name="disciple.birthday" />
                <sx:datetimepicker tooltip="Пример 20.02.2002"
                                   label="Дата" name="sp.cdate" displayFormat="dd.MM.yyyy"/>
                <s:textarea label="Описание" name="sp.description" cols="100" readonly="deleteF"/>
                <s:submit value="Применить" cssClass="aButton"/>
                <s:submit value="Назад" action="sps" cssClass="aButton"/>
            </s:form>
        </div>
    </body>
</html>
<n:set_form_focus form="editForm" input="opSp_sp_cdate"/>

