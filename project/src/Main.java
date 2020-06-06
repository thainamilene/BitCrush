import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BoardComponent board = new BoardComponent();
        board.score =  new ScoreboardComponent();
        System.out.println("Selecione o seu nível de 01 a 03");
        Scanner sc = new Scanner(System.in);
        int level = sc.nextInt();
        sc.close();
        if (level == 1) {
            board.assembleBoard(5);
        } else if (level == 2) {
            board.assembleBoard(7);
        } else {
            board.assembleBoard(9);
        }
    }
}
