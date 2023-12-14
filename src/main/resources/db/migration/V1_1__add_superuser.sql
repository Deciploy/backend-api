CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

with new_user as (
    INSERT INTO "user_account" (id,email, full_name, password)
    VALUES (uuid_generate_v4(),'imasha@superuser.com', 'Imasha Weerakoon', '$2a$10$S7gPFN99aFVg2Z5CCNuR0uqi5hu/VLLucALJflO6e2KWl/wQm2IHi')
    RETURNING id
)

INSERT INTO "user_account_roles" (user_account_id, roles)
VALUES ((SELECT id FROM new_user), 'ADMIN');