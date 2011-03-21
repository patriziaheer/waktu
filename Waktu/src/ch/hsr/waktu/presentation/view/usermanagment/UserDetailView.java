package ch.hsr.waktu.presentation.view.usermanagment;

import javax.swing.JPanel;

import ch.hsr.waktu.domain.User;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UserDetailView extends JPanel {

	private static final long serialVersionUID = -4761687566782633951L;
	private User user;
	
	/**
	 * Create the panel.
	 */
	public UserDetailView(User user) {
		this.user = user;
		init();
	}
	
	private void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblUserDetailsFor = new JLabel("User Details for User");
		GridBagConstraints gbc_lblUserDetailsFor = new GridBagConstraints();
		gbc_lblUserDetailsFor.insets = new Insets(0, 0, 0, 5);
		gbc_lblUserDetailsFor.gridx = 0;
		gbc_lblUserDetailsFor.gridy = 0;
		add(lblUserDetailsFor, gbc_lblUserDetailsFor);
		
		JLabel lblUserName = new JLabel(user.getName());
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.gridx = 2;
		gbc_lblUserName.gridy = 0;
		add(lblUserName, gbc_lblUserName);
	}

}
