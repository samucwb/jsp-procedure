package com.sschatz.jspdemo.domain;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Paciente.
 */
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

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
    @Column(name = "ds_nome_paciente", length = 100, nullable = false)
    private String dsNomePaciente;

    @NotNull
    @Column(name = "nr_idade", nullable = false)
    private Integer nrIdade;

    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ds_sexo", length = 1, nullable = false)
    private String dsSexo;

    @OneToMany(mappedBy = "paciente")
    private Set<ProcedimentoPaciente> procedimentoPacientes = new HashSet<>();

    public Paciente() {
    }

    public Paciente(Long id, Integer nrCodigo, String dsNomePaciente, Integer nrIdade, String dsSexo) {
        this.id = id;
        this.nrCodigo = nrCodigo;
        this.dsNomePaciente = dsNomePaciente;
        this.nrIdade = nrIdade;
        this.dsSexo = dsSexo;
    }

    public Paciente(Integer nrCodigo, String dsNomePaciente, Integer nrIdade, String dsSexo) {
        this.nrCodigo = nrCodigo;
        this.dsNomePaciente = dsNomePaciente;
        this.nrIdade = nrIdade;
        this.dsSexo = dsSexo;
    }
    
    
    

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNrCodigo() {
        return this.nrCodigo;
    }

    public Paciente nrCodigo(Integer nrCodigo) {
        this.nrCodigo = nrCodigo;
        return this;
    }

    public void setNrCodigo(Integer nrCodigo) {
        this.nrCodigo = nrCodigo;
    }

    public String getDsNomePaciente() {
        return this.dsNomePaciente;
    }

    public Paciente dsNomePaciente(String dsNomePaciente) {
        this.dsNomePaciente = dsNomePaciente;
        return this;
    }

    public void setDsNomePaciente(String dsNomePaciente) {
        this.dsNomePaciente = dsNomePaciente;
    }

    public Integer getNrIdade() {
        return this.nrIdade;
    }

    public Paciente nrIdade(Integer nrIdade) {
        this.nrIdade = nrIdade;
        return this;
    }

    public void setNrIdade(Integer nrIdade) {
        this.nrIdade = nrIdade;
    }

    public String getDsSexo() {
        return this.dsSexo;
    }

    public Paciente dsSexo(String dsSexo) {
        this.dsSexo = dsSexo;
        return this;
    }

    public void setDsSexo(String dsSexo) {
        this.dsSexo = dsSexo;
    }

    public Set<ProcedimentoPaciente> getProcedimentoPacientes() {
        return this.procedimentoPacientes;
    }

    public Paciente procedimentoPacientes(Set<ProcedimentoPaciente> procedimentoPacientes) {
        this.setProcedimentoPacientes(procedimentoPacientes);
        return this;
    }

    public Paciente addProcedimentoPaciente(ProcedimentoPaciente procedimentoPaciente) {
        this.procedimentoPacientes.add(procedimentoPaciente);
        procedimentoPaciente.setPaciente(this);
        return this;
    }

    public Paciente removeProcedimentoPaciente(ProcedimentoPaciente procedimentoPaciente) {
        this.procedimentoPacientes.remove(procedimentoPaciente);
        procedimentoPaciente.setPaciente(null);
        return this;
    }

    public void setProcedimentoPacientes(Set<ProcedimentoPaciente> procedimentoPacientes) {
        if (this.procedimentoPacientes != null) {
            for (ProcedimentoPaciente procedimentoPaciente : this.procedimentoPacientes) {
                procedimentoPaciente.setPaciente(null);
            }
        }
        if (procedimentoPacientes != null) {
            for (ProcedimentoPaciente procedimentoPaciente : this.procedimentoPacientes) {
                procedimentoPaciente.setPaciente(this);
            }
        }
        this.procedimentoPacientes = procedimentoPacientes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paciente)) {
            return false;
        }
        return id != null && id.equals(((Paciente) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Paciente{" +
            "id=" + getId() +
            ", nrCodigo=" + getNrCodigo() +
            ", dsNomePaciente='" + getDsNomePaciente() + "'" +
            ", nrIdade='" + getNrIdade() + "'" +
            ", dsSexo='" + getDsSexo() + "'" +
            "}";
    }
}
