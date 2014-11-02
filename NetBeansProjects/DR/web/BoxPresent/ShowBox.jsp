<%-- 
    Document   : ShowBox
    Created on : 06.09.2009, 22:36:46
    Author     : pk
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="ips.dwh.datawork.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            String webUserName = request.getRemoteUser();
            int userAccessLevel = DataWork.getUserAccessLevel(request.getRemoteUser());

            // Получаем идентификатор документа
            String idS = request.getParameter("id");
            DataWork.Result dwResult = DataWork.getBox(webUserName,idS);
            ResultSet rs = dwResult.getRs();

            if (rs.next()) {

                String accessIconPath = "../resources/images/icons/ulock_16.png";
                if (userAccessLevel >= rs.getInt("ACCESS_LEVEL")) {
                    accessIconPath = "../resources/images/icons/ulock_16.png";
                } else {
                    accessIconPath = "../resources/images/icons/lock_16.png";
                }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css" type="text/css" />
        <title>Документ №<%=idS%></title>
    </head>
    <body>
        <h1>Документ №<%=idS%></h1>
        <table border="0" width="60%" cellspacing="4" cellpadding="4">
            <tbody>
                <tr>
                    <td class="tblheader">Доступ:</td>
                    <td class=tbltd1><b><%out.print(rs.getString("ACCESS_KEY_NAME"));%></b></td>
                </tr>
                <tr>
                    <td class="tblheader">Наименование:</td>
                    <td class=tbltd1><%out.print(rs.getString("KEY_NAME"));%></td>
                </tr>
                <tr>
                    <td class="tblheader">Описание:</td>
                    <td class=tbltd1><%out.print(rs.getString("DESCRIPTION"));%></td>
                </tr>
                <tr>
                    <td class="tblheader">Ключевые слова::</td>
                    <td class=tbltd1><%out.print(rs.getString("KEY_WORDS"));%></td>
                </tr>
                <tr>
                    <td class="tblheader">Ключевые Ф.И.О.:</td>
                    <td class=tbltd1><%out.print(rs.getString("KEY_FIOS"));%></td>
                </tr>
                <tr>
                    <td class="tblheader">Ключевая дата:</td>
                    <td class=tbltd1><b><%out.print(rs.getDate("KDATE"));%></b></td>
                </tr>
                <tr>
                    <td class="tblheader">Фаил:</td>
                    <td class=tbltd1>
                        <a href="GetBoxFile?id=<%=idS%>">
                            <img src="<%=accessIconPath%>" width="16" height="16" alt="ulock_16" border="0"/>
                            <img src="../resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0"/>
                            <%out.print(rs.getString("FILE_NAME"));%>
                        </a>
                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
<%
            }
            dwResult.closeAll();
%>
