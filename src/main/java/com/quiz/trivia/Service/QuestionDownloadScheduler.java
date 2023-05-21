package com.quiz.trivia.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class QuestionDownloadScheduler {

    private final QuestionService questionService;

    public QuestionDownloadScheduler(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Scheduled(fixedDelay = 300000000)
    public void scheduleDownloadAndSaveQuestions()
    {
        questionService.downloadQuestions();
        System.out.println("download");
    }
}
