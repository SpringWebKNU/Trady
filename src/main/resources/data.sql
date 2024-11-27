INSERT INTO member(username, password, email, phonenumber, addr)
VALUES
    ('dododo','123','123@naver.com', '010-1234-5678', 'gangnam'),
    ('fofofo','456','456@naver.com', '010-9876-5432', 'yongin'),
    ('gogogo','789','789@naver.com', '010-1928-3746', 'suwon'),
    ('hohoho','012','012@naver.com', '010-5473-3245', 'seoul');



-- Pcategory 테이블에 데이터 삽입
INSERT INTO pcategory (id, pname, is_first) VALUES
                                                (1, 'Category1', TRUE),
                                                (2, 'Category2', FALSE),
                                                (3, 'Category3', FALSE);

-- Product 테이블에 데이터 삽입 (categoryId 외래 키 참조)
INSERT INTO product (pname, pprice, pimg, pcategory_id, pdate)
VALUES
    ('Product1', '100000', 'shoes1.jpg', 1, '2023-01-01'),
    ('Product2', '200000', 'shoes2.jpg', 2, '2023-02-01'),
    ('Product3', '300000', 'shoes3.jpg', 3, '2023-03-01'),
    ('Product4', '400000', 'shoes4.jpg', 1, '2023-04-01'),
    ('Product5', '500000', 'shoes5.jpg', 2, '2023-05-01');

INSERT INTO product_option (product_id, size, high_price, low_price)
VALUES
    (1, '225', 150000, 130000),
    (1, '230', 160000, 140000),
    (1, '235', 170000, 150000),
    (2, '240', 200000, 180000);
