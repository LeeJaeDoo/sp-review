-- auto-generated definition
DROP TABLE IF EXISTS `mart_review`;
CREATE TABLE mart_review
(
    `review_no`                    int(11)        NOT NULL AUTO_INCREMENT,
    `title`                        varchar(400)   CHARACTER SET utf8mb4 DEFAULT NULL,
    `content`                      mediumtext     CHARACTER SET utf8mb4,
    `image_url`                    varchar(450)   NULL,
    `member_no`                    int(11)        NOT NULL,
    `store_no`                     int(11)        NOT NULL,
    `store_accessible_yn`          char(1)        NULL,
    `product_reliable_yn`          char(1)        NULL,
    `register_ymdt`                datetime       NOT NULL,
    `update_ymdt`                  datetime       NULL,
    `review_delete_yn`             char(1)        DEFAULT 'N',
    PRIMARY KEY (`review_no`),
    KEY `ink1_mt_review` (`store_no`),
    KEY `ink2_mt_review` (`member_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT '리뷰';
