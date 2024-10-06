import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {
    // Figure out if states will be all in main or their own classes.
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> checkedItems = new ArrayList<>();

        enum listState {
            MENU, ADD, CHECK, PICK
        }
        listState state = listState.MENU;
        Scanner scanner = new Scanner(System.in);
        Random ran = new Random();
        String userInput;
        while (true) {
            switch (state) {
                case MENU:
                    System.out.println("MENU");
                    userInput = scanner.nextLine();

                    // Maybe make this a switch
                    if (userInput.equalsIgnoreCase("add")) {
                        state = listState.ADD;
                    } else if (userInput.equalsIgnoreCase("check")) {
                        state = listState.CHECK;
                    } else if (userInput.equalsIgnoreCase("view")) {
                        System.out.println(list);
                    } else if (userInput.equalsIgnoreCase("pick")) {
                        state = listState.PICK;
                    } else if (userInput.equalsIgnoreCase("quit")) {
                        System.out.println("Goodbye");
                        return;
                    }
                    break;

                case ADD:
                    // Add does not need to be its own class. Blend it into main or put more in the class
                    System.out.println("Add items");
                    while (true) {
                        userInput = scanner.nextLine();
                        if (userInput.equalsIgnoreCase("back")) {
                            state = listState.MENU;
                            break;
                        } else {
                            list.add(userInput);
                            System.out.println("Added: " + userInput);
                        }
                    }
                    break;

                case CHECK:
                    // make outputs more clear
                    int itemNum = 1;
                    while (true) {
                        for (String item : list) {
                            System.out.println(item + ": " + itemNum);
                            itemNum++;
                        }

                        if (list.isEmpty()) {
                            System.out.println("List is empty");
                            state = listState.MENU;
                            break;

                        }

                        userInput = scanner.nextLine();
                        if (userInput.equalsIgnoreCase("back")) {
                            state = listState.MENU;
                            break;
                        } else if (!userInput.matches("\\d+")) {
                            System.out.println("Not a number");
                        } else if (Integer.parseInt(userInput) > list.size()) {
                            System.out.println("Input out of range");
                        } else {
                            checkedItems.add(list.get(Integer.parseInt(userInput)-1));
                            list.remove(Integer.parseInt(userInput)-1);
                        }
                        break;
                    }
                    break;

                // Ew. Clean.
                case PICK:
                    while (true) {
                        int itemSelector = 0;

                        if (list.size() > 1) {
                            itemSelector = ran.nextInt(list.size());
                        }

                        if (list.isEmpty()) {
                            System.out.println("list is empty");
                            state = listState.MENU;
                            break;
                        }

                        System.out.println(list.get(itemSelector));

                        userInput = scanner.nextLine();
                        if (userInput.equalsIgnoreCase("back")) {
                            state = listState.MENU;
                            break;
                        } else if (userInput.equalsIgnoreCase("done")) {
                            checkedItems.add(list.get(itemSelector));
                            list.remove(itemSelector);
                        }
                    }
                    break;
            }
        }
    }
}