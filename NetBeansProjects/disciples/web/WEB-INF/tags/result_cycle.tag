<%-- 
    Document   : result_cycle
    Created on : 01.08.2010, 13:52:07
    Author     : dev_sport
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ tag import="disciples.dao.DAOImpl"%>
<%@ tag import="kpg.db.sql.ADBBase.Result"%>
<%@ tag import="java.sql.ResultSet"%>
<%@ tag import="java.util.Date"%>
<%@ tag import="java.util.logging.Logger"%>
<%@ tag import="java.util.logging.Level"%>
<%@ tag import="java.text.SimpleDateFormat"%>
<%@ tag body-content="scriptless" %>



<%@attribute name="css1"%>
<%@attribute name="css2"%>
<%@attribute name="columns"%>
<%@attribute name="url_add"%>
<%@attribute name="url_edit"%>
<%@attribute name="url_del"%>
<%@attribute name="url_rep"%>
<%@attribute name="get_pars"%>
<%@attribute name="get_pars_from_query"%>

<%@attribute name="result" type="java.lang.Object" rtexprvalue="true" required="true"%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>

<%
            System.out.println("Tag: result_cycle.tag --------------------------");

            DAOImpl.Result resultT = null;

            try {

                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

                resultT = (DAOImpl.Result) result;
                ResultSet rs = resultT.getRs();

                int id = 0;
                String rowCss = null;
                boolean cssT = false;

                String columns2[] = columns.split(";");

                System.out.println("Колонок результата: " + rs.getMetaData().getColumnCount());

                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    System.out.println("\tКолонока: " + rs.getMetaData().getColumnName(i));
                }

                while (rs.next()) {
                    id = rs.getInt("ID");

                    //rowCss = cssT ? css1 : css2;
                    
                    rowCss = cssT ? "alt" : "";
                    
                    cssT = !cssT;


%>
<tr class="<%=rowCss%>">
    <%
                        for (String col : columns2) {

                            String align = "left";

                            Object v = rs.getObject(col);


                            String sv = v != null ? v.toString() : "";

                            if (v instanceof Number) {
                                align = "right";


                                //System.out.println(v.getClass());


                            } else if (v instanceof Boolean) {
                                align = "center";
                                sv = ((Boolean) v) ? "да" : "нет";
                            } else if (v instanceof Date) {
                                sv = df.format((Date) v).replace("00.00.00", "");
                                align = "center";
                            }

    %>


    <td  align=<%=align%>>
        <%=sv%>
    </td>

    <%
                        }

                        // Просто параметры
                        String getPars = "";

                        if(get_pars!=null && get_pars.length() > 0){

                            String getParsM [] = get_pars.split(";");

                            for(String s: getParsM){
                                getPars += ("&"+s);
                            }
                        }

                        //Параметры поля запроса
                        String  getParsFromQuery = "";

                        if(get_pars_from_query!=null && get_pars_from_query.length() > 0){

                            String getParsQ [] = get_pars_from_query.split(";");

                            for(String s: getParsQ){
                                getParsFromQuery += ("&"+s+"="+rs.getString(s));
                            }
                        }


    %>
    <c:if test="${not empty url_add || not empty url_edit || not empty url_del || not empty url_rep}">
        <td >
            <c:if test="${not empty url_add}">
                <A href="${url_add}?<%=getPars%><%=getParsFromQuery%>">
                    <img src="../resources/images/icons/add_16.png" width="16" height="16" alt="add_16" border="0"/>
                    Доб.
                </A>
                /
            </c:if>
            <c:if test="${not empty url_edit}">
                <A href="${url_edit}?id=<%=id%><%=getPars%><%=getParsFromQuery%>">
                    <img src="../resources/images/icons/edit_16.png" width="16" height="16" alt="edit_16" border="0"/>
                    Ред.
                </A>
                /
            </c:if>
            <c:if test="${not empty url_del}">
                <A href="${url_del}?id=<%=id%><%=getPars%><%=getParsFromQuery%>">
                    <img src="../resources/images/icons/remov_16.png" width="16" height="16" alt="remov_16" border="0"/>
                    Уд.
                </A>
                /
            </c:if>
            <c:if test="${not empty url_rep}">
                <A href="${url_rep}?id=<%=id%><%=getPars%><%=getParsFromQuery%>" target="_blank">
                    <img src="../resources/images/icons/docs_16.png" width="16" height="16" alt="remov_16" border="0"/>
                    Просм.
                </A>
                /
            </c:if>
        </td>
    </c:if>
</tr>


<%

                }

                //String s = null;
                //System.out.println(s.length());

            } catch (Exception ex) {



                System.out.println("ERROR IN cycle tag!!!\n" + ex.getMessage());


            } finally {
                resultT.closeAll();
            }

            System.out.println("Tag: result_cycle.tag ..");





%>

<tr>
    <td>
    </td>
</tr>
