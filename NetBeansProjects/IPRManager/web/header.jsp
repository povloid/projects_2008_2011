<%--
    Document   : header
    Created on : 27.11.2009, 13:13:56
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>


<jsp:useBean class="ips.iprmanager.session.User" id="user" scope="session" />
<%
    if(user.getId() == 0){
        user.init(request.getRemoteUser());
    }
%>

<%
            String contextPath = request.getContextPath();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

%>



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
                    <b>Сервер: </b>IPRManager
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

                <table border="0" width="100%">
                    <tbody>
                        <tr>
                            <td>
                                <ul class="ulTab">
                                    <li>
                                        <a href="<%=contextPath%>/manager/users.action" ><img src="<%=contextPath%>/resources/images/icons/user_24.png" width="24" height="24" alt="user_24" border="0"/>
                                            Пользователи</a>
                                    </li>
                                    <li>
                                        <a href="<%=contextPath%>/manager/roles.action" ><img src="<%=contextPath%>/resources/images/icons/group_24.png" width="24" height="24" alt="group_24" border="0"/>
                                            Роли</a>
                                    </li>
                                </ul>
                            </td>
                            <td width="50%">
                            </td>
                            <td align="right">
                                <ul class="ulTab">
                                    <li>
                                        <a href="close_session.action" target="_parent">Выход
                                            <img src="<%=contextPath%>/resources/images/icons/close_24.png" width="24" height="24" alt="close_24" border="0" />
                                        </a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </tbody>
</table>

