
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adm
 */
public class conectaDAO {

	private Connection conexao;

	public void connectDB() throws SQLException {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/uc11", "root", "Pringles20%");
			System.out.println("Sucesso de Conexão!");

		} catch (ClassNotFoundException cn) {
			System.out.println("Falha ao conectar com o banco " + cn);
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
		}

	}

	public Connection getConexao() {
		return conexao;
	}

	public void desconectar() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
				System.out.println("Desconectado!");
			}
		} catch (SQLException sql) {
			System.out.println("Erro do SQL: " + sql);
		}
	}

}
