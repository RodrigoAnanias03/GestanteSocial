--
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

ALTER TABLE ONLY public.postagem DROP CONSTRAINT fk_usuario_postagem;
ALTER TABLE ONLY public.fotos_usuarios DROP CONSTRAINT fk_usuario_foto;
ALTER TABLE ONLY public.mensagem_usuarios DROP CONSTRAINT fk_remetente_mensagem;
ALTER TABLE ONLY public.comentario DROP CONSTRAINT fk_postagem_comentario;
ALTER TABLE ONLY public.mensagens_medicas DROP CONSTRAINT fk_mensagem_medico;
ALTER TABLE ONLY public.mensagens_medicas DROP CONSTRAINT fk_mensagem_gestante;
ALTER TABLE ONLY public.fotos_medico DROP CONSTRAINT fk_medico_foto;
ALTER TABLE ONLY public.fotos_familiar DROP CONSTRAINT fk_foto_familiar;
ALTER TABLE ONLY public.mensagem_usuarios DROP CONSTRAINT fk_destinatario_mensagem;
ALTER TABLE ONLY public.amigos_medico DROP CONSTRAINT fk_amigos_medico;
ALTER TABLE ONLY public.amigos_medico DROP CONSTRAINT fk_amigos_gestante;
ALTER TABLE ONLY public.amigos_familiar DROP CONSTRAINT fk_amigos_gestante;
ALTER TABLE ONLY public.amigos_familiar DROP CONSTRAINT fk_amigos_familiar;
ALTER TABLE ONLY public.amigos DROP CONSTRAINT fk_amigo2_amigo1;
ALTER TABLE ONLY public.amigos DROP CONSTRAINT fk_amigo1_amigo2;
ALTER TABLE ONLY public.usuario DROP CONSTRAINT pk_usuario;
ALTER TABLE ONLY public.postagem DROP CONSTRAINT pk_postagem;
ALTER TABLE ONLY public.mensagem_usuarios DROP CONSTRAINT pk_mensagens_usuario;
ALTER TABLE ONLY public.mensagens_medicas DROP CONSTRAINT pk_mensagem;
ALTER TABLE ONLY public.medico DROP CONSTRAINT pk_medico;
ALTER TABLE ONLY public.fotos_usuarios DROP CONSTRAINT pk_fotos_usuarios;
ALTER TABLE ONLY public.fotos_medico DROP CONSTRAINT pk_fotos_medico;
ALTER TABLE ONLY public.fotos_familiar DROP CONSTRAINT pk_fotos_familiar;
ALTER TABLE ONLY public.familiar DROP CONSTRAINT pk_familiar;
ALTER TABLE ONLY public.contato DROP CONSTRAINT pk_contato;
ALTER TABLE ONLY public.comentario DROP CONSTRAINT pk_comentario;
ALTER TABLE ONLY public.amigos DROP CONSTRAINT pk_amizade;
ALTER TABLE ONLY public.amigos_medico DROP CONSTRAINT pk_amigos_medico;
ALTER TABLE ONLY public.amigos_familiar DROP CONSTRAINT pk_amigos_familiar;
ALTER TABLE ONLY public.administrador DROP CONSTRAINT pk_adm;
ALTER TABLE public.usuario ALTER COLUMN id_usuario DROP DEFAULT;
ALTER TABLE public.postagem ALTER COLUMN id_postagem DROP DEFAULT;
ALTER TABLE public.mensagens_medicas ALTER COLUMN id_mensagem DROP DEFAULT;
ALTER TABLE public.mensagem_usuarios ALTER COLUMN id_mensagem DROP DEFAULT;
ALTER TABLE public.medico ALTER COLUMN id_medico DROP DEFAULT;
ALTER TABLE public.fotos_usuarios ALTER COLUMN id_fotos_usuarios DROP DEFAULT;
ALTER TABLE public.fotos_medico ALTER COLUMN id_foto_medico DROP DEFAULT;
ALTER TABLE public.fotos_familiar ALTER COLUMN id_fotos_familiar DROP DEFAULT;
ALTER TABLE public.familiar ALTER COLUMN id_familiar DROP DEFAULT;
ALTER TABLE public.contato ALTER COLUMN id_contato DROP DEFAULT;
ALTER TABLE public.comentario ALTER COLUMN id_comentario DROP DEFAULT;
ALTER TABLE public.amigos_medico ALTER COLUMN id_amizade DROP DEFAULT;
ALTER TABLE public.amigos_familiar ALTER COLUMN id_amizade DROP DEFAULT;
ALTER TABLE public.amigos ALTER COLUMN id_amigo DROP DEFAULT;
ALTER TABLE public.administrador ALTER COLUMN id_administrador DROP DEFAULT;
DROP SEQUENCE public.usuario_id_usuario_seq;
DROP TABLE public.usuario;
DROP SEQUENCE public.seq_img_post;
DROP SEQUENCE public.seq_img_perfil_usuario;
DROP SEQUENCE public.seq_img_perfil_medico;
DROP SEQUENCE public.seq_img_perfil_familiar;
DROP SEQUENCE public.seq_arquivos_medicos;
DROP SEQUENCE public.postagem_id_postagem_seq;
DROP TABLE public.postagem;
DROP SEQUENCE public.mensagens_medicas_id_mensagem_seq;
DROP TABLE public.mensagens_medicas;
DROP SEQUENCE public.mensagem_usuarios_id_mensagem_seq;
DROP TABLE public.mensagem_usuarios;
DROP SEQUENCE public.medico_id_medico_seq;
DROP TABLE public.medico;
DROP SEQUENCE public.fotos_usuarios_id_fotos_usuarios_seq;
DROP TABLE public.fotos_usuarios;
DROP SEQUENCE public.fotos_medico_id_foto_medico_seq;
DROP TABLE public.fotos_medico;
DROP SEQUENCE public.fotos_familiar_id_fotos_familiar_seq;
DROP TABLE public.fotos_familiar;
DROP SEQUENCE public.familiar_id_familiar_seq;
DROP TABLE public.familiar;
DROP SEQUENCE public.contato_id_contato_seq;
DROP TABLE public.contato;
DROP SEQUENCE public.comentario_id_comentario_seq;
DROP TABLE public.comentario;
DROP SEQUENCE public.amigos_medico_id_amizade_seq;
DROP TABLE public.amigos_medico;
DROP SEQUENCE public.amigos_id_amigo_seq;
DROP SEQUENCE public.amigos_familiar_id_amizade_seq;
DROP TABLE public.amigos_familiar;
DROP TABLE public.amigos;
DROP SEQUENCE public.administrador_id_administrador_seq;
DROP TABLE public.administrador;
DROP FUNCTION public.retira_acentuacao(p_texto text);
DROP EXTENSION plpgsql;
DROP SCHEMA public;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: retira_acentuacao(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION retira_acentuacao(p_texto text) RETURNS text
    LANGUAGE sql
    AS $_$  
 Select translate($1,  
 'áàâãäåaaaÁÂÃÄÅAAAÀéèêëeeeeeEEEÉEEÈìíîïìiiiÌÍÎÏÌIIIóôõöoooòÒÓÔÕÖOOOùúûüuuuuÙÚÛÜUUUUçÇñÑýÝ',  
 'aaaaaaaaaAAAAAAAAAeeeeeeeeeEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnNyY'   
  );  
 $_$;


ALTER FUNCTION public.retira_acentuacao(p_texto text) OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: administrador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE administrador (
    id_administrador integer NOT NULL,
    senha character varying(50)
);


ALTER TABLE administrador OWNER TO postgres;

--
-- Name: administrador_id_administrador_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE administrador_id_administrador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE administrador_id_administrador_seq OWNER TO postgres;

--
-- Name: administrador_id_administrador_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE administrador_id_administrador_seq OWNED BY administrador.id_administrador;


--
-- Name: amigos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE amigos (
    id_amigo integer NOT NULL,
    id_pessoa1 integer,
    id_pessoa2 integer,
    status_amizade boolean
);


ALTER TABLE amigos OWNER TO postgres;

--
-- Name: amigos_familiar; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE amigos_familiar (
    id_amizade integer NOT NULL,
    status_amizade boolean,
    autor_amizade character varying(20),
    id_gestante integer,
    id_familiar integer
);


ALTER TABLE amigos_familiar OWNER TO postgres;

--
-- Name: COLUMN amigos_familiar.id_amizade; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN amigos_familiar.id_amizade IS '
';


--
-- Name: amigos_familiar_id_amizade_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE amigos_familiar_id_amizade_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE amigos_familiar_id_amizade_seq OWNER TO postgres;

--
-- Name: amigos_familiar_id_amizade_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE amigos_familiar_id_amizade_seq OWNED BY amigos_familiar.id_amizade;


--
-- Name: amigos_id_amigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE amigos_id_amigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE amigos_id_amigo_seq OWNER TO postgres;

--
-- Name: amigos_id_amigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE amigos_id_amigo_seq OWNED BY amigos.id_amigo;


--
-- Name: amigos_medico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE amigos_medico (
    id_amizade integer NOT NULL,
    status_amizade boolean,
    autor_amizade character varying(20),
    id_gestante integer,
    id_medico integer
);


ALTER TABLE amigos_medico OWNER TO postgres;

--
-- Name: amigos_medico_id_amizade_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE amigos_medico_id_amizade_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE amigos_medico_id_amizade_seq OWNER TO postgres;

--
-- Name: amigos_medico_id_amizade_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE amigos_medico_id_amizade_seq OWNED BY amigos_medico.id_amizade;


--
-- Name: comentario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE comentario (
    id_comentario integer NOT NULL,
    id_postagem integer,
    id_usuario integer,
    comentario character varying(5000),
    quem_comenta character varying(20),
    his_name_is_seth_rich character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE comentario OWNER TO postgres;

--
-- Name: comentario_id_comentario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE comentario_id_comentario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE comentario_id_comentario_seq OWNER TO postgres;

--
-- Name: comentario_id_comentario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE comentario_id_comentario_seq OWNED BY comentario.id_comentario;


--
-- Name: contato; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contato (
    id_contato integer NOT NULL,
    nome character varying(50),
    sobrenome character varying(50),
    cpf character varying(20),
    assunto character varying(40),
    mensagem character varying(1000),
    status boolean,
    email character varying(50),
    his_name_is_seth_rich character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE contato OWNER TO postgres;

--
-- Name: contato_id_contato_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contato_id_contato_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contato_id_contato_seq OWNER TO postgres;

--
-- Name: contato_id_contato_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contato_id_contato_seq OWNED BY contato.id_contato;


--
-- Name: familiar; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE familiar (
    id_familiar integer NOT NULL,
    nome_familiar character varying(50),
    sobrenome_familiar character varying(50),
    cpf_familiar character(14),
    email_familiar character varying(50),
    nascimento_familiar date,
    cidade_familiar character varying(60),
    estado_familiar character varying(5),
    usuario_familiar character varying(50),
    senha_familiar character varying(50),
    foto_familiar character varying(99999),
    situacao_familiar boolean
);


ALTER TABLE familiar OWNER TO postgres;

--
-- Name: familiar_id_familiar_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE familiar_id_familiar_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE familiar_id_familiar_seq OWNER TO postgres;

--
-- Name: familiar_id_familiar_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE familiar_id_familiar_seq OWNED BY familiar.id_familiar;


--
-- Name: fotos_familiar; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fotos_familiar (
    id_fotos_familiar integer NOT NULL,
    id_familiar integer,
    foto_familiar character varying(999999),
    his_name_is_seth_rich character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE fotos_familiar OWNER TO postgres;

--
-- Name: fotos_familiar_id_fotos_familiar_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fotos_familiar_id_fotos_familiar_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fotos_familiar_id_fotos_familiar_seq OWNER TO postgres;

--
-- Name: fotos_familiar_id_fotos_familiar_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fotos_familiar_id_fotos_familiar_seq OWNED BY fotos_familiar.id_fotos_familiar;


--
-- Name: fotos_medico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fotos_medico (
    id_foto_medico integer NOT NULL,
    id_medico integer,
    foto character varying(99999),
    his_name_is_seth_rich character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE fotos_medico OWNER TO postgres;

--
-- Name: fotos_medico_id_foto_medico_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fotos_medico_id_foto_medico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fotos_medico_id_foto_medico_seq OWNER TO postgres;

--
-- Name: fotos_medico_id_foto_medico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fotos_medico_id_foto_medico_seq OWNED BY fotos_medico.id_foto_medico;


--
-- Name: fotos_usuarios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fotos_usuarios (
    id_fotos_usuarios integer NOT NULL,
    id_usuario integer,
    url_img character varying(9999999),
    his_name_is_seth_rich character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE fotos_usuarios OWNER TO postgres;

--
-- Name: fotos_usuarios_id_fotos_usuarios_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fotos_usuarios_id_fotos_usuarios_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fotos_usuarios_id_fotos_usuarios_seq OWNER TO postgres;

--
-- Name: fotos_usuarios_id_fotos_usuarios_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fotos_usuarios_id_fotos_usuarios_seq OWNED BY fotos_usuarios.id_fotos_usuarios;


--
-- Name: medico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE medico (
    id_medico integer NOT NULL,
    nome_medico character varying(50),
    sobrenome_medico character varying(50),
    cpf_medico character(14),
    telefone_medico character varying(20),
    profissao_medico character varying(50),
    documento_medico character varying(100),
    email_medico character varying(100),
    datanascimento_medico date,
    cidade_medico character varying(100),
    estado_medico character(2),
    rua_medico character varying(50),
    numero_medico character varying(10),
    bairro_medico character varying(50),
    cep_medico character(9),
    usuario_medico character varying(50),
    senha_medico character varying(50),
    status_medico boolean,
    foto_medico character varying(999999),
    his_name_is_seth_rich character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE medico OWNER TO postgres;

--
-- Name: medico_id_medico_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE medico_id_medico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE medico_id_medico_seq OWNER TO postgres;

--
-- Name: medico_id_medico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE medico_id_medico_seq OWNED BY medico.id_medico;


--
-- Name: mensagem_usuarios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mensagem_usuarios (
    id_mensagem integer NOT NULL,
    mensagem character varying(5000),
    id_remetente integer,
    id_destinatario integer,
    data_mensagem date,
    status_mensagem boolean,
    his_name_is_seth_rich character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE mensagem_usuarios OWNER TO postgres;

--
-- Name: mensagem_usuarios_id_mensagem_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE mensagem_usuarios_id_mensagem_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mensagem_usuarios_id_mensagem_seq OWNER TO postgres;

--
-- Name: mensagem_usuarios_id_mensagem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE mensagem_usuarios_id_mensagem_seq OWNED BY mensagem_usuarios.id_mensagem;


--
-- Name: mensagens_medicas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mensagens_medicas (
    id_mensagem integer NOT NULL,
    mensagem character varying(9999),
    status_mensagem boolean,
    autor character varying(50),
    data date,
    id_medico integer,
    id_gestante integer,
    arquivo_medico character varying(999999),
    his_name_is_seth_rich character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE mensagens_medicas OWNER TO postgres;

--
-- Name: mensagens_medicas_id_mensagem_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE mensagens_medicas_id_mensagem_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mensagens_medicas_id_mensagem_seq OWNER TO postgres;

--
-- Name: mensagens_medicas_id_mensagem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE mensagens_medicas_id_mensagem_seq OWNED BY mensagens_medicas.id_mensagem;


--
-- Name: postagem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE postagem (
    id_postagem integer NOT NULL,
    mensagem character varying(5000),
    url_imagem character varying(500),
    id_usuario integer,
    data date
);


ALTER TABLE postagem OWNER TO postgres;

--
-- Name: postagem_id_postagem_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE postagem_id_postagem_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE postagem_id_postagem_seq OWNER TO postgres;

--
-- Name: postagem_id_postagem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE postagem_id_postagem_seq OWNED BY postagem.id_postagem;


--
-- Name: seq_arquivos_medicos; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seq_arquivos_medicos
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_arquivos_medicos OWNER TO postgres;

--
-- Name: seq_img_perfil_familiar; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seq_img_perfil_familiar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_img_perfil_familiar OWNER TO postgres;

--
-- Name: seq_img_perfil_medico; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seq_img_perfil_medico
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_img_perfil_medico OWNER TO postgres;

--
-- Name: seq_img_perfil_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seq_img_perfil_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_img_perfil_usuario OWNER TO postgres;

--
-- Name: seq_img_post; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seq_img_post
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_img_post OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    id_usuario integer NOT NULL,
    nome_usuario character varying(40),
    sobrenome_usuario character varying(50),
    email_usuario character varying(50),
    senha_usuario character varying(50),
    login_usuario character varying(50),
    datanascimento_usuario date,
    situacao boolean,
    url_img character varying(100),
    cidade_usuario character varying(50),
    estado_usuario character(2),
    rua_usuario character varying(50),
    numero_usuario character varying(15),
    cep_usuario character(9),
    bairro_usuario character varying(50),
    telefone_usuario character varying(40),
    inicio_gestacao date,
    cpf_usuario character(14),
    his_name_is_seth_rich character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE usuario OWNER TO postgres;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_id_usuario_seq OWNER TO postgres;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_id_usuario_seq OWNED BY usuario.id_usuario;


--
-- Name: id_administrador; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY administrador ALTER COLUMN id_administrador SET DEFAULT nextval('administrador_id_administrador_seq'::regclass);


--
-- Name: id_amigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amigos ALTER COLUMN id_amigo SET DEFAULT nextval('amigos_id_amigo_seq'::regclass);


--
-- Name: id_amizade; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amigos_familiar ALTER COLUMN id_amizade SET DEFAULT nextval('amigos_familiar_id_amizade_seq'::regclass);


--
-- Name: id_amizade; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amigos_medico ALTER COLUMN id_amizade SET DEFAULT nextval('amigos_medico_id_amizade_seq'::regclass);


--
-- Name: id_comentario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comentario ALTER COLUMN id_comentario SET DEFAULT nextval('comentario_id_comentario_seq'::regclass);


--
-- Name: id_contato; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contato ALTER COLUMN id_contato SET DEFAULT nextval('contato_id_contato_seq'::regclass);


--
-- Name: id_familiar; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY familiar ALTER COLUMN id_familiar SET DEFAULT nextval('familiar_id_familiar_seq'::regclass);


--
-- Name: id_fotos_familiar; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fotos_familiar ALTER COLUMN id_fotos_familiar SET DEFAULT nextval('fotos_familiar_id_fotos_familiar_seq'::regclass);


--
-- Name: id_foto_medico; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fotos_medico ALTER COLUMN id_foto_medico SET DEFAULT nextval('fotos_medico_id_foto_medico_seq'::regclass);


--
-- Name: id_fotos_usuarios; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fotos_usuarios ALTER COLUMN id_fotos_usuarios SET DEFAULT nextval('fotos_usuarios_id_fotos_usuarios_seq'::regclass);


--
-- Name: id_medico; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medico ALTER COLUMN id_medico SET DEFAULT nextval('medico_id_medico_seq'::regclass);


--
-- Name: id_mensagem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem_usuarios ALTER COLUMN id_mensagem SET DEFAULT nextval('mensagem_usuarios_id_mensagem_seq'::regclass);


--
-- Name: id_mensagem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagens_medicas ALTER COLUMN id_mensagem SET DEFAULT nextval('mensagens_medicas_id_mensagem_seq'::regclass);


--
-- Name: id_postagem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postagem ALTER COLUMN id_postagem SET DEFAULT nextval('postagem_id_postagem_seq'::regclass);


--
-- Name: id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN id_usuario SET DEFAULT nextval('usuario_id_usuario_seq'::regclass);


--
-- Data for Name: administrador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY administrador (id_administrador, senha) FROM stdin;
\.
COPY administrador (id_administrador, senha) FROM '$$PATH$$/2165.dat';

--
-- Name: administrador_id_administrador_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('administrador_id_administrador_seq', 1, true);


--
-- Data for Name: amigos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY amigos (id_amigo, id_pessoa1, id_pessoa2, status_amizade) FROM stdin;
\.
COPY amigos (id_amigo, id_pessoa1, id_pessoa2, status_amizade) FROM '$$PATH$$/2167.dat';

--
-- Data for Name: amigos_familiar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY amigos_familiar (id_amizade, status_amizade, autor_amizade, id_gestante, id_familiar) FROM stdin;
\.
COPY amigos_familiar (id_amizade, status_amizade, autor_amizade, id_gestante, id_familiar) FROM '$$PATH$$/2168.dat';

--
-- Name: amigos_familiar_id_amizade_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('amigos_familiar_id_amizade_seq', 74, true);


--
-- Name: amigos_id_amigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('amigos_id_amigo_seq', 72, true);


--
-- Data for Name: amigos_medico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY amigos_medico (id_amizade, status_amizade, autor_amizade, id_gestante, id_medico) FROM stdin;
\.
COPY amigos_medico (id_amizade, status_amizade, autor_amizade, id_gestante, id_medico) FROM '$$PATH$$/2171.dat';

--
-- Name: amigos_medico_id_amizade_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('amigos_medico_id_amizade_seq', 21, true);


--
-- Data for Name: comentario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY comentario (id_comentario, id_postagem, id_usuario, comentario, quem_comenta, his_name_is_seth_rich) FROM stdin;
\.
COPY comentario (id_comentario, id_postagem, id_usuario, comentario, quem_comenta, his_name_is_seth_rich) FROM '$$PATH$$/2173.dat';

--
-- Name: comentario_id_comentario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('comentario_id_comentario_seq', 200, true);


--
-- Data for Name: contato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contato (id_contato, nome, sobrenome, cpf, assunto, mensagem, status, email, his_name_is_seth_rich) FROM stdin;
\.
COPY contato (id_contato, nome, sobrenome, cpf, assunto, mensagem, status, email, his_name_is_seth_rich) FROM '$$PATH$$/2175.dat';

--
-- Name: contato_id_contato_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contato_id_contato_seq', 35, true);


--
-- Data for Name: familiar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY familiar (id_familiar, nome_familiar, sobrenome_familiar, cpf_familiar, email_familiar, nascimento_familiar, cidade_familiar, estado_familiar, usuario_familiar, senha_familiar, foto_familiar, situacao_familiar) FROM stdin;
\.
COPY familiar (id_familiar, nome_familiar, sobrenome_familiar, cpf_familiar, email_familiar, nascimento_familiar, cidade_familiar, estado_familiar, usuario_familiar, senha_familiar, foto_familiar, situacao_familiar) FROM '$$PATH$$/2177.dat';

--
-- Name: familiar_id_familiar_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('familiar_id_familiar_seq', 22, true);


--
-- Data for Name: fotos_familiar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fotos_familiar (id_fotos_familiar, id_familiar, foto_familiar, his_name_is_seth_rich) FROM stdin;
\.
COPY fotos_familiar (id_fotos_familiar, id_familiar, foto_familiar, his_name_is_seth_rich) FROM '$$PATH$$/2179.dat';

--
-- Name: fotos_familiar_id_fotos_familiar_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fotos_familiar_id_fotos_familiar_seq', 55, true);


--
-- Data for Name: fotos_medico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fotos_medico (id_foto_medico, id_medico, foto, his_name_is_seth_rich) FROM stdin;
\.
COPY fotos_medico (id_foto_medico, id_medico, foto, his_name_is_seth_rich) FROM '$$PATH$$/2181.dat';

--
-- Name: fotos_medico_id_foto_medico_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fotos_medico_id_foto_medico_seq', 21, true);


--
-- Data for Name: fotos_usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fotos_usuarios (id_fotos_usuarios, id_usuario, url_img, his_name_is_seth_rich) FROM stdin;
\.
COPY fotos_usuarios (id_fotos_usuarios, id_usuario, url_img, his_name_is_seth_rich) FROM '$$PATH$$/2183.dat';

--
-- Name: fotos_usuarios_id_fotos_usuarios_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fotos_usuarios_id_fotos_usuarios_seq', 93, true);


--
-- Data for Name: medico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY medico (id_medico, nome_medico, sobrenome_medico, cpf_medico, telefone_medico, profissao_medico, documento_medico, email_medico, datanascimento_medico, cidade_medico, estado_medico, rua_medico, numero_medico, bairro_medico, cep_medico, usuario_medico, senha_medico, status_medico, foto_medico, his_name_is_seth_rich) FROM stdin;
\.
COPY medico (id_medico, nome_medico, sobrenome_medico, cpf_medico, telefone_medico, profissao_medico, documento_medico, email_medico, datanascimento_medico, cidade_medico, estado_medico, rua_medico, numero_medico, bairro_medico, cep_medico, usuario_medico, senha_medico, status_medico, foto_medico, his_name_is_seth_rich) FROM '$$PATH$$/2185.dat';

--
-- Name: medico_id_medico_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('medico_id_medico_seq', 12, true);


--
-- Data for Name: mensagem_usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY mensagem_usuarios (id_mensagem, mensagem, id_remetente, id_destinatario, data_mensagem, status_mensagem, his_name_is_seth_rich) FROM stdin;
\.
COPY mensagem_usuarios (id_mensagem, mensagem, id_remetente, id_destinatario, data_mensagem, status_mensagem, his_name_is_seth_rich) FROM '$$PATH$$/2187.dat';

--
-- Name: mensagem_usuarios_id_mensagem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('mensagem_usuarios_id_mensagem_seq', 73, true);


--
-- Data for Name: mensagens_medicas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY mensagens_medicas (id_mensagem, mensagem, status_mensagem, autor, data, id_medico, id_gestante, arquivo_medico, his_name_is_seth_rich) FROM stdin;
\.
COPY mensagens_medicas (id_mensagem, mensagem, status_mensagem, autor, data, id_medico, id_gestante, arquivo_medico, his_name_is_seth_rich) FROM '$$PATH$$/2189.dat';

--
-- Name: mensagens_medicas_id_mensagem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('mensagens_medicas_id_mensagem_seq', 51, true);


--
-- Data for Name: postagem; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY postagem (id_postagem, mensagem, url_imagem, id_usuario, data) FROM stdin;
\.
COPY postagem (id_postagem, mensagem, url_imagem, id_usuario, data) FROM '$$PATH$$/2191.dat';

--
-- Name: postagem_id_postagem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('postagem_id_postagem_seq', 662, true);


--
-- Name: seq_arquivos_medicos; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seq_arquivos_medicos', 12, true);


--
-- Name: seq_img_perfil_familiar; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seq_img_perfil_familiar', 63, true);


--
-- Name: seq_img_perfil_medico; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seq_img_perfil_medico', 15, true);


--
-- Name: seq_img_perfil_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seq_img_perfil_usuario', 124, true);


--
-- Name: seq_img_post; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seq_img_post', 645, true);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (id_usuario, nome_usuario, sobrenome_usuario, email_usuario, senha_usuario, login_usuario, datanascimento_usuario, situacao, url_img, cidade_usuario, estado_usuario, rua_usuario, numero_usuario, cep_usuario, bairro_usuario, telefone_usuario, inicio_gestacao, cpf_usuario, his_name_is_seth_rich) FROM stdin;
\.
COPY usuario (id_usuario, nome_usuario, sobrenome_usuario, email_usuario, senha_usuario, login_usuario, datanascimento_usuario, situacao, url_img, cidade_usuario, estado_usuario, rua_usuario, numero_usuario, cep_usuario, bairro_usuario, telefone_usuario, inicio_gestacao, cpf_usuario, his_name_is_seth_rich) FROM '$$PATH$$/2198.dat';

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_usuario_seq', 71, true);


--
-- Name: pk_adm; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY administrador
    ADD CONSTRAINT pk_adm PRIMARY KEY (id_administrador);


--
-- Name: pk_amigos_familiar; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY amigos_familiar
    ADD CONSTRAINT pk_amigos_familiar PRIMARY KEY (id_amizade);


--
-- Name: pk_amigos_medico; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY amigos_medico
    ADD CONSTRAINT pk_amigos_medico PRIMARY KEY (id_amizade);


--
-- Name: pk_amizade; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY amigos
    ADD CONSTRAINT pk_amizade PRIMARY KEY (id_amigo);


--
-- Name: pk_comentario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY comentario
    ADD CONSTRAINT pk_comentario PRIMARY KEY (id_comentario);


--
-- Name: pk_contato; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contato
    ADD CONSTRAINT pk_contato PRIMARY KEY (id_contato);


--
-- Name: pk_familiar; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY familiar
    ADD CONSTRAINT pk_familiar PRIMARY KEY (id_familiar);


--
-- Name: pk_fotos_familiar; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fotos_familiar
    ADD CONSTRAINT pk_fotos_familiar PRIMARY KEY (id_fotos_familiar);


--
-- Name: pk_fotos_medico; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fotos_medico
    ADD CONSTRAINT pk_fotos_medico PRIMARY KEY (id_foto_medico);


--
-- Name: pk_fotos_usuarios; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fotos_usuarios
    ADD CONSTRAINT pk_fotos_usuarios PRIMARY KEY (id_fotos_usuarios);


--
-- Name: pk_medico; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY medico
    ADD CONSTRAINT pk_medico PRIMARY KEY (id_medico);


--
-- Name: pk_mensagem; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mensagens_medicas
    ADD CONSTRAINT pk_mensagem PRIMARY KEY (id_mensagem);


--
-- Name: pk_mensagens_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mensagem_usuarios
    ADD CONSTRAINT pk_mensagens_usuario PRIMARY KEY (id_mensagem);


--
-- Name: pk_postagem; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY postagem
    ADD CONSTRAINT pk_postagem PRIMARY KEY (id_postagem);


--
-- Name: pk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (id_usuario);


--
-- Name: fk_amigo1_amigo2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amigos
    ADD CONSTRAINT fk_amigo1_amigo2 FOREIGN KEY (id_pessoa1) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: fk_amigo2_amigo1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amigos
    ADD CONSTRAINT fk_amigo2_amigo1 FOREIGN KEY (id_pessoa2) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: fk_amigos_familiar; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amigos_familiar
    ADD CONSTRAINT fk_amigos_familiar FOREIGN KEY (id_familiar) REFERENCES familiar(id_familiar) ON DELETE CASCADE;


--
-- Name: fk_amigos_gestante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amigos_familiar
    ADD CONSTRAINT fk_amigos_gestante FOREIGN KEY (id_gestante) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: fk_amigos_gestante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amigos_medico
    ADD CONSTRAINT fk_amigos_gestante FOREIGN KEY (id_gestante) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: fk_amigos_medico; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amigos_medico
    ADD CONSTRAINT fk_amigos_medico FOREIGN KEY (id_medico) REFERENCES medico(id_medico) ON DELETE CASCADE;


--
-- Name: fk_destinatario_mensagem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem_usuarios
    ADD CONSTRAINT fk_destinatario_mensagem FOREIGN KEY (id_destinatario) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: fk_foto_familiar; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fotos_familiar
    ADD CONSTRAINT fk_foto_familiar FOREIGN KEY (id_familiar) REFERENCES familiar(id_familiar) ON DELETE CASCADE;


--
-- Name: fk_medico_foto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fotos_medico
    ADD CONSTRAINT fk_medico_foto FOREIGN KEY (id_medico) REFERENCES medico(id_medico) ON DELETE CASCADE;


--
-- Name: fk_mensagem_gestante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagens_medicas
    ADD CONSTRAINT fk_mensagem_gestante FOREIGN KEY (id_gestante) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: fk_mensagem_medico; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagens_medicas
    ADD CONSTRAINT fk_mensagem_medico FOREIGN KEY (id_medico) REFERENCES medico(id_medico) ON DELETE CASCADE;


--
-- Name: fk_postagem_comentario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comentario
    ADD CONSTRAINT fk_postagem_comentario FOREIGN KEY (id_postagem) REFERENCES postagem(id_postagem) ON DELETE CASCADE;


--
-- Name: fk_remetente_mensagem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem_usuarios
    ADD CONSTRAINT fk_remetente_mensagem FOREIGN KEY (id_remetente) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: fk_usuario_foto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fotos_usuarios
    ADD CONSTRAINT fk_usuario_foto FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: fk_usuario_postagem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postagem
    ADD CONSTRAINT fk_usuario_postagem FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

