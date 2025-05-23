package MovieApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CommentWindow extends JFrame {

    public CommentWindow(int filmId, String filmName) {
        setTitle("Yorum Ekle - " + filmName);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // name panel
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("Adınız:");
        JTextField nameField = new JTextField(20);
        topPanel.add(nameLabel);
        topPanel.add(nameField);

        // comment area
        JTextArea commentArea = new JTextArea(5, 30);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        JScrollPane commentScroll = new JScrollPane(commentArea);
        commentScroll.getVerticalScrollBar().setUnitIncrement(16); // 

        // point
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JLabel ratingLabel = new JLabel("Puan:");
        JSlider ratingSlider = new JSlider(1, 5, 3);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);

        JButton saveButton = new JButton("Kaydet");

        saveButton.addActionListener(e -> {
            String username = nameField.getText().trim();
            String comment = commentArea.getText().trim();
            int rating = ratingSlider.getValue();

            // Regex contorller: only letters and space
            if (!username.matches("^[A-Za-zğüşıöçĞÜŞİÖÇ\\s]{2,20}$")) {
                JOptionPane.showMessageDialog(this, "Geçerli bir isim giriniz (2-20 karakter, sadece harf).", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            saveComment(filmId, username, comment, rating);
            JOptionPane.showMessageDialog(this, "Yorum kaydedildi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        bottomPanel.add(ratingLabel);
        bottomPanel.add(ratingSlider);
        bottomPanel.add(saveButton);

        add(topPanel, BorderLayout.NORTH);
        add(commentScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveComment(int filmId, String username, String comment, int rating) {
        String sql = "INSERT INTO comments (film_id, comment, rating, username) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, filmId);
            pstmt.setString(2, comment);
            pstmt.setInt(3, rating);
            pstmt.setString(4, username);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
