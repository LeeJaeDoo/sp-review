-- auto-generated definition
DROP TABLE IF EXISTS `mart_store`;
CREATE TABLE mart_store
(
    `store_no`                      int(11)         NOT NULL AUTO_INCREMENT COMMENT '상점 고유번호',
    `store_name`                    varchar(190)    CHARACTER SET utf8mb4 NOT NULL COMMENT '상점명',
    `map_url`                       text            CHARACTER SET utf8mb4 NOT NULL COMMENT '지도 api 연결 경로',
    `register_ymdt`                datetime         NOT NULL COMMENT '등록 일시',
    `update_ymdt`                  datetime         NULL COMMENT '수정 일시',
    PRIMARY KEY (`store_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT '상점 정보';

DROP TABLE IF EXISTS `mart_review`;
CREATE TABLE mart_review
(
    `review_no`                    int(11)        NOT NULL AUTO_INCREMENT COMMENT '리뷰 고유번호',
    `title`                        varchar(190)   CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '리뷰 제목',
    `content`                      mediumtext     CHARACTER SET utf8mb4 COMMENT '리뷰 내용',
    `image_url`                    varchar(190)   NULL COMMENT '이미지 경로',
    `member_no`                    int(11)        NOT NULL COMMENT '회원 고유번호',
    `store_no`                     int(11)        NOT NULL COMMENT '상점 고유번호',
    `store_accessible_yn`          char(1)        NULL COMMENT '',
    `product_reliable_yn`          char(1)        NULL COMMENT '',
    `register_ymdt`                datetime       NOT NULL COMMENT '등록 일시',
    `update_ymdt`                  datetime       NULL COMMENT '수정 일시',
    `review_delete_yn`             char(1)        DEFAULT 'N' COMMENT '삭제 표시 여부',
    PRIMARY KEY (`review_no`),
    KEY `ink1_mart_review_store` (`store_no`),
    KEY `ink2_mart_review_member` (`member_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT '상점 리뷰';
