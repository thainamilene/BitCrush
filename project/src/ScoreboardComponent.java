import static java.lang.System.exit;

public class ScoreboardComponent implements IScoreboard{
    int score;
    int round;

    public ScoreboardComponent() {
        score = 0;
        round = 0;
    }

    //não precisa ser public -- completar
    private void verifyRound() {
        if (round == 20) {
            exit(0);
        }
    }

    public void sumRound(boolean v) {
        if (v) {
            round ++;
        }
        verifyRound();
    }

    public void sumScore(int n) {
        score+=n;
    }

    public int getScore() {
        return score;
    }

    public int getRound() {
        return round;
    }
}
