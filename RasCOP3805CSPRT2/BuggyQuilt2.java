public class BuggyQuilt2 {

    public static void main(String[] args) {
        char[][] myBlock = {
                { 'x', '.', '.', '.', '.' },
                { 'x', '.', '.', '.', '.' },
                { 'x', '.', '.', '.', '.' },
                { 'x', 'x', 'x', 'x', 'x' }
        };
        char[][] myQuilt = new char[3 * myBlock.length][4 * myBlock[0].length];

        createQuilt(myQuilt, myBlock);

        displayPattern(myQuilt);
    }

    public static void displayPattern(char[][] myArray) {
        for (int r = 0; r < myArray.length; r++) {
            for (int c = 0; c < myArray[0].length; c++) {
                System.out.print(myArray[r][c]);
            }
            System.out.println(); // Add a line break after each row
        }
    }

    public static void createQuilt(char[][] quilt, char[][] block) {
        char[][] flippedBlock = createFlipped(block);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                if (((r + c) % 2) == 0) {
                    placeBlock(quilt, block, c * block[0].length, r * block.length);
                } else {
                    placeBlock(quilt, flippedBlock, c * block[0].length, r * block.length);
                }
            }
        }
    }

    public static void placeBlock(char[][] quilt, char[][] block, int startRow, int startCol) {
        for (int r = 0; r < block.length; r++) {
            for (int c = 0; c < block[r].length; c++) {
                quilt[r + startRow][c + startCol] = block[r][c];
            }
        }
    }

    public static char[][] createFlipped(char[][] block) {
        int blockRows = block.length;
        int blockCols = block[0].length;
        char[][] flipped = new char[blockRows][blockCols];

        int flippedRow = blockRows - 1;
        for (int row = 0; row < blockRows; row++) {
            for (int col = 0; col < blockCols; col++) {
                flipped[flippedRow][col] = block[row][col];
            }
            flippedRow--;
        }

        return flipped;
    }
}
