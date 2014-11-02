<%-- 
    Document   : sp_trangs
    Created on : 13.09.2010, 15:57:41
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
        <title>Спортивные подготовки</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <n:header2 caption="Спортивные подготовки" url_icon="../resources/images/icons/addbk_32.png"/>
        <n:add_batton caption="Добавить спортивную подготовку" url_add="addSpTrang.action"/>
        <div class="datagrid">
<!--            <table width="100%" border="0px">
                <caption align="top">
                    <h2>Список спортивных подготовок</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="5%">№</th>
                        <th class="tblheader" width="20%">Спортивное отделение</th>
                        <th class="tblheader" width="20%">Наименование</th>
                        <th class="tblheader" width="20%">Специализированная</th>
                        <th class="tblheader" >Описание</th>
                        <th class="tblheader" width="10%" >Действие</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- n:result_cycle css1="tbltd1" css2="tbltd2"
                                    columns="id;sdep_key_name;key_name;specialized;description"
                    url_edit="editSpTrang.action" url_del="delSpTrang.action" result="<!%=DAOImpl.getSp()%>" -->
                    <!--/n:result_cycle
                </tbody>
            </table>-->
        </div>
    </body>
</html>
