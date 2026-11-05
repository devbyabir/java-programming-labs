public class TwoDimensionalArray {
    public static void main(String[] args) {

        int[][] array = {
            {11, 13, 15},
            {17, 19, 21},
            {23, 25, 27}
        };


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
