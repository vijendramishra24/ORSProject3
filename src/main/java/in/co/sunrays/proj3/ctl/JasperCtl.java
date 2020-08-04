package in.co.sunrays.proj3.ctl;

import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.hibernate.internal.SessionImpl;

import in.co.sunrays.proj3.model.MarksheetModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.util.HibDataSource;
import in.co.sunrays.proj3.util.JDBCDataSource;

/**
 * Jasper functionality Controller .Performs operation for Jasper Report
 * 
 * @author Facade
 * @version 1.0
 * @Copyrigh (c) SunilOS
 */

@WebServlet(name = "JasperCtl", urlPatterns = {"/ctl/JasperCtl"})
public class JasperCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	/**
	 * Jasper functionality Controller display contant
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("helloooo");
		
		try {

			Connection conn = null;
			String path=getServletContext().getRealPath("/WEB-INF");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(path+"/new/MerritList.jrxml");
			Map<String, Object> map = new HashMap<String, Object>();
			MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
			
			ResourceBundle rb = ResourceBundle.getBundle("bundle.system");
			String database = rb.getString("DATABASE");
			if("Hibernate".equals(database)){
				conn = (((SessionImpl) HibDataSource.getSession()).connection());
			}
			if("JDBC".equals(database)){
				conn = JDBCDataSource.getConnection();
			}
				
			/*List list = model.getMeritList(0, 10);
			Iterator<MarksheetDTO> it = list.iterator();
			while (it.hasNext()) {
				MarksheetDTO dto = it.next();
			JRDataSource dataSource = new JRBeanCollectionDataSource(list, false);*/
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				
				response.setContentType("application/pdf");
				response.getOutputStream().write(pdf);
				response.getOutputStream().flush();
				
			
		} catch (Exception e) {
			System.out
					.println("In jasper ctl---------------------------------------------------------" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
System.out.println("helloooo");
		
		try {

			Connection conn = null;
			JasperReport jasperReport = JasperCompileManager
					.compileReport("/ORSProject3/src/main/webapp/jasper/MerritList.jrxml");
			Map<String, Object> map = new HashMap<String, Object>();
			MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
			
			ResourceBundle rb = ResourceBundle.getBundle("bundle.system");
			String database = rb.getString("DATABASE");
			if("Hibernate".equals(database)){
				conn = (((SessionImpl) HibDataSource.getSession()).connection());
			}
			if("JDBC".equals(database)){
				conn = JDBCDataSource.getConnection();
			}
				
			/*List list = model.getMeritList(0, 10);
	        Iterator<MarksheetDTO> it = list.iterator();
			while (it.hasNext()) {
				MarksheetDTO dto = it.next();
				JRDataSource dataSource = new JRBeanCollectionDataSource(list, false);*/
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				
				response.setContentType("application/pdf");
				response.getOutputStream().write(pdf);
				response.getOutputStream().flush();
				
			
		} catch (Exception e) {
			System.out
					.println("In jasper ctl---------------------------------------------------------" + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	protected String getView() {
		return null;
	}

}

