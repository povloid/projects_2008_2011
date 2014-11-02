<%-- 
    Document   : plist
    Created on : 02.03.2010, 16:51:20
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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <title>Список персон</title>
    </head>
    <body>

        <s:url id="plist_orderBy_id" action="plist" >
            <s:param name="orderBy">id</s:param>
        </s:url>
        <s:url id="plist_orderBy_cdate" action="plist" >
            <s:param name="orderBy">cdate</s:param>
        </s:url>
        <s:url id="plist_orderBy_vtext" action="plist" >
            <s:param name="orderBy">vtext</s:param>
        </s:url>

        <jsp:include page="/header.jsp"/>

        <table border="0" width="100%">
            <tbody>
                <tr>
                    <td width="10%" align="right"><img src="../resources/images/icons/docs_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                    <td><h1>Таблица</h1></td>
                </tr>
            </tbody>
        </table>


        <table width="100%" border="0px">
            <caption align="top">
                <h2>Список персон</h2>
            </caption>
            <thead>
                <tr>
                    <th class="tblheader" width="10%"><s:a href="%{plist_orderBy_id}">Док. №</s:a></th>
                    <th class="tblheader"><s:a href="%{plist_orderBy_vtext}">Ф.И.О.</s:a></th>
                    <th class="tblheader" width="15%" align="center"><s:a href="%{plist_orderBy_cdate}">Дата создания</s:a></th>
                    <!-- th class="tblheader">Дата последнего<br>
			обновления</th -->


                    <th class="tblheader" width="100px" >Действие</th>


                </tr>
            </thead>
            <tbody>

                <%




                            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

                            DAOImpl.getListPersinsOrderType orderType = DAOImpl.getListPersinsOrderType.FOR_ID;

                            if (request.getParameterMap().containsKey("orderBy")) {

                                String orderBy = request.getParameter("orderBy");

                                if (orderBy.equals("id")) {
                                    orderType = DAOImpl.getListPersinsOrderType.FOR_ID;
                                } else if (orderBy.equals("cdate")) {
                                    orderType = DAOImpl.getListPersinsOrderType.FOR_CDATE;
                                }
                                if (orderBy.equals("vtext")) {
                                    orderType = DAOImpl.getListPersinsOrderType.FOR_VTEXT;
                                }
                            }


                            DAOImpl.Result result = DAOImpl.getListPersons("", orderType);

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
