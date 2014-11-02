<%-- 
    Document   : rb_access_levels_select
    Created on : 05.09.2009, 17:50:49
    Author     : kopychenko
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ tag import="ips.dwh.datawork.*"%>
<%@ tag import="java.sql.ResultSet" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="selected_id" required="TRUE"%>

<%-- any content can be specified here e.g.: --%>
<select NAME="ACCESS_LEVEL" style="width: 100%">

    <%
            int selectedId = Integer.parseInt(selected_id);

            DataWork.Result result = DataWork.getRbAccessLevels();
            ResultSet rs = result.getRs();

            while (rs.next()) {

                int id = rs.getInt("ID");
                String keyName = rs.getString("KEY_NAME");


    %>

    <OPTION value="<% out.print(id); %>" <% out.print((selectedId == id ? "selected" : "")); %> > <% out.print(keyName); %> </OPTION>

    <%


}


result.closeAll();

    %>

</select>


