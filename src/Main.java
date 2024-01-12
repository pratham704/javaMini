// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        QuizQuestion[] questions = loadQuestionsFromFile("src/questions.txt");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (QuizQuestion question : questions) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            question.start();

            try {
                // Give a time limit for answering each question (e.g., 10 seconds)
                question.join(10000);

                if (!question.isAnswered()) {
                    System.out.println("Time's up! Moving to the next question.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Quiz game completed!");
    }

    private static QuizQuestion[] loadQuestionsFromFile(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int numberOfQuestions = countLines(fileName);
            QuizQuestion[] questions = new QuizQuestion[numberOfQuestions];

            for (int i = 0; i < numberOfQuestions; i++) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                String question = parts[0];
                String[] options = parts[1].split(",");
                int correctOption = Integer.parseInt(parts[2]);

                questions[i] = new QuizQuestion(question, options, correctOption);
            }

            return questions;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return new QuizQuestion[0];
        }
    }

    private static int countLines(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int count = 0;

            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                count++;
            }

            fileScanner.close();
            return count;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

class QuizQuestion extends Thread {
    private String question;
    private String[] options;
    private int correctOption;
    private volatile boolean answered;

    public QuizQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        this.answered = false;
    }

    @Override
    public void run() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        Scanner scanner = new Scanner(System.in);
        int userAnswer = scanner.nextInt();

        if (userAnswer == correctOption) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect! The correct answer is: " + correctOption);
        }

        answered = true;
    }

    public boolean isAnswered() {
        return answered;
    }
}
