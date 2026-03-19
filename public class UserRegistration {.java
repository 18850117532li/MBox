import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {
    private static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    private static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    private static final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private int UserType;

    private String cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private String cvv;
    private double feeToCharge;
    
    private boolean emailVaild;
    private boolean cardExpiryVaild;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cvvValid;

    private boolean minor;
    private boolean minorAndBirth;   
}

public void registration(){
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== ERyder Registration ===");
    System.out.println("1.Regular User");
    System.out.println("2.VIP User");
    System.out.print("enter your choice: ");
    UserType = scanner.nextInt();
    scanner.nextLine();

    System.out.print("enter your full name: ");
    name = scanner.nextLine();

    System.out.print("enter your email address: ");
    email = scanner.nextLine();
    analyseEmail();

    System.out.print("enter your date of birth(yyyy-mm-dd): ");
    String birthStr = scanner.nextLine();
    birthDate = LocalDate.parse(birthStr);
    analyseAge();

    System.out.print("enter your card number: ");
    cardNumber = scanner.nextLine();
    analyseCardNumber();

    System.out.print("enter your card expiry date: ");
    cardExpiry = scanner.nextLine();
    analyseCardExpiryDate();

    System.out.print("enter your card's CVV; ");
    cvv = scanner.nextLine();
    analyseCVV();

    finalCheckpoint();
    scanner.close();
}

private void analyseEmail() {
    if (email.contains("@") && email.contains(".")){
        System.out.println("Email is valid");
        emailVaild = true;
    } else {
        System.out.println("Invalid email address. Going back to the start of the registration");
        emailVaild = false;
        registration();
    }
}

private void analyseAge(){
    LocalDate today = LocalDate.now();
    int age = Period.between(birthDate, today).getYears();

    if (age <= 12 || age >120) {
      System.out.println("Looks like you are either too young or already dead. Sorry, you can’t be our user. Have a nice day");
        System.exit(0);
    }

    ageValid = true;
    boolean isBirthday = (birthDate.getMonth() == today.getMonth() && birthDate.getDayOfMonth() == today.getDayOfMonth());
    
    minor = (age < 18);
    minorAndBirthday = (minor && isBirthday);

    if (UserType == 2) {
        if (minorAndBirthday) {
            if (minorAndBirthday) {
                System.out.println(" Happy Birthday! You get 25% discount on the VIP subscription fee for being born today and being under 18!");
            } else if (minor) {
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
            }
        }
        
    }

    private void analyseCardNumber() {
        int len = cardNumber.length();
        String start2 = cardNumber.length() >=2 ? cardNumber.substring(0, 2) : "";
        String start4 = cardNumber.length() >=4 ? cardNumber.substring(0, 4) : "";

        boolean vaild = false;

        if((len ==13 || len ==16) && cardNumber.startsWish("4")) {
            cardProvider = "VISA";
            vaild = true;
        }

        else if (len == 15 && ((start2.compareTo("51") >= 0 && start2.compareTo("55") <= 0) || (start4.compareTo("2021") >= 0 && start4.compareTo("2720") <= 0))) {
         cardProvider = "MasterCard";
         vaild = true;   
        }

        else if (len == 15 && (start2.equals("34") || start2.equals("37"))) {
            cardProvider = "American Express";
            vaild = true;
        }

        if (vaild) {
            System.out.println("cardNumber supported");
            cardNumberValid = true;
        } else {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card. Going back to the start of the registration.");
            cardNumberValid = false;
            registration();
        }
    }
    
    private void analyseCardExpiryDate() {
    String[] parts = cardExpiry.split("/");
    int month = Integer.parseInt(parts[0]);
    int year = Integer.parseInt(parts[1]) + 2000;

    LocalDate expiry = LocalDate.of(year, month, 1);
    LocalDate now = LocalDate.now();

    if (expiry.isAfter(now)) {
        System.out.println("The card is still validOtherwise");
        cardExpiryValid = true;
    } else {
        System.out.println("Sorry, your card has expired. Please use a different card.");
        cardExpiryValid = false;
        registration();
    }
}

private  void analyseCVV() {
    boolean  valid;
    if  (cardProvider.equals("American Express")) {
        vaild = cvv.length() = 4;
    } else {
        valid = cvv.length() == 3;
    }
    if (vaild) {
        System.out.println("card CVV is valid.");
        cvvValid = true;
    } else {
        System.out.println("Invalid CVV for the given card.ing back to the start of the registration process.");
        cvvValid = false;
        registration();
    }
}

private void finalCheckpoint() {
    if (emailValid && ageValid && cardNumberValid && cardExpiryValid && cvvValid) {
        chargeFees();
    } else {
        System.out.println("Sorry, your registration was unsuccessful due to the following reason(s).");
        if (!emailValid) System.out.println("Invalid email address");
        if (!ageValid) System.out.println("Invalid age");
        if (!cardNumberValid) System.out.println("Invalid card number");
        if (!cardExpiryValid) System.out.println("Card has expire");
        if (!cvvValid) System.out.println("Invalid CVV");
        System.out.println("Going back to the start of the registration process.");
        registration();
    }
}

private void chargeFees() {
    if (UserType == 2) {
        if (minorAndBirth) {
            feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18_BIRTHDAY) / 100;
    } else if (minor) {
        feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18) / 100;
    } else {
        feeToCharge = VIP_BASE_FEE;
    }
 } else {
    feeToCharge = 0;
}

System.out.println("Thank you for your payment.");
System.out.println("A fee of #### has been charged to your card ending with ****");
String last4 = cardNumber.substring(cardNumber.length() - 4);
System.out.println("**** - The last four digits of the card.");
}

public String toString() {
    String maskedCard;
    if (cardNumber.length() >= 4) {
        String stars = "*".repeat(cardNumber.length() - 4);
        String last4 = cardNumber.substring(cardNumber.length() - 4);
        maskedCard = stars + last4;
    } else {
        maskedCard = cardNumber;
    }

    return "\n===== Registration successful! =====" +
            "\nUser Type："
            "\nFull name："
            "\nEmail Address："
            "\nDate of Birth："
            "\nCard Number："
            "\nCard Provider："
            "\nCard Expiry Date：";
}