-- =====================================================
-- Migration : V1__create_role_table.sql
-- Description : Creates Role Master Table
-- =====================================================

CREATE TABLE role
(
    id              BIGSERIAL PRIMARY KEY,

    uuid            UUID NOT NULL DEFAULT gen_random_uuid() UNIQUE,

    name            VARCHAR(50) NOT NULL UNIQUE,

    description     VARCHAR(255),

    is_active       BOOLEAN NOT NULL DEFAULT TRUE,

    is_deleted      BOOLEAN NOT NULL DEFAULT FALSE,

    created_at      TIMESTAMP NOT NULL,

    updated_at      TIMESTAMP,

    created_by      VARCHAR(100),

    updated_by      VARCHAR(100)
);