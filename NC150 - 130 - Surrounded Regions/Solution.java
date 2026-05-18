import java.util.List;
import java.util.ArrayList;

class Solution {
    public void solve(char[][] board) {
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
         * → identify surrounded regions (find connected 'O' cells not on outer
         * boundary), then
         * → for every surrounded region, replace all 'O's with 'X's.
         */

        if (board == null || board.length == 0) return;
        
        int m = board.length;
        int n = board[0].length;

        // visited matrix as 1D array: bijective mapping f(i, j) = i * n + j
        boolean[] visited = new boolean[m * n];

        // find surrounded regions
        for (int i = 1; i < m - 1; i++) {
            // exclude first and last rows
            for (int j = 1; j < n - 1; j++) {
                // exclude first and last cols

                int matToArr = i*n + j;

                if (board[i][j] == 'O' && !visited[matToArr]) {
                    // found base cell of unvisited region
                    List<Integer> currentRegion = new ArrayList<>();

                    // track if transitive chain touches boundary
                    // use boolean array so that we can pass primitive by reference, not by value!
                    boolean[] touchesBoundary = {false};

                    // do dfs to find remainder of region
                    dfs(i, j, board, visited, currentRegion, touchesBoundary);
                }
            }
        }
    }

    private void dfs(int i, int j, char[][] board, boolean[] visited, List<Integer> currentRegion, boolean[] touchesBoundary) {
        // TODO

        // now probe t,b,l,r cells
        // & mark as visitied
        for (int k = -1; k <= 1; k = k + 2) {
            // horizontal shift
            if (board[i][j + k] == 'O') {

            }
            // vertical shift
            if (board[i + k][j] == 'O') {

            }
        }
    }
}