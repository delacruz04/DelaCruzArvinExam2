package arvin.delacruz.com.delacruzarvinexam2;

public class StudentRecord {
    String firstName, lastName;
    Long average;

    public StudentRecord(String firstName, String lastName, Long average) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.average = average;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAverage() {
        return average;
    }

    public void setAverage(Long average) {
        this.average = average;
    }
}
