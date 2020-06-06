public abstract class Bonus implements IPieces{
    char type;

    public abstract void setType(int x);

    public abstract boolean verifyMovement(IMovementC xy);

    public char getType() {
        return type;
    }
}
