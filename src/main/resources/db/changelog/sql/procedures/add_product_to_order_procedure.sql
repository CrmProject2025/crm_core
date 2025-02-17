CREATE OR REPLACE PROCEDURE add_product_to_order(
        p_order_id BIGINT,
        p_product_id BIGINT,
        p_count INT,
        p_price NUMERIC(10, 2)
    ) LANGUAGE plpgsql AS $$ BEGIN -- Добавление товара в заказ
INSERT INTO order_product (order_id, product_id, count, price)
VALUES (p_order_id, p_product_id, p_count, p_price);
-- Обновление количества на складе
UPDATE stock
SET quantity = quantity - p_count
WHERE product_id = p_product_id
    AND warehouse_id = (
        SELECT warehouse_id
        FROM stock
        WHERE product_id = p_product_id
        LIMIT 1
    );
END;
$$;