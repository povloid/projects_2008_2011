<%-- 
    Document   : sps
    Created on : 17.10.2010, 19:18:42
    Author     : dev_sport
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="disciples.dao.DAOImpl"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <title>Спортиваные достижения</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <n:header2 caption="Спортиваные достижения" url_icon="../resources/images/icons/apps_32.png"/>
        <div class="datagrid">
            <table width="100%" border="0px">
                <caption align="top">
                    <h2>Список учеников</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="5%">№</s:a></th>
                        <th class="tblheader" >Фамилия</th>
                        <th class="tblheader" >Имя</th>
                        <th class="tblheader" >Отчество</th>
                        <th class="tblheader" width="10%">Записей по сп. дост.</th>
                        <th class="tblheader" width="10%" >Действие</th>
                    </tr>
                </thead>
                <tbody>
                    <n:result_cycle css1="tbltd1" css2="tbltd2"
                                    columns="disciple_id;fname;iname;oname;count"
                                    url_edit="addSp.action" get_pars_from_query="disciple_id"
                    result="<%=DAOImpl.getDisciplesAndSpsCounts()%>">
                    </n:result_cycle>
                </tbody>
            </table>
        </div>
    </body>
</html>