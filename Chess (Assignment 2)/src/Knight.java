public class Knight extends Piece {

    // Checks whether the piece is allowed to do this kind of move; Staying in bounds and capture of potential pieces has to be checked by game class.
    public boolean isValid(Column fromX, Row fromY, Column toX, Row toY) {
        if(!super.isValid(fromX, fromY, toX, toY)) {
            return false;
        }
        if(toX.column_number != fromX.column_number - 1 && toX.column_number != fromX.column_number + 1 && toX.column_number != fromX.column_number + 2 && toX.column_number != fromX.column_number - 2) {
            return false; }
        if(toY.row_number != fromY.row_number - 2 && toY.row_number != fromY.row_number + 2 && toY.row_number != fromY.row_number - 1 && toY.row_number != fromY.row_number + 1) {
            return false; }
        if((toX.column_number - 1 == fromX.column_number && toY.row_number -1 == fromY.row_number) || (toX.column_number - 2 == fromX.column_number && toY.row_number -2 == fromY.row_number)) {
            return false; }
        if((toX.column_number + 1 == fromX.column_number && toY.row_number +1 == fromY.row_number) || (toX.column_number + 2 == fromX.column_number && toY.row_number +2 == fromY.row_number)) {
            return false; }
        if((toX.column_number - 1 == fromX.column_number && toY.row_number +1 == fromY.row_number) || (toX.column_number - 2 == fromX.column_number && toY.row_number +2 == fromY.row_number)) {
            return false; }
        if((toX.column_number + 1 == fromX.column_number && toY.row_number -1 == fromY.row_number) || (toX.column_number + 2 == fromX.column_number && toY.row_number -2 == fromY.row_number)) {
            return false; }
        return true;
        }
    }
