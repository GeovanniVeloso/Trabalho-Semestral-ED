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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.ControleClientePF;
import controller.ControleClientePJ;

public class ExcluiCliente extends BaseFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField textField;
	private JTextField textFieldCpf;
	private JTextField textFieldCnpj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluiCliente frame = new ExcluiCliente();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExcluiCliente() {
		getContentPane().setLayout(null);

		contentPane = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);

		JLabel lblExclusaoCliente = new JLabel("EXCLUSÃO DE CLIENTE ");
		lblExclusaoCliente.setToolTipText("EXCLUSAO DE CLIENTE ");
		lblExclusaoCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblExclusaoCliente.setBounds(192, 11, 267, 31);
		contentPane_1.add(lblExclusaoCliente);

		JLabel lblEscolhaExclusaoCliente = new JLabel("ESCOLHA O TIPO DO CLIENTE :");
		lblEscolhaExclusaoCliente.setToolTipText("ESCOLHA O TIPO DO CLIENTE");
		lblEscolhaExclusaoCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEscolhaExclusaoCliente.setBounds(10, 83, 302, 20);
		contentPane_1.add(lblEscolhaExclusaoCliente);

		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("CPF");
		comboBoxModel.addElement("CNPJ");
		JComboBox<String> comboBox = new JComboBox<>(comboBoxModel);
		comboBox.setBounds(322, 83, 126, 22);
		contentPane_1.add(comboBox);
		comboBox.setSelectedItem("CPF");

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

		JButton btnVoltarExcluirCliente = new JButton("Voltar");
		btnVoltarExcluirCliente.setToolTipText("VOLTE A TELA ANTERIOR");
		btnVoltarExcluirCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltarExcluirCliente.setBounds(430, 285, 110, 30);
		contentPane_1.add(btnVoltarExcluirCliente);

		JButton btnExcluirCliente = new JButton("Excluir");
		btnExcluirCliente.setForeground(new Color(255, 255, 255));
		btnExcluirCliente.setBackground(new Color(245, 7, 7));
		btnExcluirCliente.setToolTipText("EXCLUIR CLIENTE");
		btnExcluirCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExcluirCliente.setBounds(550, 285, 110, 30);
		contentPane_1.add(btnExcluirCliente);

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

		/* Classes Controller */
		ControleClientePJ controleClientePJ = new ControleClientePJ(textFieldCnpj);
		ControleClientePF controleClientePF = new ControleClientePF(textFieldCpf);

		/* Inicio Dos Botões */
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) comboBox.getSelectedItem();

				// Verifica se os campos estão vazios
				if ((textFieldCpf.isEnabled() && textFieldCpf.getText().isEmpty())
						|| (textFieldCnpj.isEnabled() && textFieldCnpj.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos antes de consultar.");

				} else {

					// Faça algo com o item selecionado
					if ("CPF".equals(selectedItem)) {
						controleClientePF.actionPerformed(e);
						dispose();

					} else if ("CNPJ".equals(selectedItem)) {
						controleClientePJ.actionPerformed(e);
						dispose();

					}
					ExcluiCliente excClientejFrame = new ExcluiCliente();
					excClientejFrame.setVisible(true);

					// Fecha o frame atual, se necessário
					setVisible(false);
					dispose();
				}
			}
		});

		btnVoltarExcluirCliente.addActionListener(new ActionListener() {
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
}