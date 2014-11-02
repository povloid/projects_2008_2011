<%-- 
    Document   : progress
    Created on : 19.05.2010, 9:49:15
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="ips.prbase.dao.*"%>
<%@ page import="ips.prbase.dao.DAOImpl"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <title>Статистика вноса данных по пользователям</title>
    </head>

    <body>
        <jsp:include page="/header.jsp"/>

        <table border="0" width="100%">
            <tbody>
                <tr>
                    <td width="10%" align="right"><img src="../resources/images/icons/hist_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                    <td><h1>Статистика вноса данных по пользователям</h1></td>
                </tr>

            <table width="60%" border="0px" align="center">
                <caption align="top">
                    <h2>За сегодня</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="10%">Логин</th>
                        <th class="tblheader" >Наименование</th>
                        <th class="tblheader" width="10%" >Прогресс</th>
                    </tr>
                </thead>
                <tbody>


                    <%
                                {
                                    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

                                    DAOImpl.Result result = DAOImpl.getProgressToday();
                                    ResultSet rs = result.getRs();

                                    int cI = 2;
                                    while (rs.next()) {

                                        cI = cI == 2 ? 1 : 2;

                    %>


                    <tr>
                        <td class=tbltd<%=cI%> align="right">
                            <%=rs.getString("user_name")%>
                        </td>

                        <td class=tbltd<%=cI%> >
                            <%=rs.getString("description")%>
                        </td>

                        <td class=tbltd<%=cI%> align="right">
                            <b>
                                <%=rs.getString("ppp")%>
                            </b>
                        </td>
                    </tr>


                    <%
                                    }
                                    result.closeAll();
                                }
                    %>
                </tbody>
            </table>


            <hr width="60%">
            <table width="60%" border="0px" align="center">
                <caption align="top">
                    <h2>За весь период</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="10%">Логин</th>
                        <th class="tblheader" >Наименование</th>
                        <th class="tblheader" width="10%" >Прогресс</th>
                    </tr>
                </thead>
                <tbody>


                    <%
                                {
                                    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

                                    DAOImpl.Result result = DAOImpl.getProgress();
                                    ResultSet rs = result.getRs();

                                    int cI = 2;
                                    while (rs.next()) {

                                        cI = cI == 2 ? 1 : 2;

                    %>


                    <tr>
                        <td class=tbltd<%=cI%> align="right">
                            <%=rs.getString("user_name")%>
                        </td>

                        <td class=tbltd<%=cI%> >
                            <%=rs.getString("description")%>
                        </td>

                        <td class=tbltd<%=cI%> align="right">
                            <b>
                                <%=rs.getString("ppp")%>
                            </b>
                        </td>
                    </tr>

                    <%
                                    }
                                    result.closeAll();
                                }
                    %>
                </tbody>
            </table>



        </tbody>
    </table>
</body>
</html>
