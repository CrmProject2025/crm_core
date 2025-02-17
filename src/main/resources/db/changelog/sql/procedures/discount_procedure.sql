CREATE OR REPLACE PROCEDURE calculate_discount(p_user_id BIGINT) LANGUAGE plpgsql AS $$
DECLARE total_orders INT;
discount INT;
BEGIN -- Получаем количество заказов клиента
SELECT COUNT(*) INTO total_orders
FROM orders
WHERE user_id_client = p_user_id;
-- Расчет скидки
IF total_orders > 50 THEN discount := 20;
ELSIF total_orders > 20 THEN discount := 10;
ELSE discount := 0;
END IF;
-- Обновление скидки для клиента
UPDATE users
SET information = 'Discount: ' || discount || '%'
WHERE id = p_user_id;
END;
$$;