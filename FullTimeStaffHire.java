public class FullTimeStaffHire extends StaffHire {
    // These two fields are specific to full-time staff
    private double salary;                    // Monthly salary of the staff
    private int weeklyFractionalHours;        // Total working hours per week

    // Constructor for full-time staff – calling parent constructor and setting salary & hours
    public FullTimeStaffHire(int vacancyNumber, String designation, String jobType, String staffName, String joiningDate, String qualification, String appointedBy, boolean joined, double salary, int weeklyFractionalHours) 
    {
        // Calling the constructor from StaffHire (parent)
        super(vacancyNumber, designation, jobType, staffName, joiningDate, qualification, appointedBy, joined);
        this.salary = salary;
        this.weeklyFractionalHours = weeklyFractionalHours;
    }

    // Getters for salary and weekly hours
    public double getSalary() { 
        return salary; 
    }

    public int getWeeklyFractionalHours() { 
        return weeklyFractionalHours; 
    }

    // Setter to update salary – only allowed if someone is actually hired
    public void setSalary(double newSalary) {
        if (getJoined()) { // checking from parent class
            this.salary = newSalary;
        } else {
            System.out.println("The salary can't be set. There is no staff at this position.");
        }
    }

    // Setter to update weekly hours (allowed anytime)
    public void setWeeklyFractionalHours(int newHours) {
        this.weeklyFractionalHours = newHours;
    }

    // Display method – shows everything, including salary and hours, only if someone has joined
    @Override
    public void display() {
        super.display(); // display basic info from StaffHire
        if (getJoined()) {
            System.out.println("Salary: " + salary);
            System.out.println("Weekly Hours: " + weeklyFractionalHours);
        }
    }
}
