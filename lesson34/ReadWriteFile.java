package lesson34;

import java.io.*;

public class ReadWriteFile {
    public static void main(String[] args) throws InterruptedException {
        readFile("C:\\Users\\Ilucha\\Desktop\\test.txt");

        writeFile("C:\\Users\\Ilucha\\Desktop\\test1.txt");
    }

    private static void readFile(String path) {
        try (FileReader reader = new FileReader(path); BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        } catch (IOException e) {
            System.err.println("Reading from file " + path + " failed");
        }

    }

    private static void writeFile(String path) throws InterruptedException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {
            bufferedWriter.append("\n");
            bufferedWriter.append("TTTTT");
        } catch (IOException e) {
            System.err.println("Can't write to file");
        }
    }

}
