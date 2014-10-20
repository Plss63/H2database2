package h2;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Menu {
    private String inputstr;
    private int choice;
    Check check = new Check();
    EnterModel enterModel = new EnterModel();
    Scanner scanner = new Scanner(System.in);
    
    

    public void showmenu() throws IOException, SQLException, ParseException {
       enterModel.create();
        for (;;) {
            //do{
            System.out.println("Input 1 for add");
            System.out.println("Input 2 for delete");
            System.out.println("Input 3 for search");
            System.out.println("Input 4 for show all");
            System.out.println("Input 5 for update");
            System.out.println("Input 0 for exit");

            choice = input();
            //}while(choice < 0 | choice > 5);
            
            if (choice == 0)
                break;
            switch (choice) {
            case 1:
                enterModel.add();
                break;
            case 2:
                enterModel.delete();
                break;
            case 3:
                enterModel.search();
                break;
            case 4:
                enterModel.showall();
                break;
            case 5:
                enterModel.update();
                break;
            }

        }
    }

    public int input() {
        boolean error = false;
        while (error == false) {
            inputstr = scanner.nextLine();
            if (check.checkInput(inputstr) == false)
                System.out.println("Error! input number 0-5");
            else
                error = true;

        }
        return Integer.parseInt(inputstr);
    }
   
}
