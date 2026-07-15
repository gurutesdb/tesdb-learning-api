-- =====================================================
-- Migration : V5__create_department_table.sql
-- =====================================================

CREATE TABLE department
(
    id                  BIGSERIAL PRIMARY KEY,

    uuid                UUID NOT NULL DEFAULT gen_random_uuid() UNIQUE,

    course_program_id   BIGINT NOT NULL,

    name                VARCHAR(100) NOT NULL,

    description         VARCHAR(255),

    is_active           BOOLEAN NOT NULL DEFAULT TRUE,

    is_deleted          BOOLEAN NOT NULL DEFAULT FALSE,

    created_at          TIMESTAMP NOT NULL,

    updated_at          TIMESTAMP,

    created_by          VARCHAR(100),

    updated_by          VARCHAR(100),

    CONSTRAINT fk_department_course_program
        FOREIGN KEY (course_program_id)
        REFERENCES course_program(id),

    CONSTRAINT uk_department_program_name
        UNIQUE (course_program_id, name)
);