package controle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import conexao.Conexao;
import controle.FrmCadastro;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.sql.*;


public class LoginAcesso extends JFrame{
    Conexao con_cliente;
    
    JPasswordField caixasenha;
    JLabel titulo, rusuario, rsenha, imagem;
    JTextField caixausuario;
    JButton blogar;
     ImageIcon imagens[];
    int contador = 3;
    
    public LoginAcesso(){
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Exercício Prático");
        Container tela = getContentPane();
        tela.setBackground(new Color (200, 165, 204));
        setLayout(null);
        
        ImageIcon image = new ImageIcon("src/image2.png");
        imagem = new JLabel(image);
        imagem.setBounds(1,180,200,200);
    
        titulo = new JLabel("Acesso ao Sistema");
        rusuario = new JLabel("Usuário: ");
        rsenha = new JLabel("Senha: ");
        caixausuario = new JTextField(100);
        caixasenha = new JPasswordField(10);
        blogar = new JButton("Logar");
        
        titulo.setBounds(145,20,250,20);
        rusuario.setBounds(40,100,70,20);
        rsenha.setBounds(40,150,90,20);
        caixausuario.setBounds(120,100,250,20);
        caixasenha.setBounds(120,150,60,20);
        blogar.setBounds(180,280,80,20);
        
        titulo.setBackground(new Color(245, 142, 222));
        caixausuario.setBackground(new Color(228, 194, 237));
        caixasenha.setBackground(new Color(228, 194, 237));
        blogar.setBackground(new Color(250, 182, 224));
        
        titulo.setFont(new Font ("Lucida Sans", Font.BOLD, 16));
        rusuario.setFont(new Font ("Lucida Sans", Font.BOLD, 14));
        rsenha.setFont(new Font ("Lucida Sans", Font.BOLD, 14));
        
        //atalho para limpar dados:
        tela.add(titulo);
        tela.add(rusuario);
        tela.add(rsenha);
        tela.add(caixausuario);
        tela.add(caixasenha);
        tela.add(blogar);
        tela.add(imagem);
       
        //variaveis que representam as entradas de dados
        
        blogar.addActionListener
        (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            try{
                String pesquisa = "select * from tblusuario where usuario like '" + caixausuario.getText() + "' && senha = " + caixasenha.getText() + "";
                con_cliente.executaSQL(pesquisa);
                
                if(con_cliente.resultset.first())
                {
                    FrmCadastro mostra = new FrmCadastro();
                    mostra.setVisible(true);
                    dispose();
                }
                
                    else if(contador == 0 | contador == 1 | contador == 2 | contador == 3 )
                    { 
                        if(con_cliente.resultset.first()== false){
                            JOptionPane.showMessageDialog(null, "\nSenha INCORRETA! Restam  "+contador+" tentativas. ","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                            contador = contador -1;
                            caixausuario.setText("");
                            caixasenha.setText("");
                        }
                      
                        if(contador == -1){
                            con_cliente.desconecta();
                            System.exit(0);   
                        }
              
                    }else {
                        JOptionPane.showMessageDialog(null, "\n Usuário não cadastrado!!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                        con_cliente.desconecta();
                        System.exit(0);
            }
            
            }catch(SQLException errossql){
                JOptionPane.showMessageDialog(null,"\n Os dados digitados não foram localizados!! :\n"+errossql,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
            }});
        
        setSize(450,450);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
}
    public static void main(String args[]){
    LoginAcesso app = new LoginAcesso();
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}


