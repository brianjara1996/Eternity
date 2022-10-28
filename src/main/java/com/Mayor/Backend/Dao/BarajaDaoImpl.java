package com.Mayor.Backend.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Mayor.Backend.Models.Baraja;
import com.Mayor.Backend.Models.Carta;

@Repository
public class BarajaDaoImpl implements BarajaDao {

	@Override
	public List<Carta> juego() {
		
		Baraja bj = new Baraja();
		
		bj.crearBaraja();
		
		
		List<Carta> ct = bj.Barajar_Repartir();
		
		return ct;
	}

	
    @Override
    public String Login(String user, String pass) {
        
        if(user.equals("bjara") && pass.equals("1234")) {
            
            return "ok";
        }
        else {
            return "error";
        }
        

    }

}
