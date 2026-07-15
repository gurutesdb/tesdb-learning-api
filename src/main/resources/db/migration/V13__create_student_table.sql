-- =====================================================
-- Migration : V13__create_student_table.sql
-- =====================================================

CREATE TABLE student
(
    id                      BIGSERIAL PRIMARY KEY,

    uuid                    UUID NOT NULL DEFAULT gen_random_uuid() UNIQUE,

    first_name              VARCHAR(100) NOT NULL,

    last_name               VARCHAR(100),

    email                   VARCHAR(150) NOT NULL UNIQUE,

    password                VARCHAR(255) NOT NULL,

    mobile                  VARCHAR(15) NOT NULL,

    gender                  VARCHAR(20) NOT NULL,

    date_of_birth           DATE NOT NULL,

    register_number         VARCHAR(50) NOT NULL UNIQUE,

    semester                SMALLINT NOT NULL,

    passing_out_year        INTEGER NOT NULL,

    percentage              NUMERIC(5,2) NOT NULL,

    course_program_id       BIGINT NOT NULL,

    department_id           BIGINT NOT NULL,

    college_id              BIGINT NOT NULL,

    city_id                 BIGINT NOT NULL,

    profile_image           VARCHAR(255),

    resume_file             VARCHAR(255),

    email_verified          BOOLEAN NOT NULL DEFAULT FALSE,

    auth_provider           VARCHAR(20) NOT NULL DEFAULT 'LOCAL',

    provider_id             VARCHAR(255),

    is_active               BOOLEAN NOT NULL DEFAULT TRUE,

    is_deleted              BOOLEAN NOT NULL DEFAULT FALSE,

    created_at              TIMESTAMP NOT NULL,

    updated_at              TIMESTAMP,

    created_by              VARCHAR(100),

    updated_by              VARCHAR(100),

    CONSTRAINT fk_student_course_program
        FOREIGN KEY (course_program_id)
        REFERENCES course_program(id),

    CONSTRAINT fk_student_department
        FOREIGN KEY (department_id)
        REFERENCES department(id),

    CONSTRAINT fk_student_college
        FOREIGN KEY (college_id)
        REFERENCES college(id),

    CONSTRAINT fk_student_city
        FOREIGN KEY (city_id)
        REFERENCES city(id)
);