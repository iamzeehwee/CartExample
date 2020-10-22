package com.cartexample.app.batch;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.cartexample.app.entity.CartItem;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	Logger logger = LogManager.getLogger(BatchConfiguration.class);

	@Value("${BatchFilePath}")
    private String batchFilePath;
	
	@Value("${BatchFilePrefix}")
    private String batchFilePrefix;
	
	@Value("${BatchFileExtension}")
    private String batchFileExtension;
	
	public File[] allFiles() {
		File dir = new File(batchFilePath);
		File[] foundFiles = dir.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.startsWith(batchFilePrefix) && name.endsWith(batchFileExtension);
		    }
		});
		logger.info("Found Files" + foundFiles);
		return foundFiles;
	}
	
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
					ItemReader<CartItem> itemReader, ItemWriter<CartItem> cartItemItemWriter) {
		
		Step step = stepBuilderFactory.get("LoadCartItemStep")
									.<CartItem, CartItem>chunk(2)
									.reader(itemReader)
									.writer(cartItemItemWriter)
									.build();
		
		Job job = jobBuilderFactory.get("LoadCartItemJob")
						.incrementer(new RunIdIncrementer())
						.start(step)
						.build();
		return job;
	}

	@Bean
	public FlatFileItemReader<CartItem> itemReader() {
	  
		File[] files = allFiles();
		logger.info("ItemReader accessing file: " + files[files.length - 1].getName());
		logger.info("ItemReader accessing file in: " + files[files.length - 1].getAbsolutePath());

		FlatFileItemReader<CartItem> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource(files[files.length - 1].getAbsolutePath()));
		
		//flatFileItemReader.setResource(new FileSystemResource("src/main/resources/CartItemData.txt"));
		flatFileItemReader.setName("TXT-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	@Bean
	public LineMapper<CartItem> lineMapper() {
		DefaultLineMapper<CartItem> defaultLineMapper = new DefaultLineMapper<>();
		
		// Split each line into separate values using delimiter character
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"quantity", "prodId"});
        
        CartItemFieldSetMapper cartItemFieldSetMapper = new CartItemFieldSetMapper();        

        // Maps the extracted data to the object fields
        BeanWrapperFieldSetMapper<CartItem> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CartItem.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(cartItemFieldSetMapper);

        return defaultLineMapper;
	} 
}
