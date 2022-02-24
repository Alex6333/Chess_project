public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //координаты существуют
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)
                //в этой клетке есть фигура
                && chessBoard.board[line][column] != null) {
            //не наискосок, не рубим
            if (column == toColumn) {
                int dir;
                int start;
                if (color.equals("White")) {
                    dir = 1;//направление хода белых
                    start = 1;//старт белой пешки
                } else {
                    dir = -1;//направление хода черных
                    start = 6;//старт черной пешки
                }
                //проверка, можно ли сходить в конечную клетку
                if (line + dir == toLine) {
                    //если клетка свободна, то вернет true
                    return chessBoard.board[toLine][toColumn] == null;
                }
                //если стартовая линия и ходим на 2 клетки
                if (line == start && line + 2 * dir == toLine) {
                    //если конечная клетка свободна и на пути нет фигур, то вернет true
                    return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + dir][column] == null;
                }
            } else {
                if (Math.abs(column - toColumn) == 1 && Math.abs(line - toLine) == 1
                        && chessBoard.board[toLine][toColumn] != null) {
                    return !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
                } else return false;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
