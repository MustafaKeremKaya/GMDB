package MovieApp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CommentListGUI extends JFrame {

    public CommentListGUI(int filmId, String filmName) {
        setTitle("Yorumlar - " + filmName);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JTextArea commentsArea = new JTextArea();
        commentsArea.setEditable(false);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(commentsArea);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 

        loadComments(filmId, commentsArea);
        add(scrollPane);

        setVisible(true);
    }

    private void loadComments(int filmId, JTextArea commentsArea) {
        String sql = "SELECT username, comment, rating FROM comments WHERE film_id = ?";
        try (Connection conn = DataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, filmId);
            ResultSet rs = pstmt.executeQuery();

            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                String username = rs.getString("username");
                String comment = rs.getString("comment");
                int rating = rs.getInt("rating");

                builder.append("👤 ").append(username)
                       .append("   ⭐ ").append(rating).append("/5\n")
                       .append(comment).append("\n\n");
            }

            commentsArea.setText(builder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
