package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

        String str = new String(Files.readAllBytes(Paths.get(args[0])));
//        String str = new Scanner(System.in).nextLine();
        int sentence = str.split("[.?!]").length;
        int words = str.split(" ").length;
        int characters = (int) IntStream.range(0, str.length()).filter(i -> str.charAt(i) != ' ').count();
        double score = 4.71 * ((characters + 0.0) / words) + 0.5 * ((words + 0.0) / sentence) - 21.43;

        System.out.printf("The text is:\n%s\n\nWords: %d\nSentences: %d\nCharacters: %d\nThe score is: %.2f\n",
                str, words, sentence, characters, (int) (score * 100) / 100.0);

        int sco = (int) Math.ceil(score);
        String s;

        if (sco == 3) {
            s = "7-9";
        } else if (sco < 3) {
            s = (sco + 4) + "-" + (sco + 5);
        } else if (sco == 13) {
            s = "18-24";
        } else if (sco < 13) {
            s = (sco + 5) + "-" + (sco + 6);
        } else {
            s = "24";
        }

        System.out.printf("This text should be understood by %s-year-olds.", s);
    }
}