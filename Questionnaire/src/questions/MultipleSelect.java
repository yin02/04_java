package questions;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MultipleSelect implements Question {
    private final String questionText;
    private final Set<Integer> correctAnswers;
    private final List<String> options;

    public MultipleSelect(String questionText, String correctAnswersStr, String... options) {
        this.questionText = questionText;
        this.options = Arrays.asList(options);
        this.correctAnswers = Arrays.stream(correctAnswersStr.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        if (this.options.size() < 3 || this.options.size() > 8) {
            throw new IllegalArgumentException("There must be between 3 and 8 options.");
        }

        for (Integer answerIndex : this.correctAnswers) {
            if (answerIndex < 1 || answerIndex > this.options.size()) {
                throw new IllegalArgumentException("Invalid correct answer index.");
            }
        }
    }
    @Override
    public QuestionType getType() {
        return QuestionType.MULTIPLE_SELECT;
    }
    @Override
    public String getText() {
        return questionText;
    }

    @Override
    public String answer(String answer) {
        Set<Integer> answerIndexes = Arrays.stream(answer.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        if (answerIndexes.equals(this.correctAnswers)) {
            return "Correct";
        } else {
            return "Incorrect";
        }
    }

    @Override
    public int compareTo(Question question) {
        int diff = this.getType().ordinal() - question.getType().ordinal();

        if (diff == 0) {
            return this.questionText.compareTo(question.getText()); // '
        }
        return diff;
    }
}
