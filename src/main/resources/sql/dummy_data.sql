# dummy data for ssafit

USE ssafit;

# create dummy data for user table
INSERT INTO user (`id`, `user_id`, `user_pwd`, `name`, `email`)
VALUES 
('admin-uuid', 'admin', 'admin', 'admin', 'admin@email.com'), 
('dummydata-u1', 't3rryahn', '1234', 'Terry', 't3rryahn.dev@gmail.com'), 
('dummydata-u2', 'zzero', '1234', 'ZiYoung', 'zzero@gmail.com'), 
('dummydata-u3', 'alice02', '1234', 'Alice', 'alice@example.com'),
('dummydata-u4', 'bob03', '1234', 'Bob', 'bob@example.com');


# create dummy data for video table
INSERT INTO video (`id`, `title`, `channel_name`, `author`, `url`, `length`)
VALUES
('dummydata-v1', 'Push-up Tutorial', 'FitnessPro', 'dummydata-u1', 'https://youtu.be/pushup123', 600),
('dummydata-v2', 'Yoga for Beginners', 'YogaWorld', 'dummydata-u2', 'https://youtu.be/yoga456', 1200),
('dummydata-v3', 'HIIT Workout', 'HIITChannel', 'dummydata-u3', 'https://youtu.be/hiit789', 900);

# create dummy data for review table
INSERT INTO review (id, user_id, name, video_id, content)
VALUES
('dummydata-r1', 'dummydata-u2', 'ZiYoung', 'dummydata-v1', '좋은 영상이에요! 따라하기 쉬웠습니다.'),
('dummydata-r2', 'dummydata-u3', 'Alice', 'dummydata-v1', '운동 효과가 확실하네요.'),
('dummydata-r3', 'dummydata-u1', 'Terry', 'dummydata-v2', '요가 동작이 자세히 설명돼서 좋았습니다.'),
('dummydata-r4', 'dummydata-u2', 'ZiYoung', 'dummydata-v3', '짧지만 강도 높은 운동이에요!');



 