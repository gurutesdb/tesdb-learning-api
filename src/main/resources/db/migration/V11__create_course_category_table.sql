-- =====================================================
-- Migration : V11__create_course_category_table.sql
-- =====================================================

CREATE TABLE course_category
(
    id              BIGSERIAL PRIMARY KEY,

    uuid            UUID NOT NULL DEFAULT gen_random_uuid() UNIQUE,

    name            VARCHAR(100) NOT NULL UNIQUE,

    description     VARCHAR(255),

    is_active       BOOLEAN NOT NULL DEFAULT TRUE,

    is_deleted      BOOLEAN NOT NULL DEFAULT FALSE,

    created_at      TIMESTAMP NOT NULL,

    updated_at      TIMESTAMP,

    created_by      VARCHAR(100),

    updated_by      VARCHAR(100)
);