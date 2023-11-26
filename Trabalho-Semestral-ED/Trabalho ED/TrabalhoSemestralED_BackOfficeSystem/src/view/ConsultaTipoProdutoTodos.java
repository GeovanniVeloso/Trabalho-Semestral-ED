package view;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ConsultaTipoProdutoTodos extends BaseFrame {

	private JPanel contentPane;
	private JTextField textFieldTipoProdutoTodos;
	
	public ConsultaTipoProdutoTodos() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		JLabel lblTipoProdutoTodos = new JLabel("CONSULTA DE TODOS OS PRODUTOS DE UM TIPO ");
		lblTipoProdutoTodos.setToolTipText("CONSULTA DE TODOS OS PRODUTOS DE UM TIPO ");
		lblTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTipoProdutoTodos.setBounds(76, 27, 515, 25);
		contentPane.add(lblTipoProdutoTodos);
		
		JLabel lblResultadoTipoProdutoTodos = new JLabel("OS PRODUTOS SÃO :");
		lblResultadoTipoProdutoTodos.setToolTipText("OS PRODUTOS SÃO ");
		lblResultadoTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblResultadoTipoProdutoTodos.setBounds(34, 63, 200, 25);
		contentPane.add(lblResultadoTipoProdutoTodos);
		
		JButton btnVoltarTipoProdutoTodos= new JButton("Voltar");
		btnVoltarTipoProdutoTodos.setToolTipText("VOLTAR");
		btnVoltarTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltarTipoProdutoTodos.setBounds(425, 285, 110, 30);
		
		contentPane.add(btnVoltarTipoProdutoTodos);

		JButton btnConsultarTipoProdutoTodos = new JButton("Consultar");
		btnConsultarTipoProdutoTodos.setToolTipText("CONSULTAR");
		btnConsultarTipoProdutoTodos.setBackground(new Color(0, 204, 255));
		btnConsultarTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConsultarTipoProdutoTodos.setBounds(550, 285, 114, 30);
		contentPane.add(btnConsultarTipoProdutoTodos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(34, 92, 610, 175);
		contentPane.add(scrollPane);
		
		JTextArea textAreaResultadoConsultaTipoProdutoTodos = new JTextArea();
		textAreaResultadoConsultaTipoProdutoTodos.setEditable(false);
		textAreaResultadoConsultaTipoProdutoTodos.setFont(new Font("Arial", Font.BOLD, 14));
		scrollPane.setViewportView(textAreaResultadoConsultaTipoProdutoTodos);
		textAreaResultadoConsultaTipoProdutoTodos.setToolTipText("RESULTADO DA SUA CONSULTA DE TODOS OS PRODUTIOS DESTE TIPO");
		
		ListaEncadeada<TipoProduto> listaTipoProduto = new ListaEncadeada<>();
			
		TipoProdutoController methodsTipoProduto = new TipoProdutoController(textAreaResultadoConsultaTipoProdutoTodos, listaTipoProduto);

        
	
		btnConsultarTipoProdutoTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				methodsTipoProduto.actionPerformed(e);
			}
		});

		btnVoltarTipoProdutoTodos.addActionListener(new ActionListener() {
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