CREATE DATABASE PixelGameStore
GO

USE PixelGameStore
GO

CREATE TABLE tblUsers(
	userID VARCHAR(50) PRIMARY KEY,
	fullName NVARCHAR(100) NOT NULL,
	roleID CHAR(2) NOT NULL,
	[password] VARCHAR(50) NOT NULL
);
GO

INSERT INTO tblUsers (userID, fullName, roleID, [password]) VALUES
('admin01', N'Quản Trị Viên', 'AD', 'admin123'),
('user001', N'Nguyễn Văn Anh', 'US', 'user123'),
('user002', N'Trần Thị Bình', 'US', 'user456')
GO


ALTER TABLE tblUsers ALTER COLUMN [password] VARCHAR(250);
