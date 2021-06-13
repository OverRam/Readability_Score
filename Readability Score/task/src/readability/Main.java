package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

//        String str = new String(Files.readAllBytes(Paths.get(args[0])));
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int sentence = str.split("[.?!]").length;
        int words = str.split(" ").length;
        int characters = (int) IntStream.range(0, str.length()).filter(i -> str.charAt(i) != ' ').count();
        int syllables = 0;
        int polysyllables = 0;

        System.out.printf("The text is:\n%s\n\nWords: %d\nSentences: %d\nCharacters: %d\n",
                str, words, sentence, characters);


        String mode = "CL";//sc.nextLine();
        double averageAge = 0;
        switch (mode) {
            case "ARI":
                averageAge += ari(sentence, words, characters);
                break;
            case "FK":
                averageAge += fk(sentence, words, syllables);
                break;
            case "SMOG":
                averageAge += smog(polysyllables, sentence);
                break;
            case "CL":
                averageAge += cl(sentence, words, characters);
                break;
            case "all":
                averageAge += ari(sentence, words, characters);
                averageAge += fk(sentence, words, syllables);
                averageAge += smog(polysyllables, sentence);
                averageAge += cl(sentence, words, characters);
                break;
            default:
                System.out.println("Wrong choice.");
        }
        System.out.printf("\nThis text should be understood in average by %.2f-year-olds.", averageAge);
    }

    private static double cl(double sentence, double words, double characters) {
        double score = 0.0588 * (characters / words * 100) - 0.296 * (sentence / words * 100) - 15.8;
        System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds).",
                getFloatPointWithTwo(score), indexScale(score));
        return indexScale(score);
    }

    private static double smog(double polysyllables, double sentence) {
        double score = 0;
        return indexScale(score);
    }

    private static double fk(double sentence, double words, double syllables) {
        double score = 0;
        return indexScale(score);
    }

    private static double ari(double sentence, double words, double characters) {
        double score = 4.71 * ((characters + 0.0) / words) + 0.5 * ((words + 0.0) / sentence) - 21.43;
        System.out.printf("Automated Readability Index: %.2f This text should be understood by %d-year-olds.",
                getFloatPointWithTwo(score), indexScale(score));
        return indexScale(score);
    }

    private static double getFloatPointWithTwo(double score) {
        return (int) (score * 100) / 100.0;
    }

    private static int indexScale(double score) {
        int sco = (int) Math.round(score);
        if (sco == 3) {
            return 9;
        } else if (sco < 3) {
            return sco + 4;
        } else if (sco == 13) {
            return 24;
        } else if (sco < 13) {
            return sco + 6;
        } else {
            return 24;
        }
    }
}
