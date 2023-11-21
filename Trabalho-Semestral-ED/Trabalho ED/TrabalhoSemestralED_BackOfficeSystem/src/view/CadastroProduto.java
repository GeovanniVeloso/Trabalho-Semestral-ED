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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CadastroProduto extends BaseFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = new CadastroProduto();
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
	public CadastroProduto() {

		getContentPane().setLayout(null);

		contentPane = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(216, 115, 170, 20);
		contentPane_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(540, 175, 99, 20);
		contentPane_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(553, 112, 86, 20);
		contentPane_1.add(textField_2);
		textField_2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 221, 218, 74);
		contentPane_1.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textField_3 = new JTextField();
		textField_3.setBounds(270, 173, 105, 20);
		contentPane_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("NOME PRODUTO :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(42, 112, 194, 20);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("VALOR :");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(431, 172, 99, 20);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DESCRIÇÃO :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(44, 216, 158, 30);
		contentPane_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("QUANTIDADE :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(407, 112, 158, 20);
		contentPane_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CÓDIGO DO PRODUTO :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(42, 165, 218, 30);
		contentPane_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CADASTRO DE PRODUTO");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setBounds(216, 39, 324, 20);
		contentPane_1.add(lblNewLabel_5);
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("Bens de Consumo");
		comboBoxModel.addElement("Produtos ao Consumidor");
		
		JLabel lblNewLabel_6 = new JLabel("TIPO DE PRODUTO");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_6.setBounds(458, 221, 181, 20);
		contentPane_1.add(lblNewLabel_6);
		
		
		
        
	
		
		JButton btnVoltar = new JButton("Voltar");
	        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        btnVoltar.setBounds(430, 285, 110, 30);
	        contentPane_1.add(btnVoltar);
	        
	        JButton btnCadastrar = new JButton("Cadastrar");
	        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        btnCadastrar.setBounds(550, 285, 110, 30);
	        contentPane_1.add(btnCadastrar);
	        
	        textField_4 = new JTextField();
	        textField_4.setBounds(458, 248, 170, 20);
	        contentPane_1.add(textField_4);
	        textField_4.setColumns(10);
	
	
	
	
	


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
}
