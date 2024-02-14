-- CATEGORIES
INSERT INTO
    categories (id, name)
VALUES (1, 'Electronics'),
    (2, 'Fashion'),
    (3, 'Home'),
    (4, 'Sports'),
    (5, 'Books'),
    (6, 'Beauty'),
    (7, 'Toys'),
    (8, 'Food'),
    (9, 'Health'),
    (10, 'Furniture'),
    (11, 'Automotive'),
    (12, 'Jewelry'),
    (13, 'Music'),
    (14, 'Movies'),
    (15, 'Pet Supplies'),
    (16, 'Art'),
    (17, 'Travel'),
    (18, 'Tech'),
    (19, 'Fitness'),
    (20, 'Garden'),
    (21, 'Clothing'),
    (22, 'Shoes'),
    (23, 'Crafts'),
    (24, 'Electrical'),
    (25, 'Appliances'),
    (26, 'Beverages'),
    (27, 'Personal Care'),
    (28, 'DIY'),
    (29, 'Stationery'),
    (30, 'Wedding'),
    (31, 'Hobbies'),
    (32, 'Electronic Accessories'),
    (33, 'Outdoor'),
    (34, 'Business'),
    (35, 'Office Supplies'),
    (36, 'Education'),
    (37, 'Baby'),
    (38, 'Party'),
    (39, 'Sports Equipment'),
    (40, 'Animals'),
    (41, 'Photography'),
    (42, 'Gaming'),
    (43, 'Collectibles'),
    (44, 'Instruments'),
    (45, 'Religion'),
    (46, 'College'),
    (47, 'Memorabilia'),
    (48, 'Vintage');

-- Kullanıcıları ekle
INSERT INTO
    users (
        id, username, email, password, first_name, last_name, phone, account_type, is_verified, created_at, updated_at, account_non_expired, is_enabled, account_non_locked, credentials_non_expired
    )
VALUES (
        '1a46a531-bc88-4f51-a1bb-226ef6beae8e', 'admin_user', 'admin@example.com', '$2a$12$EzorHZDaVB3mq/qgkCgNz.xz0tg.40LmFySO6gxIcuVd/6Wi/Vs8y', 'Admin', 'User', '1234567890', 'admin', true, CURRENT_DATE, CURRENT_DATE, true, true, true, true
    ),
    (
        '6a7c6e39-3638-4992-b1b8-8374f4eb8b47', 'regular_user', 'user@example.com', '$2a$12$EzorHZDaVB3mq/qgkCgNz.xz0tg.40LmFySO6gxIcuVd/6Wi/Vs8y', 'Regular', 'User', '0987654321', 'user', true, CURRENT_DATE, CURRENT_DATE, true, true, true, true
    );

-- Rolleri ekle
INSERT INTO
    authorities (user_id, role)
VALUES (
        '1a46a531-bc88-4f51-a1bb-226ef6beae8e', 'ROLE_ADMIN'
    ),
    (
        '6a7c6e39-3638-4992-b1b8-8374f4eb8b47', 'ROLE_USER'
    );