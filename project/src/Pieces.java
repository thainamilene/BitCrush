import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;

public abstract class Pieces implements IPieces {
    protected int x;
    protected char type;
    protected int index;
    protected JButton button;
    public IBoard Board;
    protected IPieces[] board;
    protected IMovementAttributes[] moves;

    public Pieces() {
        super();
        button = new JButton(); //Cria o botao que sera as pecas do jogo
        moves = new IMovementAttributes[2];
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
        button.addActionListener(this);
    }

    public abstract void setType (int x);

    public abstract boolean verifyMovement(int target) throws InvalidPlay;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public char getType() {
        return type;
    }

    public JButton getButton() {
        return button;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Board.translate(index); //envia ao tabuleiro o indice da peca clicada
        } catch (NonAdjacentPieces error) {
            System.out.println(error.getMessage());

        } catch (UselessMovement error) {
            System.out.println(error.getMessage());
        } catch (Exception error) {
            System.out.println("erro: " + error.getMessage());
        }
    }

    public void setBoard(IBoard Board) {
        this.Board = Board;
        this.board = Board.getBoard();
    }

    public int getX() {
        return this.x;
    }

    public IMovementAttributes[] getMoves() {
        return moves;
    }

    protected void style() {
        button.setSize(50 , 50);
        button.setBorderPainted(true);
        button.setBackground(new Color(0x847C9D));
    }

    protected char getPieceInLeft (int xIndex) {
        /*Verifica se a peca na esquerda existe e retorna o char que define o tipo dela */
        return (xIndex%9)!=0 ? board[xIndex-1].getType() : ' ';
    }

    protected char getPieceInRight (int xIndex) {
        /*Verifica se a peca na direita existe e retorna o char que define o tipo dela */
        return (xIndex+1)%9 != 0 || xIndex == 0 ? board[xIndex + 1].getType() : ' ';
    }

    protected char getPieceInBottom (int xIndex) {
        /*Verifica se a peca embaixo existe e retorna o char que define o tipo dela */
        return xIndex < 72 ? board[xIndex + 9].getType() : ' ';
    }

    protected char getPieceOnTop (int xIndex) {
        /*Verifica se a peca em cima existe e retorna o char que define o tipo dela */
        return xIndex > 8 ? board[xIndex - 9].getType() : ' ';
    }

    protected void verifyTargetMovement(int target) {
        /* Verifica se o movimento da segunda peca clicada e valida*/
        if (board[target] instanceof NormalPiecesComponent) {
            if ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) { // pecas movendo na mesma linha
                if (board[target].getType() == getPieceOnTop(index) && board[target].getType() == getPieceOnTop(index - 9)) {
                    /*verifica se ha duas pecas acima do target com o mesmo tipo que este*/
                    moves[1].setV(true);
                    moves[1].setMoveType('c');
                    moves[1].setVct(index);
                    moves[1].setVct(index - 9);
                    moves[1].setVct(index - 18);
                    if (board[target].getType() == getPieceInBottom(index)) {
                        /*verifica se ha mais uma peca igual embaixo dela*/
                        moves[1].setMoveType('1');
                        moves[1].setVct(index + 9);
                    }
                }
                if (board[target].getType() == getPieceInBottom(index) && board[target].getType() == getPieceInBottom(index + 9)) {
                    /*verifica se ha duas pecas embaixo do target com o mesmo tipo que este*/
                    if (moves[1].isV()) {
                        /*caso isV seja verdadeiro, o movimento gerara um bonus do tipo 5*/
                        moves[1].setMoveType('b');
                        moves[1].setVct(index + 18);
                    } else {
                        moves[1].setV(true);
                        moves[1].setMoveType('c');
                        moves[1].setVct(index);
                        moves[1].setVct(index + 9);
                        moves[1].setVct(index + 18);
                        if (board[target].getType() == getPieceOnTop(index)) {
                             /*verifica se ha mais uma peca igual embaixo dela*/
                            moves[1].setMoveType('1');
                            moves[1].setVct(index - 9);
                        }
                    }
                } else if (!moves[1].isV() && board[target].getType() == getPieceOnTop(index) && board[target].getType() == getPieceInBottom(index)) {
                    /*Verifica se ha uma peca acima e uma embaixo do mesmo tipo que o target*/
                    moves[1].setV(true);
                    moves[1].setMoveType('c');
                    moves[1].setVct(index);
                    moves[1].setVct(index + 9);
                    moves[1].setVct(index - 9);
                }
                if (moves[1].getMovetype() != 'b') {
                    /*verifica se o movimento ja nao gera um tipo bonus 03*/
                    if (board[target].getType() == getPieceInLeft(index) && board[target].getType() == getPieceInLeft(index - 1)) {
                        /*Verifica se ha duas pecas igual ao taget a sua esquerda*/
                        if (moves[1].getMovetype() == '1') {
                            moves[1].remove();
                        }
                        if (moves[1].isV()) {
                            moves[1].setMoveType('2');
                        } else {
                            moves[1].setV(true);
                            moves[1].setMoveType('l');
                            moves[1].setVct(index);
                        }
                        moves[1].setVct(index - 1);
                        moves[1].setVct(index - 2);
                    }
                }
            } else { // As pecas movem se mesma coluna
                if (board[target].getType() == getPieceInRight(index) && board[target].getType() == getPieceInRight(index + 1)) {
                     /*Verifica se ha duas pecas igual ao taget a sua direita*/
                    moves[1].setV(true);
                    moves[1].setMoveType('l');
                    moves[1].setVct(index);
                    moves[1].setVct(index + 1);
                    moves[1].setVct(index + 2);
                    if (board[target].getType() == getPieceInLeft(index)) {
                        moves[1].setMoveType('1');
                        moves[1].setVct(index - 1);
                    }
                }
                if (board[target].getType() == getPieceInLeft(index) && board[target].getType() == getPieceInLeft(index - 1)) {
                     /*Verifica se ha duas pecas igual ao taget a sua esquerda*/
                    if (moves[1].isV()) {
                        moves[1].setMoveType('b');
                        moves[1].setVct(index - 2);
                    } else {
                        moves[1].setV(true);
                        moves[1].setMoveType('l');
                        moves[1].setVct(index);
                        moves[1].setVct(index - 1);
                        moves[1].setVct(index - 2);
                        if (board[target].getType() == getPieceInRight(index)) {
                            moves[1].setMoveType('1');
                            moves[1].setVct(index + 1);
                        }
                    }
                } else if (!moves[1].isV() && board[target].getType() == getPieceInLeft(index) && board[target].getType() == getPieceInRight(index)) {
                     /*Verifica se ha pecas igual ao taget a sua esquerda e direita*/
                    moves[1].setV(true);
                    moves[1].setMoveType('l');
                    moves[1].setVct(index);
                    moves[1].setVct(index + 1);
                    moves[1].setVct(index - 1);
                }
                if (moves[1].getMovetype() != 'b') {
                    if (moves[1].getMovetype() == '1') {
                        moves[1].remove();
                    }
                    if (board[target].getType() == getPieceOnTop(index) && board[target].getType() == getPieceOnTop(index - 9)) {
                         /*Verifica se ha duas pecas igual ao taget em cima*/
                        if (moves[1].isV()) {
                            moves[1].setMoveType('2');
                        } else {
                            moves[1].setV(true);
                            moves[1].setMoveType('c');
                            moves[1].setVct(index);
                        }
                        moves[1].setVct(index - 9);
                        moves[1].setVct(index - 18);
                    }
                }
            }
        } else { //Caso a peca nao seja do tipo NormalPieceComponent, o movimento se torna automaticamente verdadeiro
            moves[1].setV(true);
            moves[1].setMoveType(board[target].getType());
        }
    }
}
