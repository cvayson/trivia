package com.quiz.trivia.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponse {

    private List<QuestionResponse> questions;
    private int numberOfQuestions;
    private int questionNumber;


}
