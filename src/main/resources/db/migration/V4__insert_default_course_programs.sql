-- =====================================================
-- Migration : V4__insert_default_course_programs.sql
-- =====================================================

INSERT INTO course_program
(
    name,
    description,
    duration_in_semesters,
    created_at,
    created_by
)
VALUES
('BE', 'Bachelor of Engineering', 8, CURRENT_TIMESTAMP, 'SYSTEM'),
('B.Tech', 'Bachelor of Technology', 8, CURRENT_TIMESTAMP, 'SYSTEM'),
('BCA', 'Bachelor of Computer Applications', 6, CURRENT_TIMESTAMP, 'SYSTEM'),
('B.Sc', 'Bachelor of Science', 6, CURRENT_TIMESTAMP, 'SYSTEM'),
('B.Com', 'Bachelor of Commerce', 6, CURRENT_TIMESTAMP, 'SYSTEM'),
('BBA', 'Bachelor of Business Administration', 6, CURRENT_TIMESTAMP, 'SYSTEM'),
('MCA', 'Master of Computer Applications', 4, CURRENT_TIMESTAMP, 'SYSTEM'),
('MBA', 'Master of Business Administration', 4, CURRENT_TIMESTAMP, 'SYSTEM'),
('Diploma', 'Diploma', 6, CURRENT_TIMESTAMP, 'SYSTEM');