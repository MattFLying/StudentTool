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
import core.humanity.student.Student;
import core.study.group.Group;

/***
 * Class using to operate excel files with student generated from University
 * System - PracNet
 * 
 * @author Mateusz Mucha
 *
 */
public class StudentsExcel {
	/***
	 * Method to read excel file and convert data to student objects.
	 * 
	 * @param _inputStream
	 *            - input stream file of students excel file
	 * @return list of converted students
	 * @throws IOException
	 */
	public List<Student> readStudentsFromExcel(InputStream _inputStream) throws IOException {
		List<Student> list = new ArrayList<Student>();
		InputStream inputStream = _inputStream;
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

		HSSFSheet firstSheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = firstSheet.iterator();

		Group group = buildGroupFromExcel(firstSheet);

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row.getRowNum() == 0 || row.getRowNum() == 1)
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

	/***
	 * Method to read excel file and convert data to student objects.
	 * 
	 * @param excelFilePath
	 *            - path of excel file with students to convert
	 * @return - list of converted students
	 * @throws IOException
	 */
	public List<Student> readStudentsFromExcel(String excelFilePath) throws IOException {
		List<Student> list = new ArrayList<Student>();
		InputStream inputStream = new FileInputStream(new File(excelFilePath));
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

		HSSFSheet firstSheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = firstSheet.iterator();

		Group group = buildGroupFromExcel(firstSheet);

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row.getRowNum() == 0 || row.getRowNum() == 1)
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

	/***
	 * Create group for from excel file for students.
	 * 
	 * @param firstSheet
	 *            - first sheet of excel file where all data are
	 * @return group object
	 */
	private Group buildGroupFromExcel(HSSFSheet firstSheet) {
		Group group = new Group();
		Row rowGroup = firstSheet.getRow(0);
		String groupName = rowGroup.getCell(0).toString().substring(6);
		group.getDetails().setGroupName(groupName);
		return group;
	}

	/**
	 * Method build student object from excel data, sets included data from
	 * excel to student object like album number, group, first name and last
	 * name.
	 * 
	 * @param student
	 *            - student to build
	 * @param cell
	 *            - cell from excel file
	 */
	private void buildStudentFromExcel(Student student, Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			if (cell.getColumnIndex() == 1) {
				student.getDetails().setLastName(cell.getStringCellValue());
			}
			if (cell.getColumnIndex() == 2) {
				student.getDetails().setFirstName(cell.getStringCellValue());
			}
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (cell.getColumnIndex() == 0) {
				student.getDetails().setAlbumNumber((long) cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			break;
		default:
			break;
		}
	}
}