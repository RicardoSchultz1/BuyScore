package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;


public class ClienteWithTokenResponse {

   private Cliente cliente;
   private String token;

   public ClienteWithTokenResponse(Cliente cliente, String token) {
       this.cliente = cliente;
       this.token = token;
   }

   public Cliente getCliente() {
       return cliente;
   }

   public void setCliente(Cliente cliente) {
       this.cliente = cliente;
   }

   public String getToken() {
       return token;
   }

   public void setToken(String token) {
       this.token = token;
   }
}
