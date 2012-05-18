package locations.gui;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import locations.server.Message;
import locations.server.MessageType;

/**
 * Nime sisestamise aken.
 *
 */
public class EnterNameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton btnEnter = new JButton("OK");
	private JTextField nameField = new JTextField();
	private JLabel lblEnter = new JLabel("Enter your name:");
	private ObjectOutputStream netOut;
	
	public EnterNameWindow(ObjectOutputStream netOut) {
		this.netOut = netOut;
		
		setLayout(new FlowLayout());
		
		lblEnter.setSize(100, 25);
		lblEnter.setPreferredSize(lblEnter.getSize());
		
		nameField.setSize(100, 25);
		nameField.setPreferredSize(nameField.getSize());
		
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				enter();
			}
		});
		
		add(lblEnter);
		add(nameField);
		add(btnEnter);
	}

	private void enter() {
		if (nameField.getText().length() > 0) {
			Message msg = new Message(MessageType.TEXT, nameField.getText());
			try {
				netOut.reset();
				netOut.writeObject(msg);
				this.dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
