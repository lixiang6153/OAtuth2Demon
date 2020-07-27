-- ------------------------------------------------------
-- 创建并使用数据库
-- ------------------------------------------------------
set charset utf8;
create database if not exists auth character set UTF8;
use auth;

-- ------------------------------------------------------
-- 创建授权认证中心表
-- ------------------------------------------------------

--
-- Table structure for table `oauth_client_details`
--

CREATE TABLE IF NOT EXISTS `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,						/* 主键,必须唯一,不能为空. 用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). 对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念		*/
  `resource_ids` varchar(256) DEFAULT NULL,				/* 客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: “unity-resource,mobile-resource”. 该字段的值必须来源于与security.xml中标签‹oauth2:resource-server的属性resource-id值一致. 
  														       在security.xml配置有几个‹oauth2:resource-server标签, 则该字段可以使用几个该值. 在实际应用中, 我们一般将资源进行分类,并分别配置对应的‹oauth2:resource-server,如订单资源配置一个
  														   ‹oauth2:resource-server, 用户资源又配置一个‹oauth2:resource-server. 当注册客户端时,根据实际需要可选择资源id,也可根据不同的注册流程,赋予对应的资源id.	*/
  `client_secret` varchar(256) DEFAULT NULL,			/* 用于指定客户端(client)的访问密匙; 在注册时必须填写(也可由服务端自动生成). 对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appSecret,与client_secret是同一个概念.	*/
  `scope` varchar(256) DEFAULT NULL,					/* 指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: “read,write”. scope的值与security.xml中配置的‹intercept-url的access属性有关系. 如‹intercept-url
  														       的配置为‹intercept-url pattern="/m / **" access=“ROLE_MOBILE,SCOPE_READ”/>则说明访问该URL时的客户端必须有read权限范围. write的配置值为SCOPE_WRITE, trust的配置值为SCOPE_TRUST. 
  														       在实际应该中, 该值一般由服务端指定, 常用的值为read,write.	*/
  `authorized_grant_types` varchar(256) DEFAULT NULL,	/* 指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: “authorization_code,password”. 
  														       在实际应用中,当注册时,该字段是一般由服务器端指定的,而不是由申请者去选择的,最常用的grant_type组合有: “authorization_code,refresh_token”(针对通过浏览器访问的客户端); 
  														   “password,refresh_token”(针对移动设备的客户端). implicit与client_credentials在实际中很少使用		*/
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,	/* 客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致. 下面分别说明:当grant_type=authorization_code时, 
  														       第一步 从 spring-oauth-server获取 'code’时客户端发起请求时必须有redirect_uri参数, 该参数的值必须与 web_server_redirect_uri的值一致. 第二步 用 ‘code’ 换取 ‘access_token’ 时客户也必须传递相同
  														       的redirect_uri. 在实际应用中, web_server_redirect_uri在注册时是必须填写的, 一般用来处理服务器返回的code, 验证state是否合法与通过code去换取access_token值.在spring-oauth-client项目中, 可
  														       具体参考AuthorizationCodeController.java中的authorizationCodeCallback方法.当grant_type=implicit时通过redirect_uri的hash值来传递access_token值.
  														       如:http://localhost:7777/spring-oauth-client/implicit#access_token=dc891f4a-ac88-4ba6-8224-a2497e013865&token_type=bearer&expires_in=43199然后客户端通过JS等从hash值中取到access_token值. 	*/
  `authorities` varchar(256) DEFAULT NULL,				/* 指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_			*/
  `access_token_validity` int(11) DEFAULT NULL,			/* 设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时). 在服务端获取的access_token JSON数据中的expires_in字段的值即为当前access_token的有效时间值. 
  														       在项目中, 可具体参考DefaultTokenServices.java中属性accessTokenValiditySeconds. 在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义.refresh_token_validity	设定客户端的refresh_token的有效
  														       时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). 若客户端的grant_type不包括refresh_token,则不用关心该字段 在项目中, 可具体参考DefaultTokenServices.java中属性
  														   refreshTokenValiditySeconds. 在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义	*/
  `refresh_token_validity` int(11) DEFAULT NULL,		/* 设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). 若客户端的grant_type不包括refresh_token,则不用关心该字段 在项目中, 可具体参考
  														   DefaultTokenServices.java中属性refreshTokenValiditySeconds. 在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义		*/
  `additional_information` varchar(4096) DEFAULT NULL,	/* 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:{“country”:“CN”,“country_code”:“086”}按照spring-security-oauth项目中对该字段的描述 Additional information 
  														   for this client, not need by the vanilla OAuth protocol but might be useful, for example,for storing descriptive information. (详见ClientDetails.java的getAdditionalInformation()方法的注释)
  														       在实际应用中, 可以用该字段来存储关于客户端的一些其他信息,如客户端的国家,地区,注册时的IP地址等等.create_time	数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)	*/
  `autoapprove` varchar(256) DEFAULT NULL,				/* 设置用户是否自动Approval操作, 默认值为 ‘false’, 可选值包括 ‘true’,‘false’, ‘read’,‘write’. 该字段只适用于grant_type="authorization_code"的情况,当用户登录成功后,若该值为’true’或支持的scope值,则会跳过用户Approve的页面, 
  														       直接授权. 该字段与 trusted 有类似的功能, 是 spring-security-oauth2 的 2.0 版本后添加的新属性. 在项目中,主要操作oauth_client_details表的类是JdbcClientDetailsService.java, 更多的细节请参考该类. 也可以根据实际的需要,
  														       去扩展或修改该类的实现.	*/
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `oauth_client_token`
--

CREATE TABLE IF NOT EXISTS `oauth_client_token` (
  `token_id` varchar(128) DEFAULT NULL,					/* 从服务器端获取到的access_token的值.	*/
  `token` blob,											/* 这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据	*/
  `authentication_id` varchar(128) NOT NULL,			/* 该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 具体实现请参考DefaultClientKeyGenerator.java类	*/
  `user_name` varchar(128) DEFAULT NULL,				/* 登录时的用户名						*/
  `client_id` varchar(128) DEFAULT NULL,				/* 客户appkey						*/
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `oauth_access_token`
--

CREATE TABLE IF NOT EXISTS `oauth_access_token` (
  `token_id` varchar(128) DEFAULT NULL,					/* 该字段的值是将access_token的值通过MD5加密后存储的  										*/
  `token` blob,											/* 存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值			*/
  `authentication_id` varchar(128) NOT NULL,			/* 该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的 			*/
  `user_name` varchar(128) DEFAULT NULL,				/* 登录时的用户名, 若客户端没有用户名(如grant_type=“client_credentials”),则该值等于client_id	*/
  `client_id` varchar(128) DEFAULT NULL,				/* 平台注册的客户id																		*/
  `authentication` blob,								/* 存储将OAuth2Authentication.java对象序列化后的二进制数据									*/
  `refresh_token` varchar(128) DEFAULT NULL,			/* 该字段的值是将refresh_token的值通过MD5加密后存储的. 在项目中,主要操作oauth_access_token表的对象是JdbcTokenStore.java	*/
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `oauth_refresh_token`
--

CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  `token_id` varchar(128) NOT NULL,						/* 该字段的值是将refresh_token的值通过MD5加密后存储的		*/
  `token` blob,											/* 存储将OAuth2RefreshToken.java对象序列化后的二进制数据	*/
  `authentication` blob,								/* 存储将OAuth2Authentication.java对象序列化后的二进制数据	*/
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `oauth_approvals`
--

CREATE TABLE IF NOT EXISTS `oauth_approvals` (
  `userId` varchar(256) NOT NULL,						/* 登录的用户名						*/
  `clientId` varchar(256) NOT NULL,						/* 客户端ID							*/
  `scope` varchar(256) DEFAULT NULL,					/* 申请的权限							*/
  `status` varchar(10) DEFAULT NULL,					/* 状态（Approve或Deny）				*/
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,	/* 过期时间 		*/
  `lastModifiedAt` timestamp NOT NULL DEFAULT now(),	/*  最终修改时间  						*/
  PRIMARY KEY (`userId`, `clientId`)					/*  自定义主键							*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `oauth_code`
--

CREATE TABLE IF NOT EXISTS `oauth_code` (
  -- `code` varchar(128) DEFAULT NULL,					/* 存储服务端系统生成的code的值			*/
  `code` varchar(128) NOT NULL,							/* 存储服务端系统生成的code的值			*/
  `authentication` blob,								/* 存储将AuthorizationRequestHolder.java对象序列化后的二进制数据	*/
  PRIMARY KEY (`code`)									/* 自定义添加主键						*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- 插入用户授权秘钥测试记录：wx_takout_client_id秘钥值为明文为wx_takeout_client_secret
--
LOCK TABLES `oauth_client_details` WRITE;
INSERT INTO `oauth_client_details` VALUES ('dondown_client_id','gate_way_server','$2a$10$5NlAY6Q6WJQW7sqFVELN3ub32eIL5PyPfRE9dqru7MAMKHU7SuOmK','user_info','authorization_code,refresh_token,implicit,password,client_credentials','http://www.baidu.com','ROLE_ADMIN',7200,86400,'{\"systemInfo\":\"Atlas System\"}','true');
UNLOCK TABLES;


--
-- 创建权限表
--
CREATE TABLE IF NOT EXISTS t_right (
 	id int NOT NULL,							/*     权限标识		    				*/
  	name varchar(255) DEFAULT '',				/*     权限名称		    				*/
  	description varchar(64) DEFAULT '',			/*     权限描述		    				*/
  	reserver1 varchar(64) default NULL,			/*     保留字段		    				*/
	reserver2 varchar(64) default NULL,			/*     保留字段		    				*/
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system right';

--
-- 系统接口地址表
--
CREATE TABLE IF NOT EXISTS t_url (
 	id int NOT NULL,							/*     权限明细标识		    			*/
  	url varchar(256) DEFAULT '',				/*     接口地址	    					*/
  	description varchar(64) DEFAULT '',			/*     接口描述		    				*/
  	modify tinyint DEFAULT 0,				    /*     接口是否是更新操作				*/
  	reserver1 varchar(64) default NULL,			/*     保留字段		    				*/
	reserver2 varchar(64) default NULL,			/*     保留字段		    				*/
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system url list';

--
-- 权限接口表
--
CREATE TABLE IF NOT EXISTS t_right_item (
 	id int NOT NULL AUTO_INCREMENT,				/*     角色标识		    				*/
	rightId int NOT NULL,						/*     权限标识		    				*/
	urlId int NOT NULL,							/*     接口标识		    				*/
  	reserver1 varchar(64) default NULL,			/*     保留字段		    				*/
	reserver2 varchar(64) default NULL,			/*     保留字段		    				*/
	primary key(id),
	foreign key(rightId) references t_right(id),
	foreign key(urlId) references t_url(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system url list';

--
-- 创建角色表
--
CREATE TABLE IF NOT EXISTS t_role (
  	id bigint(20) NOT NULL AUTO_INCREMENT,		/*     角色标识		    				*/
  	name varchar(64) DEFAULT '',				/*     角色名称		    				*/
 	description varchar(255) DEFAULT '',		/*     角色描述		    				*/
 	userType tinyint DEFAULT 1,				    /*     1：运营商,2学校,3机构,4教师,5家长	*/
 	relativeId varchar(20) DEFAULT '',			/*     关联学校或机构id	    			*/
 	createUser varchar(32) DEFAULT '',			/*     创建用户名			     	    */
  	reserver1 varchar(64) default NULL,			/*     保留字段		    				*/
	reserver2 varchar(64) default NULL,			/*     保留字段		    				*/
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system role';

--
-- 创建角色权限表
--
CREATE TABLE IF NOT EXISTS t_role_right (
  	id bigint(20) NOT NULL AUTO_INCREMENT,		/*     表标识							*/
  	roleId bigint(20) NOT NULL,					/*     角色标识		    				*/
  	rightId int DEFAULT NULL,					/*     权限标识		    				*/
  	reserver1 varchar(64) default NULL,			/*     保留字段		    				*/
	reserver2 varchar(64) default NULL,			/*     保留字段		    				*/
	primary key(id),
	foreign key(roleId) references t_role(id),
	foreign key(rightId) references t_right(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='role right';

--
-- 创建用户表
--
CREATE TABLE IF NOT EXISTS t_user (                                          
  	id bigint(20) NOT NULL AUTO_INCREMENT,		/*     表标识			     	        */
  	username varchar(32) DEFAULT '',			/*     用户名			     	        */
  	password varchar(255) DEFAULT '',			/*     密码			         			*/            
  	mobile varchar(16) DEFAULT '',				/*     手机			         			*/            
  	email varchar(32) DEFAULT '',				/*     电子邮件		         			*/            
  	userType tinyint DEFAULT 1,				    /*     1：运营商,2学校,3机构,4教师,5家长	*/
  	relativeId varchar(20) DEFAULT '',			/*     关联学校或机构id	    			*/
  	head varchar(256) DEFAULT '',				/*     用户头像	    					*/
  	admin tinyint DEFAULT 0,				    /*     是否超级管理员					*/
  	enabled tinyint DEFAULT 1,				    /*     可用性							*/
  	expired tinyint DEFAULT 0,					/*	        是否过期					*/
  	locked tinyint DEFAULT 0,					/*	        是否锁定					*/
  	createUser varchar(32) DEFAULT '',			/*     创建用户名			     	    */
  	createTime datetime default now(),			/*     创建时间		        			*/
  	reserver1 varchar(64) default NULL,			/*     保留字段		    				*/
	reserver2 varchar(64) default NULL,			/*     保留字段		    				*/
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system user';

--
-- 创建用户角色表
--
CREATE TABLE IF NOT EXISTS t_user_role (
  	id bigint(20) NOT NULL AUTO_INCREMENT,		/*     表标识							*/
  	userId bigint(20) NOT NULL,					/*     用户标识		    				*/
  	roleId bigint(20) NOT NULL,					/*     角色标识		    				*/
  	reserver1 varchar(64) default NULL,			/*     保留字段		    				*/
	reserver2 varchar(64) default NULL,			/*     保留字段		    				*/
	primary key(id),
	foreign key(userId) references t_user(id),
	foreign key(roleId) references t_role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user role';