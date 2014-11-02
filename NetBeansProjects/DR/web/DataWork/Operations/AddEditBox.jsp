<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="ips.dwh.datawork.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib tagdir="/WEB-INF/tags/rb/" prefix="rb" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="../../resources/stylesheet.css"
              type="text/css" />

        <link rel="stylesheet"  href="../../js/jquery/jquery-ui-1.7.2.custom.css" type="text/css" media="screen"/>
        <script type="text/javascript" src='../../js/jquery/jquery-1.3.2.min.js'></script>
        <script type="text/javascript" src='../../js/jquery/jquery-ui-1.7.2.custom.min.js'></script>
        <script type="text/javascript" src='../../js/jquery/ui.datepicker-ru.js'></script>

        <script type="text/javascript">
            $(document).ready(function(){
                $("#datepicker").datepicker();
            });
        </script>

    </head>



    <%

            String webUserName = request.getRemoteUser();

            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            String op = request.getParameter("op");

            String id = request.getParameter("id");
            String parent = request.getParameter("parent");

            // Заполнение формы данными
            String parentKeyName = "";
            String keyName = "";
            String description = "";
            String keyWords = "";
            String keyFIOs = "";
            String fileName = "";
            String cdate = null;
            String udate = null;
            String accessLevel = "1";
            String kDate = "";


            if (op.equals("add_box")) {
                if (parent == null || parent.equals("root")) {
                    parentKeyName = "корень";
                } else {
                    DataWork.Result result = DataWork.getSection(parent);

                    ResultSet rs = result.getRs();
                    if (rs.next()) {
                        parentKeyName = rs.getString("KEY_NAME");
                    }

                    result.closeAll();
                }

            } else if (op.equals("edit_box") || op.equals("del_box")) {

                DataWork.Result result = DataWork.getBox(webUserName,id);

                ResultSet rs = result.getRs();
                if (rs.next()) {
                    keyName = rs.getString("KEY_NAME");
                    description = rs.getString("DESCRIPTION");
                    keyWords = rs.getString("KEY_WORDS");
                    keyFIOs = rs.getString("KEY_FIOS");
                    fileName = rs.getString("FILE_NAME");
                    cdate = df.format(rs.getTimestamp("CDATE"));
                    udate = df.format(rs.getTimestamp("UDATE"));
                    accessLevel = rs.getString("ACCESS_LEVEL");

                    Date tkDate = rs.getDate("KDATE");
                    kDate = tkDate == null ? "" :  sdf.format( tkDate );
                }

                result.closeAll();
            }

            String caption = null;
            String iconSrc = "../../resources/images/icons/add_32.png";
            String readOnly = "";
            if (op.equals("add_box")) {
                caption = "Добавить новый документ в группу " + parentKeyName;
                iconSrc = "../../resources/images/icons/add_32.png";
            } else if (op.equals("edit_box")) {
                caption = "Редактировать документ - " + keyName;
                iconSrc = "../../resources/images/icons/edit_32.png";
            } else if (op.equals("del_box")) {
                caption = "Удалить документ - " + keyName;
                readOnly = "readonly=\"readonly\"";
                iconSrc = "../../resources/images/icons/remov_32.png";
            }

            System.out.println("op=" + op + "; id=" + id + "; parent=" + parent);

            pageContext.setAttribute("access_level", accessLevel);

    %>


    <body>
        <form name="FORM_1" action="BoxesOpration" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="ID" value="<%out.print(id);%>">
            <input type="hidden" name="PARENT_ID" value="<%out.print(parent);%>">
            <input type="hidden" name="OPERATION" value="<%out.print(op);%>">
            <table align="center" width="90%" border="0" cellpadding="4" cellspacing="4" class="input-form">
                <thead>
                    <tr>
                        <th align="right">
                            <img src="<%out.print(iconSrc);%>" width="32" height="32" alt=""/>
                        </th >
                        <th align="left">
                            <h2><% out.print(caption);%></h2>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Доступ:</td>
                        <td>
                            <rb:rb_access_levels_select selected_id="${pageScope['access_level']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Наименование:</td>
                        <td><input type="text" name="KEY_NAME" size="100%" value="<% out.print(keyName);%>"  <% out.print(readOnly);%>/></td>
                    </tr>
                    <tr>
                        <td>Краткое описание:</td>
                        <td><textarea cols="100" rows="5"  name="DESCRIPTION" <% out.print(readOnly);%> style="width: 100%"/><% out.print(description);%></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Ключевые слова:</td>
                        <td>
                            <textarea cols="100" rows="5"  name="KEY_WORDS" <% out.print(readOnly);%> style="width: 100%"/><% out.print(keyWords);%></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Ключевые Ф.И.О:</td>
                        <td>
                            <textarea cols="100" rows="5"  name="KEY_FIOS" <% out.print(readOnly);%> style="width: 100%"/><% out.print(keyFIOs);%></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Ключевая дата:</td>
                        <td>
                            <input type="text" size="10" value="<%=kDate%>" name="K_DATE" id="kDate"/>
                        </td>
                    </tr>
                <script type="text/javascript">
                    $("#kDate").datepicker({
                        showOn: "button"
                    });
                </script>

                <%

        if (op.equals("edit_box") || op.equals("del_box")) {
                %>
                <tr>
                    <td>Закрепленный Фаил:</td>
                    <td>
                        <%out.print(fileName);%>
                    </td>
                </tr>
                <%            }
        if (!op.equals("del_box")) {
                %>
                <tr>
                    <td>Указать фаил:</td>
                    <td><input type="file" name="FILE" value="" size="100%" <% out.print(readOnly);%>/></td>
                </tr>
                <%

        }
        if (cdate != null) {
                %>
                <tr>
                    <td>Время создания:</td>
                    <td><% out.print(cdate);%></td>
                </tr>
                <%
        }
        if (udate != null) {
                %>
                <tr>
                    <td>Время последнего обновления:</td>
                    <td><% out.print(udate);%></td>
                </tr>
                <%}
                %>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Подтвердить" />
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <script type="text/javascript">
            try{
                document.forms['FORM_1'].KEY_NAME.focus();
            }catch(e){};
        </script>
    </body>
</html>