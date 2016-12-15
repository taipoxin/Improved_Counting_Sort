import java.util.*;



/**

CountingSort
Improved and standard implementations

Improved Counting Sort:
    *same or less used memory (arrays from min to max versus arrays from 1 to max)
    *same complexity
    *can works with negative numbers
    *almost same running time (added only one O(n) loop to find min and max values)
*/


    /**
     *
     function counting_sort
     for i = 0 to k - 1
        C[i] = 0
     for i = 0 to n - 1
        C[A[i]] = C[A[i]] + 1
     b = 0
     for j = 0 to k - 1
        for i = 0 to C[j] - 1
            A[b] = j
            b = b + 1
     *
     */

public class GreatSorter {

    // Improved
    static void onesort(int[] A) {
        int len;
        int min = A[0];
        int max = A[0];
        // find min and max elements
        for (int i = 1; i < A.length; i++) {        // O(n)
            if (A[i] < min) {
                min = A[i];
            }
            else if (A[i] > max) {
                max = A[i];
            }
        }
        // длина промежуточного массива
        // additional array's length
        len = max - min + 1;

        int[] count = new int[len];

        // filling
        for (int i = 0; i < A.length; i++) {        // O(n)
            count[A[i] - min]++;
        }

        int i = 0;
        for (int j = min; j <= max; j++) {          // O(cnt.len * y), cnt.len - количество элементов в массиве
                                                    // y - среднее кол-во повторений
            int index = count[j-min];
            if (index > 0) {
                for (int k = 0; k < index; k++) {
                    A[i] = j;
                    i++;
                }
            }
        }
    }

    // standard sort
    static void badSort(int[] A) {
        int max = A[0];  // 1
        for (int k = 1; k < A.length; k++) {    // O(n) 2 <=
            if (A[k] > max) {
                max = A[k];
            }
        }
        int[] count = new int[max+1];
        for (int i = 0; i < A.length; i++) {    // O(n) 1 <=
            count[A[i]]++;
        }
        int b = 0;  // 1
        for (int j = 0; j <= max; j++) {        // O((max+1)*y),
                                                // где max+1 - количество элемементов в доп. массиве
                                                //     y - среднее количество повторений
            for (int i = 0; i < count[j]; i++) {
                A[b] =  j;
                b++;
            }
        }
    }





    // Testing both sorts
    public static void main(String[] args) {
/*

        int [] a = {5,4,3,2,2,1,1};
        System.out.println(Arrays.toString(a));
        GreatSort.onesort(a);
        System.out.println(Arrays.toString(a));
        int [] b = {-3,5,2,1,9,12,-3,6};
        System.out.println(Arrays.toString(b));
        GreatSort.onesort(b);
        System.out.println(Arrays.toString(b));
        int [] c = {995, 996, 995,993, 990, 999, 1000};
        System.out.println(Arrays.toString(c));
        GreatSort.onesort(c);
        System.out.println(Arrays.toString(c));

*/
        System.out.println("\nFirst time - standard sort\nSecond time - improved sort\n");
        long start, end;
        int [] a = {33, 77, 44, 22, 22, 22, 1, 44, 33, 77};
        int [] a2= {33, 77, 44, 22, 22, 22, 1, 44, 33, 77};
        start = System.nanoTime();
        GreatSorter.badSort(a);
        end = System.nanoTime();
        System.out.println(Arrays.toString(a));
        System.out.println(end - start);

        start = System.nanoTime();
        GreatSorter.onesort(a2);
        end = System.nanoTime();
        System.out.println(Arrays.toString(a2));
        System.out.println(end - start);
        System.out.println("Array from 1 to 10000 in desc order");
        int[] b1 = new int[10000];
        int[] b2 = new int[10000];
        for (int i = 10000; i > 0; i--) {
            b1[10000-i] = i;
            b2[10000-i] = i;
        }

        start = System.nanoTime();
        GreatSorter.badSort(b1);
        end = System.nanoTime();
        System.out.println(end - start);

        start = System.nanoTime();
        GreatSorter.onesort(b2);
        end = System.nanoTime();
        System.out.println(end - start);
        System.out.println("Array from 1 to 10000000 in desc order");
        int[] с1 = new int[10000000];
        int[] с2 = new int[10000000];
        for (int i = 10000000; i > 3; i= i-3) {
            с1[10000000-i] = i;
            с1[10000001-i] = i;
            с1[10000002-i] = i;
            с2[10000000-i] = i;
            с2[10000001-i] = i;
            с2[10000002-i] = i;
        }


        start = System.nanoTime();
        GreatSorter.badSort(с1);
        end = System.nanoTime();

        System.out.println(end - start);

        start = System.nanoTime();
        GreatSorter.onesort(с2);
        end = System.nanoTime();

        System.out.println(end - start);
        System.out.println("Array from 500000 to 40000000 in desc order with three equal elements");
        int[] d1 = new int[40000000];       //максимум 40000000 при значениях выше просто перегружается хип
        int[] d2 = new int[40000000];
        for (int i = 40000000; i > 500000; i= i-3) {
            d1[40000000-i] = i;
            d1[40000001-i] = i;
            d1[40000002-i] = i;
            d2[40000000-i] = i;
            d2[40000001-i] = i;
            d2[40000002-i] = i;
        }
        //System.out.println(Arrays.toString(d1));    // [10000000, 10000000, 10000000, 9999997, 9999997...]

        start = System.nanoTime();
        GreatSorter.badSort(d1);
        end = System.nanoTime();

        System.out.println(end - start);

        start = System.nanoTime();
        GreatSorter.onesort(d2);
        end = System.nanoTime();

        System.out.println(end - start);
        System.out.println("Array from 500000 to 40000000 in desc order with five equal elements");
        int[] f1 = new int[4000000];
        int[] f2 = new int[4000000];
        for (int i = 4000000; i > 500000; i= i-5) {
            f1[4000000-i] = i;
            f1[4000001-i] = i;
            f1[4000002-i] = i;
            f1[4000003-i] = i;
            f1[4000004-i] = i;
            f2[4000000-i] = i;
            f2[4000001-i] = i;
            f2[4000002-i] = i;
            f2[4000003-i] = i;
            f2[4000004-i] = i;
        }
        //System.out.println(Arrays.toString(d1));    // [10000000, 10000000, 10000000, 9999997, 9999997...]

        start = System.nanoTime();
        GreatSorter.badSort(f1);
        end = System.nanoTime();

        System.out.println(end - start);

        start = System.nanoTime();
        GreatSorter.onesort(f2);
        end = System.nanoTime();
        System.out.println(end - start);

        // for running in console
        new Scanner(System.in).nextLine();



    }


}
