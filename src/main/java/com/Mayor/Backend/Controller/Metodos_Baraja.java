package com.Mayor.Backend.Controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Mayor.Backend.Dao.BarajaDao;
import com.Mayor.Backend.Models.Carta;


@Produces({"application/json"})
@RestController
@RequestMapping("/api")
public class Metodos_Baraja {
	
	@Autowired(required=true)
	private BarajaDao barajaDao ;
	
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/jugar" , method = RequestMethod.GET)
    public List<Carta> juego(){

		List<Carta> ct = barajaDao.juego();

        return ct;
        
        
    }

	

}
