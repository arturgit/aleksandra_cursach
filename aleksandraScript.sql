DROP DATABASE IF EXISTS aleksandradb;
CREATE DATABASE aleksandradb;
USE aleksandradb;

CREATE TABLE roles (
	id int not null auto_increment,
	name VARCHAR(30) not null,
	primary key (id)
);

CREATE TABLE positions (
	id int not null auto_increment,
	name VARCHAR(30) not null,
	primary key (id)
);

CREATE TABLE levels (
	id int not null auto_increment,
	name VARCHAR(30) not null,
	primary key (id)
);

CREATE TABLE users (
	id int not null auto_increment,
	login VARCHAR(100) not null,
	password VARCHAR(100) not null,
	name VARCHAR(100) not null,
	role_id int not null,
	position_id int not null,
	level_id int not null,
	primary key (id),
	CONSTRAINT FK_users_roles FOREIGN KEY (role_id) REFERENCES roles (id),
	CONSTRAINT FK_users_positions FOREIGN KEY (position_id) REFERENCES positions (id),
	CONSTRAINT FK_users_levels FOREIGN KEY (level_id) REFERENCES levels (id)
);

CREATE TABLE tests (
	id int not null auto_increment,
	title VARCHAR(200) not null,
	position_id int not null,
	level_id int not null,
	primary key (id),
	CONSTRAINT UC_PostLevel UNIQUE (position_id,level_id),
	CONSTRAINT FK_users_positions FOREIGN KEY (position_id) REFERENCES positions (id),
	CONSTRAINT FK_users_levels FOREIGN KEY (level_id) REFERENCES levels (id)
);

CREATE TABLE questions (
	id int not null auto_increment,
	title VARCHAR(200) not null,
	test_id int not null,
	primary key (id),
	CONSTRAINT FK_questions_tests FOREIGN KEY (test_id) REFERENCES tests (id)
);


CREATE TABLE options (
	id int not null auto_increment,
	title VARCHAR(200) not null,
	question_id int not null,
	istrue boolean not null,
	primary key (id),
	CONSTRAINT FK_options_questions FOREIGN KEY (question_id) REFERENCES questions (id)
);

CREATE TABLE results (
	id int not null auto_increment,
	user_id int not null,
	test_id int not null,
	result int not null,
	primary key (id),
	CONSTRAINT FK_oresults_users FOREIGN KEY (user_id) REFERENCES users (id),
	CONSTRAINT FK_oresults_tests FOREIGN KEY (test_id) REFERENCES tests (id)
);

INSERT INTO roles (name) VALUE ('SimplyUser'); 
INSERT INTO roles (name) VALUE ('Admin'); 

INSERT INTO positions (name) VALUE ('Tester'); 
INSERT INTO positions (name) VALUE ('BA');
INSERT INTO positions (name) VALUE ('JavaDev');
INSERT INTO positions (name) VALUE ('WebDev');

INSERT INTO levels (name) VALUE ('Junior');
INSERT INTO levels (name) VALUE ('Middle');
INSERT INTO levels (name) VALUE ('Senior');

INSERT INTO users (login, password, name, role_id, position_id, level_id) 
	VALUE ('admin', 'qwerty', 'Aleksandra', 2, 1, 1);

INSERT INTO tests (title, position_id, level_id) VALUE ('Junior Tester', 1, 1);	#1
INSERT INTO tests (title, position_id, level_id) VALUE ('Middle Tester', 1, 2);	#2
INSERT INTO tests (title, position_id, level_id) VALUE ('Senior Tester', 1, 3); #3
INSERT INTO tests (title, position_id, level_id) VALUE ('Junior BA', 2, 1);		#4
INSERT INTO tests (title, position_id, level_id) VALUE ('Middle BA', 2, 2);		#5
INSERT INTO tests (title, position_id, level_id) VALUE ('Senior BA', 2, 3);		#6
INSERT INTO tests (title, position_id, level_id) VALUE ('Junior JavaDev', 3, 1);#7
INSERT INTO tests (title, position_id, level_id) VALUE ('Middle JavaDev', 3, 2);#8
INSERT INTO tests (title, position_id, level_id) VALUE ('Senior JavaDev', 3, 3);#9
INSERT INTO tests (title, position_id, level_id) VALUE ('Junior WebDev', 4, 1);	#10
INSERT INTO tests (title, position_id, level_id) VALUE ('Middle WebDev', 4, 2); #11
INSERT INTO tests (title, position_id, level_id) VALUE ('Senior WebDev', 4, 3);	#12

INSERT INTO questions (title, test_id) VALUE ('Что такое баг?', 1);
INSERT INTO options (title, question_id, istrue) VALUE ('Ошибка в программе', 1, true);
INSERT INTO options (title, question_id, istrue) VALUE ('Ругательство', 1, false);
INSERT INTO options (title, question_id, istrue) VALUE ('Еда', 1, false);

INSERT INTO questions (title, test_id) VALUE ('Какое из тестирований существует?', 1);
INSERT INTO options (title, question_id, istrue) VALUE ('Обочное', 2, false);
INSERT INTO options (title, question_id, istrue) VALUE ('Нагрузочное', 2, true);
INSERT INTO options (title, question_id, istrue) VALUE ('Прачечное', 2, false);

INSERT INTO questions (title, test_id) VALUE ('Что нельзя тестировать?', 1);
INSERT INTO options (title, question_id, istrue) VALUE ('Документацию', 3, false);
INSERT INTO options (title, question_id, istrue) VALUE ('Требования', 3, false);
INSERT INTO options (title, question_id, istrue) VALUE ('Майских жуков', 3, true);
