package com.joelbeckum;

import com.joelbeckum.Exceptions.RoomAlreadyExistsException;
import com.joelbeckum.Exceptions.RoomNotFoundException;
import com.joelbeckum.Repositories.RoomData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class RoomMenu {

    public void launchRoomMenu() {
        Scanner input = new Scanner(System.in);
        RoomData roomData = new RoomData();
        boolean shouldExit = false;
        printRoomMenu();

        while(!shouldExit) {
            System.out.println("Choose your action: (0 to show a list of available actions)");
            int action = input.nextInt();
            input.nextLine();

            switch(action) {

                case 0:
                    printRoomMenu();
                    break;

                case 1:
                    try {
                        System.out.println("Enter room number to add to the database");

                        int roomInput = input.nextInt();
                        roomData.addRoom(roomInput);
                        System.out.println("Room " + roomInput + " added to the database");
                    } catch(IOException | SQLException e) {
                        System.out.println("Room addition unsuccessful");
                        e.printStackTrace();
                    } catch(RoomAlreadyExistsException e) {
                        System.out.println("Room addition unsuccessful: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Enter the room number to be removed");

                        int roomInput = input.nextInt();
                        roomData.removeRoom(roomInput);
                        System.out.println("Room " + roomInput + " removed from the database");
                    } catch(IOException | SQLException e) {
                        System.out.println("Removal unsuccessful: ");
                        e.printStackTrace();
                    } catch(RoomNotFoundException e) {
                        System.out.println("Removal unsuccessful: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Placeholder for updateRoom() method");
                    break;

                case 4:
                    try {
                        System.out.println("Rooms on record:");
                        for (Room room : roomData.getRooms()) {
                            System.out.println(room.getRoomNumber());
                        }
                    } catch(IOException | SQLException e) {
                        System.out.println("Query failed:");
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    System.out.println("Returning to main menu");
                    shouldExit = true;
                    break;

                default:
                    System.out.println("Invalid action: please select from available actions");
                    break;
            }
        }
    }

    public void printRoomMenu() {
        System.out.println("Available actions: press\n");
        System.out.println("0 - to display a list of available actions");
        System.out.println("1 - to add a room to the database");
        System.out.println("2 - to remove a room from the database");
        System.out.println("3 - to update a room number in the database");
        System.out.println("4 - to display a list of rooms in the database");
        System.out.println("5 - to return to the main menu");
    }
}
