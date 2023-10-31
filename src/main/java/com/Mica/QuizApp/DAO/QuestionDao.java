package com.Mica.QuizApp.DAO;

import com.Mica.QuizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String type);

    @Query(value = "SELECT * from question q where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestions(@Param("category") String category, @Param("numQ") int numQ);
}
