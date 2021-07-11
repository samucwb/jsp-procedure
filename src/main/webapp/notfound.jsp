<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <%@ include file="head-section.html" %>
    <body>

        <%@ include file="topbar.html" %>

        <!-- Page content-->
        <div class="container">
            <div class="text-center mt-5">

                <div class="text-center mt-5">
                    <h1>ERRO 404</h1>
                     <%
                        out.println("Requested resource: " + request.getRequestURL()+ " not found");
                    %>
                </div>
        
            </div>
        </div>
        
    </body>
    <%@ include file="footer-script.html" %>
</html>