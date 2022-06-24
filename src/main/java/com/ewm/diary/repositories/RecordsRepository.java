package com.ewm.diary.repositories;

import com.ewm.diary.entites.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordsRepository extends JpaRepository<Record, Long> {
}
