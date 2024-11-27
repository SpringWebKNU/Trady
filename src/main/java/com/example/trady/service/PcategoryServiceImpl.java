package com.example.trady.service;

import com.example.trady.entity.Pcategory;
import com.example.trady.repository.PcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PcategoryServiceImpl implements PcategoryService {

    private final PcategoryRepository pcategoryRepository;

    @Autowired
    public PcategoryServiceImpl(PcategoryRepository pcategoryRepository) {
        this.pcategoryRepository = pcategoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Pcategory findById(Long id) {
        Optional<Pcategory> pcategory = pcategoryRepository.findById(id);
        return pcategory.orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pcategory> findAll() {
        return pcategoryRepository.findAll();
    }

    @Override
    @Transactional
    public Pcategory save(Pcategory pcategory) {
        return pcategoryRepository.save(pcategory);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pcategoryRepository.deleteById(id);
    }
}