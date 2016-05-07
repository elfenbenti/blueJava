package javaSql;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.sql.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	
	Connection connection=null;
	private JTextField textCPU;
	private JTextField textMem;
	private JTextField textVers;
	
	int nrCPU;
	long nrMem;
	String vers;
	private JTextField textSql;
	private JTextField textComp;
	
	public Login() {
		initialize();
		connection=SqlLiteConnection.dbConnector();
		
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		
		
		
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCPU = new JLabel("CPUs");
		lblCPU.setBounds(427, 100, 70, 15);
		frame.getContentPane().add(lblCPU);
		
		JLabel lblMem = new JLabel("Memory");
		lblMem.setBounds(415, 131, 70, 15);
		frame.getContentPane().add(lblMem);
		
		JLabel lblVers = new JLabel("Version");
		lblVers.setBounds(415, 160, 70, 15);
		frame.getContentPane().add(lblVers);
		
		textCPU = new JTextField();
		textCPU.setText("CPU");
		textCPU.setBounds(511, 98, 114, 19);
		frame.getContentPane().add(textCPU);
		textCPU.setColumns(10);
		
		textMem = new JTextField();
		textMem.setText("memory");
		textMem.setBounds(511, 129, 114, 19);
		frame.getContentPane().add(textMem);
		textMem.setColumns(10);
		
		textVers = new JTextField();
		textVers.setText("version");
		textVers.setBounds(511, 158, 114, 19);
		frame.getContentPane().add(textVers);
		textVers.setColumns(10);
		
		JButton btnPressme = new JButton("PressMe");
		btnPressme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					int nrCPU = Runtime.getRuntime().availableProcessors();
					System.out.println(nrCPU);
					long nrMem = Runtime.getRuntime().freeMemory();
					String vers = System.getProperty("java.version");
					System.out.println(nrCPU+" "+nrMem+"  "+vers);
					
					
					textCPU.setText(Integer.toString(nrCPU));
					textMem.setText(Float.toString(nrMem));
					textVers.setText(vers);
					
				}catch(Exception e1){
					
					JOptionPane.showMessageDialog(null,e1);
					
				}
				
				
				
				
			}
		});
		btnPressme.setBounds(258, 95, 117, 25);
		frame.getContentPane().add(btnPressme);
		
		JButton btnCheckSq = new JButton("Check Company");
		btnCheckSq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String query="select * from Company where Name=?";
					PreparedStatement pst= connection.prepareStatement(query);
				
					
					pst.setString(1, textComp.getText());
					ResultSet rs =pst.executeQuery();
					
					while(rs.next()){
						
					float StVl = rs.getFloat("StockVal");
					System.out.println(StVl + "\t");
					textSql.setText(Float.toString(StVl));
						
					}
											
												
						
				
					
				rs.close();
				pst.close();
				
				}catch(Exception e2){
					
					JOptionPane.showMessageDialog(null,e2);
					
				}
				
			
				
				
			}
		});
		btnCheckSq.setBounds(220, 263, 155, 55);
		frame.getContentPane().add(btnCheckSq);
		
		textSql = new JTextField();
		textSql.setBounds(415, 260, 184, 62);
		frame.getContentPane().add(textSql);
		textSql.setColumns(10);
		
		textComp = new JTextField();
		textComp.setText("company?");
		textComp.setBounds(245, 330, 114, 19);
		frame.getContentPane().add(textComp);
		textComp.setColumns(10);
	}
}
