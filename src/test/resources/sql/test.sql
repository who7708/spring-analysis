# 字段：a、b、c、d
# 索引: a、b、c、
# Using index， 使用到了索引覆盖，查询列中只有a，不需要回表
# explain select a from test_1 where b=1 and a= 2 and c= 3;

# Using index condition; Using where，a 匹配了最左前缀，b使用到了索引，d未创建索引，因此需要将ab条件查询出来的数据再进行筛选。然后再进行回表操作，把剩下的列数据查询出来
# explain select * from test_2 where b=1 and a like '小%' and d= 3;

# Using where; Using index，
# explain select * from test_3 where b=1 and a like '小%';

# Using where，条件a 匹配联合索引最左前缀，但是使用了 or ，导致索引失效，因此进行了全表扫描
# explain select * from test_1 where a=2 and b<1 or c>3;

# Using index condition，条件a 匹配联合索引最左前缀，bc使用<>则进行范围搜索，搜索之后进行回表操作
# explain select * from test_1 where a=2 and b<1 and c>3;

# Using where; Using index，条件 a 匹配最左前缀，bc使用<>进行范围搜索，但是查询列abc全在索引列中，则不需要回表
# explain select a,b,c from test_1 where a=2 and b<1 and c>3;

DROP TABLE IF EXISTS test_1;
CREATE TABLE IF NOT EXISTS test_1
(
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    a  INT NOT NULL,
    b  INT NOT NULL,
    c  INT NOT NULL,
    d  INT NOT NULL,
    KEY `idx_a_b_c` (`a`, b, c)
) CHARSET = utf8;

DROP TABLE IF EXISTS test_2;
CREATE TABLE IF NOT EXISTS test_2
(
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    a  VARCHAR(10) NOT NULL,
    b  INT         NOT NULL,
    c  INT         NOT NULL,
    d  INT         NOT NULL,
    KEY `idx_a_b_c` (`a`, b, c)
) CHARSET = utf8;
ALTER TABLE test_1
    ADD INDEX idx_c (c);

CREATE TABLE IF NOT EXISTS test_3
(
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    a  INT NOT NULL,
    b  INT NOT NULL,
    c  INT NOT NULL,
    KEY `idx_a_b_c` (`a`, b, c)
) CHARSET = utf8;


DROP DATABASE IF EXISTS test;
CREATE DATABASE test DEFAULT CHARACTER SET utf8;
USE test;
DROP TABLE IF EXISTS t_user;
CREATE TABLE `t_user`
(
    `id`        BIGINT(0) AUTO_INCREMENT,
    `user_name` VARCHAR(64),
    `code`      VARCHAR(20),
    `age`       INT,
    PRIMARY KEY (`id`),
    KEY index_user_name (`user_name`),
    KEY index_code (`code`)
) ENGINE = InnoDB;
INSERT INTO `t_user`
VALUES (1, 'LiLei', 'aa', 21);
INSERT INTO `t_user`
VALUES (2, 'HanMeimei', 'bb', 23);
INSERT INTO `t_user`
VALUES (3, 'Lucy', 'cc', 25);
INSERT INTO `t_user`
VALUES (4, 'Lili', 'dd', 28);
INSERT INTO `t_user`
VALUES (5, 'WeiHua', 'ee', 24);
INSERT INTO `t_user`
VALUES (6, 'ZhangWei', 'ff', 30);
INSERT INTO `t_user`
VALUES (7, 'Anna', 'gg', 26);
INSERT INTO `t_user`
VALUES (8, 'Lisa', 'hh', 21);
INSERT INTO `t_user`
VALUES (9, 'ZhangWei', 'ii', 24);
INSERT INTO `t_user`
VALUES (10, 'Kate', 'jj', 29);

DROP TABLE IF EXISTS t_user_1;
CREATE TABLE t_user_1
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    sex  VARCHAR(5),
    flag CHAR(1)
) ENGINE = innodb;
#  1, shenjian, m, A
# 3, zhangsan, m, A
# 5, lisi, m, A
# 9, wangwu, f, B
INSERT INTO t_user_1(name, sex, flag)
VALUES ('shenjian', 'm', 'A');
INSERT INTO t_user_1(name, sex, flag)
VALUES ('zhangsan', 'm', 'A');
INSERT INTO t_user_1(name, sex, flag)
VALUES ('lisi', 'm', 'A');
INSERT INTO t_user_1(name, sex, flag)
VALUES ('wangwu', 'f', 'B');

EXPLAIN
SELECT id, name
FROM t_user_1
WHERE name = 'shenjian';
# drop index idx_name on t_user_1;
# ADD KEY是为了向表中添加索引而设计的，而ADD INDEX则是为了向表中添加任意类型的索引而设计的
# ADD INDEX比ADD KEY更通用，因为它可以用于创建FULLTEXT、SPATIAL和其他类型的索引。
ALTER TABLE t_user_1
    ADD INDEX idx_name (name);
ALTER TABLE t_user_1
    ADD KEY idx_name (name);

EXPLAIN
SELECT id, name
FROM t_user_1
WHERE name = 'shenjian';

# 测试索引
# id，name，age，gender，address
DROP TABLE IF EXISTS test_index;
CREATE TABLE IF NOT EXISTS test_index
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(16),
    age     INT,
    gender  CHAR(1),
    address VARCHAR(128),
    INDEX idx_name (name)
);
INSERT INTO test_index(name, age, gender, address) VALUES ('zhangsan', 11, '男', '上海市浦东新区');
INSERT INTO test_index(name, age, gender, address) VALUES ('lisi', 33, '男', '北京朝阳区');
INSERT INTO test_index(name, age, gender, address) VALUES ('wangwu', 23, '女', '北京海淀区');
EXPLAIN select * from test_index where name = 'zhangsan';
EXPLAIN select * from test_index where id = 2;
EXPLAIN select id,name from test_index where name = 'zhangsan';
# 需要回表，gender不在索引列中，需要通过id再查询一次索引获取到数据
EXPLAIN select id,name,gender from test_index where name = 'zhangsan';