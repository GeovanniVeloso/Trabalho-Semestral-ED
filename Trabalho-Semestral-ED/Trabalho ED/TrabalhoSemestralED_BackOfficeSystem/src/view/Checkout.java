package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Checkout extends BaseFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkout frame = new Checkout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Checkout() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCheckoutCompra = new JLabel("CHECKOUT DA COMPRA");
		lblCheckoutCompra.setToolTipText("CHECKOUT DA COMPRA");
		lblCheckoutCompra.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCheckoutCompra.setBounds(179, 26, 339, 27);
		contentPane.add(lblCheckoutCompra);
		
		JLabel lblCompra = new JLabel("AS SUAS COMPRAS FORAM :");
		lblCompra.setToolTipText("VEJA O CHECKOUT DAS SUAS COMPRAS");
		lblCompra.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCompra.setBounds(10, 83, 270, 22);
		contentPane.add(lblCompra);
		
		JScrollPane scrollPaneCheckout = new JScrollPane();
		scrollPaneCheckout.setToolTipText("O RESULTADO DO SEU CHECKOUT");
		scrollPaneCheckout.setBounds(274, 83, 375, 190);
		contentPane.add(scrollPaneCheckout);
		
		JTextArea textAreaCheckout = new JTextArea();
		textAreaCheckout.setToolTipText("O RESULTADO DO SEU CHECKOUT");
		textAreaCheckout.setEnabled(false);
		textAreaCheckout.setBounds(276, 85, 373, 188);
		contentPane.add(textAreaCheckout);
		
		JButton btnVoltarCheckout = new JButton("Voltar");
		btnVoltarCheckout.setToolTipText("VOLTAR A TELA ANTERIOR");
		btnVoltarCheckout.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltarCheckout.setBounds(430, 285, 110, 30);
		contentPane.add(btnVoltarCheckout);

		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setToolTipText("CLIQUE PARA VER O CHECKOUT");
		btnCheckout.setBackground(new Color(244, 164, 96));
		btnCheckout.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCheckout.setBounds(550, 285, 114, 30);
		contentPane.add(btnCheckout);

		btnVoltarCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);

				// Fecha o frame atual
				dispose();
			}
		});
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Perform the check for empty text fields
	

					JOptionPane.showMessageDialog(null, "Checkout Realizado com Sucesso", "Sucesso!", JOptionPane.PLAIN_MESSAGE);
					
				
			}
		});			
	}

	

	
}

