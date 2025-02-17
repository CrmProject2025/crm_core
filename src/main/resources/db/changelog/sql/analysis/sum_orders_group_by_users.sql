SELECT u.id AS user_id,
    u.username,
    COUNT(o.id) AS total_orders,
    COUNT(op.id) AS total_products,
    SUM(op.count * op.price) AS total_amount
FROM users u
    JOIN orders o ON u.id = o.user_id_client
    JOIN order_product op ON o.id = op.order_id
GROUP BY u.id,
    u.username
ORDER BY total_amount DESC;