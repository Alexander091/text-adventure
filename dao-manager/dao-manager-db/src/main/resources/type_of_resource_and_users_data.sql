--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2016-02-22 13:51:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 183 (class 1259 OID 17762)
-- Name: type_of_resource; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE type_of_resource (
    id bigint NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE type_of_resource OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 17775)
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_role (
    id bigint NOT NULL,
    name character varying(200) NOT NULL
);


ALTER TABLE user_role OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 17767)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    id bigint NOT NULL,
    email character varying(200) NOT NULL,
    log_name character varying(100) NOT NULL,
    name character varying(100),
    password character varying(200) NOT NULL,
    surname character varying(200),
    user_role bigint
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 2044 (class 0 OID 17762)
-- Dependencies: 183
-- Data for Name: type_of_resource; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO type_of_resource (id, name) VALUES (1, 'image');


--
-- TOC entry 2046 (class 0 OID 17775)
-- Dependencies: 185
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_role (id, name) VALUES (0, 'admin');
INSERT INTO user_role (id, name) VALUES (1, 'user');


--
-- TOC entry 2045 (class 0 OID 17767)
-- Dependencies: 184
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users (id, email, log_name, name, password, surname, user_role) VALUES (0, 'admin@admin.org', 'admin', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'admin', 0);
INSERT INTO users (id, email, log_name, name, password, surname, user_role) VALUES (1, 'user@user.org', 'user', 'user', 'BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps=', 'user', 1);
INSERT INTO users (id, email, log_name, name, password, surname, user_role) VALUES (2, 'admin2@admin.org', 'admin2', 'admin2', 'pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=', '123', 0);
INSERT INTO users (id, email, log_name, name, password, surname, user_role) VALUES (3, 'super@admin.org', 'al', 'al', 'XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=', 'password', 0);


--
-- TOC entry 1927 (class 2606 OID 17766)
-- Name: type_of_resource_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY type_of_resource
    ADD CONSTRAINT type_of_resource_pkey PRIMARY KEY (id);


--
-- TOC entry 1929 (class 2606 OID 17774)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 1931 (class 2606 OID 17779)
-- Name: user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);


--
-- TOC entry 1933 (class 2606 OID 17985)
-- Name: fk9i2me7h8o3hgco150rkrgtw0h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk9i2me7h8o3hgco150rkrgtw0h FOREIGN KEY (user_role) REFERENCES user_role(id);


--
-- TOC entry 1934 (class 2606 OID 18071)
-- Name: fkfmrml2a622f39ylokwcx9oi36; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fkfmrml2a622f39ylokwcx9oi36 FOREIGN KEY (user_role) REFERENCES user_role(id);


--
-- TOC entry 1932 (class 2606 OID 17880)
-- Name: user_user_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_user_role_fkey FOREIGN KEY (user_role) REFERENCES user_role(id);


-- Completed on 2016-02-22 13:51:39

--
-- PostgreSQL database dump complete
--

