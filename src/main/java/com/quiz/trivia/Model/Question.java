package com.quiz.trivia.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.AUTO;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="questions")
public class Question {

    @GeneratedValue(strategy = AUTO)
    @Id

    private Long id;
    private String category;
    private String difficulty;
    private String type;
    private String questionText;
    private String correctAnswer;

    private String incorrectAnswerOne;
    private String incorrectAnswerTwo;
    private String incorrectAnswerThree;

    public Question(String category, String difficulty, String type, String questionText, String correctAnswer,
                    String incorrectAnswerOne, String incorrectAnswerTwo, String incorrectAnswerThree) {
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswerOne = incorrectAnswerOne;
        this.incorrectAnswerTwo = incorrectAnswerTwo;
        this.incorrectAnswerThree = incorrectAnswerThree;
    }
}
