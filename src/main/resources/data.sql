INSERT INTO member(username, password, email)
VALUES
    ('dododo','123','123@naver.com'),
    ('fofofo','456','456@naver.com'),
    ('gogogo','789','789@naver.com'),
    ('hohoho','012','012@naver.com');


-- Pcategory 테이블에 데이터 삽입
INSERT INTO pcategory (id, pname, is_first) VALUES
                                                (1, 'Category1', TRUE),
                                                (2, 'Category2', FALSE),
                                                (3, 'Category3', FALSE);

-- Product 테이블에 데이터 삽입 (categoryId 외래 키 참조)
INSERT INTO product (pname, pprice, pimg, pcategory_id)
VALUES
    ('Product1', '100000', 'shoes1.jpg', 1),
    ('Product2', '200000', 'shoes2.jpg', 2),
    ('Product3', '300000', 'shoes3.jpg', 3),
    ('Product4', '400000', 'shoes4.jpg', 1),
    ('Product5', '500000', 'shoes5.jpg', 2);


