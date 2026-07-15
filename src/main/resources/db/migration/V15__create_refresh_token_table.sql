-- =====================================================
-- Migration : V15__create_refresh_token_table.sql
-- =====================================================
CREATE TABLE refresh_token
(
    id BIGSERIAL PRIMARY KEY,

    uuid UUID NOT NULL UNIQUE,

    student_id BIGINT REFERENCES student(id),

    admin_id BIGINT REFERENCES admin(id),

    token TEXT NOT NULL,

    expires_at TIMESTAMP NOT NULL,

    revoked BOOLEAN NOT NULL DEFAULT FALSE,

    expired BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL
);