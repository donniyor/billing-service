INSERT INTO users (email, full_name, password_hash, role, enabled, email_verified)
VALUES (
    'admin@example.uz',
    'Admin',
    'AdminPassword',
    'ADMIN',
    TRUE,
    TRUE
)
ON CONFLICT (email) DO NOTHING;
