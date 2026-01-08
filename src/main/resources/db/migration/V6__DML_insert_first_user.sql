/*
 * Insert first user
 * @author Felipe Alves
 * @date 15/12/2025
 */

 INSERT INTO ecommerce.app_user (
     id,
     login,
     email,
     password,
     user_type,
     created_by,
     created_at,
     updated_by,
     updated_at,
     enabled,
     account_non_locked,
     account_non_expired,
     credentials_non_expired
 ) VALUES (
     gen_random_uuid(),
     'admin',
     'admin@teste.com',
     '$2a$10$2U/AXH1IUAbg9zr75j.OJOrM.t12TqSe3sJWCGMdrCHq1LfHhbNGu',
     'ADMIN',
     NULL,
     NOW(),
     NULL,
     NOW(),
     TRUE,
     TRUE,
     TRUE,
     TRUE
 );
