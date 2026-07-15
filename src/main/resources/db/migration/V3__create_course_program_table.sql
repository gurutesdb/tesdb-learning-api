-- =====================================================
-- Migration : V3__create_course_program_table.sql
-- =====================================================

CREATE TABLE course_program
(
    id                      BIGSERIAL PRIMARY KEY,

    uuid                    UUID NOT NULL DEFAULT gen_random_uuid() UNIQUE,

    name                    VARCHAR(100) NOT NULL UNIQUE,

    description             VARCHAR(255),

    duration_in_semesters   SMALLINT NOT NULL,

    is_active               BOOLEAN NOT NULL DEFAULT TRUE,

    is_deleted              BOOLEAN NOT NULL DEFAULT FALSE,

    created_at              TIMESTAMP NOT NULL,

    updated_at              TIMESTAMP,

    created_by              VARCHAR(100),

    updated_by              VARCHAR(100)
);