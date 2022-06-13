package mobile.uas.kel_15.ultramovie.model;

import android.view.View;

import androidx.core.app.Person;

public class User {

    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    public static final int LEVEL_ADMIN = 1;
    public static final int LEVEL_MEMBER = 2;

    public static final int AUTHENTICATED = 1;
    public static final int AUTHENTICATION_INVALID = 2;
    public static final int UNAUTHENTICATED = 4;

    private String name;
    private int gender;
    private String countryOrigin;
    private String username;
    private String password;
    private int level;
    private int authenticated = UNAUTHENTICATED;

    public User() { }

    public User(int authentication) {
        this.authenticated = authentication;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String name, int gender, String countryOrigin, String username, String password, int level) {
        this.name = name;
        this.gender = gender;
        this.countryOrigin = countryOrigin;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(int authenticated) {
        this.authenticated = authenticated;
    }
}