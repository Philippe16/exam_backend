package dtos;

public class Username_movieIDDTO {

    private String username;
    private int movieID;

    public Username_movieIDDTO() {
    }

    public Username_movieIDDTO(String username, int movieID) {
        this.username = username;
        this.movieID = movieID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
