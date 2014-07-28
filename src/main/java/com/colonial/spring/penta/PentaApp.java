package com.colonial.spring.penta;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.colonial.spring.penta.domain.PentaEquipmentDetails;
import com.colonial.spring.penta.service.PentaEquipmentService;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PentaApp extends JPanel{
	private static Logger logger = Logger.getLogger(PentaApp.class);
	static String filePath;
	static String filename;
	static String intr_name = "";
	static JFrame frame;
	static JLabel lblPentaUser;
	static JTextArea textArea;
	private static JTextField txtPentaUser;
	static JButton btnUploadFile;
	static JButton btnDeleteRows;
	static JButton btnReadRows;
	static JCheckBox chckbxInterface_1;
	static JCheckBox chckbxInterface_2;
	static JCheckBox chckbxInterface_3;
	static JCheckBox chckbxInterface_4;
	static JCheckBox chckbxInterface_5;
	static JCheckBox chckbxInterface_6;
	static JButton btnProcess;
  private static Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
  private static Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	public PentaApp() {
		setLayout(null);		
		JLabel lblEquipmentInformationForm = new JLabel("Equipment Information Form");
		lblEquipmentInformationForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEquipmentInformationForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipmentInformationForm.setBounds(25, 0, 264, 44);
		add(lblEquipmentInformationForm);
		
		btnUploadFile = new JButton("Upload file to Interface");
		btnUploadFile.setToolTipText("It must be an Excel file with data in the first sheet!");
		btnUploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteData(0);
				getFile();
			}
		});
		btnUploadFile.setBounds(61, 86, 193, 23);
		add(btnUploadFile);
		btnUploadFile.setEnabled(false);
		
		btnDeleteRows = new JButton("Delete Interface");
		btnDeleteRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteData(0);
			}
		});
		btnDeleteRows.setBounds(61, 134, 193, 23);
		add(btnDeleteRows);
		btnDeleteRows.setEnabled(false);
		
		btnReadRows = new JButton("Read Interface");
		btnReadRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readDatabase();
			}
		});
		btnReadRows.setBounds(61, 181, 193, 23);
		add(btnReadRows);
		btnReadRows.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 238, 433, 105);
		add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setToolTipText("Just a little bit of information!");
		
		lblPentaUser = new JLabel("Penta User:");
		lblPentaUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPentaUser.setBounds(61, 55, 97, 20);
		add(lblPentaUser);
		
		txtPentaUser = new JTextField();
		txtPentaUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(txtPentaUser.getText().length()>=6)
		    {
					txtPentaUser.setText(txtPentaUser.getText().substring(0, 5).toUpperCase());
		    }
			}
			@Override
			public void keyReleased(KeyEvent e) {
				txtPentaUser.setText(txtPentaUser.getText().toUpperCase());
				if(txtPentaUser.getText().length()==6)
		    {
					btnUploadFile.setEnabled(true);
					btnDeleteRows.setEnabled(true);
					btnReadRows.setEnabled(true);
		    }
			}
		});
		txtPentaUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPentaUser.setBounds(168, 55, 61, 20);
		add(txtPentaUser);
		txtPentaUser.setColumns(6);
		
		chckbxInterface_1 = new JCheckBox("Interface 1");
		chckbxInterface_1.setEnabled(false);
		chckbxInterface_1.setBounds(317, 86, 126, 23);
		add(chckbxInterface_1);
		
		chckbxInterface_2 = new JCheckBox("Interface 2");
		chckbxInterface_2.setEnabled(false);
		chckbxInterface_2.setBounds(317, 112, 126, 23);
		add(chckbxInterface_2);
		
		chckbxInterface_3 = new JCheckBox("Interface 3");
		chckbxInterface_3.setEnabled(false);
		chckbxInterface_3.setBounds(317, 138, 126, 23);
		add(chckbxInterface_3);
		
		chckbxInterface_4 = new JCheckBox("Interface 4");
		chckbxInterface_4.setEnabled(false);
		chckbxInterface_4.setBounds(317, 164, 126, 23);
		add(chckbxInterface_4);
		
		chckbxInterface_5 = new JCheckBox("Interface 5");
		chckbxInterface_5.setEnabled(false);
		chckbxInterface_5.setBounds(317, 190, 126, 23);
		add(chckbxInterface_5);
		
		chckbxInterface_6 = new JCheckBox("Interface 6");
		chckbxInterface_6.setEnabled(false);
		chckbxInterface_6.setBounds(317, 216, 126, 23);
		add(chckbxInterface_6);
		
		btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Nothing to report.");
				ApplicationContext context = new ClassPathXmlApplicationContext(
						"classpath:META-INF/spring/applicationContext.xml");

				PentaEquipmentService pentaEquipmentService = context
						.getBean(PentaEquipmentService.class);
				if(chckbxInterface_1.isEnabled()&&chckbxInterface_1.isSelected()){
					pentaEquipmentService.callInterface(txtPentaUser.getText(),intr_name+0);
					textArea.setText("Succesful. "+intr_name+"0");
				}
				if(chckbxInterface_2.isEnabled()&&chckbxInterface_2.isSelected()){
					pentaEquipmentService.callInterface(txtPentaUser.getText(),intr_name+1);
					textArea.setText("Succesful. "+intr_name+"1");
				}
				if(chckbxInterface_3.isEnabled()&&chckbxInterface_3.isSelected()){
					pentaEquipmentService.callInterface(txtPentaUser.getText(),intr_name+2);
					textArea.setText("Succesful. "+intr_name+"2");
				}
				if(chckbxInterface_4.isEnabled()&&chckbxInterface_4.isSelected()){
					pentaEquipmentService.callInterface(txtPentaUser.getText(),intr_name+3);
					textArea.setText("Succesful. "+intr_name+"3");
				}
				if(chckbxInterface_5.isEnabled()&&chckbxInterface_5.isSelected()){
					pentaEquipmentService.callInterface(txtPentaUser.getText(),intr_name+4);
					textArea.setText("Succesful. "+intr_name+"4");
				}
				if(chckbxInterface_6.isEnabled()&&chckbxInterface_6.isSelected()){
					pentaEquipmentService.callInterface(txtPentaUser.getText(),intr_name+5);
					textArea.setText("Succesful. "+intr_name+"5");
				}
			}
		});
		btnProcess.setEnabled(false);
		btnProcess.setBounds(317, 56, 89, 23);
		add(btnProcess);
	}
	void getFile(){

		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Excel 2007+ files", "xlsx");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	filename = chooser.getSelectedFile().getName();
	    	filePath = chooser.getSelectedFile().getAbsolutePath();
			logger.info("filePath: "+filePath);
	    }
		if (filename == null){
			logger.info("You cancelled the choice");
			textArea.setText("You need to choose a file to upload");
		}
		else
			readExcel(filePath);
	}
	public static void main(String args[]) throws Exception {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
       public void run() {
           createAndShowGUI();
       }
   });
	}

	protected static void createAndShowGUI() {
		frame = new JFrame("Interface to Penta");
		//Create and set up the content pane.
        JComponent newContentPane = new PentaApp();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(100, 100, 470, 415);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private static void readDatabase() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		PentaEquipmentService pentaEquipmentService = context
				.getBean(PentaEquipmentService.class);

//		PentaEquipmentDetails pentaEquipmentDetails = new PentaEquipmentDetails();
		List <PentaEquipmentDetails> pentaEquipmentList = pentaEquipmentService
				.readFromEquipment();
		logger.info("intr_rqst_id,cus_eq_id,cus_id,cus_loc_cd,seq_num,model_id,serial_id");
		textArea.setText("Interface data:");
		ListIterator litr = pentaEquipmentList.listIterator();
	      while(litr.hasNext()) {
	    	  PentaEquipmentDetails equipment = (PentaEquipmentDetails)litr.next();
	    		logger.info(equipment.getIntr_rqst_id()+","+equipment.getCus_eq_id()+","+equipment.getCus_id()+","+equipment.getCus_loc_cd()+","+equipment.getSeq_num()+","+equipment.getModel_id()+","+equipment.getSerial_id());
	    	  logger.info("cus_eq_id: "+equipment.getCus_eq_id());
	    	  logger.info("cus_id: "+equipment.getCus_id());
	    	  logger.info("cus_loc_cd: "+equipment.getCus_loc_cd());
	    	  logger.info("seq_num: "+equipment.getSeq_num());
	    	  logger.info("model_id: "+equipment.getModel_id());
	    	  logger.info("serial_id: "+equipment.getSerial_id());
	    	  textArea.append("\nCus_eq_id: "+equipment.getCus_eq_id());
	    	  textArea.append("\nCus_id: "+equipment.getCus_id());
	      }
		logger.info("End of data");
//		pentaEquipmentService.callInterface();
	}

	private static void deleteData(int pentaEquipmentId) {		
		frame.setCursor(waitCursor);
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		PentaEquipmentService pentaEquipmentService = context
				.getBean(PentaEquipmentService.class);

		PentaEquipmentDetails pentaEquipmentDetails = new PentaEquipmentDetails();
		pentaEquipmentService
				.deleteFromEquipment(pentaEquipmentId);
		pentaEquipmentService
		.deleteArea(pentaEquipmentId);
		pentaEquipmentService
		.deleteNotes(pentaEquipmentId);

		logger.info("Delete penta equipment with id - "
				+ pentaEquipmentId);

		textArea.setText("Interface has been cleaned.");
		chckbxInterface_1.setEnabled(false);
		chckbxInterface_2.setEnabled(false);
		chckbxInterface_3.setEnabled(false);
		chckbxInterface_4.setEnabled(false);
		chckbxInterface_5.setEnabled(false);
		chckbxInterface_6.setEnabled(false);
		chckbxInterface_1.setSelected(false);
		chckbxInterface_2.setSelected(false);
		chckbxInterface_3.setSelected(false);
		chckbxInterface_4.setSelected(false);
		chckbxInterface_5.setSelected(false);
		chckbxInterface_6.setSelected(false);
		chckbxInterface_6.setText("Interface 6");
		chckbxInterface_5.setText("Interface 5");
		chckbxInterface_4.setText("Interface 4");
		chckbxInterface_3.setText("Interface 3");
		chckbxInterface_2.setText("Interface 2");
		chckbxInterface_1.setText("Interface 1");
		btnProcess.setEnabled(false);
		frame.setCursor(defaultCursor);
	}

	private static void readExcel(String path) {
		try{			
			frame.setCursor(waitCursor);
			boolean processFile = true;
		int i = 1;
		intr_name = "";
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		PentaEquipmentService pentaEquipmentService = context
				.getBean(PentaEquipmentService.class);

		PentaEquipmentDetails pentaEquipmentDetails = new PentaEquipmentDetails();
		int pentaEquipmentId;
		Calendar calendar = Calendar.getInstance();
		intr_name = "EAF" + calendar.get(Calendar.YEAR)
				+ String.format("%02d", calendar.get(Calendar.MONTH) + 1)
				+ calendar.get(Calendar.DAY_OF_MONTH);
		pentaEquipmentDetails.setIntr_rqst_id(intr_name+"0");
		pentaEquipmentDetails.setD_stat_date(new java.sql.Date(calendar.getTimeInMillis()));
		String user = txtPentaUser.getText();
		try {
			OPCPackage pkg = OPCPackage.open(path);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(pkg);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				if(i%350==0){
					pentaEquipmentDetails.setIntr_rqst_id(intr_name+i/350);
				}
				Row row = rowIterator.next();
				pentaEquipmentDetails.setSeq_num(i);
				
				//Cus_id
				Cell cellData = row.getCell(1);
				if (cellData == null) {
					logger.info("\nPlease check Row " + i
							+ ", Customer Id is null");
					processFile = false;
					textArea.append("\nPlease check after Row " + i
							+ " because Customer Id is null");
					break;
				} else {
					// Check the cell type and format accordingly
					switch (cellData.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						pentaEquipmentDetails.setCus_id((int) cellData
								.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						pentaEquipmentDetails.setCus_id(Integer.parseInt(cellData
								.getStringCellValue()));
						break;
					}
				}
				
				//Cus_loc_cd
				cellData = row.getCell(2);
				if (cellData == null) {
					logger.info("\nPlease check Row " + i
							+ ", Customer Location is null");
					processFile = false;
					textArea.append("\nPlease check after Row " + i
							+ " because Customer Location is null");
					break;
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setCus_loc_cd(String
	    							.valueOf((int) cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	  						pentaEquipmentDetails.setCus_loc_cd(String
	    							.valueOf(Integer.parseInt(cellData
									.getStringCellValue())));
	                          break;
	                  }
				}
				
				//Customer_system_id
				cellData = row.getCell(3);
				if (cellData == null) {
					pentaEquipmentDetails.setCustomer_system_id("Unit");
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setCustomer_system_id(String
	    							.valueOf(cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	                      	if(cellData.getStringCellValue().length()>30){
	                    		  pentaEquipmentDetails.setCustomer_system_id(cellData
	  	    							.getStringCellValue().substring(0, 30));
	                    	  }
	                    	  else{
	                    		  pentaEquipmentDetails.setCustomer_system_id(cellData
	  	    							.getStringCellValue());
	                    	  }
	                          break;
	                  }
				}
				
				//Cus_eq_manufacturer_id
				cellData = row.getCell(4);
				if (cellData == null) {
					logger.info("\nPlease check Row " + i
							+ ", Manufacturer is null");
					processFile = false;
					textArea.append("\nPlease check after Row " + i
							+ " because Manufaturer is null");
					break;
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setCus_eq_manufacturer_id(String
	    							.valueOf(cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	      					pentaEquipmentDetails.setCus_eq_manufacturer_id(cellData
	    							.getStringCellValue());
	                          break;
                        case Cell.CELL_TYPE_FORMULA:
          	      					pentaEquipmentDetails.setCus_eq_manufacturer_id(cellData
          	    							.getStringCellValue());
          	                          break;
	                  }
				}
				
				//Model_id
				cellData = row.getCell(6);
				if (cellData == null) {
					logger.info("\nPlease check Row " + i
							+ ", Model is null");
					processFile = false;
					textArea.append("\nPlease check after Row " + i
							+ " because Model Id is null");
					break;
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setModel_id(String
	    							.valueOf(cellData.getNumericCellValue()));
	      					if (pentaEquipmentDetails.getModel_id().length()>18){
		      					pentaEquipmentDetails.setModel_id(pentaEquipmentDetails.getModel_id().substring(0, 18));
	      					}
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	                    	  if(cellData.getStringCellValue().length()>18){
	                    		  pentaEquipmentDetails.setModel_id(cellData
	  	    							.getStringCellValue().substring(0, 18));
	                    	  }
	                    	  else{
	                    		  pentaEquipmentDetails.setModel_id(cellData
	  	    							.getStringCellValue());
	                    	  }
	                          break;
	                  }
				}
				
				//Serial_id
				cellData = row.getCell(7);
				if (cellData == null) {
					logger.info("\nPlease check Row " + i
							+ ", Serial is null");
					processFile = false;
					textArea.append("\nPlease check after Row " + i
							+ " because Serial Id is null");
					break;
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setSerial_id(String
	    							.valueOf(cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	      					pentaEquipmentDetails.setSerial_id(cellData
	    							.getStringCellValue());
	                          break;
	                  }
				}
				
				//Location_in_building
				cellData = row.getCell(8);
				if (cellData == null) {
					pentaEquipmentDetails.setLocation_in_building("");
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setLocation_in_building(String
	    							.valueOf(cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	      					pentaEquipmentDetails.setLocation_in_building(cellData
	    							.getStringCellValue());
	                          break;
	                  }
				}
				
			//Area
				cellData = row.getCell(9);
				if (cellData == null) {
					pentaEquipmentDetails.setArea("");
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setArea(String
	    							.valueOf(cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	      					pentaEquipmentDetails.setArea(cellData
	    							.getStringCellValue());
	                          break;
	                  }
				}
				
				//Description
				cellData = row.getCell(10);//Get Class type
				if (cellData == null) {
					pentaEquipmentDetails.setDescription("");
//					processFile = false;
					textArea.append("\nPlease check after Row " + i
							+ " because Class Type is null");
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setDescription(String
	    							.valueOf(cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	      					pentaEquipmentDetails.setDescription(cellData
	    							.getStringCellValue());
	                          break;
	                  }
				}//Unit#, Brand, Equip Class Type, last 4 of Serial#. 
        pentaEquipmentDetails.setDescription(pentaEquipmentDetails.getCustomer_system_id()+"-"+pentaEquipmentDetails.getCus_eq_manufacturer_id()+"-"+pentaEquipmentDetails.getDescription()+"-"+pentaEquipmentDetails.getSerial_id().substring(pentaEquipmentDetails.getSerial_id().length()-4));
				
				//Cus_eq_class_cd
				cellData = row.getCell(11);
				if (cellData == null) {
					pentaEquipmentDetails.setCus_eq_class_cd("");
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setCus_eq_class_cd(String
	    							.valueOf((int) cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	      					pentaEquipmentDetails.setCus_eq_class_cd(cellData
	    							.getStringCellValue());
	                          break;
	                  }
				}
				
			//Filter
				cellData = row.getCell(13);
				if (cellData == null) {
					pentaEquipmentDetails.setFilter("");
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setFilter(String
	    							.valueOf(cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	      					pentaEquipmentDetails.setFilter(cellData
	    							.getStringCellValue());
	                          break;
	                  }
				}
				
			//Note
				cellData = row.getCell(14);
				if (cellData == null) {
					pentaEquipmentDetails.setNote("");
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setNote(String
	    							.valueOf(cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	      					pentaEquipmentDetails.setNote(cellData
	    							.getStringCellValue());
	                          break;
	                  }
				}
				
			//Safety
				cellData = row.getCell(15);
				if (cellData == null) {
					pentaEquipmentDetails.setSafety("");
				} else {
	                  //Check the cell type and format accordingly
	                  switch (cellData.getCellType())
	                  {
	                      case Cell.CELL_TYPE_NUMERIC:
	      					pentaEquipmentDetails.setSafety(String
	    							.valueOf(cellData.getNumericCellValue()));
	                          break;
	                      case Cell.CELL_TYPE_STRING:
	      					pentaEquipmentDetails.setSafety(cellData
	    							.getStringCellValue());
	                          break;
	                  }
				}
				
				pentaEquipmentDetails.setCus_eq_id(intr_name + i);
				pentaEquipmentDetails.setUser_id(user);
				pentaEquipmentDetails.setActivity_cd("A");
				pentaEquipmentDetails.setCus_eq_stat_cd("A");
				i++;
				if(processFile){
				pentaEquipmentId = pentaEquipmentService
						.createPentaEquipment(pentaEquipmentDetails);
//				textArea.append("\nEquipment "+i);
				if (pentaEquipmentDetails.getArea()!=null&&pentaEquipmentDetails.getArea().length()>3){
					pentaEquipmentDetails.setAlpha_value(pentaEquipmentDetails.getArea());
					pentaEquipmentId = pentaEquipmentService
					.createArea(pentaEquipmentDetails);
				}
				if (pentaEquipmentDetails.getFilter()!=null&&pentaEquipmentDetails.getFilter().length()>3){
					pentaEquipmentDetails.setWo_note_type_cd(11);
					pentaEquipmentDetails.setText_3(pentaEquipmentDetails.getFilter());
					pentaEquipmentId = pentaEquipmentService
					.createFilter(pentaEquipmentDetails);
				}
				if (pentaEquipmentDetails.getNote()!=null&&pentaEquipmentDetails.getNote().length()>3){
					pentaEquipmentDetails.setWo_note_type_cd(8);
					pentaEquipmentDetails.setText_3(pentaEquipmentDetails.getNote());
					pentaEquipmentDetails.setSeq_num(i+10000);
					pentaEquipmentId = pentaEquipmentService
					.createNote(pentaEquipmentDetails);
				}
				if (pentaEquipmentDetails.getSafety()!=null&&pentaEquipmentDetails.getSafety().length()>3){
					pentaEquipmentDetails.setWo_note_type_cd(3);
					pentaEquipmentDetails.setText_3(pentaEquipmentDetails.getSafety());
					pentaEquipmentDetails.setSeq_num(i+20000);
					pentaEquipmentId = pentaEquipmentService
					.createSafety(pentaEquipmentDetails);
				}

//				logger.info("Created penta equipment with id - " + pentaEquipmentId);
				}
			}
			// file.close();
			pkg.close();
			logger.info("End of file");
////			if(processFile){
//				for (int j=0; j<=i/350; j++){
//					pentaEquipmentService.callInterface(user,intr_name+j/350);
//				}
////			}
			int j = i/350;
			switch (j){
				case 5: chckbxInterface_6.setEnabled(true);
								chckbxInterface_6.setText(intr_name+"5");
				case 4: chckbxInterface_5.setEnabled(true);
								chckbxInterface_5.setText(intr_name+"4");
				case 3: chckbxInterface_4.setEnabled(true);
								chckbxInterface_4.setText(intr_name+"3");
				case 2: chckbxInterface_3.setEnabled(true);
								chckbxInterface_3.setText(intr_name+"2");
				case 1: chckbxInterface_2.setEnabled(true);
								chckbxInterface_2.setText(intr_name+"1");
				case 0: chckbxInterface_1.setEnabled(true);
								chckbxInterface_1.setText(intr_name+"0");
								btnProcess.setEnabled(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		} finally {      
			frame.setCursor(defaultCursor);
		}
	}
}
