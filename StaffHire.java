public class StaffHire {
    // These are the common attributes for both full-time and part-time staff
    private int vacancyNumber;         // Unique number to identify a vacancy
    private String designation;        // Job title or position name
    private String jobType;            // Can be either 'Full Time' or 'Part Time'
    private String staffName;          // Name of the staff member hired (initially empty)
    private String joiningDate;        // The date when staff joins (set later)
    private String qualification;      // Education or skill qualifications
    private String appointedBy;        // Person who appointed the staff
    private boolean joined;            // True if someone is hired for this post

    // Constructor to initialise vacancy details, not the actual staff yet
    public StaffHire(int vacancyNumber, String designation, String jobType, String staffName, String joiningDate, String qualification, String appointedBy, Boolean joined) {
        this.vacancyNumber = vacancyNumber;
        this.designation = designation;
        this.jobType = jobType;
        this.staffName = "";           // no one hired initially
        this.joiningDate = "";
        this.qualification = "";
        this.appointedBy = "";
        this.joined = false;
    }

    // Getter methods – basically just fetching private values
    public int getVacancyNumber() { 
        return vacancyNumber; 
    }

    public String getDesignation() { 
        return designation; 
    }

    public String getJobType() {
        return jobType; 
    }

    public String getStaffName() { 
        return staffName; 
    }

    public String getJoiningDate() { 
        return joiningDate; 
    }

    public String getQualification() { 
        return qualification; 
    }

    public String getAppointedBy() { 
        return appointedBy; 
    }

    public boolean getJoined() {
        return joined; 
    }

    // Setter methods – used to update or assign values later
    public void setStaffName(String staffName) { 
        this.staffName = staffName; 
    }

    public void setJoiningDate(String joiningDate) { 
        this.joiningDate = joiningDate; 
    }

    public void setQualification(String qualification) { 
        this.qualification = qualification; 
    }

    public void setAppointedBy(String appointedBy) { 
        this.appointedBy = appointedBy; 
    }

    public void setJoined(boolean joined) { 
        this.joined = joined; 
    }

    // This method lets us change the joined status if needed (like a toggle)
    public void amendJoinedStatus(boolean NewJoined) {
        if (this.joined != NewJoined) {
            this.joined = NewJoined;
            System.out.println("Joined status has been amended " + NewJoined);
        } else {
            System.out.println("Joined status is already "+ NewJoined);
        }
    }

    // Displaying all the common info — shows more if someone's been hired
    public void display() {
        System.out.println("Vacancy Number: " + vacancyNumber);
        System.out.println("Designation: " + designation);
        System.out.println("Job Type: " + jobType);

        if (joined) {
            System.out.println("Staff Name: " + staffName);
            System.out.println("Joining Date: " + joiningDate);
            System.out.println("Qualification: " + qualification);
            System.out.println("Appointed By: " + appointedBy);
        } else {
            System.out.println("No staff has joined for this vacancy.");
        }
    }
}
