// Importing AWT for layout and component stuff, and Swing for GUI components
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

// Main class for the Recruitment System GUI
public class RecruitmentSystemGUI {

    // Frame is the main window of our app
    private JFrame frame;

    // This ArrayList will store all the staff we add (both full-time and part-time)
    private ArrayList<StaffHire> staffList = new ArrayList<>();

    // Constructor - this is where all the magic begins, sets up the window and main buttons
    public RecruitmentSystemGUI() {
        frame = new JFrame("Recruitment System"); // setting title of the window
        frame.setSize(400, 300); // window size (width, height)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure program closes when you click X
        frame.setLocationRelativeTo(null); // centers the window on the screen

        // Main panel that holds all the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column, with spacing

        // Creating buttons for different operations
        JButton btnFullTime = new JButton("Full-Time Staff");
        JButton btnPartTime = new JButton("Part-Time Staff");
        JButton btnDisplay = new JButton("Display All Staff");
        JButton btnExit = new JButton("Exit");

        // Adding buttons to panel one by one
        panel.add(btnFullTime);
        panel.add(btnPartTime);
        panel.add(btnDisplay);
        panel.add(btnExit);

        // Adding panel to frame and making it visible
        frame.add(panel);
        frame.setVisible(true);

        // Adding actions when buttons are clicked
        btnFullTime.addActionListener(e -> showFullTimeOptions());
        btnPartTime.addActionListener(e -> showPartTimeOptions());
        btnDisplay.addActionListener(e -> displayAll());
        btnExit.addActionListener(e -> System.exit(0)); // close app when exit button is pressed
    }

    // Handles the Full-Time options menu
    private void showFullTimeOptions() {
        String[] options = {"Add Staff", "Cancel"};
        int choice = JOptionPane.showOptionDialog(null, "Select Full-Time Staff Option:", "Full-Time Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            showFullTimeForm(); // if user picks Add Staff
        }
    }

    // Handles the Part-Time options menu
    private void showPartTimeOptions() {
        String[] options = {"Add Staff", "Terminate Staff", "Cancel"};
        int choice = JOptionPane.showOptionDialog(null, "Select Part-Time Staff Option:", "Part-Time Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        // Depending on what user clicks
        if (choice == 0) {
            showPartTimeForm(); // Add staff
        } else if (choice == 1) {
            terminatePartTime(); // Terminate staff
        }
    }

    // Full-Time form popup
    private void showFullTimeForm() {
        // Create input fields using a helper method
        JTextField[] fields = createInputFields("Vacancy No", "Designation", "Job Type", "Staff Name", "Joining Date", "Qualification", "Appointed By", "Salary", "Weekly Hours");

        // Show form in dialog
        int result = JOptionPane.showConfirmDialog(null, fields, "Full-Time Staff Form", JOptionPane.OK_CANCEL_OPTION);

        // If user clicked OK
        if (result == JOptionPane.OK_OPTION) {
            try {
                // Parse Vacancy No and check if it already exists
                int vacancyNo = Integer.parseInt(fields[0].getText());
                for (StaffHire staff : staffList) {
                    if (staff.getVacancyNumber() == vacancyNo) {
                        JOptionPane.showMessageDialog(null, "Vacancy Number already exists.");
                        return;
                    }
                }

                // Create a new FullTimeStaffHire object
                FullTimeStaffHire fullTime = new FullTimeStaffHire(
                    vacancyNo,
                    fields[1].getText(), fields[2].getText(), "", "", "", "", false,
                    Double.parseDouble(fields[7].getText()),
                    Integer.parseInt(fields[8].getText())
                );

                // Setting values
                fullTime.setStaffName(fields[3].getText());
                fullTime.setJoiningDate(fields[4].getText());
                fullTime.setQualification(fields[5].getText());
                fullTime.setAppointedBy(fields[6].getText());
                fullTime.setJoined(true);

                // Add to list
                staffList.add(fullTime);
                JOptionPane.showMessageDialog(null, "Full-Time Staff Added!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid Input. Try Again."); // Handles invalid input
            }
        }
    }

    // Part-Time form popup
    private void showPartTimeForm() {
        JTextField[] fields = createInputFields("Vacancy No", "Designation", "Job Type", "Staff Name", "Joining Date", "Qualification", "Appointed By", "Working Hours", "Wages Per Hour", "Shift");

        int result = JOptionPane.showConfirmDialog(null, fields, "Part-Time Staff Form", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int vacancyNo = Integer.parseInt(fields[0].getText());
                for (StaffHire staff : staffList) {
                    if (staff.getVacancyNumber() == vacancyNo) {
                        JOptionPane.showMessageDialog(null, "Vacancy Number already exists.");
                        return;
                    }
                }

                PartTimeStaffHire partTime = new PartTimeStaffHire(
                    vacancyNo,
                    fields[1].getText(), fields[2].getText(), "", "", "", "", false,
                    Integer.parseInt(fields[7].getText()),
                    Double.parseDouble(fields[8].getText()),
                    fields[9].getText()
                );

                partTime.setStaffName(fields[3].getText());
                partTime.setJoiningDate(fields[4].getText());
                partTime.setQualification(fields[5].getText());
                partTime.setAppointedBy(fields[6].getText());
                partTime.setJoined(true);

                staffList.add(partTime);
                JOptionPane.showMessageDialog(null, "Part-Time Staff Added!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid Input. Try Again.");
            }
        }
    }

    // Function to terminate part-time staff based on vacancy number
    private void terminatePartTime() {
        String input = JOptionPane.showInputDialog("Enter Vacancy Number to Terminate:");
        try {
            int vacancy = Integer.parseInt(input);
            for (StaffHire staff : staffList) {
                if (staff instanceof PartTimeStaffHire pts && pts.getVacancyNumber() == vacancy) {
                    if (pts.getJoined()) {
                        pts.terminate(); // actually terminate
                        JOptionPane.showMessageDialog(null, "Part-Time Staff Terminated.");
                    } else {
                        JOptionPane.showMessageDialog(null, "This staff member has not joined yet.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "No Part-Time Staff Found.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Input.");
        }
    }

    // Display all staff in a new window
    private void displayAll() {
        if (staffList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No staff to display.");
            return;
        }

        // New window to show staff details
        JFrame displayFrame = new JFrame("All Staff Details");
        displayFrame.setSize(500, 400);
        displayFrame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // we don't want user to change anything

        StringBuilder output = new StringBuilder();
        for (StaffHire staff : staffList) {
            output.append("\n-----------------------------\n");
            output.append(staff.getClass().getSimpleName()).append("\n");
            output.append("Vacancy: ").append(staff.getVacancyNumber()).append("\n");
            output.append("Designation: ").append(staff.getDesignation()).append("\n");
            output.append("Joined: ").append(staff.getJoined()).append("\n");
            if (staff.getJoined()) {
                output.append("Staff Name: ").append(staff.getStaffName()).append("\n");
            }
        }

        textArea.setText(output.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        displayFrame.add(scrollPane);
        displayFrame.setVisible(true);
    }

    // Helper function to generate multiple input fields with labels
    private JTextField[] createInputFields(String... labels) {
        JTextField[] fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            fields[i] = new JTextField(10);
            fields[i].setBorder(BorderFactory.createTitledBorder(labels[i])); // Adds a nice label around each field
        }
        return fields;
    }

    // Main method - starts everything
    public static void main(String[] args) {
        // Launch GUI safely using Swing thread
        SwingUtilities.invokeLater(RecruitmentSystemGUI::new);
    }
}
