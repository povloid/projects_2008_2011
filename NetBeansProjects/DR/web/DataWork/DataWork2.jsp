<%-- 
    Document   : DataWork2
    Created on : 02.10.2009, 16:27:18
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="ips.dwh.datawork.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
            int userAccessLevel = DataWork.getUserAccessLevel(request.getRemoteUser());

            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
            SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");

            // Получаем параметры 
            String parent = request.getParameter("parent");
            String parentPar = parent == null ? "root" : parent;

            if (parent == null || parent.equals("root")) {
                parent = null;
            }

%>

<jsp:useBean id="buffer" scope="session" class="ips.dwh.buffer.Buffer" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Работа с данными 2</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
    </head>
    <body>

        <table border="0" cellpadding="4" cellspacing="4" width="100%">
            <tbody>
                <tr>
                    <td><b>Путь: </b>
                        <A href="DataWork2.jsp?op=update">
                            <img src="../resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0" />
                            Корень/</A>
                            <%

            String path = DataWork.getSectionPath2(parent);
            System.out.println(path);

            if (path != null) {
                String[] sections = path.split("/");

                for (String s : sections) {
                    String[] attrs = s.split("-");
                    String idS = attrs[0];
                    String keyName = attrs[1];

                            %>
                        <A href="DataWork2.jsp?op=update&parent=<%=idS%>">
                            <img src="../resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0" />
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
                        <A href="Operations/AddEditGroup.jsp?op=add_group&parent=<%=parentPar%>">
                            <img src="../resources/images/icons/add_16.png" width="16" height="16" alt="add_16" border="0"/>
                            <img src="../resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0"/>
                            Добавить группу</A>
                        <b>Документы:</b>
                        <A href="Operations/AddEditBox.jsp?op=add_box&parent=<%=parentPar%>">
                            <img src="../resources/images/icons/add_16.png" width="16" height="16" alt="add_16" border="0"/>
                            <img src="../resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0"/>
                            Добавить документ</A>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>










        <form name="TO_BUFFER" action="Operations/CopyToBuffer" method="POST">

            <% if (request.isUserInRole("ips_dwh_edit")) {%>
            <input type="submit" value="Копировать"/>
            <a href="Operations/PasteFromBuffer?parent=<%=parentPar%>">
                Вставить <%=buffer.getSelectedSections().size()%> секций и <%=buffer.getSelectedBoxes().size()%> документов
            </a>

            <%}%>

            <input type="hidden" name="parent" value="<%=parentPar%>" />
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
            {
                DataWork.Result result = DataWork.getSections(parent);

                ResultSet rs = result.getRs();

                int cI = 2;
                while (rs.next()) {

                    String parentId = rs.getString("PARENT_ID");
                    parentId = parentId == null ? "root" : parentId;

                    cI = cI == 2 ? 1 : 2;

                    String idS = rs.getString("ID");

                    %>
                    <tr>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <td class=tbltd<%=cI%>>
                            <input type="checkbox" name="select_section" value="<%=idS%>" />
                        </td>
                        <%}%>
                        <td class=tbltd<%=cI%>>
                            <A href="DataWork2.jsp?op=update&parent=<%=idS%>">
                                <img src="../resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0"/>
                                <%=rs.getString("KEY_NAME")%>
                            </A>
                        </td>
                        <td class=tbltd<%=cI%>>
                            <%=rs.getString("DESCRIPTION")%>
                        </td>
                        <% if (request.isUserInRole("ips_dwh_edit")) {%>
                        <td class=tbltd<%=cI%>>
                            <A href="Operations/AddEditGroup.jsp?op=edit_group&id=<%=rs.getString("ID")%>&parent=<%=parentId%>">
                                <img src="../resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                                Ред.
                            </A> /
                            <A href="Operations/AddEditGroup.jsp?op=del_group&id=<%=rs.getString("ID")%>&parent=<%=parentId%>">
                                <img src="../resources/images/icons/remov_16.png" width="16" height="16" alt="remove_16" border="0"/>
                                Уд.
                            </A>
                        </td>
                        <%}%>
                    </tr>

                    <%

                }

                result.closeAll();
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
                        <th class="tblheader">Пом.</th>
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

                        <%}%>
                    </tr>
                </thead>
                <tbody>
                    <%
            {

                DataWork.Result docsResult = DataWork.getBoxes(
                        request.getRemoteUser(),
                        parent);

                ResultSet docsRs = docsResult.getRs();

                int cI = 2;
                while (docsRs.next()) {

                    cI = cI == 2 ? 1 : 2;

                    String idS = docsRs.getString("ID");

                    String parentIdS = docsRs.getString("SECTION_ID");
                    parentIdS = parentIdS == null ? "root" : parentIdS;

                    String accessIconPath = "../resources/images/icons/ulock_16.png";
                    if (userAccessLevel >= docsRs.getInt("ACCESS_LEVEL")) {
                        accessIconPath = "../resources/images/icons/ulock_16.png";
                    } else {
                        accessIconPath = "../resources/images/icons/lock_16.png";
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
                            <A href="../BoxPresent/ShowBox.jsp?id=<%=idS%>" target="_blanck">
                                <img src="../resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0"/>
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
                            <a href="../BoxPresent/GetBoxFile?id=<%=idS%>">
                                <img src="<%=accessIconPath%>" width="16" height="16" alt="ulock_16" border="0"/>
                                <img src="../resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0"/>
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
                            <A href="Operations/AddEditBox.jsp?op=edit_box&id=<%=idS%>&parent=<%=parentIdS%>">
                                <img src="../resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                                Ред.
                            </A> /
                            <A href="Operations/AddEditBox.jsp?op=del_box&id=<%=idS%>&parent=<%=parentIdS%>">
                                <img src="../resources/images/icons/remov_16.png" width="16" height="16" alt="remove_16" border="0"/>
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
