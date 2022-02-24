public class Bishop extends ChessPiece {

    public Bishop(String color) {
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
                //в этой клетке есть фигура и стартовая клетка не равна конечной
                && chessBoard.board[line][column] != null && (line != toLine && column != toColumn)
                //конечная точка или пустая,
                && (chessBoard.board[toLine][toColumn] == null
                // или фигура другого цвета
                || !chessBoard.board[toLine][toColumn].color.equals(this.color))
                //ход по диагонали
                && Math.max(line, toLine) - Math.min(line, toLine) == Math.max(column, toColumn) - Math.min(column, toColumn)) {
            //слева сверху - направо вниз
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
            }
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
