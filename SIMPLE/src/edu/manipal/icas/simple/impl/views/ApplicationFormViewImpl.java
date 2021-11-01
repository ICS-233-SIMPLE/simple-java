package edu.manipal.icas.simple.impl.views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.manipal.icas.simple.views.ApplicationFormView;

public class ApplicationFormViewImpl extends JFrame implements ApplicationFormView {

    private JButton submitButton;
    private JButton cancelButton;
    private JTabbedPane tabbedPane;

    public ApplicationFormViewImpl() {
        super("Application Form");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Personal Details", new personalDetailsPanel());
        tabbedPane.addTab("Questions", new questionsPanel());
        tabbedPane.addTab("Documents", new documentsPanel());
        tabbedPane.addTab("Appointment", new appointmentPanel());
        tabbedPane.addTab("Payment", new paymentPanel());

        createNavigationBar();
    }

    private void createNavigationBar() {
        JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
    }

    @Override
    public JButton getSubmitButton() {
        return submitButton;
    }

    @Override
    public JButton getCancelButton() {
        return cancelButton;
    }

    @Override
    public JTabbedPane getFormTabbedPane() {
        return tabbedPane;
    }

    @Override
	public JFrame getFrame() {
		return this;
	}
}

class personalDetailsPanel extends JPanel {

}

class questionsPanel extends JPanel {

}

class documentsPanel extends JPanel {

}

class appointmentPanel extends JPanel {

}

class paymentPanel extends JPanel {

}
