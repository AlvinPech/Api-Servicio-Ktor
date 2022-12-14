PGDMP     $    :                z            ssv2    15.1    15.1 <    W           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            X           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            Y           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            Z           1262    16554    ssv2    DATABASE     x   CREATE DATABASE ssv2 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Mexico.1252';
    DROP DATABASE ssv2;
                postgres    false            ?            1259    16555    sequence_for_alpha_numeric_2    SEQUENCE     ?   CREATE SEQUENCE public.sequence_for_alpha_numeric_2
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
 3   DROP SEQUENCE public.sequence_for_alpha_numeric_2;
       public          postgres    false            ?            1259    16556 
   asignatura    TABLE     ?   CREATE TABLE public.asignatura (
    idasignatura text DEFAULT nextval('public.sequence_for_alpha_numeric_2'::regclass) NOT NULL,
    nombre text,
    descripcion text,
    idduenio text
);
    DROP TABLE public.asignatura;
       public         heap    postgres    false    214            ?            1259    16562    sequence_for_alpha_numeric_3    SEQUENCE     ?   CREATE SEQUENCE public.sequence_for_alpha_numeric_3
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
 3   DROP SEQUENCE public.sequence_for_alpha_numeric_3;
       public          postgres    false            ?            1259    16563    examen    TABLE     ?   CREATE TABLE public.examen (
    idexamen text DEFAULT nextval('public.sequence_for_alpha_numeric_3'::regclass) NOT NULL,
    nombre text,
    descripcion text,
    idasignatura text,
    tiempo integer
);
    DROP TABLE public.examen;
       public         heap    postgres    false    216            ?            1259    16569    sequence_for_alpha_numeric    SEQUENCE     ?   CREATE SEQUENCE public.sequence_for_alpha_numeric
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.sequence_for_alpha_numeric;
       public          postgres    false            ?            1259    16570    profesor    TABLE       CREATE TABLE public.profesor (
    idprofesor text DEFAULT nextval('public.sequence_for_alpha_numeric'::regclass) NOT NULL,
    correo text,
    nombre text,
    apellidopaterno text,
    apellidomaterno text,
    contrasenia text,
    superuser boolean
);
    DROP TABLE public.profesor;
       public         heap    postgres    false    218            ?            1259    16576    sequence_for_alpha_numeric_4    SEQUENCE     ?   CREATE SEQUENCE public.sequence_for_alpha_numeric_4
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
 3   DROP SEQUENCE public.sequence_for_alpha_numeric_4;
       public          postgres    false            ?            1259    16577    reactivo    TABLE     ?   CREATE TABLE public.reactivo (
    idreactivo text DEFAULT nextval('public.sequence_for_alpha_numeric_4'::regclass) NOT NULL,
    pregunta text,
    dificultad integer,
    "requiereProcedimiento" boolean,
    correcto integer
);
    DROP TABLE public.reactivo;
       public         heap    postgres    false    220            ?            1259    16583    reactivosdeexamen    TABLE     ?   CREATE TABLE public.reactivosdeexamen (
    idexamen text NOT NULL,
    idreactivo text NOT NULL,
    orden integer NOT NULL
);
 %   DROP TABLE public.reactivosdeexamen;
       public         heap    postgres    false            ?            1259    16588    sequence_for_alpha_numeric_5    SEQUENCE     ?   CREATE SEQUENCE public.sequence_for_alpha_numeric_5
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
 3   DROP SEQUENCE public.sequence_for_alpha_numeric_5;
       public          postgres    false            ?            1259    16589 	   respuesta    TABLE     ?   CREATE TABLE public.respuesta (
    idrespuesta text DEFAULT nextval('public.sequence_for_alpha_numeric_5'::regclass) NOT NULL,
    respuesta text,
    idsigreactivo text
);
    DROP TABLE public.respuesta;
       public         heap    postgres    false    223            ?            1259    16595    respuestasdereactivo    TABLE     ?   CREATE TABLE public.respuestasdereactivo (
    idreactivo text NOT NULL,
    idrespuesta text NOT NULL,
    orden integer NOT NULL
);
 (   DROP TABLE public.respuestasdereactivo;
       public         heap    postgres    false            ?            1259    16600    sequence_for_alpha_numeric_6    SEQUENCE     ?   CREATE SEQUENCE public.sequence_for_alpha_numeric_6
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
 3   DROP SEQUENCE public.sequence_for_alpha_numeric_6;
       public          postgres    false            ?            1259    16601    sequence_for_alpha_numeric_7    SEQUENCE     ?   CREATE SEQUENCE public.sequence_for_alpha_numeric_7
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
 3   DROP SEQUENCE public.sequence_for_alpha_numeric_7;
       public          postgres    false            ?            1259    16602    tema    TABLE     ?   CREATE TABLE public.tema (
    idtema text DEFAULT nextval('public.sequence_for_alpha_numeric_6'::regclass) NOT NULL,
    nombre text,
    descripcion text,
    idunidad text
);
    DROP TABLE public.tema;
       public         heap    postgres    false    226            ?            1259    16608    temasdereactivo    TABLE     |   CREATE TABLE public.temasdereactivo (
    idreactivo text NOT NULL,
    idtema text NOT NULL,
    idunidad text NOT NULL
);
 #   DROP TABLE public.temasdereactivo;
       public         heap    postgres    false            ?            1259    16613    unidad    TABLE     ?   CREATE TABLE public.unidad (
    idunidad text DEFAULT nextval('public.sequence_for_alpha_numeric_7'::regclass) NOT NULL,
    nombre text,
    descripcion text,
    idasignatura text
);
    DROP TABLE public.unidad;
       public         heap    postgres    false    227            E          0    16556 
   asignatura 
   TABLE DATA           Q   COPY public.asignatura (idasignatura, nombre, descripcion, idduenio) FROM stdin;
    public          postgres    false    215   ?K       G          0    16563    examen 
   TABLE DATA           U   COPY public.examen (idexamen, nombre, descripcion, idasignatura, tiempo) FROM stdin;
    public          postgres    false    217   )L       I          0    16570    profesor 
   TABLE DATA           x   COPY public.profesor (idprofesor, correo, nombre, apellidopaterno, apellidomaterno, contrasenia, superuser) FROM stdin;
    public          postgres    false    219   ?L       K          0    16577    reactivo 
   TABLE DATA           g   COPY public.reactivo (idreactivo, pregunta, dificultad, "requiereProcedimiento", correcto) FROM stdin;
    public          postgres    false    221   ?M       L          0    16583    reactivosdeexamen 
   TABLE DATA           H   COPY public.reactivosdeexamen (idexamen, idreactivo, orden) FROM stdin;
    public          postgres    false    222   ?N       N          0    16589 	   respuesta 
   TABLE DATA           J   COPY public.respuesta (idrespuesta, respuesta, idsigreactivo) FROM stdin;
    public          postgres    false    224   O       O          0    16595    respuestasdereactivo 
   TABLE DATA           N   COPY public.respuestasdereactivo (idreactivo, idrespuesta, orden) FROM stdin;
    public          postgres    false    225   ?P       R          0    16602    tema 
   TABLE DATA           E   COPY public.tema (idtema, nombre, descripcion, idunidad) FROM stdin;
    public          postgres    false    228   XQ       S          0    16608    temasdereactivo 
   TABLE DATA           G   COPY public.temasdereactivo (idreactivo, idtema, idunidad) FROM stdin;
    public          postgres    false    229   ?Q       T          0    16613    unidad 
   TABLE DATA           M   COPY public.unidad (idunidad, nombre, descripcion, idasignatura) FROM stdin;
    public          postgres    false    230   _R       [           0    0    sequence_for_alpha_numeric    SEQUENCE SET     H   SELECT pg_catalog.setval('public.sequence_for_alpha_numeric', 2, true);
          public          postgres    false    218            \           0    0    sequence_for_alpha_numeric_2    SEQUENCE SET     K   SELECT pg_catalog.setval('public.sequence_for_alpha_numeric_2', 1, false);
          public          postgres    false    214            ]           0    0    sequence_for_alpha_numeric_3    SEQUENCE SET     K   SELECT pg_catalog.setval('public.sequence_for_alpha_numeric_3', 1, false);
          public          postgres    false    216            ^           0    0    sequence_for_alpha_numeric_4    SEQUENCE SET     K   SELECT pg_catalog.setval('public.sequence_for_alpha_numeric_4', 1, false);
          public          postgres    false    220            _           0    0    sequence_for_alpha_numeric_5    SEQUENCE SET     K   SELECT pg_catalog.setval('public.sequence_for_alpha_numeric_5', 1, false);
          public          postgres    false    223            `           0    0    sequence_for_alpha_numeric_6    SEQUENCE SET     K   SELECT pg_catalog.setval('public.sequence_for_alpha_numeric_6', 1, false);
          public          postgres    false    226            a           0    0    sequence_for_alpha_numeric_7    SEQUENCE SET     K   SELECT pg_catalog.setval('public.sequence_for_alpha_numeric_7', 1, false);
          public          postgres    false    227            ?           2606    16620    asignatura asignatura_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.asignatura
    ADD CONSTRAINT asignatura_pkey PRIMARY KEY (idasignatura);
 D   ALTER TABLE ONLY public.asignatura DROP CONSTRAINT asignatura_pkey;
       public            postgres    false    215            ?           2606    16622    examen examen_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.examen
    ADD CONSTRAINT examen_pkey PRIMARY KEY (idexamen);
 <   ALTER TABLE ONLY public.examen DROP CONSTRAINT examen_pkey;
       public            postgres    false    217            ?           2606    16624    profesor profesor_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_pkey PRIMARY KEY (idprofesor);
 @   ALTER TABLE ONLY public.profesor DROP CONSTRAINT profesor_pkey;
       public            postgres    false    219            ?           2606    16626    reactivo reactivo_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.reactivo
    ADD CONSTRAINT reactivo_pkey PRIMARY KEY (idreactivo);
 @   ALTER TABLE ONLY public.reactivo DROP CONSTRAINT reactivo_pkey;
       public            postgres    false    221            ?           2606    16628 (   reactivosdeexamen reactivosdeexamen_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public.reactivosdeexamen
    ADD CONSTRAINT reactivosdeexamen_pkey PRIMARY KEY (idexamen, idreactivo);
 R   ALTER TABLE ONLY public.reactivosdeexamen DROP CONSTRAINT reactivosdeexamen_pkey;
       public            postgres    false    222    222            ?           2606    16630    respuesta respuesta_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.respuesta
    ADD CONSTRAINT respuesta_pkey PRIMARY KEY (idrespuesta);
 B   ALTER TABLE ONLY public.respuesta DROP CONSTRAINT respuesta_pkey;
       public            postgres    false    224            ?           2606    16632 .   respuestasdereactivo respuestasdereactivo_pkey 
   CONSTRAINT     ?   ALTER TABLE ONLY public.respuestasdereactivo
    ADD CONSTRAINT respuestasdereactivo_pkey PRIMARY KEY (idreactivo, idrespuesta);
 X   ALTER TABLE ONLY public.respuestasdereactivo DROP CONSTRAINT respuestasdereactivo_pkey;
       public            postgres    false    225    225            ?           2606    16634    tema tema_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.tema
    ADD CONSTRAINT tema_pkey PRIMARY KEY (idtema);
 8   ALTER TABLE ONLY public.tema DROP CONSTRAINT tema_pkey;
       public            postgres    false    228            ?           2606    16636 $   temasdereactivo temasdereactivo_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.temasdereactivo
    ADD CONSTRAINT temasdereactivo_pkey PRIMARY KEY (idreactivo, idtema);
 N   ALTER TABLE ONLY public.temasdereactivo DROP CONSTRAINT temasdereactivo_pkey;
       public            postgres    false    229    229            ?           2606    16638    unidad unidad_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.unidad
    ADD CONSTRAINT unidad_pkey PRIMARY KEY (idunidad);
 <   ALTER TABLE ONLY public.unidad DROP CONSTRAINT unidad_pkey;
       public            postgres    false    230            ?           2606    16639    examen asignatura    FK CONSTRAINT     ?   ALTER TABLE ONLY public.examen
    ADD CONSTRAINT asignatura FOREIGN KEY (idasignatura) REFERENCES public.asignatura(idasignatura) ON UPDATE SET NULL ON DELETE SET NULL NOT VALID;
 ;   ALTER TABLE ONLY public.examen DROP CONSTRAINT asignatura;
       public          postgres    false    215    3223    217            ?           2606    16644    unidad asignatura    FK CONSTRAINT     ?   ALTER TABLE ONLY public.unidad
    ADD CONSTRAINT asignatura FOREIGN KEY (idasignatura) REFERENCES public.asignatura(idasignatura) ON UPDATE SET NULL ON DELETE SET NULL;
 ;   ALTER TABLE ONLY public.unidad DROP CONSTRAINT asignatura;
       public          postgres    false    230    3223    215            ?           2606    16649    asignatura duenio    FK CONSTRAINT     ?   ALTER TABLE ONLY public.asignatura
    ADD CONSTRAINT duenio FOREIGN KEY (idduenio) REFERENCES public.profesor(idprofesor) ON UPDATE SET NULL ON DELETE SET NULL NOT VALID;
 ;   ALTER TABLE ONLY public.asignatura DROP CONSTRAINT duenio;
       public          postgres    false    215    3227    219            ?           2606    16654 1   reactivosdeexamen reactivosdeexamen_idexamen_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.reactivosdeexamen
    ADD CONSTRAINT reactivosdeexamen_idexamen_fkey FOREIGN KEY (idexamen) REFERENCES public.examen(idexamen) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 [   ALTER TABLE ONLY public.reactivosdeexamen DROP CONSTRAINT reactivosdeexamen_idexamen_fkey;
       public          postgres    false    3225    217    222            ?           2606    16659 3   reactivosdeexamen reactivosdeexamen_idreactivo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.reactivosdeexamen
    ADD CONSTRAINT reactivosdeexamen_idreactivo_fkey FOREIGN KEY (idreactivo) REFERENCES public.reactivo(idreactivo) ON DELETE CASCADE NOT VALID;
 ]   ALTER TABLE ONLY public.reactivosdeexamen DROP CONSTRAINT reactivosdeexamen_idreactivo_fkey;
       public          postgres    false    3229    222    221            ?           2606    16664 9   respuestasdereactivo respuestasdereactivo_idreactivo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.respuestasdereactivo
    ADD CONSTRAINT respuestasdereactivo_idreactivo_fkey FOREIGN KEY (idreactivo) REFERENCES public.reactivo(idreactivo) ON UPDATE CASCADE ON DELETE CASCADE;
 c   ALTER TABLE ONLY public.respuestasdereactivo DROP CONSTRAINT respuestasdereactivo_idreactivo_fkey;
       public          postgres    false    221    3229    225            ?           2606    16669 :   respuestasdereactivo respuestasdereactivo_idrespuesta_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.respuestasdereactivo
    ADD CONSTRAINT respuestasdereactivo_idrespuesta_fkey FOREIGN KEY (idrespuesta) REFERENCES public.respuesta(idrespuesta) ON UPDATE CASCADE ON DELETE CASCADE;
 d   ALTER TABLE ONLY public.respuestasdereactivo DROP CONSTRAINT respuestasdereactivo_idrespuesta_fkey;
       public          postgres    false    224    225    3233            ?           2606    16674    respuesta sigreactivo    FK CONSTRAINT     ?   ALTER TABLE ONLY public.respuesta
    ADD CONSTRAINT sigreactivo FOREIGN KEY (idsigreactivo) REFERENCES public.reactivo(idreactivo) ON UPDATE SET NULL ON DELETE SET NULL;
 ?   ALTER TABLE ONLY public.respuesta DROP CONSTRAINT sigreactivo;
       public          postgres    false    3229    224    221            ?           2606    16679 /   temasdereactivo temasdereactivo_idreactivo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.temasdereactivo
    ADD CONSTRAINT temasdereactivo_idreactivo_fkey FOREIGN KEY (idreactivo) REFERENCES public.reactivo(idreactivo) ON UPDATE CASCADE ON DELETE CASCADE;
 Y   ALTER TABLE ONLY public.temasdereactivo DROP CONSTRAINT temasdereactivo_idreactivo_fkey;
       public          postgres    false    229    221    3229            ?           2606    16684 +   temasdereactivo temasdereactivo_idtema_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.temasdereactivo
    ADD CONSTRAINT temasdereactivo_idtema_fkey FOREIGN KEY (idtema) REFERENCES public.tema(idtema) ON UPDATE CASCADE ON DELETE CASCADE;
 U   ALTER TABLE ONLY public.temasdereactivo DROP CONSTRAINT temasdereactivo_idtema_fkey;
       public          postgres    false    3237    229    228            ?           2606    16689 -   temasdereactivo temasdereactivo_idunidad_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.temasdereactivo
    ADD CONSTRAINT temasdereactivo_idunidad_fkey FOREIGN KEY (idunidad) REFERENCES public.unidad(idunidad) NOT VALID;
 W   ALTER TABLE ONLY public.temasdereactivo DROP CONSTRAINT temasdereactivo_idunidad_fkey;
       public          postgres    false    230    229    3241            ?           2606    16694    tema unidad    FK CONSTRAINT     ?   ALTER TABLE ONLY public.tema
    ADD CONSTRAINT unidad FOREIGN KEY (idunidad) REFERENCES public.unidad(idunidad) ON UPDATE SET NULL ON DELETE SET NULL;
 5   ALTER TABLE ONLY public.tema DROP CONSTRAINT unidad;
       public          postgres    false    3241    230    228            E   ?   x?m?1?0???9?O???; &???b??2j?*N??I?????????2ϔ%?M?AZ?2*?5?py??L?c4?A????	z??;|?{?LCQτ?8?S??YQtdN?;?????'g??͜???U=&Y??}B_0?F?      G   ?   x?K?0?t?H?M?S0?R(??t?J?-??WHIU(?KR??E?ə?7?qrp?V????d?d??+q??T&?@H?H?1gq"D?3?*g?3ʙp?0>??6???(*MMJT0?DQ?r??W? B?B?      I   ?   x?=ι?0???y
? ??;=???*u?bR?r s????t?`}???c&W?)????qMpl??YH?,?^??:\?^?Ľ[?#??gid?Ge?ڠ?3??tXEҪ%x?l?y??z-T"???YO*??Z8ˆFbVZ-????<???;?&1??G???W?W,??]?K?      K   -  x???MN?0???S?	?vB???Kvl?x,\;8N?{q $?N???E????<?g<xm6?R???p?	??&Pݹ?@?83:H??|OQ^??@>y?sP???)? ???Z}???@??f??O??j#!?H+?"9????<?
Ƴ?b?L??Irw????	fK?F[???2???w?ܬ??Z+k=??r???M??C?xb??r??+??ڈ?	?O?????E???~9?OC%tn?A?Nd???mz?&t?V??J&??\??|7??بٛ????!)g|	?????\2?~ ????      L   H   x?]Ǳ?0C?Z??l?.???????}GC??ԇ??_?j????~?8Qֲ?!??Ei???\??L:??      N   p  x?]?=O?0??˯??P?;?*?)?jac1?)?Ҥ??=g?[2??s_?ώ`g?i?~2??k?.?f??N??Ҫ?v????f?Z+?/`*?
????	`???/Jp?v?	f?#?H??H?Kn?0*?Uo?5A??}w??0Y?h??P3:g?K??ɪ???K2????0?? ?g{IP???^_???????????F1?Ѹ???'X?fh???`>?8?e(?Dh? *????H?`?????%?=F$z>?N???R ??#_?V?m?ɴc?????u????S??A(;SZL\???Ho????݅?@hE4"4BA?W???2kEe>??4??ߴ?,?]??I$??D?#2^??8??ۢ(~X??/      O   ?   x?M??
?0E??˿?d&??/]v???_4s_AA???#??{??P???44???6???O:^]??묵:ǜ?(:?U?U?E7??!:D??t????<?!:D?????!:D?*Vw??!?M?=??C????S??S?r(|?StOk7C?Ԫ]?vs&???????n?? ?rQ?      R   ?   x?m?;?0D??S?	?쐀?A??????a?V??ۜ,DA9of???R=me
^B?}LL???ݘ??S??A?4o%???lͿ?~?8eo`??4.??1}c????NF3J?*o???<%??\&*?
Z\aQ?Pa??"? k>.      S   V   x?5?9? D?z?0Ql0?]R??r?"???}ٚ9?B!?c???񰸔??v?c?)?#?u??C9ܕTT?9?*?????G??      T   ?   x?=?=
1F??)?®???? ???l2?@6Y2ɂ?J<?^????????A?&Τp?LX?8r??fƉ!?????ѱ?8????}T?<?V???8J%??K??Boz??????q??G?ά?T|?ɋ%+?'T? ???М?S?????n??k?bN????1???G;     