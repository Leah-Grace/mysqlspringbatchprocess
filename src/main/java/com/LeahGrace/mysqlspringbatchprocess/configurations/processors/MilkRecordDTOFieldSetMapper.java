package com.LeahGrace.mysqlspringbatchprocess.configurations.processors;

import com.LeahGrace.mysqlspringbatchprocess.dataTransferObjects.MilkRecordDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class MilkRecordDTOFieldSetMapper implements FieldSetMapper {
    @Override
    public MilkRecordDTO mapFieldSet(FieldSet fieldSet) throws BindException {
        String date = fieldSet.readRawString("date");
        double price = fieldSet.readDouble("price");
        return new MilkRecordDTO(date, price);
    }
}