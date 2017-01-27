package dev;

public enum DevUtils {
	instance;
	public static DevUtils getInstance() {
		return instance;
	}

	public String convertCamelToUpper(String camelCaseString) {
		char[] charArray = camelCaseString.toCharArray();
		String outputString = "";
		for (char letter : charArray) {
			if (Character.isUpperCase(letter))
				outputString = outputString + "_" + letter;
			else
				outputString = outputString + String.valueOf(letter);
		}
		return (outputString.toUpperCase());
	}

	public String getControllerMapping(String arg) {
		// TODO Auto-generated method stub

		StringBuilder builder = new StringBuilder();

		builder.append("public static final class " + arg.toUpperCase()
				+ "CONTROLLER{\n\tpublic static final String BASE_URL = \"/routerservice/" + arg.toLowerCase()
				+ "\";   ");

		builder.append("\n\tpublic static final String CREATE_OR_UPDATE_" + arg.toUpperCase()
				+ "=auditableUserId+\"/createorupdate" + arg.toLowerCase() + "\";");
		builder.append("\n\tpublic static final String FIND_" + arg.toUpperCase() + "=auditableUserId+\"/find"
				+ arg.toLowerCase() + "\";");
		builder.append("\n\tpublic static final String DELETE_" + arg.toUpperCase() + "=auditableUserId+\"/delete"
				+ arg.toLowerCase() + "\";\n}");

		return builder.toString();

	}

	public String converter(String arg) {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"package com.immco.db.controller;\nimport java.util.HashMap;\nimport org.springframework.web.bind.annotation.PathVariable;\nimport org.springframework.web.bind.annotation.RequestBody;\nimport org.springframework.web.bind.annotation.RestController;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RequestMethod;\nimport org.springframework.web.bind.annotation.ResponseBody;\nimport com.immco.common.DBDC;\n");
		String camel = (arg.substring(0, 1).toUpperCase() + arg.substring(1, arg.length()).toLowerCase());

		builder.append("\n/**\n*@formatter:off\n*/\n");
		builder.append("\n@RestController\n@RequestMapping(\"/routerservice/" + arg.toLowerCase() + "\")");
		builder.append("\npublic class " + camel + "Controller extends BaseController\n{");

		builder.append("\n\t@RequestMapping(value = " + arg.toUpperCase() + "CONTROLLER.CREATE_OR_UPDATE_"
				+ arg.toUpperCase() + ", method = RequestMethod.POST, produces =    { \"application/hal+json\" })   ");
		builder.append(
				"\n\t@ResponseBody\n\tpublic String create(@PathVariable String auditableUserId, @RequestBody String json)\n\t{");
		builder.append(
				"\n\t\tString retJson = defaultErrMsg();\n\t\ttry\n\t\t{\n\t\t\t//  DCParam dcParam = fromJSON(json, DCParam.class);\n\t\t\tDBDC dbDc=null;\n\t\t\tretJson = toJson(dbDc);\n\t\t} catch (Exception e)\n\t\t{\n\t\t\te.printStackTrace();\n\t\t}\n\t\treturn retJson;\n\t} ");

		builder.append("\n\n\t@RequestMapping(value = " + arg.toUpperCase() + "CONTROLLER.FIND_" + arg.toUpperCase()
				+ ", method = RequestMethod.POST, produces =    { \"application/hal+json\" })    ");
		builder.append("\n\t@ResponseBody\n\tpublic String find" + camel
				+ "(@PathVariable String auditableUserId, @RequestBody String json)\n\t{");
		builder.append(
				"\n\t\tString retJson = defaultErrMsg();\n\t\ttry\n\t\t{\n\t\t\t//  DCParam dcParam = fromJSON(json, DCParam.class);\n\t\t\tretJson= toJson(\"HUB OBJECT\");\n\t\t}\n\t\tcatch(Exception e)\n\t\t{\n\t\t\te.printStackTrace();\n\t\t}\n\t\treturn retJson;\n\t}");

		builder.append("\n\n\t@RequestMapping(value = " + arg.toUpperCase() + "CONTROLLER.DELETE_" + arg.toUpperCase()
				+ ", method = RequestMethod.POST, produces =    { \"application/hal+json\" })  ");
		builder.append("\n\t@ResponseBody\n\tpublic String delete" + camel
				+ "(@PathVariable String auditableUserId, @RequestBody String json)\n\t{");
		builder.append(
				"\n\t\tString retJson = defaultErrMsg();\n\t\ttry\n\t\t{\n\t\t\t//  DCParam dcParam = fromJSON(json, DCParam.class);\n\t\t\tDBDC dbDc=null;\n\t\t\tretJson = toJson(dbDc);\n\t\t} catch (Exception e)\n\t\t{\n\t\t\te.printStackTrace();\n\t\t}\n\t\treturn retJson;\n\t}   ");

		builder.append("\n}");
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(DevUtils.getInstance().getControllerMapping("UserdefinedCustomFormExecution"));
		System.out.println(DevUtils.getInstance().converter("UserdefinedCustomFormExecution"));
	}
}
