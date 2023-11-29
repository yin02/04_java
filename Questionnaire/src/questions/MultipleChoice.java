package questions;

public class MultipleChoice extends AbstractQuestion{
    private String correctAnswer;

    public MultipleChoice(String text, String correctAnswer, String op1, String op2, String op3)
            throws IllegalArgumentException{
        super(text, QuestionType.MULTIPLE_CHOICE);
        if(!super.isValidAnswer(correctAnswer, 3)) {
            throw new IllegalArgumentException("Invalid correct answer");
        }
        this.correctAnswer = correctAnswer;
    }

    public MultipleChoice(String text, String correctAnswer, String op1, String op2, String op3,
                          String op4) throws IllegalArgumentException{
        super(text, QuestionType.MULTIPLE_CHOICE);
        if(!super.isValidAnswer(correctAnswer, 4)) {
            throw new IllegalArgumentException("Invalid correct answer");
        }
        this.correctAnswer = correctAnswer;
    }

    public MultipleChoice(String text, String correctAnswer, String op1, String op2, String op3,
                          String op4, String op5) throws IllegalArgumentException{
        super(text, QuestionType.MULTIPLE_CHOICE);
        if(!super.isValidAnswer(correctAnswer, 5)) {
            throw new IllegalArgumentException("Invalid correct answer");
        }
        this.correctAnswer = correctAnswer;
    }

    public MultipleChoice(String text, String correctAnswer, String op1, String op2, String op3,
                          String op4, String op5, String op6) throws IllegalArgumentException{
        super(text, QuestionType.MULTIPLE_CHOICE);
        if(!super.isValidAnswer(correctAnswer, 6)) {
            throw new IllegalArgumentException("Invalid correct answer");
        }
        this.correctAnswer = correctAnswer;
    }

    public MultipleChoice(String text, String correctAnswer, String op1, String op2, String op3,
                          String op4, String op5, String op6, String op7) throws IllegalArgumentException{
        super(text, QuestionType.MULTIPLE_CHOICE);
        if(!super.isValidAnswer(correctAnswer, 7)) {
            throw new IllegalArgumentException("Invalid correct answer");
        }
        this.correctAnswer = correctAnswer;
    }

    public MultipleChoice(String text, String correctAnswer, String op1, String op2, String op3,
                          String op4, String op5, String op6, String op7, String op8) throws IllegalArgumentException{
        super(text, QuestionType.MULTIPLE_CHOICE);
        if(!super.isValidAnswer(correctAnswer, 8)) {
            throw new IllegalArgumentException("Invalid correct answer");
        }
        this.correctAnswer = correctAnswer;
    }


    @Override
    public String answer(String answer) {
        if (answer.equals(this.correctAnswer)) {
            return "Correct";
        } else {
            return "Incorrect";
        }
    }
    @Override
    public String getText() {
        return this.questionText;
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
        return QuestionType.MULTIPLE_CHOICE;
    }
}
