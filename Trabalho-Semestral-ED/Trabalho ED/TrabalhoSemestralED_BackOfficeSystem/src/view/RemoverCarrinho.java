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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class RemoverCarrinho extends BaseFrame {

	private JPanel contentPane;
	private JTextField textFieldIndiceProduto;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame. public Carrinho(TelaInicial telaInicial) {
	 */
	public RemoverCarrinho() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRemoverItensCarrinho = new JLabel("REMOVER ITENS DO CARRINHO");
		lblRemoverItensCarrinho.setToolTipText("REMOVER ITENS DO CARRINHO");
		lblRemoverItensCarrinho.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRemoverItensCarrinho.setBounds(153, 29, 386, 27);
		contentPane.add(lblRemoverItensCarrinho);

		JLabel lblInidiceProduto = new JLabel("ÍNDICE DO PRODUTO :");
		lblInidiceProduto.setToolTipText("INDICE DO PRODUTO ");
		lblInidiceProduto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInidiceProduto.setBounds(43, 109, 230, 27);
		contentPane.add(lblInidiceProduto);

		textFieldIndiceProduto = createNumericTextField();
		textFieldIndiceProduto.setToolTipText("DIGITE O INDICE PARA A REMOÇÃO DE UM ITEM DO CARRINHO");
		textFieldIndiceProduto.setBounds(271, 109, 198, 26);
		contentPane.add(textFieldIndiceProduto);
		textFieldIndiceProduto.setColumns(10);

		JButton btnVoltarRemoverCarrinho = new JButton("Voltar");
		btnVoltarRemoverCarrinho.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltarRemoverCarrinho.setToolTipText("VOLTAR");
		btnVoltarRemoverCarrinho.setBounds(450, 287, 89, 23);
		contentPane.add(btnVoltarRemoverCarrinho);

		JButton btnRemoveRemoverCarrinho = new JButton("Remover");
		btnRemoveRemoverCarrinho.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRemoveRemoverCarrinho.setForeground(new Color(255, 255, 255));
		btnRemoveRemoverCarrinho.setBackground(new Color(245, 7, 7));
		btnRemoveRemoverCarrinho.setToolTipText("REMOVER ITEM");
		btnRemoveRemoverCarrinho.setBounds(549, 287, 115, 23);
		contentPane.add(btnRemoveRemoverCarrinho);

		ControleProduto cp = new ControleProduto(null, null, textFieldIndiceProduto);
		btnRemoveRemoverCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldIndiceProduto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campos não preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					cp.actionPerformed(e);
					JOptionPane.showMessageDialog(null, "Consulta Realizado com Sucesso", "Sucesso!",
							JOptionPane.PLAIN_MESSAGE);
				}
			}

		});

		btnVoltarRemoverCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				Carrinho carrinho;
				try {
					carrinho = new Carrinho(null, null);
					carrinho.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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
}
