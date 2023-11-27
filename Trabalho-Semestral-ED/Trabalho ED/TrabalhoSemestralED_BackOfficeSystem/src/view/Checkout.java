package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import model.ListaEncadeada;
import model_main.Produto;

public class Checkout extends BaseFrame {

	private JPanel contentPane;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldValorCompra;
	private JTextArea textAreaCheckout;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Checkout(ListaEncadeada<Produto> produtosCarrinho, String nomeCliente, String totalCompra) {
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
		lblCompra.setBounds(10, 155, 270, 22);
		contentPane.add(lblCompra);

//		JTextArea textAreaCheckout = new JTextArea();
//		textAreaCheckout.setEditable(false);
//		textAreaCheckout.setToolTipText("O RESULTADO DO SEU CHECKOUT");
		String teste = null;
		try {
			teste = transformaListaParaString(produtosCarrinho);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(teste);
        
//		textAreaCheckout.setBounds(276, 157, 373, 116);
//		contentPane.add(textAreaCheckout);
		
		// *Preenche Carrinho*//
		JTextArea textAreaCheckout = new JTextArea();
		textAreaCheckout.setToolTipText("O RESULTADO DO SEU CHECKOUT :");
		textAreaCheckout.setEditable(false);
		try {
			textAreaCheckout.setText(transformaListaParaString(produtosCarrinho));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		String teste = transformaListaParaString(produtosCarrinho);
//		textAreaCheckout.append(teste);
		textAreaCheckout.setBounds(276, 157, 373, 116);
		contentPane.add(textAreaCheckout);

		JButton btnVoltarCheckout = new JButton("Voltar");
		btnVoltarCheckout.setToolTipText("VOLTAR A TELA ANTERIOR");
		btnVoltarCheckout.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltarCheckout.setBounds(344, 285, 110, 30);
		contentPane.add(btnVoltarCheckout);

		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setToolTipText("CLIQUE PARA VER O CHECKOUT");
		btnFinalizarCompra.setBackground(new Color(255, 160, 122));
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizarCompra.setBounds(464, 285, 185, 30);
		contentPane.add(btnFinalizarCompra);
		
		JLabel lblNomeCliente = new JLabel("NOME DO CLIENTE :");
		lblNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeCliente.setToolTipText("NOME DO CLIENTE");
		lblNomeCliente.setBounds(10, 77, 200, 22);
		contentPane.add(lblNomeCliente);
		
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setToolTipText("O NOME DO CLIENTE É");
		textFieldNomeCliente.setText(nomeCliente);
		textFieldNomeCliente.setEditable(false);
		textFieldNomeCliente.setBounds(214, 79, 185, 22);
		contentPane.add(textFieldNomeCliente);
		textFieldNomeCliente.setColumns(10);
		
		JLabel lblValorCompra = new JLabel("VALOR DA COMPRA :");
		lblValorCompra.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValorCompra.setToolTipText("O VALOR DA COMPRA FOI");
		lblValorCompra.setBounds(10, 120, 230, 24);
		contentPane.add(lblValorCompra);
		
		textFieldValorCompra = new JTextField();
		textFieldValorCompra.setToolTipText("O VALOR DA COMPRA FOI: ");
		textFieldValorCompra.setText(totalCompra);
		textFieldValorCompra.setEditable(false);
		textFieldValorCompra.setBounds(214, 124, 185, 20);
		contentPane.add(textFieldValorCompra);
		textFieldValorCompra.setColumns(10);

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

				//Manda pra tela inicial
				TelaInicial frame = new TelaInicial();
				frame.setVisible(true);
				dispose();

			}
		});
	}

	private String transformaListaParaString(ListaEncadeada<Produto> carrinho) throws Exception {
		StringBuffer buffer = new StringBuffer();
		int tamanho = carrinho.size();
		for(int i = 0; i < tamanho; i++) {
			Produto produto = carrinho.getValue(i);
			buffer.append("#" + produto.prodId + "     " + produto.nome + "     $" + produto.valor + "\r\n");
		}
		String conteudo = buffer.toString();
		return conteudo;
//		textAreaCarrinho.setText(conteudo);
//		StringBuffer buffer = new StringBuffer();
//		for(int i = 0; i < carrinho.size(); i++) {
//			try {
////				System.out.println("DENTRO DO FOR:   "+carrinho.getValue(i));
//				Produto p = carrinho.getValue(i);
//				buffer.append(p + "\n");
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
////		System.out.println(buffer.toString());
//		return buffer.toString();
		
	}
}