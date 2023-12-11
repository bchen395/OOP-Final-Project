import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskGUI extends JFrame {
    private JTextField taskNameField;
    private JTextField taskTimeField;
    private JButton addTaskButton;
    private JLabel timeGoalLabel;
    private JLabel progressLabel;

    private int timeGoal;
    private int timeSpent;

    public TaskGUI(int TimeGoal) {
        this.timeGoal = timeGoal;
        this.timeSpent = 0;

        setTitle("Add Completed Task");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        taskNameField = new JTextField(10);
        taskTimeField = new JTextField(10);
        addTaskButton = new JButton("Add Task");

        leftPanel.add(new JLabel("Task Name:"));
        leftPanel.add(taskNameField);
        leftPanel.add(new JLabel("Task Time (in minutes):"));
        leftPanel.add(taskTimeField);
        leftPanel.add(addTaskButton);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        timeGoalLabel = new JLabel("Time Goal: " + timeGoal + " minutes");
        progressLabel = new JLabel("Progress: " + timeSpent + " minutes");

        rightPanel.add(timeGoalLabel);
        rightPanel.add(progressLabel);

        add(leftPanel);
        add(rightPanel);
    }

    public String getTaskName() {
        return taskNameField.getText();
    }

    public int getTaskTime() {
        try {
            return Integer.parseInt(taskTimeField.getText());
        } catch (NumberFormatException e) {
            return 0; 
        }
    }

    public void setAddTaskButtonListener(ActionListener listener) {
        addTaskButton.addActionListener(listener);
    }

    public void updateProgress(int taskTime) {
        timeSpent += taskTime;
        progressLabel.setText("Progress: " + timeSpent + " minutes");
    }
}
