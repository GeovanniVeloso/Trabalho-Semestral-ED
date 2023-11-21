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

import controller.TipoProdutoController;
import model.ListaEncadeada;
import model_main.TipoProduto;





public class ExcluiTipoDeProduto extends BaseFrame {

	private JPanel contentPane;
	private JTextField textFieldExclusaoTipoProduto;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ExcluiTipoDeProduto() {
		getContentPane().setLayout(null);

		contentPane = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblExclusaoTipoProduto = new JLabel("EXCLUSÃO DE TIPO DE PRODUTO");
		lblExclusaoTipoProduto.setToolTipText("EXCLUSÃO DE TIPO DE PRODUTO");
		lblExclusaoTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblExclusaoTipoProduto.setBounds(154, 11, 348, 31);
		contentPane.add(lblExclusaoTipoProduto);

		JLabel lblNomeExclusaoTipoProduto = new JLabel("NOME DO TIPO DO PRODUTO PARA EXCLUSÃO ");
		lblNomeExclusaoTipoProduto.setToolTipText("NOME DO TIPO DO PRODUTO PARA EXCLUSÃO ");
		lblNomeExclusaoTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeExclusaoTipoProduto.setBounds(10, 106, 452, 20);
		contentPane.add(lblNomeExclusaoTipoProduto);

		AlphaNumericTextField textFieldExclusaoTipoProduto =  new AlphaNumericTextField();
	    textFieldExclusaoTipoProduto.setBounds(459, 106, 205, 20);
	    textFieldExclusaoTipoProduto.setToolTipText(" DIGITE O NOME DO TIPO DE PRODUTO PARA EXCLUSÃO ");
		contentPane.add(textFieldExclusaoTipoProduto);
		textFieldExclusaoTipoProduto.setColumns(10);
		
	
		
		JButton btnVoltarExclusaoTipoProduto = new JButton("Voltar");
		btnVoltarExclusaoTipoProduto.setToolTipText("VOLTAR");
        btnVoltarExclusaoTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnVoltarExclusaoTipoProduto.setBounds(430, 285, 110, 30);
        contentPane.add(btnVoltarExclusaoTipoProduto);
        
        JButton btnExclusaoTipoProduto = new JButton("Excluir");
        btnExclusaoTipoProduto.setToolTipText("EXCLUIR");
		btnExclusaoTipoProduto.setForeground(new Color(255, 255, 255));
		btnExclusaoTipoProduto.setBackground(new Color(245, 7, 7));
        btnExclusaoTipoProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExclusaoTipoProduto.setBounds(550, 285, 110, 30);
        contentPane.add(btnExclusaoTipoProduto);
        
        ListaEncadeada<TipoProduto> listaTipoProduto = new ListaEncadeada<>();
        TipoProdutoController methodsTipoProduto = new TipoProdutoController(textFieldExclusaoTipoProduto, listaTipoProduto);
        
        btnExclusaoTipoProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(textFieldExclusaoTipoProduto.getText().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "Campo não preenchido", "Erro", JOptionPane.ERROR_MESSAGE);
            	} else {
            		methodsTipoProduto.actionPerformed(e);
            		textFieldExclusaoTipoProduto.setText("");
            	}
            }
        });
	        
	 
	   btnVoltarExclusaoTipoProduto.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               // Cria uma instância da tela inicial (classe ED) e a torna visível
        	   TelaInicial telaInicial = new TelaInicial();
               telaInicial.setVisible(true);

               // Fecha o frame atual
               dispose();
           }
       });

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
