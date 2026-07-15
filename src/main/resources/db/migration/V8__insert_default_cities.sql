-- =====================================================
-- Migration : V8__insert_default_cities.sql
-- =====================================================

INSERT INTO city
(name, state, country, created_at, created_by)
VALUES

('Chennai','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM'),
('Coimbatore','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM'),
('Madurai','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM'),
('Salem','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM'),
('Trichy','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM'),
('Erode','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM'),
('Tirunelveli','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM'),
('Vellore','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM'),
('Namakkal','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM'),
('Hosur','Tamil Nadu','India',CURRENT_TIMESTAMP,'SYSTEM');