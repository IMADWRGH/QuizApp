package com.Mica.QuizApp.Service;

import com.Mica.QuizApp.DAO.QuestionDao;
import com.Mica.QuizApp.Model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> allQuestion(){
        return questionDao.findAll();
    }
    public List<Question> getByCategory(String category){
        return questionDao.findByCategory(category);
    }

    public  ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>( "success", HttpStatus.CREATED);
    }
    public String deleteQuestion(int id) {
        questionDao.deleteById(id);
        return "success";
    }
}
