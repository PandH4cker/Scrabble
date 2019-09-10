import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionnary {
    private String[] wordList;

    public Dictionnary(final String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));
        this.wordList = new String[scan.nextInt()];
        int i = 0;
        try {
            while(scan.hasNext()) {
                this.wordList[i] = scan.next();
                i++;
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        System.out.println(this.wordList.length + " words loaded.");
    }

    public boolean isValidWord(final String word) {
        return Arrays.binarySearch(this.wordList, word) > 0;
    }

    public static boolean mayBeComposed(String word, char[] letters) {
        LinkedList<Character> letterList = new LinkedList<>();
        for(char c : letters) letterList.add(c);
        for(char c : word.toCharArray()) {
            if(!letterList.contains(c)) {
                if(letterList.contains('*')) {
                    letterList.removeFirstOccurrence('*');
                    continue;
                }
                return false;
            }
            else
                letterList.removeFirstOccurrence(c);
        }
        return true;
    }

    public static String replaceFrenchCharacter(String s) {
        return s.replaceAll("[àâä]", "a")
                .replaceAll("ç", "c")
                .replaceAll("[èéêë]", "e")
                .replaceAll("[îï]", "i")
                .replaceAll("[ôö]", "o")
                .replaceAll("[ùûü]", "u")
                .replaceAll("œ", "oe")
                .replaceAll("æ", "ae");
    }

    public List<String> getWordsThatCanBeComposed(char[] letters) {
        List<String> matchingList = new LinkedList<>();
        for(String s : this.wordList)
            if(mayBeComposed(replaceFrenchCharacter(s.toLowerCase()), letters)) matchingList.add(s);

        return matchingList;
    }

    public static char[] getComposition(String word, char[] letters) {
        LinkedList<Character> letterList = new LinkedList<>();
        LinkedList<Character> letterUsed = new LinkedList<>();
        for(char c : letters) letterList.add(c);
        for(char c : word.toCharArray()) {
            if(!letterList.contains(c)) {
                if(letterList.contains('*')) {
                    letterList.removeFirstOccurrence('*');
                    letterUsed.add('*');
                    continue;
                }
                return null;
            }
            else{
                letterList.removeFirstOccurrence(c);
                letterUsed.add(c);
            }
        }

        char[] array = new char[letterUsed.size()];
        int i = 0;
        for(char c : letterUsed) {
            array[i] = c;
            ++i;
        }

        return array;
    }
}
