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
    }
}
