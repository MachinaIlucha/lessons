package lesson3.DZ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Solution2 {

    private static final String DB_URL = "jdbc:oracle:thin:@lessonsb1te.ctjiug5ulq7u.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "B1te";
    private static final String PASS = "*****";

    public static void main(String[] args) {
        testSavePerformance();
    }

    private static void testSavePerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TEST_SPEED (ID, SOME_STRING, SOME_NUMBER) VALUES (?, ?, ?)")) {
            long startTime = System.currentTimeMillis();

            for (int i = 1; i < 5; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.setString(2, "a");
                preparedStatement.setLong(3, i);

                int res = preparedStatement.executeUpdate();
                System.out.println(res);
            }

            System.out.println(System.currentTimeMillis() - startTime);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void testDeleteByIdPerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM TEST_SPEED WHERE ID = ?")) {
            long startTime = System.currentTimeMillis();

            for (int i = 0; i <= 5; i++) {
                preparedStatement.setLong(1, i);
                int res = preparedStatement.executeUpdate();
            }

            System.out.println(System.currentTimeMillis() - startTime);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void testDeletePerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE * FROM TEST_SPEED")) {
            long startTime = System.currentTimeMillis();
            int res = preparedStatement.executeUpdate();
            System.out.println(res);
            System.out.println(System.currentTimeMillis() - startTime);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void testSelectByIdPerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT FROM TEST_SPEED WHERE ID = ?")) {
            long startTime = System.currentTimeMillis();

            for (int i = 0; i <= 1000; i++) {
                preparedStatement.setLong(1, i);
                int res = preparedStatement.executeUpdate();
                System.out.println("Save was finished with result " + res);
            }

            System.out.println(System.currentTimeMillis() - startTime);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void testSelectPerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TEST_SPEED")) {
            long startTime = System.currentTimeMillis();
            int res = preparedStatement.executeUpdate();
            System.out.println(System.currentTimeMillis() - startTime);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
