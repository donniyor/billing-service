INSERT INTO users (email, full_name, password_hash, role, enabled, email_verified)
VALUES (
    'admin@example.uz',
    'Admin',
    '$2a$10$Vo3M0V25GIug9WfswYVpnOiqTtLQB5mMh2zzSeFKHdEz2MbWFN5oq', -- todo remove
    'ADMIN',
    TRUE,
    TRUE
)
ON CONFLICT (email) DO NOTHING;
