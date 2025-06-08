package models;

public class User {
    private String username;
    private int level;
    private int xp;
    private String title;

    // Constructor
    public User(String username) {
        this.username = username;
        this.level = 1;
        this.xp = 0;
        this.title = "Beginner";
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public String getTitle() {
        return title;
    }

    // Methods to update XP and level
    public void addXP(int amount) {
        this.xp += amount;
        updateLevel();
    }

    private void updateLevel() {
        this.level = (xp / 100) + 1;
        updateTitle();
    }

    private void updateTitle() {
        if (level < 3) {
            title = "Beginner";
        } else if (level < 5) {
            title = "Hard Worker";
        } else if (level < 7) {
            title = "Task Slayer";
        } else {
            title = "S-Rank Scheduler";
        }
    }
}
