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
                        <h2>
                            <c:if test="${paciente != null}">
                                Editando Paciente
                            </c:if>
                            <c:if test="${paciente == null}">
                                Novo Paciente
                            </c:if>
                        </h2>
                        <div class="btn-toolbar justify-content-between" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group" role="group" aria-label="First group">
                                <median>
                                    <a href="pacienteform.jsp">Novo Paciente</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="paciente">Voltar para lista</a>
                                </median>
                            </div>

                        </div>

                    </center>
                </div>
            </div> 

            <c:if test="${paciente != null}">
                <form action="pacienteform?update=true" method="post">
                </c:if>
                <c:if test="${paciente == null}">
                    <form action="pacienteform?insert=true" method="post">
                    </c:if>


                    <c:if test="${paciente != null}">
                        <input type="hidden" name="id" value="<c:out value='${paciente.id}' />" />
                    </c:if> 

                    <div class="form-group size-fit-content">
                        <label for="nrCodigo">CPF</label>
                        <input value="<c:out value='${paciente.nrCodigo}' />" type="number" class="form-control" id="nrCodigo" aria-describedby="codigoHelp" placeholder="CPF">
                        <small id="codigoHelp" class="form-text text-muted">Digite seu CPF.</small>
                    </div>

                    <div class="form-group  size-fit-content">
                        <label for="dsNomePaciente">Nome</label>
                        <input value="<c:out value='${paciente.dsNomePaciente}' />" type="text" class="form-control" id="dsNomePaciente" aria-describedby="dsNomePacienteHelp" placeholder="Nome">
                        <small id="dsNomePacienteHelp" class="form-text text-muted">Digite seu Nome</small>
                    </div>

                    <div class="form-group">
                        <label for="nrIdade">Idade</label>
                        <input value="<c:out value='${paciente.nrIdade}' />" type="number" class="form-control" id="nrIdade" aria-describedby="nrIdadeHelp" placeholder="Idade">
                        <small id="nrIdadeHelp" class="form-text text-muted">Digite sua Idade.</small>
                    </div>


                    <div class="form-group  size-fit-content">
                        <label for="dsSexo">Sexo</label>
                        <select class="form-control" id="dsSexo">
                            <option>M</option>
                            <option>F</option>
                        </select>
                    </div>

                   
                        
                    <div class="col-12 justify-content-between">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                    </div>

                </form>
        </div>
    </body>
    <%@ include file="footer-script.html" %>
</html>