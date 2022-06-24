package com.ewm.diary.services;

import com.ewm.diary.entites.Record;
import com.ewm.diary.repositories.RecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    public final RecordsRepository recordsRepository;

    public void save(Record record){
        recordsRepository.save(record);
    }

    public List<Record> findAll() { return recordsRepository.findAll(); }

    public void delete(Long id) {
        recordsRepository.deleteById(id);
    }
}
