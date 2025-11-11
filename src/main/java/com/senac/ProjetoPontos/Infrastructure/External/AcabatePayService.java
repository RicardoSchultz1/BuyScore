package com.senac.ProjetoPontos.Infrastructure.External;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AcabatePayService {

    private final RestTemplate restTemplate;
    
    @Value("${acabatepay.api.url:https://api.acabatepay.com}")
    private String apiUrl;
    
    @Value("${acabatepay.api.key:your-api-key}")
    private String apiKey;

    public AcabatePayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Cria um pagamento na API do AcabatePay
     */
    public AcabatePayResponse criarPagamento(String customerName, String customerPhone, String customerEmail, String customerTaxId, BigDecimal valor, String descricao, int expiresIn) {
        String url = apiUrl + "/v1/payments";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        
        // Estrutura do customer
        Map<String, Object> customer = new HashMap<>();
        customer.put("name", customerName);
        customer.put("cellphone", customerPhone);
        customer.put("email", customerEmail);
        customer.put("taxId", customerTaxId);
        
        // Estrutura principal do data
        Map<String, Object> data = new HashMap<>();
        data.put("customer", customer);
        data.put("amount", valor.multiply(new BigDecimal("100")).intValue()); // Converter para centavos
        data.put("expiresIn", expiresIn);
        data.put("description", descricao);
        
        // Request body principal
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("data", data);
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        
        // Log da estrutura enviada (para debug)
        System.out.println("=== AcabatePay Request Structure ===");
        System.out.println("URL: " + url);
        System.out.println("Headers: Authorization: Bearer " + apiKey);
        System.out.println("Body: " + requestBody);
        System.out.println("=====================================");
        
        try {
            ResponseEntity<AcabatePayResponse> response = restTemplate.postForEntity(
                url, entity, AcabatePayResponse.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar com AcabatePay: " + e.getMessage(), e);
        }
    }

    /**
     * Consulta status de um pagamento na API do AcabatePay
     */
    public AcabatePayResponse consultarPagamento(String transacaoId) {
        String url = apiUrl + "/v1/payments/" + transacaoId;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<AcabatePayResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, AcabatePayResponse.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar pagamento no AcabatePay: " + e.getMessage(), e);
        }
    }

    /**
     * Valida webhook do AcabatePay
     */
    public boolean validarWebhook(String signature, String payload) {
        // Implementar validação de assinatura do webhook
        // Normalmente usando HMAC SHA256 com chave secreta
        return true; // Por enquanto, aceitar todos
    }

    // Classe para mapear a resposta da API do AcabatePay
    public static class AcabatePayResponse {
        private String transaction_id;
        private String status;
        private BigDecimal amount;
        private String currency;
        private String payment_url;
        private String created_at;
        private String updated_at;

        // Getters e Setters
        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPayment_url() {
            return payment_url;
        }

        public void setPayment_url(String payment_url) {
            this.payment_url = payment_url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}