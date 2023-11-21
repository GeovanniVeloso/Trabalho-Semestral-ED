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

import controller.ControleClientePJ;

@SuppressWarnings("all")
public class CadastroCNPJ extends BaseFrame {

	private JPanel contentPane;
	private JTextField textFieldNomeFantasia;
	private JTextField textFieldTelefoneCnpj;
	private JTextField textFieldNumeroCnpj;
	private JTextField textFieldLogradouroCnpj;
	private JTextField textFieldCnpj;
	private JTextField textFieldComplementoCnpj;
	private JTextField textFieldCEPCnpj;
	private JTextField textFieldEmailCnpj;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCNPJ frame = new CadastroCNPJ();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroCNPJ() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		AlphaNumericTextField textFieldNomeFantasia = new AlphaNumericTextField();

		textFieldNomeFantasia.setToolTipText("DIGITE O NOME FANTASIA DA EMPRESA");
		textFieldNomeFantasia.setBounds(191, 57, 185, 26);
		contentPane.add(textFieldNomeFantasia);
		textFieldNomeFantasia.setColumns(10);

		AlphaNumericTextField textFieldLogradouroCnpj = new AlphaNumericTextField();
		textFieldLogradouroCnpj.setToolTipText("DIGITE O LOGRADOURO DA EMPRESA");
		textFieldLogradouroCnpj.setBounds(164, 185, 140, 26);
		contentPane.add(textFieldLogradouroCnpj);
		textFieldLogradouroCnpj.setColumns(10);

		AlphaNumericTextField textFieldComplementoCnpj = new AlphaNumericTextField();
		textFieldComplementoCnpj.setToolTipText("DIGITE O COMPLEMENTO DA EMPRESA");
		textFieldComplementoCnpj.setBounds(191, 243, 148, 26);
		contentPane.add(textFieldComplementoCnpj);
		textFieldComplementoCnpj.setColumns(10);

		textFieldEmailCnpj = new JTextField();
		textFieldEmailCnpj.setToolTipText("DIGITE O E-MAIL DA EMPRESA ");
		textFieldEmailCnpj.setBounds(470, 102, 175, 26);
		contentPane.add(textFieldEmailCnpj);
		textFieldEmailCnpj.setColumns(10);

		textFieldCnpj = createNumericTextField();
		textFieldCnpj.setToolTipText("DIGITE O CNPJ");
		textFieldCnpj.setBounds(470, 57, 193, 26);
		contentPane.add(textFieldCnpj);
		textFieldCnpj.setColumns(10);

		textFieldNumeroCnpj = createNumericTextField();
		textFieldNumeroCnpj.setBounds(455, 185, 148, 26);
		contentPane.add(textFieldNumeroCnpj);
		textFieldNumeroCnpj.setColumns(10);
		textFieldNumeroCnpj.setToolTipText("DIGITE O NUMERO DA PORTA DA EMPRESA");

		textFieldTelefoneCnpj = createNumericTextField();
		textFieldTelefoneCnpj.setBounds(133, 102, 133, 26);
		contentPane.add(textFieldTelefoneCnpj);
		textFieldTelefoneCnpj.setColumns(10);
		textFieldTelefoneCnpj.setToolTipText("DIGITE O NUMERO DO TELEFONE");

		textFieldCEPCnpj = createNumericTextField();
		textFieldCEPCnpj.setBounds(423, 240, 148, 26);
		contentPane.add(textFieldCEPCnpj);
		textFieldCEPCnpj.setColumns(10);
		textFieldCEPCnpj.setToolTipText("DIGITE O CEP DA EMPRESA");

		JLabel lblNomeFantasia = new JLabel("NOME FANTASIA :");
		lblNomeFantasia.setToolTipText("NOME FANTASIA ");
		lblNomeFantasia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeFantasia.setBounds(21, 63, 200, 20);
		contentPane.add(lblNomeFantasia);

		JLabel lblTelefoneCnpj = new JLabel("TELEFONE :");
		lblTelefoneCnpj.setToolTipText("TELEFONE ");
		lblTelefoneCnpj.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblTelefoneCnpj.setBounds(21, 102, 107, 26);
		contentPane.add(lblTelefoneCnpj);

		JLabel lblLogradouroCnpj = new JLabel("LOGRADOURO :");
		lblLogradouroCnpj.setToolTipText(" LOGRADOURO");
		lblLogradouroCnpj.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogradouroCnpj.setBounds(21, 186, 160, 26);
		contentPane.add(lblLogradouroCnpj);

		JLabel lblNumeroCnpj = new JLabel("NUMERO :");
		lblNumeroCnpj.setToolTipText(" NUMERO ");
		lblNumeroCnpj.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumeroCnpj.setBounds(347, 179, 200, 33);
		contentPane.add(lblNumeroCnpj);

		JLabel lblComplementoCnpj = new JLabel("COMPLEMENTO :");
		lblComplementoCnpj.setToolTipText(" COMPLEMENTO");
		lblComplementoCnpj.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComplementoCnpj.setBounds(21, 244, 160, 25);
		contentPane.add(lblComplementoCnpj);

		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setToolTipText("CNPJ");
		lblCnpj.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCnpj.setBounds(401, 63, 59, 20);
		contentPane.add(lblCnpj);

		JLabel lblInformacaoesEmpresa = new JLabel("INFORMAÇÕES DA EMPRESA");
		lblInformacaoesEmpresa.setToolTipText("INFORMAÇÕES DA EMPRESA");
		lblInformacaoesEmpresa.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInformacaoesEmpresa.setBounds(21, 11, 341, 26);
		contentPane.add(lblInformacaoesEmpresa);

		JLabel lblCepCnpj = new JLabel("CEP :");
		lblCepCnpj.setToolTipText("CEP");
		lblCepCnpj.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCepCnpj.setBounds(349, 240, 64, 26);
		contentPane.add(lblCepCnpj);

		JLabel lblInfoEmpresaCnpj = new JLabel("INFORMAÇÕES DE ENDEREÇO DA EMPRESA");
		lblInfoEmpresaCnpj.setToolTipText("INFORMAÇÕES DE ENDEREÇO EMPRESA");
		lblInfoEmpresaCnpj.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInfoEmpresaCnpj.setBounds(21, 136, 458, 36);
		contentPane.add(lblInfoEmpresaCnpj);

		JLabel lblEmailCnpj = new JLabel("E-mail:");
		lblEmailCnpj.setToolTipText("E-MAIL DA EMPRESA");
		lblEmailCnpj.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmailCnpj.setBounds(399, 108, 80, 20);
		contentPane.add(lblEmailCnpj);
		
		JButton btnVoltarCnpj = new JButton("Voltar");
		btnVoltarCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVoltarCnpj.setBounds(430, 285, 110, 30);
		contentPane.add(btnVoltarCnpj);
		
		JButton btnCadastrarCnpj = new JButton("Cadastrar");
		btnCadastrarCnpj.setBackground(new Color(0, 250, 154));
		btnCadastrarCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrarCnpj.setBounds(550, 285, 110, 30);
		contentPane.add(btnCadastrarCnpj);
		
		ControleClientePJ controleClientePJ = new ControleClientePJ(
		        textFieldCnpj,
		        textFieldNomeFantasia,
		        textFieldLogradouroCnpj,
		        textFieldNumeroCnpj,
		        textFieldComplementoCnpj,
		        textFieldCEPCnpj,
		        textFieldTelefoneCnpj,
		        textFieldEmailCnpj
		    );
	
		/*BOTÕES A PARTIR DAQUI*/

		btnCadastrarCnpj.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		        // Verifica se todos os campos estão preenchidos
		        if (textFieldNomeFantasia.getText().isEmpty() || textFieldTelefoneCnpj.getText().isEmpty()
		                || textFieldNumeroCnpj.getText().isEmpty() || textFieldLogradouroCnpj.getText().isEmpty()
		                || textFieldCnpj.getText().isEmpty() || textFieldComplementoCnpj.getText().isEmpty()
		                || textFieldCEPCnpj.getText().isEmpty() || textFieldEmailCnpj.getText().isEmpty()) {
		        	
		            JOptionPane.showMessageDialog(null, "Campos não preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
		        
		        } else {
		        	//Aciona a Classe de controle do Cliente
		        	controleClientePJ.actionPerformed(e);
		        	JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso", "Sucesso!", JOptionPane.PLAIN_MESSAGE);
		            dispose();
		        }
		    }
		});

		btnVoltarCnpj.addActionListener(new ActionListener() {
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
