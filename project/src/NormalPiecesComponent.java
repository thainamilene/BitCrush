public class NormalPiecesComponent implements IPieces{
    char type;

    //to do
    public boolean verifyMovement(IMovementC xy) {
        return false;
    }

    public void setType(int x) {
        if (x == 0) {
            this.type = '+';
        } else if (x == 1) {
            this.type = '*';
        } else if (x == 2) {
            this.type = '@';
        } else if (x == 3) {
            this.type = '$';
        } else if (x == 4) {
            this.type = '#';
        } else if (x == 5) {
            this.type = '?';
        } else if (x == 6) {
            this.type = '&';
        } else if (x == 7) {
            this.type = '§';
        } else {
            this.type = 'º';
        }

    }
    public char getType() {
        return type;
    }
}
