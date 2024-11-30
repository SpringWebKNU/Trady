INSERT INTO member(username, password, email, phonenumber, addr)
VALUES
    ('admin','admin','admin@admin.com', '010-0000-0000', 'admin'),
    ('dododo','123','123@naver.com', '010-1234-5678', '서울특별시 종로구 사직로 161'),
    ('fofofo','456','456@naver.com', '010-9876-5432', '서울 용산구 남산공원길 105'),
    ('gogogo','789','789@naver.com', '010-1928-3746', '전북 전주시 완산구 기린대로 99'),
    ('hohoho','012','012@naver.com', '010-5473-3245', '부산 해운대구 우동');


INSERT INTO pcategory (id, pname, is_first, is_selected)
VALUES
    (1, '나이키', TRUE, TRUE),
    (2, '아디다스', FALSE, FALSE),
    (3, '아식스', FALSE, FALSE),
    (4, '뉴발란스', FALSE, FALSE),
    (5, '기타', FALSE, FALSE);

-- Product 테이블에 데이터 삽입 (가격 제외) - 상품명 수정
INSERT INTO product (pname, pimg, pcategory_id, pdate)
VALUES
    ('Converse Chuck Taylor All Star Low Top', 'shoes1.jpg', 5, '2023-01-01'),
    ('Asics Gel-Kayano 28', 'shoes2.jpg', 3, '2023-02-01'),
    ('New Balance 990v5', 'shoes3.jpg', 4, '2023-03-01'),
    ('UGG Classic Short II', 'shoes4.jpg', 5, '2023-04-01'),
    ('Adidas Ultraboost 22', 'shoes5.jpg', 2, '2023-05-01'),
    ('Adidas NMD_R1', 'adidas1.jpg', 2, '2023-05-01'),
    ('Adidas Stan Smith', 'adidas2.jpg', 2, '2023-05-01'),
    ('Adidas Superstar', 'adidas3.jpg', 2, '2023-05-01'),
    ('Adidas Gazelle', 'adidas4.jpg', 2, '2023-05-01'),
    ('Adidas ZX 2K Boost', 'adidas5.jpg', 2, '2023-05-01'),
    ('Asics Gel-Nimbus 23', 'asic1.jpg', 3, '2023-05-01'),
    ('Asics Gel-Kayano 27', 'asic2.jpg', 3, '2023-05-01'),
    ('Asics GT-2000 10', 'asic3.jpg', 3, '2023-05-01'),
    ('Asics Gel-Cumulus 23', 'asic4.jpg', 3, '2023-05-01'),
    ('Asics Gel-Pulse 13', 'asic5.jpg', 3, '2023-05-01'),
    ('Converse Chuck Taylor All Star High Top', 'Etc1.jpg', 5, '2023-05-01'),
    ('Skechers GOwalk 5', 'Etc2.jpg', 5, '2023-05-01'),
    ('Puma Suede Classic', 'Etc3.jpg', 5, '2023-05-01'),
    ('MLB Classic Leather', 'Etc4.jpg', 5, '2023-05-01'),
    ('Fila Disruptor II', 'Etc5.jpg', 5, '2023-05-01'),
    ('New Balance 574', 'Newbal1.jpg', 4, '2023-05-01'),
    ('New Balance 997H', 'Newbal2.jpg', 4, '2023-05-01'),
    ('New Balance 1080v11', 'Newbal3.jpg', 4, '2023-05-01'),
    ('New Balance 990v4', 'Newbal4.jpg', 4, '2023-05-01'),
    ('New Balance Fresh Foam 880v10', 'Newbal5.jpg', 4, '2023-05-01'),
    ('Nike Air Max 90', 'nike1.jpg', 1, '2023-05-01'),
    ('Nike Air Force 1', 'nike2.jpg', 1, '2023-05-01'),
    ('Nike ZoomX Vaporfly Next%', 'nike3.jpg', 1, '2023-05-01'),
    ('Nike React Infinity Run Flyknit', 'nike4.jpg', 1, '2023-05-01'),
    ('Nike Air Zoom Pegasus 38', 'nike5.jpg', 1, '2023-05-01');

-- ProductOption 테이블에 가격 데이터 삽입
INSERT INTO product_option (product_id, size, price)
VALUES
    (1, '225', 150000),
    (1, '230', 160000),
    (1, '235', 170000),
    (2, '240', 200000),
    (3, '245', 180000),
    (4, '250', 210000),
    (7, '265', 185000),
    (8, '270', 230000),
    (9, '275', 240000),
    (10, '250', 200000),
    (11, '285', 270000),
    (12, '295', 290000),
    (14, '275', 250000),
    (15, '300', 350000),
    (16, '300', 360000),
    (17, '255', 220000),
    (18, '300', 370000),
    (19, '225', 150000),
    (20, '300', 380000),
    (21, '250', 200000),
    (22, '230', 160000),
    (23, '270', 230000),
    (24, '260', 210000),
    (25, '280', 260000),
    (26, '235', 170000),
    (27, '295', 300000),
    (28, '245', 200000),
    (29, '265', 220000),
    (30, '290', 310000);
