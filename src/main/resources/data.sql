INSERT INTO member(username, password, email, phonenumber, addr)
VALUES
    ('dododo','123','123@naver.com', '010-1234-5678', 'gangnam'),
    ('fofofo','456','456@naver.com', '010-9876-5432', 'yongin'),
    ('gogogo','789','789@naver.com', '010-1928-3746', 'suwon'),
    ('hohoho','012','012@naver.com', '010-5473-3245', 'seoul');

<<<<<<< HEAD
INSERT INTO member(username, password, email, phonenumber, addr)
VALUES
    ('dododo','123','123@naver.com', '010-1234-5678', 'gangnam'),
    ('fofofo','456','456@naver.com', '010-9876-5432', 'yongin'),
    ('gogogo','789','789@naver.com', '010-1928-3746', 'suwon'),
    ('hohoho','012','012@naver.com', '010-5473-3245', 'seoul');
=======


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


>>>>>>> 48c929469e95915e5268c42fb5166d55f4e435f6
