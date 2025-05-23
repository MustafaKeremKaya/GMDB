package MovieApp;

// Featured movie class extending Movie
public class FeaturedMovie extends Movie {

    public FeaturedMovie(int id, String name, String description, String posterPath, int categoryId, double averageRating) {
        super(id, name, description, posterPath, categoryId, averageRating);
    }

    @Override
    public String getInfo() {
        return "[Öne Çıkan] " + getName() + " (" + getDescription() + ")";
    }
}
