-- CATEGORIES
INSERT INTO
    categories (id, name, parent_id)
VALUES (1, 'Emlak', NULL),
    (2, 'Vasıta', NULL),
    (
        3, 'Yedek Parça, Aksesuar, Donanım & Tuning', NULL
    ),
    (
        4, 'İkinci El ve Sıfır Alışveriş', NULL
    ),
    (
        5, 'İş Makineleri & Sanayi', NULL
    ),
    (
        6, 'Ustalar ve Hizmetler', NULL
    ),
    (7, 'Özel Ders Verenler', NULL),
    (8, 'İş İlanları', NULL),
    (9, 'Yardımcı Arayanlar', NULL),
    (10, 'Konut', 1),
    (11, 'İş Yeri', 1),
    (12, 'Arsa', 1),
    (13, 'Konut Projeleri', 1),
    (14, 'Bina', 1),
    (15, 'Devre Mülk', 1),
    (16, 'Turistik Tesis', 1),
    (17, 'Otomobil', 2),
    (18, 'Arazi, SUV & Pickup', 2),
    (19, 'Motosiklet', 2),
    (20, 'Minivan & Panelvan', 2),
    (21, 'Ticari Araçlar', 2),
    (22, 'Elektrikli Araçlar', 2),
    (23, 'Kiralık Araçlar', 2),
    (24, 'Deniz Araçları', 2),
    (25, 'Hasarlı Araçlar', 2),
    (26, 'Karavan', 2),
    (27, 'Klasik Araçlar', 2),
    (28, 'Hava Araçları', 2),
    (29, 'ATV', 2),
    (30, 'UTV', 2),
    (
        31, 'Engelli Plakalı Araçlar', 2
    ),
    (32, 'Otomotiv Ekipmanları', 3),
    (
        33, 'Motosiklet Ekipmanları', 3
    ),
    (
        34, 'Deniz Aracı Ekipmanları', 3
    ),
    (35, 'Bilgisayar', 4),
    (36, 'Cep Telefonu', 4),
    (37, 'Fotoğraf & Kamera', 4),
    (38, 'Ev Dekorasyon', 4),
    (39, 'Ev Elektroniği', 4),
    (
        40, 'Elektrikli Ev Aletleri', 4
    ),
    (41, 'Giyim & Aksesuar', 4),
    (42, 'Saat', 4),
    (43, 'Anne & Bebek', 4),
    (
        44, 'Kişisel Bakım & Kozmetik', 4
    ),
    (45, 'Hobi & Oyuncak', 4),
    (46, 'Oyun & Konsol', 4),
    (47, 'Kitap, Dergi & Film', 4),
    (48, 'Müzik', 4),
    (49, 'Spor', 4),
    (
        50, 'Takı, Mücevher & Altın', 4
    ),
    (51, 'Koleksiyon', 4),
    (52, 'Antika', 4),
    (53, 'Bahçe & Yapı Market', 4),
    (54, 'Teknik Elektronik', 4),
    (55, 'Ofis & Kırtasiye', 4),
    (56, 'Yiyecek & İçecek', 4),
    (57, 'Diğer Her Şey', 4),
    (58, 'İş Makineleri', 5),
    (59, 'Tarım Makineleri', 5),
    (60, 'Sanayi', 5),
    (61, 'Elektrik & Enerji', 5),
    (
        62, 'Ev Tadilat & Dekorasyon', 6
    ),
    (63, 'Nakliyat', 6),
    (64, 'Araç Servis & Bakım', 6),
    (
        65, 'Tamirat & Teknik Servis', 6
    ),
    (66, 'Düğün & Etkinlik', 6),
    (67, 'Diğer', 6),
    (68, 'Lise & Üniversite', 7),
    (69, 'İlkokul & Ortaokul', 7),
    (70, 'Yabancı Dil', 7),
    (71, 'Bilgisayar', 7),
    (72, 'Direksiyon', 7),
    (73, 'Spor', 7),
    (74, 'Sanat', 7),
    (75, 'Dans', 7),
    (76, 'Müzik & Enstrüman', 7),
    (77, 'Tiyatro & Oyunculuk', 7),
    (78, 'Kişisel Gelişim', 7),
    (79, 'Mesleki Dersler', 7),
    (80, 'Özel Eğitim', 7),
    (81, 'Çocuk Gelişimi', 7),
    (
        82, 'Güzel Konuşma & Diksiyon', 7
    ),
    (83, 'Fotoğrafçılık', 7),
    (
        84, 'Avukatlık & Hukuki Danışmanlık', 8
    ),
    (85, 'Eğitim', 8),
    (86, 'Eğlence & Aktivite', 8),
    (87, 'Güzellik & Bakım', 8),
    (
        88, 'IT & Yazılım Geliştirme', 8
    ),
    (89, 'İnsan Kaynakları', 8),
    (90, 'İnşaat & Yapı', 8),
    (
        91, 'İşletme & Stratejik Yönetim', 8
    ),
    (92, 'Koruma & Güvenlik', 8),
    (93, 'Lojistik & Taşıma', 8),
    (
        94, 'Mağazacılık ve Perakendecilik', 8
    ),
    (
        95, 'Muhasebe, Finans & Bankacılık', 8
    ),
    (96, 'Mühendislik', 8),
    (97, 'Müşteri Hizmetleri', 8),
    (
        98, 'Ofis Yönetimi & İdari İşler', 8
    ),
    (
        99, 'Part Time & Ek İş Fırsatları', 8
    ),
    (
        100, 'Pazarlama & Ürün Yönetimi', 8
    ),
    (
        101, 'Radyo, Sinema & Televizyon', 8
    ),
    (
        102, 'Restoran & Konaklama', 8
    ),
    (103, 'Sağlık', 8),
    (104, 'Satış', 8),
    (105, 'Tamir & Bakım', 8),
    (106, 'Tarım & Hayvancılık', 8),
    (
        107, 'Tasarım & Yaratıcılık', 8
    ),
    (
        108, 'Tekstil & Konfeksiyon', 8
    ),
    (109, 'Üretim & İmalat', 8),
    (
        110, 'Bebek & Çocuk Bakıcısı', 9
    ),
    (
        111, 'Yaşlı & Hasta Bakıcısı', 9
    ),
    (
        112, 'Temizlikçi & Ev İşlerine Yardımcı', 9
    );

-- Kullanıcıları ekle
INSERT INTO
    users (
        id, username, email, password, first_name, last_name, phone, is_verified, created_at, updated_at, account_non_expired, is_enabled, account_non_locked, credentials_non_expired
    )
VALUES (
        '1a46a531-bc88-4f51-a1bb-226ef6beae8e', 'admin_user', 'admin@example.com', '$2a$12$EzorHZDaVB3mq/qgkCgNz.xz0tg.40LmFySO6gxIcuVd/6Wi/Vs8y', 'Admin', 'User', '1234567890', true, CURRENT_DATE, CURRENT_DATE, true, true, true, true
    ),
    (
        '6a7c6e39-3638-4992-b1b8-8374f4eb8b47', 'regular_user', 'user@example.com', '$2a$12$EzorHZDaVB3mq/qgkCgNz.xz0tg.40LmFySO6gxIcuVd/6Wi/Vs8y', 'Regular', 'User', '0987654321', true, CURRENT_DATE, CURRENT_DATE, true, true, true, true
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