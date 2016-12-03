create table Utilisateur (
	userid INTEGER auto_increment primary key,
	username VARCHAR(45),
	email VARCHAR(255),
	password VARCHAR(60),
	enabled INT DEFAULT 1
);

create table user_role (
  userid INTEGER,
  name VARCHAR(256),
  primary key(userid,name)
);

create table ROLE (
  name VARCHAR(256) primary key,
);

-- claude: $2a$10$yFbQtEm0arOkI30gmM2BL.HclaYKAhWgZehCf9asTdXr25MvxuQim
-- MyBigSecret: $2a$10$xblaUpsWWLLbXtn4Z58bWumtIUPZewZLP2wy.rvTiKJBSF9ZSkJPq
-- admin: $2a$10$YdoUqwbZrYgqKIGaeQUREewVv7OzJ8Z/H5ox4j5irLCGqr3MB5oSy
-- user: $2a$10$IxNYXRr66hNzwy9Tg9YIoueV3Cvl1l6cbHu0Yjum5a.xPqfIXUAiO


INSERT INTO ROLE
  VALUES ('USER'),('ADMIN'),('MANAGER'),('API');
  

INSERT INTO Utilisateur  (userid, username, email, password) 
	VALUES (1, 'csa','c.sansoucy@orange.lc','$2a$10$yFbQtEm0arOkI30gmM2BL.HclaYKAhWgZehCf9asTdXr25MvxuQim');
INSERT INTO user_role
  VALUES (1,'USER');
  
	
INSERT INTO Utilisateur (userid, username, email, password) 
	VALUES (2, 'admin','admin@orange.lc','$2a$10$YdoUqwbZrYgqKIGaeQUREewVv7OzJ8Z/H5ox4j5irLCGqr3MB5oSy');
INSERT INTO user_role 
  VALUES (2,'ADMIN');
 
INSERT INTO Utilisateur  (userid, username, email, password) 
	VALUES (3, 'manager','actuator@orange.lc','$2a$10$xblaUpsWWLLbXtn4Z58bWumtIUPZewZLP2wy.rvTiKJBSF9ZSkJPq');
INSERT INTO user_role 
  VALUES (3,'MANAGER');  


INSERT INTO Utilisateur (userid, username, email, password) 
	VALUES (4, 'oauth_admin','admin@orange.lc','$2a$10$YdoUqwbZrYgqKIGaeQUREewVv7OzJ8Z/H5ox4j5irLCGqr3MB5oSy');
INSERT INTO user_role 
  VALUES (4,'ADMIN');
   
INSERT INTO Utilisateur (userid, username, email, password) 
	VALUES (5, 'resource_admin','admin@orange.lc','$2a$10$YdoUqwbZrYgqKIGaeQUREewVv7OzJ8Z/H5ox4j5irLCGqr3MB5oSy');
INSERT INTO user_role 
  VALUES (5,'MANAGER');

INSERT INTO Utilisateur (userid, username, email, password) 
	VALUES (6, 'user','user@orange.lc','$2a$10$IxNYXRr66hNzwy9Tg9YIoueV3Cvl1l6cbHu0Yjum5a.xPqfIXUAiO');
INSERT INTO user_role 
  VALUES (6,'USER');
