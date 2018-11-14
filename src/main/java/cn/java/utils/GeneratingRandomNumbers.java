package cn.java.utils;
/**
 * 
 * @author Administrator
 *
 */
public class GeneratingRandomNumbers {
	/**
	 * @deprecated: 生成一个十位随机数
	 * @return
	 */
	public static String randomNumbers() {
		String num = "";
		for(int i = 0;i < 10 ;i++) {
			int n = (int) (Math.random()*10);
			num =num+ n;
		}
		
		return num;
	}	
	public static void main(String[] args) {
		String num = GeneratingRandomNumbers.randomNumbers();
		System.out.println(num);
	}
}
