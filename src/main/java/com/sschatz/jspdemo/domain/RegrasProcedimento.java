package com.sschatz.jspdemo.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A RegrasProcedimento.
 */
@Entity
@Table(name = "regras_procedimento")
public class RegrasProcedimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nr_idade", nullable = false)
    private Integer nrIdade;

    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ds_sexo", length = 1, nullable = false)
    private String dsSexo;

    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ie_permite", length = 1, nullable = false)
    private String iePermite;

    @ManyToOne
    private Procedimento procedimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegrasProcedimento id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNrIdade() {
        return this.nrIdade;
    }

    public RegrasProcedimento nrIdade(Integer nrIdade) {
        this.nrIdade = nrIdade;
        return this;
    }

    public void setNrIdade(Integer nrIdade) {
        this.nrIdade = nrIdade;
    }

    public String getDsSexo() {
        return this.dsSexo;
    }

    public RegrasProcedimento dsSexo(String dsSexo) {
        this.dsSexo = dsSexo;
        return this;
    }

    public void setDsSexo(String dsSexo) {
        this.dsSexo = dsSexo;
    }

    public String getIePermite() {
        return this.iePermite;
    }

    public RegrasProcedimento iePermite(String iePermite) {
        this.iePermite = iePermite;
        return this;
    }

    public void setIePermite(String iePermite) {
        this.iePermite = iePermite;
    }

    public Procedimento getProcedimento() {
        return this.procedimento;
    }

    public RegrasProcedimento procedimento(Procedimento procedimento) {
        this.setProcedimento(procedimento);
        return this;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegrasProcedimento)) {
            return false;
        }
        return id != null && id.equals(((RegrasProcedimento) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegrasProcedimento{" +
            "id=" + getId() +
            ", nrIdade=" + getNrIdade() +
            ", dsSexo='" + getDsSexo() + "'" +
            ", iePermite='" + getIePermite() + "'" +
            "}";
    }
}
