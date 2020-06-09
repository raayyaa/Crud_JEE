package pizza_package;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class ControllerPizza
 */
@WebServlet("/ControllerPizza")
public class ControllerPizza extends HttpServlet {
	private final String UPLOAD_DIRECTORY = System.getProperty("user.dir")+"/photos";
	public static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
	public static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
	public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	private static final long serialVersionUID = 1L;
	java.util.List<Pizza> listpizza;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerPizza() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		// response.getWriter().append(" je suis dans le controler pizza ")
		// .append(" et l'action est : " + action );

		if (action.equalsIgnoreCase("createpizza"))
			try {
				createPizza(request, response);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		if (action.equalsIgnoreCase("modifpizza"))
			try {
				modifOnePizza(request, response);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		if (action.equalsIgnoreCase("editpizza"))
			try {
				editOnePizza(request, response);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		if (action.equalsIgnoreCase("deletepizza"))
			try {
				deletePizza(request, response);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		if (action.equalsIgnoreCase("listpizza"))
			try {
				affichelistepizza(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	private void createPizza(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {

		request.getRequestDispatcher("PizzaCreateView.jsp").forward(request, response);

	}

	private void modifOnePizza(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		int idpizza = Integer.parseInt(request.getParameter("idpizza"));
		Pizza pizza = PizzaDAO.getPizzaById(idpizza);
		request.setAttribute("pizza", pizza);

		request.getRequestDispatcher("PizzaModifView.jsp").forward(request, response);

	}

	private void deletePizza(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {

		int idpizza = Integer.parseInt(request.getParameter("idpizza"));
		Pizza delPizza = PizzaDAO.deletePizzaById(idpizza);

		request.setAttribute("delPizza", delPizza);
		request.getRequestDispatcher("PizzaDeleteView.jsp").forward(request, response);

	}

	private void affichelistepizza(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {

		listpizza = PizzaDAO.listAllPizzas();
		request.setAttribute("listpizza", listpizza);
		request.getRequestDispatcher("PizzaListView.jsp").forward(request, response);

	}

	private void editOnePizza(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, SQLException, ServletException {

		int idpizza = Integer.parseInt(request.getParameter("idpizza"));
		Pizza maPizza = PizzaDAO.getPizzaById(idpizza);
		// response.getWriter().append("i must edit pizza num : "+ idpizza );
		request.setAttribute("mapizza", maPizza);
		request.getRequestDispatcher("PizzaOneView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (ServletFileUpload.isMultipartContent(request)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			try {
				List<FileItem> formItems = upload.parseRequest(new ServletRequestContext(request));
				Map<String,String> data = new HashMap<>();
				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						
						if (!item.isFormField()) {
							String fileName = new File(item.getName()).getName();
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							System.out.println(filePath);
							item.write(storeFile);
							request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
							data.put(item.getFieldName(),item.getName());
						}else {
							data.put(item.getFieldName(), item.getString());
						}
					}
				}
				
				Pizza pizza = new Pizza(Integer.valueOf(data.get("id")), data.get("DesignPizz"),
						Double.valueOf(data.get("price")),data.get("Image"));
				PizzaDAO.saveOrUpdate(pizza);
				
			} catch (Exception ex) {
				ex.printStackTrace();
				request.setAttribute("message", "There was an error: " + ex.getMessage());
			}
			try {
				affichelistepizza(request, response);
			} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
