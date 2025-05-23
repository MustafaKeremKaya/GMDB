package MovieApp;

// Movie class implments rateable
public class Movie implements Rateable {
    private int id;
    private String name;
    private String description;
    private String posterPath;
    private int categoryId;
    private double averageRating;

    public Movie(int id, String name, String description, String posterPath, int categoryId, double averageRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.posterPath = posterPath;
        this.categoryId = categoryId;
        this.averageRating = averageRating;
    }

    public int getId() { 
    	return id; 
    	}
    public String getName() { 
    	return name; 
    	}
    public String getDescription() { 
    	return description;
    	}
    public String getPosterPath() { 
    	return posterPath;
    	}
    public int getCategoryId() { 
    	return categoryId;
    	}

    @Override
    public double getAverageRating() {
        return averageRating;
    }

    public String getInfo() {
        return name + " (" + description + ")";
    }
}
