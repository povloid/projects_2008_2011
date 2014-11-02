<%-- 
    Document   : psearch
    Created on : 02.03.2010, 22:59:30
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



<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <title>Поиск персоны</title>
    </head>
    <sx:head/>
    <body>
        <jsp:include page="/header.jsp"/>

        <table border="0" width="100%">
            <tbody>
                <tr>
                    <td width="10%" align="right"><img src="../resources/images/icons/srch_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                    <td><h1>Поиск персоны</h1></td>
                </tr>
            </tbody>
        </table>

        <s:form name="apsearch" action="apsearch" method="POST">
            <table border="0" width="100%">
                <thead>
                    <tr>
                        <th width="20%">
                        </th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td width="70%">
                            <table border="0" width="100%">
                                <tbody>
                                    <tr>
                                        <td> <s:textfield name="query" label="Запрос:" cssStyle="width: 100%"/></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td>
                            <table border="0" width="100%">
                                <tbody>
                                    <tr>
                                        <td align="left">
                                            <s:submit value="Поиск" align="left"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>

                    </tr>
                </tbody>
            </table>
        </s:form>
        <br>
        <hr>
        <table width="100%" border="0px">
            <caption align="top">
                <h2>Список персон</h2>
            </caption>
            <thead>
                <tr>
                    <th class="tblheader" width="10%">Док. №</th>
                    <th class="tblheader">Ф.И.О.</th>
                    <th class="tblheader" width="15%" align="center">Дата создания</th>
                    <!-- th class="tblheader">Дата последнего<br>
			обновления</th -->


                    <th class="tblheader" width="100px" >Действие</th>


                </tr>
            </thead>
            <tbody>
                <%




                            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

                            System.out.println("query = " + request.getParameter("query") + "");

                            DAOImpl.Result result = DAOImpl.getListPersonsForKeyWords("'" + request.getParameter("query") + "'");

                            ResultSet rs = result.getRs();


                            int cI = 2;
                            while (rs.next()) {
                                int id = rs.getInt("ID");
                                cI = cI == 2 ? 1 : 2;

                %>

                <tr>
                    <td class=tbltd<%=cI%> align="right">
                        <%=rs.getString("id")%>
                    </td>

                    <td class=tbltd<%=cI%>>
                        <%=rs.getString("vtext")%>
                    </td>

                    <td class=tbltd<%=cI%> align="center">
                        <%=df.format(rs.getTimestamp("CDATE"))%>
                    </td>
                    <td class=tbltd<%=cI%>>
                        <A href="pview.action?id=<%=id%>" target="_blank">
                            <img src="../resources/images/icons/about_16.png" width="16" height="16" alt="about_16" border="0"/>
                            Инф.
                        </A>
                    </td>

                    <%
                                }

                                result.closeAll();

                    %>


            </tbody>
        </table>

    </body>
</html>
