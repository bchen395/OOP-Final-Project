import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TimeGoalGUI extends JFrame {
    private JTextField timeGoalField;
    private JButton setGoalButton;

    public TimeGoalGUI(){
        setTitle("Time Goal");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        timeGoalField = new JTextField(10);
        setGoalButton = new JButton("Set Goal");

        add(new JLabel("Set Time Goal (in minutes):"));
        add(timeGoalField);
        add(setGoalButton);

        setGoalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                onSetGoalButtonClicked();
            }
        });
    }

    private void onSetGoalButtonClicked() {
        try {
            int goal = Integer.parseInt(timeGoalField.getText());
            if (goal <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive number for the time goal.");
            } else {
                openTaskGUI(goal);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

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
}
