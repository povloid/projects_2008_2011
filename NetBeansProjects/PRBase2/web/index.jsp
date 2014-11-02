<%-- 
    Document   : index
    Created on : 09.02.2010, 14:42:40
    Author     : kopychenko
--%>

<jsp:useBean class="ips.prbase.session.User" id="user" scope="session" />
<%
    if(user.getId() == 0){
        user.init(request.getRemoteUser());
        //System.out.println(">>>>>>>>>>>>>>>>>>" + user.getUserName());
    }
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
    <head>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=prbase/psearch.action">
    </head>
    <body>
        <p>Loading ...</p>
    </body>
</html>