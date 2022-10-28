package com.Mayor.Backend.Models;

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
@Table(name = "usuario")
@ToString
@EqualsAndHashCode


public class Usuario {
    
    @Getter @Setter @Column(name= "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Getter @Setter @Column(name= "usuario")
    private String usuario;
    
    @Getter @Setter @Column(name= "pass")
    private String pass;
    
    @Getter @Setter @Column(name= "fecha_creacion")
    private String fecha_creacion;
    
    
    
    public String getUsuario() {
        return usuario;
    }
    public String getPass() {
        return pass;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public int getId() {
        return id;
    }
    public String getFecha_creacion() {
        return fecha_creacion;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
    

}
