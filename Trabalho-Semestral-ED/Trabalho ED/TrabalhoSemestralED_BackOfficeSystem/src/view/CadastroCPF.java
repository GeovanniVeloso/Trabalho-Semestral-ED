package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

@SuppressWarnings("all")
public class CadastroCPF extends BaseFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCelular;
	private JTextField textFieldNumero;
	private JTextField textFieldLogradouroCpf;
	private JTextField textFieldCpf;
	private JTextField textFieldComplementoCpf;
	private JTextField textFieldCEP;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCPF frame = new CadastroCPF();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroCPF() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		AlphaNumericTextField textFieldNome = new AlphaNumericTextField();
		textFieldNome.setToolTipText("DIGITE SEU NOME");
		textFieldNome.setBounds(110, 48, 158, 26);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		AlphaNumericTextField textFieldLogradouroCpf = new AlphaNumericTextField();
		textFieldLogradouroCpf.setToolTipText("DIGITE SEU LOGRADOURO");
		textFieldLogradouroCpf.setBounds(164, 185, 140, 26);
		contentPane.add(textFieldLogradouroCpf);
		textFieldLogradouroCpf.setColumns(10);

		AlphaNumericTextField textFieldComplementoCpf = new AlphaNumericTextField(); // Use a variável de instância
		textFieldComplementoCpf.setToolTipText("DIGITE SEU COMPLEMENTO");
		textFieldComplementoCpf.setBounds(191, 243, 148, 26);
		contentPane.add(textFieldComplementoCpf);
		textFieldComplementoCpf.setColumns(10);

		textFieldCpf = createNumericTextField();
		textFieldCpf.setToolTipText("DIGITE SEU CPF");
		textFieldCpf.setBounds(372, 48, 193, 26);
		contentPane.add(textFieldCpf);
		textFieldCpf.setColumns(10);
		textFieldCpf.setToolTipText("DIGITE SEU CPF");

		textFieldNumero = createNumericTextField();
		textFieldNumero.setBounds(455, 185, 148, 26);
		contentPane.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		textFieldNumero.setToolTipText("DIGITE O NUMERO");

		textFieldCelular = createNumericTextField();
		textFieldCelular.setBounds(117, 102, 133, 26);
		contentPane.add(textFieldCelular);
		textFieldCelular.setColumns(10);
		textFieldCelular.setToolTipText("DIGITE O NUMERO DO SEU CELULAR");

		textFieldCEP = createNumericTextField();
		textFieldCEP.setBounds(423, 240, 148, 26);
		contentPane.add(textFieldCEP);
		textFieldCEP.setColumns(10);
		textFieldCEP.setToolTipText("DIGITE SEU CEP");

		JLabel lblNomeCpf = new JLabel("NOME :");
		lblNomeCpf.setToolTipText("NOME ");
		lblNomeCpf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeCpf.setBounds(21, 57, 79, 20);
		contentPane.add(lblNomeCpf);

		JLabel lblCelularCpf = new JLabel("CELULAR :");
		lblCelularCpf.setToolTipText("CELULAR ");
		lblCelularCpf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCelularCpf.setBounds(21, 99, 133, 26);
		contentPane.add(lblCelularCpf);

		JLabel lblLogradouroCpf = new JLabel("LOGRADOURO :");
		lblLogradouroCpf.setToolTipText(" LOGRADOURO");
		lblLogradouroCpf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogradouroCpf.setBounds(21, 185, 160, 26);
		contentPane.add(lblLogradouroCpf);

		JLabel lblNumeroCpf = new JLabel("NUMERO :");
		lblNumeroCpf.setToolTipText(" NUMERO ");
		lblNumeroCpf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumeroCpf.setBounds(347, 179, 200, 33);
		contentPane.add(lblNumeroCpf);

		JLabel lblComplementoCpf = new JLabel("COMPLEMENTO :");
		lblComplementoCpf.setToolTipText(" COMPLEMENTO");
		lblComplementoCpf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComplementoCpf.setBounds(21, 244, 160, 25);
		contentPane.add(lblComplementoCpf);

		JLabel lbCpf = new JLabel("CPF :");
		lbCpf.setToolTipText("CPF ");
		lbCpf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbCpf.setBounds(293, 57, 59, 17);
		contentPane.add(lbCpf);

		JLabel lblInformacoes = new JLabel("INFORMAÇÕES PESSOAIS");
		lblInformacoes.setToolTipText("INFORMAÇÕES PESSOAIS");
		lblInformacoes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInformacoes.setBounds(21, 11, 283, 26);
		contentPane.add(lblInformacoes);

		JLabel lblCepCpf = new JLabel("CEP :");
		lblCepCpf.setToolTipText("CEP");
		lblCepCpf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCepCpf.setBounds(349, 240, 64, 26);
		contentPane.add(lblCepCpf);

		JLabel lblInformacoesEnderecoCpf = new JLabel("INFORMAÇÕES DE ENDEREÇO");
		lblInformacoesEnderecoCpf.setToolTipText("INFORMAÇÕES DE ENDEREÇO");
		lblInformacoesEnderecoCpf.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInformacoesEnderecoCpf.setBounds(21, 136, 308, 36);
		contentPane.add(lblInformacoesEnderecoCpf);
		
		ControleClientePF controleClientePF = new ControleClientePF(textFieldCpf, textFieldNome, textFieldCelular, textFieldLogradouroCpf, textFieldNumero, textFieldComplementoCpf, textFieldCEP);
		
		//* BOTÕES *//
		JButton btnVoltarCpf = new JButton("Voltar");
		btnVoltarCpf.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltarCpf.setBounds(430, 285, 110, 30);
		contentPane.add(btnVoltarCpf);

		JButton btnCadastrarCpf = new JButton("Cadastrar");
		btnCadastrarCpf.setBackground(new Color(51, 204, 102));
		btnCadastrarCpf.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCadastrarCpf.setBounds(550, 285, 114, 30);
		contentPane.add(btnCadastrarCpf);

		btnCadastrarCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Perform the check for empty text fields
				if (textFieldNome.getText().isEmpty() || textFieldCelular.getText().isEmpty()
						|| textFieldNumero.getText().isEmpty() || textFieldLogradouroCpf.getText().isEmpty()
						|| textFieldCpf.getText().isEmpty() || textFieldComplementoCpf.getText().isEmpty()
						|| textFieldCEP.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Campos não preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
				
				} else {
					controleClientePF.actionPerformed(e);
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso", "Sucesso!", JOptionPane.PLAIN_MESSAGE);
					// Cria uma instância da classe pfoupj e a torna visível
	            	CadastroCPF clienteCpfjFrame = new CadastroCPF();
	            	clienteCpfjFrame.setVisible(true);

	                // Fecha o frame atual, se necessário
	                 setVisible(false);
	                 dispose();
				}
			}
		});

		btnVoltarCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				ClienteCPFouCNPJ telaInicial = new ClienteCPFouCNPJ();
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