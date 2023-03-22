-- INSERT INTO `shoes_db`.`tbl_trademark`
-- (`trademark_id`, `seo`, `trademark_name`, `created_by`, `created_date`, `status`, `updated_by`,
--  `updated_date`)
-- VALUES (1, "Nike", "Nike", 1, SYSDATE(), 1, 1, SYSDATE()),
--        (2, "Jordan", "Jordan", 1, SYSDATE(), 1, 1, SYSDATE()),
--        (3, "Adidas", "Adidas", 1, SYSDATE(), 1, 1, SYSDATE()),
--        (4, "NB", "NB", 1, SYSDATE(), 1, 1, SYSDATE()),
--        (5, "Kakulo", "Kakulo", 1, SYSDATE(), 1, 1, SYSDATE()),
--        (6, "Yezzy", "Yezzy", 1, SYSDATE(), 1, 1, SYSDATE()),
--        (7, "Socioak", "Socioak", 1, SYSDATE(), 1, 1, SYSDATE());
truncate tbl_trademark;
create table tbl_trademark (
                               trademark_id INT,
                               trademark_name VARCHAR(50),
                               seo VARCHAR(50),
                               created_by INT,
                               created_date DATE,
                               status VARCHAR(50),
                               updated_by INT,
                               updated_date DATE
);
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (1, 'Menthol', 'AO-LNO', 92, '2023-01-22 07:00:54', false, 90, '2022-10-17 07:45:57');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (2, 'Avobenzone, Homosalate, Octisalate, Octocrylene, and Oxybenzone', 'RO-B', 58, '2022-10-19 10:42:40', false, 23, '2022-11-18 21:18:01');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (3, 'Paroxetine Hydrochloride', 'IN-HP', 42, '2022-09-20 18:20:10', false, 83, '2023-02-11 12:52:28');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (4, 'barley malt', 'US-TX', 15, '2022-10-01 16:03:07', false, 53, '2023-01-31 23:16:00');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (5, 'Warfarin Sodium', 'CY-02', 59, '2022-05-13 10:43:33', false, 80, '2022-10-26 02:47:38');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (6, 'Anacardium orientale, Antimon. crud., Arg. nit., Berber. vulg., Bryonia, Chelidonium majus, Digitalis, Graphites, Humulus, Iris Versicolor,', 'US-AR', 34, '2022-10-30 10:06:48', false, 66, '2022-04-05 03:08:39');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (7, 'Avobenzone, Homosalate, Octisalate, and Octocrylene', 'RU-BEL', 57, '2022-10-28 02:31:47', true, 10, '2022-05-23 01:51:16');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (8, 'Octinoxate', 'US-WY', 90, '2022-05-13 12:58:50', true, 25, '2022-12-05 09:09:06');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (9, 'SODIUM CHLORIDE', 'US-MD', 1, '2022-03-24 04:27:24', true, 2, '2023-03-03 12:52:58');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (10, 'Acyclovir', 'CI-08', 48, '2022-06-18 07:50:29', true, 66, '2023-01-16 04:24:47');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (11, 'BENZALKONIUM CHLORIDE and LIDOCAINE HYDROCHLORIDE', 'US-NY', 100, '2022-06-10 15:21:02', false, 58, '2022-06-13 01:21:24');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (12, 'Meloxicam', 'US-NC', 92, '2022-11-28 18:57:43', true, 39, '2022-11-22 08:07:54');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (13, 'Arnica Montana', 'US-IA', 17, '2022-04-17 18:54:54', true, 30, '2022-06-27 23:20:31');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (14, 'Naproxen', 'US-UT', 11, '2023-02-02 01:59:05', false, 6, '2022-08-26 10:59:43');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (15, 'Garlic', 'CU-99', 7, '2022-10-28 03:40:07', false, 45, '2022-08-07 23:37:39');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (16, 'BENZALKONIUM CHLORIDE', 'US-FL', 97, '2023-01-17 22:19:31', true, 43, '2022-04-14 09:14:52');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (17, 'FENTANYL CITRATE', 'AU-VIC', 75, '2022-10-07 07:17:56', false, 17, '2022-06-24 20:39:10');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (18, 'Oxybutynin Chloride', 'VE-V', 34, '2022-06-14 17:44:57', false, 69, '2023-02-17 16:58:10');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (19, 'FERROUS SULFATE', 'CA-AB', 72, '2022-09-13 12:26:35', false, 5, '2023-02-24 18:12:34');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (20, 'OXYBENZONE', 'CM-LT', 3, '2022-04-15 18:21:33', false, 10, '2022-08-21 11:29:57');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (21, 'ALCOHOL', 'MZ-L', 67, '2022-06-06 12:02:58', false, 32, '2022-03-29 16:15:00');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (22, 'capsaicin', 'PG-MPM', 34, '2022-07-28 21:23:49', true, 26, '2022-10-20 05:54:16');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (23, 'Pseudoephedrine Hydrochloride', 'IR-02', 15, '2023-03-09 12:13:15', true, 79, '2022-09-11 20:04:36');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (24, 'Oxygen', 'US-OH', 37, '2022-06-15 01:03:26', false, 75, '2022-11-07 03:42:39');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (25, 'loratadine, pseudoephedrine sulfate', 'VE-F', 65, '2022-09-25 21:49:49', true, 91, '2022-10-22 13:15:37');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (26, 'ACYCLOVIR', 'IT-34', 17, '2022-04-29 15:43:50', true, 76, '2022-08-27 16:14:26');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (27, 'Coal Tar', 'MX-CHH', 37, '2023-02-07 08:26:11', false, 30, '2022-10-21 21:43:01');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (28, 'Prochlorperazine maleate', 'CA-NU', 20, '2022-10-21 17:34:09', false, 9, '2022-07-18 22:19:33');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (29, 'fexofenadine hcl', 'US-TX', 66, '2022-07-27 20:04:36', false, 84, '2022-10-18 20:48:59');
insert into tbl_trademark (trademark_id, trademark_name, seo, created_by, created_date, status, updated_by, updated_date) values (30, 'Loratadine and Pseudoephedrine Sulfate', 'PH-SUR', 47, '2022-09-23 20:25:10', true, 31, '2022-06-16 20:36:23');