--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: rep; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA rep;


--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: -
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = public, pg_catalog;

--
-- Name: add_money_to_cass(character varying, numeric, character varying, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION add_money_to_cass(OUT id integer, OUT t_ship_date timestamp without time zone, OUT t_order_date timestamp without time zone, t_operation_type_code character varying, summa numeric, t_description character varying, t_user_id integer) RETURNS record
    AS $$ DECLARE
BEGIN

  t_ship_date := now();
  t_order_date := t_ship_date;

  INSERT INTO orders(
            ship_date, order_date, operation_type_code, description, user_id)
    VALUES (t_ship_date, t_order_date, t_operation_type_code, t_description, t_user_id);

  id := currval('orders_id_seq');

  INSERT INTO items( orders_id, products_id, actual_price, quantity)
    VALUES (id, null, summa, 1);

END; $$
    LANGUAGE plpgsql;


--
-- Name: add_money_to_cass(numeric, character varying, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION add_money_to_cass(in_summa numeric, in_description character varying, in_user_id integer) RETURNS void
    AS $$ DECLARE
	t_operation_type_code character varying;
	t_order_id integer;
BEGIN

  IF in_summa >=0 THEN
	t_operation_type_code := 'mpl';
  ELSE
	t_operation_type_code := 'mmn';
  END IF;


  INSERT INTO orders(
            ship_date, order_date, operation_type_code, description, user_id)
    VALUES (now(), now(), t_operation_type_code, in_description, in_user_id);

  t_order_id := currval('orders_id_seq');

  INSERT INTO items( orders_id, products_id, actual_price, quantity)
    VALUES (t_order_id, null, abs(in_summa), 1);

END; $$
    LANGUAGE plpgsql;


--
-- Name: add_or_update_appaccess(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION add_or_update_appaccess(in_kod character varying) RETURNS void
    AS $$ DECLARE
	t_acces_is_have integer := null;
BEGIN

	SELECT 1 into t_acces_is_have FROM appaccess
		WHERE kod = in_kod;

	IF t_acces_is_have IS null THEN
	--raise EXCEPTION 'Привет';
		INSERT INTO appaccess(kod)
		VALUES (in_kod);
	--ELSE
	--	UPDATE appaccess
	--		SET description=in_description
	--	WHERE kod = in_kod;
	END IF;

END; $$
    LANGUAGE plpgsql;


--
-- Name: cass_edit(integer, numeric, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION cass_edit(in_orders_id integer, in_actual_price numeric, in_description character varying) RETURNS void
    AS $$ DECLARE
BEGIN

  --RAISE EXCEPTION '';

  UPDATE items
   SET  actual_price=in_actual_price
  WHERE orders_id=in_orders_id;

  UPDATE orders
   SET description=in_description
  WHERE id = in_orders_id;

END; $$
    LANGUAGE plpgsql;


--
-- Name: cass_select(date, date); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION cass_select(OUT rc refcursor, OUT current_sum numeric, OUT total_sum numeric, bdate date, edate date) RETURNS record
    AS $$ DECLARE --rc refcursor;
BEGIN

  --RAISE EXCEPTION '';

  OPEN rc FOR
	SELECT DISTINCT a.id,a.ship_date,a.order_date,a.operation_type_code,b.case_many_summ,a.oot_description,a.description
		FROM v_select_cass a ,( SELECT id,SUM(case_many) as case_many_summ from v_select_cass WHERE date(order_date) BETWEEN bdate and edate GROUP BY id ) b
	WHERE a.id = b.id AND date(a.order_date) BETWEEN bdate and edate;

  -- --------------------------------------------------------
  current_sum := get_cass_balance_date_interval(bdate,edate);
  total_sum := get_cass_balance();

  IF current_sum IS NULL THEN
    current_sum := 0;
  END IF;

  IF total_sum IS NULL THEN
    total_sum := 0;
  END IF;

  --RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: check_cass_balance(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION check_cass_balance() RETURNS trigger
    AS $$ DECLARE
	summ NUMERIC;
BEGIN

  summ := get_cass_balance();


  IF summ < 0 THEN
     RAISE EXCEPTION 'Денег в кассе неможет быть меньше нуля!!!';
  END IF;

  RETURN NEW;
END; $$
    LANGUAGE plpgsql;


--
-- Name: check_product_balance(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION check_product_balance() RETURNS trigger
    AS $$ DECLARE

BEGIN

  IF NEW.products_id IS NULL THEN
	RETURN NEW;
  ELSE
	IF 0 > select_products_quantity_for_id(NEW.products_id) THEN
		RAISE EXCEPTION 'Количество товара в наличии неможет быть меньше нуля!!!';
	END IF;
  END IF;

  RETURN NEW;
END; $$
    LANGUAGE plpgsql;


--
-- Name: create_item(integer, integer, numeric, numeric, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION create_item(t_order_id integer, t_product_id integer, t_summa numeric, t_quantity numeric, t_description character varying) RETURNS integer
    AS $$ DECLARE
  products_quantity_for_id items.quantity%Type := 0;
  t_operation_type_code orders.operation_type_code%TYPE := null;
BEGIN

  SELECT o.operation_type_code INTO t_operation_type_code FROM orders o
    WHERE o.id = t_order_id;

  IF t_operation_type_code IN ('psl') AND t_product_id IS NOT NULL THEN
    products_quantity_for_id = select_products_quantity_for_id(t_product_id);

    IF  products_quantity_for_id < t_quantity THEN
      RAISE EXCEPTION 'В данный момент уже не имеется такого колличества товара';
    END IF;
  END IF;

  INSERT INTO items( orders_id, products_id, actual_price, quantity)
      VALUES (t_order_id, t_product_id, t_summa, t_quantity);

  return currval('items_id_seq');

END; $$
    LANGUAGE plpgsql;


--
-- Name: create_order(character varying, character varying, integer, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION create_order(t_operation_type_code character varying, t_description character varying, t_sub_order_id integer, t_user_id integer) RETURNS integer
    AS $$ DECLARE
  order_id integer;
  t_ship_date timestamp;
  t_order_date timestamp;
BEGIN

  t_ship_date := now();
  t_order_date := t_ship_date;

  INSERT INTO orders(
            ship_date, order_date, operation_type_code, description, sub_order_id, user_id)
    VALUES (t_ship_date, t_order_date, t_operation_type_code, t_description, t_sub_order_id, t_user_id);

  return currval('orders_id_seq');

  --INSERT INTO items( orders_id, products_id, actual_price, quantity)
  --  VALUES (id, null, summa, 1);

END; $$
    LANGUAGE plpgsql;


--
-- Name: create_order(character varying, character varying, integer, integer, integer, boolean); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION create_order(t_operation_type_code character varying, t_description character varying, t_sub_order_id integer, t_user_id integer, t_customer_id integer, t_credit boolean) RETURNS integer
    AS $$ DECLARE
  order_id integer;
  t_ship_date timestamp;
  t_order_date timestamp;
BEGIN

  t_ship_date := now();
  t_order_date := t_ship_date;

  INSERT INTO orders(
            ship_date, order_date, operation_type_code, description, sub_order_id, user_id, customer_id, credit)
    VALUES (t_ship_date, t_order_date, t_operation_type_code, t_description, t_sub_order_id, t_user_id, t_customer_id, t_credit);

  return currval('orders_id_seq');

  --INSERT INTO items( orders_id, products_id, actual_price, quantity)
  --  VALUES (id, null, summa, 1);

END; $$
    LANGUAGE plpgsql;


--
-- Name: create_psl_item(integer, integer, numeric, numeric, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION create_psl_item(t_order_id integer, t_products_id integer, t_summa numeric, t_quantity numeric, t_description character varying) RETURNS integer
    AS $$ DECLARE

BEGIN


  INSERT INTO items( orders_id, products_id, actual_price, quantity)
      VALUES (t_order_id, t_products_id, t_summa, t_quantity, t_description);

END; $$
    LANGUAGE plpgsql;


--
-- Name: customer_add_credit(integer, integer, numeric, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION customer_add_credit(in_order_id integer, in_customr_id integer, in_summ numeric, in_description character varying) RETURNS void
    AS $$ DECLARE


BEGIN

	INSERT INTO customers_balances(
            order_id, customer_id, summ, description)
	VALUES (in_order_id, in_customr_id , in_summ, in_description);

END; $$
    LANGUAGE plpgsql;


--
-- Name: customer_add_money_to_balance(integer, numeric, character varying, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION customer_add_money_to_balance(in_customr_id integer, in_summ numeric, in_description character varying, in_user_id integer) RETURNS void
    AS $$ DECLARE
  balance_summ numeric;

  t_order_id integer;

  t_ship_date timestamp;
  t_order_date timestamp;
  t_operation_type_code character varying;

  r record;

BEGIN

	IF in_summ >= 0 THEN
		t_operation_type_code := 'mpl';
		balance_summ := in_summ;
	ELSE
		t_operation_type_code := 'mmn';
		balance_summ := in_summ;
	END IF;

	select id into t_order_id from add_money_to_cass( t_operation_type_code , abs(in_summ) , in_description, in_user_id);


	INSERT INTO customers_balances(
            order_id, customer_id, summ, description)
	VALUES (t_order_id, in_customr_id , balance_summ, in_description);

END; $$
    LANGUAGE plpgsql;


--
-- Name: customer_balance(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION customer_balance(in_customer_id integer) RETURNS numeric
    AS $$ DECLARE --rc refcursor;
  balance_sum numeric;
BEGIN

  --RAISE EXCEPTION '';

	SELECT sum(summ) into balance_sum
		FROM customers_balances where customer_id = in_customer_id;


	IF balance_sum IS NULL THEN
		balance_sum := 0;
	END IF;

  RETURN balance_sum;

END; $$
    LANGUAGE plpgsql;


--
-- Name: customer_balance_history(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION customer_balance_history(in_customer_id integer) RETURNS refcursor
    AS $$ DECLARE
	rc refcursor;
BEGIN

  --RAISE EXCEPTION '';

  OPEN rc FOR
	SELECT cb.id, cb.order_id, o.order_date, cb.summ, cb.description
	FROM customers_balances  cb, orders o
	WHERE cb.customer_id = in_customer_id and cb.order_id = o.id ;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: find_and_show_selling_order(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION find_and_show_selling_order(t_order_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  --RAISE EXCEPTION '';

  OPEN rc FOR
   SELECT i.products_id,
        p.products_groups_id,

        p."name" as product_name,
        p.description,
        p.scod,

        q.quantity,
        p.measures_id,
        m."name" as quantityName,
        i.actual_price,
        --trunc(i.quantity * i.actual_price,2) as actual_price_for_all,
        (q.summ - q.quantity) as returned,
        case when(m.mtype IS TRUE) then 0.01 else 1 end AS step

    FROM orders o, items i, products p, rb_measures m,

      (SELECT  i2.products_id, max(i2.quantity) as quantity, sum(i2.quantity) as summ FROM items i2, orders o2
        WHERE i2.orders_id = o2.id AND (o2.id = t_order_id OR o2.sub_order_id = t_order_id)
        GROUP BY products_id) as q

    WHERE o.id = t_order_id
      AND o.id = i.orders_id
      AND i.products_id = p.id
      AND p.measures_id = m.id
      AND i.products_id = q.products_id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: find_product_for_scod(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION find_product_for_scod(t_scod character varying) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT id, products_groups_id, "name", description, scod, quantity,
		measures_id, list_price, spec_price, percent_discount
	FROM products WHERE trim(scod)=trim(t_scod);
  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: find_product_for_scod_2(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION find_product_for_scod_2(t_scod character varying) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
  priduct_id integer := null;
  products_quantity NUMERIC(10,3) := 0;
BEGIN

  select id into priduct_id from products where trim(scod)=trim(t_scod);
  products_quantity := select_products_quantity_for_id(priduct_id);

  IF products_quantity > 0 THEN
    products_quantity:= 1;
  END IF;

  OPEN rc FOR
    SELECT p.id,
           p.products_groups_id,
            p."name",
            p.description,
            p.scod,
            products_quantity as quantity,
            p.measures_id,
            m."name",
            p.list_price,
            p.spec_price,
            trunc(p.percent_discount * 100,2) as percent_discount,
            case when(m.mtype IS TRUE) then 0.01 else 1 end AS step

    FROM products p, rb_measures m
    WHERE p.id=priduct_id
          AND p.measures_id = m.id;
          --AND select_products_quantity_for_id(p.id) > 0;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: find_product_for_scod_with_quantity(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION find_product_for_scod_with_quantity(t_scod character varying, OUT out_rc refcursor, OUT out_quantity numeric, OUT out_step numeric) RETURNS record
    AS $$ DECLARE
  priduct_id integer := null;
  product_measures_type boolean;
BEGIN

  select id into priduct_id from products where trim(scod)=trim(t_scod);

  OPEN out_rc FOR
    SELECT id, products_groups_id, "name", description, scod, quantity,
		measures_id, list_price, spec_price, percent_discount
	FROM products WHERE id=priduct_id;

  out_quantity := select_products_quantity_for_id(priduct_id);

  SELECT rb.mtype INTO product_measures_type
    FROM rb_measures rb, products p
    WHERE p.id=priduct_id AND p.measures_id=rb.id;

  IF product_measures_type IS TRUE THEN
    out_step := 0.01;
  ELSE
    out_step := 1;
  END IF;

END; $$
    LANGUAGE plpgsql;


--
-- Name: find_product_group_id_for_product_scod(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION find_product_group_id_for_product_scod(t_scod character varying) RETURNS integer
    AS $$ DECLARE t_product_group_id integer;
BEGIN

	SELECT products_groups_id into t_product_group_id
		FROM products WHERE scod=trim(t_scod);


	IF t_product_group_id IS NOT NULL THEN
		RETURN t_product_group_id;
	ELSE
		RETURN -1;
	END IF;

END; $$
    LANGUAGE plpgsql;


--
-- Name: find_product_id_for_scod(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION find_product_id_for_scod(t_scod character varying) RETURNS integer
    AS $$ DECLARE t_product_id integer;
BEGIN

	SELECT id into t_product_id
		FROM products WHERE scod=trim(t_scod);


	IF t_product_id IS NOT NULL THEN
		RETURN t_product_id;
	ELSE
		RETURN -1;
	END IF;

END; $$
    LANGUAGE plpgsql;


--
-- Name: get_cass_balance(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION get_cass_balance() RETURNS numeric
    AS $$ DECLARE --rc refcursor;
  total_sum numeric;
BEGIN

  --RAISE EXCEPTION '';
  SELECT SUM(case_many) into total_sum FROM v_select_cass_after_last_z;


  IF total_sum IS NULL THEN
    total_sum := 0;
  END IF;

  RETURN total_sum;

END; $$
    LANGUAGE plpgsql;


--
-- Name: get_cass_balance_date_interval(date, date); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION get_cass_balance_date_interval(bdate date, edate date) RETURNS numeric
    AS $$ DECLARE --rc refcursor;
  total_sum numeric;
BEGIN

	--RAISE EXCEPTION '';
	SELECT SUM(case_many) into total_sum FROM v_select_cass
	WHERE date(order_date) BETWEEN bdate and edate;


  IF total_sum IS NULL THEN
    total_sum := 0;
  END IF;

  RETURN total_sum;

END; $$
    LANGUAGE plpgsql;


--
-- Name: get_last_z_report_id(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION get_last_z_report_id() RETURNS integer
    AS $$ DECLARE
  t_id integer;
BEGIN
	SELECT max(id) INTO t_id FROM orders
			WHERE operation_type_code = 'z';

	IF t_id IS NULL THEN
		t_id := 0;
	END IF;

	RETURN t_id;

END; $$
    LANGUAGE plpgsql;


--
-- Name: get_prev_z_for_z(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION get_prev_z_for_z(in_z_report_id integer) RETURNS integer
    AS $$ DECLARE
  t_id integer;
BEGIN
	SELECT max(id) INTO t_id FROM orders
			WHERE operation_type_code = 'z' and id < in_z_report_id;

	IF t_id IS NULL THEN
		t_id := 0;
	END IF;

	RETURN t_id;

END; $$
    LANGUAGE plpgsql;


--
-- Name: get_prev_z_for_z_refcursor(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION get_prev_z_for_z_refcursor(in_z_report_id integer) RETURNS refcursor
    AS $$ DECLARE
  DECLARE rc refcursor;
BEGIN
	OPEN rc FOR
		SELECT * FROM orders o WHERE o.id = get_prev_z_for_z(in_z_report_id);

	RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: info_about_item(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION info_about_item(in_order_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    select
	i.id,
	i.orders_id,

	i.products_id,
	p."name" as product_name,
	p.scod,
	p.measures_id,
	m."name" as measures_name,
	p.description,
	p.products_groups_id,
	pg."name" as products_groups_name,
	pg.description as products_groups_description,

	i.actual_price,
	i.quantity,
	i.actual_price * i.quantity AS summ


	from items i LEFT OUTER JOIN products p ON (i.products_id = p.id )
	     LEFT OUTER JOIN products_groups pg ON (p.products_groups_id = pg.id)
	     LEFT OUTER JOIN rb_measures m ON (p.measures_id = m.id)
	WHERE i.orders_id = in_order_id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: info_about_order(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION info_about_order(in_order_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    select
	o.id,
	o.order_date,
	o.operation_type_code,
	ot.description as operation_name,

	o.description,
	o.sub_order_id,

	o.user_id,
	u."name" as user_name,
	u.isadmin,
	u.description as user_description,

	o.credit,
	(case when(o.credit is true) then 'да' else 'нет' end ) as credit_ru,

	o.customer_id,
	c.fio,
	c.address,
	c.phone_number,
	c.phone_number_2,
	c.email,
	c.description as customer_description,
	c.ur,
	(case when(c.ur is true) then 'да' else 'нет' end ) as ur_ru,
	c.short_name,
	c.doc_type,
	c.doc,
	c.sex,

	(select abs(sum(i.actual_price * i.quantity)) from items i WHERE i.orders_id = o.id) as to_cass,
	abs(cb.summ) as to_balance,
	cb.description as to_balance_description

	from orders o
		LEFT OUTER JOIN customers c ON (o.customer_id = c.id )
		LEFT OUTER JOIN customers_balances cb ON (o.id = cb.order_id)


		,orders_operations_types ot, users u
	where  o.id = in_order_id AND o.operation_type_code = type_code
		AND o.user_id = u.id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: is_user_have_access(integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION is_user_have_access(in_user_id integer, in_access_kod character varying) RETURNS boolean
    AS $$ DECLARE
	t_is_accesseble integer := null;
BEGIN

	SELECT 1 into t_is_accesseble FROM user_access ua, appaccess ap
		WHERE ua.user_id = in_user_id AND ua.access_id = ap.id AND ap.kod = in_access_kod;

	IF t_is_accesseble IS NOT NULL THEN
		RETURN true;
	ELSE
		RETURN false;
	END IF;

END; $$
    LANGUAGE plpgsql;


--
-- Name: make_z_report(character varying, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION make_z_report(OUT id integer, OUT t_ship_date timestamp without time zone, OUT t_order_date timestamp without time zone, OUT summa numeric, t_description character varying, t_user_id integer) RETURNS record
    AS $$ DECLARE
  id numeric;
BEGIN

  t_ship_date := now();
  t_order_date := t_ship_date;
  summa := get_cass_balance();

  INSERT INTO orders(
            ship_date, order_date, operation_type_code, description, user_id)
    VALUES (t_ship_date, t_order_date, 'z', t_description, t_user_id);

  id := currval('orders_id_seq');

  INSERT INTO items( orders_id, products_id, actual_price, quantity)
    VALUES (id, null, summa, 1);

END; $$
    LANGUAGE plpgsql;


--
-- Name: probe(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION probe(OUT out_y integer, OUT out_date timestamp without time zone, in_x integer) RETURNS record
    AS $$ DECLARE
BEGIN

  out_date := now();

  out_y := in_x * in_x;

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_delete(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_delete(t_id integer) RETURNS void
    AS $$ DECLARE
BEGIN

  DELETE FROM products
    WHERE id = t_id;

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_groups_delete(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_groups_delete(t_id integer) RETURNS void
    AS $$ DECLARE
BEGIN

  DELETE FROM products_groups
    WHERE id = t_id;

END ; $$
    LANGUAGE plpgsql;


--
-- Name: products_groups_insert(integer, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_groups_insert(t_sub_id integer, t_name character varying, t_description character varying) RETURNS integer
    AS $$ DECLARE
	probe integer := 0;
BEGIN

  IF  t_sub_id IS null THEN
	SELECT 1 INTO probe FROM products_groups
		WHERE (sub_id IS null AND NAME = t_name);
  ELSE
	SELECT 1 INTO probe FROM products_groups
		WHERE (sub_id = t_sub_id AND NAME = t_name);
  END IF;

  IF probe = 1 THEN
	RAISE EXCEPTION 'Ограничение уникальности значения' ;
  END IF;

  INSERT
    INTO products_groups
    ( sub_id, NAME, description )
  VALUES (t_sub_id, t_name, t_description );

  RETURN currval('products_groups_id_seq');

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_groups_select(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_groups_select() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT id, sub_id, NAME, description
      FROM products_groups  ORDER BY NAME;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_groups_select_cb1(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_groups_select_cb1() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT id, NAME , description
      FROM products_groups  ORDER BY sub_id,name;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_groups_update(integer, integer, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_groups_update(t_id integer, t_sub_id integer, t_name character varying, t_description character varying) RETURNS void
    AS $$ DECLARE
	probe integer := 0;
BEGIN
  IF  t_sub_id IS null THEN
	SELECT 1 INTO probe FROM products_groups
		WHERE (sub_id IS null AND NAME = t_name AND id != t_id);
  ELSE
	SELECT 1 INTO probe FROM products_groups
		WHERE (sub_id = t_sub_id AND NAME = t_name AND id != t_id);
  END IF;

  IF probe = 1 THEN
	RAISE EXCEPTION 'Ограничение уникальности значения' ;
  END IF;

  UPDATE products_groups
      SET sub_id=t_sub_id, NAME=t_name, description=t_description
    WHERE id = t_id;



END; $$
    LANGUAGE plpgsql;


--
-- Name: products_insert(integer, character varying, character varying, character varying, numeric, integer, numeric, numeric, numeric); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_insert(t_products_groups_id integer, t_name character varying, t_description character varying, t_scod character varying, t_quantity numeric, t_measures_id integer, t_list_price numeric, t_spec_price numeric, t_percent_discount numeric) RETURNS integer
    AS $$ DECLARE
BEGIN

  INSERT INTO products(
            products_groups_id, name, description, scod, quantity, measures_id,
            list_price, spec_price, percent_discount)
    VALUES (t_products_groups_id, t_name, t_description, trim(t_scod), t_quantity, t_measures_id,
            t_list_price, t_spec_price, t_percent_discount);


  RETURN currval('products_id_seq');

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_insert(integer, character varying, character varying, character varying, integer, numeric, numeric, numeric); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_insert(t_products_groups_id integer, t_name character varying, t_description character varying, t_scod character varying, t_measures_id integer, t_list_price numeric, t_spec_price numeric, t_percent_discount numeric) RETURNS integer
    AS $$ DECLARE
BEGIN

  INSERT INTO products(
            products_groups_id, name, description, scod, measures_id,
            list_price, spec_price, percent_discount)
    VALUES (t_products_groups_id, t_name, t_description, trim(t_scod), t_measures_id,
            t_list_price, t_spec_price, t_percent_discount);


  RETURN currval('products_id_seq');

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_select(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_select() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT id, products_groups_id, "name", description, scod, quantity,
		measures_id, list_price, spec_price, percent_discount,
		trunc((case when (spec_price = 0 AND percent_discount=0) THEN list_price
			when (spec_price > 0 AND percent_discount=0) THEN spec_price
			when (spec_price = 0 AND percent_discount>0) THEN (list_price - (list_price)*percent_discount)
			end),2) as total_price
	FROM products;
  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_select_by_products_groups_id(numeric); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_select_by_products_groups_id(t_products_groups_id numeric) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT p.id, p."name", p.description, p.scod,

     q.quantity as quantity,

		p.measures_id, m."name",p.list_price, p.spec_price, trunc(p.percent_discount * 100,2) as percent_discount,
		trunc((case when (p.spec_price = 0 AND p.percent_discount=0) THEN p.list_price
			when (p.spec_price > 0 AND p.percent_discount=0) THEN p.spec_price
			when (p.spec_price = 0 AND p.percent_discount>0) THEN (p.list_price - (p.list_price)*p.percent_discount)
			end),2) as spec_price_for_unit,
		trunc((case when (p.spec_price = 0 AND p.percent_discount=0) THEN p.list_price
			when (p.spec_price > 0 AND p.percent_discount=0) THEN p.spec_price
			when (p.spec_price = 0 AND p.percent_discount>0) THEN (p.list_price - (p.list_price)*p.percent_discount)
			end) * q.quantity,2) as total_price
	FROM  products p,
        rb_measures m,
        v_products_quantity q

	WHERE products_groups_id = t_products_groups_id
        AND p.measures_id = m.id
        AND p.id = q.id
	ORDER BY p.id;
  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_update(integer, integer, character varying, character varying, character varying, numeric, integer, numeric, numeric, numeric); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_update(t_id integer, t_products_groups_id integer, t_name character varying, t_description character varying, t_scod character varying, t_quantity numeric, t_measures_id integer, t_list_price numeric, t_spec_price numeric, t_percent_discount numeric) RETURNS void
    AS $$ DECLARE
BEGIN

  UPDATE products
   SET 	products_groups_id=t_products_groups_id,
				name=t_name,
				description=t_description,
				scod=trim(t_scod),
				quantity=t_quantity,
				measures_id=t_measures_id,
				list_price=t_list_price,
				spec_price=t_spec_price,
				percent_discount=t_percent_discount
	WHERE id = t_id;

  RETURN;

END; $$
    LANGUAGE plpgsql;


--
-- Name: products_update(integer, integer, character varying, character varying, character varying, integer, numeric, numeric, numeric); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION products_update(t_id integer, t_products_groups_id integer, t_name character varying, t_description character varying, t_scod character varying, t_measures_id integer, t_list_price numeric, t_spec_price numeric, t_percent_discount numeric) RETURNS void
    AS $$ DECLARE
BEGIN

  UPDATE products
   SET 	products_groups_id=t_products_groups_id,
				name=t_name,
				description=t_description,
				scod=trim(t_scod),
				measures_id=t_measures_id,
				list_price=t_list_price,
				spec_price=t_spec_price,
				percent_discount=t_percent_discount
	WHERE id = t_id;

  RETURN;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_customers_delete(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_customers_delete(in_id integer) RETURNS void
    AS $$ DECLARE
BEGIN

	DELETE FROM customers WHERE id=in_id;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_customers_insert(character varying, character varying, character varying, character varying, character varying, character varying, boolean, character varying, integer, character varying, numeric); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_customers_insert(in_fio character varying, in_address character varying, in_phone_number character varying, in_phone_number_2 character varying, in_email character varying, in_description character varying, in_ur boolean, in_short_name character varying, in_doc_type integer, in_doc character varying, in_sex numeric) RETURNS integer
    AS $$ DECLARE
BEGIN

  INSERT INTO customers(
            fio, address, phone_number, phone_number_2, email, description,
            ur, short_name, doc_type, doc, sex)
    VALUES (in_fio, in_address, in_phone_number, in_phone_number_2, in_email, in_description,
            in_ur, in_short_name, in_doc_type, in_doc, in_sex);

  RETURN currval('customers_id_seq');

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_customers_select(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_customers_select() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT c.id,
	c.ur,
	c.doc,

	c.doc_type,
	rdt."name" as doc_type_name,

	c.short_name,
	c.fio,

	c.sex,
	--CASE
        --    WHEN c.sex=0 THEN 'мужской'
        --    ELSE 'женский'
        --END as sex_name,

	c.address,
	c.phone_number,
	c.phone_number_2,
	c.email,
	c.description,
	customer_balance(c.id) as balance
    FROM customers c, rb_doc_types rdt
    WHERE c.doc_type=rdt.id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_customers_select_for_doc(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_customers_select_for_doc(in_doc character varying) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT c.id,
	c.ur,
	c.doc,

	c.doc_type,
	rdt."name" as doc_type_name,

	c.short_name,
	c.fio,

	c.sex,
	--CASE
        --    WHEN c.sex=0 THEN 'мужской'
        --    ELSE 'женский'
        --END as sex_name,

	c.address,
	c.phone_number,
	c.phone_number_2,
	c.email,
	c.description,
	customer_balance(c.id) as balance
    FROM customers c, rb_doc_types rdt
    WHERE c.doc_type=rdt.id AND c.doc=in_doc;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_customers_select_for_fio(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_customers_select_for_fio(in_fio character varying) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT c.id,
	c.ur,
	c.doc,

	c.doc_type,
	rdt."name" as doc_type_name,

	c.short_name,
	c.fio,

	c.sex,
	--CASE
        --    WHEN c.sex=0 THEN 'мужской'
        --    ELSE 'женский'
        --END as sex_name,

	c.address,
	c.phone_number,
	c.phone_number_2,
	c.email,
	c.description,
	customer_balance(c.id) as balance
    FROM customers c, rb_doc_types rdt
    WHERE c.doc_type=rdt.id AND c.fio LIKE '%'||in_fio||'%';

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_customers_select_for_id(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_customers_select_for_id(in_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT c.id,
	c.ur,
	c.doc,

	c.doc_type,
	rdt."name" as doc_type_name,

	c.short_name,
	c.fio,

	c.sex,
	--CASE
        --    WHEN c.sex=0 THEN 'мужской'
        --    ELSE 'женский'
        --END as sex_name,

	c.address,
	c.phone_number,
	c.phone_number_2,
	c.email,
	c.description,
	customer_balance(c.id) as balance
    FROM customers c, rb_doc_types rdt
    WHERE c.doc_type=rdt.id AND c.id = in_id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_customers_select_for_order_id(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_customers_select_for_order_id(in_order_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
	t_customer_id integer;
BEGIN

	SELECT customer_id INTO t_customer_id FROM orders WHERE id = in_order_id;

  OPEN rc FOR
    SELECT c.id,
	c.ur,
	c.doc,

	c.doc_type,
	rdt."name" as doc_type_name,

	c.short_name,
	c.fio,

	c.sex,
	--CASE
        --    WHEN c.sex=0 THEN 'мужской'
        --    ELSE 'женский'
        --END as sex_name,

	c.address,
	c.phone_number,
	c.phone_number_2,
	c.email,
	c.description,
	customer_balance(c.id) as balance
    FROM customers c, rb_doc_types rdt
    WHERE c.doc_type=rdt.id AND c.id = t_customer_id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_customers_select_for_short_name(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_customers_select_for_short_name(in_short_name character varying) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT c.id,
	c.ur,
	c.doc,

	c.doc_type,
	rdt."name" as doc_type_name,

	c.short_name,
	c.fio,

	c.sex,
	--CASE
        --    WHEN c.sex=0 THEN 'мужской'
        --    ELSE 'женский'
        --END as sex_name,

	c.address,
	c.phone_number,
	c.phone_number_2,
	c.email,
	c.description,
	customer_balance(c.id) as balance
    FROM customers c, rb_doc_types rdt
    WHERE c.doc_type=rdt.id AND c.short_name LIKE '%'||in_short_name||'%';

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_customers_update(integer, character varying, character varying, character varying, character varying, character varying, character varying, boolean, character varying, integer, character varying, numeric); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_customers_update(in_id integer, in_fio character varying, in_address character varying, in_phone_number character varying, in_phone_number_2 character varying, in_email character varying, in_description character varying, in_ur boolean, in_short_name character varying, in_doc_type integer, in_doc character varying, in_sex numeric) RETURNS void
    AS $$ DECLARE
BEGIN

   UPDATE customers
   SET fio=in_fio, address=in_address, phone_number=in_phone_number, phone_number_2=in_phone_number_2, email=in_email,
       description=in_description, ur=in_ur, short_name=in_short_name, doc_type=in_doc_type, doc=in_doc, sex=in_sex
   WHERE id=in_id;


END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_doc_types_delete(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_doc_types_delete(t_id integer) RETURNS void
    AS $$ DECLARE
BEGIN

  DELETE FROM rb_doc_types
    WHERE id = t_id;


END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_doc_types_insert(character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_doc_types_insert(t_name character varying, t_description character varying) RETURNS integer
    AS $$ DECLARE
BEGIN

  INSERT INTO rb_doc_types(
            name, description)
    VALUES (t_name, t_description);

  RETURN currval('rb_doc_types_id_seq');

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_doc_types_select(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_doc_types_select() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT id, name, description
      FROM rb_doc_types;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_doc_types_update(integer, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_doc_types_update(t_id integer, t_name character varying, t_description character varying) RETURNS void
    AS $$ DECLARE
BEGIN

  UPDATE rb_doc_types
      SET name=t_name, description=t_description
    WHERE id = t_id;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_measures_delete(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_measures_delete(t_id integer) RETURNS void
    AS $$ DECLARE
BEGIN

  DELETE FROM rb_measures
    WHERE id = t_id;


END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_measures_insert(character varying, character varying, boolean); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_measures_insert(t_name character varying, t_description character varying, t_mtype boolean) RETURNS integer
    AS $$ DECLARE
BEGIN

  INSERT INTO rb_measures(
            name, description,mtype)
    VALUES (t_name, t_description, t_mtype);

  RETURN currval('rb_measures_id_seq');

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_measures_select(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_measures_select() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT id, name, description, mtype
      FROM rb_measures;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rb_measures_update(integer, character varying, character varying, boolean); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rb_measures_update(t_id integer, t_name character varying, t_description character varying, t_mtype boolean) RETURNS void
    AS $$ DECLARE
BEGIN

  UPDATE rb_measures
      SET name=t_name, description=t_description, mtype=t_mtype
    WHERE id = t_id;

END; $$
    LANGUAGE plpgsql;


--
-- Name: receipts_of_the_product(boolean, integer, numeric, numeric, character varying, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION receipts_of_the_product(t_minus boolean, t_products_id integer, t_actual_price numeric, t_quantity numeric, t_description character varying, t_user_id integer) RETURNS void
    AS $$ DECLARE
  id integer;
  t_ship_date timestamp;
  t_order_date timestamp;
  t_operation_type_code character varying(8);
  tt_quantity numeric;
BEGIN

  t_ship_date := now();
  t_order_date := t_ship_date;

  IF t_minus IS true THEN
    t_operation_type_code := 'pmn';
    --tt_quantity := - t_quantity;
  ELSE
    t_operation_type_code := 'ppl';
    --tt_quantity := t_quantity;
  END IF;

  INSERT INTO orders(
            ship_date, order_date, operation_type_code, description, user_id)
    VALUES (t_ship_date, t_order_date, t_operation_type_code, t_description, t_user_id);

  id := currval('orders_id_seq');

  INSERT INTO items( orders_id, products_id, actual_price, quantity)
    VALUES (id, t_products_id, t_actual_price, t_quantity);

END; $$
    LANGUAGE plpgsql;


--
-- Name: rpt_orders_history(date, date); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rpt_orders_history(in_bdate date, in_edate date) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
	SELECT 	o.id, o.order_date, o.operation_type_code, ot.supertype,ot.description as operation_description,
		ppg.product_group_id , ppg.product_group_name,
		i.products_id as product_id, ppg.product_name, ppg.scod,
		i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description,
		u.id as user_id, u."name" as user_name, u.description as user_description

	FROM orders o,  orders_operations_types ot,  users u, items i

	LEFT OUTER JOIN(SELECT 	pg.id as product_group_id , pg."name" as product_group_name,
				p.id as product_id, p."name" as product_name, p.scod
			FROM products p, products_groups pg
			WHERE p.products_groups_id = pg.id) ppg ON (i.products_id = ppg.product_id)


	WHERE	date(o.order_date) BETWEEN in_bdate and in_edate
		and o.id = i.orders_id
		and o.operation_type_code = ot.type_code
		and u.id = o.user_id
	ORDER BY id;


  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rpt_orders_history_m2(date, date); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rpt_orders_history_m2(in_bdate date, in_edate date) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
select  -- Сведения по операции
o.id as oder_id,
o.order_date as order_date,
o.operation_type_code as operation_type_code,
ot.description as order_operation_name,

o.description as order_description,
o.sub_order_id as sub_order_id,

-- Сведения по пользователю
o.user_id as user_id,
u."name" as user_name,
u.isadmin as isadmin,
(case when(u.isadmin is true) then 'да' else 'нет' end ) as isadmin_ru,
u.description as user_description,

-- Сведения по проведенной операции
o.credit as credit,
(case when(o.credit is true) then 'да' else 'нет' end ) as credit_ru,
(select abs(sum(ii.actual_price * ii.quantity))
from items ii WHERE ii.orders_id = o.id) as to_cass,
(case when cb.summ is null then 0 else cb.summ end) as to_balance,
cb.description as to_balance_description,

-- Сведения по клиенту
o.customer_id,
----------
c.short_name,
c.doc_type,
c.doc,
c.ur,
(case when(c.ur is true) then 'да' else 'нет' end ) as ur_ru,
----------
c.fio,
c.sex,
c.address,
c.phone_number,
c.phone_number_2,
c.email,
c.description as customer_description,

-- Сведения по составу операции
-- Группа товаров
p.products_groups_id,
(case when (pg."name" is null) then 'сумма' else pg."name" end) as products_groups_name,
pg.description as products_groups_description,
-- Товар
i.products_id,
(case when (p."name" is null) then 'Касса' else p."name" end) as product_name,
p.scod,
p.measures_id,
ms."name" as measures_name,
p.description as product_description,

-- Оценка
i.actual_price,
i.quantity,
i.actual_price * i.quantity AS summ

from orders o
LEFT OUTER JOIN customers c ON (o.customer_id = c.id )
LEFT OUTER JOIN customers_balances cb ON (o.id = cb.order_id),

     items i
LEFT OUTER JOIN products p ON (i.products_id = p.id )
LEFT OUTER JOIN products_groups pg ON (p.products_groups_id = pg.id)
LEFT OUTER JOIN rb_measures ms ON (p.measures_id = ms.id)

,orders_operations_types ot, users u
where  o.order_date between in_bdate AND in_edate
--o.id = 396 -- ppl Приход товара
--o.id = 527 -- pmn Списание товара

--o.id = 563 --mpl
--o.id = 546 --mmn

--o.id = 539 -- z

--o.id = 562 --psl Продажа товара с кредитом
--or o.id = 567 --prt Возврат товара без балансных операций

AND o.id=i.orders_id AND o.operation_type_code = type_code
AND o.user_id = u.id --AND p.id is not null
AND i.actual_price >= 0
order by o.id;


  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rpt_orders_history_m2_for_id(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rpt_orders_history_m2_for_id(in_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
select  -- Сведения по операции
o.id as oder_id,
o.order_date as order_date,
o.operation_type_code as operation_type_code,
ot.description as order_operation_name,

o.description as order_description,
o.sub_order_id as sub_order_id,

-- Сведения по пользователю
o.user_id as user_id,
u."name" as user_name,
u.isadmin as isadmin,
(case when(u.isadmin is true) then 'да' else 'нет' end ) as isadmin_ru,
u.description as user_description,

-- Сведения по проведенной операции
o.credit as credit,
(case when(o.credit is true) then 'да' else 'нет' end ) as credit_ru,
(select abs(sum(ii.actual_price * ii.quantity))
from items ii WHERE ii.orders_id = o.id) as to_cass,
(case when cb.summ is null then 0 else cb.summ end) as to_balance,
cb.description as to_balance_description,

-- Сведения по клиенту
o.customer_id,
----------
c.short_name,
c.doc_type,
c.doc,
c.ur,
(case when(c.ur is true) then 'да' else 'нет' end ) as ur_ru,
----------
c.fio,
c.sex,
c.address,
c.phone_number,
c.phone_number_2,
c.email,
c.description as customer_description,

-- Сведения по составу операции
-- Группа товаров
p.products_groups_id,
(case when (pg."name" is null) then 'сумма' else pg."name" end) as products_groups_name,
pg.description as products_groups_description,
-- Товар
i.products_id,
(case when (p."name" is null) then 'Касса' else p."name" end) as product_name,
p.scod,
p.measures_id,
ms."name" as measures_name,
p.description as product_description,

-- Оценка
i.actual_price,
i.quantity,
i.actual_price * i.quantity AS summ

from orders o
LEFT OUTER JOIN customers c ON (o.customer_id = c.id )
LEFT OUTER JOIN customers_balances cb ON (o.id = cb.order_id),

     items i
LEFT OUTER JOIN products p ON (i.products_id = p.id )
LEFT OUTER JOIN products_groups pg ON (p.products_groups_id = pg.id)
LEFT OUTER JOIN rb_measures ms ON (p.measures_id = ms.id)

,orders_operations_types ot, users u

where  o.id = in_id


AND o.id=i.orders_id AND o.operation_type_code = type_code
AND o.user_id = u.id --AND p.id is not null
AND i.actual_price >= 0
order by o.id;


  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rpt_prod_select_moving(date, date); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rpt_prod_select_moving(bdate date, edate date) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR

select
	pg.id as groups_id,
	pg."name" as groups_name,
	p.id as products_id,
	p."name" as products_name,

	ppl.quantity as ppl_quantity,
	ppl.summ as ppl_summ,

	pmn.quantity as pmn_quantity,
	pmn.summ as pmn_summ,

	psl.quantity as psl_quantity,
	psl.summ as psl_summ,

	prt.quantity as prt_quantity,
	prt.summ as prt_summ

	--from products_groups pg, products p
	from products_groups pg, (select DISTINCT v.products_id as id , p."name", p.products_groups_id
				from v_operations v, products p
				where products_id is not null AND v.products_id=p.id AND date(order_date) BETWEEN bdate and edate) p

	LEFT OUTER JOIN(select operation_type_code,products_id,sum(quantity) as quantity,sum(actual_price * quantity) as summ
		from v_operations where operation_type_code='ppl' AND date(order_date) BETWEEN bdate and edate
		group by operation_type_code,products_id ) ppl ON (p.id=ppl.products_id)

	LEFT OUTER JOIN(select operation_type_code,products_id,sum(quantity) as quantity,sum(actual_price * quantity) as summ
		from v_operations where operation_type_code='pmn' AND date(order_date) BETWEEN bdate and edate
		group by operation_type_code,products_id ) pmn ON (p.id=pmn.products_id)

	LEFT OUTER JOIN(select operation_type_code,products_id,sum(quantity) as quantity,sum(actual_price * quantity) as summ
		from v_operations where operation_type_code='psl' AND date(order_date) BETWEEN bdate and edate
		group by operation_type_code,products_id ) psl ON (p.id=psl.products_id)

	LEFT OUTER JOIN(select operation_type_code,products_id,sum(quantity) as quantity,sum(actual_price * quantity) as summ
		from v_operations where operation_type_code='prt' AND date(order_date) BETWEEN bdate and edate
		group by operation_type_code,products_id ) prt ON (p.id=prt.products_id)


where pg.id = p.products_groups_id;
  -- --------------------------------------------------------

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rpt_prod_select_moving_2(date, date); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rpt_prod_select_moving_2(bdate date, edate date) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
	select
	pg.id as groups_id,
	pg."name" as groups_name,
	p.id as products_id,
	p."name" as products_name,

	ppl.quantity as ppl_quantity,
	ppl.summ as ppl_summ,

	pmn.quantity as pmn_quantity,
	pmn.summ as pmn_summ,

	psl.quantity as psl_quantity,
	psl.summ as psl_summ,

	prt.quantity as prt_quantity,
	prt.summ as prt_summ


	from products_groups pg, products p

	LEFT OUTER JOIN(select operation_type_code,products_id,sum(quantity) as quantity,sum(actual_price * quantity) as summ
		from v_operations where operation_type_code='ppl' AND date(order_date) BETWEEN bdate and edate
		group by operation_type_code,products_id ) ppl ON (p.id=ppl.products_id)

	LEFT OUTER JOIN(select operation_type_code,products_id,sum(quantity) as quantity,sum(actual_price * quantity) as summ
		from v_operations where operation_type_code='pmn' AND date(order_date) BETWEEN bdate and edate
		group by operation_type_code,products_id ) pmn ON (p.id=pmn.products_id)

	LEFT OUTER JOIN(select operation_type_code,products_id,sum(quantity) as quantity,sum(actual_price * quantity) as summ
		from v_operations where operation_type_code='psl' AND date(order_date) BETWEEN bdate and edate
		group by operation_type_code,products_id ) psl ON (p.id=psl.products_id)

	LEFT OUTER JOIN(select operation_type_code,products_id,sum(quantity) as quantity,sum(actual_price * quantity) as summ
		from v_operations where operation_type_code='prt' AND date(order_date) BETWEEN bdate and edate
		group by operation_type_code,products_id ) prt ON (p.id=prt.products_id)


	where pg.id = p.products_groups_id



order by pg.id,p.id;
  -- --------------------------------------------------------

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rpt_z(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rpt_z(in_z_report_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
preZ integer := 0;
BEGIN
preZ := get_prev_z_for_z(in_z_report_id);

  OPEN rc FOR
(select 0 as rang, 'Кассовые операции' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
0 as product_group_id , 'Касса' as product_group_name,
0 as product_id, 'у.е.' as product_name, '' as scod,
i.actual_price, 1 as quantity, i.actual_price as summ, o.description

from orders o, items i, orders_operations_types ot
where o.id = i.orders_id and o.operation_type_code = ot.type_code
and o.id < in_z_report_id and o.id > preZ
and o.operation_type_code in ('mpl','mmn')
order by o.order_date)

union

(select 1 as rang, 'Прихо товара' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
pg.id as product_group_id , pg."name" as product_group_name,
i.products_id as product_id, p."name" as product_name, p.scod,
i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
--and o.id < in_z_report_id and o.id > preZ
and o.id < in_z_report_id and o.id > preZ
and i.products_id = p.id
and p.products_groups_id = pg.id
and o.operation_type_code = 'ppl'
order by o.order_date)

union

(select 2 as rang, 'Списание товара' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
pg.id as product_group_id , pg."name" as product_group_name,
i.products_id as product_id, p."name" as product_name, p.scod,
i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
--and o.id < in_z_report_id and o.id > preZ
and o.id < in_z_report_id and o.id > preZ
and i.products_id = p.id
and p.products_groups_id = pg.id
and o.operation_type_code = 'pmn'
order by o.order_date)

union

(select 3 as rang, 'Продажа товара' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
pg.id as product_group_id , pg."name" as product_group_name,
i.products_id as product_id, p."name" as product_name, p.scod,
i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
--and o.id < in_z_report_id and o.id > preZ
and o.id < in_z_report_id and o.id > preZ
and i.products_id = p.id
and p.products_groups_id = pg.id
and o.operation_type_code = 'psl'
order by o.order_date)

union

(select 4 as rang, 'Возврат товара' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
pg.id as product_group_id , pg."name" as product_group_name,
i.products_id as product_id, p."name" as product_name, p.scod,
i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
--and o.id < in_z_report_id and o.id > preZ
and o.id < in_z_report_id and o.id > preZ
and i.products_id = p.id
and p.products_groups_id = pg.id
and o.operation_type_code = 'prt'
order by o.order_date);

RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rpt_z_ob_for_date(date, date); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rpt_z_ob_for_date(in_bdate date, in_edate date) RETURNS refcursor
    AS $$ DECLARE rc refcursor;

BEGIN


  OPEN rc FOR
(select 0 as rang, 'Кассовые операции' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
0 as product_group_id , 'Касса' as product_group_name,
0 as product_id, 'у.е.' as product_name, '' as scod,
i.actual_price, 1 as quantity, i.actual_price as summ, o.description

from orders o, items i, orders_operations_types ot
where o.id = i.orders_id and o.operation_type_code = ot.type_code
and o.order_date between in_bdate and in_edate
and o.operation_type_code in ('mpl','mmn')
order by o.order_date)

union

(select 1 as rang, 'Прихо товара' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
pg.id as product_group_id , pg."name" as product_group_name,
i.products_id as product_id, p."name" as product_name, p.scod,
i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
--and o.id < in_z_report_id and o.id > preZ
and o.order_date between in_bdate and in_edate
and i.products_id = p.id
and p.products_groups_id = pg.id
and o.operation_type_code = 'ppl'
order by o.order_date)

union

(select 2 as rang, 'Списание товара' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
pg.id as product_group_id , pg."name" as product_group_name,
i.products_id as product_id, p."name" as product_name, p.scod,
i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
--and o.id < in_z_report_id and o.id > preZ
and o.order_date between in_bdate and in_edate
and i.products_id = p.id
and p.products_groups_id = pg.id
and o.operation_type_code = 'pmn'
order by o.order_date)

union

(select 3 as rang, 'Продажа товара' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
pg.id as product_group_id , pg."name" as product_group_name,
i.products_id as product_id, p."name" as product_name, p.scod,
i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
--and o.id < in_z_report_id and o.id > preZ
and o.order_date between in_bdate and in_edate
and i.products_id = p.id
and p.products_groups_id = pg.id
and o.operation_type_code = 'psl'
order by o.order_date)

union

(select 4 as rang, 'Возврат товара' as rang_descr,
o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
pg.id as product_group_id , pg."name" as product_group_name,
i.products_id as product_id, p."name" as product_name, p.scod,
i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
--and o.id < in_z_report_id and o.id > preZ
and o.order_date between in_bdate and in_edate
and i.products_id = p.id
and p.products_groups_id = pg.id
and o.operation_type_code = 'prt'
order by o.order_date);

RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rpt_z_report_cass(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rpt_z_report_cass(in_z_report_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
	preZ integer := 0;
BEGIN
	preZ := get_prev_z_for_z(in_z_report_id);

  OPEN rc FOR
select 	o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
	i.actual_price, o.description

from orders o, items i, orders_operations_types ot
where o.id = i.orders_id and o.operation_type_code = ot.type_code
	and o.id < in_z_report_id and o.id > preZ
	and o.operation_type_code in ('mpl','mmn')
order by o.order_date;

RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: rpt_z_report_products(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION rpt_z_report_products(in_z_report_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
	preZ integer := 0;
BEGIN

	preZ := get_prev_z_for_z(in_z_report_id);

  OPEN rc FOR

(select 1 as rang,
	o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
	pg.id as product_group_id , pg."name" as product_group_name,
	i.products_id as product_id, p."name" as product_name, p.scod,
	i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
	and o.id < in_z_report_id and o.id > preZ
	and i.products_id = p.id
	and p.products_groups_id = pg.id
	and o.operation_type_code = 'ppl'
order by o.order_date)

union

(select 2 as rang,
	o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
	pg.id as product_group_id , pg."name" as product_group_name,
	i.products_id as product_id, p."name" as product_name, p.scod,
	i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
	and o.id < in_z_report_id and o.id > preZ
	and i.products_id = p.id
	and p.products_groups_id = pg.id
	and o.operation_type_code = 'pmn'
order by o.order_date)

union

(select 3 as rang,
	o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
	pg.id as product_group_id , pg."name" as product_group_name,
	i.products_id as product_id, p."name" as product_name, p.scod,
	i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
	and o.id < in_z_report_id and o.id > preZ
	and i.products_id = p.id
	and p.products_groups_id = pg.id
	and o.operation_type_code = 'psl'
order by o.order_date)

union

(select 4 as rang,
	o.id, o.order_date, o.operation_type_code, ot.description as operation_description,
	pg.id as product_group_id , pg."name" as product_group_name,
	i.products_id as product_id, p."name" as product_name, p.scod,
	i.actual_price, i.quantity, (i.actual_price * i.quantity) as summ, o.description

from orders o, items i, orders_operations_types ot, products p, products_groups pg
where o.id = i.orders_id and o.operation_type_code = ot.type_code
	and o.id < in_z_report_id and o.id > preZ
	and i.products_id = p.id
	and p.products_groups_id = pg.id
	and o.operation_type_code = 'prt'
order by o.order_date);

RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: select_products_quantity_for_id(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION select_products_quantity_for_id(t_product_id integer) RETURNS numeric
    AS $$ DECLARE quantity numeric;
BEGIN

  select sum(case when(o.operation_type_code in ('pmn','psl')) then
              ( - i.quantity) else i.quantity end )
    into quantity
    from orders o, items i
    where o.id = i.orders_id AND i.products_id=t_product_id;

  if quantity is not null then
    return quantity;
  else
    return 0;
  end if;

END; $$
    LANGUAGE plpgsql;


--
-- Name: select_products_quantity_for_id_with_step(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION select_products_quantity_for_id_with_step(t_product_id integer, OUT quantity numeric, OUT step numeric) RETURNS record
    AS $$ DECLARE
  product_measures_type boolean;
BEGIN

  select sum(case when(o.operation_type_code in ('pmn','psl')) then
              ( - i.quantity) else i.quantity end )
    into quantity
    from orders o, items i
    where o.id = i.orders_id AND i.products_id=t_product_id;

  if quantity is null then
    quantity := 0;
  end if;

  SELECT rb.mtype INTO product_measures_type
    FROM rb_measures rb, products p
    WHERE p.id=t_product_id AND p.measures_id=rb.id;

  IF product_measures_type IS TRUE THEN
    step := 0.01;
  ELSE
    step := 1;
  END IF;

END; $$
    LANGUAGE plpgsql;


--
-- Name: select_selling_orders_and_products_all(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION select_selling_orders_and_products_all() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  --RAISE EXCEPTION '';

  OPEN rc FOR
   SELECT  o.id,
        o.order_date,
        i.products_id,
        p.products_groups_id,

        p."name" as product_name,
        p.description,
        p.scod,
        i.quantity,
        p.measures_id,
        m."name" as quantityName,
        i.actual_price

    FROM orders o, items i, products p, rb_measures m

    WHERE o.id = i.orders_id
      AND i.products_id = p.id
      AND p.measures_id = m.id
      AND o.operation_type_code in ('psl')
    ORDER BY o.id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: select_selling_orders_and_products_by_customer_id(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION select_selling_orders_and_products_by_customer_id(in_customer_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  --RAISE EXCEPTION '';

  OPEN rc FOR
   SELECT  o.id,
        o.order_date,
        i.products_id,
        p.products_groups_id,

        p."name" as product_name,
        p.description,
        p.scod,
        i.quantity,
        p.measures_id,
        m."name" as quantityName,
        i.actual_price

    FROM orders o, items i, products p, rb_measures m

    WHERE o.customer_id = in_customer_id
      AND o.id = i.orders_id
      AND i.products_id = p.id
      AND p.measures_id = m.id
      AND o.operation_type_code in ('psl')
    ORDER BY o.id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: select_selling_orders_and_products_by_date(date); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION select_selling_orders_and_products_by_date(t_b_ship_date date) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  --RAISE EXCEPTION '';

  OPEN rc FOR
   SELECT  o.id,
        o.order_date,
        i.products_id,
        p.products_groups_id,

        p."name" as product_name,
        p.description,
        p.scod,
        i.quantity,
        p.measures_id,
        m."name" as quantityName,
        i.actual_price

    FROM orders o, items i, products p, rb_measures m

    WHERE o.order_date BETWEEN t_b_ship_date AND  (t_b_ship_date + interval '1 day')
      AND o.id = i.orders_id
      AND i.products_id = p.id
      AND p.measures_id = m.id
      AND o.operation_type_code in ('psl')
    ORDER BY o.id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: select_selling_orders_and_products_by_order_id(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION select_selling_orders_and_products_by_order_id(t_order_id integer) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  --RAISE EXCEPTION '';

  OPEN rc FOR
   SELECT  o.id,
        o.order_date,
        i.products_id,
        p.products_groups_id,

        p."name" as product_name,
        p.description,
        p.scod,
        i.quantity,
        p.measures_id,
        m."name" as quantityName,
        i.actual_price

    FROM orders o, items i, products p, rb_measures m

    WHERE o.id = t_order_id
      AND o.id = i.orders_id
      AND i.products_id = p.id
      AND p.measures_id = m.id
      AND o.operation_type_code in ('psl')
    ORDER BY o.id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: select_selling_orders_and_products_by_scod(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION select_selling_orders_and_products_by_scod(t_scod character varying) RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  --RAISE EXCEPTION '';

  OPEN rc FOR
   SELECT  o.id,
        o.order_date,
        i.products_id,
        p.products_groups_id,

        p."name" as product_name,
        p.description,
        p.scod,
        i.quantity,
        p.measures_id,
        m."name" as quantityName,
        i.actual_price

    FROM orders o, items i, products p, rb_measures m

    WHERE p.scod = t_scod
      AND o.id = i.orders_id
      AND i.products_id = p.id
      AND p.measures_id = m.id
      AND o.operation_type_code in ('psl')
    ORDER BY o.id;

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: select_z_reports(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION select_z_reports() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
	select 	get_prev_z_for_z(o.id) as pre_id,
		o.id, o.order_date, i.actual_price, o.description
	from orders o, items i
	where o.id = i.orders_id
		and operation_type_code = 'z';
  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: set_user_access(integer, character varying, boolean); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION set_user_access(in_user_id integer, in_access_kod character varying, in_access boolean) RETURNS void
    AS $$ DECLARE
	t_access_id integer := null;
	t_is_accesseble integer := null;
BEGIN

	IF in_access IS TRUE THEN -- Если установить то добавляем

		SELECT 1 INTO t_is_accesseble FROM user_access ua, appaccess ap
			WHERE ua.user_id = in_user_id AND ua.access_id = ap.id AND ap.kod = in_access_kod;

		IF t_is_accesseble IS NULL THEN -- Если еще не имеется то добавляем

			SELECT id INTO t_access_id FROM appaccess WHERE kod = in_access_kod;

			INSERT INTO user_access(
				user_id, access_id)
				VALUES (in_user_id, t_access_id);

		END IF;
	ELSE -- Иначе удаляем
		DELETE FROM user_access WHERE user_id = in_user_id AND access_id = (SELECT id FROM appaccess WHERE kod = in_access_kod);
	END IF;

END; $$
    LANGUAGE plpgsql;


--
-- Name: user_login(character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION user_login(user_name character varying, user_passwod character varying) RETURNS refcursor
    AS $$ DECLARE rc refcursor;

BEGIN

  OPEN rc FOR
    SELECT id,"name",isadmin,description,
    (SELECT svalue FROM conf WHERE kod='user') as user,
    (SELECT svalue FROM conf WHERE kod='passwd') as passwd

	FROM users
	WHERE "name"=user_name AND passwd=user_passwod;

  RETURN rc;

END; $$
    LANGUAGE plpgsql STRICT SECURITY DEFINER;


--
-- Name: users_delete(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION users_delete(t_id integer) RETURNS void
    AS $$ DECLARE
BEGIN

  DELETE FROM users
    WHERE id = t_id;

END; $$
    LANGUAGE plpgsql;


--
-- Name: users_insert(character varying, character varying, boolean, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION users_insert(t_name character varying, t_passwd character varying, t_isadmin boolean, t_description character varying) RETURNS integer
    AS $$ DECLARE
BEGIN

  INSERT INTO users ( NAME,passwd,isadmin,description )
    VALUES (t_name,t_passwd,t_isadmin,t_description );

  RETURN currval('users_id_seq');

END; $$
    LANGUAGE plpgsql;


--
-- Name: users_select(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION users_select() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT id, "name", passwd, isadmin, description
      FROM users ORDER BY "name";

  RETURN rc;

END; $$
    LANGUAGE plpgsql;


--
-- Name: users_select_for_loginer(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION users_select_for_loginer() RETURNS refcursor
    AS $$ DECLARE rc refcursor;
BEGIN

  OPEN rc FOR
    SELECT id, "name"
      FROM users ORDER BY "name";

  RETURN rc;

END; $$
    LANGUAGE plpgsql STRICT SECURITY DEFINER;


--
-- Name: users_update(integer, character varying, character varying, boolean, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION users_update(t_id integer, t_name character varying, t_passwd character varying, t_isadmin boolean, t_description character varying) RETURNS void
    AS $$ DECLARE
BEGIN

  UPDATE users
      SET NAME = t_name,passwd = t_passwd,isadmin=t_isadmin,description=t_description
    WHERE id = t_id;

  RETURN;

END; $$
    LANGUAGE plpgsql;


--
-- Name: users_update(integer, character varying, boolean, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION users_update(t_id integer, t_name character varying, t_isadmin boolean, t_description character varying) RETURNS void
    AS $$ DECLARE
BEGIN

  UPDATE users
      SET NAME = t_name,isadmin=t_isadmin,description=t_description
    WHERE id = t_id;

  RETURN;

END; $$
    LANGUAGE plpgsql;


--
-- Name: users_update_password(integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION users_update_password(t_id integer, t_passwd character varying) RETURNS void
    AS $$ DECLARE
BEGIN

  UPDATE users
      SET passwd = t_passwd
    WHERE id = t_id;

  RETURN;

END; $$
    LANGUAGE plpgsql;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: appaccess; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE appaccess (
    kod character varying(50) NOT NULL,
    id integer NOT NULL
);


--
-- Name: access_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE access_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: access_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE access_id_seq OWNED BY appaccess.id;


--
-- Name: conf; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE conf (
    kod character varying(30) NOT NULL,
    svalue character varying,
    dvalue numeric
);


--
-- Name: customers; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE customers (
    id integer NOT NULL,
    fio character varying(100) NOT NULL,
    address character varying(500),
    phone_number character varying(20),
    phone_number_2 character varying(20),
    email character varying(50),
    description character varying(500),
    ur boolean DEFAULT false NOT NULL,
    short_name character varying(50),
    doc_type integer NOT NULL,
    doc character varying(50),
    sex numeric(1,0) DEFAULT 0 NOT NULL
);


--
-- Name: customers_balances; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE customers_balances (
    id integer NOT NULL,
    order_id integer NOT NULL,
    customer_id integer NOT NULL,
    summ numeric(20,2) DEFAULT 0 NOT NULL,
    description character varying
);


--
-- Name: customers_balances_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE customers_balances_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: customers_balances_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE customers_balances_id_seq OWNED BY customers_balances.id;


--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE customers_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE customers_id_seq OWNED BY customers.id;


--
-- Name: items; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE items (
    id integer NOT NULL,
    orders_id integer NOT NULL,
    products_id integer,
    actual_price numeric(10,2) DEFAULT 0 NOT NULL,
    quantity numeric(10,3) DEFAULT 0 NOT NULL,
    CONSTRAINT items_ck_2 CHECK (((0)::numeric <= get_cass_balance()))
);


--
-- Name: items_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE items_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE items_id_seq OWNED BY items.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE orders (
    id integer NOT NULL,
    ship_date timestamp without time zone NOT NULL,
    order_date timestamp without time zone,
    operation_type_code character varying(3) NOT NULL,
    description character varying(255),
    sub_order_id integer,
    user_id integer NOT NULL,
    customer_id integer,
    credit boolean DEFAULT false NOT NULL,
    CONSTRAINT orders_ck_1 CHECK (((order_date <= ship_date) OR (order_date IS NULL))),
    CONSTRAINT orders_ck_2 CHECK ((((credit = true) AND (customer_id IS NOT NULL)) OR (credit = false)))
);


--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE orders_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE orders_id_seq OWNED BY orders.id;


--
-- Name: orders_operations_types; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE orders_operations_types (
    type_code character varying(3) NOT NULL,
    description character varying(255) NOT NULL,
    supertype character varying(20)
);


--
-- Name: products; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE products (
    id integer NOT NULL,
    products_groups_id integer,
    name character varying(50) NOT NULL,
    description character varying(500),
    scod character varying(255),
    quantity numeric(10,3) DEFAULT 0,
    measures_id integer,
    list_price numeric(10,2) DEFAULT 0 NOT NULL,
    spec_price numeric(10,2) DEFAULT 0 NOT NULL,
    percent_discount numeric(4,4) DEFAULT 0 NOT NULL,
    CONSTRAINT products_ck_1 CHECK (((((percent_discount > (0)::numeric) AND (spec_price = (0)::numeric)) OR ((percent_discount = (0)::numeric) AND (spec_price > (0)::numeric))) OR ((percent_discount = (0)::numeric) AND (spec_price = (0)::numeric)))),
    CONSTRAINT products_ck_2 CHECK ((spec_price <= list_price)),
    CONSTRAINT products_ck_3 CHECK (((percent_discount <= (100)::numeric) AND (percent_discount >= (0)::numeric))),
    CONSTRAINT products_ck_4 CHECK ((((spec_price >= (0)::numeric) AND (list_price >= (0)::numeric)) AND (quantity >= (0)::numeric)))
);


--
-- Name: products_groups; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE products_groups (
    id integer NOT NULL,
    sub_id integer,
    name character varying(30) NOT NULL,
    description character varying(500)
);


--
-- Name: products_groups_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE products_groups_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: products_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE products_groups_id_seq OWNED BY products_groups.id;


--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE products_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE products_id_seq OWNED BY products.id;


--
-- Name: quantity; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE quantity (
    sum numeric
);


--
-- Name: rb_doc_types; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE rb_doc_types (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(255)
);


--
-- Name: TABLE rb_doc_types; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE rb_doc_types IS 'Справочник';


--
-- Name: COLUMN rb_doc_types.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN rb_doc_types.id IS 'Цифровой идентификатор';


--
-- Name: COLUMN rb_doc_types.name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN rb_doc_types.name IS 'Наименование';


--
-- Name: COLUMN rb_doc_types.description; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN rb_doc_types.description IS 'Описание';


--
-- Name: rb_doc_types_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE rb_doc_types_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: rb_doc_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE rb_doc_types_id_seq OWNED BY rb_doc_types.id;


--
-- Name: rb_measures; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE rb_measures (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    description character varying(255),
    mtype boolean DEFAULT false NOT NULL
);


--
-- Name: TABLE rb_measures; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE rb_measures IS 'Справочник единиц измерений';


--
-- Name: rb_measures_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE rb_measures_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: rb_measures_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE rb_measures_id_seq OWNED BY rb_measures.id;


--
-- Name: user_access; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE user_access (
    user_id integer NOT NULL,
    access_id integer NOT NULL
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE users (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    passwd character varying(32) NOT NULL,
    isadmin boolean DEFAULT false NOT NULL,
    description character varying(255)
);


--
-- Name: COLUMN users.name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN users.name IS 'Имя пользователя';


--
-- Name: COLUMN users.passwd; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN users.passwd IS 'Хэш пароля';


--
-- Name: COLUMN users.isadmin; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN users.isadmin IS 'Признак администратора';


--
-- Name: COLUMN users.description; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN users.description IS 'Описание';


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE users_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: v_operations; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW v_operations AS
    SELECT o.id AS orders_id, o.ship_date, o.order_date, o.operation_type_code, o.description, o.sub_order_id, i.id AS items_id, i.products_id, i.actual_price, i.quantity FROM orders o, items i WHERE (o.id = i.orders_id);


--
-- Name: v_products_quantity; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW v_products_quantity AS
    SELECT products.id, (SELECT select_products_quantity_for_id(products.id) AS select_products_quantity_for_id) AS quantity FROM products;


--
-- Name: v_select_cass; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW v_select_cass AS
    SELECT o.id, o.ship_date, o.order_date, o.operation_type_code, CASE WHEN ((o.operation_type_code)::text = ANY ((ARRAY['mpl'::character varying, 'psl'::character varying])::text[])) THEN (i.actual_price * i.quantity) ELSE (- (i.actual_price * i.quantity)) END AS case_many, oot.description AS oot_description, o.description FROM orders o, items i, orders_operations_types oot WHERE (((o.id = i.orders_id) AND ((o.operation_type_code)::text = ANY ((ARRAY['mpl'::character varying, 'mmn'::character varying, 'prt'::character varying, 'psl'::character varying, 'z'::character varying])::text[]))) AND ((o.operation_type_code)::text = (oot.type_code)::text));


--
-- Name: v_select_cass_after_last_z; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW v_select_cass_after_last_z AS
    SELECT o.id, o.ship_date, o.order_date, o.operation_type_code, CASE WHEN ((o.operation_type_code)::text = ANY ((ARRAY['mpl'::character varying, 'psl'::character varying])::text[])) THEN (i.actual_price * i.quantity) ELSE (- (i.actual_price * i.quantity)) END AS case_many, oot.description AS oot_description, o.description FROM orders o, items i, orders_operations_types oot WHERE ((((o.id = i.orders_id) AND (o.id > get_last_z_report_id())) AND ((o.operation_type_code)::text = ANY ((ARRAY['mpl'::character varying, 'mmn'::character varying, 'prt'::character varying, 'psl'::character varying, 'z'::character varying])::text[]))) AND ((o.operation_type_code)::text = (oot.type_code)::text));


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE appaccess ALTER COLUMN id SET DEFAULT nextval('access_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE customers ALTER COLUMN id SET DEFAULT nextval('customers_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE customers_balances ALTER COLUMN id SET DEFAULT nextval('customers_balances_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE items ALTER COLUMN id SET DEFAULT nextval('items_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE orders ALTER COLUMN id SET DEFAULT nextval('orders_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE products ALTER COLUMN id SET DEFAULT nextval('products_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE products_groups ALTER COLUMN id SET DEFAULT nextval('products_groups_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE rb_doc_types ALTER COLUMN id SET DEFAULT nextval('rb_doc_types_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE rb_measures ALTER COLUMN id SET DEFAULT nextval('rb_measures_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: access_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY appaccess
    ADD CONSTRAINT access_pk PRIMARY KEY (id);


--
-- Name: appaccess_uk1; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY appaccess
    ADD CONSTRAINT appaccess_uk1 UNIQUE (kod);


--
-- Name: conf_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY conf
    ADD CONSTRAINT conf_pk PRIMARY KEY (kod);


--
-- Name: customers_balances_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY customers_balances
    ADD CONSTRAINT customers_balances_pk PRIMARY KEY (id);


--
-- Name: customers_balances_uk1; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY customers_balances
    ADD CONSTRAINT customers_balances_uk1 UNIQUE (order_id);


--
-- Name: customers_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_pk PRIMARY KEY (id);


--
-- Name: customers_uk1; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_uk1 UNIQUE (short_name);


--
-- Name: items_idx_1; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY items
    ADD CONSTRAINT items_idx_1 UNIQUE (orders_id, products_id);


--
-- Name: items_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY items
    ADD CONSTRAINT items_pk PRIMARY KEY (id);


--
-- Name: orders_idx_1; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_idx_1 UNIQUE (order_date);


--
-- Name: orders_idx_2; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_idx_2 UNIQUE (ship_date);


--
-- Name: orders_operations_types_idx1; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY orders_operations_types
    ADD CONSTRAINT orders_operations_types_idx1 UNIQUE (description);


--
-- Name: orders_operations_types_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY orders_operations_types
    ADD CONSTRAINT orders_operations_types_pk PRIMARY KEY (type_code);


--
-- Name: orders_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);


--
-- Name: products_groups_1_idx; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY products_groups
    ADD CONSTRAINT products_groups_1_idx UNIQUE (sub_id, name);


--
-- Name: products_groups_2_idx; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY products_groups
    ADD CONSTRAINT products_groups_2_idx UNIQUE (id, sub_id);


--
-- Name: products_groups_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY products_groups
    ADD CONSTRAINT products_groups_pk PRIMARY KEY (id);


--
-- Name: products_idx_1; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_idx_1 UNIQUE (scod);


--
-- Name: products_idx_2; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_idx_2 UNIQUE (id, products_groups_id);


--
-- Name: products_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pk PRIMARY KEY (id);


--
-- Name: rb_doc_types_idx_1; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY rb_doc_types
    ADD CONSTRAINT rb_doc_types_idx_1 UNIQUE (name);


--
-- Name: rb_doc_types_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY rb_doc_types
    ADD CONSTRAINT rb_doc_types_pk PRIMARY KEY (id);


--
-- Name: rb_measures_idx_1; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY rb_measures
    ADD CONSTRAINT rb_measures_idx_1 UNIQUE (name);


--
-- Name: rb_measures_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY rb_measures
    ADD CONSTRAINT rb_measures_pk PRIMARY KEY (id);


--
-- Name: user_access_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY user_access
    ADD CONSTRAINT user_access_pk PRIMARY KEY (user_id, access_id);


--
-- Name: users_name; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_name UNIQUE (name);


--
-- Name: users_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- Name: check_maney_balance; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER check_maney_balance
    AFTER INSERT OR UPDATE ON items
    FOR EACH STATEMENT
    EXECUTE PROCEDURE check_cass_balance();


--
-- Name: check_product_balance; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER check_product_balance
    AFTER INSERT OR UPDATE ON items
    FOR EACH ROW
    EXECUTE PROCEDURE check_product_balance();


--
-- Name: customer_balances_fk2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY customers_balances
    ADD CONSTRAINT customer_balances_fk2 FOREIGN KEY (customer_id) REFERENCES customers(id);


--
-- Name: customers_balances_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY customers_balances
    ADD CONSTRAINT customers_balances_fk1 FOREIGN KEY (order_id) REFERENCES orders(id);


--
-- Name: customers_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_fk1 FOREIGN KEY (doc_type) REFERENCES rb_doc_types(id);


--
-- Name: items_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY items
    ADD CONSTRAINT items_fk_1 FOREIGN KEY (orders_id) REFERENCES orders(id);


--
-- Name: items_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY items
    ADD CONSTRAINT items_fk_2 FOREIGN KEY (products_id) REFERENCES products(id);


--
-- Name: orders_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_fk1 FOREIGN KEY (operation_type_code) REFERENCES orders_operations_types(type_code);


--
-- Name: orders_fk2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_fk2 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: orders_fk3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_fk3 FOREIGN KEY (customer_id) REFERENCES customers(id);


--
-- Name: orders_fk_this_1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_fk_this_1 FOREIGN KEY (sub_order_id) REFERENCES orders(id);


--
-- Name: products_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_fk_1 FOREIGN KEY (products_groups_id) REFERENCES products_groups(id);


--
-- Name: products_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_fk_2 FOREIGN KEY (measures_id) REFERENCES rb_measures(id);


--
-- Name: products_grous_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY products_groups
    ADD CONSTRAINT products_grous_fk_1 FOREIGN KEY (sub_id) REFERENCES products_groups(id);


--
-- Name: user_access_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_access
    ADD CONSTRAINT user_access_fk1 FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_access_fk2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_access
    ADD CONSTRAINT user_access_fk2 FOREIGN KEY (access_id) REFERENCES appaccess(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

--REVOKE ALL ON SCHEMA public FROM PUBLIC;
--REVOKE ALL ON SCHEMA public FROM postgres;
--GRANT ALL ON SCHEMA public TO postgres;
--GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: user_login(character varying, character varying); Type: ACL; Schema: public; Owner: -
--

--REVOKE ALL ON FUNCTION user_login(user_name character varying, user_passwod character varying) FROM PUBLIC;
--REVOKE ALL ON FUNCTION user_login(user_name character varying, user_passwod character varying) FROM miner;
--GRANT ALL ON FUNCTION user_login(user_name character varying, user_passwod character varying) TO miner;
--GRANT ALL ON FUNCTION user_login(user_name character varying, user_passwod character varying) TO PUBLIC;
GRANT ALL ON FUNCTION user_login(user_name character varying, user_passwod character varying) TO connector;


--
-- Name: users_select_for_loginer(); Type: ACL; Schema: public; Owner: -
--

--REVOKE ALL ON FUNCTION users_select_for_loginer() FROM PUBLIC;
--REVOKE ALL ON FUNCTION users_select_for_loginer() FROM miner;
--GRANT ALL ON FUNCTION users_select_for_loginer() TO miner;
--GRANT ALL ON FUNCTION users_select_for_loginer() TO PUBLIC;
GRANT ALL ON FUNCTION users_select_for_loginer() TO connector;


--
-- PostgreSQL database dump complete
--
