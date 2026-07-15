-- =====================================================
-- Migration : V9__create_college_table.sql
-- =====================================================

CREATE TABLE college
(
    id                  BIGSERIAL PRIMARY KEY,

    uuid                UUID NOT NULL DEFAULT gen_random_uuid() UNIQUE,

    name                VARCHAR(200) NOT NULL,

    code                VARCHAR(50),

    university_name     VARCHAR(200),

    city_id             BIGINT NOT NULL,

    is_active           BOOLEAN NOT NULL DEFAULT TRUE,

    is_deleted          BOOLEAN NOT NULL DEFAULT FALSE,

    created_at          TIMESTAMP NOT NULL,

    updated_at          TIMESTAMP,

    created_by          VARCHAR(100),

    updated_by          VARCHAR(100),

    CONSTRAINT fk_college_city
        FOREIGN KEY (city_id)
        REFERENCES city(id),

    CONSTRAINT uk_college_name_city
        UNIQUE(name, city_id)
);