public class PartTimeStaffHire extends StaffHire {
    // Specific attributes just for part-time staff
    private int workingHour;             // Working hours per day
    private double wagesPerHour;         // Payment rate per hour
    private String shifts;               // Morning / Evening etc.
    private boolean terminated;          // Tells if the staff has been fired

    // Constructor for part-time staff
    public PartTimeStaffHire(int vacancyNumber, String designation, String jobType, String staffName, String joiningDate, String qualification, String appointedBy, boolean joined, int workingHour, double wagesPerHour, String shifts) 
    {
        // calling parent constructor to set base values
        super(vacancyNumber, designation, jobType, staffName, joiningDate, qualification, appointedBy, joined);

        // setting values using setters from StaffHire – your boy plays it clean
        setStaffName(staffName);
        setJoiningDate(joiningDate);
        setQualification(qualification);
        setAppointedBy(appointedBy);
        setJoined(joined);

        // setting values specific to part-time
        this.workingHour = workingHour;
        this.wagesPerHour = wagesPerHour;
        this.shifts = shifts;
        this.terminated = false; // obviously new staff isn't terminated yet
    }

    // Getters for all part-time related fields
    public int getWorkingHour() { 
        return workingHour; 
    }
    
    public double getWagesPerHour() { 
        return wagesPerHour; 
    }
    
    public String getShifts() { 
        return shifts; 
    }
    
    public boolean isTerminated() { 
        return terminated; 
    }

    // This allows shift to be updated but ONLY if the staff has joined
    public void setShifts(String newShifts) {
        if (getJoined()) {
            this.shifts = newShifts;
        } else {
            System.out.println("Cannot change shifts. Staff has not joined.");
        }
    }

    // This is like firing the staff – resetting all details
    public void terminate() {
        if (!terminated) {
            setStaffName("");
            setJoiningDate("");
            setQualification("");
            setAppointedBy("");
            setJoined(false);         // basically reversing the hiring
            terminated = true;
            System.out.println("Staff terminated.");
        } else {
            System.out.println("Staff is already terminated.");
        }
    }

    // Display method – shows part-time specific details only if someone is hired
    @Override
    public void display() {
        super.display(); // call parent display first
        if (getJoined()) {
            System.out.println("Wages Per Hour: " + wagesPerHour);
            System.out.println("Working Hours Per Day: " + workingHour);
            System.out.println("Shifts: " + shifts);
            System.out.println("Income Per Day: " + (wagesPerHour * workingHour));
            System.out.println("Terminated: " + terminated);
        }
    }
}
