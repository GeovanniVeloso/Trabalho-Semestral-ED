package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.ControleClientePF;
import controller.ControleClientePJ;
import controller.ControleProduto;
import model.ListaEncadeada;
import model_main.Produto;

public class ClienteCarrinho extends BaseFrame {

	private JPanel contentPane_1;

	private JTextField textFieldCpf;
	private JTextField textFieldCnpj;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ClienteCarrinho(ListaEncadeada<Produto> produtosCarrinho) {

		getContentPane().setLayout(null);

		contentPane_1 = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);

		JLabel lblConsultaCliente = new JLabel("VINCULE A COMPRA A UM CLIENTE");
		lblConsultaCliente.setToolTipText("VINCULE A COMPRA A UM CLIENTE");
		lblConsultaCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblConsultaCliente.setBounds(142, 11, 398, 31);
		contentPane_1.add(lblConsultaCliente);

		JLabel lblEscolhaCliente = new JLabel("ESCOLHA O TIPO DO CLIENTE :");
		lblEscolhaCliente.setToolTipText("ESCOLHA O TIPO DO CLIENTE");
		lblEscolhaCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEscolhaCliente.setBounds(10, 83, 302, 20);
		contentPane_1.add(lblEscolhaCliente);

		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("CPF");
		comboBoxModel.addElement("CNPJ");
		JComboBox<String> comboBox = new JComboBox<>(comboBoxModel);
		comboBox.setBounds(322, 83, 126, 22);
		contentPane_1.add(comboBox);
		comboBox.setSelectedItem("CPF");

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setToolTipText("VOLTE A TELA ANTERIOR");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltar.setBounds(363, 285, 110, 30);
		contentPane_1.add(btnVoltar);

		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.setBackground(new Color(255, 160, 122));
		btnFinalizar.setToolTipText("Finalizar Compra");
		btnFinalizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizar.setBounds(483, 285, 181, 30);

		contentPane_1.add(btnFinalizar);

		textFieldCpf = createNumericTextField();
		textFieldCpf.setToolTipText("CLIENTE POR CPF");
		textFieldCpf.setBounds(77, 150, 131, 20);
		contentPane_1.add(textFieldCpf);
		textFieldCpf.setColumns(10);

		textFieldCnpj = createNumericTextField();
		textFieldCnpj.setToolTipText("CLIENTE POR CNPJ");
		textFieldCnpj.setBounds(77, 187, 171, 20);
		contentPane_1.add(textFieldCnpj);
		textFieldCnpj.setColumns(10);
		textFieldCnpj.setEnabled(false);

		JLabel lblCpf = new JLabel("CPF :");
		lblCpf.setToolTipText("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCpf.setBounds(10, 150, 96, 17);
		contentPane_1.add(lblCpf);

		JLabel lblCnpj = new JLabel("CNPJ :");
		lblCnpj.setToolTipText("CNPJ");
		lblCnpj.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCnpj.setBounds(10, 187, 96, 17);
		contentPane_1.add(lblCnpj);

		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) comboBox.getSelectedItem();

				if ("CPF".equals(selectedItem)) {
					textFieldCnpj.setEnabled(false);
					textFieldCpf.setEnabled(true);
				} else if ("CNPJ".equals(selectedItem)) {
					textFieldCpf.setEnabled(false);
					textFieldCnpj.setEnabled(true);
				}
			}
		});

		ControleClientePJ controleClientePJ = new ControleClientePJ(textFieldCnpj);
		ControleClientePF controleClientePF = new ControleClientePF(textFieldCpf);

		// BOTÕES //
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtenha o item selecionado no combobox
				String selectedItem = (String) comboBox.getSelectedItem();

				// Verifique se os campos estão vazios
				if ((textFieldCpf.isEnabled() && textFieldCpf.getText().isEmpty())
						|| (textFieldCnpj.isEnabled() && textFieldCnpj.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos antes de consultar.");
					return; // Não continue com a consulta se algum campo estiver vazio
				}

				// Faça algo com o item selecionado
				if ("CPF".equals(selectedItem)) {
					//Controle do maycon
					controleClientePF.actionPerformed(e);
					dispose();
				} else if ("CNPJ".equals(selectedItem)) {
					//Controle do maycon
					controleClientePJ.actionPerformed(e);
					dispose();
				}
				
				Checkout CheckoutjFrame = new Checkout(produtosCarrinho);
				CheckoutjFrame.setVisible(true);

				// Fecha o frame atual, se necessário
				setVisible(false);
				dispose();

			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Se for Trocar o Controller alterar aqui
					ControleProduto cp = new ControleProduto();
					Carrinho CarrinhojFrame = new Carrinho(null, null);
					CarrinhojFrame.setVisible(true);
					cp.actionPerformed(e);
					// Fecha o frame atual, se necessário
					setVisible(false);
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
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