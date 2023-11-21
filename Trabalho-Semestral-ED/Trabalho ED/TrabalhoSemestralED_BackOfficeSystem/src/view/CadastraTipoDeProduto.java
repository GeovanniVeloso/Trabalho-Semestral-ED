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

import controller.TipoProdutoController;
import model.ListaEncadeada;
import model_main.TipoProduto;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
@SuppressWarnings("all")
public class CadastraTipoDeProduto extends BaseFrame {


	private JPanel contentPane_1_1;
	
	private JTextField textFieldEspecifiqueTipoProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraTipoDeProduto frame = new CadastraTipoDeProduto();
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
	public CadastraTipoDeProduto() {

		getContentPane().setLayout(null);

		contentPane_1_1 = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane_1_1 = new JPanel();
		contentPane_1_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1_1.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane_1_1);
		contentPane_1_1.setLayout(null);
		
		JLabel lblCadastroTipoProduto = new JLabel("CADASTRO DE TIPO DE PRODUTO");
		lblCadastroTipoProduto.setToolTipText("CADASTRO DE TIPO DE PRODUTO");
		lblCadastroTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCadastroTipoProduto.setBounds(147, 32, 364, 28);
		contentPane_1_1.add(lblCadastroTipoProduto);
		
		
		
		
		
		
		JLabel lblEspecifiqueTipoProduto = new JLabel("ESPECIFIQUE O TIPO DO PRODUTO :");
		lblEspecifiqueTipoProduto.setToolTipText("ESPECIFIQUE O TIPO DO PRODUTO ");
		lblEspecifiqueTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEspecifiqueTipoProduto.setBounds(24, 115, 351, 38);
		contentPane_1_1.add(lblEspecifiqueTipoProduto);
		
		JLabel lblDescricaoTipoProduto = new JLabel("DESCRIÇÃO DO TIPO DO PRODUTO");
		lblDescricaoTipoProduto.setToolTipText("DESCREVA O TIPO DO PRODUTO");
		lblDescricaoTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDescricaoTipoProduto.setBounds(24, 175, 351, 23);
		contentPane_1_1.add(lblDescricaoTipoProduto);
		
		
		
		JButton btnVoltarCadastroTipoProduto = new JButton("Voltar");
		btnVoltarCadastroTipoProduto.setToolTipText("VOLTAR A TELA ANTERIOR");
		
	        btnVoltarCadastroTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnVoltarCadastroTipoProduto.setBounds(430, 285, 100, 30);
	        contentPane_1_1.add(btnVoltarCadastroTipoProduto);
	        
	        JButton btnCadastroCadastrarTipoProduto = new JButton("Cadastrar");
	        btnCadastroCadastrarTipoProduto.setToolTipText("CADASTRAR O TIPO DE PRODUTO");
	        btnCadastroCadastrarTipoProduto.setBackground(new Color(51, 204, 102));
	        btnCadastroCadastrarTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnCadastroCadastrarTipoProduto.setBounds(543, 285, 121, 30);
	        contentPane_1_1.add(btnCadastroCadastrarTipoProduto);
	        
	        //New JTextField
	        AlphaNumericTextField textFieldEspecifiqueTipoProduto = new AlphaNumericTextField();
	        textFieldEspecifiqueTipoProduto.setToolTipText(" DIGITE A ESPECIFICAÇÃO DO TIPO DO PRODUTO ");
	        textFieldEspecifiqueTipoProduto.setColumns(10);
	        textFieldEspecifiqueTipoProduto.setBounds(360, 127, 291, 20);
	        contentPane_1_1.add(textFieldEspecifiqueTipoProduto);
	        
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setToolTipText("DESCREVA O TIPO DO PRODUTO");
	        scrollPane.setBounds(24, 200, 627, 56);
	        contentPane_1_1.add(scrollPane);
	        
	        JTextArea textAreaDescricaoTipoProduto = new JTextArea();
	        scrollPane.setViewportView(textAreaDescricaoTipoProduto);
	        textAreaDescricaoTipoProduto.setToolTipText(" DIGITE A DESCRIÇÃO DO TIPO DO PRODUTO");

	        ListaEncadeada<TipoProduto> listaTipoProduto = new ListaEncadeada<>();
	        TipoProdutoController methodsTipoProduto = new TipoProdutoController(textFieldEspecifiqueTipoProduto, textAreaDescricaoTipoProduto
	        												, listaTipoProduto);

	        btnCadastroCadastrarTipoProduto.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if(textFieldEspecifiqueTipoProduto.getText().isEmpty() || textAreaDescricaoTipoProduto.getText().isEmpty()) {
	                	JOptionPane.showMessageDialog(null, "Campos não preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
	                } else {
	                	methodsTipoProduto.actionPerformed(e);
	                	//Limpar Campos
	                    textFieldEspecifiqueTipoProduto.setText("");
	                    textAreaDescricaoTipoProduto.setText("");
	                }
	            }
	        });	
	       		
	

        btnVoltarCadastroTipoProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância da tela inicial (classe ED) e a torna visível
            	TelaInicial telaInicial = new TelaInicial();
                telaInicial.setVisible(true);

                // Fecha o frame atual
                dispose();
            }
        });
        
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
