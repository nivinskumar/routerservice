package dev;

import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.immco.db.model.router.role.AppFunction;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

public class AppFunctionConstantCreator {

	private List<AppFunction> appList = new ArrayList<>();

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@192.168.5.184:1521:cwsdevdb";
	private static final String DB_USER = "router";
	private static final String DB_PASSWORD = "router";

	private ApplicationContext appContext = new ClassPathXmlApplicationContext();

	public static void main(String arg[]) {

		try {

			AppFunctionConstantCreator mainObject = new AppFunctionConstantCreator();
			mainObject.createConstantFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createConstantFile() throws Exception {
		selectRecordsFromDbUserTable();

		Builder classBuilder = createConstantsFile();

		JavaFile javaFile = JavaFile.builder("com.immco.routing", classBuilder.build()).build();

		writeToFile(javaFile.toString());

	}

	private void writeToFile(String content) throws IOException {
		Resource devConfigResource = appContext.getResource("devconfig.properties");
		try {
			
			Properties props = new Properties();
			props.load( devConfigResource.getInputStream());
			String projectBaseLoc = props.getProperty("projectBaseLoc");
			if (!projectBaseLoc.endsWith("/"))
				projectBaseLoc = projectBaseLoc + "/";

			String javaFileName = projectBaseLoc + "com/immco/routing/AppFunctionConstants.java";
			System.out.println(javaFileName);
			File sqlConstantsJavaFile = new File(javaFileName);
			FileUtils.write(sqlConstantsJavaFile, content, false);
			System.out.println("AppFunctionConstants File Updated..!!");
		} catch (IOException e) {
			throw e;
		}
	}

	private Builder createConstantsFile() {
		Map<BigDecimal, AppFunction> allMap = new HashMap<BigDecimal, AppFunction>();
		Map<BigDecimal, List<BigDecimal>> individualMap = new HashMap<BigDecimal, List<BigDecimal>>();

		appList.forEach(e -> {
			allMap.put(e.getpKey(), e);
		});

		individualMap = appList.stream().map(e -> {
			if (e.getParentPkey() == null) {
				e.setParentPkey(new BigDecimal(0));
			}
			return e;
		}).collect(
				groupingBy(AppFunction::getParentPkey, Collectors.mapping(AppFunction::getpKey, Collectors.toList())));

		Builder classBuilder = createClazz(allMap, individualMap, null, null);
		return classBuilder;
	}

	private AppFunction createAppFunction(int i, String name, String ouid, Integer parent) {
		AppFunction app = new AppFunction();

		app.setFunctionName(name);
		app.setFunctionOid(ouid);
		if (parent != null)
			app.setParentPkey(new BigDecimal(parent));
		app.setpKey(new BigDecimal(i));
		appList.add(app);
		return app;
	}

	private Builder createClazz(Map<BigDecimal, AppFunction> allMap, Map<BigDecimal, List<BigDecimal>> individualMap,
			List<BigDecimal> ids, Builder mainClazz) {

		if (mainClazz == null) {
			mainClazz = TypeSpec.classBuilder("AppFunctionConstants").addModifiers(Modifier.PUBLIC);
			List<BigDecimal> list = individualMap.get(new BigDecimal(0));

			if (list == null || (list != null && list.isEmpty())) {
				return mainClazz;
			} else {
				createClazz(allMap, individualMap, list, mainClazz);
			}
		} else {

			if (ids != null && !ids.isEmpty()) {

				for (BigDecimal id : ids) {

					if (isHavingChildren(id, individualMap)) {

						AppFunction appFunction = allMap.get(id);

						Builder classBuilder = TypeSpec.classBuilder(
								appFunction.getFunctionName().toUpperCase().replaceAll("\\W", "_").replace("__", "_"))
								.addModifiers(Modifier.PUBLIC, Modifier.STATIC);
						classBuilder.addField(FieldSpec
								.builder(String.class,
										"_" + appFunction.getFunctionName().toUpperCase().replaceAll("\\W", "_")
												.replace("__", "_"),
										Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
								.initializer("\"" + appFunction.getFunctionOid() + "\"").build());

						List<BigDecimal> list = individualMap.get(id);
						createClazz(allMap, individualMap, list, classBuilder);

						mainClazz.addType(classBuilder.build());

					} else {

						AppFunction appFunction = allMap.get(id);
						mainClazz.addField(FieldSpec
								.builder(String.class,
										"_" + appFunction.getFunctionName().toUpperCase().replaceAll("\\W", "_")
												.replace("__", "_"),
										Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
								.initializer("\"" + appFunction.getFunctionOid() + "\"").build());
					}

				}

			}

		}
		return mainClazz;
	}

	private boolean isHavingChildren(List<BigDecimal> ids, Map<BigDecimal, List<BigDecimal>> individualMap) {

		boolean flg = false;
		for (BigDecimal id : ids) {

			List<BigDecimal> list = individualMap.get(id);

			if (list != null && list.size() > 0) {
				flg = true;
				break;
			}
		}
		return flg;
	}

	private boolean isHavingChildren(BigDecimal id, Map<BigDecimal, List<BigDecimal>> individualMap) {

		boolean flg = false;

		List<BigDecimal> list = individualMap.get(id);

		if (list != null && list.size() > 0) {
			flg = true;

		}

		return flg;
	}

	private Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	private void selectRecordsFromDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT PKEY, FUNCTION_NM, FUNCTION_OID, PARENT_PKEY FROM APP_FUNCTION ORDER BY PKEY";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {

				Integer pkey = rs.getInt("PKEY");
				String fnname = rs.getString("FUNCTION_NM");
				String ouid = rs.getString("FUNCTION_OID");
				Integer parentKey = rs.getInt("PARENT_PKEY");
				createAppFunction(pkey, fnname, ouid, parentKey);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

}