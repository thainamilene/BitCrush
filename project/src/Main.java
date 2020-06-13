import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Window windom = new Window();
      //  BoardComponent board = new BoardComponent();
     //   board.score =  new ScoreboardComponent();
        System.out.println("Selecione o seu nível de 01 a 03");
       /* Scanner sc = new Scanner(System.in);
        int level = sc.nextInt();
        if (level == 1) {
            board.assembleBoard(5);
        } else if (level == 2) {
            board.assembleBoard(7);
        } else {
            board.assembleBoard(9);
        }*/
        System.out.println("jogada");
        TranslateMovementComponent movementC = new TranslateMovementComponent();
       // movementC.translateMove(sc.next());
       // board.movePieces(movementC);
   //     sc.close();
        //board.printBoard();
    }
}
