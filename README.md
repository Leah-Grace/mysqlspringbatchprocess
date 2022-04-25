Batch Processing Application supported by Spring Batch

The application batch processes data from a .csv file using a POJO based approach to 
transform batch-processed data 
into a data transfer object/domain object
and (write to an ) sql database.

MilkRecordDTO - implements Serializable interface, supported by JPA repository to process data imported from .csv to a Data Transfer Object compatible with MySQL database.

MilkBatchConfig - Defines job/steps by providing batch configuration for job to upload .csv file and process into MySQL database. Uses a base configuration @BatchProcessing provided by Spring Batch to set up batch jobs in a @Configuration class.
    Beans:
        FlatFileItemReader - reads file
        resource - indicates location of .csv file
        names - shows order of fields in file
        lineMapper - interface to map lines from file to domain object
        fieldSetMapper - will map data from fieldset to object
        lineMapper - requires tokenizer and fieldsetmapper
        milkRecordDTOFieldSetMapper - an autowired bean.