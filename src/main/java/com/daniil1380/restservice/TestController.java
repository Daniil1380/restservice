package com.daniil1380.restservice;

import com.daniil1380.restservice.dto.Answer;
import com.daniil1380.restservice.dto.Question;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/test/latest/{userUuid}")
    Integer getLastResult(@PathVariable Long userUuid) {
        return 100;
    }

    @GetMapping("/test/start/{testId}")
    Integer getLastR(@PathVariable Long testId, @RequestParam Long userUuid) {
        return 100;
    }

    @GetMapping("/test/finish/{testId}")
    Integer getLast(@PathVariable Long testId, @RequestParam Long userUuid) {
        return 100;
    }

    @GetMapping("/test/{testId}/{questionId}")
    Question getLast(@PathVariable Long testId, @PathVariable Long questionId, @RequestParam Long userUuid) {
        Question question = new Question();
        question.setQuestion("Как зовут Петра Первого?");
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Answer answer = new Answer();
            answer.setAnswer("Петр " + i);
            answer.setId(i);
            answers.add(answer);
        }
        question.setAnswers(answers);
        question.setId(123);
        return question;
    }

    @PostMapping("/test/{testId}/{questionId}/{answerId}")
    Answer get(@PathVariable Long testId, @PathVariable Long questionId, @PathVariable Long answerId,
                 @RequestParam Long userUuid) {
        Answer answer = new Answer();
        answer.setAnswer("Петр");
        answer.setId(1);
        return answer;
    }


}
