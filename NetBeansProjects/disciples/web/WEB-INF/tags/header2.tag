<%-- 
    Document   : header2
    Created on : 01.08.2010, 16:23:44
    Author     : dev_sport
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="caption"%>
<%@attribute name="url_icon"%>

<%-- any content can be specified here e.g.: --%>
<h2>${message}</h2>

<table border="0" width="100%">
    <tbody>
        <tr>
            <td width="10%" align="right"><img src="${url_icon}" width="32" height="32" alt="icon" border="0"/></td>
            <td><h1>${caption}</h1></td>
        </tr>
    </tbody>
</table>