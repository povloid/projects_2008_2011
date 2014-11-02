<%-- 
    Document   : opDsptc
    Created on : 07.10.2010, 10:25:19
    Author     : dev_sport
--%>

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


<s:set var="id" value="dsptc.id"/>
<s:set var="did" value="disciple.id"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <n:title_op caption="спортивный показатель"/>
        <s:head/>
        <sx:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <form name="TO_DSPTCS_LIST" action="dsptcs.action">
            <input type="submit" value="<-Назад" class="aButton"/>
        </form>


        <s:if test="#request['id'] == 0">
            <div class="datagrid">
                <table width="100%" border="0px">
                    <caption align="top">
                        <h2>История спортивных показателей</h2>
                    </caption>
                    <thead>
                        <tr>
                            <th class="tblheader" width="5%">№</th>
                            <th class="tblheader" >Дата заполнения</th>
                            <th class="tblheader" >Категория</th>
                            <th class="tblheader" >Значение</th>
                            <th class="tblheader" >мера</th>
                            <th class="tblheader" width="10%" >Действие</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            int discipleId = (Integer) request.getAttribute("dsptc.discipleId");
                            int sdepId = (Integer) request.getAttribute("dsptc.sdepId");
                        %>
                        <n:result_cycle css1="tbltd1" css2="tbltd2"
                                        url_edit="editDsptc.action" url_del="delDsptc.action"
                                        get_pars="dsptc.discipleId=${dsptc.discipleId}&dsptc.sdepId=${dsptc.sdepId}"
                                        columns="id;cdate;category_key_name;val;measure_key_name"
                        result="<%=DAOImpl.getDsptcsHistory(discipleId, sdepId)%>">
                        </n:result_cycle>
                    </tbody>
                </table>
            </div>

            <hr>
        </s:if>
        <div class="opForm">
            <n:body_title_op caption="спортивный показатель"/>
            <s:form name="editForm" action="opDsptc" method="POST">
                <s:hidden name="deleteF"/>
                <s:hidden name="dsptc.id"/>
                <s:hidden name="dsptc.discipleId"/>
                <s:hidden name="dsptc.sdepId"/>
                
                <tr>
                    <td class="label" align="right" valign="top">
                        Фото:
                    </td>
                    <td>
                        <img src="foto.action?id=${did}" width="150" height="200" alt="Нет фотографии" border="1"
                    </td>
                </tr>
                
                <s:label label="Отделение" name="sdep.keyName"/>
                <s:label label="Фамилия" name="disciple.fname"/>
                <s:label label="Имя" name="disciple.iname"/>
                <s:label label="Отчество" name="disciple.oname"/>
                <s:select listKey="id" listValue="value"
                          tooltip="Спортивная подготовка" label="Спортивная подготовка"
                          list="categories" name="dsptc.categoryId" />
                <sx:datetimepicker tooltip="Пример 20.02.2002"
                                   label="Дата" name="dsptc.cdate" displayFormat="dd.MM.yyyy"/>
                <s:textfield label="Значение" name="dsptc.val"
                             value="%{getText('{0,number,#0}',{dsptc.val})}" readonly="deleteF"/>
                <s:submit value="Применить" cssClass="aButton"/>
                <s:submit value="Назад" action="dsptcs" cssClass="aButton"/>
            </s:form>
        </div>
    </body>
</body>
</html>
<n:set_form_focus form="editForm" input="opDsptc_dsptc_categoryId"/>

