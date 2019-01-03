-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS sys_menu;
DROP SEQUENCE IF EXISTS sys_menu_seq;
CREATE SEQUENCE sys_menu_seq;

CREATE TABLE sys_menu (
  id int NOT NULL DEFAULT NEXTVAL ('sys_menu_seq'),
  parent_id int DEFAULT NULL ,
  menu_name varchar(32) NOT NULL ,
  path varchar(64) DEFAULT NULL ,
  menu_type smallint NOT NULL ,
  icon varchar(32) DEFAULT NULL ,
  create_uid int NOT NULL ,
  update_uid int NOT NULL ,
  create_time timestamp(0) NOT NULL ,
  update_time timestamp(0) NOT NULL ,
  status smallint NOT NULL ,
  router varchar(64) DEFAULT NULL ,
  alias varchar(64) DEFAULT NULL,
  PRIMARY KEY (id)
)   ;

ALTER SEQUENCE sys_menu_seq RESTART WITH 43;

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO sys_menu VALUES ('1', '0', '系統管理', '', '1', 'layui-icon-set', '1', '1', '2018-11-27 14:52:10', '2018-11-27 15:11:15', '0', null, ''), ('23', '1', '使用者管理', 'views/user/index.html', '2', 'layui-icon-username', '1', '1', '2018-11-27 15:10:32', '2018-12-12 15:39:18', '0', 'user', 'sys:user:list'), ('24', '1', '角色管理', 'views/role/index.html', '2', 'layui-icon-face-surprised', '1', '1', '2018-11-27 15:16:59', '2018-12-12 15:40:03', '0', 'role', 'sys:role:list'), ('25', '1', '選單管理', 'views/menu/index.html', '2', 'layui-icon-template', '1', '1', '2018-11-27 15:17:59', '2018-12-12 15:37:35', '0', 'menu', 'sys:menu:list'), ('26', '1', '資源管理', 'views/resource/index.html', '2', 'layui-icon-read', '1', '1', '2018-11-27 15:18:31', '2018-12-12 15:35:38', '0', 'resource', 'sys:resource:list'), ('27', '26', '重新整理資源', '', '3', 'layui-icon-refresh-3', '1', '1', '2018-11-27 15:19:15', '2018-12-12 15:35:14', '0', null, 'sys:resource:refresh'), ('28', '25', '新增', '', '3', 'layui-icon-add-circle-fine', '1', '1', '2018-11-27 15:20:06', '2018-12-12 15:45:47', '0', null, 'sys:menu:add'), ('29', '25', '修改', '', '3', 'layui-icon-senior', '1', '1', '2018-11-27 15:20:27', '2018-12-12 15:36:51', '0', null, 'sys:menu:edit'), ('30', '25', '刪除', '', '3', 'layui-icon-close', '1', '1', '2018-11-27 15:21:14', '2018-12-12 15:35:49', '0', null, 'sys:menu:delete'), ('31', '24', '新增', '', '3', 'layui-icon-add-circle-fine', '1', '1', '2018-11-27 15:20:06', '2018-12-12 15:38:07', '0', null, 'sys:role:add'), ('32', '24', '修改', '', '3', 'layui-icon-senior', '1', '1', '2018-11-27 15:20:27', '2018-12-12 15:44:19', '0', null, 'sys:role:edit'), ('33', '24', '刪除', '', '3', 'layui-icon-close', '1', '1', '2018-11-27 15:21:14', '2018-12-12 15:36:07', '0', null, 'sys:role:delete'), ('34', '23', '新增', '', '3', 'layui-icon-add-circle-fine', '1', '1', '2018-11-27 15:20:06', '2018-12-12 15:44:04', '0', null, 'sys:user:add'), ('35', '23', '修改', '', '3', 'layui-icon-senior', '1', '1', '2018-11-27 15:20:27', '2018-12-12 15:39:36', '0', null, 'sys:user:edit'), ('36', '23', '重置密碼', '', '3', 'layui-icon-fire', '1', '1', '2018-11-27 15:21:14', '2018-12-12 15:38:48', '0', null, 'sys:user:resetpwd'), ('42', '24', '選單授權', null, '3', 'layui-icon-auz', '1', '1', '2018-12-08 23:58:42', '2018-12-12 15:41:52', '0', null, 'sys:role:perm');
COMMIT;

-- ----------------------------
--  Table structure for `sys_menu_resource`
-- ----------------------------
DROP TABLE IF EXISTS sys_menu_resource;
DROP SEQUENCE IF EXISTS sys_menu_resource_seq;
CREATE SEQUENCE sys_menu_resource_seq;

CREATE TABLE sys_menu_resource (
  id int NOT NULL DEFAULT NEXTVAL ('sys_menu_resource_seq'),
  menu_id int DEFAULT NULL ,
  resource_id varchar(32) DEFAULT NULL ,
  PRIMARY KEY (id)
)   ;

ALTER SEQUENCE sys_menu_resource_seq RESTART WITH 102;

-- ----------------------------
--  Records of `sys_menu_resource`
-- ----------------------------
BEGIN;
INSERT INTO sys_menu_resource VALUES ('70', '27', 'f45f1b577d72dcd86b84c6f033682b53'), ('71', '26', '829a851334028a6e47b59f8dea0cf7cb'), ('72', '30', 'f15f7b01ffe7166b05c3984c9b967837'), ('73', '33', '6692d9d95184977f82d3252de2f5eac7'), ('74', '29', 'a11e2191656cb199bea1defb17758411'), ('75', '29', '6fd51f02b724c137a08c28587f48d7f3'), ('76', '29', '2c654f1264fc85ac80516245672f3c47'), ('77', '29', 'a5529264d2645996c83bba2e961d0ec3'), ('80', '25', '6d1170346960aa8922b9b4d08a5bf71b'), ('81', '25', '30218613e987e464b13e0c0b8721aec5'), ('83', '31', 'd82de0a17f2c63106f98eb2f88d166e9'), ('85', '36', '7baa5b852bc92715d7aa503c0a0d8925'), ('87', '23', '579e469e8ac850de1ca0adc54d01acba'), ('88', '23', 'b4770c0fe93fce7e829463328c800f20'), ('89', '35', '30386fd7b8a4feb9c59861e63537acde'), ('90', '35', '8a3b4dc05867f5946235ba5fbc492b76'), ('91', '24', '04972e9f8e65b0364d9862687666da36'), ('93', '42', 'a826bca352908155da4ca6702edfa2ed'), ('94', '42', '30218613e987e464b13e0c0b8721aec5'), ('95', '34', 'a71cb59835c613f39bd936deb20cdd27'), ('96', '34', 'd9d6f7163b313b950710a637682354d1'), ('97', '32', 'eaee955f405f6b96beab5136bfa3e29b'), ('98', '32', 'd9d6f7163b313b950710a637682354d1'), ('99', '28', '8cb1442c7814f65ce0d796e1ef93c715'), ('100', '28', 'a5529264d2645996c83bba2e961d0ec3'), ('101', '28', '2c654f1264fc85ac80516245672f3c47');
COMMIT;

-- ----------------------------
--  Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS sys_resource;
CREATE TABLE sys_resource (
  id varchar(32) NOT NULL,
  resource_name varchar(32) NOT NULL ,
  mapping varchar(64) NOT NULL ,
  method varchar(6) NOT NULL ,
  auth_type smallint NOT NULL ,
  update_time timestamp(0) DEFAULT NULL,
  perm varchar(68) NOT NULL ,
  PRIMARY KEY (id)
)  ;

BEGIN;
INSERT INTO sys_resource VALUES ('04972e9f8e65b0364d9862687666da36', '查詢所有角色(分頁)', '/roles', 'GET', '3', '2018-12-12 15:32:01', 'GET:/roles'), ('29c4c75326ecf3a82f815c43b0085b2f', '修改賬戶資訊', '/account/info', 'PUT', '1', '2018-12-12 15:32:01', 'PUT:/account/info'), ('2c654f1264fc85ac80516245672f3c47', '查詢父級選單(下拉框)', '/menus/combos', 'GET', '2', '2018-12-12 15:32:01', 'GET:/menus/combos'), ('30218613e987e464b13e0c0b8721aec5', '查詢所有選單', '/menus', 'GET', '3', '2018-12-12 15:32:01', 'GET:/menus'), ('30386fd7b8a4feb9c59861e63537acde', '修改使用者', '/users/{id}', 'PUT', '3', '2018-12-12 15:32:01', 'PUT:/users/{id}'), ('579e469e8ac850de1ca0adc54d01acba', '查詢所有使用者', '/users', 'GET', '3', '2018-12-12 15:32:01', 'GET:/users'), ('6692d9d95184977f82d3252de2f5eac7', '刪除角色', '/roles/{id}', 'DELETE', '3', '2018-12-12 15:32:01', 'DELETE:/roles/{id}'), ('6ab0f8a49671e489f11a1bef2fcaf102', '清除Token', '/account/token', 'DELETE', '1', '2018-12-12 15:32:01', 'DELETE:/account/token'), ('6d1170346960aa8922b9b4d08a5bf71b', '設定選單狀態', '/menus/{id}/status', 'PUT', '3', '2018-12-12 15:32:01', 'PUT:/menus/{id}/status'), ('6fd51f02b724c137a08c28587f48d7f3', '查詢單個選單', '/menus/{id}', 'GET', '3', '2018-12-12 15:32:01', 'GET:/menus/{id}'), ('7baa5b852bc92715d7aa503c0a0d8925', '重置使用者密碼', '/users/{id}/password', 'PUT', '3', '2018-12-12 15:32:01', 'PUT:/users/{id}/password'), ('829a851334028a6e47b59f8dea0cf7cb', '查詢所有資源(分頁)', '/resources', 'GET', '3', '2018-12-12 15:32:01', 'GET:/resources'), ('842e33410b5a97b6c797e4782c97a90e', '獲取Token', '/account/token', 'POST', '2', '2018-12-12 15:32:01', 'POST:/account/token'), ('8a3b4dc05867f5946235ba5fbc492b76', '查詢單個使用者', '/users/{id}', 'GET', '3', '2018-12-12 15:32:01', 'GET:/users/{id}'), ('8cb1442c7814f65ce0d796e1ef93c715', '新增選單', '/menus', 'POST', '3', '2018-12-12 15:32:01', 'POST:/menus'), ('982803fc834e82cbb2ac1b93f2a47690', '查詢單個角色', '/roles/{id}', 'GET', '3', '2018-12-12 15:32:01', 'GET:/roles/{id}'), ('a11e2191656cb199bea1defb17758411', '修改選單', '/menus/{id}', 'PUT', '3', '2018-12-12 15:32:01', 'PUT:/menus/{id}'), ('a25e9909a0620e00ab863aed70c61432', '獲取賬戶按鈕', '/account/bottons/aliases', 'GET', '1', '2018-12-12 15:32:01', 'GET:/account/bottons/aliases'), ('a5529264d2645996c83bba2e961d0ec3', '查詢所有資源', '/resources/resources', 'GET', '3', '2018-12-12 15:32:01', 'GET:/resources/resources'), ('a71cb59835c613f39bd936deb20cdd27', '建立使用者', '/users', 'POST', '3', '2018-12-12 15:32:01', 'POST:/users'), ('a826bca352908155da4ca6702edfa2ed', '修改角色選單', '/roles/{id}/menus', 'PUT', '3', '2018-12-12 15:32:01', 'PUT:/roles/{id}/menus'), ('b4770c0fe93fce7e829463328c800f20', '設定使用者狀態', '/users/{id}/status', 'PUT', '3', '2018-12-12 15:32:01', 'PUT:/users/{id}/status'), ('c2db9729dcd4a7d703e45411bb445dfd', '修改密碼', '/account/password', 'PUT', '1', '2018-12-12 15:32:01', 'PUT:/account/password'), ('d81bffa6ffd70cc443703820b5a95e8d', '獲取賬戶選單', '/account/menus', 'GET', '1', '2018-12-12 15:32:01', 'GET:/account/menus'), ('d82de0a17f2c63106f98eb2f88d166e9', '新增角色', '/roles', 'POST', '3', '2018-12-12 15:32:01', 'POST:/roles'), ('d9d6f7163b313b950710a637682354d1', '查詢所有角色', '/roles/roles', 'GET', '3', '2018-12-12 15:32:01', 'GET:/roles/roles'), ('e78940daf86b9ac5563d539e8802429c', '獲取賬戶詳情', '/account/info', 'GET', '1', '2018-12-12 15:32:01', 'GET:/account/info'), ('eaee955f405f6b96beab5136bfa3e29b', '修改角色', '/roles/{id}', 'PUT', '3', '2018-12-12 15:32:01', 'PUT:/roles/{id}'), ('f15f7b01ffe7166b05c3984c9b967837', '刪除選單', '/menus/{id}', 'DELETE', '3', '2018-12-12 15:32:01', 'DELETE:/menus/{id}'), ('f45f1b577d72dcd86b84c6f033682b53', '重新整理資源', '/resources', 'PUT', '3', '2018-12-12 15:32:01', 'PUT:/resources');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
DROP SEQUENCE IF EXISTS sys_role_seq;
CREATE SEQUENCE sys_role_seq;

CREATE TABLE sys_role (
  id int NOT NULL DEFAULT NEXTVAL ('sys_role_seq'),
  role_name varchar(64) NOT NULL ,
  create_uid int NOT NULL ,
  update_uid int NOT NULL ,
  create_time timestamp(0) NOT NULL ,
  update_time timestamp(0) NOT NULL ,
  remark varchar(128) DEFAULT NULL ,
  PRIMARY KEY (id)
)   ;

ALTER SEQUENCE sys_role_seq RESTART WITH 3;

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO sys_role VALUES ('1', '超級管理員', '1', '1', '2018-11-12 15:59:43', '2018-11-12 15:59:47', '超級管理員'), ('2', '普通管理員', '1', '1', '2018-11-12 16:00:17', '2018-11-12 16:00:19', '普通管理員');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS sys_role_menu;
DROP SEQUENCE IF EXISTS sys_role_menu_seq;
CREATE SEQUENCE sys_role_menu_seq;

CREATE TABLE sys_role_menu (
  id int NOT NULL DEFAULT NEXTVAL ('sys_role_menu_seq'),
  role_id int NOT NULL ,
  menu_id int NOT NULL ,
  PRIMARY KEY (id)
)   ;

ALTER SEQUENCE sys_role_menu_seq RESTART WITH 278;

-- ----------------------------
--  Records of `sys_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO sys_role_menu VALUES ('245', '2', '1'), ('246', '2', '26'), ('247', '2', '27'), ('262', '1', '1'), ('263', '1', '23'), ('264', '1', '34'), ('265', '1', '35'), ('266', '1', '36'), ('267', '1', '24'), ('268', '1', '31'), ('269', '1', '32'), ('270', '1', '33'), ('271', '1', '42'), ('272', '1', '25'), ('273', '1', '28'), ('274', '1', '29'), ('275', '1', '30'), ('276', '1', '26'), ('277', '1', '27');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS sys_role_resource;
DROP SEQUENCE IF EXISTS sys_role_resource_seq;
CREATE SEQUENCE sys_role_resource_seq;

CREATE TABLE sys_role_resource (
  id int NOT NULL DEFAULT NEXTVAL ('sys_role_resource_seq'),
  role_id int NOT NULL ,
  resource_id varchar(32) NOT NULL ,
  PRIMARY KEY (id)
)  ;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
DROP SEQUENCE IF EXISTS sys_user_seq;
CREATE SEQUENCE sys_user_seq;

CREATE TABLE sys_user (
  uid int NOT NULL DEFAULT NEXTVAL ('sys_user_seq'),
  nickname varchar(50) NOT NULL ,
  email varchar(100) DEFAULT NULL ,
  phone varchar(11) DEFAULT NULL ,
  status smallint NOT NULL ,
  create_uid int NOT NULL ,
  create_time timestamp(0) NOT NULL ,
  update_time timestamp(0) NOT NULL ,
  login_name varchar(16) NOT NULL ,
  password varchar(64) NOT NULL ,
  ip varchar(32) DEFAULT NULL ,
  PRIMARY KEY (uid)
)   ;

ALTER SEQUENCE sys_user_seq RESTART WITH 20;

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO sys_user VALUES ('1', 'Crown', 'caratacus@qq.com', '13712345678', '0', '1', '2018-11-05 17:19:05', '2018-12-10 16:22:22', 'crown', '$apr1$crown$WQ2TEXVPUJ8l6N6gm0CGv.', '0:0:0:0:0:0:0:1'), ('18', 'crown1', '11@qq.com', '13718867899', '1', '1', '2018-11-19 18:56:19', '2018-12-10 15:27:20', 'crown1', '$apr1$crown1$NsepppGmlSjqtwPTlaLb1/', '0:0:0:0:0:0:0:1'), ('19', 'crown2', '13878929833@163.com', '13878929833', '1', '18', '2018-12-10 15:25:57', '2018-12-10 15:25:57', 'crown2', '$apr1$crown2$R/8LgZ.REDrXB3jlpyglI0', null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS sys_user_role;
DROP SEQUENCE IF EXISTS sys_user_role_seq;
CREATE SEQUENCE sys_user_role_seq;

CREATE TABLE sys_user_role (
  id int NOT NULL DEFAULT NEXTVAL ('sys_user_role_seq'),
  uid int DEFAULT NULL ,
  role_id int DEFAULT NULL ,
  PRIMARY KEY (id)
)   ;

ALTER SEQUENCE sys_user_role_seq RESTART WITH 51;

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO sys_user_role VALUES ('24', '18', '2'), ('49', '1', '1'), ('50', '19', '2');
COMMIT;

-- ----------------------------
--  Table structure for `sys_customer`
-- ----------------------------
DROP TABLE IF EXISTS sys_customer;
DROP SEQUENCE IF EXISTS sys_customer_seq;
CREATE SEQUENCE sys_customer_seq;

CREATE TABLE sys_customer (
  id int NOT NULL DEFAULT NEXTVAL ('sys_customer_seq'),
  customer_name varchar(64) NOT NULL ,
  create_uid int NOT NULL ,
  update_uid int NOT NULL ,
  create_time timestamp(0) NOT NULL ,
  update_time timestamp(0) NOT NULL ,
  remark varchar(128) DEFAULT NULL ,
  PRIMARY KEY (id)
)   ;

ALTER SEQUENCE sys_customer_seq RESTART WITH 3;

-- ----------------------------
--  Records of `sys_customer`
-- ----------------------------
BEGIN;
INSERT INTO sys_customer VALUES ('1', '一號客戶', '1', '1', '2018-12-16 09:34:43', '2018-12-16 09:34:43', '一號客戶');
COMMIT;

-- ----------------------------
--  Table structure for `sys_process`
-- ----------------------------
DROP TABLE IF EXISTS sys_process;
DROP SEQUENCE IF EXISTS sys_process_seq;
CREATE SEQUENCE sys_process_seq;

CREATE TABLE sys_process (
    name character varying(20) NOT NULL,
    sort smallint NOT NULL,
    status smallint DEFAULT 1 NOT NULL,
    id smallint DEFAULT nextval('public.process_id_seq'::regclass) NOT NULL
);

ALTER SEQUENCE sys_process_seq RESTART WITH 6;

-- ----------------------------
--  Records of `sys_process`
-- ----------------------------
BEGIN;
INSERT INTO sys_process VALUES ('噴砂', 5, 1, 1),('拋光', 2, 1, 2),('清洗', 3, 1, 3),('磨砂', 1, 1, 4),('髮線', 4, 1, 5);
COMMIT;