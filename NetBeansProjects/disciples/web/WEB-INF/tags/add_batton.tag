<%-- 
    Document   : add_batton
    Created on : 01.08.2010, 16:20:08
    Author     : dev_sport
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="caption"%>
<%@attribute name="url_add"%>

<%-- any content can be specified here e.g.: --%>
<table border="0" >
    <thead>
        <tr>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><form name="ADD_FORM" action="${url_add}" method="POST">
                    <input class="aButton"  type="submit" value="${caption}"/>
                </form>
            </td>
        </tr>
    </tbody>
</table>