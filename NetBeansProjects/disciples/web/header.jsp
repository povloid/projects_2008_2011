<%--
    Document   : header
    Created on : 27.11.2009, 13:13:56
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="n" %>

<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>


<jsp:useBean class="disciples.web.session.User" id="user" scope="session" />
<%
    if (user.getId() == 0) {
        user.init(request.getRemoteUser());
    }
%>

<%
    String contextPath = request.getContextPath();
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

%>



<link type="text/css" href="<%=contextPath%>/resources/menu.css" rel="stylesheet" />
<script type="text/javascript" src="<%=contextPath%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/menu.js"></script>








<s:url id="main" action="main" namespace="prbase">
    <s:param name="op">0</s:param>
</s:url>
<s:url id="listperson" action="listperson" namespace="prbase">
    <s:param name="op">0</s:param>
</s:url>
<table border="0" cellpadding="2" cellspacing="2" width="100%"  class="pcaption">
    <tbody>
        <tr>
            <td colspan="2">
                <span style="margin: 2px; padding: 2px;">
                    <img src="<%=contextPath%>/resources/images/icons/apps_16.png" width="16" height="16" alt="apps_16"/>
                    <b>Сервер: </b>disciples
                </span>
                <span style="border-left: groove teal 2px; margin: 2px; padding: 2px;">
                    <img src="<%=contextPath%>/resources/images/icons/user_16.png" width="16" height="16" alt="user_16"/>
                    <b> Пользователь: </b> <%=user.getUserName()%>
                </span>
            </td>
            <td align="right"><b>Время:</b> <%=(df.format(new Date()))%></td>
        </tr>
        <!-- tr>
                        <td colspan="3" align="center">
                        <h3 class="pcaptioncaption">IPS Document Repository</h3>
                        </td>
                </tr -->
        <tr>
            <td colspan="3">

                <table border="0" align="center" width="100%">
                    <tbody>
                        <tr>
                            <td>
                                <!--                                <ul class="ulTab">
                                                                    <li>
                                                                        <a href="<%=contextPath%>/disciples/disciples.action" ><img src="<%=contextPath%>/resources/images/icons/user_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                            Ученики</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="<%=contextPath%>/disciples/sdeps.action" ><img src="<%=contextPath%>/resources/images/icons/user_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                            Спортивные отделения</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="<%=contextPath%>/disciples/sclasses.action" ><img src="<%=contextPath%>/resources/images/icons/user_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                            Спортивные классы</a>
                                                                    </li>

                                                                </ul>-->
                                <!--                                <ul class="sf-menu">
                                                                    <li class="current">
                                                                        <a href="#w" target="_parent">
                                                                            <img src="<%=contextPath%>/resources/images/icons/apps_24.png" width="24" height="24" alt="close_24" border="0" />
                                                                            Работа
                                
                                                                        </a>
                                                                        <ul>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/ph_chs.action" ><img src="<%=contextPath%>/resources/images/icons/apps_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Физические характеристики</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/dsptcs.action" ><img src="<%=contextPath%>/resources/images/icons/apps_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Физподготовка</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/sps.action" ><img src="<%=contextPath%>/resources/images/icons/apps_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Спортивные достижения</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/avscores.action" ><img src="<%=contextPath%>/resources/images/icons/apps_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Средние балы</a>
                                                                            </li>
                                                                        </ul>
                                                                    </li>
                                                                    <li class="current">
                                                                        <a href="#a"><img src="<%=contextPath%>/resources/images/icons/addbk_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                            Справочники...</a>
                                                                        <ul>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/disciples.action" ><img src="<%=contextPath%>/resources/images/icons/addbk_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Ученики</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/sdeps.action" ><img src="<%=contextPath%>/resources/images/icons/addbk_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Спортивные отделения</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/sclasses.action" ><img src="<%=contextPath%>/resources/images/icons/addbk_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Спортивные классы</a>
                                                                            </li>
                                                                                                                        <li>
                                                                                                                            <a href="<%=contextPath%>/disciples/sp_trangs.action" ><img src="<%=contextPath%>/resources/images/icons/user_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                                                                Спортивные подготовки</a>
                                                                                                                        </li>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/categories.action" ><img src="<%=contextPath%>/resources/images/icons/addbk_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Спортивные категории</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/measures.action" ><img src="<%=contextPath%>/resources/images/icons/addbk_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Меры измерения</a>
                                                                            </li>
                                                                        </ul>
                                                                    </li>
                                                                    <li>
                                                                        <a href="" target="_parent">
                                                                            <img src="<%=contextPath%>/resources/images/icons/docs_24.png" width="24" height="24" alt="close_24" border="0" />
                                                                            Отчеты
                                                                        </a>
                                                                        <ul>
                                                                            <li>
                                                                                <a href="<%=contextPath%>/disciples/rep_disciples.action" ><img src="<%=contextPath%>/resources/images/icons/docs_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                                    Сведения по ученикам</a>
                                                                            </li>
                                                                        </ul>
                                                                    </li>
                                                                </ul>-->


                                <div id="menu">
                                    <ul class="menu">
                                        <li><a href="#" class="parent"><span>Журнал</span></a>
                                            <div >
                                                <ul>
                                                    <li>
                                                        <a href="<%=contextPath%>/disciples/ph_chs.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/about_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                Физические характеристики
                                                            </span>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="<%=contextPath%>/disciples/dsptcs.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/games_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                Физподготовка
                                                            </span>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="<%=contextPath%>/disciples/sps.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/flag_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                Спортивные достижения
                                                            </span>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="<%=contextPath%>/disciples/avscores.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/go_24.png" width="24" height="24" alt="user_24" border="0"/>
                                                                Средние балы
                                                            </span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li><a href="#" class="parent"><span>Справочники</span></a>
                                            <div class="columns two">
                                                <ul class="one">
                                                    <li>

                                                        <a href="<%=contextPath%>/disciples/disciples.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/addbk_16.png" width="16" height="16" alt="user_16" border="0"/>
                                                                Ученики
                                                            </span>
                                                        </a>
                                                    </li>
                                                    <li>

                                                        <a href="<%=contextPath%>/disciples/sdeps.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/addbk_16.png" width="16" height="16" alt="user_16" border="0"/>
                                                                Спортивные отделения
                                                            </span>
                                                        </a>

                                                    </li>
                                                    <li>
                                                        <a href="<%=contextPath%>/disciples/sclasses.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/addbk_16.png" width="16" height="16" alt="user_16" border="0"/>
                                                                Спортивные классы
                                                            </span>
                                                        </a>
                                                    </li>
                                                </ul>
                                                <ul class="two">
                                                    <li>

                                                        <a href="<%=contextPath%>/disciples/categories.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/addbk_16.png" width="16" height="16" alt="user_16" border="0"/>
                                                                Спортивные категории
                                                            </span>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="<%=contextPath%>/disciples/measures.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/addbk_16.png" width="16" height="16" alt="user_16" border="0"/>
                                                                Меры измерения
                                                            </span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        
                                        <li><a href="#" class="parent"><span>Отчеты</span></a>
                                            <div >
                                                <ul>
                                                    <li>
                                                        <a href="<%=contextPath%>/disciples/rep_disciples.action" >
                                                            <span>
                                                                <img src="<%=contextPath%>/resources/images/icons/docs_16.png" width="16" height="17" alt="docs_16" border="0"/>
                                                                Сведения по ученикам
                                                            </span>
                                                        </a>
                                                    </li>
                                                    </ul>
                                            </div>
                                        </li>

                                        <li class="last">

                                            <a href="close_session.action" target="_parent">
                                                <span>
                                                    <img src="<%=contextPath%>/resources/images/icons/close_16.png" width="16" height="16" alt="close" border="0" />
                                                    Выход
                                                </span>

                                            </a>
                                        </li>
                                    </ul>
                                </div>


                            </td>
                            <!--                            <td>
                                                        </td>
                                                        <td align="right">-->
                            <!--                                <ul class="ulTab">
                                                                <li>
                                                                    <a href="close_session.action" target="_parent">Выход
                                                                        <img src="<%=contextPath%>/resources/images/icons/close_24.png" width="24" height="24" alt="close_24" border="0" />
                                                                    </a>
                                                                </li>
                                                            </ul>-->

                            <!--                            </td>-->
                            <td align="right">
                                <!--                                <table border="0">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>
                                                                                <ul class="sf-menu">
                                                                                    <li>
                                                                                        <a href="close_session.action" target="_parent">Выход
                                                                                            <img src="<%=contextPath%>/resources/images/icons/close_24.png" width="24" height="24" alt="close_24" border="0" />
                                                                                        </a>
                                                                                    </li>
                                                                                </ul>
                                                                            </td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>-->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </tbody>
</table>









