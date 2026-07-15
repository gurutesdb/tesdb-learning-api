-- =====================================================
-- Migration : V12__insert_default_course_categories.sql
-- =====================================================

INSERT INTO course_category
(name, description, created_at, created_by)
VALUES

('Java','Core Java Programming',CURRENT_TIMESTAMP,'SYSTEM'),

('Spring Boot','Spring Boot Framework',CURRENT_TIMESTAMP,'SYSTEM'),

('Spring Security','Authentication & Authorization',CURRENT_TIMESTAMP,'SYSTEM'),

('Hibernate','Hibernate ORM Framework',CURRENT_TIMESTAMP,'SYSTEM'),

('Microservices','Microservices Architecture',CURRENT_TIMESTAMP,'SYSTEM'),

('SQL','Structured Query Language',CURRENT_TIMESTAMP,'SYSTEM'),

('PostgreSQL','PostgreSQL Database',CURRENT_TIMESTAMP,'SYSTEM'),

('Problem Solving','Programming & Logical Thinking',CURRENT_TIMESTAMP,'SYSTEM'),

('Data Structures','Data Structures using Java',CURRENT_TIMESTAMP,'SYSTEM'),

('Aptitude','Placement Aptitude',CURRENT_TIMESTAMP,'SYSTEM');