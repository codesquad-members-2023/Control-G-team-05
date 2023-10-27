CREATE TABLE IF NOT EXISTS member (
                        id	         BIGINT        AUTO_INCREMENT,
                        email	     VARCHAR(50)   NOT NULL UNIQUE,
                        name	     VARCHAR(10)   NOT NULL,
                        nickname	 VARCHAR(10)   NOT NULL,
                        gender	     VARCHAR(10)   NOT NULL,
                        profile_img	 VARCHAR(1000) NOT NULL	DEFAULT "https://png.pngtree.com/png-vector/20191115/ourmid/pngtree-beautiful-profile-line-vector-icon-png-image_1990469.jpg",
                        birth        DATE	       NOT NULL,
                        introduction VARCHAR(1000),
                        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS token (
                        id	     BIGINT        AUTO_INCREMENT,
                        member_id BIGINT	       NOT NULL,
                        token	 VARCHAR(1000) NOT NULL,
                        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS `like` (
                        id	     BIGINT AUTO_INCREMENT,
                        liker_id BIGINT	NOT NULL,
                        liked_id BIGINT	NOT NULL,
                        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS block (
                        id         BIGINT AUTO_INCREMENT,
                        blocker_id BIGINT NOT NULL,
                        blocked_id BIGINT NOT NULL,
                        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS `group` (
                        id	  BIGINT        AUTO_INCREMENT,
                        name VARCHAR(30)   NOT NULL,
                        img  VARCHAR(1000) NOT NULL DEFAULT "https://png.pngtree.com/png-vector/20191115/ourmid/pngtree-beautiful-profile-line-vector-icon-png-image_1990469.jpg",
                        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS member_group (
                        id	        BIGINT AUTO_INCREMENT,
                        member_id	BIGINT NOT NULL,
                        group_id	BIGINT NOT NULL,
                        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS chat_room (
                        id         BIGINT AUTO_INCREMENT,
                        group_id   BIGINT NOT NULL,
                        member_id1 BIGINT NOT NULL,
                        member_id2 BIGINT NOT NULL,
                        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS chat_message (
                        id           BIGINT        AUTO_INCREMENT,
                        chat_room_id BIGINT        NOT NULL,
                        sender_id	 BIGINT        NOT NULL,
                        message      VARCHAR(1000) NOT NULL,
                        sent_at      TIMESTAMP     NOT NULL,
                        is_read      BOOLEAN       NOT NULL DEFAULT 0,
                        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS chat_member (
                        id	            BIGINT  AUTO_INCREMENT,
                        chat_room_id    BIGINT	 NOT NULL,
                        member_id       BIGINT	 NOT NULL,
                        last_message_id BIGINT	 NOT NULL DEFAULT 0,
                        is_exit         BOOLEAN NOT NULL DEFAULT 0,
                        PRIMARY KEY(id)
);