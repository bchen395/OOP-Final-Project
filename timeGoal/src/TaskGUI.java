import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskGUI extends JFrame {
    private JTextField taskNameField;
    private JTextField taskTimeField;
    private JButton addTaskButton;

    public TaskGUI() {
        setTitle("Add Completed Task");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        taskNameField = new JTextField(10);
        taskTimeField = new JTextField(10);
        addTaskButton = new JButton("Add Task");

        add(new JLabel("Task Name:"));
        add(taskNameField);
        add(new JLabel("Task Time (in minutes):"));
        add(taskTimeField);
        add(addTaskButton);
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
}
