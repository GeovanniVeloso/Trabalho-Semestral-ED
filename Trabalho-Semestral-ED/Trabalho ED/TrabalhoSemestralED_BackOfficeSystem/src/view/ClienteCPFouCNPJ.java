package view;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;

public class ClienteCPFouCNPJ extends BaseFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteCPFouCNPJ frame = new ClienteCPFouCNPJ();
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
	public ClienteCPFouCNPJ() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ESCOLHA QUAL O TIPO DE CLIENTE");
		lblNewLabel.setToolTipText("ESCOLHA CADASTRAR O CLIENTE POR CPF OU CNPJ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(118, 21, 395, 44);
		contentPane.add(lblNewLabel);
		
		JButton btnClienteCpf = new JButton("CPF");
		btnClienteCpf.setToolTipText("CADASTRAR CLIENTE POR CPF");
		btnClienteCpf.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnClienteCpf.setBounds(198, 111, 229, 44);
		contentPane.add(btnClienteCpf);
		
		JButton btnClienteCnpj = new JButton(" CNPJ");
		btnClienteCnpj.setToolTipText("CADASTRAR CLIENTE POR CNPJ");
		btnClienteCnpj.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnClienteCnpj.setBounds(198, 182, 229, 44);
		contentPane.add(btnClienteCnpj);

		 JButton btnVoltar = new JButton("Voltar");
		 btnVoltar.setToolTipText("VOLTAR A TELA ANTERIOR");
	        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnVoltar.setBounds(550, 285, 110, 30);
	        contentPane.add(btnVoltar);
	        
	    	
			
	        btnClienteCpf.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Cria uma instância da classe pfoupj e a torna visível
	            	CadastroCPF clienteCpfjFrame = new CadastroCPF();
	            	clienteCpfjFrame.setVisible(true);

	                // Fecha o frame atual, se necessário
	                 setVisible(false);
	                 dispose();
	            }
	        });
	        btnClienteCnpj.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Cria uma instância da classe pfoupj e a torna visível
	            	CadastroCNPJ clienteCnpjjFrame = new CadastroCNPJ();
	            	clienteCnpjjFrame.setVisible(true);

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
