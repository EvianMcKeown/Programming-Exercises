class Solution2 {
    /*
     * GIVEN: m×n matrix containing letters 'X' or 'O'
     * TERMINOLOGY:
     * → a cell j is connected to a cell k ⇔ both j and k:
     * a) are (immediately) adjacent cells horizontally, or
     * b) are adjacent cells vertically.
     * 
     * → a set of cells is a region ⇔ every 'O' cell in the set:
     * 1) is connected.
     * 
     * → a region is surrounded ⇔ no 'O' cell in the region sits on the outer
     * boundary of the board.
     * One can also say, such regions are completely enclosed by 'X' cells.
     * 
     * TO DO:
     * → identify 'O' cells on boundary & dfs,
     * mark them with 'T', then go through board and mark all,
     * remaining 'O's to 'X', and 'T's back to 'O'.
     */

    private int[] k_i = { -1, 1, 0, 0 };
    private int[] k_j = { 0, 0, -1, 1 };

    public void solve(char[][] board) {

        // m x n matrix -> m rows, n columns
        // (0,0) (0,1) (0,2) (0,3) ... (0,n-1)
        // (1,0) (1,1) ...
        // (2,0) (2,1) ...
        // (3,0) (3,1) ...
        // ...
        // (m-1,0) ... (m-1,n-1)

        int m = board.length; // rows
        int n = board[0].length; // cols

        if (board == null || board.length == 0)
            return;

        // traverse boundary and dfs

        // top/bottom rows
        // iterate through cols
        int[] tbRows = { 0, m - 1 };
        for (int j = 0; j < n; j++) {
            for (int k : tbRows) {
                if (board[k][j] == 'O') {
                    markBoundaryRegions(k, j, m, n, board);
                }
            }
        }

        // left/right cols
        // iterate through rows
        int[] lrCols = { 0, n - 1 };
        for (int i = 0; i < m; i++) {
            for (int k : lrCols) {
                if (board[i][k] == 'O') {
                    markBoundaryRegions(i, k, m, n, board);
                }
            }
        }

        // mark all remaining 'O' -> 'X'
        // and change all 'T' back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char current = board[i][j];
                if (current == 'O') {
                    board[i][j] = 'X';
                } else if (current == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void markBoundaryRegions(int i, int j, int m, int n, char[][] board) {
        // dfs: find 'O' regions that are connected to the boundary
        // -> mark with 'T'

        // mark base cell
        board[i][j] = 'T';

        // explore connected cells
        for (int k = 0; k < 4; k++) {
            if (i + k_i[k] < m && i + k_i[k] >= 0 && j + k_j[k] < n && j + k_j[k] >= 0) {
                if (board[i + k_i[k]][j + k_j[k]] == 'O') {
                    markBoundaryRegions(i + k_i[k], j + k_j[k], m, n, board);
                }
            }
        }
    }
}
