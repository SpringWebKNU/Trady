INSERT INTO member(username, password, email, phonenumber, addr)
VALUES
    ('admin','admin','admin@admin.com', '010-0000-0000', 'admin'),
    ('dododo','123','123@naver.com', '010-1234-5678', '서울특별시 종로구 사직로 161'),
    ('fofofo','456','456@naver.com', '010-9876-5432', '서울 용산구 남산공원길 105'),
    ('gogogo','789','789@naver.com', '010-1928-3746', '전북 전주시 완산구 기린대로 99'),
    ('hohoho','012','012@naver.com', '010-5473-3245', '부산 해운대구 우동');



-- Pcategory 테이블에 데이터 삽입
INSERT INTO pcategory (id, pname, is_first) VALUES
                                                (1, '나이키', TRUE),
                                                (2, '아디다스', FALSE),
                                                (3, '아식스', FALSE),
                                                (4, '뉴발', FALSE),
                                                (5, '기타', FALSE);

-- Product 테이블에 데이터 삽입 (categoryId 외래 키 참조)
INSERT INTO product (pname, pprice, pimg, pcategory_id, pdate)
VALUES
    ('Product1', '100000', 'shoes1.jpg', 5, '2023-01-01'),
    ('Product2', '200000', 'shoes2.jpg', 3, '2023-02-01'),
    ('Product3', '300000', 'shoes3.jpg', 4, '2023-03-01'),
    ('Product4', '400000', 'shoes4.jpg', 5, '2023-04-01'),
    ('Product5', '500000', 'shoes5.jpg', 2, '2023-05-01'),

    ('Adidas1', '500000', 'adidas1.jpg', 2, '2023-05-01'),
    ('Adidas2', '500000', 'adidas2.jpg', 2, '2023-05-01'),
    ('Adidas3', '500000', 'adidas3.jpg', 2, '2023-05-01'),
    ('Adidas4', '500000', 'adidas4.jpg', 2, '2023-05-01'),
    ('Adidas5', '500000', 'adidas5.jpg', 2, '2023-05-01'),

    ('Asic1', '500000', 'asic1.jpg', 3, '2023-05-01'),
    ('Asic2', '500000', 'asic2.jpg', 3, '2023-05-01'),
    ('Asic3', '500000', 'asic3.jpg', 3, '2023-05-01'),
    ('Asic4', '500000', 'asic4.jpg', 3, '2023-05-01'),
    ('Asic5', '500000', 'asic5.jpg', 3, '2023-05-01'),

    ('Etc1', '500000', 'Etc1.jpg', 5, '2023-05-01'),
    ('Etc1', '500000', 'Etc2.jpg', 5, '2023-05-01'),
    ('Etc1', '500000', 'Etc3.jpg', 5, '2023-05-01'),
    ('Etc1', '500000', 'Etc4.jpg', 5, '2023-05-01'),
    ('Etc1', '500000', 'Etc5.jpg', 5, '2023-05-01'),

    ('Newbal1', '500000', 'Newbal1.jpg', 4, '2023-05-01'),
    ('Newbal2', '500000', 'Newbal2.jpg', 4, '2023-05-01'),
    ('Newbal3', '500000', 'Newbal3.jpg', 4, '2023-05-01'),
    ('Newbal4', '500000', 'Newbal4.jpg', 4, '2023-05-01'),
    ('Newbal5', '500000', 'Newbal5.jpg', 4, '2023-05-01'),

    ('Nike1', '500000', 'nike1.jpg', 1, '2023-05-01'),
    ('Nike2', '500000', 'nike2.jpg', 1, '2023-05-01'),
    ('Nike3', '500000', 'nike3.jpg', 1, '2023-05-01'),
    ('Nike4', '500000', 'nike4.jpg', 1, '2023-05-01'),
    ('Nike5', '500000', 'nike5.jpg', 1, '2023-05-01');



INSERT INTO product_option (product_id, size, price)
VALUES
    (1, '225', 150000),
    (1, '230', 160000),
    (1, '235', 170000),
    (2, '240', 200000);
