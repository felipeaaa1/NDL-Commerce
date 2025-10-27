/*
 * Adds authentication control columns to support JWT
 * @author Felipe Alves
 * @date 25/10/2025
 */

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'ecommerce'
          AND table_name = 'app_user'
          AND column_name = 'enabled'
    ) THEN
        ALTER TABLE ecommerce.app_user ADD COLUMN enabled BOOLEAN DEFAULT TRUE NOT NULL;
    END IF;

    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'ecommerce'
          AND table_name = 'app_user'
          AND column_name = 'account_non_locked'
    ) THEN
        ALTER TABLE ecommerce.app_user ADD COLUMN account_non_locked BOOLEAN DEFAULT TRUE NOT NULL;
    END IF;

    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'ecommerce'
          AND table_name = 'app_user'
          AND column_name = 'account_non_expired'
    ) THEN
        ALTER TABLE ecommerce.app_user ADD COLUMN account_non_expired BOOLEAN DEFAULT TRUE NOT NULL;
    END IF;

    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'ecommerce'
          AND table_name = 'app_user'
          AND column_name = 'credentials_non_expired'
    ) THEN
        ALTER TABLE ecommerce.app_user ADD COLUMN credentials_non_expired BOOLEAN DEFAULT TRUE NOT NULL;
    END IF;
END $$;
