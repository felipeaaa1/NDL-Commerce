/*
 * Fix user login column
 * @author Felipe Alves
 * @date 27/10/2025
 */


DO $$
BEGIN
	IF EXISTS (SELECT 1 FROM information_schema.columns
	WHERE table_schema = 'ecommerce'
	AND table_name = 'app_user'
	AND column_name = 'login') then
		RAISE NOTICE 'Coluna login ja existe';
	elseif EXISTS (SELECT 1 FROM information_schema.columns
	WHERE table_schema = 'ecommerce'
	AND table_name = 'app_user'
	AND column_name = 'name') then
	 	RAISE NOTICE 'Coluna nomeada "name" ajustando para "login"';
		alter table ecommerce.app_user
		rename column name
		to login;
	else
		RAISE NOTICE 'coluna não existe';
	END IF;
	RAISE NOTICE 'Fim da migração';
END $$;