package com.sschatz.jspdemo.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ProcedimentoPaciente.
 */
@Entity
@Table(name = "procedimento_paciente")
public class ProcedimentoPaciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private Long id;

    @Size(max = 500)
    @Column(name = "ds_observacao", length = 500)
    private String dsObservacao;

    @ManyToOne
    private Procedimento procedimento;

    @ManyToOne
    private Paciente paciente;

    public ProcedimentoPaciente() {
    }

    public ProcedimentoPaciente(Long id, String dsObservacao, Procedimento procedimento, Paciente paciente) {
        this.id = id;
        this.dsObservacao = dsObservacao;
        this.procedimento = procedimento;
        this.paciente = paciente;
    }

    public ProcedimentoPaciente( String dsObservacao, Procedimento procedimento, Paciente paciente) {
        this.dsObservacao = dsObservacao;
        this.procedimento = procedimento;
        this.paciente = paciente;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcedimentoPaciente id(Long id) {
        this.id = id;
        return this;
    }


    public String getDsObservacao() {
        return this.dsObservacao;
    }

    public ProcedimentoPaciente dsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
        return this;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public Procedimento getProcedimento() {
        return this.procedimento;
    }

    public ProcedimentoPaciente procedimento(Procedimento procedimento) {
        this.setProcedimento(procedimento);
        return this;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public ProcedimentoPaciente paciente(Paciente paciente) {
        this.setPaciente(paciente);
        return this;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

  

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcedimentoPaciente)) {
            return false;
        }
        return id != null && id.equals(((ProcedimentoPaciente) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProcedimentoPaciente{" +
            "id=" + getId() +
                "paciente = " + getPaciente().getId() +
                 "procedimento = " + getProcedimento().getId() +
            ", dsObservacao='" + getDsObservacao() + "'" +
            "}";
    }
}
