package com.dena.cinemaroom;

public class CinemaRoom {
    public static void main(String[] args) {
        CinemaRoomLogic cinema = new CinemaRoomLogic();
        cinema.createCinema();
        int checkMenu;
        do {
            cinema.showMenu();
            checkMenu = CinemaRoomLogic.scan.nextInt();
            switch (checkMenu) {
                case 1:
                    cinema.showSeats();
                    break;
                case 2:
                    cinema.getTicketPrice();
                    break;
                case 3:
                    cinema.showStatistics();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error! Wrong command");
            }
        }while( checkMenu != 0) ;
    }
}
