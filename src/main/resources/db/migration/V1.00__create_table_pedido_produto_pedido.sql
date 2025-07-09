CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    id_cliente UUID NOT NULL,
    status VARCHAR(30) NOT NULL,
    id_pagamento BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT NOW(),
    atualizado_em TIMESTAMP NOT NULL DEFAULT NOW(),
    valor_total NUMERIC(10,2)
);


CREATE TABLE pedido_produto (
    id SERIAL PRIMARY KEY,
    id_pedido INTEGER NOT NULL REFERENCES pedido(id) ON DELETE CASCADE,
    id_produto INTEGER NOT NULL,
    quantidade INTEGER NOT NULL
);


CREATE INDEX idx_pedido_id_cliente ON pedido(id_cliente);
CREATE INDEX idx_pedido_produto_id ON pedido_produto(id_pedido);
CREATE INDEX idx_pedido_id_produto ON pedido_produto(id_produto);


