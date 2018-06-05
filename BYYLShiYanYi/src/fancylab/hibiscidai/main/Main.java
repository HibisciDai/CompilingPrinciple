package fancylab.hibiscidai.main;

/** 
* CopyRright (c)2018-hibiscidai
* Project:	BYYLShiYanYi
* Comments:	����ԭ��|С���﷨��������ʵ��
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
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // ���建�������ڴӿ���̨��ȡ���ݣ���̬������ʡ�ڴ�

	public static String[] allWords; // �������е���

	public static Map<String, Integer> diffWordsmap = new LinkedHashMap<String, Integer>();// �����ظ��ĵ���

	// �ж�һ���ַ�������Ϊ��Ӣ��
	public static boolean isEnglish(String str) {
		byte[] bytes = str.getBytes();
		int i = bytes.length;// iΪ�ֽڳ���
		int j = str.length();// jΪ�ַ�����
		if (i == j) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		System.out.print("������һ��Ӣ��:"); // ��ʾ��Ϣ
		String str = br.readLine(); // �������̨����

		System.out.println();// �ָ�

		// �ж������Ƿ�Ϸ�
		if (!isEnglish(str)) {
			System.out.println("����Ĳ���Ӣ�ľ��ӻ�����ľ��Ӻ������Ļ����ı��");
			return;
		}

		allWords = str.split("[\\s,\\.,\\!,\\?,\\,]+");// �Կո�� . ! ?
														// ,�ָ��ַ�����þ��ӵ���

		// ������еĵ���
		System.out.println("�����й���" + allWords.length + "�����ʣ�");
		for (int i = 0; i < allWords.length; i++) {
			System.out.println(allWords[i]);
		}

		System.out.println();// �ָ�

		// ͳ�Ʋ�ͬ�ĵ���
		for (int i = 0; i < allWords.length; i++) {
			String key = allWords[i];

			// ����ͬ�ĵ��ʼ���
			if (!diffWordsmap.containsKey(key.toLowerCase())) {// �����������key����װ��key������1
				// ���ռ��ĵ���ת��ΪСд�����ж�
				diffWordsmap.put(key, 1);
			} else {// ����Ѿ�������key����value����1
				diffWordsmap.put(key, diffWordsmap.get(key) + 1);
			}
		}

		// ������еĵ���
		System.out.println("��������" + diffWordsmap.size() + "����ͬ�ĵ��ʣ�");
		Set<String> keySet2 = diffWordsmap.keySet();
		for (String setWord : keySet2) {// �߼�forѭ��
			System.out.println(setWord);
		}
	}
}