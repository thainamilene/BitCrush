import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BoardComponent board = new BoardComponent();
        board.score =  new ScoreboardComponent();
        System.out.println("Selecione o seu nível de 01 a 03");
        Scanner sc = new Scanner(System.in);
        int level = sc.nextInt();
       // sc.close();
        if (level == 1) {
            board.assembleBoard(5);
        } else if (level == 2) {
            board.assembleBoard(7);
        } else {
            board.assembleBoard(9);
        }
        System.out.println("jogada");
   //     Scanner sc1 = new Scanner(System.in);
        TranslateMovementComponent movementC = new TranslateMovementComponent();
        movementC.translateMove(sc.next());
        if (board.board[movementC.getSource()[0]][movementC.getSource()[1]].verifyMovement(movementC, board))
            System.out.println("dlksksldkslkdlaksdlk");
        sc.close();
    }
}
