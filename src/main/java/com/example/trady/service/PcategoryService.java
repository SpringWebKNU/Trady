package com.example.trady.service;

import com.example.trady.entity.Pcategory;

public interface PcategoryService {

    Pcategory findById(Long pid);
    Iterable<Pcategory> findAll(); // 이 메서드를 구현해야 함
    Pcategory save(Pcategory pcategory); // 이 메서드를 구현해야 함
    void deleteById(Long pid); // 이 메서드를 구현해야 함
}