/*
 * Create brand and category tables
 * @author Felipe Alves
 * @date 15/12/2025
 */

CREATE TABLE IF NOT EXISTS ecommerce.brand (
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    created_by UUID REFERENCES ecommerce.app_user(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES ecommerce.app_user(id),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ecommerce.category (
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    parent_id UUID REFERENCES ecommerce.category(id),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_by UUID REFERENCES ecommerce.app_user(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES ecommerce.app_user(id),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE INDEX IF NOT EXISTS idx_category_parent
ON ecommerce.category(parent_id);


ALTER TABLE IF EXISTS ecommerce.product DROP CONSTRAINT IF EXISTS product_brand_id_fkey;

ALTER TABLE IF EXISTS ecommerce.product
    ADD CONSTRAINT product_brand_id_fkey FOREIGN KEY (brand_id)
    REFERENCES ecommerce.brand(id);

ALTER TABLE IF EXISTS ecommerce.product DROP CONSTRAINT IF EXISTS product_category_id_fkey;

ALTER TABLE IF EXISTS ecommerce.product
    ADD CONSTRAINT product_category_id_fkey FOREIGN KEY (category_id)
    REFERENCES ecommerce.category(id);

