import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TaskGUI extends JFrame {
    private JTextField taskNameField;
    private JTextField taskTimeField;
    private JButton addTaskButton;
    private JLabel timeGoalLabel;
    private JLabel progressLabel;
    private JPanel taskListPanel;

    private int timeGoal;
    private int timeSpent;
    private ArrayList<String> taskList;

    public TaskGUI(int timeGoal) {
        this.timeGoal = timeGoal; 
        this.timeSpent = 0;
        this.taskList = new ArrayList<>();

        setTitle("Add Completed Task");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));

        rightPanel.add(timeGoalLabel);
        rightPanel.add(progressLabel);

        JScrollPane scrollPane = new JScrollPane(taskListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightPanel.add(scrollPane);

        add(leftPanel);
        add(rightPanel);

        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                onAddTaskButtonClicked();
            }
        });
    }

    private void onAddTaskButtonClicked(){
        String taskName = getTaskName();
        int taskTime = getTaskTime();

        updateProgress(taskTime);

        saveTask(taskName, taskTime);
        updateTaskDisplay();

        taskNameField.setText("");
        taskTimeField.setText("");
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

    public void updateProgress(int taskTime) {
        timeSpent += taskTime;
        progressLabel.setText("Progress: " + timeSpent + " minutes");
    }

    private void saveTask(String taskName, int taskTime){
        taskList.add(taskName + ": " + taskTime + " minutes");
    }

    private void updateTaskDisplay() {
        // Display each task under the TimeGoal and Progress labels
        taskListPanel.removeAll();
        for (String task : taskList) {
            JLabel taskLabel = new JLabel(task);
            taskListPanel.add(taskLabel);
        }

        // Repaint and revalidate the panel to reflect the changes
        taskListPanel.repaint();
        taskListPanel.revalidate();
    }

}
