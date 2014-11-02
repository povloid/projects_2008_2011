<%-- 
    Document   : dsptcs
    Created on : 15.09.2010, 15:52:02
    Author     : dev_sport
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="disciples.dao.DAOImpl"%>
<%@ page import="kpg.db.sql.ADBBase.Result"%>
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
        <title>Физподготовки</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <n:header2 caption="Физподготовки" url_icon="../resources/images/icons/apps_32.png"/>
        <div class="datagrid">
            <table width="100%" border="0">
                <caption align="top">
                    <h2>Перечень физических подготовок</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="2%">№</th>
                        <th class="tblheader" width="20%">Фамилия</th>
                        <th class="tblheader" width="20%">Имя</th>
                        <th class="tblheader" width="5%">Текущий возраст (лет)</th>
                        <th class="tblheader" width="5%">рост</th>
                        <th class="tblheader" width="5%">вес</th>
                        <th class="tblheader" >категории</th>
                        <th class="tblheader" width="5%" >Действие</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        DAOImpl.Result resultSdeps = null;
                        try {
                            resultSdeps = DAOImpl.getDsptcs();
                            ResultSet rs = resultSdeps.getRs();
                            boolean isSp1Printed = false;
                            boolean isSp2Printed = false;
                            int currDspId = 0;

                            String alt="alt";

                            while (rs.next()) {
                                int sdepId = rs.getInt("sdep_id");
                                int sp = rs.getInt("sp");

                                alt = alt.length() > 0 ? "" : "alt";
                                

                                if (sp == 1 && !isSp1Printed) {
                                    // Заголовки
                    %>
                    <tr>
                        <td colspan="8" class="tblheader"><h3>Общая спортивная подготовка</h3></td>
                    </tr>
                    <%
                            isSp1Printed = true;
                        }
                        if (sp == 2 && !isSp2Printed) {
                    %>
                    <tr>
                        <td colspan="8" class="tblheader"><h3>Специализированная спортивная подготовка</h3></td>
                    </tr>
                    <%
                            isSp2Printed = true;
                        }
                        // Табличный вывод данных
                        if (currDspId != sdepId && sp != 1) {
                            currDspId = sdepId;
                    %>
                    <tr>
                        <td colspan="8" class="tblheader3"><b><%=rs.getString("sdep_key_name")%> - <%=rs.getString("sdep_description")%></b></td>
                    </tr>
                    <%
                        }

                        int discipleId = rs.getInt("id");
                    %>
                    <tr <tr class="<%=alt%>">
                        <td align="right" class=><%=discipleId%></td>
                        <td ><%=rs.getString("fname")%></td>
                        <td ><%=rs.getString("iname")%></td>
                        <td align="right" ><%=rs.getInt("disciple_age")%></td>
                        <td align="right" ><%=rs.getFloat("height")%></td>
                        <td align="right" ><%=rs.getFloat("weight")%></td>
                        <td ><%=rs.getString("get_dsptc")%></td>
                        <td >
                            <A href="addDsptc.action?dsptc.discipleId=<%=discipleId%>&dsptc.sdepId=<%=currDspId%>">
                                <img src="../resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                                Ред.
                            </A>
                        </td>
                    </tr>
                    <%                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    %>
                    <%=e.getMessage()%>
                    <%
                        } finally {
                            resultSdeps.closeAll();
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>