<%-- 
    Document   : opperson
    Created on : 11.02.2010, 11:44:56
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<jsp:useBean id="currUser" scope="session" class="ips.prbase.session.User" />

<%
            currUser.init(request.getRemoteUser());
%>

<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/stylesheet.css"
              type="text/css" />

        <title>Редактирование персоны</title>
    </head>
    <sx:head/>
</head>
<body onLoad="self.focus();document.editForm.opperson_person_fio_text.focus()">
    <jsp:include page="/header.jsp"/>

    <table border="0" width="100%">
        <tbody>
            <tr>
                <td width="10%" align="right"><img src="../resources/images/icons/edit_32.png" width="32" height="32" alt="srch_24" border="0"/></td>
                <td><h1>Редактирование персоны</h1></td>
            </tr>
        </tbody>
    </table>
    <s:form name="editForm" action="opperson" method="POST" enctype="multipart/form-data">
        <s:hidden name="person.id"/>
        <table border="0" width="100%">
            <thead>
                <tr>
                    <th width="20%">
                        <img src="../resources/images/icons/ulock_32.png" width="32" height="32" alt="ulock_32"/>
                    </th>
                    <th align="left"><h2>Открытый блок</h2> <s:submit value="Применить"/> </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="label" align="right" valign="top">
                        Фото:
                    </td>
                    <td>
                        <img src='prbase/getPersonFoto.action?id=<s:text name="person.foto.imageId"/>' alt="foto"/>
                        <!-- img src='prbase/getPersonFoto.action?id=21' alt="ulock_32"/ -->
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:file tooltip="Установить фото" label="Указать фаил фотографии" name="person.foto.file" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:textfield label="Ф.И.О." name="person.fio.text" size="100"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:radio label="Пол" list="person.genders" name="person.gender.text"/>
                    </td>
                </tr>
                <tr>

                    <td>
                        <s:textarea label="Телефоны" name="person.phones.text" cols="50" rows="5"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:textarea label="Занимаемая должность" name="person.post.text" cols="80" rows="5"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>

                    <td>
                        <sx:datetimepicker tooltip="Пример 20.02.2002"
                            label="Родился" name="person.birthday.date" displayFormat="dd.MM.yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:textarea label="Место рождения" name="person.birthPlace.text" cols="80" rows="5"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:textarea label="Место Жительства" name="person.abidingPlace.text" cols="80" rows="5"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:radio label="Семейное положение" list="person.maritalStatuses" name="person.maritalStatus.text"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:textfield label="Имеет детей" name="person.hasChildren.int"
                                     value="%{getText('{0,number,#0}',{person.hasChildren.int})}" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="Официальная автобиография"
                                    name="person.officialAutobiography.text" cols="100" rows="10"
                                    cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:select
                            tooltip="Здесь необходимо указать образование"
                            label="Образование"
                            list="person.education.list"
                            name="person.education.key"
                            listKey="key"
                            listValue="description"
                            />
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:textarea label="Примечания по образованию"
                                    name="person.notesOnEducation.text" cols="100" rows="10"
                                    cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:select
                            tooltip="Необходимо указать ученую степень"
                            label="Ученая стапень"
                            list="person.degree.list"
                            name="person.degree.key"
                            listKey="key"
                            listValue="description"
                            />
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="Примечания по ученой степени"
                                    name="person.NotesOnTheDegree.text" cols="100" rows="10"
                                    cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea  label="Сфера профессиональных интересов"
                                     name="person.scopeOfProfessionalInterests.text"
                                     cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="Декларируемые политические (экономические и т.д.) взгляды, принципы, идеи"
                                    name="person.politicalViews.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="Степень публичности (участие в различных публичных мероприятиях, интервью, публикации и пр.)"
                                    name="person.theDegreeOfPublicity.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
            </tbody>
        </table>
        <s:submit value="Применить"/>
        <table border="0" width="100%">
            <thead>
                <tr>
                    <th width="20%">
                        <img src="../resources/images/icons/lock_32.png" width="32" height="32" alt="ulock_32"/>
                    </th>
                    <th align="left"><h2>Закрытый блок</h2></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="2">
                        <h2>7. Личностная характеристика</h2>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="7.1. Истинные мотивы политической и общественной и прочей профессиональной активности"
                                    name="person.theTrueMotivation.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea  label="7.2. Личные качества, сильные и слабые стороны"
                                     name="person.personality.text"
                                     cols="100" rows="10"  cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea  label="7.3. Увлечения, хобби, склонности"
                                     name="person.hobbies.text"
                                     cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="7.4. Наличие КМ (судимости, административные правонарушения и т.д.)"
                                    name="person.thePresenceOfConflictPointss.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h2>8. Рычаги влияния</h2>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="8.1. Степень личного влияния, наличие личных и деловых связей, степень зависимости."
                                    name="person.theDegreeOfPersonalInfluence.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="8.2. Наличие влиятельных лиц в ОПО, окружении, среди родственников и т.д."
                                    name="person.thePresenceOfInfluentialPeople.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="8.3. Конфликты, идейные противоречия"
                                    name="person.ideologicalContradictions.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="8.4. Перспективы профессионального продвижения"
                                    name="person.prospectsForProfessionalAdvancement.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h2>9. Контакты</h2>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="9.1. Повседневные контакты, встречи"
                                    name="person.theDailyContactsMeetings.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea  label="9.2. Наличие источников информации в государственных и иных организациях (принцип подбора, мотивы)"
                                     name="person.availableSourcesOfInformation.text"
                                     cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea label="9.3. Наличие контактов с представителями партий, ОО, движений и др."
                                    name="person.contactsWithParties.text"
                                    cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h2>10. Финансирование</h2>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea  label="10.1. Основные источники финансирования политической и прочей деятельности"
                                     name="person.mainSourcesOfFunding.text"
                                     cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea  label="10.2. Наличие иностранных источников финансирования"
                                     name="person.ForeignSourcesOfFunding.text"
                                     cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea  label="10.3. Наличие дополнительных источников доходов, наличие долгов и других материальных затруднений."
                                     name="person.additionalSourcesOfFunding.text"
                                     cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h2>11. Пресподборка</h2>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h2>12 Дополнительно</h2>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <s:textarea  label="12.1. Награды и почетные звания"
                                     name="person.honors.text"
                                     cols="100" rows="10" cssStyle="border: 2px;border-style: inset;"/>
                    </td>
                </tr>
            </tbody>
        </table>
        <s:submit value="Применить"/>
    </s:form>
</body>
</html>
