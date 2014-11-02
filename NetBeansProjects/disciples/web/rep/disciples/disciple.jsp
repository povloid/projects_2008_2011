<%-- 
    Document   : disciple
    Created on : 18.10.2010, 20:25:11
    Author     : dev_sport
--%>

<%@page import="disciples.model.SClass"%>
<%@page import="disciples.model.Sdep"%>
<%@page import="disciples.model.Disciple"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="disciples.dao.DAOImpl"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags" %>



<%
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    int discipleId = Integer.parseInt(request.getParameter("id"));

    Disciple disciple = DAOImpl.loadDisciple(discipleId);
    Sdep sdep = DAOImpl.loadSdep(disciple.getSdepId());
    SClass sclass = DAOImpl.loadSclass(disciple.getSclassId());
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />
        <title>Сведения о ученике №<%=disciple.getId()%> - <%=disciple.getFname()%> <%=disciple.getIname()%></title>
    </head>
    <body bgcolor="f1f1f1">
        <h1>Сведения о ученике №<%=disciple.getId()%></h1>
        <h2>Дата: <%=df.format(new java.util.Date())%></h2>
        <div class="datagrid">
            <table border="0" width="60%">
                <caption align="top">
                    <h2>Персональные данные</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="10%"></th>
                        <th class="tblheader" ></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td >Фото:</td>
                        <td ><img src="foto.action?id=<%=disciple.getId()%>" width="150" height="200" alt="Нет фотографии" border="1"
                                  />                        
                        </td>
                    </tr>
                    <tr>
                        <td >Фамилия:</td>
                        <td ><%=disciple.getFname()%></td>
                    </tr>
                    <tr>
                        <td >Имя</td>
                        <td ><%=disciple.getIname()%></td>
                    </tr>
                    <tr>
                        <td >Отчество</td>
                        <td ><%=disciple.getOname()%></td>
                    </tr>
                    <tr>
                        <td >Родился</td>
                        <td ><%= disciple.getBirthday() != null ? df.format(disciple.getBirthday()) : ""%></td>
                    </tr>
                    <tr>
                        <td >Поступил</td>
                        <td ><%= disciple.getBdate() != null ? df.format(disciple.getBdate()) : ""%></td>
                    </tr>
                    <tr>
                        <td >Закончил</td>
                        <td ><%= disciple.getEdate() != null ? df.format(disciple.getEdate()) : ""%></td>
                    </tr>
                    <tr>
                        <td >Спортивное отделение</td>
                        <td ><%=sdep.getKeyName()%></td>
                    </tr>
                    <tr>
                        <td >Спортивный класс</td>
                        <td ><%=sclass.getKeyName()%></td>
                    </tr>
                    <tr>
                        <td >Средний балл</td>
                        <td ><%=disciple.getAvscore()%></td>
                    </tr>
                    <tr>
                        <td >Сведения о родителях</td>
                        <td >
                            <c:out value="<%=disciple.getParents()%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td >Увлечения</td>
                        <td ><c:out value="<%=disciple.getHobbi()%>"/></td>
                    </tr>
                    <tr>
                        <td >Характеристика</td>
                        <td ><c:out value="<%=disciple.getPerformance()%>"/></td>
                    </tr>
                    <tr>
                        <td >Прочее</td>
                        <td ><c:out value="<%=disciple.getDescription()%>"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="datagrid">
            <table border="0px" width="60%">
                <caption align="top">
                    <h2>Спортивные достижения</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="10%">Дата заполнения</th>
                        <th class="tblheader" >Описание</th>
                    </tr>
                </thead>
                <tbody>
                    <n:result_cycle css1="tbltd1" css2="tbltd2"
                                    columns="cdate;description"
                    result="<%=DAOImpl.getDisciplesSpsHistory(discipleId)%>">
                    </n:result_cycle>
                </tbody>
            </table>
        </div>
        <div class="datagrid">
            <table  border="0px" width="60%">
                <caption align="top">
                    <h2>История физических характеристик</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="10%">Дата заполнения</th>
                        <th class="tblheader" width="10%">Рост (см.)</th>
                        <th class="tblheader" width="10%">Вес (кг.)</th>
                        <th class="tblheader" >Описание</th>
                    </tr>
                </thead>
                <tbody>
                    <n:result_cycle css1="tbltd1" css2="tbltd2"
                                    columns="cdate;height;weight;description"
                                    get_pars_from_query="disciple_id"
                    result="<%=DAOImpl.getPhChsHist(discipleId)%>">
                    </n:result_cycle>
                </tbody>
            </table>
        </div>
        <div class="datagrid">
            <table border="0px" width="60%" >
                <caption align="top">
                    <h2>История общих спортивных показателей</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="10%">Дата заполнения</th>
                        <th class="tblheader" >Категория</th>
                        <th class="tblheader" width="10%">Значение</th>
                        <th class="tblheader" width="10%">мера</th>
                    </tr>
                </thead>
                <tbody>
                    <n:result_cycle css1="tbltd1" css2="tbltd2"
                                    columns="cdate;category_key_name;val;measure_key_name"
                    result="<%=DAOImpl.getDsptcsHistory(discipleId, 0)%>">
                    </n:result_cycle>
                </tbody>
            </table>
        </div>
        <div class="datagrid">
            <table border="0px"  width="60%" >
                <caption align="top">
                    <h2>История специализированных спортивных показателей</h2>
                </caption>
                <thead>
                    <tr>
                        <th class="tblheader" width="10%">Дата заполнения</th>
                        <th class="tblheader" >Категория</th>
                        <th class="tblheader" width="10%">Значение</th>
                        <th class="tblheader" width="10%">мера</th>
                    </tr>
                </thead>
                <tbody>
                    <n:result_cycle css1="tbltd1" css2="tbltd2"
                                    columns="cdate;category_key_name;val;measure_key_name"
                    result="<%=DAOImpl.getDsptcsHistory(discipleId, sdep.getId())%>">
                    </n:result_cycle>
                </tbody>
            </table>
        </div>
    </body>
</html>
