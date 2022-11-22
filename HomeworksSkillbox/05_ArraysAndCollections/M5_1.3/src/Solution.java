public class Solution {
    public static void main(String[] args) {
       /*           0   1   2   3   4   5   6  j
                0 {"X"," "," "," "," "," ","X"},// 0
              i 1 {" ","X"," "," "," ","X"," "},// 1
                2 {" "," ","X"," ","X"," "," "},// 2
                3 {" "," "," ","X"," "," "," "},// 3
                4 {" "," ","X"," ","X"," "," "},// 4
                5 {" ","X"," "," "," ","X"," "},// 5
                6 {"X"," "," "," "," "," ","X"},// 6  */
        String number[][] = new String[7][7];
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < number.length; j++) {
                if (i == j) {
                    number[i][j] = "X";
                } else if (number.length - 1 - i == j) {
                    number[i][j] = "X";
                } else {
                    number[i][j] = " ";
                }
            }
        }
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < number.length; j++) {
                System.out.print(number[i][j]);
            }
            System.out.println();
        }

    }
}






