package com.daniil1380.restservice;

import com.daniil1380.restservice.dto.Answer;
import com.daniil1380.restservice.dto.Question;
import com.daniil1380.restservice.dto.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreTestComponent {

    private final List<Test> tests = new ArrayList<>();

    public Test findTestById(Integer id) {
        for (Test test : tests) {
            if (test.getId().equals(id)) {
                return test;
            }
        }
        return null;
    }

    public StoreTestComponent() {
        var test = new Test();
        var question = new Question();
        question.setQuestion("Год основания Санкт-Петербурга");
        question.setId(1);
        List<Answer> answers = new ArrayList<>();
        var firstAnswer = new Answer();
        firstAnswer.setId(1);
        firstAnswer.setAnswer("1703");
        answers.add(firstAnswer);
        var secondAnswer = new Answer();
        secondAnswer.setId(2);
        secondAnswer.setAnswer("1803");
        answers.add(secondAnswer);
        question.setAnswers(answers);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        test.setId(1);
        test.setQuestions(questions);
        List<Integer> idsOfCorrectAnswers = new ArrayList<>();
        idsOfCorrectAnswers.add(1);
        test.setIdsOfCorrectAnswers(idsOfCorrectAnswers);
        tests.add(test);
    }

}
