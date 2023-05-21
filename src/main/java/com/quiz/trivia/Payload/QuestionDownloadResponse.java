package com.quiz.trivia.Payload;

import lombok.Data;

@Data
public class QuestionDownloadResponse {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;
}
