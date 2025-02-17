ALTER TABLE order_product
ADD CONSTRAINT chk_value_non_negative_count CHECK (count >= 0);
ALTER TABLE order_product
ADD CONSTRAINT chk_value_non_negative_price CHECK (price >= 0);
ALTER TABLE measurement
ADD CONSTRAINT chk_value_non_negative_value CHECK (value >= 0);
ALTER TABLE stock
ADD CONSTRAINT chk_value_non_negative_quantity  CHECK (quantity >= 0);
ALTER TABLE product
ADD CONSTRAINT chk_value_non_negative_price  CHECK (price >= 0);