package gui;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Image;


public class hello_prompt extends JDialog 
{
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JProgressBar progressBar;
	private JLabel lblProgress;
	
	public hello_prompt() 
	{
		getContentPane().setBackground(Color.WHITE);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setResizable(false);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(((int)screenSize.getWidth()/2-250),((int)screenSize.getHeight()/2-150),500,300);
		
		JLabel lbl_icon = new JLabel("icon");
		lbl_icon.setBounds(200, 100, 100, 100);
		lbl_icon.setIcon(new ImageIcon(new ImageIcon("./icons/bk.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		
		getContentPane().add(lbl_icon);
		
		this.progressBar = new JProgressBar();
		this.progressBar.setValue(100);
		this.progressBar.setBounds(82, 250, 350, 14);
		getContentPane().add(progressBar);
		this.lblProgress = new JLabel("progress...");
		this.lblProgress.setBounds(82, 230, 350, 16);
		
		getContentPane().add(lblProgress);
		
		JLabel lblDatabaseAndTraceability = new JLabel("");
	    lblDatabaseAndTraceability.setIcon(new ImageIcon(new ImageIcon("./icons/icon_label.png").getImage().getScaledInstance(350, 17, Image.SCALE_SMOOTH)));
		lblDatabaseAndTraceability.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabaseAndTraceability.setBounds(75, 30, 350, 50);
		getContentPane().add(lblDatabaseAndTraceability);
	}
	
	public void set_prograss(int prograss)
	{
		this.progressBar.setValue(prograss);
	}
	
	public void set_label_progress(String progress_string)
	{
		this.lblProgress.setText(progress_string);
	}
}
