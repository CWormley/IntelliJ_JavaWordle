import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.List;
import java.util.regex.Pattern;
public class GameCode {
  public static void main(String[] args) throws IOException  {
     // make list of 5-letter words
      List<String> words = new ArrayList<String>();
      BufferedReader txt = new BufferedReader(new FileReader("sgb-words.txt"));
      String line = txt.readLine();
      while(line != null){
          words.add(line);
          line = txt.readLine();
      }
      txt.close();

      //initializing answer variable
       String [] words5 = new String[words.size()];
       for (int i =0; i < words.size(); i++){
           words5 [i] = words.get(i);
       }
       int random = (int)(Math.random()*words5.length);
       String answer = words5[random];
       char [] answerChar = str2char(answer);


       //rules
       System.out.println("Welcome to Wordle!\nYou have six guesses to figure out the five letter word\nFor each guess" +
               " Ill tell you what letters you got right\nIf you have the right letter in the wrong place, there will be " +
               "a '*'\nIf you have the right letter in the right place, there will be a 'X'!");
       System.out.println("------------------------------------------------------------------------------------");

       //6 tries to guess
       for(int n =0; n<6;n++){

           System.out.println("\n What's your guess?");

           //input
           Scanner input = new Scanner(System.in);
           String guess = input.nextLine();
           guess = guess.toLowerCase();

           //conditions
          boolean valid = false;
           for (String s : words5) {
               if (guess.matches(s)) {
                   valid = true;
                   break;
               }
           }
          while (!valid){
               System.out.println("This guess is invalid, please try again");
               guess = input.nextLine();
               guess = guess.toLowerCase();
              for (String s : words5) {
                  if (guess.matches(s)) {
                      valid = true;
                      break;
                  }
              }
           }

           //turn guess word into char list
           char[] letters = str2char(guess);

           //print results
           if(guess.equals(answer)){
               System.out.println("You Got IT! \nThe answer is " + answer +"!");
               System.exit(0);
           }else{
               matches(letters,answerChar);
           }


       }
       //failed after 6 tries
       System.out.println("Im sorry \nYou are out of Guesses :( \nThe answer was " + answer);
   }

   public static char[] str2char(String word){ //turn string into character list
       char [] letters = new char [word.length()];

       for(int i =0; i < word.length(); i++){
           letters[i]=word.charAt(i);
       }
       return letters;
   }

   public static void matches (char[] guessChar, char [] answerChar){ //find if guess letters match answer letters
       char[] answerCopy = answerChar.clone();//localize answer variable
       for(int i =0; i < 5; i++){
           if(guessChar [i] == answerCopy[i]){ // "green" letters
               //display results
               System.out.print("X");
               answerCopy[i]=0;//eliminate letters already used
           } else {
               //check if there's a yellow
               boolean yellow = false;
              for(int j=0;j<5;j++) {
                  if(guessChar[i]==answerCopy[j]) {
                      yellow = true;
                      answerCopy[j]=0;
                      break; //do not repeat
                  }

              }
              //display results
              if (yellow){
                  System.out.print("*");
              }else{
                  System.out.print("_");
              }
           }


       }




   }

}








