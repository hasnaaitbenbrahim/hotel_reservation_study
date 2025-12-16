package com.example.hotel.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private BigDecimal prix;
    private boolean disponible;

    public Chambre() {
    }

    public Chambre(String type, BigDecimal prix, boolean disponible) {
        this.type = type;
        this.prix = prix;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
