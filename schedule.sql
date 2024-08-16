create table if not exists manager
(
	ManagerID int auto_increment primary key comment '관리자 ID',
	Email varchar(100) comment '관리자 이메일',
	RegistrationDate DATETIME  comment '등록 날짜',
	ModificationDate DATETIME  comment '수정 날짜'
);

create table if not exists schedule
(
	ScheduleID int AUTO_INCREMENT primary key comment '스케줄 ID',
	ToDo varchar(200) comment '할 일',
	Password varchar(100) comment '비밀번호',
	RegistrationDate DATETIME  comment '등록 날짜',
	ModificationDate DATETIME  comment '수정 날짜',
	ManagerID INT COMMENT '관리자 ID',
	foreign key(ManagerID) references manager(ManagerID)
);
