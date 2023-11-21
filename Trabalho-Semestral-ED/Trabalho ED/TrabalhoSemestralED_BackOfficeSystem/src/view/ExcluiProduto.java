package view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;



public class ExcluiProduto extends BaseFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluiProduto frame = new ExcluiProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		JLabel lblNewLabel = new JLabel("EXCLUSÃO DE PRODUTO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(192, 11, 267, 31);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CODIGO DO PRODUTO  QUE DESEJA EXCLUIR :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 106, 462, 20);
		contentPane.add(lblNewLabel_1);

		textField = createNumericTextField();
		textField.setBounds(448, 109, 144, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
	
		
		JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnVoltar.setBounds(430, 285, 110, 30);
        contentPane.add(btnVoltar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnExcluir.setBounds(550, 285, 110, 30);
        contentPane.add(btnExcluir);
        
	        
	        JLabel lblNewLabel_2 = new JLabel("O RESULTADO DA EXCLUSÃO FOI :");
	        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
	        lblNewLabel_2.setBounds(10, 178, 335, 39);
	        contentPane.add(lblNewLabel_2);
	        
	        textField_1 = new JTextField();
	        textField_1.setBounds(323, 190, 136, 20);
	        contentPane.add(textField_1);
	        textField.setColumns(10);
	
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

}
