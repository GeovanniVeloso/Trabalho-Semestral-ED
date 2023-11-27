package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import model.ListaEncadeada;
import model_main.Produto;

public class Checkout extends BaseFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Checkout(ListaEncadeada<Produto> produtosCarrinho) {
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
		scrollPaneCheckout.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneCheckout.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
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

		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setToolTipText("CLIQUE PARA VER O CHECKOUT");
		btnFinalizarCompra.setBackground(new Color(255, 160, 122));
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizarCompra.setBounds(500, 285, 114, 30);
		contentPane.add(btnFinalizarCompra);

		btnVoltarCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteCarrinho clienteCarrinho = new ClienteCarrinho(produtosCarrinho);
				clienteCarrinho.setVisible(true);

				// Fecha o frame atual
				dispose();
			}
		});
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Perform the check for empty text fields
				// Destrói carrinho
				File arquivo = new File("C:\\PastaTrabalhoED", "CarrinhoDeCompras.csv");
				arquivo.delete();

				TelaInicial frame = new TelaInicial();
				frame.setVisible(true);

			}
		});
	}

}
