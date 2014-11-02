<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/stylesheet.css" type="text/css" />

        <style type="text/css">
        </style>

    </head>
    <body class="pcaption">
        <table border="0" cellpadding="2" cellspacing="2" width="100%">
            <tbody>
                <tr>
                    <td colspan="2">
                        <span style="margin: 2px; padding: 2px;">
                            <img src="resources/images/icons/apps_16.png" width="16" height="16" alt="apps_16"/>
                            <b>Сервер: </b>IPSDocumentsRepository
                        </span>
                        <span style="border-left: groove teal 2px; margin: 2px; padding: 2px;">
                            <img src="resources/images/icons/user_16.png" width="16" height="16" alt="user_16"/>
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
                                    <td><a href="Search/Search.jsp" target="main" ><img src="resources/images/icons/srch_24.png" width="24" height="24" alt="srch_24" border="0"/>
                                             Поиск</a></td>
                                    <!--td><a href="DataWork/DataWork.jsp" target="main" ><img src="resources/images/icons/hd_24.png" width="24" height="24" alt="srch_24" border="0"/>
                                             Работа с данными</a></td -->
                                    <td><a href="DataWork/DataWork2.jsp" target="main" ><img src="resources/images/icons/hd_24.png" width="24" height="24" alt="srch_24" border="0"/>
                                             Работа с данными</a></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td align="right"><a href="CloseSession" target="_parent">Выход
                                            <img src="resources/images/icons/close_24.png" width="24" height="24" alt="close_24" border="0" />
                                            </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
