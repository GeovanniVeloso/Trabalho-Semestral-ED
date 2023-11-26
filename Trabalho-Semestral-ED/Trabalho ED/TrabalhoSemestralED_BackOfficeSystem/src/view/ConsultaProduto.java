package view;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.ControleProduto;
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
import model.ListaEncadeada;
import model_main.Produto;
=======
>>>>>>> 966725c709bb2fc7548dd21139509a1fe61d17b8
>>>>>>> Stashed changes
import view.CadastraTipoDeProduto.AlphaNumericTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ConsultaProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldTipoProduto;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ConsultaProduto() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaProduto = new JLabel("CONSULTA DE PRODUTO");
		lblConsultaProduto.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblConsultaProduto.setBounds(181, 36, 289, 21);
		contentPane.add(lblConsultaProduto);
		
		JLabel lblID = new JLabel("ID :");
		lblID.setToolTipText("ID");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblID.setBounds(153, 114, 58, 24);
		contentPane.add(lblID);
	
		
		textFieldID = createNumericTextField();
		textFieldID.setBounds(221, 114, 202, 24);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JButton btnAdicionarCarrinho = new JButton("Adicionar no carrinho");
		btnAdicionarCarrinho.setBackground(new Color(255, 160, 122));
		btnAdicionarCarrinho.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAdicionarCarrinho.setBounds(440, 287, 224, 23);
		contentPane.add(btnAdicionarCarrinho);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConsultar.setBackground(new Color(0, 204, 255));
		btnConsultar.setBounds(283, 287, 140, 23);
		contentPane.add(btnConsultar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltar.setBounds(161, 287, 112, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblTipoProduto = new JLabel("TIPO PRODUTO :");
		lblTipoProduto.setToolTipText("TIPO PRODUTO");
		lblTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTipoProduto.setBounds(22, 149, 185, 21);
		contentPane.add(lblTipoProduto);
		
	    AlphaNumericTextField  textFieldTipoProduto = new   AlphaNumericTextField ();
		textFieldTipoProduto.setBounds(221, 149, 202, 20);
		contentPane.add(textFieldTipoProduto);
		textFieldTipoProduto.setColumns(10);
		
		ControleProduto cp = new ControleProduto(textFieldID, textFieldTipoProduto);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldID.getText().isEmpty() || textFieldTipoProduto.getText().isEmpty()
					) {

					JOptionPane.showMessageDialog(null, "Campos não preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
				
				} else {
				cp.actionPerformed(e);
				ConsultaProduto ConsultaProdutojFrame = new ConsultaProduto();
				ConsultaProdutojFrame.setVisible(true);
 
				// Fecha o frame atual, se necessário
				setVisible(false);
				dispose();
			}
			}
		});
<<<<<<< Updated upstream
		
		btnAdicionarCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				cp.actionPerformed(e);
=======
<<<<<<< HEAD
		ListaEncadeada<Produto>Carrinho = new ListaEncadeada<>();
		ControleProduto cp1 = new ControleProduto(textFieldID, textFieldTipoProduto, Carrinho);
		btnAdicionarCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				cp1.actionPerformed(e);
=======
		
		btnAdicionarCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				cp.actionPerformed(e);
>>>>>>> 966725c709bb2fc7548dd21139509a1fe61d17b8
>>>>>>> Stashed changes
				JOptionPane.showMessageDialog(null, "Adicionado no carrinho com Sucesso", "Sucesso!", JOptionPane.PLAIN_MESSAGE);
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);

				// Fecha o frame atual
				dispose();
			}
		});
	}
	private JTextField createNumericTextField() {
		JTextField textField = new JTextField();

		textField.setDocument(new NumericDocument());
		return textField;
	}

	private class NumericDocument extends PlainDocument {
		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			// Verifica se a string contém apenas dígitos
			if (str != null && str.matches("\\d+")) {
				super.insertString(offs, str, a);
			}
		}
	}

	public class AlphaNumericTextField extends JTextField {
	    public AlphaNumericTextField() {
	        setDocument(new AlphaNumericDocument());
	    }

	    public class AlphaNumericDocument extends PlainDocument {
	        @Override
	        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	            // Verifica se a string contém apenas letras ou espaços
	            if (str != null && str.matches("[a-zA-Z ]+")) {
	                super.insertString(offs, str, a);
	            }
	        }
	    }

}
	}

