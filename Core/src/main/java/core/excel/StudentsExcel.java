package core.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.humanity.student.Student;
import core.study.group.Group;

public class StudentsExcel {	
	public List<Student> readStudentsFromExcel(InputStream _inputStream) throws IOException {
		List<Student> list = new ArrayList<Student>();
	    InputStream inputStream = _inputStream;    
	    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
	    
	    HSSFSheet firstSheet = workbook.getSheetAt(0);
	    
	    Iterator<Row> rowIterator = firstSheet.iterator();    
	    
	    Group group = buildGroupFromExcel(firstSheet);
	    
	    while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(row.getRowNum() == 0 || row.getRowNum() == 1)
            	continue;
            
            Student student = new Student();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                
                student.getDetails().setGroup(group);
                
                buildStudentFromExcel(student, cell);
            }
            list.add(student);
        }
	    
	    inputStream.close();
	    
	    return list;
	}
	
	
	
	
	public List<Student> readStudentsFromExcel(String excelFilePath) throws IOException {
		List<Student> list = new ArrayList<Student>();
	    InputStream inputStream = new FileInputStream(new File(excelFilePath));    
	    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
	    
	    HSSFSheet firstSheet = workbook.getSheetAt(0);
	    
	    Iterator<Row> rowIterator = firstSheet.iterator();    
	    
	    Group group = buildGroupFromExcel(firstSheet);
	    
	    while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(row.getRowNum() == 0 || row.getRowNum() == 1)
            	continue;
            
            Student student = new Student();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                
                student.getDetails().setGroup(group);
                
                buildStudentFromExcel(student, cell);
            }
            list.add(student);
        }
	    
	    inputStream.close();
	    
	    return list;
	}
	private Group buildGroupFromExcel(HSSFSheet firstSheet) {
		Group group = new Group();
	    Row rowGroup = firstSheet.getRow(0);
	    String groupName = rowGroup.getCell(0).toString().substring(6);
	    group.getDetails().setGroupName(groupName);
		return group;
	}
	private void buildStudentFromExcel(Student student, Cell cell) {
		switch (cell.getCellType()) {
		    case Cell.CELL_TYPE_STRING:
		    	if(cell.getColumnIndex() == 1) {
		    		student.getDetails().setLastName(cell.getStringCellValue());
		    	}
		    	if(cell.getColumnIndex() == 2) {
		    		student.getDetails().setFirstName(cell.getStringCellValue());
		    	}
		        break;
		    case Cell.CELL_TYPE_NUMERIC:
		    	if(cell.getColumnIndex() == 0) {
		    		student.getDetails().setAlbumNumber((long)cell.getNumericCellValue());
		    	}
		        break;
		    case Cell.CELL_TYPE_BOOLEAN:
		        break;
		    default :
		    	break;
		}
	}
}