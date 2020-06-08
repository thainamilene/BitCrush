import static java.lang.System.exit;

public class ScoreboardComponent implements IScoreboard{
    int score;
    int round;

    public ScoreboardComponent() {
        score = 0;
        round = 0;
    }

    private void verifyRound() {
        if (round == 20) {
            printLastScoreboard();
            exit(0);
        }
    }

    //arrumar print
    private void printLastScoreboard() {
        if (score<200) {
            System.out.println("Você falhou, sorry");
        } else {
            System.out.println("Congratulations");
        }
    }

    public void sumRound(boolean v) {
        if (v) {
            round ++;
        }
        verifyRound();
    }

    public void sumScore(int n) {
        score += n;
    }

    public int getScore() {
        return score;
    }

    public int getRound() {
        return round;
    }
}
