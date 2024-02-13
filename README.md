1. **Importing necessary Java packages for input/output operations, network connections, and utilities for scanning.**
2. **Defining a public class named `Main` which contains the main method, the entry point for the program.**
3. **Inside the `Main` class's main method:**
4. **Defining a String variable `apiUrl` containing the URL of an API endpoint.**
5. **Printing empty lines for spacing.**
6. **Printing the API URL.**
7. **Printing a success message indicating data fetching from the Dockerized endpoint.**
8. **Fetching and storing questions from the API endpoint.**
9. **Loading questions from the stored file.**
10. **Printing success message for loading questions from the API.**
11. **Iterating through loaded questions and starting a thread for each question.**
12. **Waiting for a certain amount of time for each question to be answered.**
13. **Handling interruption exceptions.**
14. **Printing a completion message for the quiz game.**
15. **Defining a private static method named `fetchAndStoreQuestions` which fetches questions from the specified API URL and stores them in a file:**
16. **Creating a URL object from the provided API URL.**
17. **Opening a connection to the URL.**
18. **Configuring the connection to use the GET request method.**
19. **Reading the response from the connection and storing it in a StringBuilder.**
20. **Closing the reader and disconnecting the connection.**
21. **Storing the response in a file named "src/questions.txt".**
22. **Printing a success message indicating data storage.**
23. **Defining a private static method named `loadQuestionsFromFile` which loads questions from a file:**
24. **Creating a Scanner to read from the specified file.**
25. **Counting the number of lines in the file to determine the number of questions.**
26. **Closing the fileScanner.**
27. **Creating an array of QuizQuestion objects based on the number of questions counted.**
28. **Reopening the file and reading each line to create QuizQuestion objects.**
29. **Parsing each line to extract question, options, and correct option.**
30. **Storing the QuizQuestion objects in the array.**
31. **Closing the fileScanner and returning the array of QuizQuestion objects.**
32. **Defining a class named `QuizQuestion` which extends Thread:**
33. **Declaring private fields for question, options, correct option, and answered status.**
34. **Defining a constructor to initialize these fields.**
35. **Implementing the `run` method which displays the question and options, accepts user input for the answer, and checks if it's correct.**
36. **Implementing a method `isAnswered` to check if the question has been answered.**
