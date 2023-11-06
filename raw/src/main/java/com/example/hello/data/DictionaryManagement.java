/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hello.data;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class DictionaryManagement {

    Scanner sc = new Scanner(System.in);

    public void run() {
        Dictionary dict = new Dictionary();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Welcome to my Dictionary! choose any numbers below to start:");
            System.out.println(
                "[0] Exit\n" +
                "[1] Add\n" +
                "[2] Remove\n" +
                "[3] Update\n" +
                "[4] Display\n" +
                "[5] Lookup\n" +
                "[6] Search\n" +
                "[7] Game\n" +
                "[8] Import from file\n" +
                "[9] Export to file\n" +
                "Your action:");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    System.out.println("Enter the word you want to add in the dictionary:");
                    String word = sc.nextLine();
                    System.out.println("Enter the meaning of " + word + ":");
                    String meaning = sc.nextLine();
                    dict.addWord(new Word(word, meaning));
                    break;
                case 2:
                    System.out.println("Enter the word you want to remove from the dictionary:");
                    dict.remove(sc.nextLine());
                    break;
                
                case 4:
                    System.out.println("All words in the dictionary:");
                    dict.display();
                    break;
                
                case 5:
                    System.out.println("Enter the word you want to look up:");
                    dict.lookup(sc.nextLine());
                    break;
                case 6:
                    System.out.println("Enter the word you want to search:");
                    dict.search(sc.nextLine());
                    break;
                case 8:
                    System.out.println("Please enter the path of your file");
                    dict.addFromFile(sc.nextLine());
                    break;
                default:
                    System.out.println("Please try again :<");
                    break;
            }
//            for(int i =0;i<20;i++){
//                System.out.println("");
//            }
        }
        System.out.println("Thank you for using our dictionary, good luck!");

    }
}
