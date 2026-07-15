-- =====================================================
-- Migration : V14__create_admin_table.sql
-- =====================================================
CREATE TABLE admin
(
    id BIGSERIAL PRIMARY KEY,

    uuid UUID NOT NULL UNIQUE,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(150) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL,

    role_id BIGINT NOT NULL REFERENCES role(id),

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    created_by VARCHAR(100),

    updated_by VARCHAR(100)
);