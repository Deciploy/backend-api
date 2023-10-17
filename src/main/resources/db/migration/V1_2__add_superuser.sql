CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
INSERT INTO "user_account" (id,email, full_name, password)
VALUES (uuid_generate_v4(),'imasha@superuser.com', 'Imasha Weerakoon', '$2a$10$S7gPFN99aFVg2Z5CCNuR0uqi5hu/VLLucALJflO6e2KWl/wQm2IHi');