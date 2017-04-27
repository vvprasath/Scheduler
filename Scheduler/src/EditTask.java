

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

/**
 * Servlet implementation class EditTask
 */
@WebServlet("/EditTask")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer taskid = Integer.parseInt(request.getParameter("taskid"));
		List<Task> datalist = new ArrayList<Task>();		
		DBparms dbparms = new DBparms();
		
		try{
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(dbparms.geturl(),
					dbparms.getuser(), dbparms.getpasswd());
			String sql = "select * from scheduler where taskid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, taskid);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Task task = new Task(rs.getInt("taskid"), rs.getString("task"), rs.getInt("duration"));
				datalist.add(task);
			}
			
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		request.setAttribute("datalist", datalist);
		request.getRequestDispatcher("EditTask.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("submit");
		DBparms dbparms = new DBparms();
		
		try{
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(dbparms.geturl(),
					dbparms.getuser(), dbparms.getpasswd());
									
			if(action.equals("Save")){
				String sql = "update scheduler set task = ?, duration = ? where taskid = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("task"));
				pstmt.setInt(2, Integer.parseInt(request.getParameter("duration")));
				pstmt.setInt(3, Integer.parseInt(request.getParameter("taskid")));
				pstmt.executeUpdate();
			
			}
			else if(action.equals("Delete")){
				String sql = "Delete from scheduler where taskid = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(request.getParameter("taskid")));
				pstmt.executeUpdate();
			}
			
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();			
		}
		catch(SQLException e){
			e.printStackTrace();			
		}
		
		response.sendRedirect("DisplayServ");
	}

}

