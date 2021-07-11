package com.sschatz.jspdemo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Procedimento.
 */
@Entity
@Table(name = "procedimento")
public class Procedimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nr_codigo", nullable = false, unique = true)
    private Integer nrCodigo;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "ds_nome_procedimento", length = 100, nullable = false)
    private String dsNomeProcedimento;

    @OneToMany(mappedBy = "procedimento")
    private Set<ProcedimentoPaciente> procedimentoPacientes = new HashSet<>();

    @OneToMany(mappedBy = "procedimento")
    private Set<RegrasProcedimento> regrasProcedimentos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Procedimento id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNrCodigo() {
        return this.nrCodigo;
    }

    public Procedimento nrCodigo(Integer nrCodigo) {
        this.nrCodigo = nrCodigo;
        return this;
    }

    public void setNrCodigo(Integer nrCodigo) {
        this.nrCodigo = nrCodigo;
    }

    public String getDsNomeProcedimento() {
        return this.dsNomeProcedimento;
    }

    public Procedimento dsNomeProcedimento(String dsNomeProcedimento) {
        this.dsNomeProcedimento = dsNomeProcedimento;
        return this;
    }

    public void setDsNomeProcedimento(String dsNomeProcedimento) {
        this.dsNomeProcedimento = dsNomeProcedimento;
    }

    public Set<ProcedimentoPaciente> getProcedimentoPacientes() {
        return this.procedimentoPacientes;
    }

    public Procedimento procedimentoPacientes(Set<ProcedimentoPaciente> procedimentoPacientes) {
        this.setProcedimentoPacientes(procedimentoPacientes);
        return this;
    }

    public Procedimento addProcedimentoPaciente(ProcedimentoPaciente procedimentoPaciente) {
        this.procedimentoPacientes.add(procedimentoPaciente);
        procedimentoPaciente.setProcedimento(this);
        return this;
    }

    public Procedimento removeProcedimentoPaciente(ProcedimentoPaciente procedimentoPaciente) {
        this.procedimentoPacientes.remove(procedimentoPaciente);
        procedimentoPaciente.setProcedimento(null);
        return this;
    }

    public void setProcedimentoPacientes(Set<ProcedimentoPaciente> procedimentoPacientes) {
        if (this.procedimentoPacientes != null) {
            for (ProcedimentoPaciente procedimentoPaciente : this.procedimentoPacientes) {
                procedimentoPaciente.setProcedimento(null);
            }
        }
        if (procedimentoPacientes != null) {
            for (ProcedimentoPaciente procedimentoPaciente : this.procedimentoPacientes) {
                procedimentoPaciente.setProcedimento(this);
            }
        }
        this.procedimentoPacientes = procedimentoPacientes;
    }

    public Set<RegrasProcedimento> getRegrasProcedimentos() {
        return this.regrasProcedimentos;
    }

    public Procedimento regrasProcedimentos(Set<RegrasProcedimento> regrasProcedimentos) {
        this.setRegrasProcedimentos(regrasProcedimentos);
        return this;
    }

    public Procedimento addRegrasProcedimento(RegrasProcedimento regrasProcedimento) {
        this.regrasProcedimentos.add(regrasProcedimento);
        regrasProcedimento.setProcedimento(this);
        return this;
    }

    public Procedimento removeRegrasProcedimento(RegrasProcedimento regrasProcedimento) {
        this.regrasProcedimentos.remove(regrasProcedimento);
        regrasProcedimento.setProcedimento(null);
        return this;
    }

    public void setRegrasProcedimentos(Set<RegrasProcedimento> regrasProcedimentos) {
        if (this.regrasProcedimentos != null) {
             for (RegrasProcedimento regrasProcedimento : this.regrasProcedimentos) {
                regrasProcedimento.setProcedimento(null);
            }
        }
        if (regrasProcedimentos != null) {
            for (RegrasProcedimento regrasProcedimento : this.regrasProcedimentos) {
                regrasProcedimento.setProcedimento(this);
            }
        }
        this.regrasProcedimentos = regrasProcedimentos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Procedimento)) {
            return false;
        }
        return id != null && id.equals(((Procedimento) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Procedimento{" +
            "id=" + getId() +
            ", nrCodigo=" + getNrCodigo() +
            ", dsNomeProcedimento='" + getDsNomeProcedimento() + "'" +
            "}";
    }
}
