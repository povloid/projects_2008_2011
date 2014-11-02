<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>

<%
	String parent = request.getParameter("parent");
	if (parent == null) {
		parent = "root";
	}

	String op = request.getParameter("op");
	System.out.println("Выбрана операция: "+op);

	if (op == null) {
		op = "update";
	}

	String url = "DataWorkCenter.jsp";

	if (op.equals("update")) {
		url = "DataWorkCenter.jsp";
	} else if( op.equals("add_group")){
		url = "Operations/AddEditGroup.jsp";
	}
	
	else if(op.equals("add_box")){
		url = "Operations/AddEditBox.jsp";
	}
	
	url = url + "?parent=" + parent + "&op=" + op;
	
%>


<frameset id="datawork" rows="64px,*" frameborder="yes" border="1">
	<frame id="dwtop" src="DataWorkTop.jsp?parent=<%=parent%>" name="dwtop" scrolling="no"
		frameborder="1" marginwidth="0" marginheight="0" />
	<frame id="dwcenter" src="<%=url%>" name="dwcenter"
		scrolling="auto" frameborder="1" marginwidth="20" marginheight="10" />
</frameset>

<body />
</html>
