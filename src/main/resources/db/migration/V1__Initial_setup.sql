-- V1__create_schema.sql

CREATE SCHEMA IF NOT EXISTS ecommerce;
SET search_path TO ecommerce;

-- User types
CREATE TABLE user_type (
    id UUID PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- System users
CREATE TABLE app_user (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_type_id UUID REFERENCES user_type(id),
    created_by UUID REFERENCES app_user(id),
    created_at TIMESTAMP DEFAU2LT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES app_user(id),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Customers
CREATE TABLE customer (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES app_user(id) NOT NULL,
    name VARCHAR(255) NOT NULL,
    contact VARCHAR(50),
    address TEXT,
    active BOOLEAN DEFAULT TRUE,
    created_by UUID REFERENCES app_user(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES app_user(id),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Products
CREATE TABLE product (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_by UUID REFERENCES app_user(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES app_user(id),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Product variations (SKUs)
CREATE TABLE product_sku (
    id UUID PRIMARY KEY,
    product_id UUID REFERENCES product(id) NOT NULL,
    attributes JSONB,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

-- Orders
CREATE TABLE order_header (
    id UUID PRIMARY KEY,
    customer_id UUID REFERENCES customer(id),
    status VARCHAR(30) NOT NULL CHECK (status IN (
        'waiting_payment',
        'paid',
        'payment_denied',
        'processing',
        'shipped',
        'delivered'
    )),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2),
    created_by UUID REFERENCES app_user(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES app_user(id),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Order items
CREATE TABLE order_item (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL REFERENCES order_header(id),
    sku_id UUID NOT NULL REFERENCES product_sku(id),
    quantity INT NOT NULL CHECK (quantity > 0),
    unit_price DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    created_by UUID REFERENCES app_user(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES app_user(id),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cart
CREATE TABLE cart (
    id UUID PRIMARY KEY,
    customer_id UUID REFERENCES customer(id),
    total DECIMAL(10,2) DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cart_item (
    id UUID PRIMARY KEY,
    cart_id UUID REFERENCES cart(id),
    sku_id UUID REFERENCES product_sku(id),
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

-- Sales report view
CREATE OR REPLACE VIEW sales_report AS
SELECT
    o.id AS order_id,
    DATE_TRUNC('day', o.order_date) AS period,
    SUM(oi.quantity * oi.unit_price) AS total_sales,
    SUM(oi.quantity) AS total_units_sold,
    o.status,
    p.id AS product_id,
    p.name AS product_name,
    SUM(oi.quantity) AS units_per_product,
    SUM(oi.subtotal) AS revenue_per_product
FROM
    order_header o
JOIN
    order_item oi ON o.id = oi.order_id
JOIN
    product_sku s ON oi.sku_id = s.id
JOIN
    product p ON s.product_id = p.id
GROUP BY
    o.id, period, p.id, p.name, o.status;

-- Indexes
CREATE INDEX idx_app_user_email ON app_user(email);
CREATE INDEX idx_order_customer ON order_header(customer_id);
CREATE INDEX idx_sku_product ON product_sku(product_id);
CREATE INDEX idx_order_item_sku ON order_item(sku_id);
