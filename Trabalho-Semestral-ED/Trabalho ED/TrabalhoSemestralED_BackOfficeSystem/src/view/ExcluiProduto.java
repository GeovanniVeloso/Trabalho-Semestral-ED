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

import controller.ControleProduto;
import view.ConsultaProduto.AlphaNumericTextField;
import view.ConsultaProduto.AlphaNumericTextField.AlphaNumericDocument;
 
 
 
public class ExcluiProduto extends BaseFrame {
 
	private JPanel contentPane;
	private JTextField textFieldExcluirProduto;
	/**
	 * Launch the application.
	 */
 
 
	/**
	 * Create the frame.
	 */
	public ExcluiProduto() {
		getContentPane().setLayout(null);
 
		contentPane = new JPanel();
 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblExclusaoProduto = new JLabel("EXCLUSÃO DE PRODUTO");
		lblExclusaoProduto.setToolTipText("EXCLUSÃO DE PRODUTO");
		lblExclusaoProduto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblExclusaoProduto.setBounds(192, 23, 267, 31);
		contentPane.add(lblExclusaoProduto);
 
		JLabel lblCodigoProduto = new JLabel("CODIGO DO PRODUTO  QUE DESEJA EXCLUIR :");
		lblCodigoProduto.setToolTipText("CODIGO DO PRODUTO  QUE DESEJA EXCLUIR ");
		lblCodigoProduto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCodigoProduto.setBounds(10, 106, 462, 20);
		contentPane.add(lblCodigoProduto);
 
		textFieldExcluirProduto = createNumericTextField();
		textFieldExcluirProduto.setToolTipText("DIGITE O CODIGO DO PRODUTO QUE DESEJA EXCLUIR");
		textFieldExcluirProduto.setBounds(448, 108, 172, 20);
		contentPane.add(textFieldExcluirProduto);
		textFieldExcluirProduto.setColumns(10);
		
        JLabel lblTipoProduto = new JLabel("TIPO PRODUTO :");
		lblTipoProduto.setToolTipText("TIPO PRODUTO");
		lblTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTipoProduto.setBounds(278, 149, 175, 20);
		contentPane.add(lblTipoProduto);
		
		AlphaNumericTextField  textFieldTipoProduto = new AlphaNumericTextField ();
		textFieldTipoProduto.setBounds(448, 150, 172, 20);
		contentPane.add(textFieldTipoProduto);
		textFieldTipoProduto.setColumns(10);
		
	    /*BOTOES*/
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setToolTipText("VOLTAR");
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnVoltar.setBounds(430, 285, 110, 30);
        contentPane.add(btnVoltar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setToolTipText("EXCLUIR");
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.setBackground(new Color(245, 7, 7));
        btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExcluir.setBounds(550, 285, 110, 30);
        contentPane.add(btnExcluir);
		
	   btnVoltar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               // Cria uma instância da tela inicial (classe ED) e a torna visível
        	   TelaInicial telaInicial = new TelaInicial();
               telaInicial.setVisible(true);
 
               // Fecha o frame atual
               dispose();
           }
       });
 
	   ControleProduto cp = new ControleProduto(textFieldExcluirProduto, textFieldTipoProduto);
	   btnExcluir.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
           	if(textFieldExcluirProduto.getText().isEmpty()) {
           		JOptionPane.showMessageDialog(null, "Campo não preenchido", "Erro", JOptionPane.ERROR_MESSAGE);
           	} else {
           		JOptionPane.showMessageDialog(null, "Exclusao concluida", "SUCESSO", JOptionPane.PLAIN_MESSAGE);
           		cp.actionPerformed(e);
           		ExcluiProduto excProdutojFrame = new ExcluiProduto();
           		excProdutojFrame.setVisible(true);
 
				// Fecha o frame atual, se necessário
				setVisible(false);
				dispose();
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
