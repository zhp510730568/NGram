package com.xh.text.ngram.twogram;

import java.util.Map;

/**
 * 二元词信息
 * @author news
 *
 */
public class TwoGramInfo {
	/**
	 * 词信息
	 * @author news
	 *
	 */
	public class WordInfo {
		// 总频次
		private double freq;
		// 文档频次
		private double textCount;
		
		/**
		 * 构造函数
		 * @param freq 总频次
		 * @param textCount 文档频次
		 */
		public WordInfo(double freq, double textCount) {
			this.freq = freq;
			this.textCount = textCount;
		}

		/**
		 * 返回总频次
		 * @return 总频次
		 */
		public double getFreq() {
			return freq;
		}

		/**
		 * 递增总频次
		 */
		public void incrFreq() {
			++this.freq;
		}

		/**
		 * 返回文档频次
		 * @return 文档频次
		 */
		public double getTextCount() {
			return textCount;
		}

		/**
		 * 递增文档频次
		 */
		public void incrTextCount() {
			++this.textCount;
		}
	}
	
	// 以符号结尾的词信息
	private Map<Character, WordInfo> subChars;
	// 以此字符开始的词的总频次
	private double freq;
	// 文档频次
	private double textCount;
	
	/**
	 * 默认构造函数
	 */
	public TwoGramInfo() {
		
	}
	
	/**
	 * 构造函数
	 * @param freq 总频次
	 * @param textCount 文档频次
	 * @param subChars 字符信息
	 */
	public TwoGramInfo(double freq, double textCount, Map<Character, WordInfo> subChars) {
		this.freq = freq;
		this.textCount = textCount;
		this.subChars = subChars;
	}
	
	/**
	 * 返回总频次
	 * @return 总频次
	 */
	public double getFreq() {
		return freq;
	}

	/**
	 * 递增总频次
	 */
	public void incrFreq() {
		++this.freq;
	}
	
	/**
	 * 返回文档频次
	 * @return 文档频次
	 */
	public double getTextCount() {
		return textCount;
	}
	
	/**
	 * 递增文档频次
	 */
	public void incrTextCount() {
		++this.textCount;
	}

	/**
	 * 返回字符信息
	 * @return 字符信息
	 */
	public Map<Character, WordInfo> getSubChars() {
		return subChars;
	}

	/**
	 * 设置字符信息
	 * @param subChars 字符信息
	 */
	public void setSubChars(Map<Character, WordInfo> subChars) {
		this.subChars = subChars;
	}
}
