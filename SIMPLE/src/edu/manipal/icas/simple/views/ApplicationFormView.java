package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public interface ApplicationFormView extends View {
    public JTabbedPane getFormTabbedPane();
    public JButton getSubmitButton();
    public JButton getCancelButton();
}
