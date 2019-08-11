
-- mysql  set date value

alter table COMMENT_INFO modify COMMENT_DATE datetime COMMENT '评论日期';
alter table ARTICLE_INFO modify PUB_DATE datetime COMMENT '发布日期';
alter table INTER_MSG_SENDER modify SEND_DATE datetime COMMENT '发送时间';


