package questions;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class QuestionBank {
    private List<Question> questions;

    public QuestionBank() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
        this.questions.sort(new QuestionComparator());
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(this.questions);
    }
}
