CREATE OR REPLACE PROCEDURE create_order(
        p_user_id_client BIGINT,
        p_user_id_saler BIGINT,
        p_info VARCHAR,
        p_address VARCHAR
    ) LANGUAGE plpgsql AS $$ BEGIN -- Создание нового заказа
INSERT INTO orders (
        user_id_client,
        user_id_saler,
        info,
        address,
        date_create,
        status
    )
VALUES (
        p_user_id_client,
        p_user_id_saler,
        p_info,
        p_address,
        CURRENT_TIMESTAMP,
        'NEW'
    )
RETURNING id INTO p_order_id;
END;
$$;