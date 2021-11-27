// R. A. Pratama  
// Nov 21, 2021 @ 18:26

// This program matches people for secret santa
import java.util.Scanner; //imports scanner
import java.io.*;
import java.util.*;

public class SecretSanta //remember to change this
{
   public static void print(String line){ // condenses System.out.print into print(line)
      System.out.print(line);
   }
   
   public static void println(String line){ // condenses System.out.print into print(line)
      System.out.println(line);
   }
   
    public static void main(String[] args) //main function
    {
      Scanner systemIn = new Scanner(System.in);
      System.out.println("Enter the file name, including the file extension. This program only accepts .tsv files and must be in the same folder as the java program.");
      String file = systemIn.nextLine();
   
      ArrayList<String> input = new ArrayList<>();
      try {//imports TSV file

         Scanner s = new Scanner(new File(file));
         while(s.hasNextLine()){
            input.add(s.nextLine());
         }

      } catch (Exception e){
         System.out.println(e);
      }
   //names2 is giver, names is receiver
      ArrayList<String> names = new ArrayList<>();
      ArrayList<String> names2 = new ArrayList<>();
      String topRowFill = " " + input.get(0);
      input.set(0, topRowFill);
      
      for (int i = 1; i < input.size(); i++){
         
         int k = 0;
         while (input.get(i).substring(k, k+1).equals("\t")==false){
            k++;
         }
         k+=1;
         int j = k;
         while (input.get(i).substring(j, j+1).equals("\t")==false){
            j++;
         }
         names.add(input.get(i).substring(k,j));
         names2.add(input.get(i).substring(k,j));
         
      }

      boolean confirmShuffle = false;
      while (confirmShuffle == false){
         //shuffles the names
         Collections.shuffle(names2);
         boolean shuffled = false;
         while (shuffled == false){
            int matches = 0;
            for (int i = 0; i < names.size() && matches < 1; i++){//checks for self pairs
               if (names.get(i).equals(names2.get(i))){
                  matches++;
               }
            }

            if (matches == 0){
               for (int j = 0; j < names.size() && matches < 1; j++){//checks for 2 ways
                  for (int k = 0; k < names.size() && matches < 1; k++){
                     if (names.get(j).equals(names2.get(k))&&names.get(k).equals(names2.get(j))){
                        matches++;
                     }
                  }
               }
            } 

            if (matches == 0){ //confirms table 2
               shuffled = true;
            } else {
               Collections.shuffle(names2);
            }
         }
         
         System.out.printf("\n%20s | %-20s", "Giver", "Receiver");//Who gives and Who receives table
         System.out.print("\n-------------------------------------------------");
         for (int i = 0; i < names.size();i++){
            
            System.out.printf("\n%20s | %-20s", names2.get(i), names.get(i));
         }      
         System.out.println("\n\nPrint \"yes\" to confirm shuffle, \"no\" to continue");
         if (systemIn.nextLine().equals("yes")){
            confirmShuffle = true;
         }
         

      }
      //input important information
      System.out.println("Input the date when the presents are due");
      String dueDate = systemIn.nextLine();
      System.out.println("Input the place where the presents are due");
      String duePlace = systemIn.nextLine();
      System.out.println("Input the minimum value");
      double minVal = systemIn.nextDouble();
      System.out.println("Input the maximum value");
      double maxVal = systemIn.nextDouble();

      ArrayList<String> topRow = new ArrayList<>();
      ArrayList<String> curRow = new ArrayList<>();
      int itemCount = 0;
      topRow.add(" ");
      
      
      for (int i = 0; i < input.get(0).length(); i++){//top row array
         if (input.get(0).substring(i, i+1).equals("\t")==false){
            topRow.set(itemCount, topRow.get(itemCount) + input.get(0).substring(i, i+1));
         } else {
            itemCount++;
            topRow.add(" ");
         }
      }
      
      for (int i = 0; i < names.size(); i++){//prints copypastas
         itemCount = 0;
         curRow.clear();
         curRow.add(" ");
         for (int j = 0; j < input.get(i+1).length(); j++){
            if (input.get(i+1).substring(j, j+1).equals("\t")==false){
               curRow.set(itemCount, curRow.get(itemCount) + input.get(i+1).substring(j, j+1));
            } else {
               itemCount++;
               curRow.add(" ");
            }
         }
         System.out.printf("----------------------\nHi, %s!\nYou will be giving a present to **%s**!\nHere's some information to help you think about what to get for them.\n**REMEMBER**: keep the cost between $%.2f and $%.2f and deposit your present in %s by %s.\nHave fun!\n\n", names2.get(i), names.get(i), minVal, maxVal, duePlace, dueDate);
         for (int j = 2; j < topRow.size(); j++){
            System.out.print("**");
            System.out.print(topRow.get(j));
            System.out.println("**");
            System.out.println(curRow.get(j));
            System.out.println();
         }
         System.out.println("----------------------\n");
         System.out.println("\nPrint \"y\" to continue to the next message.");
         if (systemIn.nextLine()=="y"){
            continue;
         }
      }
      







    }
}
