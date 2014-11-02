<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <frameset border="1" rows="90px, *">
        <frame name="header_frame" src="Header.jsp"/>
        <frame id="center_frame" src="Search/Search.jsp" name="main" scrolling="auto" frameborder="0" marginwidth="20" marginheight="10"/>


        <!-- frameset id="frame2" cols="300px, *" frameborder="0" border="1">
            <frame id="left_frame" src="Left.jsp" name="index" scrolling="yes" frameborder="1" marginwidth="0" marginheight="0"/>
            <frame id="center_frame" src="SearchDocs.jsp" name="main" scrolling="auto" frameborder="0" marginwidth="20" marginheight="10"/>
        </frameset -->
    </frameset>
    <body></body>
</html>
