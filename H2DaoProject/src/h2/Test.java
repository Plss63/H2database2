package h2;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Test {

    public static void main(String[] args) throws  IOException, ParseException {
        Menu menu = new Menu();
        try {
            menu.showmenu();
        } catch (SQLException e) {
            System.out.println("Access error to database");
            e.printStackTrace();
        }
    }
}
