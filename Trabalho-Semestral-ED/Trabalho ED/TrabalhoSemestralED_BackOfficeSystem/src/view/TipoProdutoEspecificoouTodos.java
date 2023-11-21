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
import javax.swing.border.EmptyBorder;

public class TipoProdutoEspecificoouTodos extends BaseFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public TipoProdutoEspecificoouTodos() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipoProdutoConsulta = new JLabel("ESCOLHA QUAL O TIPO DE CONSULTA");
		lblTipoProdutoConsulta.setToolTipText("SELECIONE O TIPO DE CONSULTA");
		lblTipoProdutoConsulta.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTipoProdutoConsulta.setBounds(118, 21, 421, 44);
		contentPane.add(lblTipoProdutoConsulta);
		
		JButton btnTipoProdutoEspecifico = new JButton("ESPECIFICA");
		btnTipoProdutoEspecifico.setToolTipText("CONSULTAR TIPO PRODUTO ESPECIFICO");
		btnTipoProdutoEspecifico.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTipoProdutoEspecifico.setBounds(198, 111, 229, 44);
		contentPane.add(btnTipoProdutoEspecifico);
		
		JButton btnTipoProdutoTodos = new JButton("TODOS");
		btnTipoProdutoTodos.setToolTipText("CONSULTAR  TODOS OS TIPOS DE PRODUTOS");
		btnTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTipoProdutoTodos.setBounds(198, 182, 229, 44);
		contentPane.add(btnTipoProdutoTodos);

		 JButton btnVoltar = new JButton("Voltar");
		 btnVoltar.setToolTipText("VOLTAR A TELA ANTERIOR");
	        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnVoltar.setBounds(550, 285, 110, 30);
	        contentPane.add(btnVoltar);
	        
	 
	        
	        
	    	
			
	        btnTipoProdutoEspecifico.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Cria uma instância da classe pfoupj e a torna visível
	            	ConsultaTipoProdutoEspecifica ConsultaTipoProdutoEspecificajFrame = new ConsultaTipoProdutoEspecifica();
	            	ConsultaTipoProdutoEspecificajFrame.setVisible(true);

	                // Fecha o frame atual, se necessário
	                 setVisible(false);
	                 dispose();
	            }
	        });
	        btnTipoProdutoTodos.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Cria uma instância da classe pfoupj e a torna visível
	            	ConsultaTipoProdutoTodos ConsultaTipoProdutoTodosjFrame = new ConsultaTipoProdutoTodos();
	            	ConsultaTipoProdutoTodosjFrame.setVisible(true);

	                // Fecha o frame atual, se necessário
	                 setVisible(false);
	                 dispose();
	            }
	        });
	        
	        

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
