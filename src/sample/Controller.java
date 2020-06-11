package sample;

import mainPackage.*;

import java.util.ArrayList;


public class Controller {

    public static boolean createAccount(String username, String password) {
        User abc = UsersList.addUser(new User(username, password));
        return abc == null;
    }

    public static boolean deleteAccount(String username, String password) {
        User u = UsersList.findUser(username);
        if (!u.getPassword().equals(password))
            return false;
        return UsersList.deleteUser(username);
    }

    public static User logIn(String username, String password) {
        User abc = UsersList.findUser(username);
        if (abc == null)
            return null;
        if (abc.getPassword().equals(password))
            return abc;
        return null;
    }

    public static boolean uploadToGallery(User author, String title, String desc, ArrayList<String> tags, String URL) {
        return author.uploadToGallery(new Artwork(title, desc, tags, URL));
    }

    public static void upgradeToPremium(User user, int level) throws NumberFormatException {
        if (level < 1 || level > 5)
            throw new NumberFormatException();
        user.setPremiumLevel(level);
    }
}
