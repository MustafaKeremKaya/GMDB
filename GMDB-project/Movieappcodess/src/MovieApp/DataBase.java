package MovieApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final String URL = "jdbc:sqlite:film.db";

    // Connect to the database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        return conn;
    }

    // Create tables
    public static void createTables() {
        String usersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL);";

        String categoriesTable = "CREATE TABLE IF NOT EXISTS categories (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL UNIQUE);";

        String filmsTable = "CREATE TABLE IF NOT EXISTS films (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "description TEXT," +
                "category_id INTEGER," +
                "poster_path TEXT," +
                "FOREIGN KEY (category_id) REFERENCES categories(id));";

        String commentsTable = "CREATE TABLE IF NOT EXISTS comments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "film_id INTEGER," +
                "comment TEXT," +
                "rating REAL," +
                "FOREIGN KEY (user_id) REFERENCES users(id)," +
                "FOREIGN KEY (film_id) REFERENCES films(id));";

        String favoritesTable = "CREATE TABLE IF NOT EXISTS favorites (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "film_id INTEGER," +
                "FOREIGN KEY (film_id) REFERENCES films(id));"; 

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(usersTable);
            stmt.execute(categoriesTable);
            stmt.execute(filmsTable);
            stmt.execute(commentsTable);
            stmt.execute(favoritesTable); 
          
            try {
                stmt.executeUpdate("ALTER TABLE comments ADD COLUMN username TEXT;");
                System.out.println("username sütunu eklendi.");
            } catch (SQLException e) {
                if (e.getMessage().contains("duplicate column name")) {
                    System.out.println("username sütunu zaten var.");
                } else {
                    System.out.println("username sütunu eklenirken hata: " + e.getMessage());
                }
            }

            System.out.println("Tables created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    // Add a new category
    public static void addCategory(String name) {
        String sql = "INSERT INTO categories(name) VALUES(?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Category added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding category: " + e.getMessage());
        }
    }

    // Add new film
    public static void addFilm(String name, String description, int categoryId, String posterPath) {
        String sql = "INSERT INTO films(name, description, category_id, poster_path) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, categoryId);
            pstmt.setString(4, posterPath);
            pstmt.executeUpdate();
            System.out.println("Film added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding film: " + e.getMessage());
        }
    }

    // Add a new favorite
    public static void addFavorite(int filmId) {
        String sql = "INSERT INTO favorites(film_id) VALUES(?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, filmId);
            pstmt.executeUpdate();
            System.out.println("Added to favorites!");
        } catch (Exception e) {
            System.out.println("Error adding to favorites: " + e.getMessage());
        }
    }
    public static void removeFavorite(int filmId) {
        String sql = "DELETE FROM favorites WHERE film_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, filmId);
            pstmt.executeUpdate();
            System.out.println("Removed from favorites.");
        } catch (Exception e) {
            System.out.println("Error removing favorite: " + e.getMessage());
        }
    }
    public static boolean isFilmInFavorites(int filmId) {
        String sql = "SELECT COUNT(*) FROM favorites WHERE film_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, filmId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println("Error checking favorite: " + e.getMessage());
        }
        return false;
    }


    public static double getAverageRating(int filmId) {
        String sql = "SELECT AVG(rating) FROM comments WHERE film_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, filmId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                double avg = rs.getDouble(1);
                if (rs.wasNull()) return -1;
                return avg;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


}
