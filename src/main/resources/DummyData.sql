INSERT INTO `za`.`users` (`usersid`,`creationdate`,`dateofbirth`,`email`,`firstname`,`enabled`,`lastaccessdate`,`location`,`middlename`,`password`,`phone`,`preferredlanguage`,`status`,`surname`,`title`,`updatedat`,`username`)VALUES(1,null,null,null,null,1,null,null,null,'$2a$12$g/jR.6MAnaODm.XOyV4B6ulYb2R8b7Lmsidj6Ros2Yzq2E.BR765e',null,null,null,null,null,null,'admin');
INSERT INTO `za`.`users` (`usersid`,`creationdate`,`dateofbirth`,`email`,`firstname`,`enabled`,`lastaccessdate`,`location`,`middlename`,`password`,`phone`,`preferredlanguage`,`status`,`surname`,`title`,`updatedat`,`username`)VALUES(2,null,null,null,null,1,null,null,null,'$2a$12$ERLyPs9iYR1RbG7EFU3L.Ob4HP1nuNNNIMTMZBmUCZSm9vMQl8ira',null,null,null,null,null,null,'caseworker');
INSERT INTO `za`.`users` (`usersid`,`creationdate`,`dateofbirth`,`email`,`firstname`,`enabled`,`lastaccessdate`,`location`,`middlename`,`password`,`phone`,`preferredlanguage`,`status`,`surname`,`title`,`updatedat`,`username`)VALUES(3,null,null,null,null,1,null,null,null,'$2a$12$ddJro0Z4lGp60A2dMERVnO6/1d909y2LdzloBk243g/CAJ0W5Yqfq',null,null,null,null,null,null,'supervisor');

INSERT INTO `za`.`roles`(`rolesid`,`name`,`status`)VALUES(1,'admin',null);
INSERT INTO `za`.`roles`(`rolesid`,`name`,`status`)VALUES(2,'caseworker',null);
INSERT INTO `za`.`roles`(`rolesid`,`name`,`status`)VALUES(3,'supervisor',null);

INSERT INTO `za`.`usersroles`(`usersid`,`rolesid`)VALUES(1,1);
INSERT INTO `za`.`usersroles`(`usersid`,`rolesid`)VALUES(2,2);
INSERT INTO `za`.`usersroles`(`usersid`,`rolesid`)VALUES(3,3);