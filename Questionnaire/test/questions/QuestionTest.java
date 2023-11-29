package questions;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuestionTest {

    @Test
    public void testTrueFalseQuestion() {
        TrueFalse question = new TrueFalse("Is the sky blue?", "true");
        assertEquals("Correct", question.answer("true"));
        assertEquals("Incorrect", question.answer("false"));
        assertEquals("Incorrect", question.answer("maybe"));
        assertEquals(QuestionType.TRUE_FALSE, question.getType());
    }

    @Test
    public void testMultipleChoiceQuestion() {
        MultipleChoice question = new MultipleChoice("What is the capital of France?", "1", "Paris", "Berlin", "London");
        assertEquals("Correct", question.answer("1"));
        assertEquals("Incorrect", question.answer("2"));
        assertEquals("Incorrect", question.answer("3"));
        assertEquals(QuestionType.MULTIPLE_CHOICE, question.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultipleChoiceInvalidAnswer() {
        MultipleChoice question = new MultipleChoice("What is the capital of France?", "4", "Paris", "Berlin", "London");
    }

    @Test
    public void testQuestionBankAddAndGetQuestions() {
        QuestionBank bank = new QuestionBank();
        TrueFalse question1 = new TrueFalse("Is the sky blue?", "true");
        MultipleChoice question2 = new MultipleChoice("What is the capital of France?", "1", "Paris", "Berlin", "London");

        bank.addQuestion(question1);
        bank.addQuestion(question2);

        assertEquals(2, bank.getQuestions().size());
        assertTrue(bank.getQuestions().contains(question1));
        assertTrue(bank.getQuestions().contains(question2));
    }

    @Test
    public void testQuestionComparator() {
        TrueFalse question1 = new TrueFalse("Is the sky blue?", "true");
        MultipleChoice question2 = new MultipleChoice("What is the capital of France?", "1", "Paris", "Berlin", "London");

        QuestionComparator comparator = new QuestionComparator();
        assertTrue(comparator.compare(question1, question2) < 0);
        assertTrue(comparator.compare(question2, question1) > 0);
    }
}
