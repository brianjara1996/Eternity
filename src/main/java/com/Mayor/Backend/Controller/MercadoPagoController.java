package com.Mayor.Backend.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.apache.maven.model.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Mayor.Backend.Dao.CobroDao;
import com.Mayor.Backend.Models.Jsonresp;
import com.mercadopago.exceptions.MPException;

@Produces({"application/json"})
@RestController
@RequestMapping("/mp")
public class MercadoPagoController {
    
    @Autowired(required=true)
    private CobroDao cobroDao;
    
    
    @RequestMapping(value = "/cobrar/{Saldo}" , method = RequestMethod.GET)
    public Jsonresp Cobro(@PathVariable int Saldo) {
        
        Jsonresp js = new Jsonresp();
                js.setJson(cobroDao.Cobro(Saldo));
               
               return js;
        
    }
    
    
    
    @RequestMapping(value = "/aprovado" , method = RequestMethod.GET)
    public ModelAndView  aprovado(HttpServletRequest request,
            @RequestParam("collection_id") String collectionid,
            @RequestParam("collection_status") String collectionstatus,
            @RequestParam("external_reference") String externalReference,
            @RequestParam("payment_type") String pymentType,
            @RequestParam("merchant_order_id") String merchant,
            @RequestParam("preference_id") String preferenceId,
            @RequestParam("site_id") String siteId,
            @RequestParam("processing_mode") String processingMode,
            @RequestParam("merchant_account_id") String merchatId,
            Model model) throws MPException {
        
        
        if(collectionstatus == "approved" || collectionstatus.equals("approved")) {
            
          if(cobroDao.insertoPago(collectionid, Metodos_Baraja.prueba)) {
              return new ModelAndView("redirect:http://localhost:8080/juego.html"); 
          }
            
        }
        return new ModelAndView("redirect:http://www.google.es");
    }
    
    
    
    @RequestMapping(value = "/saldo" , method = RequestMethod.GET)
    public Jsonresp consultaSaldo() {
            
        String saldo = cobroDao.saldo(Metodos_Baraja.prueba);
        
        
        Jsonresp rp = new Jsonresp();
        
        rp.setJson(saldo);
        
        return rp;  
    }
    
    
}
