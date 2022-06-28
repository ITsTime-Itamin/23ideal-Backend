package com.example.itaminbackend.global.batch.job;

import com.example.itaminbackend.global.batch.chunk.OpenApiItemProcessor;
import com.example.itaminbackend.global.batch.chunk.OpenApiItemReader;
import com.example.itaminbackend.global.batch.chunk.OpenApiItemWriter;
import com.example.itaminbackend.domain.rentalhouse.entity.PublicRentalHouse;
import com.example.itaminbackend.global.batch.dto.PublicRentalHouseResponse;
import com.example.itaminbackend.global.batch.tasklet.DeleteDataTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class OpenApiJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DeleteDataTasklet deleteDataTasklet;

    @Bean
    public Job openApiJob() throws Exception {
//        return jobBuilderFactory.get("openApiJob")
//                .start(deleteDataStep())
//                .next(openApiStep())
//                .build();
        return null;
    }

    @Bean
    public Step deleteDataStep() {
        return stepBuilderFactory.get("deleteDataStep")
                .tasklet(deleteDataTasklet)
                .build();
    }

    @Bean
    public Step openApiStep() throws Exception {
        return stepBuilderFactory.get("openApiStep")
                .<List<PublicRentalHouseResponse>, List<PublicRentalHouse>>chunk(1)
                .reader(openApiItemReader())
                .processor(openApiItemProcessor())
                .writer(openApiItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<List<PublicRentalHouseResponse>> openApiItemReader() throws Exception {
        return new OpenApiItemReader();
    }

    @Bean
    public ItemProcessor<List<PublicRentalHouseResponse>, List<PublicRentalHouse>> openApiItemProcessor(){
        return new OpenApiItemProcessor();
    }

    @Bean
    public ItemWriter<List<PublicRentalHouse>> openApiItemWriter() {
        return new OpenApiItemWriter();
    }
}
