package dev;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class PackageMover
{
    private ApplicationContext appContext = new ClassPathXmlApplicationContext();
    private String proxyProjectBaseLoc;
    private String projectBaseLoc;

    public static void main(String[] args)
    {
	PackageMover p = new PackageMover();
	try
	{
	    p.move();
	    p.trimAnnotations();
	} catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    private void trimAnnotations() throws Exception
    {
	File modelFolderDst = new File(proxyProjectBaseLoc + "com/immco/db/model");
	Iterator<File> iter = FileUtils.iterateFiles(modelFolderDst, new SuffixFileFilter("java"), DirectoryFileFilter.DIRECTORY);
	while (iter.hasNext())
	{
	    StringBuffer sb = new StringBuffer();
	    File javaFile = iter.next();
	    List<String> readLines = FileUtils.readLines(javaFile);
	    for (String line : readLines)
	    {
		if (line.contains("javax.persistence") || line.contains("org.hibernate") || (line.contains("@") && (!line.contains("@Json"))))
		{
		    // ignore line
		} else
		{
		    sb.append(line).append("\n");
		}
	    }

	    FileUtils.write(javaFile, sb.toString(), false);
	}

	// System.out.println(modelFolderSrc.getAbsolutePath());
	System.out.println(modelFolderDst.getAbsolutePath());
    }

    private void move() throws Exception
    {
	Resource devConfigResource = appContext.getResource("devconfig.properties");
	File propsFile = devConfigResource.getFile();
	Properties props = new Properties();
	props.load(new FileInputStream(propsFile));

	proxyProjectBaseLoc = props.getProperty("proxyProjectBaseLoc");
	projectBaseLoc = props.getProperty("projectBaseLoc");

	if (!projectBaseLoc.endsWith("/"))
		projectBaseLoc = projectBaseLoc + "/";
	if (!proxyProjectBaseLoc.endsWith("/"))
	    proxyProjectBaseLoc = proxyProjectBaseLoc + "/";

	File commonFolderSrc = new File(projectBaseLoc + "com/immco/common");
	File commonfolderDst = new File(proxyProjectBaseLoc + "com/immco/common");

	System.out.println(commonFolderSrc.getAbsolutePath());
	System.out.println(commonfolderDst.getAbsolutePath());
	FileUtils.copyDirectory(commonFolderSrc, commonfolderDst, true);

	File modelFolderSrc = new File(projectBaseLoc + "com/immco/db/model");
	File modelFolderDst = new File(proxyProjectBaseLoc + "com/immco/db/model");

	System.out.println(modelFolderSrc.getAbsolutePath());
	System.out.println(modelFolderDst.getAbsolutePath());
	FileUtils.copyDirectory(modelFolderSrc, modelFolderDst, true);
	
	
	File appConstantFileSrc = new File(projectBaseLoc + "com/immco/routing/AppFunctionConstants.java");
	File appConstantFileDst = new File(proxyProjectBaseLoc + "com/immco/routing/AppFunctionConstants.java");
	
	FileUtils.copyFile(appConstantFileSrc, appConstantFileDst, true);

		// File remoteFolderSrc = new File(projectBaseLoc +
		// "com/immco/db/remote");
		// File remoteFolderDst = new File(proxyProjectBaseLoc +
		// "com/immco/db/remote");
		//
		// System.out.println(remoteFolderSrc.getAbsolutePath());
		// System.out.println(remoteFolderDst.getAbsolutePath());
		// FileUtils.copyDirectory(remoteFolderSrc, remoteFolderDst, true);

	System.out.println("Done..!!!");
    }
}
