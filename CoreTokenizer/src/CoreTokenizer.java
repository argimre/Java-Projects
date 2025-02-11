import java.util.Scanner;

public class CoreTokenizer {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        Tokenizer tokenizer = new Tokenizer(filePath);

        while (true) {
            int token = tokenizer.getToken();

            if (token == 33) {
                System.out.println("End of file reached.");
                break;
            } else if (token == 34) {
                System.err.println("Error: Invalid token encountered.");
                break;
            } else {
                System.out.println("Token: " + token);
                tokenizer.skipToken();
            }
        }
        scanner.close();
    }
}
