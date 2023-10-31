package com.Mica.QuizApp.Controller;

import com.Mica.QuizApp.Model.Question;
import com.Mica.QuizApp.Service.QuestionService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(
        value = "/question",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST})
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public  ResponseEntity<List<Question>> allQuestions(){
        try{
        return new ResponseEntity<>(questionService.allQuestion(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return new ResponseEntity<>(questionService.getByCategory(category), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> AddQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }
}
