package com.Mayor.Backend.Models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "saldos")
@ToString
@EqualsAndHashCode


public class saldos {
    
    
    @Getter @Setter @Column(name= "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Getter @Setter @Column(name= "usuario")
    private String usuario;
    
    @Getter @Setter @Column(name= "saldo")
    private int saldo;
    
    @Getter @Setter @Column(name= "id_pago")
    private String id_pago;
    
    @Getter @Setter @Column(name= "fecha_pago")
    private  String fecha_pago;
    
    @Getter @Setter @Column(name= "informado")
    private Boolean informado;
    
    

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getId_pago() {
        return id_pago;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public Boolean getInformado() {
        return informado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setId_pago(String id_pago) {
        this.id_pago = id_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public void setInformado(Boolean informado) {
        this.informado = informado;
    }
    
    

}
