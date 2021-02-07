package main.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class Dispatcher1 {

	private JFrame frmPmanger;
	private JTextField txtProcessName;
	private JTextField pNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dispatcher1 window = new Dispatcher1();
					window.frmPmanger.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dispatcher1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPmanger = new JFrame();
		frmPmanger.setTitle("Pmanger");
		frmPmanger.setResizable(false);
		frmPmanger.setBounds(100, 100, 933, 543);
		frmPmanger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPmanger.getContentPane().setLayout(null);
		
		priorityItem process1 = new priorityItem("MainProcess",1);
		priorityItem process2 = new priorityItem("subProcess",2);
		priorityItem process3 = new priorityItem();
		priorityItem process4 = new priorityItem("aGoodProcess",1);
		priorityItem process5 = new priorityItem("lessGoodProcess",2);
		
		DefaultListModel<String> listModelReady;
		DefaultListModel<String> listModelRun;
		DefaultListModel<String> listModelBlocked;
		DefaultListModel<String> listModelTerminate;
		
		listModelReady = new DefaultListModel<>();
		listModelReady.addElement(process1.getProcessName() + " " 
		    + Integer.toString(process1.getPriority()));
		listModelReady.addElement(process2.getProcessName() + " " 
		    + Integer.toString(process2.getPriority()));
		listModelReady.addElement(process3.getProcessName() + " " 
		    + Integer.toString(process3.getPriority()));
		listModelReady.addElement(process4.getProcessName() + " " 
		    + Integer.toString(process4.getPriority()));
		listModelReady.addElement(process5.getProcessName() + " " 
		    + Integer.toString(process5.getPriority()));
		listModelRun = new DefaultListModel<>();
		listModelBlocked = new DefaultListModel<>();
		listModelTerminate = new DefaultListModel<>(); 
		
		JList<String> readyList = new JList<>(listModelReady);
		readyList.setBounds(36, 39, 232, 300);
		frmPmanger.getContentPane().add(readyList);
		
		JList<String> runningList = new JList<>(listModelRun);
		runningList.setBounds(353, 39, 232, 50);
		frmPmanger.getContentPane().add(runningList);
		
		JList<String> blockedList = new JList<>(listModelBlocked);
		blockedList.setBounds(353, 157, 232, 186);
		frmPmanger.getContentPane().add(blockedList);
		
		JList<String> terminateList = new JList<>(listModelTerminate);
		terminateList.setBounds(659, 39, 232, 300);
		frmPmanger.getContentPane().add(terminateList);
		
		txtProcessName = new JTextField();
		txtProcessName.setText(null);
		txtProcessName.setBounds(36, 424, 193, 43);
		frmPmanger.getContentPane().add(txtProcessName);
		txtProcessName.setColumns(10);
		
		JButton btnReady = new JButton("Ready");
		btnReady.setForeground(Color.BLACK);
		btnReady.setBackground(Color.ORANGE);
		btnReady.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReady.setBounds(499, 396, 115, 29);
		frmPmanger.getContentPane().add(btnReady);
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (blockedList.getSelectedValue() == null) {
						blockedList.setSelectedIndex(0);
					}
					listModelReady.addElement(blockedList.getSelectedValue().toString());
					listModelBlocked.removeElement(blockedList.getSelectedValue());
				}
				catch(Exception noReady) {
					JOptionPane.showMessageDialog(null, "No Processes are awaiting ready status.");
				}
			}
		});
		
		JButton btnRun = new JButton("Run");
		btnRun.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRun.setBackground(Color.GREEN);
		btnRun.setBounds(629, 396, 115, 29);
		frmPmanger.getContentPane().add(btnRun);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (readyList.getSelectedValue() == null) {
						readyList.setSelectedIndex(0);
						
						if (readyList.getModel().getSize() > 1) {
						    int j = 0;
							String temp1 = readyList.getSelectedValue().toString();
						    int x = Integer.parseInt(temp1.substring(temp1.length() - 1));
						    for (int i = 1; i < readyList.getModel().getSize(); i++) {
						    	readyList.setSelectedIndex(i);
							    String temp2 = readyList.getSelectedValue().toString();
							    int y = Integer.parseInt(temp2.substring(temp2.length() - 1));
							    if (y < x) {
							    	j=i;
							    }
						    }
						    readyList.setSelectedIndex(j);
						}
					}
					if (runningList.getSelectedValue() == null) {
						runningList.setSelectedIndex(0);
					}
					if (runningList.getSelectedValue() == null) {
						
					    listModelRun.addElement(readyList.getSelectedValue().toString());
					    listModelReady.removeElement(readyList.getSelectedValue());
					}
					else {
						JOptionPane.showMessageDialog(null, "The processor is in use.");
						readyList.clearSelection();
						runningList.clearSelection();
					}
				}
				catch(Exception noRun) {
					JOptionPane.showMessageDialog(null, "No Processes are awaiting run status.");
				}
			}
		});
		
		JButton btnBlock = new JButton("Block");
		btnBlock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBlock.setBackground(Color.RED);
		btnBlock.setBounds(499, 438, 115, 29);
		frmPmanger.getContentPane().add(btnBlock);
		btnBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (runningList.getSelectedValue() == null) {
						runningList.setSelectedIndex(0);
					}
					listModelBlocked.addElement(runningList.getSelectedValue().toString());
					listModelRun.removeElement(runningList.getSelectedValue());
				}
				catch(Exception noReady) {
					JOptionPane.showMessageDialog(null, "No Processes can be blocked.");
				}
			}
		});
		
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtProcessName.getText().length() != 0) {
						if (pNum.getText().length() != 0) {
						    priorityItem process = new priorityItem(txtProcessName.getText(),Integer.parseInt(pNum.getText()));
						    listModelReady.addElement(process.getProcessName() + " " 
							        + Integer.toString(process.getPriority()));
						    txtProcessName.setText(null);
						    pNum.setText(null);
						} 
						else {
							priorityItem process = new priorityItem(txtProcessName.getText());
							listModelReady.addElement(process.getProcessName() + " " 
							        + Integer.toString(process.getPriority()));
						    txtProcessName.setText(null);
						    pNum.setText(null);
						}
					}
					else {
						if (pNum.getText().length() != 0) {
						    priorityItem process = new priorityItem("generic",Integer.parseInt(pNum.getText()));
						    listModelReady.addElement(process.getProcessName() + " " 
							        + Integer.toString(process.getPriority()));
						    txtProcessName.setText(null);
						    pNum.setText(null);
						}
						else {
							priorityItem process = new priorityItem();
							listModelReady.addElement(process.getProcessName() + " " 
							        + Integer.toString(process.getPriority()));
						    txtProcessName.setText(null);
						    pNum.setText(null);
						}
					}
				}
				catch(Exception noEnteredProcess) {
					JOptionPane.showMessageDialog(null, "No Processes have been added.");
				}
			}
		});
		btnAdd.setBounds(369, 424, 115, 43);
		frmPmanger.getContentPane().add(btnAdd);
		
		JButton btnTerminate = new JButton("Terminate");
		btnTerminate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTerminate.setBackground(Color.BLUE);
		btnTerminate.setBounds(776, 424, 115, 29);
		frmPmanger.getContentPane().add(btnTerminate);
		btnTerminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (runningList.getSelectedValue() != null) {
						listModelTerminate.addElement(runningList.getSelectedValue());
						listModelRun.removeElement(runningList.getSelectedValue());
						
					}
					else if (readyList.getSelectedValue() != null) {
						listModelTerminate.addElement(readyList.getSelectedValue());
						listModelReady.removeElement(readyList.getSelectedValue());
					}
					else if (blockedList.getSelectedValue() != null) {
						listModelTerminate.addElement(blockedList.getSelectedValue());
					    listModelBlocked.removeElement(blockedList.getSelectedValue());
					}
					else if (runningList.getModel().getSize() >= 1){
						runningList.setSelectedIndex(0);

						listModelTerminate.addElement(runningList.getSelectedValue());
						listModelRun.removeElement(runningList.getSelectedValue());
                        readyList.setSelectedIndex(0);
		 				
						if (readyList.getModel().getSize() > 1) {
						    int j = 0;
							String temp1 = readyList.getSelectedValue().toString();
						    int x = Integer.parseInt(temp1.substring(temp1.length() - 1));
						    for (int i = 1; i < readyList.getModel().getSize(); i++) {
						    	readyList.setSelectedIndex(i);
							    String temp2 = readyList.getSelectedValue().toString();
							    int y = Integer.parseInt(temp2.substring(temp2.length() - 1));
							    if (y < x) {
							    	j=i;
							    }
						    }
						    readyList.setSelectedIndex(j);
						}
						listModelRun.addElement(readyList.getSelectedValue().toString());
					    listModelReady.removeElement(readyList.getSelectedValue());
					}
					readyList.clearSelection();
					runningList.clearSelection();
				}
				catch(Exception noReady) {
					JOptionPane.showMessageDialog(null, "No Processes can be terminated.");
				}
			}
		});
		
		
		JButton btnTimeslice = new JButton("Timeslice");
		btnTimeslice.setForeground(Color.BLACK);
		btnTimeslice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimeslice.setBackground(Color.ORANGE);
		btnTimeslice.setBounds(629, 441, 115, 29);
		frmPmanger.getContentPane().add(btnTimeslice);
		btnTimeslice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (runningList.getSelectedValue() == null) {
						runningList.setSelectedIndex(0);
						
						if (runningList.getSelectedValue() != null) {
							listModelReady.addElement(runningList.getSelectedValue().toString());
							listModelRun.removeElement(runningList.getSelectedValue());
						}
					}
					
					if (readyList.getSelectedValue() == null) {
						readyList.setSelectedIndex(0);

						if (readyList.getModel().getSize() > 1) {
						    int j = 0;
							String temp1 = readyList.getSelectedValue().toString();
						    int x = Integer.parseInt(temp1.substring(temp1.length() - 1));
						    for (int i = 1; i < readyList.getModel().getSize() - 1; i++) {
						    	readyList.setSelectedIndex(i);
							    String temp2 = readyList.getSelectedValue().toString();
							    int y = Integer.parseInt(temp2.substring(temp2.length() - 1));
							    if (x > y) {
							    	j = i;
							    	x = y;
							    }
						    }
						readyList.setSelectedIndex(j);
						}
					}
					listModelRun.addElement(readyList.getSelectedValue().toString());
					listModelReady.removeElement(readyList.getSelectedValue());	
				}
				catch(Exception noReady) {
					JOptionPane.showMessageDialog(null, "No Processes in ready and run.");
				}
			}
		});

		
		pNum = new JTextField();
		pNum.setText((String) null);
		pNum.setColumns(10);
		pNum.setBounds(244, 424, 115, 43);
		frmPmanger.getContentPane().add(pNum);
		
		JLabel lblReady = new JLabel("Ready");
		lblReady.setBackground(Color.ORANGE);
		lblReady.setHorizontalAlignment(SwingConstants.CENTER);
		lblReady.setBounds(117, 16, 69, 20);
		frmPmanger.getContentPane().add(lblReady);
		
		JLabel lblRunning = new JLabel("Running");
		lblRunning.setHorizontalAlignment(SwingConstants.CENTER);
		lblRunning.setBounds(430, 16, 69, 20);
		frmPmanger.getContentPane().add(lblRunning);
		
		JLabel lblBlocked = new JLabel("Blocked");
		lblBlocked.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlocked.setBounds(430, 121, 69, 20);
		frmPmanger.getContentPane().add(lblBlocked);
		
		JLabel lblNewProcessName = new JLabel("New process name");
		lblNewProcessName.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewProcessName.setBounds(57, 400, 149, 20);
		frmPmanger.getContentPane().add(lblNewProcessName);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriority.setBounds(254, 400, 83, 20);
		frmPmanger.getContentPane().add(lblPriority);
		
		
		JLabel lblTerminated = new JLabel("Terminated");
		lblTerminated.setHorizontalAlignment(SwingConstants.CENTER);
		lblTerminated.setBounds(742, 16, 83, 20);
		frmPmanger.getContentPane().add(lblTerminated);
		
	}
}
