<%--
    Document   : header
    Created on : 27.11.2009, 13:13:56
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
   String contextPath = request.getContextPath();

%>

<table border="0" cellpadding="2" cellspacing="2" width="100%"  class="pcaption">
    <tbody>
        <tr>
            <td colspan="2">
                <span style="margin: 2px; padding: 2px;">
                    <img src="<%=contextPath%>/resources/images/icons/apps_16.png" width="16" height="16" alt="apps_16"/>
                    <b>Сервер: </b>IPSDocumentsRepository
                </span>
                <span style="border-left: groove teal 2px; margin: 2px; padding: 2px;">
                    <img src="<%=contextPath%>/resources/images/icons/user_16.png" width="16" height="16" alt="user_16"/>
                    <b> Пользователь: </b> <% out.print(request.getRemoteUser());%>
                </span>
            </td>
            <td align="right"></td>
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
                            <td><a href="Search.jsp" ><img src="<%=contextPath%>/resources/images/icons/srch_24.png" width="24" height="24" alt="srch_24" border="0"/>
                                    Поиск</a></td>
                            <!--td><a href="DataWork/DataWork.jsp" target="main" ><img src="resources/images/icons/hd_24.png" width="24" height="24" alt="srch_24" border="0"/>
                                     Работа с данными</a></td -->
                            <td><a href="addPerson.do" ><img src="<%=contextPath%>/resources/images/icons/hd_24.png" width="24" height="24" alt="srch_24" border="0"/>
                                    Добавить персону</a></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td align="right"><a href="CloseSession" target="_parent">Выход
                                    <img src="<%=contextPath%>/resources/images/icons/close_24.png" width="24" height="24" alt="close_24" border="0" />
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </tbody>
</table>

