package com.fortyfirst.orbat.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Troopers.
 */
@Entity
@Table(name = "troopers")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Troopers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "rango")
    private String rango;

    @Column(name = "apodo")
    private String apodo;

    @Column(name = "isactive")
    private Integer isactive;

    @Column(name = "amonestacion")
    private Integer amonestacion;

    @Column(name = "rol")
    private String rol;

    @Column(name = "fecha_entrada_servicio")
    private LocalDate fechaEntradaServicio;

    @Column(name = "fecha_ultima_prom")
    private LocalDate fechaUltimaProm;

    @Column(name = "reclutador")
    private String reclutador;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public Troopers numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRango() {
        return rango;
    }

    public Troopers rango(String rango) {
        this.rango = rango;
        return this;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getApodo() {
        return apodo;
    }

    public Troopers apodo(String apodo) {
        this.apodo = apodo;
        return this;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public Troopers isactive(Integer isactive) {
        this.isactive = isactive;
        return this;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Integer getAmonestacion() {
        return amonestacion;
    }

    public Troopers amonestacion(Integer amonestacion) {
        this.amonestacion = amonestacion;
        return this;
    }

    public void setAmonestacion(Integer amonestacion) {
        this.amonestacion = amonestacion;
    }

    public String getRol() {
        return rol;
    }

    public Troopers rol(String rol) {
        this.rol = rol;
        return this;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDate getFechaEntradaServicio() {
        return fechaEntradaServicio;
    }

    public Troopers fechaEntradaServicio(LocalDate fechaEntradaServicio) {
        this.fechaEntradaServicio = fechaEntradaServicio;
        return this;
    }

    public void setFechaEntradaServicio(LocalDate fechaEntradaServicio) {
        this.fechaEntradaServicio = fechaEntradaServicio;
    }

    public LocalDate getFechaUltimaProm() {
        return fechaUltimaProm;
    }

    public Troopers fechaUltimaProm(LocalDate fechaUltimaProm) {
        this.fechaUltimaProm = fechaUltimaProm;
        return this;
    }

    public void setFechaUltimaProm(LocalDate fechaUltimaProm) {
        this.fechaUltimaProm = fechaUltimaProm;
    }

    public String getReclutador() {
        return reclutador;
    }

    public Troopers reclutador(String reclutador) {
        this.reclutador = reclutador;
        return this;
    }

    public void setReclutador(String reclutador) {
        this.reclutador = reclutador;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Troopers)) {
            return false;
        }
        return id != null && id.equals(((Troopers) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Troopers{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", rango='" + getRango() + "'" +
            ", apodo='" + getApodo() + "'" +
            ", isactive=" + getIsactive() +
            ", amonestacion=" + getAmonestacion() +
            ", rol='" + getRol() + "'" +
            ", fechaEntradaServicio='" + getFechaEntradaServicio() + "'" +
            ", fechaUltimaProm='" + getFechaUltimaProm() + "'" +
            ", reclutador='" + getReclutador() + "'" +
            "}";
    }
}
