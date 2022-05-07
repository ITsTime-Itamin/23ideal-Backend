package com.example.itaminbackend.global.batch.tasklet;

import com.example.itaminbackend.domain.rentalhouse.service.PublicRentalHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteDataTasklet implements Tasklet {

    private final PublicRentalHouseService publicRentalHouseService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        this.publicRentalHouseService.deleteAll();
        return RepeatStatus.FINISHED;
    }
}
