import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TimeGoalGUI extends JFrame {
    private JTextField timeGoalField;
    private JButton setGoalButton;
    

    public TimeGoalGUI(){
        // Main Frame Properties
        setTitle("Time Goal");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        // Init GUI components
        timeGoalField = new JTextField(10);
        setGoalButton = new JButton("Set Goal");

        // Add new components
        add(new JLabel("Set Time Goal (in minutes):"));
        add(timeGoalField);
        add(setGoalButton);

        // ActionListener for setGoalButton
        setGoalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                onSetGoalButtonClicked();
            }
        });
    }

    // method for button clicked
    private void onSetGoalButtonClicked() {
        try {
            // Parse user input 
            int goal = Integer.parseInt(timeGoalField.getText());
            if (goal <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive number for the time goal.");
            } else {
                // TaskGUI opened here
                openTaskGUI(goal);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }
    // method to open TaskGUI
    private void openTaskGUI(int timeGoal) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TaskGUI taskGUI = new TaskGUI(timeGoal);
                taskGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                taskGUI.setVisible(true);

                dispose();
            }
        });
    }
    // getter for time goal
    public int getTimeGoal() {
        try {
            return Integer.parseInt(timeGoalField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
