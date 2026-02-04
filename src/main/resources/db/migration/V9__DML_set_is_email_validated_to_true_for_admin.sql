/*
 * Update user admin to have a valid email
 * @author Felipe Alves
 * @date 03/02/2026
 */

update ecommerce.app_user
set is_email_valid = true
where login = 'admin'