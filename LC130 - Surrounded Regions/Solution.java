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
         * → identify surrounded regions, then
         * → for every surrounded region, replace all 'O's with 'X's.
         */

        if (board == null || board.length == 0)
            return;

        int m = board.length, n = board[0].length;

        // visited matrix as 1D array: bijective mapping f(i, j) = (i * n) + j = k
        // f'(k) = (k / n, k % n) = (i, j)
        boolean[] visited = new boolean[m * n];

        // find surrounded regions
        for (int i = 1; i < m - 1; i++) {
            // exclude first and last rows
            for (int j = 1; j < n - 1; j++) {
                // exclude first and last cols

                int matToArr = i * n + j;
                if (board[i][j] == 'O' && !visited[matToArr]) {
                    // ̶m̶a̶r̶k̶ ̶v̶i̶s̶i̶t̶e̶d̶ -> moved to in dfs(...)
                    // visited[(i * n) + j] = true;

                    // found base cell of unvisited region
                    List<Integer> currentRegion = new ArrayList<>();

                    // track if transitive chain touches boundary
                    // use boolean array so that we can pass primitive by reference, not by value!
                    boolean[] touchesBoundary = { false };

                    // do dfs to find remainder of region
                    dfs(i, j, board, visited, currentRegion, touchesBoundary);

                    if (!touchesBoundary[0]) {
                        // change all 'O' to 'X' in currentRegion
                        for (Integer cellPos : currentRegion) {
                            int k_i = cellPos / n;
                            int k_j = cellPos % n;
                            board[k_i][k_j] = 'X';
                        }
                    }
                }
            }
        }
    }

    private void dfs(int i, int j, char[][] board, boolean[] visited, List<Integer> currentRegion,
            boolean[] touchesBoundary) {

        int m = board.length;
        int n = board[0].length;

        // add current cell to region & mark visited
        currentRegion.add((i * n) + j);
        visited[(i * n) + j] = true;

        // Check if THIS cell is on the boundary
        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
            touchesBoundary[0] = true;
        }

        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };

        // recursively search
        for (int k = 0; k < 4; k++) {
            int ni = i + dr[k];
            int nj = j + dc[k];

            // bounds check
            if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                if (board[ni][nj] == 'O' && !visited[ni * n + nj]) {
                    dfs(ni, nj, board, visited, currentRegion, touchesBoundary);
                }
            }
        }
    }
}