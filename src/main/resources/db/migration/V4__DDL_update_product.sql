/*
 * changing product table
 * @author Felipe Alves
 * @date 14/12/2025
 */

ALTER TABLE ecommerce.product
ADD COLUMN IF NOT EXISTS short_description VARCHAR(255) not null,
ADD COLUMN IF NOT EXISTS long_description TEXT,
ADD COLUMN IF NOT EXISTS brand_id UUID not null,
ADD COLUMN IF NOT EXISTS category_id UUID not null,
ADD COLUMN IF NOT EXISTS active BOOLEAN DEFAULT TRUE not null;
