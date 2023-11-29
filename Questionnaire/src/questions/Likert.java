package questions;

public class Likert implements Question {
    private final String questionText;

    // Constructor
    public Likert(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public String getText() {
        return questionText;
    }

    @Override
    public String answer(String answer) {
        try {
            int answerNumber = Integer.parseInt(answer);
            if (answerNumber >= 1 && answerNumber <= 5) {
                return "Correct";
            } else {
                return "Incorrect";
            }
        } catch (NumberFormatException e) {
            // This block executes if the input wasn't a number,
            // which means it isn't a valid answer.
            return "Incorrect";
        }
    }
    @Override
    public QuestionType getType() {
        return QuestionType.LIKERT;
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
