package locations.client;

import javax.swing.*;

public class Starter {
	
	private static String getTitle(Object o) {
		return o.getClass().getSimpleName();
	}
	
	public static void run(final JFrame frame, int width, int height) {
		frame.setTitle(getTitle(frame));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
	
	public static void run(final JApplet applet, int width, int height) {
		JFrame frame = new JFrame(getTitle(applet));
		frame.add(applet);
		applet.init();
		applet.start();
		run(frame, width, height);
	}
}
