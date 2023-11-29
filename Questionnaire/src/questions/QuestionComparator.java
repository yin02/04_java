package questions;

import java.util.Comparator;

public class QuestionComparator implements Comparator<Question> {
    @Override
    public int compare(Question q1, Question q2) {
        // First, compare by question type priority
        int typeCompare = Integer.compare(typePriority(q1), typePriority(q2));
        if (typeCompare != 0) {
            return typeCompare;
        }

        // If questions are of the same type, compare lexicographically by question text
        return q1.getText().compareTo(q2.getText());
    }

    // Helper method to assign priorities to different question types
    private int typePriority(Question q) {
        if (q instanceof TrueFalse) {
            return 1;
        } else if (q instanceof MultipleChoice) {
            return 2;
        } else if (q instanceof MultipleSelect) {
            return 3;
        } else if (q instanceof Likert) {
            return 4;
        }
        return Integer.MAX_VALUE; // unknown types are placed at the end
    }
}
