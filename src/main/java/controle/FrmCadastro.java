package controle;

import java.awt.*;
import java.text.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import conexao.Conexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
        
import java.sql.*;
import static java.time.temporal.TemporalAdjusters.next;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class FrmCadastro extends JFrame{
    Conexao con_cliente;
    
    JLabel rCodigo, rNome, rEmail, rTel, rData, rPesquisar,rTitulo,imagem;
    JTextField tcodigo, tnome, temail,tPesquisar;
    JFormattedTextField tel, data;
    MaskFormatter mTel, mData;
    JButton Primeiro, Anterior, Proximo, Ultimo, Novo, Gravar, Alterar,Excluir,LimparPesq,LimparForms,Pesquisar,Sair;
    JTable tblClientes;
    JScrollPane scp_tabela;
    ImageIcon imagens[];
    
    public FrmCadastro(){
        
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Conexão Java com MySql");
        Container tela = getContentPane();
        tela.setBackground(new Color (200, 165, 204));
        setLayout(null);
        setResizable(false);
        
        ImageIcon image = new ImageIcon("src/image.png");
        imagem = new JLabel(image);
        imagem.setBounds(480,35,200,200);
        
        rTitulo = new JLabel("Cadastro de Clientes");
        rCodigo = new JLabel ("Codigo: ");
        rNome =  new JLabel ("Nome: ");
        rData = new JLabel ("Data: ");
        rTel = new JLabel ("Telefone: ");
        rEmail = new JLabel ("Email: ");
        rPesquisar = new JLabel ("Pesquisa por nome do Cliente:");
        
        rTitulo.setBounds(330,10,200,20);
        rCodigo.setBounds(50,20,80,20);
        rNome.setBounds(50,60,80,20);
        rData.setBounds(50,100,80,20);
        rTel.setBounds(50,140,80,20);
        rEmail.setBounds(50,180,80,20);
        rPesquisar.setBounds(70,295,180,20);
        
        rTitulo.setFont(new Font("Lucida Sans",Font.BOLD, 14));
                
        //setar antes do try
        tel = new JFormattedTextField();
        data = new JFormattedTextField();
        
        tcodigo = new JTextField();
        tnome = new JTextField();
        temail = new JTextField();
        tPesquisar = new JTextField();
        
        tcodigo.setBounds(115,20,70,20);
        tnome.setBounds(130,60,200,20);
        temail.setBounds(130,180,180,20);
        tPesquisar.setBounds(250,295,250,20);
        
        try{
            
            mTel = new MaskFormatter("(##)####-####");
            mData = new MaskFormatter("##/##/##");
            
            mTel.setPlaceholderCharacter(' ');
            mData.setPlaceholderCharacter(' ');
        }
        catch(ParseException excp){}
        
        tel = new JFormattedTextField(mTel);
        data = new JFormattedTextField(mData);
        
        data.setBounds(130,100,80,20);
        tel.setBounds(130,140,100,20);
        
        Primeiro = new JButton("Primeiro");
        Anterior = new JButton("Anterior");
        Proximo = new JButton("Proximo");
        Ultimo = new JButton("Ultimo");
        
        Novo = new JButton("Registrar");
        Gravar = new JButton("Salvar");
        Alterar = new JButton("Alterar");
        Excluir = new JButton("Excluir");
        
        Pesquisar = new JButton("Pesquisar");
        Sair = new JButton("Sair");
        LimparPesq = new JButton("Limpar Pesquisa");
        LimparForms = new JButton("Limpar");
        
        Primeiro.setBounds(50,230,85,20);
        Anterior.setBounds(130,230,80,20);
        Proximo.setBounds(210,230,85,20);
        Ultimo.setBounds(290,230,80,20);
        
        Novo.setBounds(425,230,95,20);
        Gravar.setBounds(520,230,75,20);
        Alterar.setBounds(595,230,75,20);
        Excluir.setBounds(670,230,75,20);
        
        Pesquisar.setBounds(500,295,95,20);
        LimparPesq.setBounds(595,295,130,20);
        LimparForms.setBounds(330,180,80,20);
        Sair.setBounds(670,485,70,20);
        
        Primeiro.setBackground(new Color (250, 182, 224));
        Anterior.setBackground(new Color (250, 182, 224));
        Proximo.setBackground(new Color (250, 182, 224));
        Ultimo.setBackground(new Color (250, 182, 224));
        Novo.setBackground(new Color (250, 182, 224));
        Excluir.setBackground(new Color (250, 182, 224));
        Gravar.setBackground(new Color (250, 182, 224));
        Alterar.setBackground(new Color (250, 182, 224));
        Pesquisar.setBackground(new Color (225, 182, 250));
        LimparPesq.setBackground(new Color (225, 182, 250));
        LimparForms.setBackground(new Color (225, 182, 250));
        Sair.setBackground(new Color (225, 182, 250));
                
        Primeiro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    if(con_cliente.resultset.first()==true){
                        mostrar_Dados();
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o primeiro registro! Tente outro Botão.");
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
        }
        });
        
        Anterior.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    if(con_cliente.resultset.previous()==true){
                        mostrar_Dados();
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o registro anterior! Tente outro Botão.");
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
        }
        });
        
        Proximo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
                try {
                    if(con_cliente.resultset.next()==true){
                        mostrar_Dados();
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o próximo registro! Tente outro Botão.");
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
      
        }
        });
        
        Ultimo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    if(con_cliente.resultset.last()==true){
                        mostrar_Dados();
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o último registro! Tente outro Botão.");
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
        }
        });
        
        Pesquisar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String pesquisa = "select * from tbclientes where nome like '"+ tPesquisar.getText() +"%'";
                    con_cliente.executaSQL(pesquisa);
                    
                    if(con_cliente.resultset.first()){
                        preencherTabela();
                    }else{
                        JOptionPane.showMessageDialog(null,"\n Não existe dados com este parâmetro!!","Mensagem do Programa",  JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(SQLException errosql){
                     JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +errosql, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        //Limpa somente os registros digitados nas caixas de texto do formulário
        LimparForms.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               tcodigo.setText("");
               tnome.setText("");
               data.setText("");
               tel.setText("");
               temail.setText("");
               tcodigo.requestFocus();
            }
        });
        
        LimparPesq.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               tPesquisar.setText("");
               tPesquisar.requestFocus();
               
               //Foi necessário implementar essa linha de código para que ao limpar pesquisa a tabela volte a apresentar todos os registros
               try{
                    String pesquisa = "select * from tbclientes where nome like '"+ tPesquisar.getText() +"%'";
                    con_cliente.executaSQL(pesquisa);
                    
                    if(con_cliente.resultset.first()){
                        preencherTabela();
                    }else{
                        JOptionPane.showMessageDialog(null,"\n Não existe dados com este parâmetro!!","Mensagem do Programa",  JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(SQLException errosql){
                     JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +errosql, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        Novo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               tcodigo.setText("");
               tnome.setText("");
               data.setText("");
               tel.setText("");
               temail.setText("");
               tcodigo.requestFocus();
               JOptionPane.showMessageDialog(null,"Os registros foram limpos!\n Agora é possível incluir um novo registro.","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        Gravar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nome = tnome.getText();
                String nasc = data.getText();
                String fone = tel.getText();
                String email = temail.getText();
        
                try{
                    String insert_sql="insert into tbclientes (nome,telefone,email,dt_nasc) values ('"+nome+"','"+fone+"','"+email+"','"+nasc+"')";
                    con_cliente.statement.executeUpdate(insert_sql);
                    JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                    
                    con_cliente.executaSQL("Select * from tbclientes order by cod");
                    preencherTabela();
                }catch(SQLException errosql){
                    JOptionPane.showMessageDialog(null, "\n Erro de Gravação :\n"+errosql,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
       
        Alterar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nome = tnome.getText();
                String nasc = data.getText();
                String fone = tel.getText();
                String email = temail.getText();
                String sql;
                String msg="";
                
                try{
                    if(tcodigo.getText().equals("")){
                        sql="insert into tbclientes (nome,telefone,email,dt_nasc) values ('"+nome+"','"+fone+"','"+email+"','"+nasc+"')";
                        msg="Gravação de um novo registro";
                    }else{
                        sql="update tbclientes set nome='" + nome + "',telefone='" + fone + "', email='" + email + "', dt_nasc='" + nasc + "' where cod = " + tcodigo.getText();
                        msg="Alteração de registro";
                    }
                    
                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"ALteração realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                    
                    con_cliente.executaSQL("Select * from tbclientes order by cod");
                    preencherTabela();
                    
                }catch(SQLException errosql){
                    JOptionPane.showMessageDialog(null, "\n Erro de Alteração :\n"+errosql,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        Excluir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                String sql="";
                
                try{
                    int resposta = JOptionPane.showConfirmDialog(rootPane,"Deseja excluir o regsitro: ","Confirmar Exclusão",JOptionPane.YES_NO_OPTION,3);
                    
                    if(resposta==0){
                        sql="delete from tbclientes where cod ="+tcodigo.getText();
                        int excluir = con_cliente.statement.executeUpdate(sql);
                        
                        if(excluir==1){
                            JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                            con_cliente.executaSQL("Select * from tbclientes order by cod");
                            con_cliente.resultset.first();
                            preencherTabela();
                            posicionarRegistro();
                        }
                        
                        }else{
                            JOptionPane.showMessageDialog(null,"Operação cancelada pelo usuário!!","Mensagem do Progarama",JOptionPane.INFORMATION_MESSAGE);
                        }
                    
                }catch(SQLException excecao){
                    JOptionPane.showMessageDialog(null, "\n Erro na Exclusão :\n"+excecao,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        Sair.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        int status = JOptionPane.showConfirmDialog(null,"Deseja realmente fechar o programa?","Mensagem de saída",
        JOptionPane.YES_NO_OPTION);
        if (status == JOptionPane.YES_OPTION){
         System.exit(0);}
        }
        }
        );
        
        tela.add(Primeiro);
        tela.add(Anterior);
        tela.add(Proximo);
        tela.add(Ultimo);
        tela.add(Novo);
        tela.add(Gravar);
        tela.add(Alterar);
        tela.add(Excluir);
        tela.add(Pesquisar);
        tela.add(Sair);
        tela.add(LimparPesq);
        tela.add(LimparForms);
        tela.add(imagem);
//Este código a seguir deverá ser digitado antes da definição do tamanho e visibilidade do formulário, no final do“construtor”:Figuras
    
    //Configuração da JTable
    tblClientes = new javax.swing.JTable();
    scp_tabela = new javax.swing.JScrollPane();
    
    tblClientes.setBounds(50,335,700,130);
    scp_tabela.setBounds(50,335,700,130);
    
    tela.add(tblClientes);
    tela.add(scp_tabela);
        
    tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
    tblClientes.setFont(new java.awt.Font("Arial",1,12));
    
    tblClientes.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null}
        },
     
        new String [] {"Código", "Nome", "Data Nascimento", "Telefone", "Email"})
        {
        boolean[] canEdit = new boolean [] {
        false,false,false,false,false };
        
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];}
    });
        scp_tabela.setViewportView(tblClientes);
        
        tblClientes.setAutoCreateRowSorter(true);
        
        //fim da config da jlable
     
    tela.add(tcodigo);
    tela.add(tnome);
    tela.add(temail);
    tela.add(tPesquisar);
    tela.add(tel);
    tela.add(data);
    
    tela.add(rTitulo);
    tela.add(rCodigo);
    tela.add(rNome);
    tela.add(rEmail);
    tela.add(rTel);
    tela.add(rData);
    tela.add(rPesquisar);
    
    setSize(800,600);
    setVisible(true);
    setLocationRelativeTo(null);
        
    con_cliente.executaSQL("Select * from tbclientes order by cod");
    preencherTabela();
    posicionarRegistro();
}
    public void preencherTabela(){
                    tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
                    tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
                    tblClientes.getColumnModel().getColumn(2).setPreferredWidth(11);
                    tblClientes.getColumnModel().getColumn(3).setPreferredWidth(14);
                    tblClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
                    
                    DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
                    modelo.setNumRows(0);
                    
                    try {
                        con_cliente.resultset.beforeFirst();
                        while(con_cliente.resultset.next()){
                            modelo.addRow(new Object[]{
                                con_cliente.resultset.getString("cod"),con_cliente.resultset.getString("nome"),con_cliente.resultset.getString("dt_nasc"),con_cliente.resultset.getString("telefone"),con_cliente.resultset.getString("email")    
                            });
                        }
                    }catch(SQLException erro){
                        JOptionPane.showMessageDialog(null, "\n Erro ao listar dados na tabela!! :\n " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                    }}
                    
                    public void posicionarRegistro(){
                        try{
                            con_cliente.resultset.first(); //posiciona no 1º registro da tabela
                            mostrar_Dados(); // chama o método que irá buscar o dado da tabela
                        }catch (SQLException erro){
                            JOptionPane.showMessageDialog(null, "Não foi possivel posicionar o 1º registro:" +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                        }}
                        
                        public void mostrar_Dados(){
                            try{
                                tcodigo.setText(con_cliente.resultset.getString("cod")); // associar a caixa de texto ao campo cod
                                tnome.setText(con_cliente.resultset.getString("nome")); // associar a caixa de texto ao campo nome
                                data.setText(con_cliente.resultset.getString("dt_nasc"));
                                tel.setText(con_cliente.resultset.getString("telefone"));
                                temail.setText(con_cliente.resultset.getString("email")); 
                            }catch (SQLException erro){
                                JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
    public static void main(String args[]){
    FrmCadastro app = new FrmCadastro();
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
                        
}
