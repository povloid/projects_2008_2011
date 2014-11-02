<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>


<sj:head jqueryui="true" jquerytheme="redmond" locale="ru"/>

<html>
    <head>
        <title><s:text name="HelloWorld.message"/></title>
    </head>

    <body>
        <h2><s:property value="message"/></h2>

        <h3>Languages</h3>
        <ul>
            <li>
                <s:url id="url" action="HelloWorld">
                    <s:param name="request_locale">en</s:param>
                </s:url>
                <s:a href="%{url}">English</s:a>
            </li>

            <li>
                <s:url id="url" action="HelloWorld">
                    <s:param name="request_locale">es</s:param>
                </s:url>

                <s:a href="%{url}">Espanol</s:a>

            </li>
        </ul>


        <s:url id="remoteurl" action="jsontable"/>
        <sjg:grid
            id="gridtable"
            caption="Customer Examples"
            dataType="json"
            href="%{remoteurl}"
            pager="true"
            gridModel="gridModel"
            rowList="10,15,20"
            rowNum="15"
            rownumbers="true"
            >
            <sjg:gridColumn name="id" index="id" title="ID" formatter="integer" sortable="false"/>
            <sjg:gridColumn name="name" index="name" title="Name" sortable="true"/>
            <sjg:gridColumn name="country" index="country" title="Country" sortable="false"/>
            <sjg:gridColumn name="city" index="city" title="City" sortable="false"/>
            <sjg:gridColumn name="creditLimit" index="creditLimit" title="Credit Limit" formatter="currency" sortable="false" align="right"/>
        </sjg:grid>
    </body>
</html>

