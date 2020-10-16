package com.cartexample.app.batch;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.cartexample.app.entity.CartItem;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
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
	  
		FlatFileItemReader<CartItem> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource("src/main/resources/CartItemData.txt"));
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
