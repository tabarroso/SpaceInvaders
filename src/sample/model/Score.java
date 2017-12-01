package sample.model;

public class Score{
    private int score;
    private String pseudo;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Score(int score, String pseudo){
        this.score = score;
        this.pseudo = pseudo;
    }
}
