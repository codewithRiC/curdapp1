package hmspackage;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class hsm {

	private JFrame frame;
	private JTextField PID;
	private JTextField PNAME;
	private JTextField DID;
	private JTextField DOA;
	private JTextField ADDR;
	private JTextField MOBNO;
	private JTextField DS;
	private JTextField SID;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hsm window = new hsm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public hsm() {
		initialize();
		Connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	/**
	 * Initialize the contents of the frame.
	 */
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/HMS","root","system");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR IN connection");
		}
	
		
	}
	public void table_load()
	{
		try
		{
			pst = con.prepareStatement("Select * from Patients");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel (rs));
		}
		catch (SQLException e)
		{
			System.out.println("ERROE IN TABLE LOAD");
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 766, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "REGISTRATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 59, 327, 227);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		PID = new JTextField();
		PID.setBounds(188, 27, 83, 10);
		panel.add(PID);
		PID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PATIENT ID");
		lblNewLabel_1.setBounds(10, 26, 110, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PATIENT NAME");
		lblNewLabel_1_1.setBounds(10, 51, 126, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("DOCTOR ID");
		lblNewLabel_1_2.setBounds(10, 74, 126, 13);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("DATE OF ADMISSION");
		lblNewLabel_1_3.setBounds(10, 97, 139, 13);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("ADDRESS");
		lblNewLabel_1_4.setBounds(10, 120, 139, 13);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("MOBILE NUMBER");
		lblNewLabel_1_5.setBounds(10, 143, 126, 13);
		panel.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("DISCHARGE STATUS");
		lblNewLabel_1_6.setBounds(10, 166, 126, 13);
		panel.add(lblNewLabel_1_6);
		
		PNAME = new JTextField();
		PNAME.setColumns(10);
		PNAME.setBounds(188, 47, 83, 10);
		panel.add(PNAME);
		
		DID = new JTextField();
		DID.setColumns(10);
		DID.setBounds(188, 75, 83, 10);
		panel.add(DID);
		
		DOA = new JTextField();
		DOA.setColumns(10);
		DOA.setBounds(188, 98, 83, 10);
		panel.add(DOA);
		
		ADDR = new JTextField();
		ADDR.setColumns(10);
		ADDR.setBounds(188, 121, 83, 10);
		panel.add(ADDR);
		
		MOBNO = new JTextField();
		MOBNO.setColumns(10);
		MOBNO.setBounds(188, 144, 83, 10);
		panel.add(MOBNO);
		
		DS = new JTextField();
		DS.setColumns(10);
		DS.setBounds(188, 167, 83, 10);
		panel.add(DS);
		
		JLabel lblNewLabel = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
		lblNewLabel.setBounds(260, 10, 255, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Pname,Doa,addr,ds,Pid,Did,Mob;
				 
				Pid=PID.getText(); 
				Pname=PNAME.getText();
				Did=DID.getText();
				Doa=DOA.getText();
				addr=ADDR.getText();
				
				Mob=MOBNO.getText();
				ds=DS.getText();
				
				try {
					pst = con.prepareStatement("insert into Patients(PatientId,PatientName,DoctorId,DateOfAdmission,Address,MobileNumber,DischargeStatus)values(?,?,?,?,?,?,?)");
					pst.setString(1, Pid);
					pst.setString(2,Pname);
					pst.setString(3,Did);
					pst.setString(4,Doa);
					pst.setString(5,addr);
					pst.setString(6,Mob);
					pst.setString(7,ds);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Addeddd!!!!");
					table_load();
					PID.setText("");
					PNAME.setText("");
					DID.setText("");
					DOA.setText("");
				    ADDR.setText("");
					MOBNO.setText("");
					DS.setText("");
					PNAME.requestFocus();
					
				}
				catch (SQLException e1) {
					System.out.println("ERROR IN SAVE BTN");
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(10, 296, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PID.setText("");
				PNAME.setText("");
				DID.setText("");
				DOA.setText("");
			    ADDR.setText("");
				MOBNO.setText("");
				DS.setText("");
				PNAME.requestFocus();
				
			}
		});
		btnClear.setBounds(252, 296, 85, 21);
		frame.getContentPane().add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SEARCH", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(20, 330, 317, 80);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_7 = new JLabel("PATIENT ID");
		lblNewLabel_1_7.setBounds(10, 30, 117, 13);
		panel_1.add(lblNewLabel_1_7);
		
		SID = new JTextField();
		SID.setColumns(10);
		SID.setBounds(181, 30, 83, 12);
		panel_1.add(SID);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id=SID.getText();
					   
					   pst=con.prepareStatement("Select * from Patients where PatientId= ?");
					   pst.setString(1, id);
					   ResultSet rs=pst.executeQuery();
					   
					if(rs.next()==true)
					{
						String Pid=rs.getString(1);
						String Pname =rs.getString(2);
						String Did=rs.getString(3);
						String Doa=rs.getString(4);
						String addr=rs.getString(5);
						String Mob=rs.getString(6);
						String ds=rs.getString(7);
						
						PID.setText(Pid);
						PNAME.setText(Pname);
						DID.setText(Did);
						DOA.setText(Doa);
					    ADDR.setText(addr);
						MOBNO.setText(Mob);
						DS.setText(ds);
						
						
					}
					else
					{
						PID.setText("");
						PNAME.setText("");
						DID.setText("");
						DOA.setText("");
					    ADDR.setText("");
						MOBNO.setText("");
						DS.setText("");
						PNAME.requestFocus();
					}
				}
				catch (SQLException ex) {
					System.out.println("ERROR IN THE SEARCH");
				}
			
			}
		});
		btnSearch.setBounds(179, 49, 85, 21);
		panel_1.add(btnSearch);
		
		table = new JTable();
		table.setBounds(369, 61, 373, 256);
		frame.getContentPane().add(table);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Pname,Doa,addr,ds,Pid,Did,Mob;
				 
				Pid=SID.getText(); 
				Pname=PNAME.getText();
				Did=DID.getText();
				Doa=DOA.getText();
				addr=ADDR.getText();
				
				Mob=MOBNO.getText();
				ds=DS.getText();
				try {
					pst = con.prepareStatement("update Patients set PatientId=?,PatientName=?,DoctorId=?,DateOfAdmission=?,Address=?,MobileNumber=?,DischargeStatus=? where PatientId=?");
					pst.setString(1, Pid);
					pst.setString(2,Pname);
					pst.setString(3,Did);
					pst.setString(4,Doa);
					pst.setString(5,addr);
					pst.setString(6,Mob);
					pst.setString(7,ds);
					pst.setString(8,Pid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Updated!!!!");
					table_load();
					PID.setText("");
					PNAME.setText("");
					DID.setText("");
					DOA.setText("");
				    ADDR.setText("");
					MOBNO.setText("");
					DS.setText("");
					PNAME.requestFocus();
					
				}
				catch (SQLException e1) {
					System.out.println("ERROR IN SAVE BTN");
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(384, 347, 85, 21);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Pid;
				
				Pid=SID.getText();
				try {
					pst = con.prepareStatement("Delete from Patients where PatientId=?");
					
					pst.setString(1, Pid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Deleted!!!");
					table_load();
					PID.setText("");
					PNAME.setText("");
					DID.setText("");
					DOA.setText("");
				    ADDR.setText("");
					MOBNO.setText("");
					DS.setText("");
					PNAME.requestFocus();
					
				}
				catch (SQLException e1) {
					System.out.println("ERROR IN SAVE BTN");
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(638, 347, 85, 21);
		frame.getContentPane().add(btnDelete);
	}
}
