SELECT AVG(total_orders) AS avg_orders_per_client
FROM (
        SELECT u.id,
            COUNT(o.id) AS total_orders
        FROM users u
            LEFT JOIN orders o ON u.id = o.user_id_client
        WHERE o.date_create >= CURRENT_DATE - INTERVAL '30 days'
        GROUP BY u.id
    ) AS order_counts;