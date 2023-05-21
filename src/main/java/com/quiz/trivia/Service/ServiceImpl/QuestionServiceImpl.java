package com.quiz.trivia.Service.ServiceImpl;

import com.quiz.trivia.Model.Question;
import com.quiz.trivia.Payload.OpenDtbResponse;
import com.quiz.trivia.Payload.QuestionDownloadResponse;
import com.quiz.trivia.Payload.QuestionResponse;
import com.quiz.trivia.Payload.QuizResponse;
import com.quiz.trivia.Repository.QuestionRepository;
import com.quiz.trivia.Service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private RestTemplate restTemplate;
    private QuestionRepository questionRepository;

    public QuestionServiceImpl(RestTemplate restTemplate,QuestionRepository questionRepository) {
        this.restTemplate = restTemplate;
        this.questionRepository=questionRepository;
    }

    @Override
    public OpenDtbResponse getQuestions() {

        OpenDtbResponse questions= restTemplate.getForObject("https://opentdb.com/api.php?amount=10", OpenDtbResponse.class);
        return questions;

    }

    @Override
    public Set<Question> downloadQuestions() {

        List<QuestionDownloadResponse> questionResponse= Arrays.stream(getQuestions().getResults()).toList();
        List<Question>questionList=questionResponse.stream().map(response->
        {
            Question tempQuestion=new Question();
            tempQuestion.setCategory(response.getCategory());
            tempQuestion.setQuestionText(response.getQuestion());
            tempQuestion.setDifficulty(response.getDifficulty());
            tempQuestion.setType(response.getType());
            tempQuestion.setCorrectAnswer(response.getCorrect_answer());
            String[]incorrectAnswers=response.getIncorrect_answers();
            tempQuestion.setIncorrectAnswerOne(incorrectAnswers[0]);
            if(response.getType().equals("multiple")) {
                tempQuestion.setIncorrectAnswerTwo(incorrectAnswers[1]);
                tempQuestion.setIncorrectAnswerThree(incorrectAnswers[2]);
            }
            tempQuestion.setDifficulty(response.getDifficulty());

            return tempQuestion;

        }).toList();

        List<Question> oldQuestions=questionRepository.findAll();
        Set<String> oldQuestionsSet=oldQuestions.stream()
                .map(Question::getQuestionText)
                .collect(Collectors.toSet());
        Set<Question> newQuestionSet=questionList.stream()
                .filter(question -> !oldQuestionsSet.contains(question.getQuestionText()))
                .collect(Collectors.toSet());
        if(!newQuestionSet.isEmpty())
        {
            questionRepository.saveAll(newQuestionSet);
        }
        System.out.println("download");



        return newQuestionSet;
    }

    @Override
    public QuizResponse getQuizQuestions(int numberOfQuestion, int quizNumber,String category,String difficulty) {

        Pageable pageable= PageRequest.of(quizNumber,numberOfQuestion);
        Page<Question>questions;
        if(category==null||difficulty==null){
      questions=questionRepository.findAll(pageable);
        }
        else {
            questions = questionRepository.findAllByCategoryAndDifficulty(category, difficulty, pageable);
        }
             List<QuestionResponse>questionResponseList=questions.stream().map(question -> {
            QuestionResponse tempResponse = new QuestionResponse();
            tempResponse.setId(question.getId());
            tempResponse.setQuestion(question.getQuestionText());
            tempResponse.setCorrectAnswer(question.getCorrectAnswer());
            if(question.getIncorrectAnswerOne()!=null)
                tempResponse.addIncorrectAnswer(question.getIncorrectAnswerOne());
            if (question.getType().equals("multiple"))
            {
                tempResponse.addIncorrectAnswer(question.getIncorrectAnswerTwo());
                tempResponse.addIncorrectAnswer(question.getIncorrectAnswerThree());

            }
            return tempResponse;
        }).toList();



        return new QuizResponse(questionResponseList,questions.getNumber(),questions.getSize());
    }

}
