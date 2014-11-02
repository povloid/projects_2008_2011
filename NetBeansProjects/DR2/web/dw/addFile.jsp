<%-- 
    Document   : addFile
    Created on : 18.11.2009, 10:37:26
    Author     : kopychenko
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить фаил</title>
    </head>
    <body>
        <h1>Добавить фаил</h1>
        <html:form action="/AddFile" enctype="multipart/form-data">

            <html:file property="myFile" />
            <html:submit value="Принять" />

        </html:form>
    </body>
</html:html>
