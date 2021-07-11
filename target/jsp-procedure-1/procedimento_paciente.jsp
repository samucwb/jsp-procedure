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
                        <h1>Execucao de Procedimentos</h1>
                        <div class="btn-toolbar justify-content-between" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group" role="group" aria-label="First group">
                                <medium>
                                   <a href="?new=true">Adicionar</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="procedimento_paciente">Listar</a> 
                                </medium>


                            </div>

                        </div>

                    </center>
                 </div>
                 <div class="card-body">
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Procedimento</th>
                                <th>Paciente</th>
                                <th>Observacao</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <c:forEach var="procedimentoPaciente" items="${listProcedimentoPaciente}">
                            <tr>
                                <td><c:out value="${procedimentoPaciente.id}" /></td>
                                <td><c:out value="${procedimentoPaciente.procedimento.dsNomeProcedimento}" /></td>
                                <td><c:out value="${procedimentoPaciente.paciente.dsNomePaciente}" /></td>
                                <td><c:out value="${procedimentoPaciente.dsObservacao}" /></td>
                                <td>
                                    <a href="?edit=true&id=<c:out value='${procedimentoPaciente.id}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="?delete=true&id=<c:out value='${procedimentoPaciente.id}' />">Delete</a>                     
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                 </div>
            </div> 
        </div>
    </body>
    <%@ include file="footer-script.html" %>
</html>