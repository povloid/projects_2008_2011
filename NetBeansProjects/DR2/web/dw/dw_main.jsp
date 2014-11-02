<%-- 
    Document   : dw_main
    Created on : 26.11.2009, 16:51:37
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="ips.dwh.datawork.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:useBean id="dateWork" scope="session" class="ips.dwh.session.DataWork" />
<jsp:useBean id="buffer" scope="session" class="ips.dwh.buffer.Buffer" />

<%
            int userAccessLevel = DAOImpl.getUserAccessLevel(request.getRemoteUser());

            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
            SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");

            Integer parentId = null;
            if (request.getParameterMap().containsKey("parentId")) {
                String parentIdS = request.getParameter("parentId");
                parentId = (parentIdS == null ||
                        parentIdS.length() == 0 ||
                        parentIdS.equals("null")) ? null : Integer.parseInt(parentIdS);
                dateWork.setParentId(parentId);
            } else {
                parentId = dateWork.getParentId();
            }

%>
<html>
    <head>
        <link rel="stylesheet" href="resources/stylesheet.css"
              type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Работа с данными</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jspf/header.jsp"/>
        <h1>Работа с данными</h1>

        <table border="0" cellpadding="4" cellspacing="4" width="100%">
            <tbody>
                <tr >
                    <td><b>Путь: </b>
                        <A href="DataWork.do?op=update&parentId" >
                            <img src="resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0" />
                            Корень/</A>
                            <%

            String path = DAOImpl.getSectionPath2(parentId);
            System.out.println(path);

            if (path != null) {
                String[] sections = path.split("/");

                for (String s : sections) {
                    String[] attrs = s.split("-");
                    String idS = attrs[0];

                    String keyName = attrs[1];

                            %>
                        <A href="DataWork.do?op=update&parentId=<%=idS%>"
                           <img src="resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0" />
                            <%=keyName%>/</A>
                            <%
                }
            }
                            %>
                    </td>
                </tr>
                <% if (request.isUserInRole("ips_dwh_edit")) {%>
                <tr>
                    <td>
                        <b>Группы:</b>
                        <A href="OpSection.do?op=add&parentId=<c:out value="${param['parentId']}"/>">
                            <img src="resources/images/icons/add_16.png" width="16" height="16" alt="add_16" border="0" />
                            <img src="resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0"/>
                            Добавить группу</A>
                        <b>Документы:</b>
                        <A href="OpBox.do?op=add&sectionId=<c:out value="${param['parentId']}"/>">
                            <img src="resources/images/icons/add_16.png" width="16" height="16" alt="add_16" border="0"/>
                            <img src="resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0"/>
                            Добавить документ</A>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <form name="TO_BUFFER" action="CopyToBuffer.do" method="POST">
            <% if (request.isUserInRole("ips_dwh_edit")) {%>
            <input type="submit" value="Копировать"/>
            <a href="PasteFromBuffer.do">
                Вставить <%=buffer.getSelectedSections().size()%> секций и <%=buffer.getSelectedBoxes().size()%> документов
            </a>
            <%}%>
            <table width="100%" border="0px">
                <caption align="top"><b>Секции</b></caption>
                <thead>
                    <tr>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <th class="tblheader" width="5%">Пом.</th>
                        <%}%>
                        <th class="tblheader">Наименование</th>
                        <th class="tblheader">Краткое описание</th>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <th class="tblheader" width="100px">Действие</th>
                        <%}%>
                    </tr>
                </thead>
                <tbody>

                    <%

            DAOImpl.Result result = DAOImpl.getChildSections(parentId);
            ResultSet rs = result.getRs();


            int cI = 2;
            while (rs.next()) {


                int id = rs.getInt("ID");

                String parentIdS2 = rs.getString("PARENT_ID");

                cI = cI == 2 ? 1 : 2;

                    %>

                    <tr>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <td class=tbltd<%=cI%>>
                            <input type="checkbox" name="select_section" value="<%=id%>" />
                        </td>
                        <%}%>
                        <td class=tbltd<%=cI%>>
                            <A href="DataWork.do?op=update&parentId=<%=id%>" target="_parent">
                                <img src="resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0"/>
                                <%=rs.getString("KEY_NAME")%>
                            </A>
                        </td>
                        <td class=tbltd<%=cI%>>
                            <%=rs.getString("DESCRIPTION")%>
                        </td>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <td class=tbltd<%=cI%>>
                            <A href="OpSection.do?op=edit&id=<%=id%>&parent=<%=parentIdS2%>">
                                <img src="resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                                Ред.
                            </A> /
                            <A href="OpSection.do?op=del&id=<%=id%>&parent=<%=parentIdS2%>">
                                <img src="resources/images/icons/remov_16.png" width="16" height="16" alt="remove_16" border="0"/>
                                Уд.
                            </A>
                        </td>
                        <%}%>
                    </tr>
                    <%
            }
                    %>
                </tbody>
            </table>
            <br>
            <hr>
            <table width="100%" border="0px">
                <caption align="top"><b>Документы</b></caption>
                <thead>
                    <tr>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <th class="tblheader" width="5%">Пом.</th>
                        <%}%>
                        <th class="tblheader">Док. №</th>

                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <th class="tblheader">Доступ</th>
                        <%}%>

                        <th class="tblheader">Наименование</th>
                        <th class="tblheader">Краткое описание</th>
                        <th class="tblheader">Ключевые слова</th>
                        <th class="tblheader">Ключевые Ф.И.О.</th>
                        <th class="tblheader">Ключевая дата</th>
                        <th class="tblheader">Фаил:</th>
                        <th class="tblheader">Дата создания</th>
                        <th class="tblheader">Дата последнего<br>
			обновления</th>

                        <% if (request.isUserInRole("ips_dwh_edit")) {%>

                        <th class="tblheader" width="100px" >Действие</th>

                        <%            }
                        %>
                    </tr>
                </thead>
                <tbody>
                    <%
            {

                DAOImpl.Result docsResult = DAOImpl.getChildBoxes(parentId, request.getRemoteUser());

                ResultSet docsRs = docsResult.getRs();

                cI = 2;
                while (docsRs.next()) {

                    cI = cI == 2 ? 1 : 2;

                    String idS = docsRs.getString("ID");

                    String parentIdS2 = docsRs.getString("SECTION_ID");

                    String accessIconPath = "resources/images/icons/ulock_16.png";
                    if (userAccessLevel >= docsRs.getInt("ACCESS_LEVEL")) {
                        accessIconPath = "resources/images/icons/ulock_16.png";
                    } else {
                        accessIconPath = "resources/images/icons/lock_16.png";
                    }

                    // Работа с ключевой датой
                    Date kDate = docsRs.getDate("KDATE");
                    String sKDate = kDate == null ? "" : df2.format(kDate);


                    %>
                    <tr>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <td class=tbltd<%=cI%>>
                            <input type="checkbox" name="select_box" value="<%=idS%>" />
                        </td>
                        <%}%>
                        <td class=tbltd<%=cI%>>
                            <A href="PresentBox.do?id=<%=idS%>" target="_blanck">
                                <img src="resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0"/>
                                №<%=idS%>

                            </A>
                        </td>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <td class=tbltd<%=cI%>>
                            <b>
                                <%=docsRs.getString("ACCESS_KEY_NAME")%>
                            </b>
                        </td>
                        <%}%>
                        <td class=tbltd<%=cI%>>
                            <%=docsRs.getString("KEY_NAME")%>
                        </td>
                        <td class=tbltd<%=cI%>>
                            <%=docsRs.getString("DESCRIPTION")%>
                        </td>
                        <td class=tbltd<%=cI%>>
                            <%=docsRs.getString("KEY_WORDS")%>
                        </td>
                        <td class=tbltd<%=cI%>>
                            <%=docsRs.getString("KEY_FIOS")%>
                        </td>
                        <td class=tbltd<%=cI%> align="center">
                            <b>
                                <%=sKDate%>
                            </b>
                        </td>
                        <td class=tbltd<%=cI%>>
                            <a href="PresentBox.do?id=<%=idS%>">
                                <img src="<%=accessIconPath%>" width="16" height="16" alt="ulock_16" border="0"/>
                                <img src="resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0"/>
                                <%=docsRs.getString("FILE_NAME")%>
                            </a>
                        </td>
                        <td class=tbltd<%=cI%>>
                            <%=df.format(docsRs.getTimestamp("CDATE"))%>
                        </td>
                        <td class=tbltd<%=cI%>>
                            <%=df.format(docsRs.getTimestamp("UDATE"))%>
                        </td>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <td class=tbltd<%=cI%>>
                            <A href="OpBox.do?op=edit&id=<%=idS%>&parent=<%=parentIdS2%>">
                                <img src="resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                                Ред.
                            </A> /
                            <A href="OpBox.do?op=del&id=<%=idS%>&parent=<%=parentIdS2%>">
                                <img src="resources/images/icons/remov_16.png" width="16" height="16" alt="remove_16" border="0"/>
                                Уд.
                            </A>
                        </td>
                        <%}%>
                    </tr>
                    <%
                }

                docsResult.closeAll();
            }
                    %>
                </tbody>
            </table>
        </form>
    </body>
</html>

<%
            result.closeAll();
%>
