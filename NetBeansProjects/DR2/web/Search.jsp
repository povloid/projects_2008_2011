<%-- 
    Document   : Search
    Created on : 07.09.2009, 9:14:41
    Author     : kopychenko
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="ips.dwh.datawork.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/stylesheet.css"
              type="text/css" />

        <link rel="stylesheet"  href="js/jquery/jquery-ui-1.7.2.custom.css" type="text/css" media="screen"/>



        <title>JSP Page</title>
    </head>
    <body>

        <jsp:include page="/WEB-INF/jspf/header.jsp"/>
        <!-- %@include file="SearchTop.jsp" % -->
        <table border="0" width="100%">
            <tbody>
                <tr>
                    <td>
                        <img src="resources/images/icons/srch_32.png" width="32" height="32" alt="srch_32"/>
                        Поиск:
                        <span>
                            <a href="Search.jsp?STYPE=FOR_ID"> по номеру документа </a>
                        </span>
                        <span style="border-left: groove teal 2px; margin: 2px; padding: 2px;">
                            <a href="Search.jsp?STYPE=FOR_KEY_WORDS"> простой по ключевым словам </a>
                        </span>
                        <span style="border-left: groove teal 2px; margin: 2px; padding: 2px;">
                            <a href="Search.jsp?STYPE=FOR_DATE"> за промежуток времени</a>
                        </span>
                    </td>
                </tr>
            </tbody>
        </table>
        <br>

        <%

            String webUserName = request.getRemoteUser();
            int userAccessLevel = DAOImpl.getUserAccessLevel(webUserName);

            request.setCharacterEncoding("UTF-8");
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

            String type = request.getParameter("STYPE");
            if (type == null || type.equals("FOR_ID")) {
                // Ищем по номеру документа

                String boxId = request.getParameter("BOX_ID");
                boxId = boxId != null ? boxId : "";
        %>
        <form name="FORM_FOR_ID" action="Search.jsp" method="POST" accept-charset="UTF-8">
            <input type="hidden" name="STYPE" value="FOR_ID" />
            <table align="center" width="80%" border="0" cellpadding="4" cellspacing="4" class="input-form">
                <thead>
                    <tr>
                        <th colspan="1" align="right">
                            <img src="resources/images/icons/prefs_32.png" width="32" height="32" alt="prefs_32"/>

                        </th>
                        <th align="left">
                            Поиск по номеру документа
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Документ №</td>
                        <td>
                            <input type="text" name="BOX_ID" value="<%=boxId%>" size="100%"/>
                        </td>
                        <td>
                            <input type="submit" value="Найти" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <script type="text/javascript">
            try{
                document.forms['FORM_FOR_ID'].BOX_ID.focus();
            }catch(e){};
        </script>

        <%
            } else if (type.equals("FOR_KEY_WORDS")) {

                String words = request.getParameter("KEY_WORDS");
                words = words != null ? words : "";

        %>
        <form name="FORM_FOR_KEY_WORDS" action="Search.jsp" method="POST" ENCTYPE="application/x-www-form-urlencoded; charset=UTF-8"  accept-charset="UTF-8">
            <input type="hidden" name="STYPE" value="FOR_KEY_WORDS" />
            <table align="center" width="80%" border="0" cellpadding="4" cellspacing="4" class="input-form">
                <thead>
                    <tr>
                        <th colspan="1" align="right">
                            <img src="resources/images/icons/prefs_32.png" width="32" height="32" alt="prefs_32"/>
                        </th>
                        <th align="left">
                            Простой поиск по ключевым словам
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Ключевые слова</td>
                        <td>
                            <input id="KEY_WORDS" type="text"  name="KEY_WORDS" value="<%=words%>" size="100%" />
                        </td>
                        <td>
                            <input type="submit" value="Найти"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <script type="text/javascript">
            try{
                document.forms['FORM_FOR_KEY_WORDS'].KEY_WORDS.focus();
            }catch(e){};
        </script>


        <%
            } else if (type.equals("FOR_DATE")) {

                String forKDate = request.getParameter("FOR_KDATE");

                String forCDate = request.getParameter("FOR_CDATE");

                String forUDate = request.getParameter("FOR_UDATE");


                String bkdate = request.getParameter("bkdate");
                String ekdate = request.getParameter("ekdate");

                String cbdate = request.getParameter("cbdate");
                String cedate = request.getParameter("cedate");

                String ubdate = request.getParameter("ubdate");
                String uedate = request.getParameter("uedate");

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");


                bkdate = bkdate != null ? bkdate : sdf.format(new Date());
                ekdate = ekdate != null ? ekdate : sdf.format(new Date());

                cbdate = cbdate != null ? cbdate : sdf.format(new Date());
                cedate = cedate != null ? cedate : sdf.format(new Date());

                ubdate = ubdate != null ? ubdate : sdf.format(new Date());
                uedate = uedate != null ? uedate : sdf.format(new Date());

        %>
        <form name="FORM_FOR_DATE" action="Search.jsp" method="POST"  onsubmit="return checkForm(this);">

            <script type="text/javascript" src='js/jquery/jquery-1.3.2.min.js'></script>
            <script type="text/javascript" src='js/jquery/jquery-ui-1.7.2.custom.min.js'></script>
            <script type="text/javascript" src='js/jquery/ui.datepicker-ru.js'></script>

            <script type="text/javascript">
                $(document).ready(function(){
                    $("#datepicker").datepicker();
                });
            </script>


            <input type="hidden" name="STYPE" value="FOR_DATE" />
            <table align="center" width="80%" border="0" cellpadding="4" cellspacing="4" class="input-form">
                <thead>
                    <tr>
                        <th colspan="1" align="right">
                            <img src="resources/images/icons/prefs_32.png" width="32" height="32" alt="prefs_32"/>
                        </th>
                        <th align="left">
                            Простой поиск по датам
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input id="FOR_KDATE" type="checkbox" name="FOR_KDATE" value="ON"  <%= forKDate != null ? "checked=\"checked\"" : ""%>  onchange="show_hide()"  /></td>
                        <td>По ключевым датам:</td>
                        <td>
                            с <input type="text" size="10" value="<%=bkdate%>" name="bkdate" id="bkDate"/>
                            по
                            <input type="text" size="10" value="<%=ekdate%>" name="ekdate" id="ekDate"/>
                            <script type="text/javascript">
                                function customRange(input) {
                                    return {minDate: (input.id == "ekDate" ? $("#bkDate").datepicker("getDate") : null),
                                        maxDate: (input.id == "bkDate" ? $("#ekDate").datepicker("getDate") : null)};
                                }
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td><input id="FOR_CDATE" type="checkbox" name="FOR_CDATE" value="ON"  <%= forCDate != null ? "checked=\"checked\"" : ""%>  onchange="show_hide()"  /></td>
                        <td>По дате создания:</td>
                        <td>
                            с <input type="text" size="10" value="<%=cbdate%>" name="cbdate" id="cbDate"/>
                            по
                            <input type="text" size="10" value="<%=cedate%>" name="cedate" id="ceDate"/>
                            <script type="text/javascript">
                                function customRange(input) {
                                    return {minDate: (input.id == "ceDate" ? $("#cbDate").datepicker("getDate") : null),
                                        maxDate: (input.id == "cbDate" ? $("#ceDate").datepicker("getDate") : null)};
                                }
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td><input id="FOR_UDATE" type="checkbox" name="FOR_UDATE" value="ON"  <%= forUDate != null ? "checked=\"checked\"" : ""%>  onchange="show_hide()"/></td>
                        <td>По дате последнего обновления:</td>
                        <td>
                            с <input type="text" size="10" value="<%=ubdate%>" name="ubdate" id="ubDate"/>
                            по
                            <input type="text" size="10" value="<%=uedate%>" name="uedate" id="ueDate"/>
                            <script type="text/javascript">
                                function customRange(input) {
                                    return {minDate: (input.id == "ueDate" ? $("#ubDate").datepicker("getDate") : null),
                                        maxDate: (input.id == "ubDate" ? $("#ueDate").datepicker("getDate") : null)};
                                }
                            </script>
                        </td>
                        <td>
                            <input type="submit" value="Найти"/>
                        </td>
                    </tr>
                </tbody>
            </table>

        </form>

        <script type="text/javascript">

            //$("#basics1").datepicker({
            //    showOn: "button"
            //});

            //$("#basics2").datepicker({
            //    showOn: "button"
            //});

            $("#bkDate,#ekDate").datepicker({
                beforeShow: customRange,
                showOn: "both",
                showOn: "button"
            });


            $("#cbDate,#ceDate").datepicker({
                beforeShow: customRange,
                showOn: "both",
                showOn: "button"
            });

            $("#ubDate,#ueDate").datepicker({
                beforeShow: customRange,
                showOn: "both",
                showOn: "button"
            });

            function show_hide(){

                document.getElementById('bkDate').disabled = !document.getElementById('FOR_KDATE').checked;
                document.getElementById('ekDate').disabled = !document.getElementById('FOR_KDATE').checked;

                document.getElementById('cbDate').disabled = !document.getElementById('FOR_CDATE').checked;
                document.getElementById('ceDate').disabled = !document.getElementById('FOR_CDATE').checked;

                document.getElementById('ubDate').disabled = !document.getElementById('FOR_UDATE').checked;
                document.getElementById('ueDate').disabled = !document.getElementById('FOR_UDATE').checked;
            }

            show_hide();

            function checkForm(form) {

                if(document.getElementById('FOR_KDATE').checked){
                    if(form.bkDate.value==''){
                        alert('Небходимо заполнить начальную ключевую дату!');
                        return false;
                    } else if(form.ekDate.value==''){
                        alert('Небходимо заполнить конечную ключевую дату!');
                        return false;
                    } else {
                        form.submit();
                        return true;
                    }
                }

                if(document.getElementById('FOR_CDATE').checked){
                    if(form.cbDate.value==''){
                        alert('Небходимо заполнить начальную дату создания!');
                        return false;
                    } else if(form.ceDate.value==''){
                        alert('Небходимо заполнить конечную дату создания!');
                        return false;
                    } else {
                        form.submit();
                        return true;
                    }
                }

                if(document.getElementById('FOR_UDATE').checked){
                    if(form.ubDate.value==''){
                        alert('Небходимо заполнить начальную дату последнего обновления!');
                        return false;
                    } else if(form.ueDate.value==''){
                        alert('Небходимо заполнить конечную дату последнего обновления!');
                        return false;
                    } else {
                        form.submit();
                        return true;
                    }
                }

                return true;
            }

        </script>


        <%            }
        %>


        <%
            String stype = request.getParameter("STYPE");

            DAOImpl.Result dwResult = null;
            ResultSet rs = null;

            if (stype != null) {

                if (stype.equals("FOR_ID")) {
                    // Выбор по номеру документа


                    String idS = request.getParameter("BOX_ID");

                    if (idS != null && idS.trim().length() > 0) {
                        dwResult = DAOImpl.getBox(webUserName,idS);
                        rs = dwResult.getRs();
                    }

                } else if (stype.equals("FOR_KEY_WORDS")) {
                    // Простой по ключевым словам

                    String words = request.getParameter("KEY_WORDS");
                    System.out.println(words);

                    if (words != null) {

                        dwResult = DAOImpl.getBoxesForWords(
                                webUserName,
                                "'" + words + "'");
                        rs = dwResult.getRs();
                    }

                } else if (stype.equals("FOR_DATE")) {

                    // За промежуток времени

                    String forKDate = request.getParameter("FOR_KDATE");
                    String forCDate = request.getParameter("FOR_CDATE");
                    String forUDate = request.getParameter("FOR_UDATE");

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

                    try {

                        boolean kdtype = forKDate == null ? false : forKDate.equals("ON");
                        boolean cdtype = forCDate == null ? false : forCDate.equals("ON");
                        boolean udtype = forUDate == null ? false : forUDate.equals("ON");

                        System.out.println("1");

                        Date bkdate = kdtype ? sdf.parse(request.getParameter("bkdate")) : null;
                        Date ekdate = kdtype ? sdf.parse(request.getParameter("ekdate")) : null;

                        Date cbdate = cdtype ? sdf.parse(request.getParameter("cbdate")) : null;
                        Date cedate = cdtype ? sdf.parse(request.getParameter("cedate")) : null;

                        Date ubdate = udtype ? sdf.parse(request.getParameter("ubdate")) : null;
                        Date uedate = udtype ? sdf.parse(request.getParameter("uedate")) : null;

                        System.out.println("2");

                        dwResult = DAOImpl.getBoxesForDates(
                                webUserName,
                                kdtype,
                                cdtype,
                                udtype,
                                bkdate, ekdate,
                                cbdate, cedate,
                                ubdate, uedate);
                        rs = dwResult.getRs();

                    } catch (Exception ex) {

        %>
        <br>
        Неправильный формат даты!
        <%=ex.getMessage()%>
        <%=ex.getStackTrace()%>

        <%
                        ex.printStackTrace();
                    }


                }

                if (rs != null) {
        %>


        <br>
        <hr>
        <table width="100%" border="0px">
            <caption align="top">
                <b>Документы</b>
            </caption>
            <thead>
                <tr>
                    <th class="tblheader">Док. №</th>
                    <th class="tblheader">Доступ</th>
                    <th class="tblheader">Наименование</th>
                    <th class="tblheader">Краткое описание</th>
                    <th class="tblheader">Ключевые слова</th>
                    <th class="tblheader">Ключевые Ф.И.О.:</th>
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
                    SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");

                    int cI = 2;
                    while (rs.next()) {
                        cI = cI == 2 ? 1 : 2;

                        int id = rs.getInt("ID");

                        String accessIconPath = "resources/images/icons/ulock_16.png";
                        if (userAccessLevel >= rs.getInt("ACCESS_LEVEL")) {
                            accessIconPath = "resources/images/icons/ulock_16.png";
                        } else {
                            accessIconPath = "resources/images/icons/lock_16.png";
                        }

                        // Работа с ключевой датой
                        Date kDate = rs.getDate("KDATE");
                        String sKDate = kDate == null ? "" : df2.format(kDate);

                        String parentIdS = rs.getString("SECTION_ID");
                        parentIdS = parentIdS == null ? "root" : parentIdS;

                %>


                <tr>
                    <td class=tbltd<%=cI%>>
                        <A href="PresentBox.do?id=<%=id%>" target="_blanck">
                            <img src="resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0" />
                            <%=id%>
                        </A>
                    </td>
                    <td class=tbltd<%=cI%>>
                        <%=rs.getString("ACCESS_KEY_NAME")%>
                    </td>
                    <td class=tbltd<%=cI%>>
                        <b>
                            <%=rs.getString("KEY_NAME")%>
                        </b>
                    </td>
                    <td class=tbltd<%=cI%>>
                        <%=rs.getString("DESCRIPTION")%>
                    </td>
                    <td class=tbltd<%=cI%>>
                        <%=rs.getString("KEY_WORDS")%>
                    </td>
                    <td class=tbltd<%=cI%>>
                        <%=rs.getString("KEY_FIOS")%>
                    </td>
                    <td class=tbltd<%=cI%> align="center">
                        <b>
                            <%=sKDate%>
                        </b>
                    </td>
                    <td class=tbltd<%=cI%>>
                        <a href="PresentBox.do?id=<%=id%>">
                            <img src="<%=accessIconPath%>" width="16" height="16" alt="ulock_16" border="0"/>
                            <img src="resources/images/icons/docs_16.png" width="16" height="16" alt="docs_16" border="0"/>
                            <%=rs.getString("FILE_NAME")%>
                        </a>
                    </td>
                    <td class=tbltd<%=cI%>>
                        <%=df.format(rs.getTimestamp("CDATE"))%>
                    </td>
                    <td class=tbltd<%=cI%>>
                        <%=df.format(rs.getTimestamp("UDATE"))%>
                    </td>
                    <% if (request.isUserInRole("ips_dwh_edit")) {%>
                    <td class=tbltd<%=cI%>>
                        <A href="OpSection.do?op=edit&id=<%=id%>&parent=<%=parentIdS%>">
                            <img src="resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                            Ред. 
                        </A> /
                        <A href="OpSection.do?op=del&id=<%=id%>&parent=<%=parentIdS%>">
                            <img src="resources/images/icons/remov_16.png" width="16" height="16" alt="remove_16" border="0"/>
                            Уд.
                        </A>
                    </td>
                    <%}%>
                </tr>


                <%
                    }
                }
            }
                %>

            </tbody>
        </table>


        <%
            if (dwResult != null) {
                dwResult.closeAll();
            }
        %>
    </body>
</html>

