package edu.manipal.icas.simple.impl.views;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.views.ApplicationFormView;

public class ApplicationFormViewImpl extends JFrame implements ApplicationFormView {

    private JButton submitButton;
    private JButton cancelButton;
    private JTabbedPane tabbedPane;
    private GridBagConstraints gbc;

    public ApplicationFormViewImpl() {
        super("Application Form");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(255,80,80,200));
        cancelButton = new JButton("Cancel");
        gbc = new GridBagConstraints();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Personal Details", new personalDetailsPanel());
        tabbedPane.addTab("Questions", createQuestionsPanel());
        tabbedPane.addTab("Documents", new documentsPanel());
        tabbedPane.addTab("Appointment", new appointmentPanel());
        tabbedPane.addTab("Payment", new paymentPanel());

        createNavigationBar();
    }

    private void createNavigationBar() {
        JPanel navBar = new JPanel(new GridBagLayout());
        navBar.setBackground(new Color(217,217,217));
        gbcReset();
        
        gbc.anchor = GridBagConstraints.NORTHEAST;

        gbc.insets = new Insets(10,0,0,20);
        place(getCancelButton(),navBar,0,2,false);

        gbc.insets = new Insets(10,1170,0,20);
        place(getSubmitButton(),navBar,0,1,false);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(20,5,5,5);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;

        place(tabbedPane,navBar,0,1,false);

        add(navBar);
    }

    private JPanel createQuestionsPanel() {
        JPanel questionsPanel = new JPanel(new GridBagLayout());
        gbcReset();

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(25,0,10,0);

        JLabel quesOne = new JLabel("1) Provide the following details if there are any criminal proceedings pending against the applicant.");
        quesOne.setFont(ResourceConstants.FONT_LABEL_BOLD);
        JLabel quesTwo = new JLabel("2) Provide the following details if the applicant has been convicted by a court in India.");
        quesTwo.setFont(ResourceConstants.FONT_LABEL_BOLD);
        JLabel quesThree = new JLabel("3) Provide the following details if the applicant has been refused/denied passport.");
        quesThree.setFont(ResourceConstants.FONT_LABEL_BOLD);
        JLabel quesFour = new JLabel("4) Provide the following details if applicant has applied for or been granted foreign citizenship");
        quesFour.setFont(ResourceConstants.FONT_LABEL_BOLD);
        JLabel quesFive = new JLabel("5) Provide the following details if applicant has returned to India on Emergency Certificate.");
        quesFive.setFont(ResourceConstants.FONT_LABEL_BOLD);
        
        place(quesOne,questionsPanel,0,0,false);
        place(quesTwo,questionsPanel,5,0,false);
        place(quesThree,questionsPanel,8,0,false);
        place(quesFour,questionsPanel,12,0,false);
        place(quesFive,questionsPanel,17,0,false);
        
        gbc.insets = new Insets(3,25,3,0);

        place(new JLabel("Are any proceedings in respect of an offence alleged to have been committed by you pending before a criminal court in India?"),questionsPanel,1,0,true);
        place(new JLabel("Has any warrant or summons for your appearance been issued and pending before a court under any law for the time being in force?"),questionsPanel,2,0,true);
        place(new JLabel("Has a warrant for your arrest been issued by a court under any law for the time being in force?"),questionsPanel,3,0,true);
        place(new JLabel("Has an order prohibiting your departure from India been made by any court?"),questionsPanel,4,0,true);
        
        place(new JLabel("Have you, at any time during the period of five years immediately preceding the date of this application, been convicted by a court in India"),questionsPanel,6,0,true);
        place(new JLabel("for any offence involving moral turpitude and sentenced in respect thereof to imprisonment for not less than two years?"),questionsPanel,7,0,true);
        
        place(new JLabel("Have you ever been refused/denied passport? "),questionsPanel,9,0,true);
        place(new JLabel("Has your passport ever been impounded?"),questionsPanel,10,0,true);
        place(new JLabel("Has your passport ever been revoked?"),questionsPanel,11,0,true);

        place(new JLabel("Have you ever been granted citizenship by any other country?"),questionsPanel,13,0,true);
        place(new JLabel("Have you ever held the passport of any other country at any time?"),questionsPanel,14,0,true);
        place(new JLabel("Have you ever surrendered your Indian passport?"),questionsPanel,15,0,true);
        place(new JLabel("Have you ever applied for renunciation of Indian citizenship?"),questionsPanel,16,0,true);

        place(new JLabel("Have you ever returned to India on Emergency Certificate (EC)?"),questionsPanel,18,0,true);
        place(new JLabel("Have you ever been deported from any country?"),questionsPanel,19,0,true);
        place(new JLabel("Have you ever been repatriated from any country back to India?"),questionsPanel,20,0,true);


        JRadioButton quesOneAyes = new JRadioButton("Yes");
        JRadioButton quesOneAno = new JRadioButton("No");
        JRadioButton quesOneByes = new JRadioButton("Yes");
        JRadioButton quesOneBno = new JRadioButton("No");
        JRadioButton quesOneCyes = new JRadioButton("Yes");
        JRadioButton quesOneCno = new JRadioButton("No");
        JRadioButton quesOneDyes = new JRadioButton("Yes");
        JRadioButton quesOneDno = new JRadioButton("No");

        JRadioButton quesTwoAyes = new JRadioButton("Yes");
        JRadioButton quesTwoAno = new JRadioButton("No");

        JRadioButton quesThreeAyes = new JRadioButton("Yes");
        JRadioButton quesThreeAno = new JRadioButton("No");
        JRadioButton quesThreeByes = new JRadioButton("Yes");
        JRadioButton quesThreeBno = new JRadioButton("No");
        JRadioButton quesThreeCyes = new JRadioButton("Yes");
        JRadioButton quesThreeCno = new JRadioButton("No");

        JRadioButton quesFourAyes = new JRadioButton("Yes");
        JRadioButton quesFourAno = new JRadioButton("No");
        JRadioButton quesFourByes = new JRadioButton("Yes");
        JRadioButton quesFourBno = new JRadioButton("No");
        JRadioButton quesFourCyes = new JRadioButton("Yes");
        JRadioButton quesFourCno = new JRadioButton("No");
        JRadioButton quesFourDyes = new JRadioButton("Yes");
        JRadioButton quesFourDno = new JRadioButton("No");

        JRadioButton quesFiveAyes = new JRadioButton("Yes");
        JRadioButton quesFiveAno = new JRadioButton("No");
        JRadioButton quesFiveByes = new JRadioButton("Yes");
        JRadioButton quesFiveBno = new JRadioButton("No");
        JRadioButton quesFiveCyes = new JRadioButton("Yes");
        JRadioButton quesFiveCno = new JRadioButton("No");

        ButtonGroup quesOneA = new ButtonGroup();
        quesOneA.add(quesOneAyes);
        quesOneA.add(quesOneAno);
        ButtonGroup quesOneB = new ButtonGroup();
        quesOneB.add(quesOneByes);
        quesOneB.add(quesOneBno);
        ButtonGroup quesOneC = new ButtonGroup();
        quesOneC.add(quesOneCyes);
        quesOneC.add(quesOneCno);
        ButtonGroup quesOneD = new ButtonGroup();
        quesOneD.add(quesOneDyes);
        quesOneD.add(quesOneDno);

        ButtonGroup quesTwoA = new ButtonGroup();
        quesTwoA.add(quesTwoAyes);
        quesTwoA.add(quesTwoAno);

        ButtonGroup quesThreeA = new ButtonGroup();
        quesThreeA.add(quesThreeAyes);
        quesThreeA.add(quesThreeAno);
        ButtonGroup quesThreeB = new ButtonGroup();
        quesThreeB.add(quesThreeByes);
        quesThreeB.add(quesThreeBno);
        ButtonGroup quesThreeC = new ButtonGroup();
        quesThreeC.add(quesThreeCyes);
        quesThreeC.add(quesThreeCno);

        ButtonGroup quesFourA = new ButtonGroup();
        quesFourA.add(quesFourAyes);
        quesFourA.add(quesFourAno);
        ButtonGroup quesFourB = new ButtonGroup();
        quesFourB.add(quesFourByes);
        quesFourB.add(quesFourBno);
        ButtonGroup quesFourC = new ButtonGroup();
        quesFourC.add(quesFourCyes);
        quesFourC.add(quesFourCno);
        ButtonGroup quesFourD = new ButtonGroup();
        quesFourD.add(quesFourDyes);
        quesFourD.add(quesFourDno);

        ButtonGroup quesFiveA = new ButtonGroup();
        quesFiveA.add(quesFiveAyes);
        quesFiveA.add(quesFiveAno);
        ButtonGroup quesFiveB = new ButtonGroup();
        quesFiveB.add(quesFiveByes);
        quesFiveB.add(quesFiveBno);
        ButtonGroup quesFiveC = new ButtonGroup();
        quesFiveC.add(quesFiveCyes);
        quesFiveC.add(quesFiveCno);

        gbc.insets = new Insets(0,220,0,0);

        place(quesOneAyes, questionsPanel, 1,1,false);
	    place(quesOneByes, questionsPanel, 2,1,false);
	    place(quesOneCyes, questionsPanel, 3,1,false);
	    place(quesOneDyes, questionsPanel, 4,1,false);

	    place(quesTwoAyes, questionsPanel, 7,1,false);

        place(quesThreeAyes, questionsPanel, 9,1,false);
	    place(quesThreeByes, questionsPanel, 10,1,false);
	    place(quesThreeCyes, questionsPanel, 11,1,false);

        place(quesFourAyes, questionsPanel, 13,1,false);
	    place(quesFourByes, questionsPanel, 14,1,false);
	    place(quesFourCyes, questionsPanel, 15,1,false);
	    place(quesFourDyes, questionsPanel, 16,1,false);

        place(quesFiveAyes, questionsPanel, 18,1,false);
	    place(quesFiveByes, questionsPanel, 19,1,false);
	    place(quesFiveCyes, questionsPanel, 20,1,false);
        
        gbc.insets = new Insets(0,60,0,0);

        place(quesOneAno, questionsPanel, 1,2,false);
        place(quesOneBno, questionsPanel, 2,2,false);
        place(quesOneCno, questionsPanel, 3,2,false);
        place(quesOneDno, questionsPanel, 4,2,false);

        place(quesTwoAno, questionsPanel, 7,2,false);

        place(quesThreeAno, questionsPanel, 9,2,false);
        place(quesThreeBno, questionsPanel, 10,2,false);
        place(quesThreeCno, questionsPanel, 11,2,false);
        
        place(quesFourAno, questionsPanel, 13,2,false);
        place(quesFourBno, questionsPanel, 14,2,false);
        place(quesFourCno, questionsPanel, 15,2,false);
        place(quesFourDno, questionsPanel, 16,2,false);

        place(quesFiveAno, questionsPanel, 18,2,false);
        place(quesFiveBno, questionsPanel, 19,2,false);
        place(quesFiveCno, questionsPanel, 20,2,false);


        return questionsPanel;
    }

    private void place(JComponent child, JComponent parent, int y, int x, boolean changeFont) {
        gbc.gridy = y;
        gbc.gridx = x;

        if(changeFont)
            child.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);

        parent.add(child, gbc);
    }

    private void gbcReset() {
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
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

class documentsPanel extends JPanel {

}

class appointmentPanel extends JPanel {

}

class paymentPanel extends JPanel {

}