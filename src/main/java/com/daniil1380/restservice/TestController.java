package com.daniil1380.restservice;

import com.daniil1380.restservice.dto.Answer;
import com.daniil1380.restservice.dto.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    StoreUserComponent storeUserComponent;

    @Autowired
    StoreTestComponent storeTestComponent;

    @GetMapping("/test/latest/{userUuid}")
    public ResponseEntity<Integer> getLastResult(@PathVariable Integer userUuid) {
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(loggedUser.getLastResult());
    }

    @GetMapping("/test/start/{testId}")
    public ResponseEntity<Integer> startTesting(@PathVariable Integer testId, @RequestParam Integer userUuid) {
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) return ResponseEntity.badRequest().body(null);
        if (loggedUser.getStartedTest() != -1) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            Integer size = storeTestComponent.findTestById(testId).getQuestions().size();
            loggedUser.setStartedTest(testId);
            loggedUser.setPickedAnswerIds(new int[size]);
            return ResponseEntity.ok(size);
        }
    }

    @GetMapping("/test/finish/{testId}")
    public ResponseEntity<Integer> endTesting(@PathVariable Integer testId, @RequestParam Integer userUuid) {
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) return ResponseEntity.badRequest().body(null);
        if (!loggedUser.getStartedTest().equals(testId)) {
            return ResponseEntity.badRequest().body(null);
        }
        var test = storeTestComponent.findTestById(testId);
        List<Integer> correctAnswers = test.getIdsOfCorrectAnswers();
        int[] pickedAnswers = loggedUser.getPickedAnswerIds();
        if (correctAnswers.size() != pickedAnswers.length) {
            return ResponseEntity.internalServerError().body(null);
        }
        var sumOfCorrect = 0;
        for (var i = 0; i < correctAnswers.size(); i++) {
            if (pickedAnswers[i] == correctAnswers.get(i)) {
                sumOfCorrect++;
            }
        }
        loggedUser.setPickedAnswerIds(null);
        loggedUser.setStartedTest(-1);
        Integer result = sumOfCorrect * 100 / correctAnswers.size();
        loggedUser.setLastResult(result);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/test/{testId}/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer testId, @PathVariable Integer questionId, @RequestParam Integer userUuid) {
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) return ResponseEntity.badRequest().body(null);;
        if (loggedUser.getStartedTest().equals(testId)) {
            List<Question> questions = storeTestComponent.findTestById(testId).getQuestions();
            for (Question question : questions) {
                if (question.getId().equals(questionId)) {
                    return ResponseEntity.ok(question);
                }
            }
        }
        return null;
    }

    @PostMapping("/test/{testId}/{questionId}/{answerId}")
    public ResponseEntity<Answer> postPickedAnswer(@PathVariable Integer testId, @PathVariable Integer questionId, @PathVariable Integer answerId,
                                                   @RequestParam Integer userUuid) {
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) return ResponseEntity.badRequest().body(null);;
        if (loggedUser.getStartedTest().equals(testId)) {
            loggedUser.getPickedAnswerIds()[questionId - 1] = answerId;
            List<Question> questions = storeTestComponent.findTestById(testId).getQuestions();
            for (Question question : questions) {
                if (question.getId().equals(questionId)) {
                    for (Answer answer : question.getAnswers()) {
                        if (answer.getId().equals(answerId)) {
                            return ResponseEntity.ok(answer);
                        }
                    }
                }
            }
        }
        return null;
    }
}
