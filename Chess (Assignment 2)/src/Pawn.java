public class Pawn extends Piece {

    public Pawn (Row pRow, Column pColumn, Color pColor){
        super();
        this.y=pRow;
        this.x=pColumn;
        this.color=pColor;
        this.number_of_moves=0;
    }

    // Checks whether the piece is allowed to do this kind of move; Staying in bounds and capture of potential pieces has to be checked by game class.
    public boolean isValid(Column fromX, Row fromY, Column toX, Row toY) {
        if (!super.isValid(fromX, fromY, toX, toY)) {
            return false;
        }
        if (number_of_moves == 0) {
            if (color == Color.WHITE) {
                return toX.column_number == fromX.column_number && toY.row_number == fromY.row_number + 1 || toX.column_number == fromX.column_number && toY.row_number == fromY.row_number + 2;
            }
            if (color == Color.BLACK) {
                return toX.column_number == fromX.column_number && toY.row_number == fromY.row_number - 1 || toX.column_number == fromX.column_number && toY.row_number == fromY.row_number - 2;
            }
        } else {
            if (color == Color.WHITE) {
                return toX.column_number == fromX.column_number && toY.row_number == fromY.row_number + 1;
            }
            if (color == Color.BLACK) {
                return toX.column_number == fromX.column_number && toY.row_number == fromY.row_number - 1;
            }
        }
        return false;
    }
}
