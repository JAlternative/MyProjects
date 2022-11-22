import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainForm {
    private JPanel mainPanel;
    private JButton collapseButton;
    private JTextArea nameArea;
    private JTextArea surnameArea;
    private JTextArea patronymicArea;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel patronymicLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Collapse");
        frame.setSize(400, 200);

        frame.add(new MainForm().getMainPanel());

        frame.setVisible(true); //сделать окно видимысс
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //завершение программы
        frame.setLocationRelativeTo(null); //расположение в центре экрана
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainForm() {
        collapseButton.addActionListener(new AbstractAction() { //пытался прикрутить переключение между полями
            @Override
            public void actionPerformed(ActionEvent e) {
                nameArea.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyChar() == '\t'){
                            e.consume();
                            mainPanel.requestFocusInWindow();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });

                if (collapseButton.getText().equals("Collapse")) {
                    if (nameArea.getText().isEmpty() && surnameArea.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(mainPanel, "Поля должны быть заполнены !");
                    } else {
                        nameArea.setVisible(false);
                        nameLabel.setVisible(false);
                        surnameArea.setVisible(false);
                        surnameLabel.setVisible(false);

                        patronymicLabel.setText("Ф.И.О");
                        patronymicArea.setText(nameArea.getText() + " " + surnameArea.getText() + " " + patronymicArea.getText());
                        collapseButton.setText("Expand");
                    }
                } else if (collapseButton.getText().equals("Expand")){
                    String[] FIO = patronymicArea.getText().split("\\s");
                    if (FIO.length > 3){
                        JOptionPane.showMessageDialog(mainPanel,"Слишком много слов");
                    } else if (FIO.length < 2){
                        JOptionPane.showMessageDialog(mainPanel,"Чего-то не хватает");
                    }else {
                        nameArea.setText(FIO[0]);
                        surnameArea.setText(FIO[1]);
                        patronymicArea.setText("");
                        if (FIO.length == 3) {
                            patronymicArea.setText(FIO[2]);
                        }
                        nameArea.setVisible(true);
                        nameLabel.setVisible(true);
                        surnameArea.setVisible(true);
                        surnameLabel.setVisible(true);
                        patronymicLabel.setText("Отчество");
                        collapseButton.setText("Collapse");
                    }
                }
            }
        });
    }


}

