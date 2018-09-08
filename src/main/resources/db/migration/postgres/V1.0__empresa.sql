CREATE TABLE empresa
(
  id bigserial NOT NULL,
  cnpj character varying(255) NOT NULL,
  data_atualizacao timestamp without time zone NOT NULL,
  data_criacao timestamp without time zone NOT NULL,
  razao_social character varying(255) NOT NULL,
  CONSTRAINT empresa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE funcionario
(
  id bigserial NOT NULL,
  nome character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  senha character varying(255) NOT NULL,
   cpf character varying(255) NOT NULL,
   valor_hora   
)
