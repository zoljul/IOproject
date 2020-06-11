package mainPackage;

import java.util.HashMap;

public class UsersList {
    private static final HashMap<String, User> usersList = new HashMap<>();

    public static User addUser(User user) {
        return usersList.putIfAbsent(user.getUsername(), user);
        // zwraca null, jeśli nazwa uzytkownika jest niezajęta, w przeciwnym razie zwraca Uzytkownika
    }

    public static User findUser(String username) {
        return usersList.get(username);
        // zwraca null, jeśli nie ma takiego uzytkownika
    }

    public static boolean deleteUser(String username) {
        return usersList.remove(username) != null;
    }

    public static void printAllUsers() {
        if (usersList.isEmpty()) {
            System.out.println("Brak uzytkownikow.");
            return;
        }
        for (User u : usersList.values()) {
            System.out.println(u.getUsername() + "\tPoziom premium: " + u.getPremiumLevel() + "\tIlosc przeslanych prac: " + u.getUploadedArtworks().size());
        }
    }
}
