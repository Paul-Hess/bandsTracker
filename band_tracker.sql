--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bands; Type: TABLE; Schema: public; Owner: home; Tablespace: 
--

CREATE TABLE bands (
    id integer NOT NULL,
    band_name character varying NOT NULL,
    band_genre character varying
);


ALTER TABLE bands OWNER TO home;

--
-- Name: bands_id_seq; Type: SEQUENCE; Schema: public; Owner: home
--

CREATE SEQUENCE bands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_id_seq OWNER TO home;

--
-- Name: bands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: home
--

ALTER SEQUENCE bands_id_seq OWNED BY bands.id;


--
-- Name: bands_venues; Type: TABLE; Schema: public; Owner: home; Tablespace: 
--

CREATE TABLE bands_venues (
    id integer NOT NULL,
    band_id integer NOT NULL,
    venue_id integer NOT NULL
);


ALTER TABLE bands_venues OWNER TO home;

--
-- Name: bands_venues_id_seq; Type: SEQUENCE; Schema: public; Owner: home
--

CREATE SEQUENCE bands_venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_venues_id_seq OWNER TO home;

--
-- Name: bands_venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: home
--

ALTER SEQUENCE bands_venues_id_seq OWNED BY bands_venues.id;


--
-- Name: venues; Type: TABLE; Schema: public; Owner: home; Tablespace: 
--

CREATE TABLE venues (
    id integer NOT NULL,
    venue_name character varying NOT NULL,
    location character varying
);


ALTER TABLE venues OWNER TO home;

--
-- Name: venues_id_seq; Type: SEQUENCE; Schema: public; Owner: home
--

CREATE SEQUENCE venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE venues_id_seq OWNER TO home;

--
-- Name: venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: home
--

ALTER SEQUENCE venues_id_seq OWNED BY venues.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: home
--

ALTER TABLE ONLY bands ALTER COLUMN id SET DEFAULT nextval('bands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: home
--

ALTER TABLE ONLY bands_venues ALTER COLUMN id SET DEFAULT nextval('bands_venues_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: home
--

ALTER TABLE ONLY venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq'::regclass);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: home
--

COPY bands (id, band_name, band_genre) FROM stdin;
4	agalloch	atmospheric black metal
5	fka twigs	trip hop n b
6	ultra bide	expiremental post rock 
7	mother mother 	pop
8		
9		
\.


--
-- Name: bands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: home
--

SELECT pg_catalog.setval('bands_id_seq', 9, true);


--
-- Data for Name: bands_venues; Type: TABLE DATA; Schema: public; Owner: home
--

COPY bands_venues (id, band_id, venue_id) FROM stdin;
\.


--
-- Name: bands_venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: home
--

SELECT pg_catalog.setval('bands_venues_id_seq', 1, true);


--
-- Data for Name: venues; Type: TABLE DATA; Schema: public; Owner: home
--

COPY venues (id, venue_name, location) FROM stdin;
1	test venue	portland
2	test venue	por
3	heebgbs	new york
4	teh roseland	portland
5	crystal ballroom	portland
\.


--
-- Name: venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: home
--

SELECT pg_catalog.setval('venues_id_seq', 5, true);


--
-- Name: bands_pkey; Type: CONSTRAINT; Schema: public; Owner: home; Tablespace: 
--

ALTER TABLE ONLY bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);


--
-- Name: bands_venues_pkey; Type: CONSTRAINT; Schema: public; Owner: home; Tablespace: 
--

ALTER TABLE ONLY bands_venues
    ADD CONSTRAINT bands_venues_pkey PRIMARY KEY (id);


--
-- Name: venues_pkey; Type: CONSTRAINT; Schema: public; Owner: home; Tablespace: 
--

ALTER TABLE ONLY venues
    ADD CONSTRAINT venues_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: home
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM home;
GRANT ALL ON SCHEMA public TO home;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

