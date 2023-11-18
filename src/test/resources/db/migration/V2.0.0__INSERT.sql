-- Insert sample data into "order" table
INSERT INTO "order" (address_id, comment, create_stamp, customer_id, status_id, sub_total, supplier_id, total)
VALUES
    (1, 'Delivery to 123 Main St', NOW(), 101, 1, 30.00, 201, 35.00),
    (2, 'Delivery to 456 Oak St', NOW(), 102, 1, 25.50, 202, 30.00),
    (3, 'Delivery to 789 Elm St', NOW(), 103, 1, 40.00, 203, 45.00),
    (4, 'Delivery to 321 Pine St', NOW(), 104, 1, 22.75, 204, 27.00),
    (5, 'Delivery to 567 Maple St', NOW(), 105, 1, 18.90, 205, 22.00),
    (6, 'Delivery to 876 Cedar St', NOW(), 106, 1, 35.25, 206, 40.00),
    (7, 'Delivery to 234 Birch St', NOW(), 107, 1, 28.50, 207, 33.00),
    (8, 'Delivery to 654 Walnut St', NOW(), 108, 1, 32.75, 208, 38.00),
    (9, 'Delivery to 987 Pine St', NOW(), 109, 1, 15.20, 209, 20.00),
    (10, 'Delivery to 876 Oak St', NOW(), 110, 1, 19.80, 210, 24.00);

-- Insert sample data into "order_line" table with calculated total and sub_total
INSERT INTO order_line (product_id, quantity, unit_gross_price, unit_net_price, order_id)
VALUES
    (301, 2, 7.50, 6.00, 1),
    (302, 1, 5.50, 4.50, 1),
    (303, 3, 8.00, 7.00, 2),
    (304, 2, 9.25, 8.00, 3),
    (305, 1, 6.00, 5.50, 4),
    (306, 2, 10.00, 9.00, 5),
    (307, 1, 7.50, 6.75, 6),
    (308, 3, 7.50, 6.00, 7),
    (309, 2, 12.00, 10.50, 8),
    (310, 1, 8.90, 7.50, 9),
    (311, 2, 9.90, 8.50, 10);

-- Update total and sub_total based on the multiplication
UPDATE order_line
SET total = quantity * unit_gross_price,
    sub_total = quantity * unit_net_price;