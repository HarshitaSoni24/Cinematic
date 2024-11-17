package src.pkg;


public class Seats {
    private char[][] seatLayout;
    private static final int ROWS = 5;
    private static final int COLUMNS = 10;

    public Seats() {
        seatLayout = new char[ROWS][COLUMNS];
        initializeSeats();
    }

    // Initialize the seats to be available ('O' for open seat)
    private void initializeSeats() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                seatLayout[i][j] = 'O'; // 'O' means the seat is open
            }
        }
    }

    // Display the seating arrangement
    public void displaySeats() {
        System.out.println("\n     *** Screen ***\n");
        System.out.println("    1 2 3 4 5  6 7 8 9 10");
        System.out.println("   ---------------------");

        for (int i = 0; i < ROWS; i++) {
            System.out.print((char) ('A' + i) + " | "); // Row labels (A, B, C, ...)
            for (int j = 0; j < COLUMNS; j++) {
                if (j == 5) {
                    System.out.print(" "); // Gap between the fifth and sixth seat
                }
                System.out.print(seatLayout[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n'O' = Open, 'X' = Booked");
    }

    // Book a seat (row: A-E, column: 1-10)
    public boolean bookSeat(String seat) {
        int row = seat.charAt(0) - 'A'; // Convert 'A'-'E' to 0-4
        int col = Integer.parseInt(seat.substring(1)) - 1; // Convert '1'-'10' to 0-9

        if (row < 0 || row >= ROWS || col < 0 || col >= COLUMNS) {
            System.out.println("Invalid seat selection.");
            return false;
        }

        if (seatLayout[row][col] == 'X') {
            System.out.println("Seat " + seat + " is already booked.");
            return false;
        } else {
            seatLayout[row][col] = 'X'; // 'X' means the seat is booked
            System.out.println("Seat " + seat + " booked successfully.");
            return true;
        }
    }
}
