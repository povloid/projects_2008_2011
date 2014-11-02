select 
	-- Сведения по операции
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
	u.description as user_description,

	-- Сведения по проведенной операции
	o.credit as credit,
	(case when(o.credit is true) then 'да' else 'нет' end ) as credit_ru,
	(select abs(sum(ii.actual_price * ii.quantity)) 
		from items ii WHERE ii.orders_id = o.id) as to_cass,
	(cb.summ) as to_balance,
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
	m."name" as measures_name,
	p.description,

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
		LEFT OUTER JOIN rb_measures m ON (p.measures_id = m.id)
		
		,orders_operations_types ot, users u
	where  
		--o.id = 396 -- ppl Приход товара
		--o.id = 527 -- pmn Списание товара
		
		--o.id = 563 --mpl
		--o.id = 546 --mmn
		
		--o.id = 539 -- z
		
		o.id = 562 --psl Продажа товара с кредитом
		--o.id = 567 --prt Возврат товара без балансных операций
		AND o.id=i.orders_id AND o.operation_type_code = type_code
		AND o.user_id = u.id --AND p.id is not null 
		AND i.actual_price >= 0