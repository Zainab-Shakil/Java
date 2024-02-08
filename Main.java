import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;


class PfProj{
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        boolean[] table = new boolean[10];
        String[][] customerData = {{"customer1", "2Dt60"}, {"customer2", "5gh7"}, {"customer3", "78jk"}, {"customer4", "io8k"}};
        String[][] breakfast = {{"BreakToast", "100"}, {"FreshToast", "200"}, {"Paratha", "670"}, {"Omelet", "90"}};
        String[][] lunch = {{"LunchItem1", "150"}, {"LunchItem2", "250"}, {"LunchItem3", "350"},{"LunchItem4","765"}};
        String[][] dinner = {{"DinnerItem1", "180"}, {"DinnerItem2", "280"}, {"DinnerItem3", "380"},{"DinnerItem4","876"}};
        String[][] desert = {{"Cake", "500"}, {"Jalebi", "120"}, {"Zarda", "700"}, {"SheerKhurma", "590"}};

        boolean exitProgram = false;

        do {
            System.out.println("Enter 1 for ordering");
            System.out.println("Enter 2 for complain");
            System.out.println("Enter 3 for rating");
            System.out.println("Enter 4 for membership");
            System.out.println("Enter 5 for canceling order");
            System.out.println("Enter 6 for viewing menu");
            System.out.println("Enter 7 to exit");
            System.out.println("Enter 8 for reservation of seats");
            System.out.println("Enter 9 for openly Hours");
            System.out.println("Enter 10 for tracking your order");
            System.out.println("Enter 11 for Suggest items to menu");
            System.out.println("Enter 12 for giviing feedBack");
            System.out.println("Enter 13 for viewing feedback");
            System.out.println("Enter 14 for getting info about deals");
            System.out.println("Enter 15 for  write_data_to_file");

            int userInput = console.nextInt();
            switch (userInput) {
                case 1:
                    ordering(breakfast, lunch, dinner, desert);
                    break;
                case 2:
                    complain();
                    break;
                case 3:
                    int numberForRating = input();
                    display(numberForRating);
                    break;
                case 4:
                    String[][] updated = membership(customerData);
                    break;
                case 5:
                    cancelOrder();
                    break;

                case 6:
                    viewMenu(breakfast, lunch, dinner, desert);
                    break;
                case 7:
                    exitProgram = true;
                    break;
                case 8:
                    ReservationOfTables(table);
                    break;
                case 9:
                    displayOpenHours();
                    break;
                case 10:
                    trackOrder();
                    break;
                case 11:
                    suggestMenu();
                    break;
                case 12:
                    giveFeedback();
                    break;
                case 13:
                    viewFeedback();
                    break;
                case 14:
                    InfoAboutDiscount();
                    break;
                case 15:
                    write_data_to_file();
                    break;

            }
        } while (!exitProgram);
    }


    //functionNo1
    public static int input() {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a number (1-10) to review the cafeteria:");
        return console.nextInt();
    }
    //functionNo2
    public static void display(int rating) {
        if (rating >= 1 && rating <= 10) {
            if (rating <= 5) {
                System.out.println("Thank you for your feedback. We will work on improving.");
            } else {
                System.out.println("Thank you for your positive feedback!");
            }
        } else {
            System.out.println("Invalid rating. Please enter a number between 1 and 10.");
        }
    }
    //functionNo3
    public static void complain() {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter your complaint:");
        String complaint = console.next();
        System.out.println("Thank you for your feedback.");
    }
//functionNo4

    public static String[][] membership(String[][] customerData) {
        Scanner console = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = console.next();

        int code = (int) ((Math.random() * (10000 - 1000)) + 1000);
        String membershipCode = String.valueOf(code);

        String[][] newCustomerData = new String[customerData.length + 1][2];

        // Iterate through the existing data and copy it to the new array
        int i;
        for (i = 0; i < customerData.length; i++) {
            for(int j=0;j<customerData.length; j++){
                newCustomerData[i][j] = customerData[i][j];
            }
        }
        // Add new customer data to the new array
        newCustomerData[i][0] = name;
        newCustomerData[i][1] = membershipCode;

        System.out.println("Your membership code is " + membershipCode);
        return newCustomerData;
    }
//functionNo5

    public static void viewMenu(String[][] breakfast, String[][] lunch, String[][] dinner, String[][] dessert) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter 1 for viewing breakfast");
        System.out.println("Enter 2 for viewing lunch");
        System.out.println("Enter 3 for viewing dinner");
        System.out.println("Enter 4 for viewing dessert");
        int x = console.nextInt();
        switch (x) {
            case 1:
                displayMenu(breakfast);
                break;
            case 2:
                displayMenu(lunch);
                break;
            case 3:
                displayMenu(dinner);
                break;
            case 4:
                displayMenu(dessert);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
    //functionNo6
    public static void displayMenu(String[][] menu) {
        System.out.println("Menu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i][0] + " - Rs" + menu[i][1]);   //first column holds the name,while second column holds the price.
        }
    }
//functionNo7

    public static void ordering(String[][] breakfast, String[][] lunch, String[][] dinner, String[][] dessert) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter 1 for ordering breakfast");
        System.out.println("Enter 2 for ordering lunch");
        System.out.println("Enter 3 for ordering dinner");
        System.out.println("Enter 4 for ordering dessert");
        int x = console.nextInt();

        switch (x) {
            case 1:
                double totalBreakfast = order(breakfast);
                discount(totalBreakfast);
                break;
            case 2:
                double totalLunch = order(lunch);
                discount(totalLunch);
                break;
            case 3:
                double totalDinner = order(dinner);
                discount(totalDinner);
                break;
            case 4:
                double totalDessert = order(dessert);
                discount(totalDessert);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
//functionNo8

    public static double order(String[][] menu) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the number of items you want to order:");
        int itemCount = console.nextInt();

        // Validate user input
        if (itemCount <= 0 || itemCount > menu.length) {
            System.out.println("Invalid input. Please enter a valid number.");
            return 0;
        }

        // Display the menu
        System.out.println("Menu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i][0] + " - Rs" + menu[i][1]);
        }

        // Initialize variables
        double total = 0;
        String orderSummary = "Order Summary:\n";


        // Take user's order
        for (int i = 0; i < itemCount; i++) {
            System.out.print("Enter the item number for item " + (i + 1) + ": ");
            int choice = console.nextInt();

            // Validate user input
            if (choice < 1 || choice > menu.length) {
                System.out.println("Invalid choice. Please enter a valid item number.");
                i--; // Decrement i to re-enter the current item
                continue;
            }

            System.out.print("Enter the quantity for " + menu[choice - 1][0] + ": ");
            int quantity = console.nextInt();

            // Calculate item total
            double itemTotal = Double.parseDouble(menu[choice - 1][1]) * quantity;

            // Update total and order summary
            total += itemTotal;
            System.out.println("enter 1 if you wanna play a quiz.Winner gets a 50% discount");
            int ans=console.nextInt();
            if(ans==1){
                boolean Quiz_discount=Quiz();
                if(Quiz_discount==true){
                    total=total *0.5;
                }

            }

            orderSummary += quantity + "x " + menu[choice - 1][0] + " - " + itemTotal + "\n";
        }

        System.out.println("\n" + orderSummary + "Total: " + total);

        return total;
    }
    //functionNo9
    public static void discount(double total) {
        if (total > 500) {
            total *= 0.7; // 30% discount
            System.out.println("You got a 30% discount. Your total is " + total);
        } else if (total > 1000) {
            total *= 0.6; // 40% discount
            System.out.println("You got a 40% discount. Your total is " + total);
        } else if (total > 2000) {
            total *= 0.5; // 50% discount
            System.out.println("You got a 50% discount. Your total is " + total);
        } else {
            System.out.println("Sorry, you cannot get a discount.");
        }
    }
    //functionNo10
    public static void cancelOrder() {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the order number to cancel:");
        int orderNumber = console.nextInt();
        System.out.println("Order number " + orderNumber + " has been canceled.");
    }
    //functionNo11
    public static boolean Quiz()
    {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        boolean won=false;
        String[] questions = {
                "1. What is the primary cause of global warming?",
                "2. Which gas is commonly associated with the greenhouse effect?",
                "3. What is the consensus among scientists about global warming?",
                "4. What is the main concern of global warming skeptics?",
                "5. Which organization aims to address climate change globally?"
        };
        String[] options = {
                "1. Deforestation\n2. Increased solar activity\n3. Greenhouse gas emissions\n4. Volcanic eruptions",
                "1. Oxygen (O2)\n2. Carbon dioxide (CO2)\n3. Nitrogen (N2)\n4. Hydrogen (H2)",
                "1. There is no consensus\n2. It is a natural cycle\n3. Human activities are the main cause\n4. It's a hoax",
                "1. Lack of scientific evidence\n2. Economic impact\n3. Biodiversity loss\n4. Extreme weather events",
                "1. WHO\n2. UNICEF\n3. IPCC\n4. UNFCCC"
        };
        int[] answers = {3, 2, 3, 4, 4};
        int userAnswer;

        System.out.println("Welcome to the Global Warming Quiz!");
        System.out.println("Please select the correct answer (1-4) for each question:");

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\n" + questions[i]);
            System.out.println(options[i]);

            userAnswer = scanner.nextInt();

            if (userAnswer == answers[i]) {
                score++;

            }
            userAnswer=-1;
        }

        System.out.println("\nQuiz Completed!");
        System.out.println("Your score: " + score + " out of 5");

        if (score == 5) {
            System.out.println("Excellent!");
            won=true;
        }

        else {
            System.out.println("sorry you lost.");

        }

        return won;
    }
    //functionNo12
    public static void  ReservationOfTables(boolean[] table){
        Scanner console = new Scanner(System.in);
        int num;
        do {
            System.out.println("Enter your name");
            String name = console.next();

            // Call the function to reserve seats
            ReserveSeats(table, name);

            System.out.println("Do you want to reserve another table? If yes, enter 1");
            num = console.nextInt();
        } while (num == 1);

        console.close();
    }
    //functionNo13
    public static void ReserveSeats(boolean[] table, String name) {
        // We have 10 tables
        for (int i = 0; i < table.length; i++) {
            if (!table[i]) { // Seat is not reserved
                System.out.println(name + ", you have been given table number " + i);
                table[i] = true;
                break; // Exit the loop after reserving a table
            }
        }
    }
    //functionNo14
    public static void displayOpenHours() {
        System.out.println("Open Hours: Monday to Sunday, 8:00 AM to 10:00 PM");
    }

    //functionNo16
    public static void trackOrder() {
        System.out.println("Your order is being prepared. It will be delivered soon.");
    }
    ////functionNo17
    public static void suggestMenu() {
        Scanner console=new Scanner(System.in);
        console.nextLine(); // Consume the newline character
        System.out.println("Suggest a new item for the menu:");
        String suggestion = console.nextLine();
        System.out.println("Thank you for your suggestion! We will consider it.");
    }
    //functionNo18
    public static void giveFeedback() {
        Scanner console=new Scanner(System.in);
        console.nextLine(); // Consume the newline character
        System.out.println("Please provide your feedback:");
        String feedback = console.nextLine();
        System.out.println("Thank you for your feedback!");
    }
    //functionNo19
    public static void viewFeedback() {
        System.out.println("Viewing Customer Feedback: No feedback available at the moment.");
    }
    //functionNo20
    public static void InfoAboutDiscount(){
        System.out.println("If you order more than 500, you will get a discount of 30%.");
        System.out.println("If you order more than 1500, you will get a discount of 50%.");
    }

    //functionNo21
    public static void write_data_to_file() {
        try {
            File file = new File("name1.txt");
            PrintWriter output = new PrintWriter(file);

            output.println("Order Type: Dine-in");
            output.println("Items Ordered:");
            output.println("1. Dahi Bare                   Rs 150");
            output.println("2. Samosa Chat                 Rs 150");
            // Add more items as needed
            output.println("Total Bill: $XXX");

            output.close();

            System.out.println("Data written to file successfully");

        } catch (IOException e) {
            System.out.println("File not written");
        }
    }
}