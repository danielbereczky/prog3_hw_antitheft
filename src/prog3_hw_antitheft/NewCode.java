package prog3_hw_antitheft;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCode extends JFrame {
    private String cfgcode;
    private JLabel statusLabel;
    private JPasswordField oldC;
    private JPasswordField newC;

    NewCode(String oldc) {
        cfgcode = oldc;
        this.setSize(500, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Change access code");
        oldC = new JPasswordField(10);
        newC = new JPasswordField(10);
        JLabel oldLabel = new JLabel("Old code:");
        JLabel newLabel = new JLabel("New code:");
        JButton changeBtn = new JButton("Change code");
        JPanel cPanel = new JPanel();
        statusLabel = new JLabel("Enter codes");
        cPanel.setLayout(new GridLayout(6, 0));
        cPanel.add(oldLabel);
        cPanel.add(oldC);
        cPanel.add(newLabel);
        cPanel.add(newC);
        cPanel.add(changeBtn);
        cPanel.add(statusLabel);

        changeBtn.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                cfgcode = codeChange(cfgcode, oldC.getText(), newC.getText());
                oldC.setText("");
                newC.setText("");
                // disposing the window after changing the code
                dispose();
            }
        });

        this.add(cPanel);
        this.setVisible(true);
    }

    public String getUpdatedCode() {
        return cfgcode;
    }

    private String codeChange(String systemOldCode, String oldcode, String newcode) {
        if (oldcode.equals(systemOldCode)) {
            System.out.println("Code changed to " + newcode);
            return newcode;
        }
        return systemOldCode;
    }
}
