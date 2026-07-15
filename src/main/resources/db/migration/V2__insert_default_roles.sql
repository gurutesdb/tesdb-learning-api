-- =====================================================
-- Migration : V2__insert_default_roles.sql
-- =====================================================

INSERT INTO role
(
    name,
    description,
    created_at,
    created_by
)
VALUES
('SUPER_ADMIN', 'Super Administrator', CURRENT_TIMESTAMP, 'SYSTEM'),
('ADMIN', 'Administrator', CURRENT_TIMESTAMP, 'SYSTEM');