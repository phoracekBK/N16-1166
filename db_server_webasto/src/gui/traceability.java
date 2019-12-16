package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class traceability extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private traceability_view station_monitor;
	public traceability() 
	{
		station_monitor = new traceability_view();
		station_monitor.setWidth(1000);
		JScrollPane scrollPane = new JScrollPane(station_monitor);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane);
		events();
	}
	
	public traceability_view get_traceability_referece()
	{
		return this.station_monitor;
	}
	
	private void events()
	{
		addComponentListener(new ComponentListener() 
		{
		    public void componentResized(ComponentEvent e) 
		    {
			     station_monitor.setWidth(getWidth()-25);
		    }

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
