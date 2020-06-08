public class Bonus02Component extends Pieces {

    public void setType(int x) {
        type = '2';
    }

    public boolean verifyMovement(ITranslateMovementC xy, BoardComponent board) {
        moves[0].setV(true);
        moves[0].setMovetype(board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType());
        verifyTargetMovement(xy, board);
        return true;
    }
}
