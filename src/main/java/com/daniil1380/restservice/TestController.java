package com.daniil1380.restservice;

import com.daniil1380.restservice.dto.Answer;
import com.daniil1380.restservice.dto.Question;
import com.daniil1380.restservice.dto.Test;
import com.daniil1380.restservice.dto.TestWithQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    StoreUserComponent storeUserComponent;

    @Autowired
    StoreTestComponent storeTestComponent;

    @GetMapping("/test/latest/{userUuid}")
    public ResponseEntity<Long> getLastResult(@PathVariable Long userUuid) {
        System.out.println("Получение последнего результата для пользователя: " + userUuid);
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) {
            System.out.println("Такого пользователя нет");
            return ResponseEntity.badRequest().body(null);
        }
        System.out.println("Результат получен для пользователя: " + userUuid);
        return ResponseEntity.ok(loggedUser.getLastResult());
    }

    @GetMapping("/test/start/{testId}")
    public ResponseEntity<Long> startTesting(@PathVariable Long testId, @RequestParam Long userUuid) {
        System.out.println("Старт тестирования пользователем: " + userUuid);
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) {
            System.out.println("Такого пользователя нет");
            return ResponseEntity.badRequest().body(null);
        }
        if (loggedUser.getStartedTest() != -1) {
            System.out.println("Тест уже начат, его id: " + testId);
            return ResponseEntity.badRequest().body(null);
        }
        else {
            int size = storeTestComponent.findTestById(testId).getQuestions().size();
            loggedUser.setStartedTest(testId);
            loggedUser.setPickedAnswerIds(new Long[size]);
            System.out.println("Тест номер " + testId + " успешно запущен!");
            return ResponseEntity.ok((long) size);
        }
    }

    @GetMapping("/test/finish/{testId}")
    public ResponseEntity<Long> endTesting(@PathVariable Long testId, @RequestParam Long userUuid) {
        System.out.println("Конец тестирования пользователем: " + userUuid);
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) {
            System.out.println("Такого пользователя нет");
            return ResponseEntity.badRequest().body(null);
        }
        if (!loggedUser.getStartedTest().equals(testId)) {
            System.out.println("Выбранный для завершения тест не совпадает с активным тестом пользователя!");
            return ResponseEntity.badRequest().body(null);
        }
        var test = storeTestComponent.findTestById(testId);
        List<Long> correctAnswers = test.getIdsOfCorrectAnswers();
        Long[] pickedAnswers = loggedUser.getPickedAnswerIds();
        if (correctAnswers.size() != pickedAnswers.length) {
            System.out.println("Ошибка сервера при завершении теста!");
            return ResponseEntity.internalServerError().body(null);
        }
        var sumOfCorrect = 0;
        for (var i = 0; i < correctAnswers.size(); i++) {
            if (pickedAnswers[i] == correctAnswers.get(i)) {
                sumOfCorrect++;
            }
        }
        loggedUser.setPickedAnswerIds(null);
        loggedUser.setStartedTest(-1L);
        Long result = (long) (sumOfCorrect * 100 / correctAnswers.size());
        loggedUser.setLastResult(result);
        System.out.println("Тест номер " + testId + " успешно завершен!");
        return ResponseEntity.ok(result);

    }

    @GetMapping("/test/{testId}/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long testId, @PathVariable Long questionId, @RequestParam Long userUuid) {
        System.out.println("Получение вопроса пользователем: " + userUuid);
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) {
            System.out.println("Такого пользователя нет");
            return ResponseEntity.badRequest().body(null);
        };
        if (loggedUser.getStartedTest().equals(testId)) {
            List<Question> questions = storeTestComponent.findTestById(testId).getQuestions();
            for (Question question : questions) {
                if (question.getId().equals(questionId)) {
                    return ResponseEntity.ok(question);
                }
            }
        }
        System.out.println("Такого вопроса нет");
        return null;
    }

    @PostMapping("/test/{testId}/{questionId}/{answerId}")
    public ResponseEntity<Answer> postPickedAnswer(@PathVariable Long testId, @PathVariable Long questionId, @PathVariable Long answerId,
                                                   @RequestParam Long userUuid) {
        System.out.println("Отправка ответа пользователем: " + userUuid);
        var loggedUser = storeUserComponent.findUserByUuid(userUuid);
        if (loggedUser == null) {
            System.out.println("Такого пользователя нет");
            return ResponseEntity.badRequest().body(null);
        }
        if (loggedUser.getStartedTest().equals(testId)) {
            loggedUser.getPickedAnswerIds()[questionId.intValue() - 1] = answerId;
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
        System.out.println("Отправка ответа пользователем: " + userUuid);
        return null;
    }

    @GetMapping("/test/all")
    public ResponseEntity<List<Test>> getAllTests() {
        System.out.println("Получение всех тестов");
        List<TestWithQuestions> testsWithQuestions = storeTestComponent.getTestWithQuestions();
        List<Test> tests = new ArrayList<>();
        testsWithQuestions.forEach(testWithQuestions -> tests.add(new Test(testWithQuestions)));
        return ResponseEntity.ok(tests);
    }
}
