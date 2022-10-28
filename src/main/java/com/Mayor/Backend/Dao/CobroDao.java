package com.Mayor.Backend.Dao;

public interface CobroDao {
    
    String Cobro(int Saldo);
    
    String aprovado();
    
    Boolean insertoPago(String id_pago, String user);
    
    String saldo(String user);

}
