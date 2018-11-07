package com.tao.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.tao.model.ColumnEntity;
import com.tao.model.TableEntity;

public class GenUtils2 {

	public static List<String> getTemplates(){
		List<String> templates = new ArrayList<String>();
		templates.add("template/Entity.java.vm");
		templates.add("template/Dao.java.vm");
		templates.add("template/Dao.xml.vm");
		templates.add("template/Service.java.vm");
		templates.add("template/ServiceImpl.java.vm");
		templates.add("template/Controller.java.vm");
		templates.add("template/list.jsp.vm");
		templates.add("template/list.js.vm");
		templates.add("template/menu.sql.vm");
		return templates;
	}
	
	/**
	 * ���ɴ���
	 * @param iszip 
	 */
	public static void generatorCode(Map<String, String> table,
			List<Map<String, String>> columns, ZipOutputStream zip, boolean iszip,String projectPath){
		//������Ϣ
		Configuration config = getConfig();
		
		//����Ϣ
		TableEntity tableEntity = new TableEntity();
		tableEntity.setTableName(table.get("tableName"));
		tableEntity.setComments(table.get("tableComment"));
		//����ת����Java����
		String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
		tableEntity.setClassName(className);
		tableEntity.setClassname(StringUtils.uncapitalize(className));
		
		//����Ϣ
		List<ColumnEntity> columsList = new ArrayList<>();
		for(Map<String, String> column : columns){
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setColumnName(column.get("columnName"));
			columnEntity.setDataType(column.get("dataType"));
			columnEntity.setComments(column.get("columnComment"));
			columnEntity.setExtra(column.get("extra"));
			
			//����ת����Java������
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setAttrName(attrName);
			columnEntity.setAttrname(StringUtils.uncapitalize(attrName));
			
			//�е��������ͣ�ת����Java����
			String attrType = config.getString(columnEntity.getDataType(), "unknowType");
			columnEntity.setAttrType(attrType);
			
			//�Ƿ�����
			if("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null){
				tableEntity.setPk(columnEntity);
			}
			
			columsList.add(columnEntity);
		}
		tableEntity.setColumns(columsList);
		
		//û���������һ���ֶ�Ϊ����
		if(tableEntity.getPk() == null){
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}
		
		//����velocity��Դ������
		Properties prop = new Properties();  
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");  
		Velocity.init(prop);
		
		//��װģ������
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("pathName", tableEntity.getClassname().toLowerCase());
		map.put("columns", tableEntity.getColumns());
		map.put("package", config.getString("package"));
		map.put("basepackage", config.getString("basepackage"));
		map.put("author", config.getString("author"));
		map.put("email", config.getString("email"));
		map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        VelocityContext context = new VelocityContext(map);
        
        //��ȡģ���б�
		List<String> templates = getTemplates();
		for(String template : templates){
			//��Ⱦģ��
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			
			if(iszip){
				
				try {
					//��ӵ�zip
					zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"))));  
					IOUtils.write(sw.toString(), zip, "UTF-8");
					IOUtils.closeQuietly(sw);
					zip.closeEntry();
					
					
				} catch (IOException e) {
					throw new RuntimeException("��Ⱦģ��ʧ�ܣ�������" + tableEntity.getTableName(), e);
				}
			}else{
				File file = new File(projectPath + File.separator + getFileName(template, tableEntity.getClassName(), config.getString("package")) );
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				try (FileOutputStream fos = new FileOutputStream(file);) {
					fos.write(sw.toString().getBytes());
				} catch (Exception e) {
					throw new RuntimeException("��Ⱦģ��ʧ�ܣ�������" + tableEntity.getTableName(), e);
				}
			}
		}
	}
	
	
	/**
	 * ����ת����Java������
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}
	
	/**
	 * ����ת����Java����
	 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if(StringUtils.isNotBlank(tablePrefix)){
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}
	
	/**
	 * ��ȡ������Ϣ
	 */
	public static Configuration getConfig(){
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException("��ȡ�����ļ�ʧ�ܣ�", e);
		}
	}
	
	/**
	 * ��ȡ�ļ���
	 */
	public static String getFileName(String template, String className, String packageName){
		String packagePath =  "src" + File.separator;
		if(StringUtils.isNotBlank(packageName)){
			packagePath += packageName.replace(".", File.separator) + File.separator;
		}
		
		if(template.contains("Entity.java.vm")){
			return packagePath + "model" + File.separator + className + "Entity.java";
		}
		
		if(template.contains("Dao.java.vm")){
			return packagePath + "dao" + File.separator + className + "Dao.java";
		}
		
		if(template.contains("Dao.xml.vm")){
			return packagePath + "dao" + File.separator + className + "Dao.xml";
		}
		
		if(template.contains("Service.java.vm")){
			return packagePath + "service" + File.separator + className + "Service.java";
		}
		
		if(template.contains("ServiceImpl.java.vm")){
			return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}
		
		if(template.contains("Controller.java.vm")){
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}
		
		if(template.contains("list.jsp.vm")){
			return   "WebRoot" + File.separator + "WEB-INF" + File.separator + "page"
					+ File.separator + "generator" + File.separator + className.toLowerCase() + ".jsp";
		}
		
		if(template.contains("list.js.vm")){
			return   "WebRoot" + File.separator + "js" + File.separator + "generator" + File.separator + className.toLowerCase() + ".js";
		}

		if(template.contains("menu.sql.vm")){
			return className.toLowerCase() + "_menu.sql";
		}
		
		return null;
	}
}
