package com.LeahGrace.mysqlspringbatchprocess.configurations.processors;

import com.LeahGrace.mysqlspringbatchprocess.dataTransferObjects.MilkRecordDTO;
import com.LeahGrace.mysqlspringbatchprocess.repositories.MilkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;


import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

public class MilkRecordProcessor implements ItemProcessor<MilkRecordDTO, MilkRecordDTO> {

    private static final Logger logger = LoggerFactory.getLogger(MilkRecordProcessor.class);

    @Autowired
    private MilkRepository milkRepository;

    @Override
    public MilkRecordDTO process(MilkRecordDTO milkRecordDTO) throws Exception {
        return milkRecordDTO;
    }
}
