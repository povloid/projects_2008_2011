<%-- 
    Document   : rb_access_levels_select
    Created on : 05.09.2009, 17:50:49
    Author     : kopychenko
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ tag import="ips.dwh.datawork.*"%>
<%@ tag import="java.sql.ResultSet" %>
<%@ tag import="ips.dwh.rb.RbAccessLevels" %>
<%@ tag import="java.util.Collection" %>
<%@ tag import="java.util.ArrayList" %>


<%
            DAOImpl.Result result = DAOImpl.getRbAccessLevels();

            ResultSet rs = result.getRs();

            Collection accesLevels = new ArrayList();

            while (rs.next()) {
                accesLevels.add(new RbAccessLevels(
                        rs.getInt("id"),
                        rs.getInt("access_level"),
                        rs.getString("key_name"),
                        rs.getString("description")));
            }

            request.setAttribute("accessLevels", accesLevels);

            result.closeAll();
%>




