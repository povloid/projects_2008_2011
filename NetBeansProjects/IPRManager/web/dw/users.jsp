<%-- 
    Document   : users
    Created on : 26.04.2010, 12:15:16
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
        <title>Список персон</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>

        <table border="0" width="100%">
            <tbody>
                <tr>
                    <td width="10%" align="right"><img src="../resources/images/icons/user_32.png" width="32" height="32" alt="user_24" border="0"/></td>
                    <td><h1>Пользователи</h1></td>
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
                    <td><form name="ADD_USER_FORM" action="addUser.action" method="POST">
                            <input type="submit" value="Добавить пользователя"/>
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
                    <th class="tblheader" width="20%">Пользователь</th>
                    <th class="tblheader" align="center">Описание</th>
                    <th class="tblheader" width="10%" align="center">Ранг доступа</th>
                    <th class="tblheader" width="10%" align="center">Отдел</th>
                    <th class="tblheader" align="5%">Блокир.</th>
                    <th class="tblheader" width="10%" >Действие</th>
                </tr>
            </thead>
            <tbody>

                <%
                            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

                            DAOImpl.Result result = DAOImpl.getUsers();
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
                            <%=rs.getString("user_name")%>
                        </b>
                    </td>

                    <td class=tbltd<%=cI%> >
                        <%=rs.getString("description")%>
                    </td>

                    <td class=tbltd<%=cI%> align="right">
                        <%=rs.getInt("access_level")%>
                    </td>

                    <td class=tbltd<%=cI%>>
                        <%=rs.getString("key_name")%>
                    </td>

                    <td class=tbltd<%=cI%> align="center">
                        <%=(rs.getBoolean("blocked")?"да":"нет")%>
                    </td>

                    <td class=tbltd<%=cI%>>
                        <A href="editUser.action?id=<%=id%>">
                            <img src="../resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                            Ред.
                        </A>
                         /
                        <A href="delUser.action?id=<%=id%>">
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
