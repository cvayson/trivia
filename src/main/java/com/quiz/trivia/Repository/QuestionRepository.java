package com.quiz.trivia.Repository;

import com.quiz.trivia.Model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(
            value = "select * from questions  where questions.category like %:category% AND questions.difficulty like %:difficulty% ",
            nativeQuery = true
    )
    Page<Question> findAllByCategoryAndDifficulty(String category,String difficulty, Pageable pageable);
}
