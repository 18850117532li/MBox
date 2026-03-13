public class Feedback {

    public String firstName;
    public String lastName;
    public String email;
    public String completeFeedback;
    public String reviewID;
    public boolean longFeedback;

    public Feedback(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3, String sent4, String sent5,) {
        return sent1 + sent2 + sent3 + sent4 + sent5;
    }

    private StringBuilder feedbackUsingStringBuilder(String sent1, String sent2, String sent3, String sent4, String sent5,){
        StringBuilder sb = new StringBuilder();
        sb.append(sent1);
        sb.append(sent2);
        sb.append(sent3);
        sb.append(sent4);
        sb.append(sent5);
        return sb;
    }

    private boolean checkFeedbackLength(String feedback) {
        this.longFeedback = feedback.length() > 500;
        return this.longFeedback;
    }

    private void createReviewID(String fn, String ln, String feedback) {
        String name = fn+ln;
        String n = name.substring(2,6);
        n = n.toUpperCase();

        String f = feedback.substring(10,15);
        f = f.toLowerCase();

        int len = feedback.length();
        long time = System.currentTimeMillis();

        String id = n + f + len + "_" + time;
        id = id.replace(" ","");
        reviewID = id;
    }

    public void analyseFeedback(boolean isConcatenation, String s1, String s2, String s3, String s4, String s5) {
        if(isConcatenation==true) {
            completeFeedback = feedbackUsingConcatenation(s1,s2,s3,s4,s5);
        }
        else {
            StringBuilder sb = feedbackUsingStringBuilder(s1,s2,s3,s4,s5);
            completeFeedback = sb.toString();
        }

        checkFeedbackLength(completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }

    public String toString() {
        return "Feedback{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", completeFeedback='" + completeFeedback + '\'' +
                ", longFeedback=" + longFeedback +
                ", reviewID='" + reviewID + '\'' +
                '}';
    }
}