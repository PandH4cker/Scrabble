import java.util.Comparator;

public class ScrabbleComparator implements Comparator<String> {
    private char[] letters;

    public ScrabbleComparator(char[] letters) {
        this.letters = letters;
    }

    public static int letterValue(char letter) {
        switch (letter) {
            case '*':
                return 0;
            case 'e':
            case 'a':
            case 'i':
            case 'n':
            case 'o':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'l':
                return 1;
            case 'd':
            case 'm':
            case 'g':
                return 2;
            case 'b':
            case 'c':
            case 'p':
                return 3;
            case 'f':
            case 'h':
            case 'v':
                return 4;
            case 'j':
            case 'q':
                return 8;
            case 'k':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
                return 10;
            default: return -1;
        }
    }

    public static int lettersValue(char[] letters) {
        int value = 0;
        for(char c : letters) value += letterValue(c);
        return value;
    }

    public int wordValue(String word) {
        return lettersValue(word.toCharArray());
    }

    @Override
    public int compare(String o1, String o2) {
        char[] compositionO1 = Dictionnary.getComposition(Dictionnary.replaceFrenchCharacter(o1), this.letters);
        char[] compositionO2 = Dictionnary.getComposition(Dictionnary.replaceFrenchCharacter(o2), this.letters);
        if(compositionO1 != null && compositionO2 != null)
            return Integer.compare(lettersValue(compositionO1), lettersValue(compositionO2));
        return 0;
    }
}
