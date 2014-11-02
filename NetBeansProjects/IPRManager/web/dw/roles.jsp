<%-- 
    Document   : roles
    Created on : 17.05.2010, 14:40:24
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="ips.iprmanager.dao.*"%>
<%@ page import="ips.iprmanager.dao.DAOImpl"%>

<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <title>Список ролей</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>

        <table border="0" width="100%">
            <tbody>
                <tr>
                    <td width="10%" align="right"><img src="../resources/images/icons/group_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                    <td><h1>Роли</h1></td>
                </tr>
            </tbody>
        </table>

        <table border="0">
            <thead>
                <tr>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><form name="ADD_ROLE_FORM" action="addRole.action" method="POST">
                            <input type="submit" value="Добавить роль"/>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>


        <table width="100%" border="0px">
            <caption align="top">
                <h2>Список пользователей</h2>
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


                <%
                            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

                            DAOImpl.Result result = DAOImpl.getRoles();
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
                        <b>
                            <%=rs.getString("role_name")%>
                        </b>
                    </td>

                    <td class=tbltd<%=cI%> >
                        <%=rs.getString("description")%>
                    </td>

                    <td class=tbltd<%=cI%>>
                        <A href="editRole.action?id=<%=id%>">
                            <img src="../resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                            Ред.
                        </A>
                            /
                        <A href="delRole.action?id=<%=id%>">
                            <img src="../resources/images/icons/remov_16.png" width="16" height="16" alt="remov_16" border="0"/>
                            Уд.
                        </A>
                    </td>
                </tr>


                <%

                            }

                            result.closeAll();

                %>

            </tbody>
        </table>
    </body>
</html>
