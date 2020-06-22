import javax.swing.ImageIcon;

public class NormalPiecesComponent extends Pieces {

    public NormalPiecesComponent() {
        super();
        style();
    }

    public void setType(int x) {
        /*Recebe um inteiro aleatorio e a partir dele define o tipo de peca*/
        this.x = x;
        switch (x) {
            case -1:
                this.type = 'D';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/field.png"));
                break;
            case 0:
                this.type = '1';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece01.png"));
                break;
            case 1:
                this.type = '2';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece02.png"));
                break;
            case 2:
                this.type = '3';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece03.png"));
                break;
            case 3:
                this.type = '4';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece04.png"));
                break;
            case 4:
                this.type = '5';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece05.png"));
                break;
            case 5:
                this.type = '6';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece06.png"));
                break;
            case 6:
                this.type = '7';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece07.png"));
                break;
            case 7:
                this.type = '8';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece08.png"));

                break;
            case 8:
                this.type = '9';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece09.png"));
                break;
        }
    }

    public boolean verifyMovement(int target) throws UselessMovement {
        /*verifica se o movimento da primeira peca e valido, verificando o arredor da peca, comparando o tipo da peca atual, com as que estao ao redor dela*/
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
        if (((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) || ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 81)) {
            if ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) { // pecas movendo na mesma linha
                if (type == getPieceOnTop(target) && type == getPieceOnTop(target - 9)) {
                    moves[0].setV(true);
                    moves[0].setMoveType('c');
                    moves[0].setVct(target);
                    moves[0].setVct(target - 9);
                    moves[0].setVct(target - 18);
                    if (type == getPieceInBottom(target)) {
                        moves[0].setMoveType('1');
                        moves[0].setVct(target + 9);
                    }
                }
                if (type == getPieceInBottom(target) && type == getPieceInBottom(target + 9)) {
                    if (moves[0].isV()) {
                        moves[0].setMoveType('b');
                        moves[0].setVct(target + 18);
                    } else {
                        moves[0].setV(true);
                        moves[0].setMoveType('c');
                        moves[0].setVct(target);
                        moves[0].setVct(target + 9);
                        moves[0].setVct(target + 18);
                        if (type == getPieceOnTop(target)) {
                            moves[0].setMoveType('1');
                            moves[0].setVct(target - 9);
                        }
                    }
                } else if (!moves[0].isV() && type == getPieceOnTop(target) && type == getPieceInBottom(target)) {
                    moves[0].setV(true);
                    moves[0].setMoveType('c');
                    moves[0].setVct(target);
                    moves[0].setVct(target + 9);
                    moves[0].setVct(target - 9);
                }
                if (moves[0].getMovetype() != 'b') {
                    if (type == getPieceInRight(target) && type == getPieceInRight(target + 1)) {
                        if (moves[0].getMovetype() == '1') {
                            moves[0].remove();
                        }
                        if (moves[0].isV()) {
                            moves[0].setMoveType('2');
                        } else {
                            moves[0].setV(true);
                            moves[0].setMoveType('l');
                            moves[0].setVct(target);
                        }
                        moves[0].setVct(target + 1);
                        moves[0].setVct(target + 2);
                    }
                }
            } else { //pecas se movendo na mesma coluna
                if (type == getPieceInRight(target) && type == getPieceInRight(target + 1)) {
                    moves[0].setV(true);
                    moves[0].setMoveType('l');
                    moves[0].setVct(target);
                    moves[0].setVct(target + 1);
                    moves[0].setVct(target + 2);
                    if (type == getPieceInLeft(target)) {
                        moves[0].setMoveType('1');
                        moves[0].setVct(target - 1);
                    }
                }
                if (type == getPieceInLeft(target) && type == getPieceInLeft(target - 1)) {
                    if (moves[0].isV()) {
                        moves[0].setMoveType('b');
                        moves[0].setVct(target - 2);
                    } else {
                        moves[0].setV(true);
                        moves[0].setMoveType('l');
                        moves[0].setVct(target);
                        moves[0].setVct(target - 1);
                        moves[0].setVct(target - 2);
                        if (type == getPieceInRight(target)) {
                            moves[0].setMoveType('1');
                            moves[0].setVct(target + 1);
                        }
                    }
                } else if (!moves[0].isV() && type == getPieceInLeft(target) && type == getPieceInRight(target)) {
                    moves[0].setV(true);
                    moves[0].setMoveType('l');
                    moves[0].setVct(target);
                    moves[0].setVct(target + 1);
                    moves[0].setVct(target - 1);
                }
                if (moves[0].getMovetype() != 'b') {
                    if (type == getPieceInBottom(target) && type == getPieceInBottom(target + 9)) {
                        if (moves[0].getMovetype() == '1') {
                            moves[0].remove();
                        }
                        if (moves[0].isV()) {
                            moves[0].setMoveType('2');
                        } else {
                            moves[0].setV(true);
                            moves[0].setMoveType('c');
                            moves[0].setVct(target);
                        }
                        moves[0].setVct(target + 9);
                        moves[0].setVct(target + 18);
                    }
                }
            }
            verifyTargetMovement(target); //verifica o movimento da segunda peca selecionada
        }
        return moves[0].isV() || moves[1].isV(); //se um dos dois movimentos sao verdadeiro, entao o movimento final e valido
    }
}