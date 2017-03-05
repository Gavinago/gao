package com.wssys.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	private static POIFSFileSystem fs;
	private static HSSFWorkbook wb;
	private static HSSFSheet sheet;
	private static HSSFRow row;
	private static FileInputStream input;
	private static String[] excleTitle;

	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	/**
	 * 根据文件路径读取Excel数据内容 返回map
	 * 
	 * @param excelPath
	 * @return
	 */
	public static Map<Integer, JSONObject> readExcelContent(String excelPath) {
		Map<Integer, JSONObject> contentJson = new LinkedHashMap<Integer, JSONObject>();
		String excelStr = "";// excel 内容
		try {
			input = new FileInputStream(new File(excelPath));

			fs = new POIFSFileSystem(input);
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum(); // 得到总行数
			row = sheet.getRow(0);// 得到标题的内容对象。
			int colNum = row.getPhysicalNumberOfCells();// 得到每行的列数。

			excleTitle = new String[colNum];
			for (int i = 0; i < colNum; i++) {

				excleTitle[i] = getStringCellValue(row.getCell((short) i));
			}

			// 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = 1; i <= rowNum; i++) {

				row = sheet.getRow(i);
				int j = 0;

				while (j < colNum) {
					String v = "";
					if (j + 1 == colNum) {
						String vs = getStringCellValue(row.getCell((short) j))
								.trim();
						if (vs.indexOf(".") > -1) {
							if (isNum(vs)) { // 是否是数字

								if (vs.endsWith("0")) {

									v = vs.substring(0, vs.indexOf("."));
								}

							} else {
								v = vs.trim();
							}

						} else {
							v = vs.trim();
						}
						excelStr += v;
					} else {

						String vs = getStringCellValue(row.getCell((short) j))
								.trim() + "&";
						if (vs.indexOf(".") > -1) {
							if (isNum(vs)) { // 是否是数字
								if (vs.endsWith("0")) { // 处理用poi读取excel整数后面加.0的格式化
									v = vs.substring(0, vs.indexOf("."));
								}

							} else {
								v = vs.trim();
							}

						} else {
							v = vs.trim();
						}
						excelStr += v;
					}

					j++;

				}
				String excelstrArray[] = excelStr.split("&", -1); // 每行数据

				Map<String, String> params = new LinkedHashMap<String, String>();
				for (int k = 0; k < excelstrArray.length; k++) {
					params.put(excleTitle[k], excelstrArray[k]);
				}
				JSONObject jsonObject = JSONObject.fromObject(params);
				contentJson.put(i, jsonObject);

				// content.put(i, excelStr);

				excelStr = "";
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return contentJson;
	}

	/**
	 * 根据文件流读取Excel数据内容 返回map 2003
	 * 
	 * @param input
	 * @param count从第几行开始读
	 * @return
	 */
	public static Map<Integer, JSONObject> readExcelContent(InputStream input,
			int count) {// 读取Excel数据内容
		Map<Integer, JSONObject> contentJson = new LinkedHashMap<Integer, JSONObject>();
		String excelStr = "";// excel 内容
		try {

			fs = new POIFSFileSystem(input);
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum(); // 得到总行数
			row = sheet.getRow(0);// 得到标题的内容对象。
			int colNum = row.getPhysicalNumberOfCells();// 得到每行的列数。

			excleTitle = new String[colNum];
			for (int i = 0; i < colNum; i++) {

				excleTitle[i] = getStringCellValue(row.getCell(i));
			}

			// 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = count; i <= rowNum; i++) {

				row = sheet.getRow(i);
				int j = 0;

				while (j < colNum) {
					String v = "";
					if (j + 1 == colNum) {
						String vs = getStringCellValue(row.getCell(j)).trim();
						if (vs.indexOf(".") > -1) {
							if (isNum(vs)) { // 是否是数字

								if (vs.endsWith("0")) {

									v = vs.substring(0, vs.indexOf("."));
								}

							} else {
								v = vs.trim();
							}

						} else {
							v = vs.trim();
						}
						excelStr += v;
					} else {

						String vs = getStringCellValue(row.getCell(j)).trim()
								+ "&";
						if (vs.indexOf(".") > -1) {
							if (isNum(vs)) { // 是否是数字
								if (vs.endsWith("0")) { // 处理用poi读取excel整数后面加.0的格式化
									v = vs.substring(0, vs.indexOf("."));
								}

							} else {
								v = vs.trim();
							}

						} else {
							v = vs.trim();
						}
						excelStr += v;
					}

					j++;

				}
				String excelstrArray[] = excelStr.split("&", -1); // 每行数据

				Map<String, String> params = new LinkedHashMap<String, String>();
				for (int k = 0; k < excelstrArray.length; k++) {
					params.put(excleTitle[k], excelstrArray[k]);
				}
				JSONObject jsonObject = JSONObject.fromObject(params);
				contentJson.put(i, jsonObject);

				// content.put(i, excelStr);

				excelStr = "";
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return contentJson;
	}

	/**
	 * 读取Office 2007 excel
	 * 
	 * @param input
	 * @param count
	 *            从第几行开始读
	 * @return
	 * @throws IOException
	 */
	public static Map<Integer, JSONObject> read2007Excels(InputStream input,
			int count) throws IOException {
		Map<Integer, JSONObject> contentJson = new LinkedHashMap<Integer, JSONObject>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(input);
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);

		XSSFRow row = null;
		XSSFCell cell = null;

		XSSFRow headerrow = sheet.getRow(0); // 表头 得到标题的内容对象
		int colNum = headerrow.getPhysicalNumberOfCells();// 得到每行的列数。
		excleTitle = new String[colNum];
		for (int i = 0; i < colNum; i++) {

			excleTitle[i] = getStringCellValue(headerrow.getCell((short) i));
		}

		// System.out.println(sheet.getPhysicalNumberOfRows());
		// 循环内容项 不循环标题 所以+1
		for (int i = sheet.getFirstRowNum() + count; i <= sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<String> linked = new LinkedList<String>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Object value = null;
				cell = row.getCell(j);
				if (null != cell) {
					value = getStringCellValue(cell);
				}

				linked.add(StringUtil.stringIsNull(value));
			}
			Map<String, String> params = new LinkedHashMap<String, String>();
			for (int j = 0; j < linked.size(); j++) {
				params.put(excleTitle[j], linked.get(j));
			}
			JSONObject jsonObject = JSONObject.fromObject(params);
			contentJson.put(i, jsonObject);
		}

		return contentJson;
	}

	/**
	 * 根据(字节串(或叫字节数组)变成输入流的形式)读取Excel数据内容 返回map
	 * 
	 * @param input
	 * @return
	 */
	public static Map<Integer, JSONObject> readExcelContent(
			ByteArrayInputStream input) {// 读取Excel数据内容
		// Map<Integer, String> content = new HashMap<Integer, String>();
		Map<Integer, JSONObject> contentJson = new LinkedHashMap<Integer, JSONObject>();
		String excelStr = "";// excel 内容
		try {

			// ByteArrayInputStream is = new ByteArrayInputStream( new
			// byte[1000]);

			fs = new POIFSFileSystem(input);
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum(); // 得到总行数
			row = sheet.getRow(0);// 得到标题的内容对象。
			int colNum = row.getPhysicalNumberOfCells();// 得到每行的列数。

			excleTitle = new String[colNum];
			for (int i = 0; i < colNum; i++) {

				excleTitle[i] = getStringCellValue(row.getCell((short) i));
			}

			// 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = 1; i <= rowNum; i++) {

				row = sheet.getRow(i);
				int j = 0;

				while (j < colNum) {
					String v = "";
					if (j + 1 == colNum) {
						String vs = getStringCellValue(row.getCell((short) j))
								.trim();
						if (vs.indexOf(".") > -1) {
							if (isNum(vs)) { // 是否是数字

								if (vs.endsWith("0")) {

									v = vs.substring(0, vs.indexOf("."));
								}

							} else {
								v = vs.trim();
							}

						} else {
							v = vs.trim();
						}
						excelStr += v;
					} else {

						String vs = getStringCellValue(row.getCell((short) j))
								.trim() + "&";
						if (vs.indexOf(".") > -1) {
							if (isNum(vs)) { // 是否是数字
								if (vs.endsWith("0")) { // 处理用poi读取excel整数后面加.0的格式化
									v = vs.substring(0, vs.indexOf("."));
								}

							} else {
								v = vs.trim();
							}

						} else {
							v = vs.trim();
						}
						excelStr += v;
					}

					j++;

				}
				String excelstrArray[] = excelStr.split("&", -1); // 每行数据

				Map<String, String> params = new LinkedHashMap<String, String>();
				for (int k = 0; k < excelstrArray.length; k++) {
					params.put(excleTitle[k], excelstrArray[k]);
				}
				JSONObject jsonObject = JSONObject.fromObject(params);
				contentJson.put(i, jsonObject);

				// content.put(i, excelStr);

				excelStr = "";
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return contentJson;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据97-2003
	 * 
	 * @param cell
	 * @return
	 */
	private static String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				// strCell = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
					SimpleDateFormat sdf = null;
					if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
							.getBuiltinFormat("h:mm")) {
						sdf = new SimpleDateFormat("HH:mm");
					} else {// 日期
						sdf = new SimpleDateFormat("yyyy-MM-dd");
					}
					Date date = cell.getDateCellValue();
					strCell = sdf.format(date);
				} else if (cell.getCellStyle().getDataFormat() == 58) {
					// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					double value = cell.getNumericCellValue();
					Date date = org.apache.poi.ss.usermodel.DateUtil
							.getJavaDate(value);
					strCell = sdf.format(date);
				} else {
					double value = cell.getNumericCellValue();
					CellStyle style = cell.getCellStyle();
					DecimalFormat format = new DecimalFormat();
					String temp = style.getDataFormatString();
					// 单元格设置成常规
					if (temp.equals("General")) {
						format.applyPattern("#");
					}
					strCell = format.format(value);
				}
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				strCell = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			default:
				cell.setCellType(Cell.CELL_TYPE_STRING); // 如果出现意外类型就先设置为string类型否则将会报数据类型异常
				strCell = cell.getStringCellValue();
				// strCell = "";
				break;
			}
		}

		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 * @return
	 */
	private static String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据byte数组，生成文件
	 */
	public static void getFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	// 从byte[]转file
	public static File getFileFromBytes(byte[] b, String outputFile) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			if (!file.exists() && file.isDirectory()) {// 判断文件目录是否存在
				file.mkdirs(); // mkdirs() 可以在不存在的目录中创建文件夹。诸如：a\\b,既可以创建多级目录。
			}

			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	// /**
	// * 读取Office 2007 excel
	// * */
	// private static Map<Integer, JSONObject> read2007Excels(File file)
	// throws IOException {
	// Map<Integer, JSONObject> contentJson = new LinkedHashMap<Integer,
	// JSONObject>();
	// // 构造 XSSFWorkbook 对象，strPath 传入文件路径
	// XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
	// // 读取第一章表格内容
	// XSSFSheet sheet = xwb.getSheetAt(0);
	// Object value = null;
	// XSSFRow row = null;
	// XSSFCell cell = null;
	//
	// XSSFRow headerrow = sheet.getRow(0); // 表头 得到标题的内容对象
	// int colNum = headerrow.getPhysicalNumberOfCells();// 得到每行的列数。
	// excleTitle = new String[colNum];
	// for (int i = 0; i < colNum; i++) {
	//
	// excleTitle[i] = getStringCellValue(headerrow.getCell((short) i));
	// }
	//
	// // System.out.println(sheet.getPhysicalNumberOfRows());
	// // 循环内容项 不循环标题 所以+1
	// for (int i = sheet.getFirstRowNum() + 1; i <= sheet
	// .getPhysicalNumberOfRows(); i++) {
	// row = sheet.getRow(i);
	// if (row == null) {
	// continue;
	// }
	// List<String> linked = new LinkedList<String>();
	// for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
	// cell = row.getCell(j);
	// if (null != cell) {
	// value = parseExcel(cell);
	// }
	//
	// linked.add(StringUtils.stringIsNull(value));
	// }
	// Map<String, String> params = new LinkedHashMap<String, String>();
	// for (int j = 0; j < linked.size(); j++) {
	// params.put(excleTitle[j], linked.get(j));
	// }
	// JSONObject jsonObject = JSONObject.fromObject(params);
	// contentJson.put(i, jsonObject);
	// }
	//
	// return contentJson;
	// }

	private static String parseExcel(Cell cell) {
		String result = new String();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
			// 1、判断是否是数值格式
			// if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			// short format = cell.getCellStyle().getDataFormat();
			// SimpleDateFormat sdf = null;
			// if(format == 14 || format == 31 || format == 57 || format == 58){
			// //日期
			// sdf = new SimpleDateFormat("yyyy-MM-dd");
			// }else if (format == 20 || format == 32) {
			// //时间
			// sdf = new SimpleDateFormat("HH:mm");
			// }
			// double value = cell.getNumericCellValue();
			// Date date =
			// org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
			// result = sdf.format(date);
			// }
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
						.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil
						.getJavaDate(value);
				result = sdf.format(date);
			} else {
				double value = cell.getNumericCellValue();
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String temp = style.getDataFormatString();
				// 单元格设置成常规
				if (temp.equals("General")) {
					format.applyPattern("#");
				}
				result = format.format(value);
			}

			break;
		case HSSFCell.CELL_TYPE_STRING:// String类型
			result = cell.getRichStringCellValue().toString();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
		default:
			result = cell.getDateCellValue().toString();
			break;
		}
		return result;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据 excel2007
	 * 
	 * @param cell
	 * @return
	 */
	public static String getStringCellValue(XSSFCell cell) {
		String strCell = "";
		// if(cell.equals("发布日期")){
		// return String.valueOf(cell.getDateCellValue());
		// }

		DecimalFormat df = new DecimalFormat("#");
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			// strCell = String.valueOf(cell.getNumericCellValue());
			// break;
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
						.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				strCell = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil
						.getJavaDate(value);
				strCell = sdf.format(date);
			} else {
				double value = cell.getNumericCellValue();
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String temp = style.getDataFormatString();
				// 单元格设置成常规
				if (temp.equals("General")) {
					format.applyPattern("#");
				}
				strCell = format.format(value);
			}
			// if(HSSFDateUtil.isCellDateFormatted(cell)){
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// strCell=
			// sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			// }else{
			// strCell= df.format(cell.getNumericCellValue());
			// }

			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			// cell.setCellType(Cell.CELL_TYPE_STRING);
			try {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
					SimpleDateFormat sdf = null;
					if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
							.getBuiltinFormat("h:mm")) {
						sdf = new SimpleDateFormat("HH:mm");
					} else {// 日期
						sdf = new SimpleDateFormat("yyyy-MM-dd");
					}
					Date date = cell.getDateCellValue();
					strCell = sdf.format(date);
				} else if (cell.getCellStyle().getDataFormat() == 58) {
					// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					double value = cell.getNumericCellValue();
					Date date = org.apache.poi.ss.usermodel.DateUtil
							.getJavaDate(value);
					strCell = sdf.format(date);
				} else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					strCell = cell.getStringCellValue();
				}
			} catch (Exception e) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				strCell = cell.getStringCellValue();
			}
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public static void main(String args[]) throws IOException {
		Map<Integer, JSONObject> content = new LinkedHashMap<Integer, JSONObject>();
		File f = new File("D://test//铜价导入模版.xlsx");
		FileInputStream input = null;
		try {
			input = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filename = f.getName();
		if (filename.toLowerCase().endsWith("xls")) {
			content = ExcelUtil.readExcelContent(input, 1);
		} else if (filename.toLowerCase().endsWith("xlsx")) {
			content = ExcelUtil.read2007Excels(input, 1);
		}

	}

	/**
	 * 分組依據接口，用于集合分組時，獲取分組依據
	 * 
	 * @author ZhangLiKun
	 * @title GroupBy
	 * @date 2013-4-23
	 */
	public interface GroupBy<T> {
		T groupby(Object obj);
	}

	/**
	 * 
	 * @param colls
	 * @param gb
	 * @return
	 */
	public static final <T extends Comparable<T>, D> Map<T, List<D>> group(
			Collection<D> colls, GroupBy<T> gb) {
		if (colls == null || colls.isEmpty()) {
			System.out.println("分組集合不能為空!");
			return null;
		}
		if (gb == null) {
			System.out.println("分組依據接口不能為Null!");
			return null;
		}
		Iterator<D> iter = colls.iterator();
		Map<T, List<D>> map = new HashMap<T, List<D>>();
		while (iter.hasNext()) {
			D d = iter.next();
			T t = gb.groupby(d);
			if (map.containsKey(t)) {
				map.get(t).add(d);
			} else {
				List<D> list = new ArrayList<D>();
				list.add(d);
				map.put(t, list);
			}
		}
		return map;
	}

//	public static List<AreaCopperInfos> removeDuplicate(
//			List<AreaCopperInfos> list) {
//		for (int i = 0; i < list.size() - 1; i++) {
//			for (int j = list.size() - 1; j > i; j--) {
//				if (list.get(j).getReleaseDate()
//						.equals(list.get(i).getReleaseDate())) {
//					list.remove(j);
//				}
//			}
//		}
//		return list;
//	}

	public static int getWeek(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 第几周
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		// 第几天，从周日开始
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return week;
	}

	/**
	 * 根据日期获得星期
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六" };
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysCode[intWeek];
	}

	// 根据日期取得星期几
	public static String getWeek(Date date) {
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

	/**
	 * 判断两个日期是否为同一周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDate(String date1, String date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(date1);
			d2 = format.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		// subYear==0,说明是同一年
		if (subYear == 0) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		// 例子:cal1是"2005-1-1"，cal2是"2004-12-25"
		// java对"2004-12-25"处理成第52周
		// "2004-12-26"它处理成了第1周，和"2005-1-1"相同了
		// 大家可以查一下自己的日历
		// 处理的比较好
		// 说明:java的一月用"0"标识，那么12月用"11"
		else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;

		}
		// 例子:cal1是"2004-12-31"，cal2是"2005-1-1"
		else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;

		}
		return false;
	}

	@SuppressWarnings("static-access")
	public static String getValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			// DecimalFormat df = new DecimalFormat("#.######");
			// String strCell = df.format(xssfCell.getNumericCellValue());
			// DecimalFormat df = new DecimalFormat("0");
			// String strCell = df.format(xssfCell.getNumericCellValue());

			return String.valueOf(xssfCell.getNumericCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	@SuppressWarnings("static-access")
	public static String getValue(HSSFCell hssfCell) {

		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			if (org.apache.poi.ss.usermodel.DateUtil
					.isCellDateFormatted(hssfCell)) {
				// sb.append(SEPARATOR + cell.getDateCellValue());
				return String.valueOf(hssfCell.getDateCellValue());
			} else {
				// sb.append(SEPARATOR + cellValue.getNumberValue());
				return String.valueOf(hssfCell.getNumericCellValue());
			}
			// return String.valueOf(hssfCell.getNumericCellValue());
			// return String.valueOf(hssfCell.getNumericCellValue());
			// } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_STRING) {
			// return String.valueOf(hssfCell.getStringCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	//
	// public static int getweekofyear(String str){
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// Date date = null;
	// try {
	// date = sdf.parse(str);
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// Calendar cal=Calendar.getInstance();
	// cal.setTime(date);
	// return cal.get(Calendar.WEEK_OF_YEAR);
	//
	// }

	// 获取当前时间所在年的最大周数
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

		return getWeekOfYear(c.getTime());
	}

	// 获取当前时间所在年的周数
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);

		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}
}
