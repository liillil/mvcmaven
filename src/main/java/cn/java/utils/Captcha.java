package cn.java.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Captcha {
	/**
	 * 宽度
	 */
	private int width;
	/**
	 * 高度
	 */
	private int height;
	/**
	 * 验证码字符数
	 */
	private int num;
	/**
	 * 验证码字典
	 */
	private String code;
	/**
	 * 获取一个随机数对象
	 */
	private static final Random ran = new Random();
	//单例模式
	private static Captcha captcha;
	private Captcha() {
		num = 4;
		code = "123456789QWERTYUIOPLKJHGFDSAZXCVBNM";
	}
	
	public static  Captcha  getInstance() {
		if(captcha == null) captcha = new Captcha();
		return captcha;
		
	}
	/**
	 * 设置验证码宽高验证码数和字典
	 * @param width
	 * @param heigth
	 * @param num
	 * @param code
	 */
	public void set (int width,int heigth,int num , String code) {
		setWidth(width);
		setHeight(heigth);
		setNum(num);
		setCode(code);
	}
	
	/**
	 * 设置图片的宽高
	 * @param width
	 * @param heigth
	 */
	public void set (int width,int heigth) {
		setWidth(width);
		setHeight(heigth);
	}
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String randomChar() {
		StringBuffer cc = new StringBuffer();
		for(int i = 0 ;i < num;i++) {
			cc.append(code.charAt(ran.nextInt(code.length())));
		}
		return cc.toString();
	}
	
	public BufferedImage drawing(String Checkcode ) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D graphic = img.createGraphics();
		graphic.setColor(Color.WHITE);
		graphic.fillRect(0, 0, width, height);
		graphic.setColor(Color.BLACK);
		graphic.drawRect(0, 0, width-1, height-1);
		Font font = new Font("微软雅黑",Font.BOLD+Font.ITALIC, (int)height);
		graphic.setFont(font);
		for(int i = 0 ; i < num ;i++) {
			int degree = new Random().nextInt() % 25;
			graphic.setColor(new Color(ran.nextInt(155), ran.nextInt(255), ran.nextInt(255)));
			graphic.rotate(degree * Math.PI / 180, i*(width/num)+4, (int)height-5);
			graphic.drawString(String.valueOf(Checkcode.charAt(i)), i*(width/num)+4, (int)height-5);
			graphic.rotate(-degree * Math.PI / 180, i*(width/num)+4, (int)height-5);
		}
		/**
		 * 画点
		 */
		for(int i = 0 ;i < (width+height);i++) {
			graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
			graphic.drawOval(ran.nextInt(width), ran.nextInt(height), 1, 1);
		}
		
		for(int i = 0 ;i < 3;i++) {
			graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
			graphic.drawLine(0, ran.nextInt(height), width, ran.nextInt(height));
		}
		return img;
		
		
	}
	
	
	
	
}
