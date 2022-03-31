package com.daniil1380.restservice;

import com.daniil1380.restservice.dto.Answer;
import com.daniil1380.restservice.dto.Question;
import com.daniil1380.restservice.dto.TestWithQuestions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreTestComponent {

    private final List<TestWithQuestions> testWithQuestions = new ArrayList<>();

    public TestWithQuestions findTestById(Long id) {
        for (TestWithQuestions test : this.testWithQuestions) {
            if (test.getId().equals(id)) {
                return test;
            }
        }
        return null;
    }

    public StoreTestComponent() {
        var test = new TestWithQuestions();
        var question = new Question();
        question.setQuestion("Год основания Санкт-Петербурга");
        question.setId(1L);
        List<Answer> answers = new ArrayList<>();
        var firstAnswer = new Answer();
        firstAnswer.setId(1L);
        firstAnswer.setAnswer("1703");
        answers.add(firstAnswer);
        var secondAnswer = new Answer();
        secondAnswer.setId(2L);
        secondAnswer.setAnswer("1803");
        answers.add(secondAnswer);
        question.setAnswers(answers);
        var questionSecond = new Question();
        questionSecond.setQuestion("Кто основал Санкт-Петербург");
        questionSecond.setId(2L);
        List<Answer> answersSecond = new ArrayList<>();
        var firstAnswerSecond = new Answer();
        firstAnswerSecond.setId(1L);
        firstAnswerSecond.setAnswer("Петрушка");
        answersSecond.add(firstAnswerSecond);
        var secondAnswerSecond = new Answer();
        secondAnswerSecond.setId(2L);
        secondAnswerSecond.setAnswer("Петр 1 Великий");
        answersSecond.add(secondAnswerSecond);
        questionSecond.setAnswers(answersSecond);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        questions.add(questionSecond);
        test.setId(1L);
        test.setQuestions(questions);
        List<Long> idsOfCorrectAnswers = new ArrayList<>();
        idsOfCorrectAnswers.add(1L);
        idsOfCorrectAnswers.add(2L);
        test.setIdsOfCorrectAnswers(idsOfCorrectAnswers);
        test.setTestName("История Санкт-Петербурга");
        testWithQuestions.add(test);
    }

    public List<TestWithQuestions> getTestWithQuestions() {
        return testWithQuestions;
    }
}
