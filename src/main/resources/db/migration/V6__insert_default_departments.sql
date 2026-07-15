-- =====================================================
-- Migration : V6__insert_default_departments.sql
-- =====================================================

INSERT INTO department
(course_program_id, name, description, created_at, created_by)
VALUES

((SELECT id FROM course_program WHERE name='BE'),
'Computer Science and Engineering',
'Computer Science and Engineering',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='BE'),
'Information Technology',
'Information Technology',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='BE'),
'Electronics and Communication Engineering',
'Electronics and Communication Engineering',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='BE'),
'Electrical and Electronics Engineering',
'Electrical and Electronics Engineering',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='BE'),
'Mechanical Engineering',
'Mechanical Engineering',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='BE'),
'Civil Engineering',
'Civil Engineering',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='BCA'),
'Computer Applications',
'Computer Applications',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='B.Sc'),
'Computer Science',
'Computer Science',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='B.Com'),
'Commerce',
'Commerce',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='BBA'),
'Business Administration',
'Business Administration',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='MCA'),
'Computer Applications',
'Computer Applications',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='MBA'),
'Finance',
'Finance',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='MBA'),
'Marketing',
'Marketing',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='MBA'),
'Human Resources',
'Human Resources',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='Diploma'),
'Mechanical Engineering',
'Mechanical Engineering',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='Diploma'),
'Civil Engineering',
'Civil Engineering',
CURRENT_TIMESTAMP,
'SYSTEM'),

((SELECT id FROM course_program WHERE name='Diploma'),
'Electrical and Electronics Engineering',
'Electrical and Electronics Engineering',
CURRENT_TIMESTAMP,
'SYSTEM');