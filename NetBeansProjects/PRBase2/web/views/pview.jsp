<%-- 
    Document   : pview
    Created on : 03.03.2010, 9:33:17
    Author     : kopychenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="ips.prbase.dao.*"%>
<%@ page import="ips.prbase.dao.DAOImpl"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>


<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="../resources/stylesheet.css" type="text/css" />

        <title>Просмотр персоны</title>
        <sx:head/>
    </head>

    <s:url id="R1" action="R1" namespace="prbase">
        <s:param name="id"><%=request.getParameter("id")%></s:param>
    </s:url>
    <table border="0">
        <tbody>
            <tr>
                <td><h3>Выгрузить в формате:</h3></td>
                <td><s:a href="%{R1}"><img src="../resources/images/pdf.png" width="32" height="32" alt="PDF" border="0"/>PDF</s:a></td>
            </tr>
        </tbody>
    </table>
    <body onLoad="self.focus();document.editForm.opperson_person_fio_text.focus()">
        <!-- jsp:include page="/header.jsp"/-->
        <hr class="hr0">
        <s:form name="editForm" action="opperson" method="POST" enctype="multipart/form-data">
            <s:hidden name="person.id"/>
            <table border="0" width="100%">
                <thead>
                    <tr>
                        <th width="20%">
                            <img src="../resources/images/icons/ulock_32.png" width="32" height="32" alt="ulock_32"/>
                        </th>
                        <th align="left"><h1>Открытый блок</h1></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="tblheader2" valign="top">
                            Фото:
                        </td>
                        <td>
                            <img src='prbase/getPersonFoto.action?id=<s:text name="person.foto.imageId"/>' alt="ulock_32"/>
                            <!-- img src='prbase/getPersonFoto.action?id=21' alt="ulock_32"/ -->
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Ф.И.О.
                        </td>
                        <td>
                            <s:text name="person.fio.text" />
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Пол
                        </td>
                        <td>
                            <s:text name="person.gender.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Телефоны
                        </td>
                        <td>
                            <s:text name="person.phones.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Занимаемая должность
                        </td>
                        <td>
                            <s:text name="person.post.text" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Родился
                        </td>
                        <td>
                            <s:text name="person.birthday.date"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Место рождения
                        </td>
                        <td>
                            <s:text name="person.birthPlace.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Место жительства
                        </td>
                        <td>
                            <s:text name="person.abidingPlace.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Семейное положение
                        </td>
                        <td>
                            <s:text name="person.maritalStatus.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Имеет детей
                        </td>
                        <td>
                            <s:text name="person.hasChildren.int"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Автобиография
                        </td>
                        <td valign="top">
                            <s:text  name="person.officialAutobiography.text" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Образование
                        </td>
                        <td>
                            <s:text name="person.education.keyDescription" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Примечания по образованию
                        </td>
                        <td>
                            <s:text name="person.notesOnEducation.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Ученая степень
                        </td>
                        <td>
                            <s:text name="person.degree.keyDescription"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Примечания по ученой степени
                        </td>
                        <td valign="top">
                            <s:text name="person.NotesOnTheDegree.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Сфера профессиональных интересов
                        </td>
                        <td valign="top">
                            <s:text  name="person.scopeOfProfessionalInterests.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Декларируемые политические (экономические и т.д.) взгляды, принципы, идеи
                        </td>
                        <td valign="top">
                            <s:text name="person.politicalViews.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            Степень публичности (участие в различных публичных мероприятиях, интервью, публикации и пр.)
                        </td>
                        <td valign="top">
                            <s:text name="person.theDegreeOfPublicity.text"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <hr class="hr0">
            <table border="0" width="100%">
                <thead>
                    <tr>
                        <th width="20%">
                            <img src="../resources/images/icons/lock_32.png" width="32" height="32" alt="ulock_32"/>
                        </th>
                        <th align="left"><h1>Закрытый блок</h1></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="2">
                            <h2>7. Личностная характеристика</h2>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            7.1. Истинные мотивы политической и общественной и прочей профессиональной активности
                        </td>
                        <td valign="top">
                            <s:text  name="person.theTrueMotivation.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            7.2. Личные качества, сильные и слабые стороны
                        </td>
                        <td valign="top">
                            <s:text  name="person.personality.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            7.3. Увлечения, хобби, склонности
                        </td>
                        <td valign="top">
                            <s:text  name="person.hobbies.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            7.4. Наличие КМ (судимости, административные правонарушения и т.д.)
                        </td>
                        <td valign="top">
                            <s:text  name="person.thePresenceOfConflictPointss.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <h2>8. Рычаги влияния</h2>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            8.1. Степень личного влияния, наличие личных и деловых связей, степень зависимости.
                        </td>
                        <td valign="top">
                            <s:text  name="person.theDegreeOfPersonalInfluence.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            8.2. Наличие влиятельных лиц в ОПО, окружении, среди родственников и т.д.
                        </td>
                        <td valign="top">
                            <s:text  name="person.thePresenceOfInfluentialPeople.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            8.3. Конфликты, идейные противоречия
                        </td>
                        <td valign="top">
                            <s:text  name="person.ideologicalContradictions.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            8.4. Перспективы профессионального продвижения
                        </td>
                        <td valign="top">
                            <s:text  name="person.prospectsForProfessionalAdvancement.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <h2>9. Контакты</h2>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2" >
                            9.1. Повседневные контакты, встречи
                        </td>
                        <td valign="top">
                            <s:text  name="person.theDailyContactsMeetings.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            9.2. Наличие источников информации в государственных и иных организациях (принцип подбора, мотивы)
                        </td>
                        <td valign="top">
                            <s:text  name="person.availableSourcesOfInformation.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            9.3. Наличие контактов с представителями партий, ОО, движений и др.
                        </td>
                        <td valign="top">
                            <s:text  name="person.contactsWithParties.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <h2>10. Финансирование</h2>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            10.1. Основные источники финансирования политической и прочей деятельности
                        </td>
                        <td valign="top">
                            <s:text  name="person.mainSourcesOfFunding.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            10.2. Наличие иностранных источников финансирования
                        </td>
                        <td valign="top">
                            <s:text  name="person.ForeignSourcesOfFunding.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr2">
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            10.3. Наличие дополнительных источников доходов, наличие долгов и других материальных затруднений.
                        </td>
                        <td valign="top">
                            <s:text  name="person.additionalSourcesOfFunding.text"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <h2>11. Пресподборка</h2>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr class="hr1">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <h2>12 Дополнительно</h2>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblheader2">
                            12.1. Награды и почетные звания
                        </td>
                        <td valign="top">
                            <s:text  name="person.honors.text"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </s:form>
    </body>
</html>
