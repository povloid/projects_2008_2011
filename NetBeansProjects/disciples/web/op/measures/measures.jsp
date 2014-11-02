<%-- 
    Document   : measures
    Created on : 15.09.2010, 13:23:42
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
        <title>Меры измерения</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <n:header2 caption="Меры измерения" url_icon="../resources/images/icons/addbk_32.png"/>
        <n:add_batton caption="Добавить меру измерения" url_add="addMeasure.action"/>
        <div class="datagrid">
            <table width="100%" border="0px">
                <caption align="top">
                    <h2>Список мер</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="5%">№</s:a></th>
                        <th class="tblheader" width="20%">Наименование</th>
                        <th class="tblheader" align="center">Описание</th>
                        <th class="tblheader" width="10%" >Действие</th>
                    </tr>
                </thead>
                <tbody>
                    <n:result_cycle css1="tbltd1" css2="tbltd2"
                                    columns="id;key_name;description"
                    url_edit="editMeasure.action" url_del="delMeasure.action" result="<%=DAOImpl.getMeasures()%>">
                    </n:result_cycle>
                </tbody>
            </table>

        </div>
    </body>
</html>
