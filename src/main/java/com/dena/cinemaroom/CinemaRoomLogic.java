package com.dena.cinemaroom;

import java.util.Scanner;

public class CinemaRoomLogic {
    protected final static Scanner scan=new Scanner(System.in);
    private static char[][] cinemaHall;
    private static int currentIncome = 0;
    private static int ticketCount = 0;
    private static int rowNumber;
    private static int seatNumber;
    protected CinemaRoomLogic() {

    }

    protected  void createCinema(){
        System.out.println("Enter the number of rows: ");
        rowNumber = scan.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        seatNumber = scan.nextInt();

        cinemaHall = new char[rowNumber][seatNumber];
        for(int i=0; i < rowNumber; ++i){
            for(int j=0; j < seatNumber; ++j){
                cinemaHall[i][j] = 'S';
            }
        }
    }
    protected  void getTicketPrice(){

        System.out.println("Enter a row number:");
        int customerRowNumber = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        int customerSeatNumber = scan.nextInt();

        boolean reply;
        reply = notOutOfBounds(customerRowNumber, customerSeatNumber);//this will call the notOutOfBounds() to check if the coordinates are within the cinema room

        if (reply) {//this will execute only when the reply is TRUE

            boolean booked = isBooked(customerRowNumber, customerSeatNumber);//isBooked() is called to check if that seat has already been taken
            while(booked){
                System.out.printf("Sorry,seat %d in Row %d is already booked%n", customerSeatNumber, customerRowNumber);
                System.out.println("If you would like to make another selection Press 1 or else press in any other number  to Return To Option Menu");
                int selection = scan.nextInt();
                if(selection == 1) {

                    System.out.println("Enter a row number:");
                    customerRowNumber = scan.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    customerSeatNumber = scan.nextInt();
                    booked = isBooked(customerRowNumber, customerSeatNumber);

                }else {
                    return;//this will return the user back to the selection interface
                }

            }
            if(customerRowNumber > 0 && customerSeatNumber > 0)++ticketCount;

            int totalSeatNumber = cinemaHall.length * cinemaHall[0].length;
            int frontHalfRow = cinemaHall.length / 2;

            int ticketPrice;

            if(totalSeatNumber <= 60) {
                ticketPrice = 10;
            } else if (customerRowNumber <= frontHalfRow){
                ticketPrice = 10;
            }else {
                ticketPrice = 8;
            }
            System.out.println("Ticket Price: $"+ ticketPrice);
            currentIncome += ticketPrice;
            cinemaHall[customerRowNumber -1][customerSeatNumber-1] ='B';//this will update the cinemaHall after a seat has been purchased
        } else {
            System.out.printf("Sorry, Seat Selected is out of range; There are only %d Rows and %d Seats in the cinema%n",rowNumber,seatNumber);
        }


    }
    protected  void getTotalIncome(){

        int totalIncome;
        int totalSeatNumbers = cinemaHall.length * cinemaHall[0].length;
        int firstRowHalf= cinemaHall.length/ 2;
        int backRowHalf = cinemaHall.length - firstRowHalf;
        if (totalSeatNumbers <= 60){
            totalIncome =totalSeatNumbers * 10;
        }else{
            totalIncome = firstRowHalf * cinemaHall[0].length * 10 + backRowHalf * cinemaHall[0].length* 8;
        }
        System.out.println("Total Income :$"+totalIncome);
    }
    protected  void showSeats() {
        //this will iterate over the 2D array and print out the seating arrangement
        System.out.println("Cinema:");
        System.out.print(" ");
        for(int i=0; i<cinemaHall[0].length; ++i)System.out.print(" "+(i+1));
        System.out.println();

        for( int row=0; row < cinemaHall.length; ++row) {//this handles the row
            System.out.print(row+1);

            for( int column=0; column < cinemaHall[row].length; ++column){//this handles the column in each row
                System.out.print(" "+cinemaHall[row][column]);
            }
            System.out.println();
        }
    }
    protected  void showMenu(){
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    protected  void showStatistics(){
        System.out.println("Number of purchased tickets: "+ticketCount);
        double ticketPercent = 0.0;
        if (ticketCount == 0){
            System.out.println("Percentage: "+ticketPercent+"%");
        }else {

            ticketPercent = (double)ticketCount/100 * cinemaHall.length * cinemaHall[0].length;
            System.out.println("Percentage: "+ticketPercent+"%");
        }
        System.out.println("Current Income: $"+currentIncome);
        getTotalIncome();

    }
    protected  boolean notOutOfBounds(int customerRowNumber, int customerSeatNumber){
        boolean reply;
        reply = customerRowNumber <= cinemaHall.length && customerSeatNumber <= cinemaHall[customerRowNumber-1].length;
        //returns true if the expression above is correct otherwise false
        return reply;
    }
    protected  boolean isBooked(int customerRowNumber, int customerSeatNumber ){

        return cinemaHall[customerRowNumber-1][customerSeatNumber-1] == 'B';
        //returns true if the expression above is correct otherwise false
    }
}
