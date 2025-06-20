
/**
 *
 * @author Adm
 */
import java.util.List;
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
		String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, 'A Venda')";

		conectaDAO conn = null;
		PreparedStatement ps = null;

		try {
			conn = new conectaDAO();
			conn.connectDB();
			ps = conn.getConexao().prepareStatement(sql);

			ps.setString(1, produto.getNome());
			ps.setString(2, Integer.toString(produto.getValor()));
			
			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {
				JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso!");

			}

		} catch (SQLException es) {
			JOptionPane.showMessageDialog(null, "Erro Ao Cadastrar" + es);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.desconectar();
				}
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar conexão: " + ex); 
			}
		}

	}

	public static List<ProdutosDTO> listarProdutos() throws SQLException {

		ArrayList<ProdutosDTO> prod = new ArrayList();

		String sql = "SELECT id, nome, valor, status FROM produtos";
		conectaDAO conn = null;
		PreparedStatement ps = null;

		try {
			conn = new conectaDAO();
			conn.connectDB();
			ps = conn.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProdutosDTO produto = new ProdutosDTO();

				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setValor((rs.getInt("valor")));
				produto.setStatus(rs.getString("status"));

				prod.add(produto);

			}
		} catch (SQLException er) {
			JOptionPane.showMessageDialog(null, "Erro na Listagem, SQL: " + er);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.desconectar();
				}
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar conexão: " + ex); // LOG
			}
		}

		return prod;
	}

	public static boolean venderProduto(ProdutosDTO produto) {

		String sql = "UPDATE produtos SET status = 'Vendido'  WHERE Id = ?";

		conectaDAO conn = null;
		PreparedStatement ps = null;

		try {
			conn = new conectaDAO();
			conn.connectDB();
			ps = conn.getConexao().prepareStatement(sql);

			ps.setInt(1, produto.getId());

			int linhasAfetadas = ps.executeUpdate();
			return linhasAfetadas > 0;

		} catch (SQLException er) {
			JOptionPane.showMessageDialog(null, "Erro ao Vender Produto: " + er);
			return false;
		}
	}

	public static List<ProdutosDTO> listarProdutosVendidos() throws SQLException {

		ArrayList<ProdutosDTO> prod = new ArrayList();

		String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = 'Vendido'";
		conectaDAO conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = new conectaDAO();
			conn.connectDB();
			ps = conn.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				ProdutosDTO produto = new ProdutosDTO();

				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setValor(rs.getInt("valor"));
				produto.setStatus(rs.getString("status"));

				prod.add(produto);
			}
		} catch (SQLException er) {
			JOptionPane.showMessageDialog(null, "Erro na Listagem, SQL: " + er);
			er.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.desconectar();
				}
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar conexão: " + ex);
			}
		}

		return prod;

	}

}
