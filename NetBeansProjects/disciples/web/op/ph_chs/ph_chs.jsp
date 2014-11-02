<%-- 
    Document   : ph_chs
    Created on : 10.09.2010, 17:18:26
    Author     : dev_sport
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="disciples.dao.DAOImpl"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ taglib prefix="ss" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <title>Физические характеристики</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <n:header2 caption="Физические характеристики" url_icon="../resources/images/icons/apps_32.png"/>
        <div class="datagrid">
            <table width="100%" border="0px">
                <caption align="top">
                    <h2>Список физических характеристик</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="5%">№</th>
                        <th class="tblheader" >Фамилия</th>
                        <th class="tblheader" >Имя</th>
                        <th class="tblheader" >Отчество</th>
                        <th class="tblheader" >Дата заполнения</th>
                        <th class="tblheader" >Возраст (лет)</th>
                        <th class="tblheader" >Рост (см.)</th>
                        <th class="tblheader" >Вес (кг.)</th>
                        <th class="tblheader" width="10%" >Действие</th>
                    </tr>
                </thead>
                <tbody>
                    <n:result_cycle css1="tbltd1" css2="tbltd2" url_add="addPhCh.action"
                                    columns="disciple_id;fname;iname;oname;cdate;disciple_age;height;weight"
                    result="<%=DAOImpl.getPhChs()%>" get_pars_from_query="disciple_id">
                    </n:result_cycle>
                </tbody>
            </table>
        </div>
    </body>
</html>
