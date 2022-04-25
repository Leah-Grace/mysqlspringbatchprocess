package com.LeahGrace.mysqlspringbatchprocess.repositories;

import com.LeahGrace.mysqlspringbatchprocess.models.MilkRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilkRepository extends JpaRepository<MilkRecord, Integer> {
}

