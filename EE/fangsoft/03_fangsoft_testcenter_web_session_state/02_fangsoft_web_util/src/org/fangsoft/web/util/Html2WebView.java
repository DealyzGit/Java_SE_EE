package org.fangsoft.web.util;

import java.io.*;


public class Html2WebView {

    public static final String indent = "   ";

    public static String getIndent(int many) {
        if (many <= 0) return "";
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < many; i++) {
            buf.append(indent);
        }
        return buf.toString();
    }

    public static final String METHOD_DEC = "public void output(PrintWriter writer)";
    public static final String FILE_ENCODING = "utf-8";
    public static final String GC = "{";
    public static final String LC = "}";

    public static StringBuffer html2OutputMethod(
            String htmlFileName, int intIndent) throws IOException {
        StringBuffer buf = new StringBuffer();
        String mIndent = getIndent(intIndent);
        String sIndent = getIndent(intIndent + 1);
        buf.append(mIndent + METHOD_DEC + GC + "\n");
        String line = "";
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(htmlFileName), FILE_ENCODING);
        BufferedReader br = new BufferedReader(reader);
        while ((line = br.readLine()) != null) {
            String javaCode = line.replaceAll("\"", "\\\\\"");
            buf.append(sIndent + "writer.println(");
            buf.append("\"" + javaCode + "\"");
            buf.append(");\n");
        }
        buf.append("\n");
        buf.append(mIndent + LC);
        br.close();
        return buf;
    }

    public static final String PRINTWRITER_IMPORT = "import java.io.PrintWriter;";
    public static final String CLASS_DECL =
            "public class %className% extends HtmlView";
    public static final String SERIAL_VERSION_UID =
            "private static final long serialVersionUID = 1L;";

    public static StringBuffer html2Class(String className, String htmlFileName, String packageName) throws IOException {
        StringBuffer buf = new StringBuffer();
        String class_dec = CLASS_DECL.replaceAll("%className%", className);
        if (packageName != null) {
            packageName = packageName.trim();
            if (packageName.length() > 0) {
                buf.append("package " + packageName + ";" + "\n");
                buf.append("\n");
            }
        }
        buf.append(PRINTWRITER_IMPORT);
        buf.append(class_dec + GC);
        buf.append("\n");
        buf.append(indent + SERIAL_VERSION_UID + "\n");
        buf.append(html2OutputMethod(htmlFileName, 1));
        buf.append("\n");
        buf.append(LC);
        return buf;
    }

    public static String validate(String str) {
        if (str == null) return "";
        return str.trim();
    }


    public static String getJavaFilePath(String toDir, String packageName) {
        String dir = validate(toDir);
        if (dir.length() != 0) dir = dir + "/";
        packageName = validate(packageName);
        String packagePath = packageName.trim().replaceAll("\\.", "/");
        ;
        if (packagePath.length() != 0) dir = dir + packagePath + "/";
        new File(dir).mkdirs();
        return dir;
    }

    public static void save(String toDir, String className, String htmlFileName,
                            String packageName) throws IOException {
        String javaFilePath = getJavaFilePath(toDir, packageName);
        FileOutputStream fos = new
                FileOutputStream(javaFilePath + className + ".java");
        BufferedWriter bw = new
                BufferedWriter(new OutputStreamWriter(fos, FILE_ENCODING));
        bw.write(html2Class(className, htmlFileName, packageName).toString());
        bw.flush();
        bw.close();
    }


    public static String firstLetterToUpperCase(String str) {
        String letter = str.substring(0, 1).toUpperCase();
        return letter + str.substring(1);
    }

    public static void run(String htmlDir, String javaDir, String packageName) throws IOException {
        File d = new File(htmlDir);
        if (!d.exists()) return;
        if (d.isFile()) {
            String fileName = d.getName();
            int idx = fileName.lastIndexOf(".");
            if (idx != -1) {
                fileName = fileName.substring(0, idx);
            }
            String className = firstLetterToUpperCase(fileName) + "View";
            save(javaDir, className, htmlDir, packageName);
        } else {
            File[] fs = d.listFiles();
            if (fs != null) {
                for (File ff : fs) {
                    run(ff.getAbsolutePath(), javaDir, packageName);
                }
            }
        }
    }

    public static void saveHtmlViewClass(String toDir, String packageName) throws IOException {
        String javaFilePath = getJavaFilePath(toDir, packageName);
        FileOutputStream fos = new FileOutputStream(javaFilePath + "HtmlView.java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, FILE_ENCODING));
        packageName = validate(packageName);
        if (packageName.length() != 0) {
            bw.write("package " + packageName + ";\n");
        }
        bw.write(PRINTWRITER_IMPORT + "\n");
        bw.write("public class HtmlView implements java.io.Serializable{\n");
        bw.write(indent + SERIAL_VERSION_UID + "\n");
        bw.write(indent + "public void output(PrintWriter writer){}\n");
        bw.write("}");
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        saveHtmlViewClass("D:\\TONG\\Java\\02\\02_fangsoft_web_util\\src",
                "org.fangsoft.testcenter.web.view");
        Html2WebView.run("D:\\TONG\\Java\\02\\02_fangsoft_web_util\\html",
                "D:\\TONG\\Java\\02\\02_fangsoft_web_util\\src",
                "org.fangsoft.testcenter.web.view");
    }


}
