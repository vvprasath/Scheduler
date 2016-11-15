

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;


/**
 * Servlet implementation class DisplayServ
 */
@WebServlet("/DisplayServ")
public class DisplayServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Task> datalist = new ArrayList<Task>();
		
		try{
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb",
					"postgres", "vvp");
			String sql = "select * from scheduler order by taskid";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Task task = new Task(rs.getInt("taskid"),rs.getString("task"),rs.getInt("duration"));
				datalist.add(task);
			}
			
			request.setAttribute("datalist", datalist);
			request.getRequestDispatcher("Display.jsp").forward(request, response);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String task = request.getParameter("task");
		int duration = Integer.parseInt(request.getParameter("duration"));

		
		try{
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb",
					"postgres", "vvp");
			String sql = "insert into scheduler (task, duration) values(?,?)";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, task);
			pstmt.setInt(2, duration);
			
			pstmt.executeUpdate();
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();			
		}
		
		doGet(request,response);
		
	}

}
