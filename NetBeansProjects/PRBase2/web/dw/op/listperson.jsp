<%--
    Document   : index
    Created on : 09.02.2010, 14:42:40
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

        <title>Редактирование персон</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>

        <table border="0" width="100%">
            <tbody>
                <tr>
                    <td width="10%" align="right"><img src="../resources/images/icons/edit_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                    <td><h1>Редактирование персон</h1></td>
                </tr>
            </tbody>
        </table>


        <s:url id="listperson_orderBy_id" action="listperson" >
            <s:param name="orderBy">id</s:param>
        </s:url>
        <s:url id="listperson_orderBy_cdate" action="listperson" >
            <s:param name="orderBy">cdate</s:param>
        </s:url>
        <s:url id="listperson_orderBy_vtext" action="listperson" >
            <s:param name="orderBy">vtext</s:param>
        </s:url>




        <s:url id="addPerson" action="addPerson" >
            <s:param name="op">add</s:param>
        </s:url>
        <s:url id="editPerson" action="editPerson" namespace="prbase">
            <s:param name="op">edit</s:param>
            <s:param name="id">482</s:param>
        </s:url>
        <s:url id="opperson" action="opperson">
        </s:url>

        <%--<s:a href="%{addPerson}">Добавить персону</s:a>--%>

        <table border="0">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><form name="ADD_PERSON_FORM" action="addPerson.action" method="POST">
                            <input type="submit" value="Добавить новую персону"/>
                        </form>
                    </td>
                    <td><form name="EDIT_PERSON_FORM" action="editPerson.action" method="POST">
                            №<input type="text" name="id" value="" />
                            <input type="submit" value="Редактировать"/>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>


        <hr>
        <table width="100%" border="0px">
            <caption align="top">
                <h2>Список персон</h2>
            </caption>
            <thead>
                <tr>
                    <th class="tblheader" width="10%"><s:a href="%{listperson_orderBy_id}">Док. №</s:a></th>
                    <th class="tblheader"><s:a href="%{listperson_orderBy_vtext}">Ф.И.О.</s:a></th>
                    <th class="tblheader" width="15%" align="center"><s:a href="%{listperson_orderBy_cdate}">Дата создания</s:a></th>
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
                        <A href="editPerson.action?id=<%=id%>" target="_parent">
                            <img src="../resources/images/icons/edit_16.png" width="16" height="16" alt="foldr_16" border="0"/>
                            Ред.
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
