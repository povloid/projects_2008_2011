<%-- 
    Document   : AddQuestionForm
    Created on : 02.11.2009, 0:58:11
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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить вопрос</title>
        <link rel="stylesheet" href="resources/stylesheet.css"
              type="text/css" />
    </head>
    <body>
        <%

            // Перебираем все отделы
            DepsJpaController depsCont = new DepsJpaController();
            QuestionsJpaController qCont = new QuestionsJpaController();
            WebUsersJpaController usersCont = new WebUsersJpaController();

            WebUsers user = usersCont.findWebUsersEntityForName(request.getRemoteUser());

            String op = request.getParameter("op");
            String id = request.getParameter("id");
            String readOnly = "";
            String QUESTION = "";
            String DESCRIPTION = "";

            if (op.equals("add")) {

            } else if (op.equals("edit") || op.equals("del")) {

                Questions q = qCont.findQuestions(Integer.parseInt(id));

                QUESTION = q.getQuestion();
                DESCRIPTION = q.getDescription();
            }

            if (op.equals("del")) {
                readOnly = "readonly=\"readonly\"";
            }

        %>
        <form name="ADD_QUESTION_FORM" action="AEDQuestion" method="POST">
            <input type="hidden" name="DEP" value="<%=request.getParameter("dep")%>" />
            <input type="hidden" name="OP" value="<%= op%>" />
            <input type="hidden" name="ID" value="<%= id%>" />
            <br>
            <table align="center" width="80%" border="0" cellpadding="4" cellspacing="4" class="input-form">
                <thead>
                    <tr>
                        <th colspan="1" align="right">
                            <img src="resources/images/icons/add_32.png" width="32" height="32" alt="add_32"/>
                        </th>
                        <th align="left">
                            <h2>Добавить вопрос</h2>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Вопрос:</td>
                        <td>
                            <textarea cols="100" rows="5"  name="QUESTION"  style="width: 100%" <%=readOnly%>><%=QUESTION%></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Краткое описание:</td>
                        <td>
                            <textarea cols="100" rows="10"  name="DESCRIPTION"  style="width: 100%" <%=readOnly%> /><%=DESCRIPTION%></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Подтвердить" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
