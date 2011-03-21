package ch.hsr.waktu.presentation.view.usermanagment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import ch.hsr.waktu.domain.User;
import ch.hsr.waktu.domain.UserProperties;
import ch.hsr.waktu.presentation.model.UserTreeModel;

public class UserManagmentView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988600126721632829L;
	public JPanel contentPane;
	
	private JTree userTree;
	private JPanel pnDetails;

	/**
	 * Create the frame.
	 */
	public UserManagmentView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		contentPane.add(splitPane, gbc_splitPane);
		
		JPanel pnTree = new JPanel();
		splitPane.setLeftComponent(pnTree);
		GridBagLayout gbl_pnTree = new GridBagLayout();
		gbl_pnTree.columnWidths = new int[]{32, 0};
		gbl_pnTree.rowHeights = new int[]{53, 0};
		gbl_pnTree.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnTree.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnTree.setLayout(gbl_pnTree);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnTree.add(scrollPane, gbc_scrollPane);
		
		UserTreeModel model = new UserTreeModel();
		userTree = new JTree(model);
		scrollPane.setViewportView(userTree);
		
		userTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				 TreePath path = userTree.getSelectionPath();
				    if (path == null)
				      return;
				    Object selectedNode = path.getLastPathComponent();
				    TreePath parentPath = path.getParentPath();
				    if (selectedNode instanceof UserProperties) {
				    	UserProperties userProperties = (UserProperties) selectedNode;
				    	if (userProperties.getDescription().equals("Daten")) {
				    		pnDetails = new UserDetailView((User)parentPath.getLastPathComponent());
				    	}
				    	else {
				    		pnDetails = new JPanel();
				    	}
				    }
				    
			}
		});
		
		pnDetails = new JPanel();
		splitPane.setRightComponent(pnDetails);
		GridBagLayout gbl_pnDetails = new GridBagLayout();
		gbl_pnDetails.columnWidths = new int[]{0};
		gbl_pnDetails.rowHeights = new int[]{0};
		gbl_pnDetails.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_pnDetails.rowWeights = new double[]{Double.MIN_VALUE};
		pnDetails.setLayout(gbl_pnDetails);
	}

}
