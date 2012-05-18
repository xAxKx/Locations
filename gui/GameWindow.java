package locations.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnEnter = new JButton("OK");
	private JLabel lblPlayer= new JLabel();
	private JPanel map = new JPanel();
	private ObjectOutputStream netOut;
	
	public GameWindow(ObjectOutputStream netOut) {
		this.netOut = netOut;
		
		setLayout(new FlowLayout());
		
		lblPlayer.setText("{nimi_siia_kuidagi}");
		lblPlayer.setSize(650, 25);
		lblPlayer.setPreferredSize(lblPlayer.getSize());
		
		map.setBackground(Color.BLACK);
		map.setSize(650, 500);
		map.setPreferredSize(map.getSize());
		
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				enter();
			}
		});
		
		add(lblPlayer);
		add(map);
		add(btnEnter);
	}

	private void enter() {
		//template
	}
}
