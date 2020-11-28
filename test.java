import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class test {
	public static void main(String[] args) throws IOException {
		Student stu = new Student();
		System.out.println("-------ѧ����ҵϵͳ-------");
		System.out.print("������ѧ������:");
		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNext()) {
			String name = scanner.next();
			stu.setName(name);
		}

		System.out.print("������ѧ������:");
		if (scanner.hasNextInt()) {
			int age = scanner.nextInt();
			stu.setAge(age);
		}

		System.out.print("������ѧ���༶:");
		if (scanner.hasNext()) {
			String grade = scanner.next();
			stu.setGrade(grade);
		}

		System.out.print("������ѧ���Ա�:");
		if (scanner.hasNext()) {
			String sex = scanner.next();
			stu.setSex(sex);
		}

		System.out.print("��������Ҫͳ�Ƶ��ֻ��ߴ�:");
		String searchKey = "";
		if (scanner.hasNext()) {
			searchKey = scanner.next();
		}
		scanner.close();

		String str = readFile();
		int count = search(str, searchKey);
		String countStr = "�����Ĺؼ���:" + searchKey + "\n"
				+"һ�����ִ���:" + count + "��";
		writeFile(convertStr(str), convertStudent(stu), countStr);
	}
	
    //ͳ�ƹؼ���
	public static int search(String str, String searchKey) {
		System.out.println("-------��ʼͳ�ƹؼ���-------");
		int n = 0;// ������
		int index = 0;// ָ���ַ��ĳ���
		index = str.indexOf(searchKey);
		while (index != -1) {
			n++;
			index = str.indexOf(searchKey, index + 1);
		}
		System.out.println("�ؼ��֣�" + searchKey + ",���֣�" + n + "��");
		System.out.println("-------�ؼ���ͳ�ƽ���-------");
		return n;
	}

	// ƴװѧ����Ϣ
	 
	public static String convertStudent(Student stu) {
		StringBuffer stuStr = new StringBuffer();
		stuStr.append("����:").append(stu.getName()).append("\n");
		stuStr.append("�Ա�:").append(stu.getSex()).append("\n");
		stuStr.append("����:").append(stu.getAge()).append("\n");
		stuStr.append("�༶:").append(stu.getGrade()).append("\n");
		stuStr.append("\n");
		return stuStr.toString();
	}

	//ת����ȡ�����ַ��� 
	public static String convertStr(String str) {
		StringBuffer newStr = new StringBuffer();
		for (int index = 0; index < str.length(); index++) {
			newStr.append(str.charAt(index)); // ��ȡ�����ַ���ƴ�ӳ����ַ���
			if (index > 0) {
				if ((index + 1) % 7 == 0 && (index + 1) % 2 != 0) { // ����ѭ���Ǵ�0��ʼ�ģ����Լ���7��ʱ������Ҫ��1
					newStr.append(",");
				} else if ((index + 1) % 7 == 0 && (index + 1) % 2 == 0) {
					newStr.append("��\n"); // �ھ�ź���ӻ���
				}
			}
		}
		newStr.append("\n");
		return newStr.toString();
	}

	//���ļ�
	public static String readFile() {
		String fileStr = "";
		FileInputStream fis = null;
		File file = new File("C:\\Users\\Administrator\\Desktop\\B.txt");
		try {
			fis = new FileInputStream(file);
			int len = 0;
			byte[] buff = new byte[1024];
			while ((len = fis.read(buff)) != -1) {
				fileStr = new String(buff, 0, len, "gbk");   // gbk�����ʽ ��������Ļ��޸Ĵ˴�
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
     //д�ļ�
	public static void writeFile(String newStr, String stuStr, String countStr) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\A.txt");
		fos.write(stuStr.getBytes());
		fos.write(newStr.getBytes());
		fos.write(countStr.getBytes());// �ֽ�����֧��ֱ��д���ַ��������Խ��ַ�����Ϊ�ַ�����
		fos.close();// �ر���Դ
		System.out.println("-------д����Ϣ����-------");
	}

}

