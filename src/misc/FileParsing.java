package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileParsing {

    public static class UserModel {
        private int id;
        private String userName;
        private int age;
        private String address;

        public UserModel withId(int id) {
            this.id = id;
            return this;
        }
        public UserModel withName(String name) {
            this.userName = name;
            return this;
        }

        public UserModel withAge(int age) {
            this.age = age;
            return this;
        }

        public UserModel withAddress(String address) {
            this.address = address;
            return this;
        }

        @Override
        public String toString() {
            return String.format("%s %s %s %s", id, userName, age, address);
        }
    }

    public List<UserModel> parseUsersFromCSV(String fileAbsolutePath, String headerLine) {
        Path filePath = Paths.get(fileAbsolutePath);
        if (!filePath.toFile().exists()) {
            throw new IllegalArgumentException(String.format("File %s does not exist.", fileAbsolutePath));
        }

        List<UserModel> users = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (!headerLine.equals(line)) {
                    // Ignore parsing the header line
                    String[] linePartsOnComma = line.split(",");
                    // Skipping any malformed line for now.
                    if (linePartsOnComma.length == 4) {
                        String idStr = linePartsOnComma[0].trim();
                        int id = strToInt(idStr);
                        String name = linePartsOnComma[1].trim();
                        if (name.length() > 20) {
                            name = name.substring(0, 20);
                        }
                        String ageStr = linePartsOnComma[2].trim();
                        int age = strToInt(ageStr);
                        String address = linePartsOnComma[3].trim();
                        if (address.length() > 50) {
                            address = address.substring(0, 50);
                        }

                        users.add(new UserModel().withId(id).withName(name).withAge(age).withAddress(address));
                    }
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException("Failed to read file " + fileAbsolutePath);
        }

        return users;
    }

    private int strToInt(String intStr) {
        return Integer.valueOf(intStr);
    }

    public void readFile(String fileAbsolutePath) {
        Path filePath = Paths.get(fileAbsolutePath);
        try(BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line = null;
            System.out.println("File contents are :");
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        FileParsing fileParsing = new FileParsing();
        // Reading this file itself.
//        fileParsing.readFile("/Users/ashok/Desktop/Projects/LeetCode/src/misc/FileParsing.java");
        String HEADER_LINE = "#id,name,age,address";
        List<UserModel> users = fileParsing.parseUsersFromCSV(
                "/Users/ashok/Desktop/Projects/LeetCode/src/misc/testUsers.csv", HEADER_LINE);
        for (UserModel user : users) {
            System.out.println(user);
        }
    }
}


