import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class test {
	public static void main(String[] args) throws IOException {
		Student stu = new Student();
		System.out.println("-------学生作业系统-------");
		System.out.print("请输入学生姓名:");
		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNext()) {
			String name = scanner.next();
			stu.setName(name);
		}

		System.out.print("请输入学生年龄:");
		if (scanner.hasNextInt()) {
			int age = scanner.nextInt();
			stu.setAge(age);
		}

		System.out.print("请输入学生班级:");
		if (scanner.hasNext()) {
			String grade = scanner.next();
			stu.setGrade(grade);
		}

		System.out.print("请输入学生性别:");
		if (scanner.hasNext()) {
			String sex = scanner.next();
			stu.setSex(sex);
		}

		System.out.print("请输入需要统计的字或者词:");
		String searchKey = "";
		if (scanner.hasNext()) {
			searchKey = scanner.next();
		}
		scanner.close();

		String str = readFile();
		int count = search(str, searchKey);
		String countStr = "搜索的关键字:" + searchKey + "\n"
				+"一共出现次数:" + count + "次";
		writeFile(convertStr(str), convertStudent(stu), countStr);
	}
	
    //统计关键字
	public static int search(String str, String searchKey) {
		System.out.println("-------开始统计关键字-------");
		int n = 0;// 计数器
		int index = 0;// 指定字符的长度
		index = str.indexOf(searchKey);
		while (index != -1) {
			n++;
			index = str.indexOf(searchKey, index + 1);
		}
		System.out.println("关键字：" + searchKey + ",出现：" + n + "次");
		System.out.println("-------关键字统计结束-------");
		return n;
	}

	// 拼装学生信息
	 
	public static String convertStudent(Student stu) {
		StringBuffer stuStr = new StringBuffer();
		stuStr.append("姓名:").append(stu.getName()).append("\n");
		stuStr.append("性别:").append(stu.getSex()).append("\n");
		stuStr.append("年龄:").append(stu.getAge()).append("\n");
		stuStr.append("班级:").append(stu.getGrade()).append("\n");
		stuStr.append("\n");
		return stuStr.toString();
	}

	//转换读取到的字符串 
	public static String convertStr(String str) {
		StringBuffer newStr = new StringBuffer();
		for (int index = 0; index < str.length(); index++) {
			newStr.append(str.charAt(index)); // 获取单个字符，拼接成新字符串
			if (index > 0) {
				if ((index + 1) % 7 == 0 && (index + 1) % 2 != 0) { // 由于循环是从0开始的，所以计算7的时候数据要加1
					newStr.append(",");
				} else if ((index + 1) % 7 == 0 && (index + 1) % 2 == 0) {
					newStr.append("。\n"); // 在句号后添加换行
				}
			}
		}
		newStr.append("\n");
		return newStr.toString();
	}

	//读文件
	public static String readFile() {
		String fileStr = "";
		FileInputStream fis = null;
		File file = new File("C:\\Users\\Administrator\\Desktop\\B.txt");
		try {
			fis = new FileInputStream(file);
			int len = 0;
			byte[] buff = new byte[1024];
			while ((len = fis.read(buff)) != -1) {
				fileStr = new String(buff, 0, len, "gbk");   // gbk编码格式 出现乱码的话修改此处
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return fileStr;
	}
     //写文件
	public static void writeFile(String newStr, String stuStr, String countStr) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\A.txt");
		fos.write(stuStr.getBytes());
		fos.write(newStr.getBytes());
		fos.write(countStr.getBytes());// 字节流不支持直接写入字符串，所以将字符串变为字符数组
		fos.close();// 关闭资源
		System.out.println("-------写入信息结束-------");
	}

}

