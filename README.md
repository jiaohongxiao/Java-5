# java-2020.11.23  云班课  实验五 模拟学生作业处理

## 实验5

### **计G202  2020322104  焦鸿霄**

**阅读程序**

**一、实验目的**

1. 掌握字符串String及其方法的使用；
2. 掌握文件的读取/写入方法；
3. 掌握异常处理结构。

**二、实验要求**

在某课上,学生要提交实验结果，该结果存储在一个**文本文件A**中。

**文件A**包括两部分内容：

一. 是学生的基本信息；
二. 是学生处理后的作业信息，该作业的业务逻辑内容是：利用已学的字符串处理知识编程完成《长恨歌》古诗的整理对齐工作，写出功能方法，实现如下功能：

* * *
内容:

1. 每7个汉字加入一个标点符号，奇数时加“，”，偶数时加“。”
2. 允许提供输入参数，统计古诗中某个字或词出现的次数
3. 输入的文本来源于**文本文件B**读取，把处理好的结果写入到**文本文件A**
4. 考虑操作中可能出现的异常，在程序中设计异常处理程序
* * *
要求：

1. 设计学生类（可利用之前的）；
2. 采用交互式方式实例化某学生；
3. 设计程序完成上述的业务逻辑处理，并且把“古诗处理后的输出”结果存储到学生基本信息所在的**文本文件A**中。

**三、实验方法**

##### 实验里的核心代码、注释


```
//统计关键字
		int n = 0;                     
		int index = 0;                 
		index = str.indexOf(searchKey);
		while (index != -1) {
			n++;
			index = str.indexOf(searchKey, index + 1);
		}
```

```
//将所需要的学生信息统一封装
 StringBuffer stuStr = new StringBuffer();
		stuStr.append("姓名:").append(stu.getName()).append("\n");
		......（同上代码）
		stuStr.append("\n");
		return stuStr.toString();
```

```
//按要求转换读取到的字符串（添加，和。）
StringBuffer newStr=new StringBuffer();
		for(int index=0;index<str.length();index++){
			newStr.append(str.charAt(index));                //获取单个字符，拼接成新字符串
			if(index>0){
				if((index+1)%7==0&&(index+1)%2!=0){            //由于循环是从0开始的，所以计算7的时候数据要加1    
					newStr.append(",");                          //每七个字添加逗号
				}
        else if((index+1)%7==0&&(index+1)%2==0){
					newStr.append("。\n");                      //在句号后添加换行
				}
			}
}
```

```
//读取文件
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
```
```
//将学生信息以及文件内容写入新的文件
public static void writeFile(String newStr, String stuStr, String countStr) throws IOException {	
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\A.txt");
		fos.write(stuStr.getBytes());
		fos.write(newStr.getBytes());
		fos.write(countStr.getBytes());// 字节流不支持直接写入字符串，所以将字符串变为字符数组
		fos.close();                    // 关闭资源
	}
```

**四、实验结果**

```
eclipse运行结果：
-------学生作业系统-------
请输入学生姓名:焦鸿霄
请输入学生年龄:22
请输入学生班级:计G202
请输入学生性别:女
请输入需要统计的字或者词:汉
-------开始统计关键字-------
关键字：汉,出现：1次
-------关键字统计结束-------
-------写入信息结束-------

```
写入文本内容为
```
姓名:焦鸿霄
性别:女
年龄:22
班级:计G202

汉皇重色思倾国,御宇多年求不得。
杨家有女初长成,养在深闺人未识。
天生丽质难自弃,一朝选在君王侧。
回眸一笑百媚生,六宫粉黛无颜色。
春寒赐浴华清池,温泉水滑洗凝脂。
侍儿扶起娇无力,始是新承恩泽时。
云鬓花颜金步摇,芙蓉帐暖度春宵。
春宵苦短日高起,从此君王不早朝。
承欢侍宴无闲暇,春从春游夜专夜。
后宫佳丽三千人,三千宠爱在一身。
金屋妆成娇侍夜,玉楼宴罢醉和春。
姊妹弟兄皆列士,可怜光采生门户。
遂令天下父母心,不重生男重生女。
骊宫高处入青云,仙乐风飘处处闻。
缓歌慢舞凝丝竹,尽日君王看不足。
渔阳鼙鼓动地来,惊破霓裳羽衣曲。
九重城阙烟尘生,千乘万骑西南行。
<未完，待续>,
搜索的关键字:汉
一共出现次数:1次
```

**五、实验感想**

通过这次实验掌握Java中string的使用方法，学会使用FileInputStream/ FileOutputStream方法来实现文本的内容的读取写入，并学会了try、catch、finally的用法。
如果{}中的代码块出现了异常，会被catch捕获，然后执行catch中的代码，接着执行finally中的码，其中catch中的代码有了异常才会被执行，finally中的代码无论有没有异常都会被执行，。

实验中遇到的问题：
1. 在实验中写入的文本总是出现乱码状态（中文繁体乱码）---通过百度查询解决了问题，查询到除了可以使用gbk编码格式还可以使用UTF8；
2. 从一个文件中读取内容写入到另一个文件中刚开始没有理解，通过室友的讲解解决了问题；
3. 在使用try、catch、finally中， finally可以没有,有的话只能有一个，并且发现尽量不要将try…catch写在循环中。
