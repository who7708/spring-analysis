CREATE DATABASE  IF NOT EXISTS mydb;

USE `mydb`;

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course`
(
    `id`   INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8;

/*Data for the table `course` */

INSERT INTO `course`(`id`, `name`)
VALUES (1, '语文'),
       (2, '高等数学'),
       (3, '视听说'),
       (4, '体育'),
       (5, '马克思概况'),
       (6, '民族理论'),
       (7, '毛中特'),
       (8, '计算机基础'),
       (9, '深度学习'),
       (10, 'Java程序设计'),
       (11, 'c语言程序设计'),
       (12, '操作系统'),
       (13, '计算机网络'),
       (14, '计算机组成原理'),
       (15, '数据结构'),
       (16, '数据分析'),
       (17, '大学物理'),
       (18, '数字逻辑'),
       (19, '嵌入式开发'),
       (20, '需求工程');

/*Table structure for table `stu_course` */

DROP TABLE IF EXISTS `stu_course`;

CREATE TABLE `stu_course`
(
    `sid` INT(10) NOT NULL,
    `cid` INT(10) NOT NULL,
    PRIMARY KEY (`sid`, `cid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `stu_course` */

INSERT INTO `stu_course`(`sid`, `cid`)
VALUES (1, 2),
       (1, 4),
       (1, 14),
       (1, 16),
       (1, 19),
       (2, 4),
       (2, 8),
       (2, 9),
       (2, 14),
       (3, 13),
       (3, 14),
       (3, 20),
       (4, 5),
       (4, 8),
       (4, 9),
       (4, 11),
       (4, 16),
       (5, 4),
       (5, 8),
       (5, 9),
       (5, 11),
       (5, 12),
       (5, 16),
       (6, 2),
       (6, 14),
       (6, 17),
       (7, 1),
       (7, 8),
       (7, 15),
       (8, 2),
       (8, 3),
       (8, 7),
       (8, 17),
       (9, 1),
       (9, 7),
       (9, 16),
       (9, 20),
       (10, 4),
       (10, 12),
       (10, 14),
       (10, 20),
       (11, 3),
       (11, 9),
       (11, 16),
       (12, 3),
       (12, 7),
       (12, 9),
       (12, 12),
       (13, 1),
       (13, 5),
       (13, 13),
       (14, 1),
       (14, 3),
       (14, 18),
       (15, 1),
       (15, 9),
       (15, 15),
       (16, 2),
       (16, 7);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student`
(
    `id`   INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) DEFAULT NULL,
    `age`  INT(2)      DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`),
    KEY `idx_name_age` (`name`, `age`),
    KEY `idx_id_name_age` (`id`, `name`, `age`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 31
  DEFAULT CHARSET = utf8;

/*Data for the table `student` */

INSERT INTO `student`(`id`, `name`, `age`)
VALUES (25, '乾隆', 17),
       (14, '关羽', 43),
       (13, '刘备', 12),
       (28, '刘永', 12),
       (21, '后裔', 12),
       (30, '吕子乔', 28),
       (18, '嬴政', 76),
       (22, '孙悟空', 21),
       (4, '安其拉', 24),
       (6, '宋江', 22),
       (26, '康熙', 51),
       (29, '张伟', 26),
       (20, '张郃', 12),
       (12, '张飞', 32),
       (27, '朱元璋', 19),
       (11, '李世民', 54),
       (9, '李逵', 12),
       (8, '林冲', 43),
       (5, '橘右京', 43),
       (24, '沙和尚', 25),
       (23, '猪八戒', 22),
       (15, '王与', 21),
       (19, '王建', 23),
       (10, '王莽', 43),
       (16, '秦叔宝', 43),
       (17, '程咬金', 65),
       (3, '荆轲', 21),
       (2, '诸葛亮', 71),
       (7, '钟馗', 23),
       (1, '鲁班', 21);
# drop index name_age on student;
# create index idx_name_age
#     on student (name, age);


EXPLAIN SELECT * FROM student;
EXPLAIN
SELECT
    S.id,S.name,S.age,C.id,C.name
FROM course C JOIN stu_course SC ON C.id = SC.cid
              JOIN student S ON S.id = SC.sid

EXPLAIN
SELECT *
FROM course AS C
WHERE C.`id` = (
    SELECT SC.`cid`
    FROM stu_course AS SC
    WHERE SC.`sid` =
          (
              SELECT
                  S.`id`
              FROM student AS S
              WHERE  S.`name` = '安其拉'
          ) ORDER BY SC.`cid` LIMIT 1
);

EXPLAIN
SELECT *
FROM course AS C
WHERE C.`id` IN (
    SELECT SC.`cid`
    FROM stu_course AS SC
    WHERE SC.`sid` =
          (
              SELECT
                  S.`id`
              FROM student AS S
              WHERE  S.`name` = '安其拉'
          )
);

EXPLAIN
SELECT * FROM student JOIN stu_course ON student.`id` = sid;

EXPLAIN
SELECT SC.`cid`
FROM stu_course AS SC
WHERE SC.`sid` =
      (
          SELECT
              S.`id`
          FROM student AS S
          WHERE  S.`name` = '安其拉'
      );