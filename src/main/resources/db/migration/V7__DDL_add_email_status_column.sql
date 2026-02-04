/*
 * Alter table to add is_email_valid column
 * @author Felipe Alves
 * @date 15/12/2025
 */

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'ecommerce'
          AND table_name   = 'app_user'
          AND column_name  = 'is_email_valid'
    ) THEN
        RAISE NOTICE 'Coluna is_email_valid já existe';
    ELSE
        RAISE NOTICE 'Criando coluna is_email_valid';
        ALTER TABLE ecommerce.app_user
            ADD COLUMN is_email_valid BOOLEAN DEFAULT false NOT NULL;
    END IF;
END $$;
