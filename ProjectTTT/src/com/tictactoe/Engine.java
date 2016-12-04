/*
* TicTacToe
*/

package com.tictactoe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Engine {

	ImageIcon X = new ImageIcon(getClass().getResource("res/x.png"));
	ImageIcon O = new ImageIcon(getClass().getResource("res/o.png"));

	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	JFrame frame;
	JPopupMenu pop = new JPopupMenu();
	JLabel backgrnd, tic, tac, toe, name;
	JProgressBar bar;
	JButton[] but = new JButton[10];
	JTextField player1, player2;
	JButton next;
	JMenuBar menubar;
	JMenu file, help;
	JMenuItem newg, reset, exit, about;

	Font font = new Font("serif", Font.TRUETYPE_FONT, 50);

	int prcnt = 1, chk = 0;
	int[] button = new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 24 };
	String pr1, pr2;

	public void splashScreen() {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
		frame = new JFrame("Tic Tac Toe");
		frame.setSize(640, 400);
		frame.setLocation((int) screen.getWidth() / 2 - (int) frame.getWidth()
				/ 2, (int) screen.getHeight() / 2 - (int) frame.getHeight() / 2);
		frame.setLayout(new BorderLayout());
		backgrnd = new JLabel(new ImageIcon(getClass().getResource(
				"res/grid.png")));
		frame.add(backgrnd);

		bar = new JProgressBar();
		bar.setValue(0);
		bar.setStringPainted(true);
		bar.setBorderPainted(false);
		bar.setBounds(110, 320, 160, 20);
		backgrnd.add(bar);

		tic = new JLabel("Tic");
		toe = new JLabel("Toe");
		tac = new JLabel("Tac");
		tic.setFont(font);
		tac.setFont(font);
		toe.setFont(font);
		tic.setBounds(100, 40, 100, 80);
		tac.setBounds(200, 130, 100, 80);
		toe.setBounds(300, 220, 100, 80);

		backgrnd.add(tic);
		backgrnd.add(tac);
		backgrnd.add(toe);

		frame.setIconImage(new ImageIcon(getClass().getResource("res/icon.png"))
				.getImage());

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void iterate() {
		for (int i = 1; i <= 100; i++) {
			try {
				Thread.sleep(100);
				bar.setValue(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void clear() {
		backgrnd.removeAll();
		backgrnd.revalidate();
		backgrnd.repaint();
		frame.pack();
	}

	public void playerInput() {
		menubar = new JMenuBar();
		file = new JMenu("File");
		help = new JMenu("Help");
		newg = new JMenuItem("New Game");
		reset = new JMenuItem("Reset");
		exit = new JMenuItem("Exit");
		about = new JMenuItem("About");
		pop.add(about);

		name = new JLabel("Enter names ");
		player1 = new JTextField("Player 1");
		player2 = new JTextField("Player 2");
		next = new JButton(
				new ImageIcon(getClass().getResource("res/next.png")));
		name.setBounds(140, 40, 100, 80);
		player1.setBounds(150, 130, 180, 30);
		player2.setBounds(150, 210, 180, 30);
		next.setBounds(160, 270, 80, 80);
		next.setBorderPainted(false);
		next.setContentAreaFilled(false);

		next.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {}

			public void mouseEntered(MouseEvent arg0) {}

			public void mouseExited(MouseEvent arg0) {}

			public void mousePressed(MouseEvent arg0) {
						next.setIcon(new ImageIcon(getClass().getResource(
								"res/next_pressed.png")));
						pr1 = player1.getText().toString();
						pr2 = player2.getText().toString();
		
			}

			public void mouseReleased(MouseEvent arg0) {
				clear(); grid();
			}
			
		});

		frame.setJMenuBar(menubar);
		newg.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clear();
				reset();
				playerInput();
			}

		});
		reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				clear();
				reset();
				grid();
			}

		});
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}

		});
		about.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				frame.setTitle("Author :--------   Ivan");
				final JDialog dial = new JDialog();
				ImageIcon t = new ImageIcon(getClass().getResource(
						"res/about.png"));
				JLabel label = new JLabel(t);
				dial.setUndecorated(true);
				dial.add(label);
				dial.setAlwaysOnTop(true);
				dial.setLocation((int) screen.width / 2 - t.getIconWidth() / 2,
						(int) screen.height / 2 - t.getIconHeight() / 2);
				dial.getRootPane().setOpaque(false);
				dial.pack();
				dial.setVisible(true);
				dial.addKeyListener(new KeyListener() {

					public void keyPressed(KeyEvent arg0) {
					}

					public void keyReleased(KeyEvent arg0) {
						frame.setTitle("Tic Tac Toe");
						dial.dispose();
					}

					public void keyTyped(KeyEvent arg0) {
					}
				});
			}

		});
		menubar.add(file);
		menubar.add(help);
		file.add(newg);
		file.add(reset);
		file.addSeparator();
		file.add(exit);
		help.add(about);
		backgrnd.add(name);
		backgrnd.add(player1);
		backgrnd.add(player2);
		backgrnd.add(next);
		backgrnd.repaint();
	}

	public int check() {
		if (button[0] == button[1] && button[1] == button[2]
				|| button[3] == button[4] && button[4] == button[5]
				|| button[6] == button[7] && button[7] == button[8]
				|| button[0] == button[4] && button[4] == button[8]
				|| button[2] == button[4] && button[4] == button[6]
				|| button[0] == button[3] && button[3] == button[6]
				|| button[1] == button[4] && button[4] == button[7]
				|| button[2] == button[5] && button[5] == button[8])
			return 1;
		else if (prcnt == 10)
			return 3;
		else
			return 9;

	}

	public void gameOver() {
		if (prcnt % 2 != 0)
			JOptionPane.showMessageDialog(null, pr1 + " has won !!!", "Winner",
					JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, pr2 + " has won !!!", "Winner",
					JOptionPane.INFORMATION_MESSAGE);

		for (int i = 0; i < 10; i++)
			but[i].setEnabled(false);
	}

	public void grid() {

		backgrnd.setLayout(null);
		for (int i = 0; i < 10; i++) {
			but[i] = new JButton(new ImageIcon(getClass().getResource(
					"res/blk.png")));
			but[i].setOpaque(false);
			but[i].setContentAreaFilled(false);
			but[i].setBorderPainted(false);
			but[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JButton tb = (JButton) arg0.getSource();
					if (prcnt % 2 != 0) {
						tb.setIcon(X);
						button[Integer.parseInt(tb.getName())] = 1;
					} else {
						tb.setIcon(O);
						button[Integer.parseInt(tb.getName())] = 2;

					}
					chk = check();
					if (chk == 1)
						gameOver();
					prcnt++;
				}

			});
			but[i].setName(Integer.toString(i));
		}
		but[0].setBounds(100, 100, 65, 65);
		but[1].setBounds(170, 100, 65, 65);
		but[2].setBounds(240, 100, 65, 65);
		but[3].setBounds(100, 170, 65, 65);
		but[4].setBounds(170, 170, 65, 65);
		but[5].setBounds(240, 170, 65, 65);
		but[6].setBounds(100, 240, 65, 65);
		but[7].setBounds(170, 240, 65, 65);
		but[8].setBounds(240, 240, 65, 65);

		for (int i = 0; i < 10; i++)
			backgrnd.add(but[i]);
	}

	public void reset() {
		prcnt = 1;
		for (int i = 0; i < 9; i++)
			button[i] = 10 + i;
		pr1 = pr2 = "";
	}

	public static void main(String[] arg) {
		Engine co = new Engine();
		co.splashScreen();
		co.iterate();
		co.clear();
		co.playerInput();
	}
}
