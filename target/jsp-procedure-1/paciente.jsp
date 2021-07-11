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
                        <h1>Pacientes</h1>
                    </center>
                 </div>
                 <div class="card-body">
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Codigo</th>
                                <th>Idade</th>
                                <th>Sexo</th>
                            </tr>
                        </thead>
                        <c:forEach var="paciente" items="${listPaciente}">
                            <tr>
                                <td><c:out value="${paciente.id}" /></td>
                                <td><c:out value="${paciente.dsNomePaciente}" /></td>
                                <td><c:out value="${paciente.nrCodigo}" /></td>
                                <td><c:out value="${paciente.nrIdade}" /></td>
                                <td><c:out value="${paciente.dsSexo}" /></td>
                            </tr>
                        </c:forEach>
                    </table>
                 </div>
            </div> 
        </div>
    </body>
    <%@ include file="footer-script.html" %>
</html>