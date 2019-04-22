package com.tao.frame;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.tao.utils.GenUtils;
import com.tao.utils.MysqlDBUtils;

public class MainFrame implements MouseListener, SelectionListener  {

	private static final String SHOW_TABLES = "show tables;";
	private static final String SHOW_DATABASES = "show databases;";
	private static final String MYSQL = "mysql";
	protected Shell shell;
	private Text ipText;
	private Text userText;
	private Text passText;
	private Text diskText;
	private Button opnFilebutton;
	private Button getDataSourceButton;
	private Button startButton;
	private Text packageText;
	private Text portText;
	private Label lblTest;
	private org.eclipse.swt.widgets.List dataBaseList;
	private org.eclipse.swt.widgets.List tableList;
	private String selectDataBase;
	private Button allselectbutton;
	private Button inverseSelectButton;
	private Button standard;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainFrame window = new MainFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		try {
			Display display = Display.getDefault();
			createContents();
			initData();

			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initData() throws IOException {
		prop = new Properties();
		prop.load(MainFrame.class.getClassLoader().getResourceAsStream("generator.properties"));

		ipText.setText(prop.getProperty("jdbc.ip", "127.0.0.1"));
		passText.setText(prop.getProperty("jdbc.pass", "zyspace@#$"));
		userText.setText(prop.getProperty("jdbc.user", "admin"));
		packageText.setText(prop.getProperty("package", "com.tao"));

	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(977, 536);
		shell.setText("Code Generator for mybatis mysql");
		shell.setLayout(null);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("ip/host\uFF1A");
		lblNewLabel.setBounds(10, 32, 76, 20);

		ipText = new Text(shell, SWT.BORDER);
		ipText.setBounds(106, 29, 273, 26);

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(10, 87, 76, 20);
		label.setText("\u7528\u6237\u540D\uFF1A");

		userText = new Text(shell, SWT.BORDER);
		userText.setBounds(106, 84, 273, 26);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5BC6\u7801\uFF1A");
		label_1.setBounds(10, 141, 76, 20);

		passText = new Text(shell, SWT.BORDER);
		passText.setBounds(106, 138, 273, 26);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(10, 321, 76, 20);
		label_2.setText("\u8F93\u51FA\u76EE\u5F55\uFF1A");

		diskText = new Text(shell, SWT.BORDER);
		diskText.setBounds(106, 318, 273, 26);

		opnFilebutton = new Button(shell, SWT.NONE);
		opnFilebutton.setBounds(396, 316, 57, 30);
		opnFilebutton.setText("\u6253\u5F00");
		opnFilebutton.addMouseListener(this);

		getDataSourceButton = new Button(shell, SWT.NONE);
		getDataSourceButton.setBounds(136, 378, 98, 30);
		getDataSourceButton.setText("\u83B7\u53D6\u6570\u636E\u6E90");
		getDataSourceButton.addMouseListener(this);
		startButton = new Button(shell, SWT.NONE);
		startButton.setBounds(251, 378, 98, 30);
		startButton.setText("\u5F00\u59CB\u751F\u6210");
		startButton.addMouseListener(this);

		Label lblPackage = new Label(shell, SWT.NONE);
		lblPackage.setBounds(10, 261, 76, 20);
		lblPackage.setText("package:");

		packageText = new Text(shell, SWT.BORDER);
		packageText.setBounds(106, 258, 273, 26);

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u7AEF\u53E3\uFF1A");
		label_3.setBounds(10, 198, 76, 20);

		portText = new Text(shell, SWT.BORDER);
		portText.setText("3306");
		portText.setBounds(106, 195, 273, 26);

		dataBaseList = new org.eclipse.swt.widgets.List(shell, SWT.V_SCROLL | SWT.H_SCROLL);
		tableList = new org.eclipse.swt.widgets.List(shell, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);

		dataBaseList.setBounds(478, 32, 194, 377);

		dataBaseList.addSelectionListener(this);

		tableList.setBounds(719, 65, 194, 342);
		tableList.addSelectionListener(this);
		allselectbutton = new Button(shell, SWT.CHECK);
		allselectbutton.addSelectionListener(this);
		allselectbutton.setBounds(719, 32, 57, 20);
		allselectbutton.setText("\u5168\u9009");

		inverseSelectButton = new Button(shell, SWT.CHECK);
		inverseSelectButton.setBounds(856, 32, 57, 20);
		inverseSelectButton.setText("\u53CD\u9009");
		inverseSelectButton.addSelectionListener(this);
		
		
		standard = new Button(shell, SWT.CHECK);
		standard.setBounds(10, 378, 150, 30);
		standard.setText("标准maven项目");
		standard.addSelectionListener(this);

	}

	@Override
	public void mouseDoubleClick(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDown(MouseEvent arg0) {
		Object source = arg0.getSource();
		if (source == getDataSourceButton) {
			getDataSource();
		} else if (source == opnFilebutton) {

			openFileDiaLog();
		} else if (source == startButton) {
			startGener();
		}

	}

	private void startGener() {

		if ("".equals(diskText.getText())) {

			byte[] data = generatorCode(tableList.getSelection());

			File file = new File(diskText.getText() + File.separator + "test.zip");
			if (file.exists()) {
				file.delete();
			}
			try (FileOutputStream fos = new FileOutputStream(file);) {

				fos.write(data);
			} catch (Exception e) {

			}
		} else {

			generatorCode(tableList.getSelection(), diskText.getText() + File.separator);

		}
	}

	private void generatorCode(String[] tableNames, String projectPath) {
		for (String tableName : tableNames) {
			Map<String, String> table = MysqlDBUtils.getInstanse().queryTable(ipText.getText().trim(),
					portText.getText().trim(), passText.getText().trim(), userText.getText().trim(), selectDataBase,
					tableName);
			List<Map<String, String>> columns = MysqlDBUtils.getInstanse().queryColumns(ipText.getText().trim(),
					portText.getText().trim(), passText.getText().trim(), userText.getText().trim(), selectDataBase,
					tableName);
			GenUtils.generatorCode(table, columns, null, false,projectPath,packageText.getText());
		}

	}

	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for (String tableName : tableNames) {
			Map<String, String> table = MysqlDBUtils.getInstanse().queryTable(ipText.getText().trim(),
					portText.getText().trim(), passText.getText().trim(), userText.getText().trim(), selectDataBase,
					tableName);
			List<Map<String, String>> columns = MysqlDBUtils.getInstanse().queryColumns(ipText.getText().trim(),
					portText.getText().trim(), passText.getText().trim(), userText.getText().trim(), selectDataBase,
					tableName);
			GenUtils.generatorCode(table, columns, zip, true, null,packageText.getText());
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	private void openFileDiaLog() {
		// JFileChooser chooser = new JFileChooser();
		//
		// int state = chooser.showDialog(null, "ѡ����ĿĿ¼");
		// String str = "";
		// if (state != JFileChooser.CANCEL_OPTION) {
		// str = chooser.getCurrentDirectory().toString();
		// } else {
		// File f;
		// f = chooser.getSelectedFile();
		// if (f == null) {
		// f = chooser.getCurrentDirectory();
		// str = f.toString();
		// }
		// str = f.toString();
		// }
		FileDialog fileDialog1 =  new FileDialog(shell);
		
		fileDialog1.setText("选择项目");
		fileDialog1.open();
		diskText.setText(fileDialog1.getFilterPath());
	}

	
	
	private void getDataSource() {
		dataBaseList.removeAll();
		addData(dataBaseList, MYSQL, SHOW_DATABASES);
	}

	private void addData(org.eclipse.swt.widgets.List dataBaseList, String dataBase, String sql) {
		List<List<String>> dataList = MysqlDBUtils.getInstanse().getDataList(ipText.getText().trim(),
				portText.getText().trim(), passText.getText().trim(), userText.getText().trim(), dataBase, sql);
		for (List<String> list : dataList) {
			for (String item : list) {
				dataBaseList.add(item);
			}

		}
	}

	@Override
	public void mouseUp(MouseEvent arg0) {

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
	}

	private boolean all = false;
	private Properties prop;

	@Override
	public void widgetSelected(SelectionEvent event) {

		if (event.getSource() == dataBaseList) {

			selectDataBase = dataBaseList.getItem(dataBaseList.getSelectionIndex());
			tableList.removeAll();
			addData(tableList, selectDataBase, SHOW_TABLES);
		} else if (event.getSource() == tableList) {
			int[] indices = tableList.getSelectionIndices();
			System.out.println(Arrays.toString(indices));
		} else if (allselectbutton == event.getSource()) {
			if (!all) {
				all = true;
				tableList.selectAll();
			} else {
				all = false;
			}

		} else if (inverseSelectButton == event.getSource()) {
			int[] indices = tableList.getSelectionIndices();
			tableList.selectAll();
			tableList.deselect(indices);
		} else if (standard == event.getSource()) {
			
			GenUtils.src = "src/main/java";
			GenUtils.WebRoot = "src/main/webapp";
			
			
		}

	}

}
