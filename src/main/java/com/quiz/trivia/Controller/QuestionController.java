package com.quiz.trivia.Controller;

import com.quiz.trivia.Model.Question;
import com.quiz.trivia.Payload.OpenDtbResponse;
import com.quiz.trivia.Payload.QuizRequest;
import com.quiz.trivia.Payload.QuizResponse;
import com.quiz.trivia.Service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/get")
    public OpenDtbResponse getQuestions()
    {
        return questionService.getQuestions();
    }

    @PostMapping("/download")
    public Set<Question> downloadQuestions()
    {
        return questionService.downloadQuestions();
    }

    @GetMapping("/quiz/getRandom/{numberOfQuestions}/{quizNumber}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public QuizResponse getRandomQuiz(@PathVariable int numberOfQuestions, @PathVariable int quizNumber)
    {
        return questionService.getQuizQuestions(numberOfQuestions,quizNumber,null,null);
    }

    @GetMapping("/quiz/getQuiz/{numberOfQuestions}/{quizNumber}/{category}/{difficulty}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public QuizResponse getQuiz(@PathVariable int numberOfQuestions,@PathVariable int quizNumber,
                                @PathVariable String category,@PathVariable String difficulty)
        {
            System.out.println("angular");
            return this.questionService.getQuizQuestions(numberOfQuestions,quizNumber,category,difficulty);
        }
}
