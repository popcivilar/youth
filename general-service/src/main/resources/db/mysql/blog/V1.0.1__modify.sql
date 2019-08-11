
-- mysql 修改主键自增长
alter table TYPE_INFO change id  id int AUTO_INCREMENT;
alter table USER_INFO change id  id int AUTO_INCREMENT;
alter table ARTICLE_INFO change id  id int AUTO_INCREMENT;
alter table COMMENT_INFO change id  id int AUTO_INCREMENT;
alter table INTER_MSG_RECEIVER change id  id int AUTO_INCREMENT;
alter table INTER_MSG_SENDER change id  id int AUTO_INCREMENT;
alter table LINK_MANAGE change id  id int AUTO_INCREMENT;
alter table WEB_SITE_INFO change id  id int AUTO_INCREMENT;


alter table TYPE_INFO modify column CREATE_DATE DateTime;
alter table TYPE_INFO modify column MODIFY_DATE DateTime;
alter table USER_INFO modify column CREATE_DATE DateTime;
alter table USER_INFO modify column MODIFY_DATE DateTime;
alter table ARTICLE_INFO modify column CREATE_DATE DateTime;
alter table ARTICLE_INFO modify column MODIFY_DATE DateTime;
alter table COMMENT_INFO modify column CREATE_DATE DateTime;
alter table COMMENT_INFO modify column MODIFY_DATE DateTime;
alter table INTER_MSG_RECEIVER modify column CREATE_DATE DateTime;
alter table INTER_MSG_RECEIVER modify column MODIFY_DATE DateTime;
alter table INTER_MSG_SENDER modify column CREATE_DATE DateTime;
alter table INTER_MSG_SENDER modify column MODIFY_DATE DateTime;
alter table LINK_MANAGE modify column CREATE_DATE DateTime;
alter table LINK_MANAGE modify column MODIFY_DATE DateTime;
alter table WEB_SITE_INFO modify column CREATE_DATE DateTime;
alter table WEB_SITE_INFO modify column MODIFY_DATE DateTime;


