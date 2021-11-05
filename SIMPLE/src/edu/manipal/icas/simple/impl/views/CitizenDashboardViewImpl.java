/**
 * 
 */
package edu.manipal.icas.simple.impl.views;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.views.CitizenDashboardView;
import net.miginfocom.swing.MigLayout;

/**
 * @author Mehsheed(syed.ahmed2@learner.manipal.edu)
 *
 * 
 */
public class CitizenDashboardViewImpl extends JFrame implements CitizenDashboardView {

	private JButton appForNewPPButton;
	private JButton redirect2AppstepsButton;
	private JButton logOutButton;
	
	private JComboBox<String> appCombo;
	
	
	private JButton checkApplicationStatusButton;
	 private String response [] [] = 
     	{
     			 
     			{"Application Id:",""},
     			{"Applicant's First name(Given Name):",""},
     			{"Applicant's Last name(Family Name):",""},
     			{"Birth Adress :",""},
     			{"Permanent Adress:",""},
     			{"Present Adress:",""},
     			{"Father's Name:",""},
     			{"Mother's Name:",""},
     			{"Application Creation date:",""},
     			{"Appointment Date:",""},
     			{"Application Type:",""},
     			{"Proceedings in respect of an offence alleged to have been committed by you pending before a criminal court in India:",""},
     			{"Any warrant or summons for your appearance been issued and pending before a court under any law for the time being in force:",""},
     			{"Any warrant for your arrest been issued by a court under any law for the time being in force? :",""},
     			{"Any order prohibiting your departure from India been made by any court :",""},
     			{"Have you, at any time during the period of five years immediately preceding the date of this application, been convicted by a court in India:",""},
     			{"For any offence involving moral turpitude and sentenced in respect thereof to imprisonment for not less than two years:",""},
     			{"Have you ever been refused/denied passport:",""},
     			{"Has your passport ever been impounded:",""},
     			{"Has your passport ever been revoked:",""},
     			{"Have you ever been granted citizenship by any other country:",""},
     			{"Have you ever held the passport of any other country at any time:",""},
     			{"Have you ever surrendered your Indian passpor:",""},
     			{"Have you ever applied for renunciation of Indian citizenship:",""},
     			{"Have you ever returned to India on Emergency Certificate (EC):",""},
     			{"Have you ever been deported from any country:",""},
     			{"Have you ever been repatriated from any country back to India:",""}
     	};
	
	
	
	
	public CitizenDashboardViewImpl()
	{
		appForNewPPButton = new JButton("Apply for New/Re-issue of Passport");
		redirect2AppstepsButton = new JButton("Click here");
		logOutButton = new JButton("Logout");
		String appId[] = {"100013","100343"};
		appCombo = new JComboBox(appId);
		checkApplicationStatusButton = new JButton("Click here");
		initialiseUI();
		
		
		
		
		
	}
	
	
	
	/**
	 * 
	 */
	private void initialiseUI() {
		
		
		JFrame frame = new JFrame("Citizen Dashboard");
		
		
		//JLabels:
		JLabel ctDB = new JLabel("Citizen Dashboard");
		ctDB.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel appLab  = new JLabel("Application Panel");
		appLab.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel faq = new JLabel("To check application process/FAQ: ");
		faq.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);
		JLabel checkApply = new JLabel("Select your Application Id.no:");
		checkApply.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		JLabel tLab = new JLabel("Track application status panel");
		tLab.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel supportLab = new JLabel("Support Panel");
		supportLab.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel supportClab = new JLabel("For any information and suggestions on Passport services, please call at 1800-258-1800 (Toll Free)");
		JLabel supportClickLab = new JLabel("To check application process/FAQ: ");
		JLabel applicationStatus = new JLabel("Application Status: ");
		JLabel appStatus = new JLabel("Initiated");
		applicationStatus.setFont(ResourceConstants.FONT_HEADING_BOLD);
		appStatus.setFont(ResourceConstants.FONT_HEADING_BOLD); 
		
		
		
		
		
		//JPanels
		JPanel appNew = new JPanel();
		JPanel support = new JPanel();
		JPanel tcp = new JPanel();
		
		
		
		//JTabs
        tp.addTab("Track/View Application", tcp);
        tp.addTab("Support", support);
        
        //JTable
        String column [] = {"Field","Response"};
        JTable viewApplicationTable = new JTable(response,column);
        viewApplicationTable.setDefaultEditor(Object.class, null);
	
        
        //JScrollpane
        JScrollPane apptab = new JScrollPane(viewApplicationTable);
        
       
        
        
        
		//Track application Tab Layout
		tcp.setLayout(new MigLayout("","[] [grow] [grow]","[] [] [] [grow]"));
		tcp.add(tLab,"span,center,wrap");
		tcp.add(checkApply, "growx,split");
		tcp.add(appCombo,"wrap");
		tcp.add(applicationStatus,"split");
		tcp.add(appStatus,"wrap");
		tcp.add(apptab);
		
		
		tcp.add(apptab,"growx,growy,span");
		
		
		
		
		
		//Apply Tab Layout
		appNew .setLayout(new MigLayout("","[] 10 [grow] [grow] [grow]","[] [] [] 100 [] 100 [] "));
		appNew.add(appLab,"span,center,wrap");
		
		appNew.add(appForNewPPButton,"sg 1,span,center");
		
		
	
		//Support Tab Layout
		support.setLayout(new MigLayout("","[] [] [] [grow]","[] [] [] 10 [] 10 []"));
		support.add(supportLab,"span,center,wrap");
		support.add(supportClab,"span,left,wrap");
		support.add(supportClickLab,"split");
		support.add(redirect2AppstepsButton,"wrap");
		
		
		
		
		
		
		//Frame Layout
		frame.setLayout(new MigLayout("", "[grow]","[][grow]"));
		frame.add(appForNewPPButton,"left");
		frame.add(logOutButton,"right,wrap");
		frame.add(tp,"growx,growy,span");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		
		
		
	}
	
	



	@Override
	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public JButton getAppForNewPPButton() {
		// TODO Auto-generated method stub
		return appForNewPPButton ;
	}

	
	

	@Override
	public JButton getredirect2AppstepsButton() {
		// TODO Auto-generated method stub
		return redirect2AppstepsButton = new JButton("Click here");
	}

	@Override
	public JButton getLogoutButton() {
		// TODO Auto-generated method stub
		return logOutButton = new JButton("Logout");
	}

	@Override
	public JButton checkApplicationStatusButton() {
		// TODO Auto-generated method stub
		return checkApplicationStatusButton;
	}



	@Override
	public String[][] getTableResponses() {
		// TODO Auto-generated method stub
		return response;
	}



	@Override
	public JComboBox<String> getApplicationIdComboBox() {
		// TODO Auto-generated method stub
		return appCombo;
	}
	
	

}