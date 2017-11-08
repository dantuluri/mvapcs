/**
 * SuperH.java
 * EDIT                                                                HERE
 * @author Surya Dantuluri
 * @version 1.0
 * @since 10/24/2017
 */

import java.util.Scanner;

public class SuperH
{
    /** The list of words that are possible solutions to the current "word" being looked for by the user. */
    /** You should start with the simpler game of Hangman, and make this a single String, not an array    */
    /** of String.     */
    private String [] wordlist;
    /** A boolean to determine if the list of words should be shown.  True to show, false to hide.        */
    private boolean show;

    /** A random int, from 5 to 9 (inclusive), representing the length of the chosen word.                */
    private int wordlength;

    /** You may want to create a few more field variables.    */
    private Dice die1;

    private char inputChar;


    /** Setup field variables, setting show equal to the boolean in the parameter
     *  list, choosing a random number from 5 to 9 for the wordlength, etc.
     */
    public SuperH(boolean peek)
    {
        show = peek;
        wordlength = -1;
        die1 = new Dice(4);
        wordlength = die1.roll()+5;
    }

    /** Play the game.  */
    public static void main (String [] args)
    {
        SuperH run;
        if(args.length > 0 && args[0].equalsIgnoreCase("show"))
        {
            run = new SuperH(true);
        }
        else
        {
            run = new SuperH(false);
        }
        run.loadWords();
        run.welcome();
        run.playGame();
        //run.exitMessage();
    }

    /** Open up the text file for reading.  Count the number of words of length wordlength in the file.
     *  Close the text file.  Set the size of the wordlist array to be equal to the count you just found
     *  for the number of words.  Open up the text file again, saving the words in the the wordlist
     *  array.  Close the text file.
     */
    public void loadWords ( )
    {
     Scanner wordScanner = OpenFile.openToRead("words.txt");
     int counter = 0;
     String inputString = "";
        while(wordScanner.hasNextLine())
        {
            inputString = wordScanner.nextLine();
            if(wordlength==inputString.length())
            {
                counter++;
            }
        }
        //System.out.println("len "+wordlength);
        wordScanner.close();
        wordlist = new String[counter];
        wordScanner = OpenFile.openToRead("words.txt");
        inputString = "";
        counter = 0;
        while(counter<wordlist.length)
        {
            inputString = wordScanner.nextLine();
            if(inputString.length()==wordlength)
            {
                //System.out.println(inputString);
                wordlist[counter] = inputString;
                counter++;
            }
        }
        wordScanner.close();
        //printing press
        for(int i = 0;i<counter;i++)
        {
            System.out.println(wordlist[i]);
        }
        System.out.println("len "+wordlength);
    }

    /** A loop that plays a single game.      */
    public void playGame ( )
    {
        boolean done = false;
        do
        {
            if(show)
            {
                printWordList();
            }
            getCharGuess();
            wordlist = eliminateWords();        /** This is the hard part.  Skip this as you get started.  Write a simple    */
                                                /** version of Hangman first, then add this functionality later.             */
            done = check();
        }
        while(!done);
    }

    /** A welcome with some basic instructions.       */
    public void welcome ( )
    {
        System.out.println("\n\n\n");
        System.out.println(" _____                                  __  __                                                           ");
        System.out.println("/\\  __`\\                               /\\ \\/\\ \\                                                          ");
        System.out.println("\\ \\,\\L\\_\\  __  __  _____      __   _ __\\ \\ \\_\\ \\     __      ___      __     ___ ___      __      ___    ");
        System.out.println(" \\/_\\__ \\ /\\ \\/\\ \\/\\ '__`\\  /'__`\\/\\`'__\\ \\  _  \\  /'__`\\  /' _ `\\  /'_ `\\ /' __` __`\\  /'__`\\  /' _ `\\  ");
        System.out.println("   /\\ \\L\\ \\ \\ \\_\\ \\ \\ \\L\\ \\/\\  __/\\ \\ \\/ \\ \\ \\ \\ \\/\\ \\L\\.\\_/\\ \\/\\ \\/\\ \\L\\ \\/\\ \\/\\ \\/\\ \\/\\ \\L\\.\\_/\\ \\/\\ \\ ");
        System.out.println("   \\ `\\____\\ \\____/\\ \\ ,__/\\ \\____\\\\ \\_\\  \\ \\_\\ \\_\\ \\__/.\\_\\ \\_\\ \\_\\ \\____ \\ \\_\\ \\_\\ \\_\\ \\__/.\\_\\ \\_\\ \\_\\");
        System.out.println("    \\/_____/\\/___/  \\ \\ \\/  \\/____/ \\/_/   \\/_/\\/_/\\/__/\\/_/\\/_/\\/_/\\/___L\\ \\/_/\\/_/\\/_/\\/__/\\/_/\\/_/\\/_/");
        System.out.println("                     \\ \\_\\                                            /\\____/                            ");
        System.out.println("                      \\/_/                                            \\_/__/     ");
        System.out.println("\n");
        System.out.println("\n                       WElCOME TO THE GAME OF SUPER HANGMAN.");
        System.out.println("\n     A word, of length 5 to 9 letters, will be chosen at random from the dictionary.");
        System.out.println("     You will be invited to input letter guesses, attempting to spell the word");
        System.out.println("     before making 7 wrong guesses.  If you can choose the correct letters before");
        System.out.println("     making 7 wrong guesses, you win.  If you reach 7 wrong guesses before");
        System.out.println("     completing the word, you lose.  Good luck!\n\n");
        Prompt.getString("     Press the ENTER key to begin the game: ");
    }

    /** Prints the list of words.  If you are creating the simple game of Hangman, just print out the
     *  single word that was chosen at random from the text file.
     */
    public void printWordList ( )
    {
        for(int i = 0; i < wordlist.length; i++)
        {
            if(i % 7 == 0)
            {
                System.out.println();
            }
            System.out.print(wordlist[i] + "  ");
        }
        System.out.print("\n\n" + wordlength + "   " + wordlist.length);
        System.out.println("\n\n");
    }

    /** Prompt and receive a letter guess from the user.  Be sure to deal with "bad input" smoothly.
     */
    public void getCharGuess ( )
    {
        //printScore();
        String input = "";
        while(input.length()==0||input.length()>1)
        {
          input = Prompt.getString("\n\nEnter your character: ");
        }
        inputChar = input.charAt(0);
        System.out.println("end guess");
    }

    /** Reacts to user input, paring down the list of words.  The idea is to keep as many words as possible
     *  (to make it harder for the user), but following the letters input by the user . . .
     *  @return   The String array of words still possible, matching the user's guesses.
     */
    public String [] eliminateWords ()
    {
      //0101
      //0100
      //0100

      System.out.println("hea");
      // for(int i = 0;i<50;i++)
      // {
      //     System.out.println(wordlist[i]);
      // }

      String[] bypass1 = new String[wordlist.length];
      String[] bypass2 = new String[wordlist.length];
      int[] frequency = new int[wordlist.length];
      for(int i = 0;i<wordlist.length;i++)
      {
        wordlist[i] = bypass1[i];
      }
      
      for(int i = 0;i<wordlist.length;i++)
      {
        for(int j = 0;j<wordlength+1;j++)
        {
          if(bypass[i].charAt(j)==inputChar)
          {
            bypass[i] = bypass[i].substring(0,j)+"1"+bypass[j].substring(j+2,bypass[j].length()-1);
          }
          bypass[i] = bypass1[i].replace(j

          bypass1[j]+="1";
          else
          bypass1[j]+="0";
        }
      }
      for(int i = 0;i<bypass1.length;i++)
      {
        bypass1[i] = bypass2[i];
      }
      for(int i = 0;i<wordlist.length;i++)
      {
        for(int j = 0;j<wordlist.length;j++)
        {
          System.out.println("b1"+bypass1[i]);
          System.out.println("b2"+bypass2[j]);
          // if(bypass1[i].equals(bypass2[j])&&i!=j)
          // {
          //   frequency[i]++;
          //   bypass2[j] = "";
          // }
        }
      }
      int best = 0;
      int indexOfBest = 0;
      //find biggest
      for(int i = 0;i<frequency.length;i++)
      {
        if(!(best>frequency[i]))
        {
          best = frequency[i];
          indexOfBest = i;
        }
      }
      System.out.println("most num times: "+frequency[indexOfBest]+" binarysig: "+bypass1[indexOfBest]+" string "+wordlist[indexOfBest]);
      //construct better array
      //for(int i = 0;i<)
      return null;
    }

    /** A check to see if the game is finished, either because the user has made
     *  too many wrong guesses, or the word has been found.
     *  @return   true if the game is over, false if the game should continue
     *            with another turn.
     */
    public boolean check ( )
    {
        return false;
    }

    /** A message at the end of the game, letting the user know if they won or lost.
     *  The word should be printed, and the user should be invited to play again.
     */
    public void exitMessage ( )
    {

    }


    /** More methods to write (and comment) . . .  ?    */



}
