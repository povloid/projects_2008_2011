<%-- 
    Document   : boxOp
    Created on : 01.12.2009, 17:56:02
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/rb/" prefix="rb" %>


<%

            String webUserName = request.getRemoteUser();


            String op = request.getParameter("op");
            String caption = "";
            String iconSrc = "resources/images/icons/add_32.png";

            if (op.equals("add_box")) {
                caption = "Добавить новый документ в группу";
                iconSrc = "resources/images/icons/add_32.png";
            } else if (op.equals("edit_box")) {
                caption = "Редактировать документ";
                iconSrc = "resources/images/icons/edit_32.png";
            } else if (op.equals("del_box")) {
                caption = "Удалить документ";
                iconSrc = "resources/images/icons/remov_32.png";
            }


%>


<rb:rb_access_levels_select/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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

        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jspf/header.jsp"/>
        <h1>Работа с документом</h1>
        <!-- form name="FORM_1" action="BoxesOpration" method="POST" enctype="multipart/form-data" -->
        <html:form  action="/AOpBox" enctype="multipart/form-data">
            <html:hidden property="id" value="${param['id']}"/>
            <html:hidden property="op" value="${param['op']}"/>
            <html:hidden property="sectionId" value="${param['sectionId']}"/>
            <html:hidden property="repFilePath"/>
            <table align="center" width="90%" border="0" cellpadding="4" cellspacing="4" class="input-form">
                <thead>
                    <tr>
                        <th align="right" width="30%">
                            <img src="<%=iconSrc%>" width="32" height="32" alt=""/>
                        </th >
                        <th align="left">
                            <h2>Название</h2>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <html:errors/>
                        </td>
                    </tr>
                    <tr>
                        <td>Доступ:</td>
                        <td>
                            <html:select property="accessLevelId" >
                                <html:optionsCollection name="accessLevels" label="keyName" value="id"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Наименование:</td>
                        <td>
                            <html:text property="keyName" style="width: 100%"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Краткое описание:</td>
                        <td>
                            <html:textarea property="description" rows="5" style="width: 100%"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Ключевые слова:</td>
                        <td>
                            <html:textarea property="keyWords" rows="5" style="width: 100%"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Ключевые Ф.И.О:</td>
                        <td>
                            <html:textarea property="keyFios" rows="5" style="width: 100%"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Ключевая дата:</td>
                        <td>
                            <!-- input type="text" size="10" value="" name="K_DATE" id="kDate"/ -->
                            <html:text styleId="kDate" property="kDate" />
                        </td>
                    </tr>


                    <%

                                if (op.equals("edit") || op.equals("del")) {
                    %>
                    <tr>
                        <td>Закрепленный Фаил:</td>
                        <td>
                            <html:text property="fileName" readonly="true"/>
                        </td>
                    </tr>
                    <%            }
                                if (!op.equals("del")) {
                    %>
                    <tr>
                        <td>Указать фаил:</td>
                        <td>
                            <html:file property="inputFile" />
                        </td>
                    </tr>
                    <%            }
                    %>
                    <tr>
                        <td>Время создания:</td>
                        <td>
                            <html:text property="cdate" readonly="true"/>
                        </td>
                    </tr>
                    <%
                    %>
                    <tr>
                        <td>Время последнего обновления:</td>
                        <td>
                            <html:text property="udate" readonly="true"/>
                        </td>
                    </tr>
                    <%
                    %>
                    <tr>
                        <td colspan="2" align="center">
                            <html:submit value="Принять" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </html:form>
        <script type="text/javascript">
            $("#kDate").datepicker({
                showOn: "button"
            });
        </script>
        <script type="text/javascript">
            try{
                document.forms['BoxOpActionForm'].keyName.focus();
            }catch(e){};
        </script>
    </body>
</html>
