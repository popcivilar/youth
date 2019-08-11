
-- mysql  set default value

alter table COMMENT_INFO modify ARTICLE_ID int(10) DEFAULT -1 COMMENT '评论文章ID';
alter table COMMENT_INFO modify USER_ID int(10) DEFAULT -1 COMMENT '发表用户';
alter table COMMENT_INFO modify THUNB_NUM int(4) DEFAULT 0 COMMENT '点赞数';
alter table COMMENT_INFO modify COMMENT_NUM int(4) DEFAULT 0 COMMENT '评论数';
alter table COMMENT_INFO modify PARTENT_ID int(10) DEFAULT -1 COMMENT '父评论';


