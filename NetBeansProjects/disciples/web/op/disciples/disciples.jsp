<%-- 
    Document   : disciples
    Created on : 15.08.2010, 19:09:30
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
        <title>Ученики</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <n:header2 caption="Ученики" url_icon="../resources/images/icons/addbk_32.png"/>
        
        
                    <n:add_batton caption="Добавить ученика" url_add="addDisciple.action"/>
        <div class="datagrid">

            
            <table width="100%" border="0px">
                <caption align="top">
                    <h2>Список учеников</h2>
                </caption>
                <!--
                               <a href="<ss:url action='Main'/>">Main Page</a><br>
                               <a href="<ss:url action='Books'/>">Books Shop</a>-->


                <thead>
                    <tr>
                        <th class="tblheader" width="5%">№</s:a></th>
                        <th class="tblheader" >Фамилия</th>
                        <th class="tblheader" >Имя</th>
                        <th class="tblheader" >Отчество</th>
                        <th class="tblheader" align="center" >Родился</th>
                        <th class="tblheader" align="center">Поступил</th>
                        <th class="tblheader" align="center">Закончил</th>
                        <th class="tblheader" >Отделение</th>
                        <th class="tblheader" >Класс</th>
                        <th class="tblheader" width="10%" >Действие</th>
                    </tr>
                </thead>
                <tbody>
                    <n:result_cycle css1="tbltd1" css2="tbltd2"
                                    columns="id;fname;iname;oname;birthday;bdate;edate;sdep;sclass"
                    url_edit="editDisciple.action" url_del="delDisciple.action" result="<%=DAOImpl.getDisciples()%>">
                    </n:result_cycle>
                </tbody>
            </table>
        </div>
    </body>
</html>
