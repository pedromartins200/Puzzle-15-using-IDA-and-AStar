import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        String aString = sc.next();
        String bString = sc.next();
        Puzzle15 puzzle_initial = new Puzzle15(aString);
        Puzzle15 puzzle_goal = new Puzzle15(bString);
        puzzle_initial.setGoal(bString);
        int iter = 0;


        //i5-3570 3.40ghz 4cpus
        //A Star is only able to solve to 21 steps.
        //Dont bother using it on the rest

        /*
        123456789ABCDEF0
		23401567ABC89DEF
		OUTPUT - 15
		*/

        /*
        23471568ABCF09DE
        123456789ABCDEF0
		OUTPUT - 21
		*/

		/*
		2F1C856B49A73ED0
		123456789ABCDEF0
		OUTPUT - 56   (27 MINUTES) - updated 6min 9s - updated 5min 51s -

		A10EF423B657CD89
        123456789ABCDEF0
        OUTPUT - 46 (3 seconds) - updated = 1second 50s - updated 641ms

        26741DA0BF5CE983
        123456789ABCDEF0
        OUTPUT - 44 (6 MINUTES) - updated = 4 minutes 46s - updated 3min 40s - updated 16s -- 14s

        D8A6E1F435CB7092
        123456789ABCDEF0
        OUTPUT - 48  (1 MINUTE) - updated - 6s - updated 3s -- updated 896ms


        287B504FD9E31A6C
        123456789ABCDEF0
        OUTPUT - 42 (2seconds)
		*/

		/*
		2F1C0856B49A73ED
		123456789ABCDEF0
		OUTPUT - 59 - updated 30min 9s
		*/

		/*
		123456789ABCDEF0
		0FEDCBA987654321
		OUTPUT - 78 - around 1 hour
		*/


        IDAStar s = new IDAStar();
        long startTime = System.currentTimeMillis();
        Iterator<IDAStar.State> it = s.solveIDAStar(puzzle_initial, puzzle_goal);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;



        int seconds = (int) (totalTime / 1000) % 60 ;
        int minutes = (int) ((totalTime / (1000*60)) % 60);
        int hours   = (int) ((totalTime / (1000*60*60)) % 24);

        if(it == null) {
            System.out.println("no solution found");
        } else {
            System.out.println("Total time: " + hours + "hours and " + minutes + "min and " + seconds + "s or " + totalTime + "ms");
            while (it.hasNext()) {
                IDAStar.State i = it.next();
                //System.out.println(i.toString());

                if (!it.hasNext()) {
                    System.out.println((int) iter + 1);
                }
                iter++;
            }

        }


        sc.close();
    }
}

