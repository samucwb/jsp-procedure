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
                            <c:if test="${entity != null}">
                                Editando procedimento
                            </c:if>
                            <c:if test="${entity == null}">
                                Nova execucao de procedimento
                            </c:if>
                        </h2>
                        <div class="btn-toolbar justify-content-between" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group" role="group" aria-label="First group">
                                <median>
                                    <a href="proc_paciente_form.jsp">Adicionar</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="procedimento_paciente">Voltar para lista</a>
                                </median>
                            </div>

                        </div>

                    </center>
                </div>
            </div> 

<!--            onsubmit="checkValidation();return false;"-->
            <c:if test="${entity != null}">
                <form action="procedimento_paciente_form?update=true" id="myForm" name="myForm" method="post" >
                </c:if>
                <c:if test="${entity == null}">
                    <form action="procedimento_paciente_form?insert=true" method="post" id="myForm" name="myForm"  >
                    </c:if>


                    <c:if test="${entity != null}">
                        <input type="hidden" name="id" value="<c:out value='${entity.id}' />" />
                    </c:if> 
                        
                    <div class="form-group  size-fit-content">
                        <label for="procedimento">Escolha um Procedimento</label>
                        <select class="form-control" id="procedimento" name="procedimento">
                            <c:forEach items="${procedureList}" var="procedimento">
                                <option value="${procedimento.id}"
                                    <c:if test="${procedimento.id eq entity.procedimento.id}">selected="selected"</c:if>
                                    >
                                    ${procedimento.dsNomeProcedimento}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                        
                    <div class="form-group  size-fit-content">
                        <label for="paciente">Escolha um Paciente</label>
                        <select class="form-control" id="paciente" name="paciente">
                            <c:forEach items="${pacienteList}" var="paciente">
                                <option value="${paciente.id}"
                                    <c:if test="${paciente.id eq entity.paciente.id}">selected="selected"</c:if>
                                    >
                                    ${paciente.dsNomePaciente}
                                </option>
                            </c:forEach>
                        </select>
                    </div>


                    <div class="form-group  size-fit-content">
                        <label for="dsObservacao">Observacao</label>
                        <input value="<c:out value='${entity.dsObservacao}' />" type="text" class="form-control" id="dsObservacao"  name="dsObservacao" aria-describedby="dsObservacao" placeholder="Observacao">
                        <small id="dsObservacaoHelp" class="form-text text-muted">Digite aqui sua observacao</small>
                    </div>


                        
                    <div class="col-12 justify-content-between">
                        <button type="submit"   class="btn btn-primary" /> Salvar</button>
                    </div>

                </form>
        </div>
    </body>
    
<!--    onclick="javascript:return checkValidation();return false;"-->
    <script type="text/javascript">
   
        function verifyRules(cdPaciente, cdProcedimento) {
            
            return new Promise((resolve, reject) => {
                if (cdPaciente == null || cdPaciente == "" || cdProcedimento == null || cdProcedimento == "") {
                    alert("Preencha o campo paciente e procedimento..!!");
                    reject(false);
                } else {

                    $.get('check_rule?cdPaciente='+ cdPaciente +'&cdProcedimento='+cdProcedimento, function(responseJson) {
                        if(responseJson.status) {
                            resolve(responseJson.status); 
                        } else {
                            reject();
                        }
                    }).fail(function(xhr, httpStatusMessage, customErrorMessage) {
                          console.error(xhr);
                          console.error(httpStatusMessage);
                    });
                }
            });
        }
        
        function sleep(lf_ms) {
            return new Promise(resolve => setTimeout(resolve, lf_ms));
         }
        
        async function checkValidation(cdPaciente, cdProcedimento) {
            let retorno = await  verifyRules(cdPaciente, cdProcedimento);
            return retorno;
        }
        
        function initiliaze() {
            $(document).on("submit", "#myForm", function(event) {
                var $form = $(this);
                
                var form = document.getElementById("myForm");
                const cdPaciente = form.elements["paciente"].value;
                const cdProcedimento = form.elements["procedimento"].value;
                
                checkValidation(cdPaciente, cdProcedimento).then(() => {
                    $.post($form.attr("action"), $form.serialize(), function() { window.location = 'procedimento_paciente' });    
                }, () => {
                    alert("Procedimento não permitido!!");
                });
                

                event.preventDefault(); // Important! Prevents submitting the form.
            });
        }
        
        initiliaze();
        
    </script>
    <%@ include file="footer-script.html" %>
</html>