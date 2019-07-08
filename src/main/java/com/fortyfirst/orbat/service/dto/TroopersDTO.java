package com.fortyfirst.orbat.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.fortyfirst.orbat.domain.Troopers} entity.
 */
public class TroopersDTO implements Serializable {

    private Long id;

    private String numero;

    private String rango;

    private String apodo;

    private Integer isactive;

    private Integer amonestacion;

    private String rol;

    private LocalDate fechaEntradaServicio;

    private LocalDate fechaUltimaProm;

    private String reclutador;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Integer getAmonestacion() {
        return amonestacion;
    }

    public void setAmonestacion(Integer amonestacion) {
        this.amonestacion = amonestacion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDate getFechaEntradaServicio() {
        return fechaEntradaServicio;
    }

    public void setFechaEntradaServicio(LocalDate fechaEntradaServicio) {
        this.fechaEntradaServicio = fechaEntradaServicio;
    }

    public LocalDate getFechaUltimaProm() {
        return fechaUltimaProm;
    }

    public void setFechaUltimaProm(LocalDate fechaUltimaProm) {
        this.fechaUltimaProm = fechaUltimaProm;
    }

    public String getReclutador() {
        return reclutador;
    }

    public void setReclutador(String reclutador) {
        this.reclutador = reclutador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TroopersDTO troopersDTO = (TroopersDTO) o;
        if (troopersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), troopersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TroopersDTO{" +
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
