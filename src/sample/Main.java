package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mainPackage.UsersList;
import mainPackage.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) {
        Controller.createAccount("ExampleUser", "abc");
        ArrayList<String> exampleTagi = new ArrayList<String>();
        exampleTagi.add("kwiat");
        exampleTagi.add("wiosna");
        Controller.uploadToGallery(UsersList.findUser("ExampleUser"), "Kwiat1", "Fotografia przedstawia kwiat o fioletowych płatkach.", exampleTagi, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Cosmos_bipinnatus_pink%2C_Burdwan%2C_West_Bengal%2C_India_10_01_2013.jpg/1200px-Cosmos_bipinnatus_pink%2C_Burdwan%2C_West_Bengal%2C_India_10_01_2013.jpg");
        Controller.uploadToGallery(UsersList.findUser("ExampleUser"), "Kwiat2", "Fotografia przedstawia kwiat o różowych płatkach.", exampleTagi, "https://pixfeeds.com/images/flowers/chrysanthemums/1280-680408732-chrysanthemum-flowers.jpg");
        AtomicReference<User> u = new AtomicReference<>();
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 500, 400);
        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();
        Button btn5 = new Button();
        Button btn6 = new Button();
        Button btn7 = new Button();
        Button btn8 = new Button();
        Button btn9 = new Button();
        Button btn10 = new Button();
        Label lb = new Label();
        lb.setText("Jesteś niezalogowany.\nDane przykładowego użytkownika:\nnazwa: ExampleUser, hasło: abc");
        btn1.setText("Wyświetl użytkownikow");
        btn1.setOnAction(event -> {
            UsersList.printAllUsers();
        });
        btn2.setText("Utwórz konto");
        btn2.setOnAction(event -> {
            String nazwaUz="", haslo="";
            while (nazwaUz.equals("")) {
                System.out.println("Podaj nazwę użytkownika:\t");
                nazwaUz = scanner.nextLine();
            }
            while (haslo.equals("")) {
                System.out.println("Podaj hasło:\t");
                haslo = scanner.nextLine();
            }
            boolean b = Controller.createAccount(nazwaUz, haslo);
            if (b) {
                System.out.println("Utworzono konto.");
                u.set(Controller.logIn(nazwaUz, haslo));
                zalogowanoPomyslnie(lb, u, root, btn2, btn4, btn5, btn6, btn7, btn8, btn10);
            }
            else
                System.out.println("Nazwa użytkownika jest zajęta.");
        });
        btn3.setText("Zamknij");
        btn3.setOnAction(event -> {
            scanner.close();
            Platform.exit();
        });
        btn4.setText("Zaloguj się");
        btn4.setOnAction(event -> {
            String nazwaUz="", haslo="";
            while (nazwaUz.equals("")) {
                System.out.println("Podaj nazwę użytkownika:\t");
                nazwaUz = scanner.nextLine();
            }
            while (haslo.equals("")) {
                System.out.println("Podaj hasło:\t");
                haslo = scanner.nextLine();
            }
            u.set(Controller.logIn(nazwaUz, haslo));
            if (u.get() == null) {
                System.out.println("Nie udało się zalogować.");
                return;
            }
            System.out.println("Zalogowano pomyślnie.");
            zalogowanoPomyslnie(lb, u, root, btn2, btn4, btn5, btn6, btn7, btn8, btn10);
        });
        btn5.setText("Wyloguj się");
        btn5.setOnAction(event -> {
            wylogowanoPomyslnie(u, lb, root, btn2, btn4, btn5, btn6, btn7, btn8, btn10);
        });
        btn6.setText("Usuń konto");
        btn6.setOnAction(event -> {
            String temp;
            System.out.println("Wprowadź hasło, by zatwierdzić operację. Wpisz błędne hasło, by anulować operację.\n");
            temp = scanner.nextLine();
            if(!Controller.deleteAccount(u.get().getUsername(), temp)) {
                System.out.println("Anulowano operację.");
                return;
            }
            System.out.println("Usunięto konto.");
            wylogowanoPomyslnie(u, lb, root, btn2, btn4, btn5, btn6, btn7, btn8, btn10);
        });
        btn7.setText("Ulepsz do Premium");
        btn7.setOnAction(event -> {
            System.out.println("Wybierz pożądany poziom Premium:\n(1) Premium Bronze\n(2) Premium Silver\n(3) Premium Gold\n(4) Premium Platinum\n(5) Premium Diamond\n\nWpisz odpowiednią cyfrę:\t");
            String wybor = scanner.nextLine();
            int wybor2;
            try {
                wybor2 = Integer.parseInt(wybor);
                Controller.upgradeToPremium(u.get(), wybor2);
                System.out.println("Ulepszono pomyślnie.");
            } catch (NumberFormatException e) {
                System.out.println("Podano niewłaściwy format danych.");
            }
        });
        btn8.setText("Prześlij do galerii");
        btn8.setOnAction(event -> {
            String tytul, opis, URLobrazu, tag = "";
            ArrayList<String> tagi = new ArrayList<>();
            System.out.println("Prześlij swoją grafikę! W dowolnym momencie wpisz frazę 'exit', by anulować.");
            System.out.println("Podaj tytuł:\t");
            tytul = scanner.nextLine();
            if (tytul.equals("exit")) {
                System.out.println("Anulowano");
                return;
            }
            System.out.println("Podaj krótki opis:\t");
            opis = scanner.nextLine();
            if (opis.equals("exit")) {
                System.out.println("Anulowano");
                return;
            }
            System.out.println("Podaj tagi (każdy tag zatwierdzaj klawiszem enter, wpisz frazę 'endtags' by zakończyć wpisywanie):\t");
            while (true) {
                tag = scanner.nextLine();
                if (tag.equals("exit")) {
                    System.out.println("Anulowano");
                    return;
                }
                if (tag.equals("endtags")) {
                    break;
                }
                tagi.add(tag);
            }
            System.out.println("Podaj URL obrazu:\t");
            URLobrazu = scanner.nextLine();
            if (URLobrazu.equals("exit")) {
                System.out.println("Anulowano");
                return;
            }
            boolean b = Controller.uploadToGallery(u.get(), tytul, opis, tagi, URLobrazu);
            if (!b) {
                System.out.println("Nie udało się przesłać grafiki.");
                return;
            }
            System.out.println("Przesłano pomyślnie!");
        });
        btn9.setText("Przeglądaj galerie");
        btn9.setOnAction(event -> {
            System.out.println("Wpisz nazwę poszukiwanego użytkownika:\t");
            String nazwa = scanner.nextLine();
            User u1 = UsersList.findUser(nazwa);
            if (u1 == null) {
                System.out.println("Nie znaleziono użytkownika.");
                return;
            }
            wyswietlProfil(new AtomicReference<User>(u1), primaryStage, scene);
        });
        btn10.setText("Wyświetl mój profil");
        btn10.setOnAction(event -> {
            wyswietlProfil(u, primaryStage, scene);
        });
        root.getChildren().add(btn1);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);
        root.getChildren().add(lb);
        root.getChildren().add(btn4);
        root.getChildren().add(btn9);
        StackPane.setAlignment(btn1, Pos.TOP_LEFT);
        StackPane.setAlignment(btn2, Pos.TOP_CENTER);
        StackPane.setAlignment(btn3, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(btn9, Pos.CENTER);
        StackPane.setAlignment(lb, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(btn4, Pos.TOP_RIGHT);
        primaryStage.setTitle("Welcome to ArtRoom!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void zalogowanoPomyslnie(Label lb, AtomicReference<User> u, StackPane root, Button btn2, Button btn4, Button btn5, Button btn6, Button btn7, Button btn8, Button btn10) {
        lb.setText("Zalogowano jako: " + u.get().getUsername());
        root.getChildren().remove(btn2);
        root.getChildren().remove(btn4);
        root.getChildren().add(btn5);
        root.getChildren().add(btn6);
        root.getChildren().add(btn7);
        root.getChildren().add(btn8);
        root.getChildren().add(btn10);
        StackPane.setAlignment(btn5, Pos.TOP_RIGHT);
        StackPane.setAlignment(btn6, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(btn7, Pos.CENTER_LEFT);
        StackPane.setAlignment(btn8, Pos.CENTER_RIGHT);
        StackPane.setAlignment(btn10, Pos.TOP_CENTER);
    }

    private void wylogowanoPomyslnie(AtomicReference<User> u, Label lb, StackPane root, Button btn2, Button btn4, Button btn5, Button btn6, Button btn7, Button btn8, Button btn10) {
        u.set(null);
        lb.setText("Jesteś niezalogowany.\nDane przykładowego użytkownika:\nnazwa: ExampleUser, hasło: abc");
        root.getChildren().remove(btn5);
        root.getChildren().remove(btn6);
        root.getChildren().remove(btn7);
        root.getChildren().remove(btn8);
        root.getChildren().remove(btn10);
        root.getChildren().add(btn2);
        root.getChildren().add(btn4);
    }

    private void wyswietlProfil(AtomicReference<User> u, Stage primaryStage, Scene scene) {
        AtomicInteger idx = new AtomicInteger();
        StackPane profil = new StackPane();
        if (u.get().getUploadedArtworks().size() > 0) {
            String URL = u.get().getUploadedArtworks().get(idx.get()).getURL();
            BackgroundImage myBI= new BackgroundImage(new Image(URL,400,400,true,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            profil.setBackground(new Background(myBI));
        } else {
            Label L2 = new Label();
            L2.setText("Brak prac do wyświetlenia.");
            profil.getChildren().add(L2);
            StackPane.setAlignment(L2, Pos.CENTER);
        }
        Label L = new Label();
        L.setText(u.get().getUsername() + "\tPoziom premium: " + u.get().getPremiumLevel() + "\tIlosc przeslanych prac: " + u.get().getUploadedArtworks().size());
        Button B1 = new Button();
        Button B2 = new Button();
        Button B3 = new Button();
        B1.setText("<<");
        B2.setText(">>");
        B3.setText("Powrót");
        B1.setOnAction(event1 -> {
            idx.getAndDecrement();
            if (idx.get() == -1)
                idx.set(u.get().getUploadedArtworks().size()-1);
            String URL = u.get().getUploadedArtworks().get(idx.get()).getURL();
            BackgroundImage myBI= new BackgroundImage(new Image(URL,400,400,true,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            profil.setBackground(new Background(myBI));
        });
        B2.setOnAction(event1 -> {
            idx.getAndIncrement();
            if (idx.get() == u.get().getUploadedArtworks().size())
                idx.set(0);
            String URL = u.get().getUploadedArtworks().get(idx.get()).getURL();
            BackgroundImage myBI= new BackgroundImage(new Image(URL,400,400,true,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            profil.setBackground(new Background(myBI));
        });
        B3.setOnAction(event1 -> {
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        profil.getChildren().add(L);
        profil.getChildren().add(B1);
        profil.getChildren().add(B2);
        profil.getChildren().add(B3);
        StackPane.setAlignment(L, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(B1, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(B2, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(B3, Pos.CENTER_RIGHT);
        Scene scene2 = new Scene(profil, 500, 400);
        primaryStage.setScene(scene2);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}