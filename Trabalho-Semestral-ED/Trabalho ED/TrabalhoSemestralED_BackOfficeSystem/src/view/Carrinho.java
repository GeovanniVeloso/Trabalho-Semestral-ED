package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import controller.ControleProduto;
import model.ListaEncadeada;
import model_main.Produto;

public class Carrinho extends BaseFrame {

    private JPanel contentPane;
    private TelaInicial telaInicial;

    /**
     * Create the frame.
     * @throws Exception 
     */  
    public Carrinho(TelaInicial telaInicial, ListaEncadeada<Produto> produtosCarrinho) throws Exception {
    	
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 690, 360);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCarrinho = new JLabel("CARRINHO");
        lblCarrinho.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblCarrinho.setToolTipText("CARRINHO");
        lblCarrinho.setBounds(257, 11, 198, 37);
        contentPane.add(lblCarrinho);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 68, 654, 198);
        contentPane.add(scrollPane);
        
        //*Preenche Carrinho*//
        JTextArea textAreaCarrinho = new JTextArea();
        textAreaCarrinho.setToolTipText("OS SEUS ITENS QUE FORAM ADICIONADOS NO CARRINHO SÃO :");
        scrollPane.setViewportView(textAreaCarrinho);
        textAreaCarrinho.setEditable(false);
        
        ControleProduto methodsProduto = new ControleProduto(textAreaCarrinho);
        methodsProduto.exibirCarrinho();;
        
        JButton btnCarrinhoFinalizar = new JButton("Finalizar");
        btnCarrinhoFinalizar.setBackground(new Color(255, 160, 122));
        btnCarrinhoFinalizar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCarrinhoFinalizar.setToolTipText("FINALIZAR A COMPRA");
        btnCarrinhoFinalizar.setBounds(558, 287, 106, 23);
        contentPane.add(btnCarrinhoFinalizar);

        JButton btnCarrinhoVoltar = new JButton("Voltar");
        btnCarrinhoVoltar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCarrinhoVoltar.setToolTipText("VOLTAR");
        btnCarrinhoVoltar.setBounds(327, 287, 89, 23);
        contentPane.add(btnCarrinhoVoltar);

        JButton btnCarrinhoRemover = new JButton("Remover");
        btnCarrinhoRemover.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCarrinhoRemover.setForeground(new Color(255, 255, 255));
        btnCarrinhoRemover.setBackground(new Color(245, 7, 7));
        btnCarrinhoRemover.setToolTipText("REMOVER");
        btnCarrinhoRemover.setBounds(426, 287, 122, 23);
        contentPane.add(btnCarrinhoRemover);

        btnCarrinhoFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Compras Realizadas com Sucesso", "Sucesso!", JOptionPane.PLAIN_MESSAGE);

                // Fecha o frame atual
                dispose();
            }
        });
        
        
        btnCarrinhoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RemoverCarrinho remover = new RemoverCarrinho();
                remover.setVisible(true);

                // Fecha o frame atual
                dispose();
            }
        });
        
        btnCarrinhoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Torna a tela inicial visível
            	TelaInicial telaInicial = new TelaInicial();
                telaInicial.setVisible(true);
                //Destrói carrinho
    			File dir = new File("C:\\PastaTrabalhoED");
    			File arquivo = new File(dir , "CarrinhoDeCompras.csv");
    			arquivo.delete();
                // Fecha o frame atual
                dispose();
            }
        });
    }

	private String pegaListaDeCompras(ListaEncadeada<Produto> ls) throws Exception {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < ls.size(); i++) {
			String S = ls.getValue(i).toString();
			buffer.append(S+ "\n\r");
		}
		return buffer.toString();
	}
	
	@SuppressWarnings("unused")
	private ListaEncadeada<Produto>  tsLista(ListaEncadeada<Produto> ls){
		
		for (int i = 0; i < 10; i++) {
			Produto p = new Produto(i, "Name", 0.0, "Desconto", 100, 7);
			try {
				ls.addLast(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ls;
		
	}
}
