import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TaskGUI extends JFrame {
    // GUI components
    private JTextField taskNameField;
    private JTextField taskTimeField;
    private JButton addTaskButton;
    private JLabel timeGoalLabel;
    private JLabel progressLabel;
    private JPanel taskListPanel;
    private JButton newTimeGoalButton;

    // Task variables
    private int timeGoal;
    private int timeSpent;
    private ArrayList<String> taskList;

    public TaskGUI(int timeGoal) {
        // init task variables
        this.timeGoal = timeGoal; 
        this.timeSpent = 0;
        this.taskList = new ArrayList<>();

        // Main Frame Properties
        setTitle("Add Completed Task");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // Left Panel components
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

        newTimeGoalButton = new JButton("New Time Goal?");
        leftPanel.add(newTimeGoalButton);

        // Right panel components
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        timeGoalLabel = new JLabel("Time Goal: " + timeGoal + " minutes");
        progressLabel = new JLabel("Progress: " + timeSpent + " minutes");

        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));

        rightPanel.add(timeGoalLabel);
        rightPanel.add(progressLabel);
        
        // task list scrollable
        JScrollPane scrollPane = new JScrollPane(taskListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightPanel.add(scrollPane);

        // add the components
        add(leftPanel);
        add(rightPanel);

        // action listeners for add task and new time goal
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                onAddTaskButtonClicked();
            }
        });

        newTimeGoalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNewTimeGoalButtonClicked();
            }
        });
    }

    // method for addTaskButton
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
            // create a panel for each task with a delete button
            JPanel taskPanel = new JPanel();
            taskPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            taskPanel.setBorder(BorderFactory.createEmptyBorder());


            JLabel taskLabel = new JLabel(task);

            JButton deleteButton = new JButton("X");
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                onDeleteTaskButtonClicked(task);
                }
            });
            
            taskPanel.add(taskLabel);
            taskPanel.add(deleteButton);

            taskListPanel.add(taskPanel);

        }

        taskListPanel.add(Box.createVerticalGlue());

        // Repaint and revalidate the panel to reflect the changes
        taskListPanel.repaint();
        taskListPanel.revalidate();
    }

    private void onDeleteTaskButtonClicked(String task) {
        // Extract the task name and time from the task string (format: "Task Name: Task Time minutes")
        String[] taskParts = task.split(":");
        String taskName = taskParts[0].trim();
        int taskTime = Integer.parseInt(taskParts[1].replaceAll("\\D", "").trim());
    
        // Remove the task from the taskList
        taskList.remove(task);
    
        // Update the display after removing the task
        updateTaskDisplay();
    
        // Update the progress label by subtracting the time spent on the deleted task
        timeSpent -= taskTime;
        progressLabel.setText("Progress: " + timeSpent + " minutes");
    }

    private void onNewTimeGoalButtonClicked() {
        String newTimeGoalString = JOptionPane.showInputDialog(this, "Enter new time goal (in minutes):");

        try {
            int newTimeGoal = Integer.parseInt(newTimeGoalString);
            if (newTimeGoal >= 0) {
                timeGoal = newTimeGoal;
                timeGoalLabel.setText("Time Goal: " + timeGoal + " minutes");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid non-negative integer.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid non-negative integer.");
        }
    }


}
