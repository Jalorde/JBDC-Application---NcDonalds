/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbcassignment;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Bonif
 */
public class SongDriver {
    private ArrayList songs;
    private Scanner input;
    private SongDAO songDAO;

    public SongDriver() {
        songs = new ArrayList();
        input = new Scanner(System.in);
        songDAO = new SongDAO();
    }

    public static void main(String[] args) {
        SongDriver driver = new SongDriver();
        driver.run();
    }

    public void run() {
        int choice;
        do {
            choice = displayMenu();
            processChoice(choice);
        } while (choice != 5);
    }

    public int displayMenu() {
        System.out.println("\n--- Song Manager System Menu ---");
        System.out.println("1. Display All Songs");
        System.out.println("2. Get Song by ID");
        System.out.println("3. Update Song by ID");
        System.out.println("4. Delete Song by ID");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }

    public void processChoice(int choice) {
        switch (choice) {
            case 1:
                displayAllSongs();
                break;
            case 2:
                getSongById();
                break;
            case 3:
                updateSongById();
                break;
            case 4:
                deleteSongById();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void displayAllSongs() {
        songs = songDAO.getSongs();
        for (int i = 0; i < songs.size(); i++) {
            Song song = (Song) songs.get(i);
            System.out.println("---------------------------");
            System.out.println(song.getFullInfo());
        }
    }

    public void getSongById() {
        System.out.print("Enter song ID: ");
        int id = input.nextInt();
        Song song = songDAO.getSongById(id);
        if (song.getId() != 0) { // Assuming ID is always > 0 for valid songs
            System.out.println("-----------------------------");
            System.out.println(song.getFullInfo());
        } else {
            System.out.println("Song not found.");
        }
    }

    public void updateSongById() {
        System.out.print("Enter song ID to update: ");
        int id = input.nextInt();
        input.nextLine(); 

        Song song = songDAO.getSongById(id);
        if (song.getId() == 0) {
            System.out.println("Song not found.");
            return;
        }

        System.out.println("Current title: " + song.getTitle());
        System.out.print("Enter new title: ");
        String newTitle = input.nextLine();

        System.out.println("Current artist: " + song.getArtist());
        System.out.print("Enter new artist: ");
        String newArtist = input.nextLine();

        System.out.println("Current album: " + song.getAlbum());
        System.out.print("Enter new album: ");
        String newAlbum = input.nextLine();

        System.out.println("Current release year: " + song.getReleaseYear());
        System.out.print("Enter new release year: ");
        int newYear = input.nextInt();

        System.out.println("Current genre ID: " + song.getGenreId());
        System.out.print("Enter new genre ID: ");
        int newGenreId = input.nextInt();

        song.setTitle(newTitle);
        song.setArtist(newArtist);
        song.setAlbum(newAlbum);
        song.setReleaseYear(newYear);
        song.setGenreId(newGenreId);

        songDAO.updateSongById(song);
        System.out.println("Song updated successfully.");
    }

    public void deleteSongById() {
        System.out.print("Enter song ID to delete: ");
        int id = input.nextInt();
        songDAO.deleteSongById(id);
        System.out.println("Song deleted successfully.");
    }
}
