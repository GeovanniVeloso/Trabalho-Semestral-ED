package view;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.TipoProdutoController;
import model.ListaEncadeada;
import model_main.TipoProduto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ConsultaTipoProdutoEspecifica extends BaseFrame {

	
	private JPanel contentPane_1;

	private JTextField textFieldNomeTipoProdutoEspecifica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaTipoProdutoEspecifica frame = new ConsultaTipoProdutoEspecifica();
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
	public ConsultaTipoProdutoEspecifica() {
		getContentPane().setLayout(null);

		contentPane_1 = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblConsultaEspecifica = new JLabel("CONSULTA DE TIPO DE PRODUTO ESPECIFICA");
		lblConsultaEspecifica.setToolTipText("CONSULTA DE TIPO DE PRODUTO ESPECIFICA");
		lblConsultaEspecifica.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblConsultaEspecifica.setBounds(79, 26, 471, 25);
		contentPane_1.add(lblConsultaEspecifica);
		
		JLabel lblNomeTipoProdutoEspecifica = new JLabel("NOME DO TIPO DE PRODUTO :");
		lblNomeTipoProdutoEspecifica.setToolTipText("DIGITE O NOME DO TIPO DE PRODUTO ");
		lblNomeTipoProdutoEspecifica.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeTipoProdutoEspecifica.setBounds(10, 95, 299, 33);
		contentPane_1.add(lblNomeTipoProdutoEspecifica);
		
		
		// 	AlphaNumericTextField textFieldLogradouroCpf = new AlphaNumericTextField();
		AlphaNumericTextField textFieldNomeTipoProdutoEspecifica = new AlphaNumericTextField();
		textFieldNomeTipoProdutoEspecifica.setToolTipText("DIGITE O NOME DO TIPO DE PRODUTO");
		textFieldNomeTipoProdutoEspecifica.setBounds(293, 102, 217, 25);
		contentPane_1.add(textFieldNomeTipoProdutoEspecifica);
		textFieldNomeTipoProdutoEspecifica.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("RESULTADO DA SUA CONSULTA");
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(266, 185, 398, 90);
		contentPane_1.add(scrollPane);
		
		JTextArea textAreaResultadoConsultaEspecifica = new JTextArea();
		textAreaResultadoConsultaEspecifica.setEditable(false);
		scrollPane.setViewportView(textAreaResultadoConsultaEspecifica);
		textAreaResultadoConsultaEspecifica.setToolTipText("O RESULTADO DA CONSULTA SAIRA AQUI");
		
		JLabel lblResultadoConsultaEspecifica = new JLabel("RESULTADO DA CONSULTA :");
		lblResultadoConsultaEspecifica.setToolTipText("RESULTADO DA CONSULTA ");
		lblResultadoConsultaEspecifica.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblResultadoConsultaEspecifica.setBounds(10, 182, 286, 25);
		contentPane_1.add(lblResultadoConsultaEspecifica);
		
		
		
		JButton btnVoltarTipoProdutoEspecifica= new JButton("Voltar");
		btnVoltarTipoProdutoEspecifica.setToolTipText("VOLTAR A TELA ANTERIOR");
		btnVoltarTipoProdutoEspecifica.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltarTipoProdutoEspecifica.setBounds(430, 286, 110, 30);
		contentPane_1.add(btnVoltarTipoProdutoEspecifica);

		JButton btnConsultarTipoProdutoEspecifica = new JButton("consultar");
		btnConsultarTipoProdutoEspecifica.setToolTipText("CONSULTAR");
		btnConsultarTipoProdutoEspecifica.setBackground(new Color(0, 204, 255));
		btnConsultarTipoProdutoEspecifica.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConsultarTipoProdutoEspecifica.setBounds(550, 286, 114, 30);
		contentPane_1.add(btnConsultarTipoProdutoEspecifica);
		
        ListaEncadeada<TipoProduto> listaTipoProduto = new ListaEncadeada<>();
		TipoProdutoController methodsTipoProduto = new TipoProdutoController(textAreaResultadoConsultaEspecifica, textFieldNomeTipoProdutoEspecifica
													, listaTipoProduto);

		btnConsultarTipoProdutoEspecifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNomeTipoProdutoEspecifica.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo não preenchido", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					methodsTipoProduto.actionPerformed(e);
				}
		}
			
		});

		btnVoltarTipoProdutoEspecifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				TipoProdutoEspecificoouTodos telaInicial = new TipoProdutoEspecificoouTodos();
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