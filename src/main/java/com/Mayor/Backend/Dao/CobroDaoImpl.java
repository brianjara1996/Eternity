package com.Mayor.Backend.Dao;

import java.math.BigDecimal;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.net.http.HttpResponse.BodyHandlers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;



import org.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.Mayor.Backend.Models.saldos;
// SDK de Mercado Pago
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional

public class CobroDaoImpl implements CobroDao{
    
    
   @PersistenceContext
   private EntityManager entityManager;

    @Override
    public String Cobro(int Saldo) {

     MercadoPagoConfig.setAccessToken("TEST-7762551772916768-102410-ebb2c6730c77e84963d15c5c73123be3-317056802");
     

  // Crea un objeto de preferencia
  PreferenceClient client = new PreferenceClient();
  
  
  // Crea un ítem en la preferencia
  List<PreferenceItemRequest> items = new ArrayList<>();
  PreferenceItemRequest item =
     PreferenceItemRequest.builder()
         .title("Producto 1")
         .quantity(1)
         .unitPrice(new BigDecimal(Saldo))
         .build();
  items.add(item);
  

PreferenceBackUrlsRequest backUrls =
   PreferenceBackUrlsRequest.builder()
       .success("http://localhost:8080/mp/aprovado")
       .pending("https://www.seu-site/pending")
       .failure("https://www.seu-site/failure")
       .build();

   PreferenceRequest request = PreferenceRequest.builder().backUrls(backUrls).items(items).build();
   
   
 
  
  try {

      Preference mp = client.create(request);
      
      System.out.println(mp.getNotificationUrl());
        
    return mp.getInitPoint();
} catch (MPException | MPApiException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    return "Error";
}

    }

    @Override
    public String aprovado() {
        
        
        
        return null;
    }

    @Override
    public Boolean insertoPago(String id_pago , String user) {
        
        
         Boolean bandera = false;
        
        String URL1 = "https://api.mercadopago.com/v1/payments/";
        
        String URL2 = "?access_token=TEST-7762551772916768-102410-ebb2c6730c77e84963d15c5c73123be3-317056802";
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(URL1 + id_pago + URL2))
              .GET()
              .build();
        
        
        HttpClient client2 = HttpClient.newHttpClient();
      
      
      
        CompletableFuture<String> respuesta=client
            .sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body);
        
        CompletableFuture<String> respuesta2=client2
            .sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body);
         
        
    String resultado = Stream.of(respuesta, respuesta2).map(CompletableFuture::join)
        .collect(Collectors.joining(" "));
    
    System.out.println(resultado);
    
    JSONObject json = new JSONObject(resultado);

//Obtenemos la propiedad dentro de una rama del json
     String estado_pago = json.getString("status"); //json.getJSONObject("status").getString("Holder");

//Obtenemos la propiedad de tipo string fuera de una rama en un json
     int monto = json.getInt("transaction_amount");

     if(estado_pago.equals("approved")) {
         
         

         String timestamp = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"))
                 .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
         
         
         
         saldos sl = new saldos();
         
         sl.setUsuario(user);
         sl.setId_pago(id_pago);
         sl.setSaldo(monto);
         sl.setFecha_pago(timestamp);
         sl.setInformado(false);
        
         try {
             
             entityManager.merge(sl);
             
             bandera = true;
             
         }
         catch(Exception e) {
             System.out.println("ERROOR: " + e);
             
             bandera = false;
             
         }
                 
     }
    return bandera;
    

    }

    @Override
    public String saldo(String user) {

        String query = "SELECT saldo From saldos where usuario = '" + user+"' and informado = false" ;

       String result = entityManager.createQuery(query).getSingleResult().toString();
        
        if(result != null) {
            
            String query2 = "Update saldos set informado = true where usuario = '" + user + "'";
            
            entityManager.createQuery(query2).executeUpdate();
          
            
            return result;
        }
        else {
            return null;
        }
    }

}
