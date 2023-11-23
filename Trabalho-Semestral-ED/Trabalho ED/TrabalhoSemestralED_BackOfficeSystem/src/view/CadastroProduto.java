package view;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.ControleProduto;

public class CadastroProduto extends BaseFrame {

	private JPanel contentPane_1;
	private JTextField textFieldNomeProduto;
	private JTextField textFieldValorProduto;
	private JTextField textFieldQuantidadeProduto;
	private JTextField textFieldCodigoProduto;
	private JTextField textFieldTipoProduto;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CadastroProduto() {

		getContentPane().setLayout(null);

		contentPane_1 = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);

		AlphaNumericTextField textFieldTipoProduto = new AlphaNumericTextField();
		textFieldTipoProduto.setBounds(458, 248, 170, 20);
		textFieldTipoProduto.setToolTipText("DIGITE O TIPO DO PRODUTO");
		contentPane_1.add(textFieldTipoProduto);
		textFieldTipoProduto.setColumns(10);

		AlphaNumericTextField textFieldNomeProduto = new AlphaNumericTextField();
		textFieldNomeProduto.setBounds(216, 115, 170, 20);
		textFieldNomeProduto.setToolTipText("DIGITE O NOME DO PRODUTO");
		contentPane_1.add(textFieldNomeProduto);
		textFieldNomeProduto.setColumns(10);

		textFieldCodigoProduto = createNumericTextField();
		textFieldCodigoProduto.setToolTipText("DIGITE O CODIGO DO PRODUTO");
		textFieldCodigoProduto.setBounds(270, 173, 105, 20);
		contentPane_1.add(textFieldCodigoProduto);
		textFieldCodigoProduto.setColumns(10);

		textFieldValorProduto = createDoubleTextField();
		textFieldValorProduto.setToolTipText("DIGITE O VALOR DO PRODUTO");
		textFieldValorProduto.setBounds(540, 175, 99, 20);
		contentPane_1.add(textFieldValorProduto);
		textFieldValorProduto.setColumns(10);

		textFieldQuantidadeProduto = createNumericTextField();
		textFieldQuantidadeProduto.setBounds(553, 112, 86, 20);
		textFieldQuantidadeProduto.setToolTipText("DIGITE A QUANTIDADE DO PRODUTO");
		contentPane_1.add(textFieldQuantidadeProduto);
		textFieldQuantidadeProduto.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 221, 218, 74);
		contentPane_1.add(scrollPane);

		JTextArea textAreaDescricaoProduto = new JTextArea();
		textAreaDescricaoProduto.setToolTipText("DIGITE A DESCRIÇÃO DO PRODUTO");
		scrollPane.setViewportView(textAreaDescricaoProduto);

		JLabel lblNomeProduto = new JLabel("NOME PRODUTO :");
		lblNomeProduto.setToolTipText("NOME PRODUTO ");
		lblNomeProduto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeProduto.setBounds(42, 112, 194, 20);
		contentPane_1.add(lblNomeProduto);

		JLabel lblValor = new JLabel("VALOR :");
		lblValor.setToolTipText("VALOR");
		lblValor.setFont(new Font("Dialog", Font.BOLD, 18));
		lblValor.setBounds(431, 172, 99, 20);
		contentPane_1.add(lblValor);

		JLabel lblDescricao = new JLabel("DESCRIÇÃO :");
		lblDescricao.setToolTipText("DESCRIÇÃO ");
		lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDescricao.setBounds(44, 216, 158, 30);
		contentPane_1.add(lblDescricao);

		JLabel lblQuantidade = new JLabel("QUANTIDADE :");
		lblQuantidade.setToolTipText("QUANTIDADE");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblQuantidade.setBounds(407, 112, 158, 20);
		contentPane_1.add(lblQuantidade);

		JLabel lblCodigoProduto = new JLabel("CÓDIGO DO PRODUTO :");
		lblCodigoProduto.setToolTipText("CÓDIGO DO PRODUTO ");
		lblCodigoProduto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCodigoProduto.setBounds(42, 165, 218, 30);
		contentPane_1.add(lblCodigoProduto);

		JLabel lblTituloCadastroProduto = new JLabel("CADASTRO DE PRODUTO");
		lblTituloCadastroProduto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTituloCadastroProduto.setBounds(216, 39, 324, 20);
		contentPane_1.add(lblTituloCadastroProduto);

		JLabel lblTipoProduto = new JLabel("TIPO DE PRODUTO");
		lblTipoProduto.setToolTipText("TIPO DE PRODUTO");
		lblTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTipoProduto.setBounds(458, 221, 181, 20);
		contentPane_1.add(lblTipoProduto);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltar.setBounds(430, 285, 86, 30);
		contentPane_1.add(btnVoltar);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(new Color(51, 204, 102));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCadastrar.setBounds(530, 285, 130, 30);
		contentPane_1.add(btnCadastrar);

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);

				// Fecha o frame atual
				dispose();
			}
		});

		ControleProduto cp = new ControleProduto(textFieldCodigoProduto, textFieldNomeProduto, textFieldValorProduto,
				textAreaDescricaoProduto, textFieldQuantidadeProduto, textFieldTipoProduto);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Perform the check for empty text fields
				if (textFieldNomeProduto.getText().isEmpty() || textFieldQuantidadeProduto.getText().isEmpty()
						|| textFieldCodigoProduto.getText().isEmpty() || textFieldValorProduto.getText().isEmpty()
						|| textAreaDescricaoProduto.getText().isEmpty() || textFieldTipoProduto.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Campos não preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);

				} else {
					cp.actionPerformed(e);
					CadastroProduto cadProdutojFrame = new CadastroProduto();
					cadProdutojFrame.setVisible(true);

					// Fecha o frame atual, se necessário
					setVisible(false);
					dispose();

				}
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

	private JTextField createDoubleTextField() {
		JTextField textField = new JTextField();

		textField.setDocument(new DoubleDocument());
		return textField;
	}

	public class DoubleDocument extends PlainDocument {
		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			// Verifica se a string contém apenas números ou ponto (para números decimais)
			if (str != null && str.matches("\\d*\\.?\\d*")) {
				super.insertString(offs, str, a);
			}
		}
	}

	public class AlphaNumericTextField extends JTextField {
		public AlphaNumericTextField() {
			setDocument(new AlphaNumericDocument());
		}

		private class AlphaNumericDocument extends PlainDocument {
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
