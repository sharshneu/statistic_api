DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'statistic_api_user') THEN
        CREATE USER statistic_api_user WITH PASSWORD '123';
        RAISE NOTICE 'User statistic_api_user created';
ELSE
        RAISE NOTICE 'User statistic_api_user already exists';
END IF;
    GRANT ALL PRIVILEGES ON DATABASE statistic_api TO statistic_api_user;
END
$$;
