import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Cliente {
    String codigo;
    String nome;
    LocalDate dataNascimento;
    String telefone;

    Cliente(String codigo, String nome, LocalDate dataNascimento, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\n Nome: " + nome + "\n Data de Nascimento: " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n Telefone: " + telefone + "\n";
    }
}

public class CadastroCliente {
    List<Cliente> clientes;

    CadastroCliente() {
        clientes = new LinkedList<>();
    }

    boolean validarDados(String codigo, String nome, String dataNascimento, String telefone) {

        if (codigo.isEmpty()) {
            String mensagem = "● Codigo invalido. Tente novamente.";
            JOptionPane.showMessageDialog(null, mensagem, "● ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if (nome.isEmpty()) {
            String mensagem = "● Nome invalido. Tente novamente.";
            JOptionPane.showMessageDialog(null, mensagem, "● ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(dataNascimento, formatter);
        } catch (DateTimeParseException e) {
            String mensagem = "● Data de nascimento invalida. Use a forma DD/MM/AAAA.";
            JOptionPane.showMessageDialog(null, mensagem, "● ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if (telefone.isEmpty()) {
            String mensagem = "● Numero de telefone invalido. Tente novamente.";
            JOptionPane.showMessageDialog(null, mensagem, "● ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    void gravarCadastro(String codigo, String nome, String dataNascimento, String telefone) {
        if (validarDados(codigo, nome, dataNascimento, telefone)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            clientes.add(new Cliente(codigo, nome, LocalDate.parse(dataNascimento, formatter), telefone));
            String mensagem = "● O cliente foi cadastrado!";
            JOptionPane.showMessageDialog(null, mensagem, "● Sucesso", JOptionPane.INFORMATION_MESSAGE);
            exibirCadastros();
        }
    }

    void excluirCadastro(String codigo) {
        Cliente clienteParaExcluir = null;
        for (Cliente cliente : clientes) {
            if (cliente.codigo.equals(codigo)) {
                clienteParaExcluir = cliente;
                break;
            }
        }
        if (clienteParaExcluir != null) {
            clientes.remove(clienteParaExcluir);
            String mensagem = "● O cadastro do cliente foi excluído!\n" + clienteParaExcluir;
            JOptionPane.showMessageDialog(null, mensagem, "● Sucesso", JOptionPane.INFORMATION_MESSAGE);
            exibirCadastros();
        } else {
            String mensagem = "● Não existe cliente cadastrado com este codigo.";
            JOptionPane.showMessageDialog(null, mensagem, "● ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    void alterarCadastro(String codigo) {
        Cliente clienteParaAlterar = null;
        for (Cliente cliente : clientes) {
            if (cliente.codigo.equals(codigo)) {
                clienteParaAlterar = cliente;
                break;
            }
        }
        if (clienteParaAlterar != null) {
            String novoNome = JOptionPane.showInputDialog("● Informe o novo nome:", clienteParaAlterar.nome);
            String novaDataNascimento = JOptionPane.showInputDialog("● Informe a nova data de nascimento (DD/MM/AAAA):", clienteParaAlterar.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            String novoTelefone = JOptionPane.showInputDialog("● Informe o novo numero de telefone:", clienteParaAlterar.telefone);
            if (validarDados(codigo, novoNome, novaDataNascimento, novoTelefone)) {
                clienteParaAlterar.nome = novoNome;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                clienteParaAlterar.dataNascimento = LocalDate.parse(novaDataNascimento, formatter);
                clienteParaAlterar.telefone = novoTelefone;
                String mensagem = "● O cadastro foi alterado!";
                JOptionPane.showMessageDialog(null, mensagem, "● Sucesso", JOptionPane.INFORMATION_MESSAGE);
                exibirCadastros();
            }
        } else {
            String mensagem = "● Não existe cliente cadastrado com este codigo.";
            JOptionPane.showMessageDialog(null, mensagem, "● ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    void recuperarCadastro(String codigo) {
        Cliente clienteParaRecuperar = null;
        for (Cliente cliente : clientes) {
            if (cliente.codigo.equals(codigo)) {
                clienteParaRecuperar = cliente;
                break;
            }
        }
        if (clienteParaRecuperar != null) {
            JOptionPane.showMessageDialog(null, "● Dados do cliente:\n " + clienteParaRecuperar);
        } else {
            String mensagem = "● Não existe cliente cadastrado com este codigo.";
            JOptionPane.showMessageDialog(null, mensagem, "● ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    void exibirCadastros() {
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : clientes) {
            sb.append(cliente).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void main(String[] args) {
        CadastroCliente cadastro = new CadastroCliente();

        while (true) {
            String opcao = JOptionPane.showInputDialog("● Escolha uma opção ●\n1. Criar cadastro\n2. Excluir cadastro\n3. Alterar cadastro\n4. Localiazar/Recuperar cadastro\n5. Sair");
            switch (opcao) {
                case "1":
                    String codigo = JOptionPane.showInputDialog("● Crie um código:");
                    String nome = JOptionPane.showInputDialog("● Digite o nome:");
                    String dataNascimento = JOptionPane.showInputDialog("● Digite a data de nascimento (DD/MM/AAAA):");
                    String telefone = JOptionPane.showInputDialog("● Digite o telefone:");
                    cadastro.gravarCadastro(codigo, nome, dataNascimento, telefone);
                    break;
                case "2":
                    codigo = JOptionPane.showInputDialog("● Informe o código do cadastro a ser excluído:");
                    cadastro.excluirCadastro(codigo);
                    break;
                case "3":
                    codigo = JOptionPane.showInputDialog("● Informe o código do cadastro a ser alterado:");
                    cadastro.alterarCadastro(codigo);
                    break;
                case "4":
                    codigo = JOptionPane.showInputDialog("● Informe o código do cadastro a ser recuperado:");
                    cadastro.recuperarCadastro(codigo);
                    break;
                case "5":
                    System.exit(0);
            }
        }
    }
}



