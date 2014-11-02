<%-- 
    Document   : ExampleForm2
    Created on : 13.04.2011, 10:19:18
    Author     : traveler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- -->
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!-- -->
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<sj:head jqueryui="true" jquerytheme="redmond" locale="ru"/>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ExampleForm2</title>
    </head>
    <body>
        <h1>ExampleForm2</h1>
        <s:form name="ExampleForm2" action="ExampleForm2" method="POST">

            <s:textarea label="Строка" name="str" cols="100"/>

            <sj:datepicker name="date" appendText=" (dd.MM.yy)" label="Сегодня" />


            <sj:submit value="Применить"  button="true"/>
        </s:form>



        <s:url id="remoteurl3" action="table3"/>
        <sjg:grid
            id="gridtable3"
            caption="Customer Examples"
            dataType="json"
            href="%{remoteurl3}"
            pager="true"
            gridModel="gridModel"
            rowList="10,15,20"
            rowNum="15"
            rownumbers="true"

            navigator="true"
            navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
            navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
            navigatorEditOptions="{height:280,reloadAfterSubmit:false}"
            navigatorEdit="true"
            navigatorView="true"
            navigatorDelete="true"
            navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
            
            onSelectRowTopics="rowselect"
            
            >


            <sjg:gridColumn name="id" index="id" title="ID" formatter="integer" sortable="false"/>
            <sjg:gridColumn name="name" index="name" title="Name" sortable="true"/>
            <sjg:gridColumn name="country" index="country" title="Country" sortable="false"/>
            <sjg:gridColumn name="city" index="city" title="City" sortable="false"/>
            <sjg:gridColumn name="creditLimit" index="creditLimit" title="Credit Limit" formatter="currency" sortable="false" align="right"/>
        </sjg:grid>


        <br>
        <sj:submit id="grid_multi_getselectedbutton" value="Get Selected Rows" onClickTopics="getselectedids" button="true"/>

        <script  type='text/javascript'>
            $.subscribe('getselectedids', function(event,data) {
                //var s,s2;
                //s = $("#gridtable3").jqGrid('getGridParam','selarrrow');
                //s2 = $("#gridtable3").getRowData('9');
                //alert('Selected Rows : '+s2);
            
            
                var gsr = jQuery("#gridtable3").jqGrid('getGridParam','selrow');
                var data = jQuery("#gridtable3").jqGrid('getRowData',gsr);

                alert(data.name);
                location.href='?id=12';
            });
        </script>


    </body>
</html>
