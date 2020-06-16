package com.rishiramgolam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    public static ArrayList<Album> albums = new ArrayList<Album>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Album album = new Album("Absolution", "Muse");
        album.addSong("Hysteria",3.23);
        album.addSong("Blackout",3.55);
        album.addSong("Fury",4.20);
        albums.add(album);

        album = new Album("Origin of Symmetry", "Fuse");
        album.addSong("New Born", 3.11);
        album.addSong("Bliss", 5.14);
        albums.add(album);


        LinkedList<Song> playlist = new LinkedList<Song>();
        albums.get(0).addToPlayList("Shouldn't work", playlist);
        albums.get(0).addToPlayList("Hysteria", playlist);
        albums.get(0).addToPlayList("Blackout", playlist);
        albums.get(0).addToPlayList(3, playlist);

        albums.get(1).addToPlayList("New Born", playlist);
        albums.get(1).addToPlayList(2, playlist);

        printPlaylist(playlist);

        System.out.println("==========================================\n");

        play(playlist);

    }

    private static void printPlaylist(LinkedList<Song> playlist) {
        for (Song song : playlist) {
            System.out.println(song.toString());
        }
    }

    private static void play(LinkedList<Song> playlist) {
        ListIterator<Song> playlistIter = playlist.listIterator();
        boolean quit = false;
        boolean forward = true;

        printMenu();
        while (!quit) {

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (playlistIter.hasNext()) {
                            playlistIter.next();
                        }
                        forward = true;
                    }
                    if (playlistIter.hasNext()) {
                        System.out.println("Now playing " + playlistIter.next().toString());
                        //forward = false;
                    } else {
                        System.out.println("No more tracks.");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (playlistIter.hasPrevious()) {
                            playlistIter.previous();
                        }
                        forward = false;
                    }
                    if (playlistIter.hasPrevious()) {
                        System.out.println("Now playing " + playlistIter.previous().toString());
                    } else {
                        System.out.println("We are at the start playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    // replay
                    if (forward) {
                        if (playlistIter.hasPrevious()) {
                            System.out.println("Now playing " + playlistIter.previous());
                            forward = false;
                        }
                    } else {
                        if (playlistIter.hasNext()) {
                            System.out.println("Now playing " + playlistIter.next());
                            forward = true;
                        }
                    }
                    break;
                case 4:
                    // list songs in playlist
                    printPlaylist(playlist);
                    break;
                case 5:
                    printMenu();
                    break;
                default:
                    quit = true;
                    break;
            }

        }
    }

    public static void printMenu() {
        System.out.println("Available actions: \n ");
        System.out.println("\t0 - quit");
        System.out.println("\t1 - next track");
        System.out.println("\t2 - previous track");
        System.out.println("\t3 - replay current song");
        System.out.println("\t4 - list songs in the playlist");
        System.out.println("\t5 - print actions");
    }
}
