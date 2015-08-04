package com.xh.text.ngram.smooth;

/**
 * 拉普拉斯平滑
 * @author news
 *
 */
public class Laplace {
	// 词例数
	private long wordNum;
	// 词型数
	private long typeNum;
	// 平滑因子
	private double factor;
	
	private double total;
	
	/**
	 * 构造函数
	 * @param wordNum
	 * @param typeNum
	 * @param factor
	 */
	public Laplace(long wordNum, long typeNum, double factor) {
		this.wordNum = wordNum;
		this.typeNum = typeNum;
		this.total =  wordNum + typeNum * factor;
		this.factor = factor;
	}
	
	/**
	 * 返回词例数
	 * @return 词例数
	 */
	public long getWordNum() {
		return wordNum;
	}

	/**
	 * 设置词例数
	 * @param wordNum 词例数
	 */
	public void setWordNum(long wordNum) {
		this.wordNum = wordNum;
	}

	/**
	 * 返回词型数
	 * @return 词型数
	 */
	public long getTypeNum() {
		return typeNum;
	}

	/**
	 * 设置词型数
	 * @param typeNum 词型数
	 */
	public void setTypeNum(long typeNum) {
		this.typeNum = typeNum;
	}

	/**
	 * 返回平滑因子
	 * @return 平滑因子
	 */
	public double getFactor() {
		return factor;
	}
	
	/**
	 * 设置平滑因子
	 * @param factor 平滑因子
	 */
	public void setFactor(double factor) {
		this.factor = factor;
	}

	/**
	 * 计算平滑后的值
	 * @param freq 词频值
	 * @return 平滑后的值
	 */
	public double smoothValue(double freq) {
		if(total > 0) {
			return (freq + this.factor) / this.total;
		}
		return 0.0;
	}
}
