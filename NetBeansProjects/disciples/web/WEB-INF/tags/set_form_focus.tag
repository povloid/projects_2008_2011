<%-- 
    Document   : set_form_focus
    Created on : 01.08.2010, 12:47:07
    Author     : dev_sport
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="form"%>
<%@attribute name="input"%>

<script type="text/javascript">

    try{
        document.forms['<%=form%>'].<%=input%>.focus();
    }catch(e){};

</script>