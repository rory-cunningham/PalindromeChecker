import com.company.UserData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class PalindromeCheckerTest {


    @Test
    void checkInputValidity() {
        String input = "kayak";
        boolean tested = true;
        char[] ch = input.toCharArray();
        for (char c : ch) {
            if (!Character.isLetter(c)) {
                tested = false;
                break;
            }
        }
        assertTrue(tested);
    }

    @Test
    void checkIfPalindrome(){
        String palindrome = "kayak";
        StringBuilder reverse = new StringBuilder();
        for (int i = palindrome.length() - 1; i >= 0; i-- ) {
            reverse.append(palindrome.charAt(i));
        }
        assertTrue(palindrome.equals(reverse.toString()));
    }

    @Test
    void addPalindromeToCache(){
        UserData user = new UserData();
        ArrayList<UserData> storeOfPalindromes = new ArrayList<UserData>();
        storeOfPalindromes.add(user);
        assertNotNull(storeOfPalindromes);
    }

    @Test
    void createOrFindFile()
    {
        Path path = Paths.get("src/test/resources/target.txt");
        if (Files.notExists(path)) {
            Path newFilePath = Paths.get("src/test/resources/target.txt");
            try {
                Files.createFile(newFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assertTrue(Files.exists(path));
    }

    @Test
    void addPalindromeToFile() throws IOException {
        UserData user = new UserData();
        user.setUserName("Rory");
        FileOutputStream fileOut = new FileOutputStream("src/test/resources/target.txt");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(user);
        objectOut.close();

        try (FileInputStream fis = new FileInputStream("src/test/resources/target.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            UserData passedInObj = (UserData) ois.readObject();
            assertNotNull(passedInObj);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    @Test
    void populateCache() {
        try (FileInputStream fis = new FileInputStream("src/test/resources/target.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            UserData user = (UserData) ois.readObject();
            assertNotNull(user);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    @Test
    void askForUserName() {
        String data = "Users Input";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        assertEquals(data,answer);

    }

    @Test
    void askForUserInput() {
        String data = "Users Input";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        assertEquals(data,answer);
    }

}