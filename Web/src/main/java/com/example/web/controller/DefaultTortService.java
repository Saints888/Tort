package com.example.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DefaultTortService implements TortService{

    private final TortRepository tortRepository;

    public DefaultTortService(TortRepository tortRepository) {
        this.tortRepository = tortRepository;
    }

    @Override
    public Tort getTortById(Long id) {
        TortEntity tortEntity = tortRepository
                .findById(id)
                .orElseThrow(() -> new TortNotFoundException("Tord not found: id= " + id));


        return new Tort(tortEntity.getId(),
                tortEntity.getAuthor(),
                tortEntity.getTitle(),
                tortEntity.getPrice());


    }

    @Override
    public List<Tort> getAllTorts() {
        Iterable <TortEntity> iterable = tortRepository.findAll();


        ArrayList<Tort> torts = new ArrayList<>();
        for (TortEntity tortEntity : iterable) {
            torts.add(new Tort(tortEntity.getId(),
                    tortEntity.getAuthor(),
                    tortEntity.getTitle(),
                    tortEntity.getPrice()));
        }
        return torts;
    }

    @Override
    public void addTort(Tort tort) {
        TortEntity tortEntity = new TortEntity(null, tort.getautrho);

    }
}
