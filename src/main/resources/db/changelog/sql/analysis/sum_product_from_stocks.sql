SELECT w.id AS warehouse_id,
    w.name AS warehouse_name,
    COUNT(s.id) AS total_products,
    SUM(s.quantity * p.price) AS total_value
FROM warehouse w
    JOIN stock s ON w.id = s.warehouse_id
    JOIN product p ON s.product_id = p.id
GROUP BY w.id,
    w.name
ORDER BY total_value DESC;