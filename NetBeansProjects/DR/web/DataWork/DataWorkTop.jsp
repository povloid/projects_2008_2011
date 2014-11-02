<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="ips.dwh.datawork.*"%>

<%
            String parent = request.getParameter("parent");
            String parentPar = parent == null ? "root" : parent;
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
    </head>

    <body class="toolbar">

        <table border="0" cellpadding="4" cellspacing="4" width="100%">
            <tbody>
                <tr >
                    <td><b>Путь: </b>
                        <A href="DataWork.jsp?op=update" target="_parent" >
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
                        <A href="DataWork.jsp?op=update&parent=<%out.println(idS);%>"
                           target="_parent" >
                            <img src="../resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0" />
                            <%out.println(keyName);%>/</A>
                        <%
                }
            }
                        %>
                    </td>
                </tr>
                <% if(request.isUserInRole("ips_dwh_edit")){%>
                <tr>
                    <td>
                        <b>Группы:</b>
                        <A href="DataWork.jsp?op=add_group&parent=<%out.println(parentPar);%>"
                           target="_parent" >
                            <img src="../resources/images/icons/add_16.png" width="16" height="16" alt="add_16" border="0" />
                            <img src="../resources/images/icons/foldr_16.png" width="16" height="16" alt="foldr_16" border="0"/>
                            Добавить группу</A>
                        <b>Документы:</b>
                        <A href="DataWork.jsp?op=add_box&parent=<%out.println(parentPar);%>"
                           target="_parent" >
                            <img src="../resources/images/icons/add_16.png" width="16" height="16" alt="add_16" border="0"/>
                            <img src="../resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0"/>
                            Добавить документ</A>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>