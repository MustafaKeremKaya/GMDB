package MovieApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {

    private JPanel sidebarPanel;
    private JPanel moviePanel;
    private boolean showBackgroundImage = true;
    private JButton selectedButton = null; 
    private JScrollPane scrollPane;

    public MainWindow() {
        setTitle("Film Platformu");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createSidebar();
        createMoviePanel();

        setVisible(true);
    }

    private void createSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(20, 20, 20));

        JButton homeButton = createSidebarButton("Ana Sayfa");
        homeButton.addActionListener(e -> {
            resetButtonColor(homeButton);
            showBackgroundImage = true;
            moviePanel.removeAll();
            moviePanel.revalidate();
            moviePanel.repaint();
        });
        sidebarPanel.add(homeButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel title = new JLabel("Kategoriler");
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(title);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        addCategoryButton("Tüm Filmler", -1);
        addCategoryButton("Bilim Kurgu", 1);
        addCategoryButton("Aksiyon", 2);
        addCategoryButton("Dram", 3);
        addCategoryButton("Komedi", 4);
        addCategoryButton("Korku", 5);
        addCategoryButton("Gerilim", 6);
        addCategoryButton("Polisiye", 7);

        sidebarPanel.add(Box.createVerticalGlue());

        JButton featuredButton = createSidebarButton("Öne Çıkanlar");
        featuredButton.addActionListener(e -> {
            resetButtonColor(featuredButton);
            showBackgroundImage = false;
            loadFeaturedMovies();
        });
        sidebarPanel.add(featuredButton);

        JButton favoritesButton = createSidebarButton("Favorilerim");
        favoritesButton.addActionListener(e -> {
            resetButtonColor(favoritesButton);
            showBackgroundImage = false;
            loadMovies(-1, true);
        });
        sidebarPanel.add(favoritesButton);

        add(sidebarPanel, BorderLayout.WEST);
    }

    private void createMoviePanel() {
        moviePanel = new JPanel(new GridLayout(0, 3, 20, 20)) {
            private Image backgroundImage = new ImageIcon("images/arkaplan3.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (showBackgroundImage && backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    g.setFont(new Font("Arial", Font.BOLD, 36));
                    g.setColor(Color.WHITE);
                    FontMetrics fm = g.getFontMetrics();
                    String text = "GMDB'ye Hoş Geldiniz!";
                    int x = (getWidth() - fm.stringWidth(text)) / 2;
                    int y = getHeight() / 2;
                    g.drawString(text, x, y);
                } else {
                    g.setColor(new Color(18, 18, 18));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        moviePanel.setOpaque(false);

        scrollPane = new JScrollPane(moviePanel);
        scrollPane.getViewport().setBackground(new Color(18, 18, 18));
        scrollPane.getVerticalScrollBar().setUnitIncrement(20); 
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(150, 30));
        button.setBackground(new Color(46, 46, 46));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void addCategoryButton(String name, int categoryId) {
        JButton button = createSidebarButton(name);
        button.addActionListener(e -> {
            resetButtonColor(button);
            showBackgroundImage = false;
            loadMovies(categoryId, false);
        });
        sidebarPanel.add(button);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private void resetButtonColor(JButton newSelected) {
        if (selectedButton != null) {
            selectedButton.setBackground(new Color(46, 46, 46));
        }
        selectedButton = newSelected;
        selectedButton.setBackground(new Color(80, 80, 120)); 
    }

    private void loadMovies(int categoryId, boolean onlyFavorites) {
        moviePanel.removeAll();
        moviePanel.revalidate();
        moviePanel.repaint();

        new SwingWorker<Void, JPanel>() {
            @Override
            protected Void doInBackground() {
                String sql;
                if (onlyFavorites) {
                    sql = "SELECT films.id, films.name, films.description, films.poster_path FROM films " +
                          "JOIN favorites ON films.id = favorites.film_id";
                } else if (categoryId != -1) {
                    sql = "SELECT * FROM films WHERE category_id = " + categoryId;
                } else {
                    sql = "SELECT * FROM films";
                }

                try (Connection conn = DataBase.connect();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {

                    while (rs.next()) {
                        int filmId = rs.getInt("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        String posterPath = rs.getString("poster_path");

                        JPanel movieCard = createMovieCard(filmId, name, description, posterPath, onlyFavorites);
                        publish(movieCard);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void process(java.util.List<JPanel> chunks) {
                for (JPanel card : chunks) {
                    moviePanel.add(card);
                }
                moviePanel.revalidate();
                moviePanel.repaint();
            }
        }.execute();
    }

    private void loadFeaturedMovies() {
        moviePanel.removeAll();

        try (Connection conn = DataBase.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, description, poster_path, category_id FROM films")) {

            List<Rateable> featuredList = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String posterPath = rs.getString("poster_path");
                int categoryId = rs.getInt("category_id");

                double avgRating = DataBase.getAverageRating(id);
                if (avgRating >= 4.5) {
                    FeaturedMovie fm = new FeaturedMovie(id, name, description, posterPath, categoryId, avgRating);
                    featuredList.add(fm);
                }
            }

            for (Rateable film : featuredList) {
                if (film instanceof Movie) {
                    Movie m = (Movie) film;
                    moviePanel.add(createMovieCard(m.getId(), m.getName(), m.getDescription(), m.getPosterPath(), false));
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Öne çıkan filmler yüklenirken hata oluştu: " + e.getMessage());
        }

        moviePanel.revalidate();
        moviePanel.repaint();
    }

    private JPanel createMovieCard(int filmId, String name, String description, String posterPath, boolean isFavoriteView) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        card.setBackground(new Color(30, 30, 30));

        ImageIcon icon = new ImageIcon(posterPath);
        Image img = icon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel titleLabel = new JLabel(name, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);

        JTextArea descArea = new JTextArea(description + "\n" + getAverageRatingText(filmId));
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setOpaque(false);
        descArea.setForeground(Color.LIGHT_GRAY);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isFavoriteView) {
                    Object[] options = {"Evet", "Hayır"};
                    int confirm = JOptionPane.showOptionDialog(card,
                            "Favorilerden çıkarmak istiyor musunuz?",
                            "Favori Çıkarma",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (confirm == JOptionPane.YES_OPTION) {
                        DataBase.removeFavorite(filmId);
                        JOptionPane.showMessageDialog(card, "Favorilerden çıkarıldı!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                        loadMovies(-1, true);
                    }
                } else {
                    String[] options = {"Yorum Ekle", "Yorumları Gör", "Favorilere Ekle"};
                    int choice = JOptionPane.showOptionDialog(
                            card,
                            "Bir işlem seçin:",
                            "İşlem Seçimi",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );

                    if (choice == 0) {
                        new CommentWindow(filmId, name);
                    } else if (choice == 1) {
                        new CommentListGUI(filmId, name);
                    } else if (choice == 2) {
                        if (DataBase.isFilmInFavorites(filmId)) {
                            JOptionPane.showMessageDialog(card, "Bu film zaten favorilerde!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                        } else {
                            DataBase.addFavorite(filmId);
                            JOptionPane.showMessageDialog(card, "Film favorilere eklendi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });

        card.add(imgLabel, BorderLayout.NORTH);
        card.add(titleLabel, BorderLayout.CENTER);
        card.add(descArea, BorderLayout.SOUTH);

        return card;
    }

    private String getAverageRatingText(int filmId) {
        double avg = DataBase.getAverageRating(filmId);
        if (avg == -1) {
            return "⭐ Puan yok";
        } else {
            return "⭐ " + String.format("%.1f", avg) + "/5";
        }
    }
}
