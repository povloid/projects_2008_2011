<%-- 
    Document   : plan
    Created on : 01.11.2009, 14:51:14
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="ips.planer.dao.jpa.Deps"%>
<%@ page import="ips.planer.dao.jpa.DepsJpaController"%>
<%@ page import="ips.planer.dao.jpa.Questions"%>
<%@ page import="ips.planer.dao.jpa.QuestionsJpaController"%>
<%@ page import="ips.planer.dao.jpa.WebUsers"%>
<%@ page import="ips.planer.dao.jpa.WebUsersJpaController"%>
<%@ page import="java.util.List"%>

<%



            // Перебираем все отделы
            DepsJpaController depsCont = new DepsJpaController();
            QuestionsJpaController qCont = new QuestionsJpaController();
            WebUsersJpaController usersCont = new WebUsersJpaController();

            WebUsers user = usersCont.findWebUsersEntityForName(request.getRemoteUser());
%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>План</title>
        <link rel="stylesheet" href="resources/stylesheet.css"
              type="text/css" />
    </head>
    <body>
        <table border="0">
            <tbody>
                <tr>
                    <td align="right">Вы зашли как:</td>
                    <td>
                        <b><%=user.getUserName()%> - <%=user.getDescription()%>
                        </b>
                    </td>
                </tr>
                <tr>
                    <td align="right">Отдел:</td>
                    <td><b><%=user.getDepId().getKeyName()%>  - <%=user.getDepId().getDescription()%></b></td>
                </tr>
            </tbody>
        </table>
        <h1>План</h1>
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
            List<Deps> deps = depsCont.findDepsEntitiesForNotForDep(user.getDepId());
            for (Deps dep : deps) {
                List<Questions> qs = qCont.findQuestionsEntitiesForDeps(dep, user.getDepId());

                %>
                <tr>
                    <td colspan="3" class="qtable-cell">
                        <b><%=dep.getKeyName()%></b> - <%= dep.getDescription()%>
                        <br>
                        <a href="AEDQuestionForm.jsp?op=add&dep=<%=dep.getId()%>">
                            <img src="resources/images/icons/add_16.png" width="16" height="16" alt="add_16" border="0"/>

                            Добавить вопрос</a>
                        <br>
                        <table border="0" width="100%">
                            <caption>Исходящие вопросы и задания - <%=qs.size()%></caption>
                            <thead>
                                <tr>
                                    <th class="tblheader" width="60px">№</th>
                                    <th class="tblheader" width="50%">Вопрос</th>
                                    <th class="tblheader">Примечания</th>
                                    <th class="tblheader" width="30px">Операции</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%

                                int cI = 2;
                                for (Questions q : qs) {
                                    cI = cI == 2 ? 1 : 2;
                                %>
                                <tr>
                                    <td class=tbltd<%=cI%> align="right"><%= q.getId()%></td>
                                    <td class=tbltd<%=cI%>><%= q.getQuestion()%></td>
                                    <td class=tbltd<%=cI%>><%=q.getDescription()%></td>
                                    <td class=tbltd<%=cI%> align="center">
                                        <a href="AEDQuestionForm.jsp?op=edit&dep=<%=dep.getId()%>&id=<%=q.getId()%>">
                                            <img src="resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                                        </a>
                                        /
                                        <a href="AEDQuestionForm.jsp?op=del&dep=<%=dep.getId()%>&id=<%=q.getId()%>">
                                            <img src="resources/images/icons/remov_16.png" width="16" height="16" alt="remov_16" border="0"/>
                                        </a>
                                    </td>
                                </tr>
                                <%                }

                                qs = qCont.findQuestionsEntitiesForDeps(user.getDepId(), dep);
                                %>
                            </tbody>
                        </table>
                        <br>
                        <table border="0" width="100%">
                            <caption>Входящие вопросы и задания - <%=qs.size()%></caption>
                            <thead>
                                <tr>
                                    <th class="tblheader" width="60px">№</th>
                                    <th class="tblheader" width="50%">Вопрос</th>
                                    <th class="tblheader">Примечания</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%


                                for (Questions q : qs) {
                                    cI = cI == 2 ? 1 : 2;
                                %>
                                <tr>
                                    <td class=tbltd<%=cI%>  align="right" ><%= q.getId()%></td>
                                    <td class=tbltd<%=cI%>><%= q.getQuestion()%></td>
                                    <td class=tbltd<%=cI%>><%=q.getDescription()%></td>
                                </tr>
                                <%                }
                                %>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <%
            }
                %>
            </tbody>
        </table>

    </body>
</html>
