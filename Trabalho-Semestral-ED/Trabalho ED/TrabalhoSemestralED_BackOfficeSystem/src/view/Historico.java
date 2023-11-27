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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.ControleCompra;
import controller.TipoProdutoController;
import model.ListaEncadeada;
import model_main.TipoProduto;


public class Historico extends BaseFrame {

	private JPanel contentPane_1;
	private JTextField textFieldIDdeCompra;
	private JTextArea textAreaResultadoConsultaHistorico;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Historico() {
		getContentPane().setLayout(null);

		contentPane_1 = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblConsultaEspecifica = new JLabel("HISTORICO DE COMPRAS");
		lblConsultaEspecifica.setToolTipText("CONSULTA DE TIPO DE PRODUTO ESPECIFICA");
		lblConsultaEspecifica.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblConsultaEspecifica.setBounds(203, 26, 355, 25);
		contentPane_1.add(lblConsultaEspecifica);
		
		JLabel lblIdCompraHistorico = new JLabel("ID DA COMPRA : ");
		lblIdCompraHistorico.setToolTipText("ID DA COMPRA");
		lblIdCompraHistorico.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIdCompraHistorico.setBounds(10, 95, 164, 33);
		contentPane_1.add(lblIdCompraHistorico);
		
		
		// 	AlphaNumericTextField textFieldLogradouroCpf = new AlphaNumericTextField();
		 textFieldIDdeCompra = createNumericTextField();
		textFieldIDdeCompra.setToolTipText("DIGITE O ID DA COMPRA");
		textFieldIDdeCompra.setBounds(167, 102, 217, 25);
		contentPane_1.add(textFieldIDdeCompra);
		textFieldIDdeCompra.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("RESULTADO DA SUA CONSULTA");
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(266, 185, 398, 90);
		contentPane_1.add(scrollPane);
		
		textAreaResultadoConsultaHistorico = new JTextArea();
		textAreaResultadoConsultaHistorico.setEditable(false);
		scrollPane.setViewportView(textAreaResultadoConsultaHistorico);
		textAreaResultadoConsultaHistorico.setToolTipText("O RESULTADO DA CONSULTA SAIRA AQUI");
		
		JLabel lblResultadoConsultaHistorico = new JLabel("RESULTADO DA CONSULTA :");
		lblResultadoConsultaHistorico.setToolTipText("RESULTADO DA CONSULTA ");
		lblResultadoConsultaHistorico.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblResultadoConsultaHistorico.setBounds(10, 182, 286, 25);
		contentPane_1.add(lblResultadoConsultaHistorico);
		
		
		
		JButton btnVoltarHistorico= new JButton("Voltar");
		btnVoltarHistorico.setToolTipText("VOLTAR A TELA ANTERIOR");
		btnVoltarHistorico.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltarHistorico.setBounds(430, 286, 110, 30);
		contentPane_1.add(btnVoltarHistorico);

		JButton btnConsultarHistorico = new JButton("consultar");
		btnConsultarHistorico.setToolTipText("CONSULTAR");
		btnConsultarHistorico.setBackground(new Color(0, 204, 255));
		btnConsultarHistorico.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConsultarHistorico.setBounds(550, 286, 114, 30);
		contentPane_1.add(btnConsultarHistorico);

		btnConsultarHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDdeCompra.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo não preenchido", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					ControleCompra opCC = new ControleCompra(textFieldIDdeCompra, textAreaResultadoConsultaHistorico);
					
				}
		}
			
		});

		btnVoltarHistorico.addActionListener(new ActionListener() {
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
		JTextField textFieldIdCompra = new JTextField();

		textFieldIdCompra.setDocument(new NumericDocument());
		return textFieldIdCompra;
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