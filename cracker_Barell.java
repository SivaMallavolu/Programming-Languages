import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class cracker_Barell {
    public cracker_Barell(String filename) throws IOException {
        int size;
        List<Integer> mylist = new ArrayList<>();
        FileWriter output = new FileWriter(filename);
        for(int j=0; j<10; j++){
        for(int i=0; i<10; i++){
            if(i == j)
                mylist.add(0);
            else
                mylist.add(1);
        }
            List<Integer> game = (ArrayList) ((ArrayList<Integer>) mylist).clone();
            List<int[]> moves = new ArrayList<>();
            play_game play_game = new play_game(mylist, 10);
            boolean completed = play_game.is_possible(mylist,10,moves);
            if(completed) {
                System.out.println("Game solved for initial position = " + j);
                output.write("Solving for I = " + j + "\n");
                Collections.reverse(play_game.directions);
                List<int[]> directions = play_game.directions;
                ArrayList<String> list = null;
                String Result = null;
                for (int[] x : directions) {
                    list = new ArrayList<String>();
                    for (int i = 0; i < 10; i++) {
                        if (game.get(i) == 0)
                            list.add(".");
                        else
                            list.add("*");
                    }
                    Result = "   " + list.get(0) + "\n" + "  " + list.get(1) + " " + list.get(2) + "\n" + " " + list.get(3) + " " + list.get(4) + " " + list.get(5) + "\n" + list.get(6) + " " + list.get(7) + " " + list.get(8) + " " + list.get(9) + "\n\n";
                    output.write(Result);
                    game.set(x[0], 0);
                    game.set(x[1], 0);
                    game.set(x[2], 1);
                }
                list = new ArrayList<String>();
                for (int i = 0; i < 10; i++) {
                    if (game.get(i) == 0)
                        list.add(".");
                    else
                        list.add("*");
                }
                Result = "   " + list.get(0) + "\n" + "  " + list.get(1) + " " + list.get(2) + "\n" + " " + list.get(3) + " " + list.get(4) + " " + list.get(5) + "\n" + list.get(6) + " " + list.get(7) + " " + list.get(8) + " " + list.get(9) + "\n\n";
                output.write(Result);
            }
            output.write("\n");
            mylist.clear();
        }
        output.close();
    }
}
