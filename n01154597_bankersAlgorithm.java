/* Written By: Afnan Sultan (n01154597)
   COP4610 - Dr. Ahuja
   Date Last Modified: 4/14/2020
 */
import java.util.Arrays;
import java.util.Scanner;

public class n01154597_bankersAlgorithm {

    private static int n = 5; // Default number of processes
    private static int m = 3; // Default number of resources
    private static int need[][] = new int[n][m];
    private static int[][] max;
    private static int[][] allocation;
    private static int[] available;
    private static int[] safeSequence = new int[n];

    private static void printSafeSequence() {
        int count = 0;

        boolean visited[] = new boolean[n];
        Arrays.fill(visited, false);

        // Make temporary copy of available
        int[] avail = new int[m];
        for(int i=0; i<m; i++) {
            avail[i] = available[i];
        }

        while( count < n ) {
            boolean flag = false;
            for(int i=0; i<n; i++) {
                if ( !visited[i] ) {
                    int j;
                    for(j=0; j<m; j++) {
                        // Stop immediately if need is larger than available vector
                        if (need[i][j] > avail[j]) {
                            break;
                        }
                    }
                    if ( j == m ) {
                        // Safe sequence if we iterate through all rows successfully
                        safeSequence[count++] = i;
                        visited[i] = true;
                        flag = true;
                        // Increment available vector with current row of allocation matrix
                        for(j=0; j<m; j++) {
                            avail[j] = avail[j] + allocation[i][j];
                        }
                    }
                }
            }
            // Jump to next iteration because we already visited and marked as safe
            if ( !flag ) {
                break;
            }
        }
        if ( count < n ) {
            System.out.println("The system is unsafe!");
        } else {
            System.out.print("Safe sequence: ");
            System.out.print("<");
            for(int i=0; i<n; i++) {
                if (i == safeSequence.length-1) {
                    System.out.print("p" + safeSequence[i]);
                } else {
                    System.out.print("p" + safeSequence[i] + ",");
                }
            }
            System.out.print(">");
        }
    }

    private static void printNeedMatrix() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of processes (typically 5): ");
        n = input.nextInt();

        System.out.print("Enter the number of resources (typically 3): ");
        m = input.nextInt();

        input.nextLine();

        System.out.print("Enter the available vector (typically 1x3 dimensions): ");
        String[] arr0 = input.nextLine().split("\\s+");
        available = new int[arr0.length];
        for(int i=0; i<available.length; i++) {
            available[i] = Integer.parseInt(arr0[i]);
        }

        System.out.println("Enter the values for the allocation matrix (typically 5x3 dimensions): ");
        allocation = new int[n][m];
        for(int i=0; i<n; i++) {
            System.out.print("p" + i + ": ");
            for(int j=0; j<m; j++) {
                allocation[i][j] = input.nextInt();
            }
        }

        System.out.println("Enter the values for the max matrix (typically 5x3 dimensions): ");
        max = new int[n][m];
        for(int i=0; i<n; i++) {
            System.out.print("p" + i + ": ");
            for(int j=0; j<m; j++) {
                max[i][j] = input.nextInt();
            }
        }
        input.nextLine();

        System.out.print("Enter the process number to request for (0-4): ");
        int processNumber = Integer.valueOf(input.nextLine());

        System.out.print("Enter the request vector (typically 1x3 dimensions): ");
        String[] arr2 = input.nextLine().split("\\s+");
        int[] request = new int[arr2.length];
        for(int i=0; i<arr2.length; i++) {
            request[i] = Integer.parseInt(arr2[i]);
        }

        printNeedMatrix();
        printSafeSequence();

    }
}
