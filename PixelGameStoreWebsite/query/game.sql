create table tblGames(
	gameID char(10) PRIMARY KEY,
	gameName nvarchar(200) NOT NULL,
	developer nvarchar(100) NOT NULL,
	genre nvarchar(200) NOT NULL,
	price DECIMAL(10,2)
);

insert into tblGames(gameID, gameName, developer, genre, price) VALUES
('G0001', 'MO: Astray','Archpray Inc.' , 'Puzzle Platformer', 14.99),
('G0002', 'Dead Cells', 'Motion Twin', 'Side-scrolling', 24.99),
('G0003', 'Terraria', 'Re-Logic', 'Sandbox', 9.99),
('G0004', 'Undertale', 'Toby Fox', 'RPG', 9.99),
('G0005', 'Stardew Valley', 'The Secret Police Limited', 'Indie', 14.99),
('G0006', 'Celeste', 'Maddy Makes Games', 'Indie', 19.99),
('G0007', 'Blasphemous', 'The Game Kitchen','Indie', 24.99),
('G0008', 'Yume Nikki', 'Kikiyama', 'Adventure', 19.99)


alter table tblGames add status bit;

ALTER TABLE dbo.tblGames
ADD [image] [text] Null; 

 




