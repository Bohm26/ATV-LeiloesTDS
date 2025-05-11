
/**
 *
 * @author Adm
 */
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

	Connection conn;
	PreparedStatement prep;
	ResultSet resultset;
	ArrayList<ProdutosDTO> listagem = new ArrayList<>();

	public void cadastrarProduto(ProdutosDTO produto) {
		String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)";

		conectaDAO conn = null;
		PreparedStatement ps = null;

		try {
			conn = new conectaDAO();
			conn.connectDB();
			ps = conn.getConexao().prepareStatement(sql);

			ps.setString(1, produto.getNome());
			ps.setString(2, Integer.toString(produto.getValor()));
			ps.setString(3, produto.getStatus());

			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {
				JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso!");

				conn.desconectar();
			}
		} catch (SQLException es) {
			JOptionPane.showMessageDialog(null, "Erro Ao Cadastrar" + "es");
		}

	}

	public ArrayList<ProdutosDTO> listarProdutos() {

		return listagem;
	}

}
