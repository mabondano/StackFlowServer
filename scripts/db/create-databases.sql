-- StackFlowServer v2 - databases for MariaDB 11.7 (localhost:3307), step 0.3.3.
-- Run as an administrator (e.g. root). Safe to run more than once.
--
--   mariadb -h 127.0.0.1 -P 3307 -u root -p < scripts/db/create-databases.sql
--
-- The tables are NOT created here: Flyway creates them when StackFlow starts (step C1).

CREATE DATABASE IF NOT EXISTS stackflowdb
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS stackflowdb_test
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Optional but recommended: a dedicated application user instead of root.
-- Uncomment, choose a password HERE ONLY IN YOUR LOCAL COPY (never commit it), and run again.
--
-- CREATE USER IF NOT EXISTS 'stackflow'@'localhost' IDENTIFIED BY 'choose-a-password';
-- GRANT ALL PRIVILEGES ON stackflowdb.*      TO 'stackflow'@'localhost';
-- GRANT ALL PRIVILEGES ON stackflowdb_test.* TO 'stackflow'@'localhost';
-- FLUSH PRIVILEGES;

SHOW DATABASES LIKE 'stackflowdb%';
