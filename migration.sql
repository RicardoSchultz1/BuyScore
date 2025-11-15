-- Script para atualizar o banco de dados com o novo sistema de pontos por comércio
-- e adicionar a coluna código na tabela compra

-- 1. Criar a tabela cliente_ponto_comercio
CREATE TABLE IF NOT EXISTS cliente_ponto_comercio (
    id CHAR(36) NOT NULL PRIMARY KEY,
    cliente_id CHAR(36) NOT NULL,
    comercio_id CHAR(36) NOT NULL,
    pontos INT NOT NULL DEFAULT 0,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE,
    FOREIGN KEY (comercio_id) REFERENCES comercio(id) ON DELETE CASCADE,
    UNIQUE KEY unique_cliente_comercio (cliente_id, comercio_id)
);

-- 2. Adicionar a coluna codigo na tabela compra (se não existir)
ALTER TABLE compra 
ADD COLUMN IF NOT EXISTS codigo VARCHAR(10) UNIQUE;

-- 3. Migrar pontos existentes dos clientes para todos os comércios
-- (Opcional: se você quiser migrar os pontos globais existentes para todos os comércios)
-- INSERT INTO cliente_ponto_comercio (id, cliente_id, comercio_id, pontos)
-- SELECT UUID(), c.id, cm.id, c.pontos 
-- FROM cliente c 
-- CROSS JOIN comercio cm 
-- WHERE c.pontos > 0;

-- 4. Criar índices para melhorar performance
CREATE INDEX IF NOT EXISTS idx_cliente_ponto_cliente_id ON cliente_ponto_comercio(cliente_id);
CREATE INDEX IF NOT EXISTS idx_cliente_ponto_comercio_id ON cliente_ponto_comercio(comercio_id);
CREATE INDEX IF NOT EXISTS idx_compra_codigo ON compra(codigo);

-- Exemplo de como adicionar pontos para alguns clientes em comércios específicos
-- (Substitua os UUIDs pelos IDs reais do seu banco)
-- INSERT INTO cliente_ponto_comercio (id, cliente_id, comercio_id, pontos) VALUES
-- (UUID(), 'cliente-uuid-1', 'comercio-uuid-1', 100),
-- (UUID(), 'cliente-uuid-1', 'comercio-uuid-2', 50),
-- (UUID(), 'cliente-uuid-2', 'comercio-uuid-1', 200);