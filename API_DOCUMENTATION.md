# 📖 Documentação de API - BuyScore

**Versão:** 1.0  
**Base URL:** `http://localhost:8081`

---

## 🔐 Autenticação

### POST `/auth/login`
Realiza login e retorna token JWT.

**Request Body:**
```json
{
  "email": "string",
  "senha": "string"
}
```

**Response (200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Response (401):** `"Credenciais inválidas"`

---

## 👤 Usuários

### GET `/usuario/{id}`
Busca usuário por ID.

**Parâmetros:**
- `id` (UUID): ID do usuário

**Response (200):**
```json
{
  "id": "uuid",
  "nome": "string",
  "email": "string",
  "perfilUsuario": 1,
  "fotoUsuario": "string",
  "endereco": { /* objeto endereco */ }
}
```

### POST `/usuario`
Cria novo usuário.

**Request Body:**
```json
{
  "nome": "string",
  "email": "string",
  "senha": "string",
  "perfilUsuario": 1,
  "fotoUsuario": "string",
  "endereco": { /* objeto endereco */ }
}
```

**Response (200):** Objeto Usuario criado

### GET `/usuario/all` 🔒
Lista todos os usuários (ADMIN apenas).

**Headers:** `Authorization: Bearer {token}`

**Response (200):** Array de objetos Usuario

---

## 🏪 Clientes

### GET `/cliente/all`
Lista todos os clientes.

**Response (200):** Array de objetos Cliente

### GET `/cliente/{id}`
Busca cliente por ID.

**Parâmetros:**
- `id` (UUID): ID do cliente

**Response (200):**
```json
{
  "id": "uuid",
  "usuario": { /* objeto usuario */ },
  "pontos": 100,
  "comerciosFavoritos": [],
  "produtosFavoritos": []
}
```

### POST `/cliente`
Cria novo cliente.

**Request Body:**
```json
{
  "nome": "string",
  "email": "string",
  "senha": "string",
  "fotoUsuario": "string",
  "cep": "string",
  "logradouro": "string",
  "complemento": "string",
  "bairro": "string",
  "cidade": "string",
  "numero": 123,
  "uf": "string"
}
```

**Response (200):** Token JWT

### PUT `/cliente/{id}`
Atualiza cliente.

**Parâmetros:**
- `id` (UUID): ID do cliente

**Request Body:** Objeto Cliente completo

**Response (204):** No Content

### DELETE `/cliente/{id}`
Remove cliente.

**Parâmetros:**
- `id` (UUID): ID do cliente

**Response (204):** No Content

### Comércios Favoritos 🔒

#### POST `/cliente/comercio-favoritos/{comercioId}`
Adiciona comércio aos favoritos.

**Headers:** `Authorization: Bearer {token}`
**Parâmetros:**
- `comercioId` (UUID): ID do comércio

**Response (200):** Success

#### DELETE `/cliente/comercio-favoritos/{comercioId}`
Remove comércio dos favoritos.

**Headers:** `Authorization: Bearer {token}`
**Parâmetros:**
- `comercioId` (UUID): ID do comércio

**Response (204):** No Content

#### GET `/cliente/comercio-favoritos`
Lista comércios favoritos do cliente logado.

**Headers:** `Authorization: Bearer {token}`

**Response (200):** Array de objetos Comercio

#### GET `/cliente/comercio-favoritos/{comercioId}/check`
Verifica se comércio é favorito.

**Headers:** `Authorization: Bearer {token}`
**Parâmetros:**
- `comercioId` (UUID): ID do comércio

**Response (200):** `boolean`

### Produtos Favoritos 🔒

#### POST `/cliente/produto-favoritos/{produtoId}`
Adiciona produto aos favoritos.

**Headers:** `Authorization: Bearer {token}`
**Parâmetros:**
- `produtoId` (UUID): ID do produto

**Response (200):** Success

#### DELETE `/cliente/produto-favoritos/{produtoId}`
Remove produto dos favoritos.

**Headers:** `Authorization: Bearer {token}`
**Parâmetros:**
- `produtoId` (UUID): ID do produto

**Response (204):** No Content

#### GET `/cliente/produto-favoritos`
Lista produtos favoritos do cliente logado.

**Headers:** `Authorization: Bearer {token}`

**Response (200):** Array de objetos Produto

#### GET `/cliente/produto-favoritos/{produtoId}/check`
Verifica se produto é favorito.

**Headers:** `Authorization: Bearer {token}`
**Parâmetros:**
- `produtoId` (UUID): ID do produto

**Response (200):** `boolean`

---

## 🏢 Comércios

### GET `/comercio/{id}`
Busca comércio por ID.

**Parâmetros:**
- `id` (UUID): ID do comércio

**Response (200):**
```json
{
  "id": "uuid",
  "usuario": { /* objeto usuario */ },
  "cnpj": "string",
  "razaoSocial": "string",
  "descricao": "string",
  "seguimento": "string",
  "matriz": { /* objeto usuario matriz */ },
  "vendas": 0
}
```

### POST `/comercio`
Cria novo comércio.

**Request Body:**
```json
{
  "cnpj": "string",
  "razaoSocial": "string",
  "descricao": "string",
  "seguimento": "string",
  "matrizId": "string",
  "nome": "string",
  "email": "string",
  "senha": "string",
  "fotoUsuario": "string",
  "cep": "string",
  "logradouro": "string",
  "complemento": "string",
  "bairro": "string",
  "cidade": "string",
  "numero": 123,
  "uf": "string"
}
```

**Response (200):** Token JWT

### GET `/comercio/all`
Lista todos os comércios.

**Response (200):** Array de objetos Comercio

### Consultas de Vendas

#### GET `/comercio/top5/setor/{seguimento}`
Top 5 comércios por setor específico.

**Parâmetros:**
- `seguimento` (String): Nome do setor

**Response (200):** Array de objetos Comercio

#### GET `/comercio/top5/setores-principais`
Top 5 dos setores principais (restaurante, farmácia, outros).

**Response (200):** Array de objetos Comercio

#### GET `/comercio/top5/cada-setor`
Top 5 de cada setor.

**Response (200):** Array de objetos Comercio

#### GET `/comercio/top5/setores`
Top 5 por múltiplos setores.

**Query Params:**
- `setores` (Array[String]): Lista de setores

**Response (200):** Array de objetos Comercio

---

## 📦 Produtos

### GET `/produto/{id}`
Busca produto por ID.

**Parâmetros:**
- `id` (String): ID do produto

**Response (200):**
```json
{
  "id": "uuid",
  "nome": "string",
  "descricao": "string",
  "valor": 100,
  "ativo": true,
  "fotoProduto": "string",
  "comercio": { /* objeto comercio */ }
}
```

### POST `/produto` 🔒
Cria novo produto.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "nome": "string",
  "descricao": "string",
  "valor": 100,
  "ativo": true,
  "fotoProduto": "string"
}
```

**Response (200):** Objeto Produto criado

### PUT `/produto`
Atualiza produto.

**Request Body:** Objeto Produto completo

**Response (200):** Objeto Produto atualizado

### DELETE `/produto/{id}`
Remove produto.

**Parâmetros:**
- `id` (String): ID do produto

**Response (200):** Success

### GET `/produto/all`
Lista todos os produtos.

**Response (200):** Array de objetos Produto

### GET `/produto/comercio/{comercioId}`
Lista todos os produtos de um comércio específico.

**Parâmetros:**
- `comercioId` (UUID): ID do comércio

**Response (200):** Array de objetos Produto

### PUT `/produto/ativar/{id}`
Ativa produto.

**Parâmetros:**
- `id` (String): ID do produto

**Response (200):** Success

### PUT `/produto/desativar/{id}`
Desativa produto.

**Parâmetros:**
- `id` (String): ID do produto

**Response (200):** Success

### GET `/produto/comercio/{comercioId}`
Lista todos os produtos de um comércio específico.

**Parâmetros:**
- `comercioId` (UUID): ID do comércio

**Response (200):** Array de objetos Produto

### GET `/produto/meusprodutos` 🔒
Lista produtos do comércio logado.

**Headers:** `Authorization: Bearer {token}`

**Response (200):** Array de objetos Produto

---

## 💳 AcabatePay (Pagamentos)

### POST `/acabatepay/pagar` 🔒
Cria pagamento para benefícios premium no comércio.

**Headers:** `Authorization: Bearer {token}` (Comércio autenticado)

**Request Body:**
```json
{
  "valor": 99.90,
  "beneficio": "Destaque na busca + Analytics avançado",
  "duracaoMeses": 3,
  "expiresIn": 30
}
```

**Campos:**
- `valor` (Number): Valor em reais do pagamento
- `beneficio` (String): Descrição dos benefícios adquiridos
- `duracaoMeses` (Integer): Duração em meses dos benefícios
- `expiresIn` (Integer, opcional): Tempo em minutos para expirar o PIX (padrão: 30)

**Response (200):**
```json
{
  "id": "uuid",
  "comercio": { /* objeto comercio */ },
  "valor": 99.90,
  "status": "PENDENTE",
  "transacaoId": "acabatepay_transaction_id",
  "dataPagamento": "2025-11-10T17:30:00",
  "validade": null,
  "beneficio": "Destaque na busca + Analytics avançado | URL: https://pay.acabatepay.com/xyz",
  "duracaoMeses": 3
}
```

### POST `/acabatepay/webhook`
Webhook para confirmação de pagamentos (AcabatePay API).

**Query Parameters:**
- `transacaoId` (String): ID da transação no AcabatePay
- `status` (String): Status do pagamento (CONFIRMADO, CANCELADO)

**Response (200):** `"confirmado"` ou `"status atualizado: {status}"`

### GET `/acabatepay/comercio` 🔒
Lista todos os pagamentos do comércio logado.

**Headers:** `Authorization: Bearer {token}`

**Response (200):** Array de objetos Pagamento

### GET `/acabatepay/{id}`
Consulta pagamento por ID.

**Parâmetros:**
- `id` (UUID): ID do pagamento

**Response (200):** Objeto Pagamento

### GET `/acabatepay/{id}/status-externo`
Consulta status atual na API externa do AcabatePay.

**Parâmetros:**
- `id` (UUID): ID do pagamento

**Response (200):** Objeto Pagamento com status atualizado

**Observações AcabatePay:**
- 🔗 **Integração Externa:** Sistema integrado com API oficial do AcabatePay
- 🔐 **Configuração:** Requer `acabatepay.api.key` nas configurações
- 📧 **Webhook:** Confirmações automáticas via callback URL
- 💰 **Status:** `PENDENTE` → `CONFIRMADO` → Benefícios ativados
- 🕒 **Validade:** Calculada automaticamente baseada em `duracaoMeses`
- 👤 **Customer:** Dados do comércio são usados automaticamente (nome, email, CNPJ)
- 💳 **PIX:** Valor convertido para centavos automaticamente na API
- ⏱️ **Expiração:** PIX expira em `expiresIn` minutos (padrão: 30 min)

---

## 🛒 Compras

### POST `/compra` 🔒
Cria nova compra.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "produtoId": "uuid",
  "quantidade": 2
}
```

**Response (201):**
```json
{
  "id": "uuid",
  "cliente": { /* objeto cliente */ },
  "produto": { /* objeto produto */ },
  "quantidade": 2,
  "valorTotal": 200.0,
  "dataCompra": "2025-11-05T10:30:00",
  "status": "PENDENTE"
}
```

### GET `/compra/all`
Lista todas as compras.

**Response (200):** Array de objetos Compra

### GET `/compra/{id}`
Busca compra por ID.

**Parâmetros:**
- `id` (UUID): ID da compra

**Response (200):** Objeto Compra

### GET `/compra/cliente/{clienteId}`
Lista compras por cliente.

**Parâmetros:**
- `clienteId` (UUID): ID do cliente

**Response (200):** Array de objetos Compra

### GET `/compra/produto/{produtoId}`
Lista compras por produto.

**Parâmetros:**
- `produtoId` (UUID): ID do produto

**Response (200):** Array de objetos Compra

### GET `/compra/status/{status}`
Lista compras por status.

**Parâmetros:**
- `status` (String): Status da compra (PENDENTE, CONFIRMADA, CANCELADA)

**Response (200):** Array de objetos Compra

### PUT `/compra/{id}/confirmar`
Confirma compra e debita pontos.

**Parâmetros:**
- `id` (UUID): ID da compra

**Response (200):** Objeto Compra atualizado

### PUT `/compra/{id}/cancelar`
Cancela compra.

**Parâmetros:**
- `id` (UUID): ID da compra

**Response (200):** Objeto Compra atualizado

### DELETE `/compra/{id}`
Remove compra.

**Parâmetros:**
- `id` (UUID): ID da compra

**Response (204):** No Content

---

## 🎯 Pontos

### POST `/ponto/criarponto` 🔒
Cria pontos para comerciante.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "pontos": 100
}
```

**Response (200):** String com resultado

### GET `/ponto/codigo/{codigo}` 🔒
Resgata pontos por código.

**Headers:** `Authorization: Bearer {token}`
**Parâmetros:**
- `codigo` (String): Código do ponto

**Response (200):** Quantidade de pontos (Integer)

---

## 🏠 Endereços

### POST `/endereco`
Cria novo endereço.

**Request Body:**
```json
{
  "cep": "string",
  "logradouro": "string",
  "complemento": "string",
  "bairro": "string",
  "cidade": "string",
  "numero": 123,
  "uf": "string"
}
```

**Response (200):** Objeto Endereco criado

### GET `/endereco/{id}`
Busca endereço por ID.

**Parâmetros:**
- `id` (UUID): ID do endereço

**Response (200):** Objeto Endereco

### PUT `/endereco`
Atualiza endereço.

**Request Body:** Objeto Endereco completo

**Response (200):** Success

### DELETE `/endereco/{id}`
Remove endereço.

**Parâmetros:**
- `id` (UUID): ID do endereço

**Response (200):** Success

### GET `/endereco/all`
Lista todos os endereços.

**Response (200):** Array de objetos Endereco

### POST `/endereco/cep`
Busca endereço por CEP (ViaCEP).

**Request Body:** `"12345678"` (String CEP)

**Response (200):** Objeto Endereco preenchido

---

## 🔧 Observações Gerais

### Autenticação
- Endpoints marcados com 🔒 requerem autenticação JWT
- Header: `Authorization: Bearer {token}`
- Token obtido via `/auth/login`

### Status Codes
- **200**: Success
- **201**: Created  
- **204**: No Content
- **400**: Bad Request
- **401**: Unauthorized
- **403**: Forbidden
- **404**: Not Found

### Formatos
- **UUIDs**: Formato padrão UUID v4
- **Datas**: ISO 8601 (YYYY-MM-DDTHH:mm:ss)
- **Status de Compra**: `PENDENTE`, `CONFIRMADA`, `CANCELADA`
- **Perfil de Usuário**: `1` (Cliente), `2` (Comerciante)

### Validações
- Campos obrigatórios conforme DTOs
- CNPJs devem ser únicos
- Emails devem ser únicos
- Senhas são criptografadas com BCrypt
- Pontos são calculados automaticamente nas compras

---

## 💳 AcabatePay (Pagamentos para comércios)

Serviço que permite que um comércio realize pagamentos para obter benefícios na aplicação (destaque, promoções, aumento de visibilidade, etc.).

### POST `/acabatepay/pagar` 🔒
Cria uma tentativa de pagamento para o comércio autenticado. Retorna o pagamento criado com `transacaoId` que pode ser usado para confirmação pelo gateway.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "valor": 100.0,
  "beneficio": "DESTAQUE",
  "duracaoMeses": 3
}
```

**Response (200):**
```json
{
  "id": "uuid",
  "valor": 100.0,
  "status": "PENDENTE",
  "transacaoId": "string",
  "dataPagamento": "2025-11-10T12:00:00",
  "validade": null,
  "beneficio": "DESTAQUE",
  "duracaoMeses": 3
}
```

### POST `/acabatepay/webhook`
Endpoint público (ou protegido via IP/assinatura no gateway) para receber confirmações do gateway.

**Query params:** `transacaoId` (String), `status` (String)

Exemplo: `/acabatepay/webhook?transacaoId=abc&status=CONFIRMADO`

**Response (200):** `"confirmado"` ou `"status atualizado: {status}"`

### GET `/acabatepay/comercio` 🔒
Lista pagamentos do comércio autenticado.

**Headers:** `Authorization: Bearer {token}`

**Response (200):** Array de objetos Pagamento (mesma estrutura do response acima)

### GET `/acabatepay/{id}`
Obtém detalhe de um pagamento por ID.

**Parâmetros:**
- `id` (UUID): ID do pagamento

**Response (200):** Objeto Pagamento

### Segurança e notas
- Recomenda-se validar webhooks por assinatura ou IP do gateway.
- `transacaoId` é gerado internamente e deve ser enviado ao gateway para correlacionamento.
- Ao confirmar um pagamento, o sistema define a `validade` do benefício baseado em `duracaoMeses`.
