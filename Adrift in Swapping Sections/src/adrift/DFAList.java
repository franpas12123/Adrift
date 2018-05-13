package adrift;

import java.util.ArrayList;
import java.util.Random;

public class DFAList {

    ArrayList<Integer> dfaList[][][];
    final int max = 4;

    public DFAList() {
        dfaList = new ArrayList[max][max][1];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                for (int k = 0; k < 1; k++) {
                    dfaList[i][j][k] = new ArrayList<>();
//                    System.out.println(dfaList[i][j][k]);
                }
//                System.out.println();
            }
//            System.out.println();
        }

//        dfaList[0][1].possibleNext = {1,2,3,4};
        dfaList[0][0][0].add(1);
        dfaList[0][0][0].add(2);
        dfaList[0][0][0].add(3);
        dfaList[0][0][0].add(4);

//        dfaList[1][0].possibleNext = {4,5};
        dfaList[1][0][0].add(4);
        dfaList[1][0][0].add(5);

//        dfaList[2][0].possibleNext = {1,4,5};
        dfaList[2][0][0].add(1);
        dfaList[2][0][0].add(4);
        dfaList[2][0][0].add(5);

//        dfaList[3][0].possibleNext = {4,5};
        dfaList[3][0][0].add(4);
        dfaList[3][0][0].add(5);

//        dfaList[0][2].possibleNext = {1,2,3};
        dfaList[0][1][0].add(1);
        dfaList[0][1][0].add(2);
        dfaList[0][1][0].add(3);

//        dfaList[0][3].possibleNext = {1,2,3};
        dfaList[0][2][0].add(1);
        dfaList[0][2][0].add(2);
        dfaList[0][2][0].add(3);

//        dfaList[1][1].possibleNext = {2,5};
        dfaList[1][1][0].add(2);
        dfaList[1][1][0].add(5);

//        dfaList[2][1].possibleNext = {1};
        dfaList[2][1][0].add(1);

//        dfaList[3][1].possibleNext = {4};
        dfaList[3][1][0].add(4);

//        dfaList[1][2].possibleNext = {1,4,5};
        dfaList[1][2][0].add(1);
        dfaList[1][2][0].add(4);
        dfaList[1][2][0].add(5);

//        dfaList[2][2].possibleNext = {1,3,4};
        dfaList[2][2][0].add(1);
        dfaList[2][2][0].add(3);
        dfaList[2][2][0].add(4);

//        dfaList[3][2].possibleNext = {2,4};
        dfaList[3][2][0].add(2);
        dfaList[3][2][0].add(4);

//        dfaList[0][4].possibleNext = {1,2,3};
        dfaList[0][3][0].add(1);
        dfaList[0][3][0].add(2);
        dfaList[0][3][0].add(3);

//        dfaList[1][3].possibleNext = {2,3,5};
        dfaList[1][3][0].add(2);
        dfaList[1][3][0].add(3);
        dfaList[1][3][0].add(5);

//        dfaList[2][3].possibleNext = {2,3,5};
        dfaList[2][3][0].add(2);
        dfaList[2][3][0].add(3);
        dfaList[2][3][0].add(5);

//        dfaList[3][3].possibleNext = {2,4,5};
        dfaList[3][3][0].add(2);
        dfaList[3][3][0].add(4);
        dfaList[3][3][0].add(5);
    }

    public void print() {
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                for (int k = 0; k < 1; k++) {
                    System.out.println(dfaList[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public int getNewMap(int x, int y) {
        Random rand = new Random();
        int newMap = rand.nextInt(dfaList[x][y][0].size());
        return dfaList[x][y][0].get(newMap);
    }
}
