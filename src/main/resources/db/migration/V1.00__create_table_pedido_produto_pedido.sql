CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    id_cliente UUID NOT NULL,
    status VARCHAR(30) NOT NULL,
    id_pagamento BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT NOW(),
    atualizado_em TIMESTAMP NOT NULL DEFAULT NOW()
);


CREATE TABLE produto_pedido (
    id SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL REFERENCES pedido(id) ON DELETE CASCADE,
    sku_produto VARCHAR(100) NOT NULL,
    quantidade INTEGER NOT NULL
);


CREATE INDEX idx_pedido_id_cliente ON pedido(id_cliente);
CREATE INDEX idx_produto_pedido_pedido_id ON produto_pedido(pedido_id);
CREATE INDEX idx_produto_pedido_sku_produto ON produto_pedido(sku_produto);


