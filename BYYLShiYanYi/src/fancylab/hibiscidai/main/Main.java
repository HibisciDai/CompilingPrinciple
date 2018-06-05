package fancylab.hibiscidai.main;

/** 
* CopyRright (c)2018-hibiscidai
* Project:	BYYLShiYanYi
* Comments:	编译原理|小型语法分析器的实现
* JDK version used:	JDK1.8          
* Namespace:	fancylab.hibiscidai.main                 
* Author:	hibiscidai   
* Create Date:	2018-3-22
* Version:	1.0       
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 定义缓冲流用于从控制台读取数据，静态变量节省内存

	public static String[] allWords; // 保存所有单词

	public static Map<String, Integer> diffWordsmap = new LinkedHashMap<String, Integer>();// 保存重复的单词

	// 判断一个字符串室友为纯英文
	public static boolean isEnglish(String str) {
		byte[] bytes = str.getBytes();
		int i = bytes.length;// i为字节长度
		int j = str.length();// j为字符长度
		if (i == j) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		System.out.print("请输入一句英文:"); // 提示信息
		String str = br.readLine(); // 读入控制台输入

		System.out.println();// 分割

		// 判断输入是否合法
		if (!isEnglish(str)) {
			System.out.println("输入的不是英文句子或输入的句子含有中文或中文标点");
			return;
		}

		allWords = str.split("[\\s,\\.,\\!,\\?,\\,]+");// 以空格符 . ! ?
														// ,分割字符串获得句子单词

		// 输出所有的单词
		System.out.println("句子中共有" + allWords.length + "个单词：");
		for (int i = 0; i < allWords.length; i++) {
			System.out.println(allWords[i]);
		}

		System.out.println();// 分割

		// 统计不同的单词
		for (int i = 0; i < allWords.length; i++) {
			String key = allWords[i];

			// 将不同的单词计入
			if (!diffWordsmap.containsKey(key.toLowerCase())) {// 如果不包含该key，则装入key，计数1
				// 将收集的单词转换为小写进行判断
				diffWordsmap.put(key, 1);
			} else {// 如果已经包含该key，则value增加1
				diffWordsmap.put(key, diffWordsmap.get(key) + 1);
			}
		}

		// 输出所有的单词
		System.out.println("句子中有" + diffWordsmap.size() + "个不同的单词：");
		Set<String> keySet2 = diffWordsmap.keySet();
		for (String setWord : keySet2) {// 高级for循环
			System.out.println(setWord);
		}
	}
}