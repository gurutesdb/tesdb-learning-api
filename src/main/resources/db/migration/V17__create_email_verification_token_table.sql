CREATE TABLE email_verification_token
(
    id BIGSERIAL PRIMARY KEY,

    uuid UUID NOT NULL UNIQUE,

    student_id BIGINT NOT NULL REFERENCES student(id),

    token TEXT NOT NULL,

    expires_at TIMESTAMP NOT NULL,

    verified BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL
);