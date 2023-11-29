package questions;

public interface Question extends Comparable<Question> {
    String getText();
    String answer(String answer);
    QuestionType getType();

}
abstract class AbstractQuestion implements Question {
    protected String questionText;
    protected QuestionType type;

    public AbstractQuestion(String text, QuestionType type) {
        this.questionText = text;
        this.type = type;
    }

    public abstract String getText();
    public abstract String answer(String answer);

    public static boolean isValidAnswer(String answer, int upperbound) {
        String pattern = String.format("^[1-%d]$", upperbound);
        return answer.matches(pattern);
    }

    public QuestionType getType() {
        return type;
    }
}
