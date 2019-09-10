import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ScrabbleConsole {
    private static final Scanner scan = new Scanner(System.in);

    public ScrabbleConsole() {
        System.out.println("Welcome to the Scrabble assistant");
        try {
            Dictionnary dico = new Dictionnary("fr_FR_utf8.dico");
            System.out.println("Please enter a letter list:");
            char[] letters = scan.next().toCharArray();
            List<String> matchingList = dico.getWordsThatCanBeComposed(letters);
            System.out.println(matchingList.size() + " matching word(s) found : " + matchingList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ScrabbleConsole();
    }
}
