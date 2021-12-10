// Rizky Pratama
// Period 4
// Date


import java.util.Scanner; //imports scanner
import java.util.*;
import java.io.*;

public class BlackjackDealerBot //remember to change this
{
   public static void print(String line){ // condenses System.out.print into print(line)
      System.out.print(line);
   }
   
   public static void println(String line){ // condenses System.out.print into print(line)
      System.out.println(line);
   }
   
   public static void printArray(int[] array){ // prints array w/o fencepost prob
      System.out.print("[" + array[0]);
      for (int i = 1; i < array.length; i++){
         System.out.print(", " + array[i]);
      }
      System.out.println("]");
   }
   
   public static ArrayList<String> generateDeck(){//generates a 6 deck of random cards
      ArrayList<String> cards = new ArrayList<String>();
      for (int i = 0; i < 24; i++){
         cards.add("A"); //A's temporarily removed for the time being - until readHand() is done with 2-X
         cards.add("2");
         cards.add("3");
         cards.add("4");
         cards.add("5");
         cards.add("6");
         cards.add("7");
         cards.add("8");
         cards.add("9");
         cards.add("X");
         cards.add("K");
         cards.add("Q");
         cards.add("J");
      }
      Collections.shuffle(cards);
      return cards;
   }
   
   public static int readHand(String hand){//reads the total of a hand, not counting full aces
      int total = 0;
      for (int i = 0; i < hand.length(); i++){
         if (hand.substring(i,i+1).equals("X")||hand.substring(i,i+1).equals("K")||hand.substring(i,i+1).equals("Q")||hand.substring(i,i+1).equals("J")){
            total += 10;
         //} else if (hand.substring(i,i+1).equals("A")) {//big A
         //   total += 11;
         } else if (hand.substring(i,i+1).equals("A")) {//Aces add one for now, adds more later
            total += 1;
         } else if (hand.substring(i,i+1).equals(" ")) {
            total += 0;
         } else {
            total += Integer.parseInt(hand.substring(i, i+1));
         }
      }
      return total;      
   }

   public static String checkBlackJack(int hand){//checks for blackjack or bust
      if (hand > 21){
         return "bust";
      } else if (hand == 21){
         return "blackjack";
      } else {
         return "under";
      }
   }

   public static String checkDealer(int hand){//checks for blackjack or bust
      if (hand > 21){
         return "bust";
      } else if (hand == 21){
         return "blackjack";
      } else if (hand >= 17){
         return "stand";
      } else {
         return "under";
      }
   }

   public static String dealerVsPlayer(int playerHand, int dealerHand){//checks for dealer or player win
      if (playerHand > dealerHand || dealerHand > 21){
         return "win";
      } else if (playerHand < dealerHand){
         return "lose";
      } else {
         return "tie";
      }
   }

   public static ArrayList<Integer> handList(String hand){//presents list of possible hand values, accounting for aces
      int aceCount = 0;
      ArrayList<Integer>hands = new ArrayList<Integer>();
      for (int i = 0; i < hand.length(); i++){
         if (hand.substring(i, i+1).equals("A")){
            aceCount ++;
         }
      }
      for (int i = 0; i <= aceCount; i++){//accounts for hand combinations
         hands.add(readHand(hand)+(i*10));
      }
      return hands;
   }

   public static Integer dealerHand(ArrayList<Integer> dealerHandList){//if dealer hand list is larger than 1
      if (dealerHandList.size()>1){
         if (dealerHandList.get(dealerHandList.size()-2) <= 21){
            return dealerHandList.get(dealerHandList.size()-2);
         } else {
            return dealerHandList.get(dealerHandList.size()-1);
         }
      } else {
         return dealerHandList.get(0);
      }
   }

   public static void currentTable(String dealerHand, String playerHand, int dealerValue, int playerValue, double bet, double wallet){//outputs the current table
      System.out.println("Dealer Hand: " + dealerHand + "\nValue: " + dealerValue);
      System.out.println("Player Hand:" + playerHand + "\nValue: " + playerValue);
      System.out.println("Current Bet: " + bet + "\nWallet: " + wallet);
      System.out.println();
   }
      
   
   
   
   
   

   
    public static void main(String[] args) //main function
    {
      Scanner input = new Scanner(System.in);//imports scanner

      //test archives
      //System.out.print(handList("AAAAAAAAAAAAAAAAAAAAA")); //tested 21 A's
      //close test archives

      //System.out.print(generateDeck());
      double playerMoneyTotal = 500;
      System.out.print("How many games of Blackjack to play? ");
      int gamesToPlay = input.nextInt();
      for (int gamesPlayed = 0; gamesPlayed < gamesToPlay; gamesPlayed++){
         ArrayList<String> currentDeck = generateDeck();
         System.out.print("\nShuffling Deck...\n");
         while (currentDeck.size() > 104) {
            System.out.print("How much do you want to bet for this round? ");//Calculates Bet
            double currentBet = input.nextDouble();
            playerMoneyTotal -= currentBet;

            String currentCard = currentDeck.get(0);//draws the dealer card
            currentDeck.remove(0);
            String dealerHand = currentCard;
            ArrayList<Integer> dealerHandList = handList(dealerHand);
            int dealerValue = dealerHandList.get(dealerHandList.size()-1);

            String playerHand = " ";
            String status = " ";
            int playerValue = 0;
            while (!(status.equals("blackjack")||status.equals("bust")||status.equals("stand"))){//player actions
               currentCard = currentDeck.get(0);
               currentDeck.remove(0);
               playerHand += currentCard;
               playerValue = 0;
               ArrayList<Integer> playerHandList = handList(playerHand);
               playerValue = playerHandList.get(0);
               for (int i = 0; i < playerHandList.size(); i++){
                  if (playerHandList.get(i) <= 21){
                     playerValue = playerHandList.get(i);
                  }
               }
               currentTable(dealerHand, playerHand, dealerValue, playerValue, currentBet, playerMoneyTotal);
               status = checkBlackJack(playerValue);
               if (status.equals("blackjack")||status.equals("bust")){
                  break;
               }
               if (playerHand.length()>2){
                  System.out.print("Stand or Hit? ");
               }
               if (input.nextLine().equals("stand")){
                  status = "stand";
                  System.out.println();
                  break;
               } 
               //currentTable(dealerHand, playerHand, dealerValue, playerValue, currentBet);
            }
            System.out.println(status);
            System.out.println();
            String dealerStatus = " ";
            while (!(dealerStatus.equals("blackjack")||dealerStatus.equals("bust")||dealerStatus.equals("stand"))){// dealer actions
               currentCard = currentDeck.get(0);
               currentDeck.remove(0);
               dealerHand += currentCard;
               dealerValue = 0;
               dealerHandList = handList(dealerHand);
               dealerValue = dealerHand(dealerHandList);
               currentTable(dealerHand, playerHand, dealerValue, playerValue, currentBet, playerMoneyTotal);
               dealerStatus = checkDealer(dealerValue);
               if (dealerStatus.equals("blackjack")||dealerStatus.equals("bust")||dealerStatus.equals("stand")){
                  break;
               }
            }
            if (status.equals("blackjack")&&!dealerStatus.equals("blackjack")){//check payout
               System.out.println("blackjack");
               playerMoneyTotal += currentBet * 2.5;
            } else if (status.equals("bust")){
               System.out.println("lose");
            } else {
               String gameState = dealerVsPlayer(playerValue, dealerValue);
               if (gameState.equals("win")){
                  playerMoneyTotal += currentBet * 2;
               } else if (gameState.equals("tie")){
                  playerMoneyTotal += currentBet;
               }
               System.out.println(gameState);
            }
            System.out.println("Current Balance: " + playerMoneyTotal);
            System.out.println();      
         }
      }    
      input.close();
    }
}
