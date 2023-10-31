package com.Mica.QuizApp.Service;

import com.Mica.QuizApp.DAO.QuestionDao;
import com.Mica.QuizApp.DAO.QuizDao;
import com.Mica.QuizApp.Model.Question;
import com.Mica.QuizApp.Model.QuestionWrapper;
import com.Mica.QuizApp.Model.Quiz;
import com.Mica.QuizApp.Model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
private final QuizDao quizDao;
private final QuestionDao questionDao;

    public QuizService(QuizDao quizDao, QuestionDao questionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions =questionDao.findRandomQuestions(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
    Optional<Quiz> quiz= quizDao.findById(id);
    List<Question>questionFromDb=quiz.get().getQuestions();
    List<QuestionWrapper> questionsForUser=new ArrayList<>();
    for(Question q: questionFromDb){
        QuestionWrapper qw =new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
        questionsForUser.add(qw);
    }
    return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for (Response r:responses){
            if (r.getResponse().equals( questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
