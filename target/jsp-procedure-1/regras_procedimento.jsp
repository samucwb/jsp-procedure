<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <%@ include file="head-section.html" %>
    <body>

        <%@ include file="topbar.html" %>
        <div class="container"">
        
            <div class="card" align="center">
                <div class="card-header">
                    <center>
                        <h1>Regras para execução de Procedimentos</h1>
                    </center>
                 </div>
                 <div class="card-body">
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Procedimento</th>
                                <th>Idade</th>
                                <th>Sexo</th>
                                <th>Permite</th>
                            </tr>
                        </thead>
                        <c:forEach var="regraProcedimento" items="${listRegrasProcedimento}">
                            <tr>
                                <td><c:out value="${regraProcedimento.id}" /></td>
                                <td><c:out value="${regraProcedimento.procedimento.dsNomeProcedimento}" /></td>
                                <td><c:out value="${regraProcedimento.nrIdade}" /></td>
                                <td><c:out value="${regraProcedimento.dsSexo}" /></td>
                                <td><c:out value="${regraProcedimento.iePermite}" /></td>
                                
                            </tr>
                        </c:forEach>
                    </table>
                 </div>
            </div> 
        </div>
    </body>
    <%@ include file="footer-script.html" %>
</html>