-- USERS
INSERT INTO users (id, name, email, phone) VALUES (1, 'Joel', 'joel@hotmail.com', '123456');
INSERT INTO users (id, name, email, phone) VALUES (2, 'Laura', 'laura@hotmail.com', '234567');

-- SUBSCRIPTIONS
INSERT INTO user_subscriptions (user_id, subscribed) VALUES (1, 'SPORTS');
INSERT INTO user_subscriptions (user_id, subscribed) VALUES (1, 'MOVIES');
INSERT INTO user_subscriptions (user_id, subscribed) VALUES (2, 'FINANCE');

-- CHANNELS
INSERT INTO user_channels (user_id, channels) VALUES (1, 'EMAIL');
INSERT INTO user_channels (user_id, channels) VALUES (1, 'SMS');
INSERT INTO user_channels (user_id, channels) VALUES (2, 'PUSH');