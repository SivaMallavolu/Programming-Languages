import java.util.ArrayList;
import java.util.List;

class play_game {
    public int len;
    public List<Integer> game;
    List<int[]> directions;

    public int[][] turns = {{0, 1, 3}, {0, 2, 5}, {1, 3, 6}, {1, 4, 8}, {2, 4, 7}, {2, 5, 9}, {3, 4, 5}, {6, 7, 8}, {7, 8, 9}};

    public play_game(List<Integer> board, int n) {
        this.game = board;
        this.directions = new ArrayList<int[]>();
        this.len = n;
    }

    public int possible_helper(List<Integer> board, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (board.get(i) == 1) {
                count++;
            }
        }
        return count;
    }


    public boolean poss_movements(List<Integer> board, int f, int x, int t) {
        if (board.get(f) == 1 && board.get(x) == 1 && board.get(t) == 0 && f != x && x != t && t != f) {
            int[] x1 = {f, x, t};
            int[] x2 = {t, x, f};
            for (int i = 0; i < this.turns.length; i++){
                for(int j=0;j<3;j++){
                    if(this.turns[i][j] != x1[j])
                        break;
                    if(j == 2) return true;
                }
                for(int j=0;j<3;j++){
                    if(this.turns[i][j] != x2[j])
                        break;
                    if(j == 2) return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean is_possible(List<Integer> board, int size, List<int[]> state) {
        int count = possible_helper(board, size);
        if (count == 1) {
            this.directions = state;
            return true;
        } else {
            for (int i = 0; i < size; i++) {
                if (board.get(i) == 0)
                    continue;
                for (int j = 0; j < size; j++) {
                    if (board.get(j) == 0)
                        continue;
                    for (int k = 0; k < size; k++) {
                        if (board.get(k) == 1)
                            continue;
                        if (poss_movements(board, i, j, k)) {
                            this.game.set(k,1);
                            this.game.set(i,0);
                            this.game.set(j,0);

                            int[] a = {i,j,k};
                            boolean nex = is_possible(this.game, size, state);
                            if (nex) {
                                state.add(a);
                                return true;
                            } else {
                                this.game.set(i,1);
                                this.game.set(j,1);
                                this.game.set(k,0);
                                continue;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
}
