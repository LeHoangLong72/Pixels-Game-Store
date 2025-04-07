create database PixelGameStore
go

use PixelGameStore
go

create table tblUsers(
	userID varchar(50) PRIMARY KEY,
	fullName nvarchar(100) NOT NULL,
	roleID char(2) NOT NULL,
	password varchar(50) NOT NULL
);
go

insert into tblUsers (userID, fullName, roleID, password) VALUES
('admin01', N'Quản Trị Viên', 'AD', 'admin123'),
('user001', N'Nguyễn Văn Anh', 'US', 'user123'),
('user002', N'Trần Thị Bình', 'US', 'user456')
go

