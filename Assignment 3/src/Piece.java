import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Piece implements ObservablePieces{
    protected Row y;
    protected Column x;
    protected Color color;
    protected int number_of_moves;
    private List<ObserverPieces> subscribed_observers= new ArrayList<ObserverPieces>();

    public Piece(Piece p) {
        this.y = p.getRow();
        this.x = p.getColumn();
        this.color = p.getColor();
        this.number_of_moves = p.getNumber_of_moves();
    }

    public Piece() {
    }

    //not moving anything is not a valid move; going out of bounds is not a valid move; capturing King is not a valid move; capturing a piece of your own is not a valid move
    public boolean isValid(Gameboard gb, Column toX, Row toY) {
        if(toX.column_number > 8 || toY.row_number > 8 || toX.column_number < 1 || toY.row_number < 1) {
            return false;
        }
        if(gb.getPiece(toX, toY) != null) {
            if(gb.getPiece(toX, toY).getColor() == color) {
                return false;
            }
        }
        //checks if this move undoes check in case of checked King or places the king in check in the first place
        Gameboard gb2 = new Gameboard(gb);
        for (int i = 0; i < gb2.getPieces().size(); i++) {
          if (gb2.getPieces().get(i).getColor() == color && gb2.getPieces().get(i) instanceof King) {
              if (gb2.getPiece(toX, toY) != null) {
                  gb2.Pieces.remove(gb2.getPiece(toX, toY));
                  gb2.getPiece(x, y).setPosition(toX, toY);
                  gb2.getField(x, y).unoccupy();
                  gb2.getField(toX, toY).occupy();
              } else {
                  gb2.getPiece(x, y).setPosition(toX, toY);
                  gb2.getField(x, y).unoccupy();
                  gb2.getField(toX, toY).occupy();

              }
          }
        }
        for (int l = 0; l < gb2.getPieces().size(); l++) {
            if (gb2.getPieces().get(l).getColor() == color && gb2.getPieces().get(l) instanceof King) {
                if (gb2.getPieces().get(l).checkIfChecked(gb2)) {
                    return false;
                }
            }
        }
        return toX.column_number != x.column_number || toY.row_number != y.row_number;
    }

    public Color getColor() {
        return color;
    }

    public Column getColumn() {
        return x;
    }

    public Row getRow() {
        return y;
    }

    public int getNumber_of_moves() {
        return number_of_moves;
    }

    public void setPosition(Column x, Row y) {
        this.y = y;
        this.x = x;
        number_of_moves ++;
    }
    public boolean checkIfChecked(Gameboard gb) {
        return false;
    }

    @Override
    public void registerObserver(ObserverPieces observerPieces) {subscribed_observers.add(observerPieces); }

    @Override
    public void removeObserver(ObserverPieces observerPieces) {
        for (int i=0 ; i<subscribed_observers.size(); i++){
            if (subscribed_observers.get(i) == observerPieces) subscribed_observers.remove(i);
        }
    }

    @Override
    public void notifyObserver(Piece dead_piece) {
        for (int i=0 ; i<subscribed_observers.size(); i++){
            subscribed_observers.get(i).update(dead_piece);
        }
    }
}