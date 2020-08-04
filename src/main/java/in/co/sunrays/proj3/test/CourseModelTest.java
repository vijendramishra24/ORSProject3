package in.co.sunrays.proj3.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelHibImpl;
import in.co.sunrays.proj3.model.CourseModelInt;

public class CourseModelTest {
	
	/** 
     * Model object to test 
     */ 
	 public static CourseModelInt model = new CourseModelHibImpl(); 
 
 //   public static CourseModelInt model = new CourseModelJDBCImpl(); 
 
    /** 
     * Main method to call test methods. 
     *  
     * @param args 
     * @throws ParseException 
     */ 
    public static void main(String[] args){ 
        // testAdd(); 
        // testDelete(); 
       // testUpdate(); 
       //  testFindByPK(); 
      //   testFindByName(); 
        testSearch(); 
      //  testList(); 
 
    } 
 
    /** 
     * Tests add a User 
     *  
     * @throws ParseException 
     */ 
    public static void testAdd() { 
 
        try { 
        	CourseDTO dto = new CourseDTO(); 
            // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
 
            // dto.setId(1L); 
            dto.setName("ME"); 
            dto.setDescription("Mechanical Engg"); 
            dto.setCourseCode("ME100");
            
            long pk = model.add(dto); 
            System.out.println("Test add succ"); 
            CourseDTO addedDto = model.findByPK(pk); 
            if (addedDto == null) { 
                System.out.println("Test add fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests delete a User 
     */ 
    public static void testDelete() { 
 
        try { 
        	CourseDTO dto = new CourseDTO(); 
            long pk = 1L; 
            dto.setId(pk); 
            model.delete(dto); 
            System.out.println("Test Delete succ"); 
            CourseDTO deletedDto = model.findByPK(pk); 
            if (deletedDto != null) { 
                System.out.println("Test Delete fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests update a User 
     */ 
    public static void testUpdate() { 
 
        try { 
        	CourseDTO dto = model.findByPK(2L); 
            dto.setName("EC"); 
            dto.setDescription("Electronics and Communication"); 
            dto.setCourseCode("EC1010");
            model.update(dto); 
 
            CourseDTO updatedDTO = model.findByPK(2L); 
            System.out.println("Test Update "); 
            if (!"EC".equals(updatedDTO.getName())) { 
                System.out.println("Test Update fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests find a User by PK. 
     */ 
    public static void testFindByPK() { 
        try { 
        	CourseDTO dto = new CourseDTO(); 
            long pk = 2L; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getName()); 
            System.out.println(dto.getDescription()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests find a User by Login. 
     */ 
    public static void testFindByName() { 
        try { 
        	CourseDTO dto = new CourseDTO(); 
            dto = model.findByName("ME"); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getName()); 
            System.out.println(dto.getDescription()); 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests get Search 
     */ 
    public static void testSearch() { 
 
        try { 
        	CourseDTO dto = new CourseDTO(); 
            List list = new ArrayList(); 
            dto.setName("M"); 
            list = model.search(dto, 0, 0); 
            if (list.size() < 0) { 
                System.out.println("Test Serach fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (CourseDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getName()); 
                System.out.println(dto.getDescription()); 
            } 
 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests get List. 
     */ 
    public static void testList() { 
 
        try { 
        	CourseDTO dto = new CourseDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size() < 0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (CourseDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getName()); 
                System.out.println(dto.getDescription()); 
            } 
 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 


}
