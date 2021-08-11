import java.util.Objects;

public class QuizCard {
    String question;
    String answer;


    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public QuizCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizCard quizCard = (QuizCard) o;
        return Objects.equals(question, quizCard.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }
}
