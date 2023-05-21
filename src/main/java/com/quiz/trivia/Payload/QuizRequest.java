package com.quiz.trivia.Payload;

import lombok.Data;

@Data
public class QuizRequest {

    private int quizNumber;
    private int numberOfQuestions;
    private String category;
    private String difficulty;
}
