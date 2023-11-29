package questions;

public class TrueFalse implements Question {
    private final String questionText;
    private final boolean correctAnswer;

    // Constructor with question text and the correct answer
    public TrueFalse(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = Boolean.parseBoolean(correctAnswer);
    }

    @Override
    public String getText() {
        return questionText;
    }

    @Override
    public String answer(String answer) {
        // Normalize and check the answer. If it doesn't match "true" or "false" (case insensitive), it's incorrect.
        if (!answer.equalsIgnoreCase("true") && !answer.equalsIgnoreCase("false")) {
            return "Incorrect";
        }

        boolean answerBoolean = Boolean.parseBoolean(answer);
        return (answerBoolean == this.correctAnswer) ? "Correct" : "Incorrect";
    }

    @Override
    public int compareTo(Question question) {
        int diff = this.getType().ordinal() - question.getType().ordinal();

        if (diff == 0) {
            return this.questionText.compareTo(question.getText()); // '
        }
        return diff;
    }
    @Override
    public QuestionType getType() {
        return QuestionType.TRUE_FALSE;
    }
}
