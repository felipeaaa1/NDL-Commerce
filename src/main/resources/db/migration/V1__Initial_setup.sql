CREATE SCHEMA IF NOT EXISTS ecommerce;
SET search_path TO ecommerce;


-- app_user
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'ecommerce' AND table_name = 'app_user') THEN
        RAISE NOTICE 'Já existe a tabela app_user';
    ELSE
        RAISE NOTICE 'Criando tabela app_user';
        CREATE TYPE user_type_enum AS ENUM ('ADMIN', 'COMMON');

        CREATE TABLE ecommerce.app_user (
            id UUID PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            email VARCHAR(255) UNIQUE NOT NULL,
            password VARCHAR(255) NOT NULL,
            user_type user_type_enum NOT NULL,
            created_by UUID REFERENCES ecommerce.app_user(id),
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_by UUID REFERENCES ecommerce.app_user(id),
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    END IF;
END $$;

-- customer
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'ecommerce' AND table_name = 'customer') THEN
        RAISE NOTICE 'Já existe a tabela customer';
    ELSE
        RAISE NOTICE 'Criando tabela customer';
        CREATE TABLE ecommerce.customer (
            id UUID PRIMARY KEY,
            user_id UUID REFERENCES ecommerce.app_user(id) NOT NULL,
            name VARCHAR(255) NOT NULL,
            contact VARCHAR(50),
            address TEXT,
            active BOOLEAN DEFAULT TRUE,
            created_by UUID REFERENCES ecommerce.app_user(id),
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_by UUID REFERENCES ecommerce.app_user(id),
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    END IF;
END $$;

-- product
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'ecommerce' AND table_name = 'product') THEN
        RAISE NOTICE 'Já existe a tabela product';
    ELSE
        RAISE NOTICE 'Criando tabela product';
        CREATE TABLE ecommerce.product (
            id UUID PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            description TEXT,
            created_by UUID REFERENCES ecommerce.app_user(id),
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_by UUID REFERENCES ecommerce.app_user(id),
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    END IF;
END $$;

-- product_sku
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'ecommerce' AND table_name = 'product_sku') THEN
        RAISE NOTICE 'Já existe a tabela product_sku';
    ELSE
        RAISE NOTICE 'Criando tabela product_sku';
        CREATE TABLE ecommerce.product_sku (
            id UUID PRIMARY KEY,
            product_id UUID REFERENCES ecommerce.product(id) NOT NULL,
            attributes JSONB,
            price DECIMAL(10,2) NOT NULL,
            stock INT NOT NULL
        );
    END IF;
END $$;

-- order_header
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'ecommerce' AND table_name = 'order_header') THEN
        RAISE NOTICE 'Já existe a tabela order_header';
    ELSE
        RAISE NOTICE 'Criando tabela order_header';
        CREATE TABLE ecommerce.order_header (
            id UUID PRIMARY KEY,
            customer_id UUID REFERENCES ecommerce.customer(id),
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
            created_by UUID REFERENCES ecommerce.app_user(id),
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_by UUID REFERENCES ecommerce.app_user(id),
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    END IF;
END $$;

-- order_item
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'ecommerce' AND table_name = 'order_item') THEN
        RAISE NOTICE 'Já existe a tabela order_item';
    ELSE
        RAISE NOTICE 'Criando tabela order_item';
        CREATE TABLE ecommerce.order_item (
            id UUID PRIMARY KEY,
            order_id UUID NOT NULL REFERENCES ecommerce.order_header(id),
            sku_id UUID NOT NULL REFERENCES ecommerce.product_sku(id),
            quantity INT NOT NULL CHECK (quantity > 0),
            unit_price DECIMAL(10,2) NOT NULL,
            subtotal DECIMAL(10,2) NOT NULL,
            created_by UUID REFERENCES ecommerce.app_user(id),
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_by UUID REFERENCES ecommerce.app_user(id),
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    END IF;
END $$;

-- cart
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'ecommerce' AND table_name = 'cart') THEN
        RAISE NOTICE 'Já existe a tabela cart';
    ELSE
        RAISE NOTICE 'Criando tabela cart';
        CREATE TABLE ecommerce.cart (
            id UUID PRIMARY KEY,
            customer_id UUID REFERENCES ecommerce.customer(id),
            total DECIMAL(10,2) DEFAULT 0,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    END IF;
END $$;

-- cart_item
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'ecommerce' AND table_name = 'cart_item') THEN
        RAISE NOTICE 'Já existe a tabela cart_item';
    ELSE
        RAISE NOTICE 'Criando tabela cart_item';
        CREATE TABLE ecommerce.cart_item (
            id UUID PRIMARY KEY,
            cart_id UUID REFERENCES ecommerce.cart(id),
            sku_id UUID REFERENCES ecommerce.product_sku(id),
            quantity INT NOT NULL,
            price DECIMAL(10,2) NOT NULL
        );
    END IF;
END $$;

-- Sales report view
CREATE OR REPLACE VIEW ecommerce.sales_report AS
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
    ecommerce.order_header o
JOIN
    ecommerce.order_item oi ON o.id = oi.order_id
JOIN
    ecommerce.product_sku s ON oi.sku_id = s.id
JOIN
    ecommerce.product p ON s.product_id = p.id
GROUP BY
    o.id, period, p.id, p.name, o.status;

-- Indexes
CREATE INDEX IF NOT EXISTS idx_app_user_email ON ecommerce.app_user(email);
CREATE INDEX IF NOT EXISTS idx_order_customer ON ecommerce.order_header(customer_id);
CREATE INDEX IF NOT EXISTS idx_sku_product ON ecommerce.product_sku(product_id);
CREATE INDEX IF NOT EXISTS idx_order_item_sku ON ecommerce.order_item(sku_id);
