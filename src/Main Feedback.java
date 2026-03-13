public class Main Feedback {
    public static void main(String[] args) {

        String s1 = "I really enjoy using eRyder. ";
        String s2 = "The app is simple and easy to use. ";
        String s3 = "Bikes are always available when I need them. ";
        String s4 = "Prices are cheap and fair for students. ";
        String s5 = "I will keep using this service every day.";

        Feedback feedback = new Feedback("Tom", "Brown", "tombrown@gmail.com");

        feedback.analyseFeedback(true, s1, s2, s3, s4, s5);

        System.out.println(feedback);
    }
}

