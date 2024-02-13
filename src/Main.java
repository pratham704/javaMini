import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String apiUrl = "http://localhost:8080/data/questions";

        fetchAndStoreQuestions(apiUrl);

        QuizQuestion[] questions = loadQuestionsFromFile("src/questions.txt");
        System.out.println();
        System.out.println();
        System.out.println("Successfully loaded to Questions.txt , Via api");
        System.out.println();
        System.out.println();
        for (QuizQuestion question : questions) {
            question.start();

            try {
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

    private static void fetchAndStoreQuestions(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n"); // Add newline character between questions
            }

            reader.close();
            connection.disconnect();

            // Store the response in a file
            String fileName = "src/questions.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(response.toString());
            writer.close();

            System.out.println("Questions stored in " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static QuizQuestion[] loadQuestionsFromFile(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int numberOfQuestions = 0;

            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                numberOfQuestions++;
            }

            fileScanner.close();

            QuizQuestion[] questions = new QuizQuestion[numberOfQuestions];
            fileScanner = new Scanner(new File(fileName));

            for (int i = 0; i < numberOfQuestions; i++) {
                String line = fileScanner.nextLine().trim();
                String[] parts = line.split(";");
                String question = parts[0];
                String[] options = parts[1].split(",");

                String correctOptionString = parts[2].replaceAll("\\D", "");
                int correctOption = Integer.parseInt(correctOptionString);

                questions[i] = new QuizQuestion(question, options, correctOption);
            }


            fileScanner.close();
            return questions;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return new QuizQuestion[0];
        }
    }
}
class
QuizQuestion extends Thread {
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
