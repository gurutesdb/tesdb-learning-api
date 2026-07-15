-- =====================================================
-- Migration : V10__insert_default_colleges.sql
-- =====================================================

INSERT INTO college
(name, code, university_name, city_id, created_at, created_by)
VALUES

(
'Anna University',
'AU',
'Anna University',
(SELECT id FROM city WHERE name='Chennai'),
CURRENT_TIMESTAMP,
'SYSTEM'
),

(
'College of Engineering Guindy',
'CEG',
'Anna University',
(SELECT id FROM city WHERE name='Chennai'),
CURRENT_TIMESTAMP,
'SYSTEM'
),

(
'PSG College of Technology',
'PSG',
'Anna University',
(SELECT id FROM city WHERE name='Coimbatore'),
CURRENT_TIMESTAMP,
'SYSTEM'
),

(
'Coimbatore Institute of Technology',
'CIT',
'Anna University',
(SELECT id FROM city WHERE name='Coimbatore'),
CURRENT_TIMESTAMP,
'SYSTEM'
),

(
'Thiagarajar College of Engineering',
'TCE',
'Anna University',
(SELECT id FROM city WHERE name='Madurai'),
CURRENT_TIMESTAMP,
'SYSTEM'
),

(
'Government College of Engineering',
'GCE',
'Anna University',
(SELECT id FROM city WHERE name='Salem'),
CURRENT_TIMESTAMP,
'SYSTEM'
);