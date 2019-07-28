/*==============================================================*/
/* Table: ARTICLE_INFO                                               */
/*==============================================================*/
CREATE TABLE
    ARTICLE_INFO
    (
        ID INT(10) NOT NULL,
        PUB_DATE DATE COMMENT '发布日期
',
        USER_ID INT(10) COMMENT '发表用户
',
        TITLE VARCHAR(50) COMMENT '博文标题
',
        TYPE VARCHAR(100) COMMENT '博文类别
',
        TYPE_ID INT(4) COMMENT '类别Id',
        CONTENT text COMMENT '博文内容
',
        THUMB_NUM INT(4) COMMENT '点赞数

',
        COMMENT_NUM INT(4) COMMENT '评论数
',
        READ_NUM INT(4) COMMENT '浏览量
',
        DELETED_FLAG VARCHAR(2) COMMENT '逻辑删除标识;0:未删除,1:已删除',
        CREATE_DATE DATE,
        MODIFY_DATE DATE,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章信息';

/*==============================================================*/
/* Table: COMMENT_INFO                                               */
/*==============================================================*/
CREATE TABLE
    COMMENT_INFO
    (
        ID INT(10) NOT NULL,
        ARTICLE_ID INT(10) COMMENT '评论文章ID
',
        COMMENT_DATE DATE COMMENT '评论日期
',
        THUNB_NUM INT(4) COMMENT '点赞数
',
        USER_ID INT(10) COMMENT '发表用户
',
        CONTENT text COMMENT '评论内容
',
        COMMENT_NUM INT(4) COMMENT '评论数
',
        PARTENT_ID INT(10) COMMENT '父评论ID
',
        DELETED_FLAG VARCHAR(2) COMMENT '逻辑删除标识;0:未删除,1:已删除',
        CREATE_DATE DATE,
        MODIFY_DATE DATE,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论信息';

/*==============================================================*/
/* Table: INTER_MSG_RECEIVER                                               */
/*==============================================================*/
CREATE TABLE
    INTER_MSG_RECEIVER
    (
        ID INT(10) NOT NULL,
        RECEIVED_USER_ID INT(10) COMMENT '接收人ID

',
        MSG_ID INT(10) COMMENT '站内信ID
',
        NOTICE_STATUS INT(4) COMMENT '查看状态 1:已查看 0：未查看
',
        DELETED_FLAG VARCHAR(2) COMMENT '逻辑删除标识;0:未删除,1:已删除',
        CREATE_DATE DATE,
        MODIFY_DATE DATE,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站内信（接收方）';


/*==============================================================*/
/* Table: inter_msg_sender                                               */
/*==============================================================*/
CREATE TABLE
    inter_msg_sender
    (
        ID INT(10) NOT NULL,
        SEND_USER_ID INT(10) COMMENT '发送人ID
',
        CONTENT VARCHAR(255) COMMENT '发送内容
',
        SEND_DATE DATE COMMENT '发送时间
',
        DELETED_FLAG VARCHAR(2) COMMENT '逻辑删除标识;0:未删除,1:已删除',
        CREATE_DATE DATE,
        MODIFY_DATE DATE,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站内信（发送方）';

/*==============================================================*/
/* Table: link_manage                                               */
/*==============================================================*/
CREATE TABLE
    link_manage
    (
        ID INT(10) NOT NULL,
        URL VARCHAR(255) COMMENT '友链链接

',
        NAME VARCHAR(20) COMMENT '友链名称
',
        LINK_DESC VARCHAR(100) COMMENT '友链描述

',
        LOGO VARCHAR(100) COMMENT '友链LOGO

',
        DELETED_FLAG VARCHAR(2) COMMENT '逻辑删除标识;0:未删除,1:已删除',
        CREATE_DATE DATE,
        MODIFY_DATE DATE,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='友链管理';

/*==============================================================*/
/* Table: type_info                                               */
/*==============================================================*/
CREATE TABLE
    type_info
    (
        ID INT(10) NOT NULL,
        TYPE_NAME VARCHAR(20) COMMENT '分类名称
',
        TYPE_DESC VARCHAR(100) COMMENT '分类描述
',
        PARTENT_ID INT(10) COMMENT '父评论ID
',
        DELETED_FLAG VARCHAR(2) COMMENT '逻辑删除标识;0:未删除,1:已删除',
        CREATE_DATE DATE,
        MODIFY_DATE DATE,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='类别信息';

/*==============================================================*/
/* Table: USER_INFO                                               */
/*==============================================================*/
CREATE TABLE USER_INFO (
        ID INT(10) NOT NULL,
        USER_IP_ADDR VARCHAR(20) COMMENT '用户IP地址
',
        USER_CODE VARCHAR(20) COMMENT '用户名
',
        NICK_NAME VARCHAR(50) COMMENT '用户昵称
',
        USER_PWD VARCHAR(100) COMMENT '用户密码
',
        USER_MAIL VARCHAR(100) COMMENT '用户邮箱
',
        USER_HEAD VARCHAR(100) COMMENT '用户头像
',
        USER_BIRTH DATE COMMENT '用户生日
',
        USER_PHONE INT(20) COMMENT '用户手机号
',
        USER_LEVEL INT(2) COMMENT '用户等级
',
        DELETED_FLAG VARCHAR(2) COMMENT '逻辑删除标识;0:未删除,1:已删除',
        CREATE_DATE DATE,
        MODIFY_DATE DATE,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

/*==============================================================*/
/* Table: WEB_SITE_INFO                                               */
/*==============================================================*/
CREATE TABLE WEB_SITE_INFO
    (
        ID INT(10) NOT NULL,
        SITE_TITLE VARCHAR(50) COMMENT '网站标题
',
        SITE_SUB_TITLE VARCHAR(50) COMMENT '网站副标题
',
        SITE_DESC VARCHAR(100) COMMENT '网站描述
',
        SITE_ICO VARCHAR(100) COMMENT '网站ico图标
',
        SITE_KEYWORD VARCHAR(100) COMMENT '网站关键词
',
        SITE_URL VARCHAR(100) COMMENT '网站网址
',
        DELETED_FLAG VARCHAR(2) COMMENT '逻辑删除标识;0:未删除,1:已删除',
        CREATE_DATE DATE,
        MODIFY_DATE DATE,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站信息';