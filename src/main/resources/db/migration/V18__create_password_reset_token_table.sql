CREATE TABLE password_reset_token
(
    id BIGSERIAL PRIMARY KEY,

    uuid UUID NOT NULL UNIQUE,

    student_id BIGINT NOT NULL REFERENCES student(id),

    token TEXT NOT NULL UNIQUE,

    expires_at TIMESTAMP NOT NULL,

    used BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL
);