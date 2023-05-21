package com.quiz.trivia.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private Long id;
    private String question;
    private String correctAnswer;
    private List<String>incorrectAnswers=new ArrayList<>();

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        for (String answer:incorrectAnswers)
        {
            if(answer!=null)
            {
                this.incorrectAnswers.add(answer);
            }
        }
    }
    public void addIncorrectAnswer(String answer)
    {
        this.incorrectAnswers.add(answer);
    }
}
