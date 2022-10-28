package com.Mayor.Backend.Controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Mayor.Backend.Dao.BarajaDao;

import com.Mayor.Backend.Models.Carta;
import com.Mayor.Backend.Models.Usuario;


@Produces({"application/json"})
@RestController
@RequestMapping("/api")
public class Metodos_Baraja {
	
	@Autowired(required=true)
	private BarajaDao barajaDao ;
	
	public static String prueba;
		

	@RequestMapping(value = "/jugar" , method = RequestMethod.GET)
    public List<Carta> juego(){

		List<Carta> ct = barajaDao.juego();

        return ct;
        
        
    }    
	
	
	
    @RequestMapping(value = "/login/{user}/{pass}" , method = RequestMethod.GET)
    public Usuario Login(@PathVariable String user, @PathVariable String pass){
     
        Usuario us = new Usuario();
        
       String result =  barajaDao.Login(user, pass);
        
       if(result.equals("ok")) {
           us.setUsuario(user);
           us.setPass(pass);
           
           prueba = us.getUsuario();
                     
           return us;
       }
       else {
           return us;
       }

    }
	
	

}
