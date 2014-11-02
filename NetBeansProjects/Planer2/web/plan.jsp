<%-- 
    Document   : plan
    Created on : 03.11.2009, 12:11:10
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="ips.planer.dao.IPS_ODaoImpl" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<jsp:useBean id="currUser" scope="session" class="ips.planer.session.User" />
<jsp:useBean id="currDep" scope="session" class="ips.planer.session.Dep" />
<jsp:useBean id="selectParams" scope="session" class="ips.planer.session.SelectParams" />


<%

            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            currUser.init(request.getRemoteUser());
            currDep.init(currUser.getDepId());

            String bkdate = request.getParameter("bkdate");
            String ekdate = request.getParameter("ekdate");

            bkdate = bkdate != null ? bkdate : sdf.format(selectParams.getbDate());
            ekdate = ekdate != null ? ekdate : sdf.format(selectParams.geteDete());

            selectParams.setbDate(sdf.parse(bkdate));
            selectParams.seteDete(sdf.parse(ekdate));

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>План</title>
        <link rel="stylesheet" href="resources/stylesheet.css" type="text/css" />

        <link rel="stylesheet"  href="js/jquery/jquery-ui-1.7.2.custom.css" type="text/css" media="screen"/>

        <script type="text/javascript" src='js/jquery/jquery-1.3.2.min.js'></script>
        <script type="text/javascript" src='js/jquery/jquery-ui-1.7.2.custom.min.js'></script>
        <script type="text/javascript" src='js/jquery/ui.datepicker-ru.js'></script>

        <script type="text/javascript">
            $(document).ready(function(){
                $("#datepicker").datepicker();
            });
        </script>

    </head>
    <body>
        <table border="0">
            <tbody>
                <tr>
                    <td align="right">Вы зашли как:</td>
                    <td>
                        <b><%=currUser.getUserName()%> - <%=currUser.getDescription()%>
                        </b>
                    </td>
                </tr>
                <tr>
                    <td align="right">Отдел:</td>
                    <td><b><%=currDep.getKeyName()%>  - <%=currDep.getDescription()%></b></td>
                </tr>
            </tbody>
        </table>
        <h1>План</h1>
        <form name="REQ_FORM" action="plan.jsp" method="POST">
            с <input type="text" size="10" value="<%=bkdate%>" name="bkdate" id="bkDate"/>
            по
            <input type="text" size="10" value="<%=ekdate%>" name="ekdate" id="ekDate"/>
            <script type="text/javascript">
                function customRange(input) {
                    return {minDate: (input.id == "ekDate" ? $("#bkDate").datepicker("getDate") : null),
                        maxDate: (input.id == "bkDate" ? $("#ekDate").datepicker("getDate") : null)};
                }
            </script>
            <input type="submit" value="Принять"/>
        </form>

        <script type="text/javascript">
            $("#bkDate,#ekDate").datepicker({
                beforeShow: customRange,
                showOn: "both",
                showOn: "button"
            });
        </script>


        <table border="0" width="100%" cellpadding="12" cellspacing="5" class="qtable">
            <!--thead>
                <tr>
                    <th>Отдел</th>
                    <th>Входящие вопросы и задания</th>
                    <th>Исходящие вопросы и задания</th>
                </tr>
            </thead -->
            <tbody>

                <%

                            // Получаем список подразделений
                            IPS_ODaoImpl.Result depsResult = IPS_ODaoImpl.getDeps();

                            ResultSet rs = depsResult.getRs();
                            while (rs.next()) {
                                int depId = rs.getInt("ID");
                %>
                <tr>
                    <td colspan="3" class="qtable-cell">
                        <b><%=rs.getString("KEY_NAME")%></b> - <%=rs.getString("DESCRIPTION")%>
                        <br>
                        <%
                                        if (currDep.getId() != depId) {
                        %>
                        <a href="AEDQuestionForm.jsp?dep=<%=depId%>&op=add">
                            <img src="resources/images/icons/add_16.png" width="16" height="16" alt="add_16" border="0"/>

                            Добавить вопрос</a>
                            <%
                                            } else {
                            %>
                        <img src="resources/images/icons/home_32.png" width="32" height="32" alt="home_32"/>
                        Ваш отдел

                        <%                }

                        %>
                        <br>
                        <br>
                        <table border="0" width="100%">
                            <caption>Входящие вопросы и задания - <%=IPS_ODaoImpl.getInputQuestionCount(depId, selectParams.getbDate(), selectParams.geteDete())%></caption>
                            <thead>
                                <tr>
                                    <th class="tblheader" width="60px">№</th>
                                    <th class="tblheader" width="150px">Время</th>
                                    <th class="tblheader" width="50%">Вопрос</th>
                                    <th class="tblheader">Примечания</th>
                                    <th class="tblheader" width="30px">Операции</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                                // Получаем список подразделений
                                                IPS_ODaoImpl.Result otherDepsResult = IPS_ODaoImpl.getDepsNotForDep(depId, selectParams.getbDate(), selectParams.geteDete());
                                                ResultSet rs2 = otherDepsResult.getRs();
                                                while (rs2.next()) {

                                                    int fromDepId = rs2.getInt("ID");

                                %>
                                <tr>
                                    <td colspan="3" class="group-cell">
                                        <b><%=rs2.getString("KEY_NAME")%></b> - <%=rs2.getString("DESCRIPTION")%>
                                    </td>
                                    <td colspan="2" align="right" class="group-cell">
                                        <b>Всего вопросов: <%=rs2.getString("INCOUNT")%></b>
                                    </td>
                                </tr>
                                <%

                                                    // Получаем список подразделений
                                                    IPS_ODaoImpl.Result qResult = IPS_ODaoImpl.getQuestionsForDepFromDep(depId, fromDepId, selectParams.getbDate(), selectParams.geteDete());
                                                    ResultSet rs3 = qResult.getRs();
                                                    int cI = 2;
                                                    boolean showEdit = currDep.getId() == fromDepId;

                                                    while (rs3.next()) {
                                                        cI = cI == 2 ? 1 : 2;

                                                        int qId = rs3.getInt("ID");

                                %>

                                <tr>
                                    <td class=tbltd<%=cI%> align="right"><%=qId%></td>
                                    <td class=tbltd<%=cI%>><b><%=df.format(rs3.getTimestamp("CTIME"))%></b></td>
                                    <td class=tbltd<%=cI%>><%=rs3.getString("QUESTION")%></td>
                                    <td class=tbltd<%=cI%>><%=rs3.getString("DESCRIPTION")%></td>
                                    <%

                                                            if (showEdit) {

                                    %>
                                    <td class=tbltd<%=cI%> align="center">
                                        <a href="AEDQuestionForm.jsp?op=edit&id=<%=qId%>">
                                            <img src="resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                                        </a>
                                        /
                                        <a href="AEDQuestionForm.jsp?op=del&&id=<%=qId%>">
                                            <img src="resources/images/icons/remov_16.png" width="16" height="16" alt="remov_16" border="0"/>
                                        </a>
                                    </td>
                                    <%
                                                            }
                                    %>
                                </tr>

                                <%

                                                    }
                                                    qResult.closeAll();
                                                }

                                %>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <%
                                otherDepsResult.closeAll();


                            } // 1 Департаменты

                %>
            </tbody>
        </table>
    </body>
</html>

<%
            // Обязательно закрываем ресурсы
            depsResult.closeAll();

%>
