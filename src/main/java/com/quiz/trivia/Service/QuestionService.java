package com.quiz.trivia.Service;

import com.quiz.trivia.Model.Question;
import com.quiz.trivia.Payload.OpenDtbResponse;
import com.quiz.trivia.Payload.QuestionDownloadResponse;
import com.quiz.trivia.Payload.QuestionResponse;
import com.quiz.trivia.Payload.QuizResponse;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    public OpenDtbResponse getQuestions();
    public Set<Question> downloadQuestions();
    public QuizResponse getQuizQuestions(int numberOfQuestion,int quizNumber,String category,String difficulty);
}
