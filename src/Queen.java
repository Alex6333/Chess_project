public class Queen extends ChessPiece{
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            if (column == toColumn) {
                for (int i = Math.min(line, toLine); i < Math.max(line, toLine); i++) {
                    if (chessBoard.board[i][column] != null) {
                        if (chessBoard.board[i][column] == this && i == Math.max(line,toLine)) return false;
                        else if (chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                            return false;
                        else if (!chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                            return true;
                    }
                }
                if (chessBoard.board[toLine][column] != null) {
                    if (chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this)
                        return false;
                    else return !chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this;
                }
                else return true;
            } else if (line == toLine) {
                for (int i = Math.min(column, toColumn); i < Math.max(column, toColumn); i++) {
                    if (chessBoard.board[line][i] != null) {
                        if (chessBoard.board[line][i] == this && i == Math.max(column,toColumn)) return false;
                        else if (chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn)
                            return false;
                        if (!chessBoard.board[line][i].color.equals(this.color) && i == toColumn)
                            return true;
                    }
                }
                if (chessBoard.board[toLine][toColumn] != null) {
                    if (chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this)
                        return false;
                    else return !chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this;
                }
                else return true;
            }//слева сверху - направо вниз
            else if (Math.max(line, toLine) - Math.min(line, toLine) == Math.max(column, toColumn) - Math.min(column, toColumn)
                    && chessBoard.board[line][column] != null && (line != toLine && column != toColumn)
                    //конечная точка или пустая,
                    && (chessBoard.board[toLine][toColumn] == null
                    // или фигура другого цвета
                    || !chessBoard.board[toLine][toColumn].color.equals(this.color))){
                if ((line == Math.max(line, toLine) && column == Math.min(column, toColumn))
                        || toLine == Math.max(line, toLine) && column == Math.min(column, toColumn)) {
                    int fromL = Math.max(line, toLine);
                    int fromC = Math.min(column, toColumn);
                    int toL = Math.min(line, toLine);
                    int toC = Math.max(column, toColumn);
                    //позиции по пути слона
                    int[][] positions = new int[toC - fromC][1];
                    for (int i = 1; i < toC - fromC; i++) {
                        //на пути пусто
                        if (chessBoard.board[fromL - i][fromC + i] == null) {
                            positions[i - 1] = new int[]{fromL - i, fromC + i};
                            //в конечной точке фигура другого цвета
                        } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                            positions[i - 1] = new int[]{fromL - i, fromC + i};
                        } else {
                            return false;
                        }
                    }
                    return true;
                } else {
                    //справа сверху - налево вниз
                    int fromL = Math.max(line, toLine);
                    int fromC = Math.max(column, toColumn);
                    int toL = Math.min(line, toLine);
                    int toC = Math.min(column, toColumn);
                    //позиции по пути слона
                    int[][] positions = new int[fromC + toC][1];
                    for (int i = 1; i < fromC - toC; i++) {
                        //на пути пусто
                        if (chessBoard.board[fromL - i][fromC - i] == null) {
                            positions[i - 1] = new int[]{fromL - i, fromC - i};
                            //в конечной точке фигура другого цвета
                        } else if (!chessBoard.board[fromL - i][fromC - i].color.equals(this.color) && fromL - i == toLine) {
                            positions[i - 1] = new int[]{fromL - i, fromC - i};
                        } else {
                            return false;
                        }
                    }
                    return true;
                }}
            else return false;
        }
        else return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
