INSERT INTO AJAXBOARD(id, writer, subject, content) VALUES (0, 'Magneto', 'Hello World', 'It''s better world!');

SELECT * FROM AJAXBoard;

hello.NewWriting 'Abysslover', 'Hello Magneto', 'This is the greeting message.';
go
hello.NewWriting 'Professor-X', 'Charles Xavier', 'Founder of the X-men.';
go
hello.NewWriting 'Cyclops', 'Scott Summers', 'First of the team''s founding recruits and longtime field leader.';
go
hello.NewWriting 'Iceman', 'Robert Drake', 'The second and youngest of the team''s founding member.';
go
hello.NewWriting 'Archangel', 'Warren Worthington III', 'Xavier''s third recruit into the team''s original roster.';
go
hello.NewWriting 'Beast', 'Dr. Henry McCoy', 'Xavier''s fourth recruit into the team''s original lineup.';
go
hello.NewWriting 'Phoenix', 'Jean Grey-Summers', 'Fifth and final recruit into the team'' founding roster.';
go
hello.NewWriting 'Mimic', 'Calvin Rankin', 'Blackmailed his way into membership.';
go
hello.NewWriting 'Changeling', 'Kevin Sidney', 'Former villain who impersonated Professor X.';
go
hello.NewWriting 'Polaris', 'Lorna Dane', 'Havok''s on/off girlfriend.';
go
hello.NewWriting 'Havok', 'Alexander Summers', 'Brother of Cyclops.';
go
hello.NewWriting 'Vulcan', 'Gabriel Summers', 'Originally a student of Moira MacTaggert.';
go
hello.NewWriting 'Petra', 'Petra Kristensen', 'Originally a student of Moira MacTaggert.';
go
hello.NewWriting 'Darwin', 'Armando Munoz', 'Originally a student of Moira MacTaggert.';
go
hello.NewWriting 'Sway', 'Suzanne Chan', 'Originally a student of Moira MacTaggert.';
go
hello.NewWriting 'Nightcrawler', 'Kurt Wagner', 'Currently member of the Uncanny X-men; son of Mystique.';
go
hello.NewWriting 'Wolverine', 'James Howlett', 'Nicknamed and best known as Logan.';
go
hello.NewWriting 'Banshee', 'Sean Cassidy', 'Former co-leader of the young mutant team Generation X';
go
hello.NewWriting 'Storm', 'Ororo Munroe', 'Second field-leader and longtime leader of the team.';
go
hello.NewWriting 'Colossus', 'Piotr Rasputin', 'Supposedly died in finding a cure for Legacy Virus.';
go

hello.GetWriting 3, 10, 10;
hello.GetWriting 1, 10, 10, 'writer LIKE ''%Storm%''';
hello.UpdateWriting 19, 'This is Test Subject', 'This is Test Content';
hello.GetContent 20;
hello.DeleteWriting 19;
hello.GetContent 19;
hello.GetCountWithPredicate;
hello.GetCountWithPredicate ' writer LIKE ''%Storm''';