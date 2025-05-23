package MovieApp;

public class Comment {
    private int id;
    private int userId;
    private int filmId;
    private String commentText;
    private double rating;

    // Constructor
    public Comment(int id, int userId, int filmId, String commentText, double rating) {
        this.id = id;
        this.userId = userId;
        this.filmId = filmId;
        this.commentText = commentText;
        this.rating = rating;
    }

    // Getters and Setters 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
