<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="ips.dwh.datawork.*"%>
<%@ page import="java.sql.ResultSet"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add/Edit Group</title>
        <link rel="stylesheet" href="../../resources/stylesheet.css"
              type="text/css" />
    </head>
    <body>

        <%
            String op = request.getParameter("op");

            String id = request.getParameter("id");
            String parent = request.getParameter("parent");

            // Заполнение формы данными
            String keyName = "";
            String description = "";
            String parentKeyName = "";
            if (op.equals("add_group")) {
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
            } else if (op.equals("edit_group") || op.equals("del_group")) {
                DataWork.Result result = DataWork.getSection(id);

                ResultSet rs = result.getRs();
                if (rs.next()) {
                    keyName = rs.getString("KEY_NAME");
                    description = rs.getString("DESCRIPTION");
                }

                result.closeAll();
            }

            String caption = null;
            String readOnly = "";
            String iconSrc = "../../resources/images/icons/add_32.png";
            if (op.equals("add_group")) {
                caption = "Добавить новую группу в группу - " + parentKeyName;
                iconSrc = "../../resources/images/icons/add_32.png";
            } else if (op.equals("edit_group")) {
                caption = "Редактировать группу - " + keyName;
                iconSrc = "../../resources/images/icons/edit_32.png";
            } else if (op.equals("del_group")) {
                caption = "Удалить группу - " + keyName;
                readOnly = "readonly=\"readonly\"";
                iconSrc = "../../resources/images/icons/remov_32.png";
            }

            System.out.println("op=" + op + "; id=" + id + "; parent=" + parent);
        %>


        <form  name="FORM_1" action="GroupOpration" method="POST">
            <table align="center" width="80%" border="0" cellpadding="4" cellspacing="4" class="input-form">
                <thead>
                    <tr>
                        <th colspan="1" align="right">
                            <img src="<%out.print(iconSrc);%>" width="32" height="32" alt="" />
                        </th>
                        <th align="left">
                            <h2><% out.print(caption);%></h2>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Наименование:</td>
                        <td><input type="text" name="KEY_NAME" size="100%"
                                   value="<%out.print(keyName);%>" <%out.print(readOnly);%> /></td>
                    </tr>
                    <tr>
                        <td>Краткое описание:</td>
                        <td>
                            <textarea cols="100" rows="5"  name="DESCRIPTION" <% out.print(readOnly);%> style="width: 100%"/><% out.print(description);%></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Подтвердить" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="ID" value="<%out.print(id);%>"> <input
                type="hidden" name="PARENT_ID" value="<%out.print(parent);%>">
            <input type="hidden" name="OPERATION" value="<%out.print(op);%>">
        </form>
        <script type="text/javascript">
            try{
                document.forms['FORM_1'].KEY_NAME.focus();
            }catch(e){};
        </script>
    </body>
</html>