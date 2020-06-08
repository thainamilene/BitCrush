public class Bonus01Component extends Pieces {

    public void setType(int x) {
        this.type = '1';
    }

    @Override
    public boolean verifyMovement(ITranslateMovementC xy, BoardComponent board) {
        moves[0].setV(true);
        moves[0].setMovetype(board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType());
        verifyTargetMovement(xy, board);
        return true;
    }
}