DROP TABLE IF EXISTS tbl_comment;

CREATE TABLE tbl_comment (
                             id mediumint(8) unsigned NOT NULL auto_increment,
                             comment_id mediumint,
                             created_by mediumint default NULL,
                             created_date varchar(255),
                             updated_by mediumint default NULL,
                             updated_date varchar(255),
                             user_id mediumint default NULL,
                             article_id mediumint default NULL,
                             comment_code mediumint default NULL,
                             comment_detail TEXT default NULL,
                             product_id mediumint default NULL,
                             PRIMARY KEY (id)
) AUTO_INCREMENT=1;

INSERT INTO tbl_comment (comment_id,created_by,created_date,updated_by,updated_date,user_id,article_id,comment_code,comment_detail,product_id)
VALUES
(1,8,'2023-03-01 19:30:22',9,'2022-07-21 01:43:47',19,25,2,'vestibulum lorem, sit amet ultricies sem magna nec quam. Curabitur vel lectus. Cum sociis',27),
(2,4,'2023-01-08 23:02:44',2,'2023-02-28 18:49:07',17,8,2,'primis in faucibus orci luctus et ultrices posuere cubilia Curae',28),
(3,4,'2023-04-09 20:42:39',9,'2022-06-04 21:38:44',20,8,1,'ac arcu. Nunc mauris. Morbi non sapien molestie orci tincidunt adipiscing. Mauris molestie',29),
(4,10,'2022-08-19 07:08:24',2,'2024-03-16 13:30:19',9,29,2,'luctus. Curabitur egestas nunc sed libero. Proin sed turpis nec mauris blandit mattis. Cras',5),
(5,7,'2023-09-02 00:28:08',7,'2022-05-28 06:55:12',23,19,2,'Maecenas libero est, congue a, aliquet vel, vulputate eu, odio. Phasellus at augue id',14),
(6,2,'2023-01-19 23:06:21',7,'2022-09-11 18:14:30',28,26,2,'neque. Sed eget lacus. Mauris non dui nec urna suscipit nonummy. Fusce fermentum fermentum arcu.',19),
(7,3,'2023-01-31 19:56:08',8,'2023-09-07 11:24:29',26,20,2,'Morbi accumsan laoreet ipsum. Curabitur consequat, lectus sit amet luctus vulputate,',16),
(8,6,'2023-06-25 14:21:15',4,'2022-08-06 05:19:14',24,11,1,'enim, gravida sit amet, dapibus id, blandit at, nisi. Cum sociis natoque penatibus et',4),
(9,6,'2022-05-30 10:20:34',5,'2024-03-06 08:35:43',15,20,2,'facilisis non, bibendum sed, est. Nunc laoreet lectus quis massa.',28),
(10,7,'2022-04-18 04:47:35',7,'2023-03-17 12:10:46',12,10,2,'vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque',5),
(11,4,'2022-05-09 21:57:14',10,'2024-01-02 16:32:43',20,10,2,'ut aliquam iaculis, lacus pede sagittis augue, eu tempor erat neque',21),
(12,2,'2022-03-27 14:24:22',6,'2022-09-02 15:54:15',26,14,1,'lacinia vitae, sodales at, velit. Pellentesque ultricies dignissim lacus. Aliquam rutrum lorem ac risus. Morbi',21),
(13,9,'2023-09-03 05:57:21',2,'2023-09-25 01:35:19',8,10,2,'vulputate ullamcorper magna. Sed eu eros. Nam consequat dolor vitae dolor. Donec fringilla. Donec',15),
(14,3,'2022-09-20 17:56:01',4,'2022-12-08 22:59:46',25,21,1,'lobortis quis, pede. Suspendisse dui. Fusce diam nunc, ullamcorper eu, euismod ac, fermentum vel,',15),
(15,8,'2023-04-15 19:45:28',6,'2023-02-06 05:23:45',15,27,1,'nisi dictum augue malesuada malesuada. Integer id magna et ipsum',4),
(16,4,'2023-06-10 12:38:42',8,'2022-06-02 19:18:27',1,13,2,'lacinia at, iaculis quis, pede. Praesent eu dui. Cum sociis natoque penatibus et magnis dis',3),
(17,1,'2022-07-10 11:06:19',8,'2022-12-22 01:38:22',13,19,1,'leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit,',12),
(18,3,'2023-08-29 23:58:02',9,'2022-08-22 20:05:42',22,7,2,'in faucibus orci luctus et ultrices posuere cubilia Curae Donec tincidunt. Donec vitae',1),
(19,2,'2023-03-12 18:37:56',7,'2023-10-06 15:24:58',4,17,2,'sed libero. Proin sed turpis nec mauris blandit mattis. Cras eget',21),
(20,6,'2023-01-06 06:04:46',7,'2024-03-02 16:45:26',27,21,1,'arcu iaculis enim, sit amet ornare lectus justo eu arcu.',19),
(21,8,'2024-01-27 01:20:28',7,'2023-08-26 10:18:21',19,2,2,'diam eu dolor egestas rhoncus. Proin nisl sem, consequat nec, mollis vitae, posuere at,',14),
(22,7,'2023-11-03 00:42:39',7,'2022-10-19 06:04:26',6,3,2,'mattis semper, dui lectus rutrum urna, nec luctus felis purus ac',26),
(23,6,'2022-11-16 19:08:53',7,'2022-05-11 19:16:16',29,23,2,'quis turpis vitae purus gravida sagittis. Duis gravida. Praesent eu nulla',24),
(24,4,'2022-07-01 23:39:09',5,'2024-01-09 01:10:36',5,26,1,'nec mauris blandit mattis. Cras eget nisi dictum augue malesuada malesuada. Integer id magna et',14),
(25,7,'2024-01-04 10:32:26',6,'2022-04-11 05:04:27',14,13,2,'Maecenas malesuada fringilla est. Mauris eu turpis. Nulla aliquet. Proin velit. Sed malesuada augue',30),
(26,2,'2022-03-23 16:27:59',3,'2023-03-02 11:09:15',5,15,2,'Integer tincidunt aliquam arcu. Aliquam ultrices iaculis odio. Nam interdum enim non',4),
(27,5,'2023-12-14 08:47:28',8,'2023-02-06 21:44:11',19,19,2,'Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque. Nullam nisl.',16),
(28,6,'2023-10-18 15:23:27',4,'2023-05-17 06:46:05',10,23,2,'enim. Etiam gravida molestie arcu. Sed eu nibh vulputate mauris',1),
(29,2,'2023-11-18 10:05:43',9,'2023-07-14 08:44:35',6,4,2,'dui quis accumsan convallis, ante lectus convallis est, vitae sodales nisi magna',10),
(30,10,'2024-02-01 17:14:35',5,'2022-08-10 08:32:15',4,24,2,'cursus, diam at pretium aliquet, metus urna convallis erat, eget tincidunt',14);
