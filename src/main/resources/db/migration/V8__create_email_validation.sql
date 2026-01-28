/*
 * Alter table to add is_email_valid column
 * @author Felipe Alves
 * @date 25/01/2026
 */

CREATE TABLE IF NOT EXISTS ecommerce.tb_email_validate (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    used_at TIMESTAMP NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_tb_email_validate_user
        FOREIGN KEY (user_id)
        REFERENCES ecommerce.app_user(id)
        ON DELETE CASCADE
);
