-- =====================================================
-- Migration : V7__create_city_table.sql
-- =====================================================

CREATE TABLE city
(
    id              BIGSERIAL PRIMARY KEY,

    uuid            UUID NOT NULL DEFAULT gen_random_uuid() UNIQUE,

    name            VARCHAR(100) NOT NULL,

    state           VARCHAR(100) NOT NULL,

    country         VARCHAR(100) NOT NULL DEFAULT 'India',

    is_active       BOOLEAN NOT NULL DEFAULT TRUE,

    is_deleted      BOOLEAN NOT NULL DEFAULT FALSE,

    created_at      TIMESTAMP NOT NULL,

    updated_at      TIMESTAMP,

    created_by      VARCHAR(100),

    updated_by      VARCHAR(100),

    CONSTRAINT uk_city UNIQUE(name, state)
);