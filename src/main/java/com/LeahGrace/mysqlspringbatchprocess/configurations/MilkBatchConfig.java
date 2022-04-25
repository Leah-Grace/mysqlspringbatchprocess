package com.LeahGrace.mysqlspringbatchprocess.configurations;

import com.LeahGrace.mysqlspringbatchprocess.configurations.processors.JobCompletionListener;
import com.LeahGrace.mysqlspringbatchprocess.configurations.processors.MilkRecordDTOFieldSetMapper;
import com.LeahGrace.mysqlspringbatchprocess.configurations.processors.MilkRecordProcessor;
import com.LeahGrace.mysqlspringbatchprocess.dataTransferObjects.MilkRecordDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class MilkBatchConfig {

    private String[] format = new String[]{"date","price"};

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public MilkRecordDTOFieldSetMapper milkRecordDTOFieldSetMapper;

    @Bean
    public Job processJob(Step step){
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step).end().build();

    }

    @Bean
    public Step orderStep1(JdbcBatchItemWriter<MilkRecordDTO> writer){
        return stepBuilderFactory.get("orderStep1").<MilkRecordDTO, MilkRecordDTO> chunk(10)
                .reader(flatFileItemReader())
                .processor(milkRecordProcessor())
                .writer(writer).build();
    }

    @Bean
    public FlatFileItemReader flatFileItemReader(){
        return new FlatFileItemReaderBuilder()
                .name("flatFileItemReader")
                .resource(new ClassPathResource("/input/historicalMilkData.csv"))
                .delimited()
                .names(format)
                .linesToSkip(1)
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper(){{
                    setTargetType(MilkRecordDTO.class);
        }})
                .build();
    }

    @Bean
    public LineMapper<MilkRecordDTO> lineMapper(){
        final DefaultLineMapper defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames(format);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(milkRecordDTOFieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public MilkRecordProcessor milkRecordProcessor(){
        return new MilkRecordProcessor();
    }

    private JobExecutionListener listener() {
        return new JobCompletionListener();
    }

    @Bean
    public JdbcBatchItemWriter writer(final DataSource dataSource){
        return new JdbcBatchItemWriterBuilder()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO milkRecord(date, price) VALUES(:date, :price)")     // TODO: align variable names
                .dataSource(dataSource)
                .build();

    }



}
