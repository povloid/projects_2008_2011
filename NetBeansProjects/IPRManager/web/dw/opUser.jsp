<%-- 
    Document   : opUser
    Created on : 27.04.2010, 11:39:53
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />



        <s:if test="user.id == 0">
            <title>Добавить пользователя</title>
        </s:if>
        <s:elseif test="user.id > 0 && !deleteF">
            <title>Редактирование пользователя</title>
        </s:elseif>
        <s:elseif test="deleteF">
            <title>Удаление пользователя</title>
        </s:elseif>


        <s:head/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>

        <table border="0" width="100%">
            <tbody>
                <tr>
                    <s:if test="user.id == 0">
                        <td width="10%" align="right"><img src="../resources/images/icons/add_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                        <td><h1>Добавить пользователя</h1></td>
                    </s:if>
                    <s:elseif test="user.id > 0 && !deleteF">
                        <td width="10%" align="right"><img src="../resources/images/icons/edit_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                        <td><h1>Редактирование пользователя</h1></td>
                    </s:elseif>
                    <s:elseif test="deleteF">
                        <td width="10%" align="right"><img src="../resources/images/icons/remov_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                        <td><h1>Удаление пользователя</h1></td>
                    </s:elseif>
                </tr>
            </tbody>
        </table>

        <s:form name="editForm" action="opUser" method="POST">
            <s:hidden name="user.id"/>
            <s:hidden name="deleteF"/>

            <s:textfield label="Пользователь" name="user.userName" size="100"  readonly="deleteF"/>
            <s:textarea label="Описание" name="user.description" cols="100"  readonly="deleteF"/>
            
            <s:checkbox label="Заблокирован" name="user.blocked" readonly="deleteF"/>

            <s:if test="!deleteF">

                <s:label/>

                <s:password label="Пароль" name="user.password" showPassword="true"  readonly="deleteF"/>
                <s:password label="Подтверждение пароля" name="user.password2" showPassword="true"  readonly="deleteF"/>
                <s:label/>


                <s:textfield label="Уровень доступа" name="user.accessLevel"
                             value="%{getText('{0,number,#0}',{user.accessLevel})}"  readonly="deleteF"/>

                <s:select tooltip="Отдел"
                          label="Отдел"
                          name="user.depId"
                          list="user.depsList"
                          listKey="id"
                          listValue="keyName"  readonly="deleteF"/>


                <s:optiontransferselect
                    tooltip="Членство в группах"
                    label="Членство в группах"

                    leftTitle="Входит в группы"
                    rightTitle="Группы"

                    name="user.userRoles.haveRoles2"
                    list="user.userRoles.haveRoles"
                    listKey="id"
                    listValue="keyName"


                    headerKey="0"
                    headerValue="--- Please select group ---"


                    doubleName="user.userRoles.otherRoles2"
                    doubleList="user.userRoles.otherRoles"
                    doubleListKey="id"
                    doubleListValue="keyName"


                    doubleHeaderKey="0"
                    doubleHeaderValue="--- Please select group ---"

                    />
            </s:if>



            <s:submit value="Применить"/>
        </s:form>


    </body>
</html>
