package com.xh.text.ngram.twogram;

import java.util.HashMap;
import java.util.Map;

public class FirstCharInfo {
	// 总句子数
	private double sentenceCount;
	// 首字符在句子中的出现次数
	private Map<Character, Double> firstCharCountMap;
	
	public FirstCharInfo() {
		sentenceCount = 0.0;
		firstCharCountMap = new HashMap<Character, Double>();
	}
	
	/**
	 * 递增句子数
	 */
	public void increSentenceCount() {
		sentenceCount += 1.0;
	}
	
	public void addNewChar(Character firstChar) {
		// 递增句子数量
		increSentenceCount();
		Double count = firstCharCountMap.get(firstChar);
		if(count != null) {
			firstCharCountMap.put(firstChar, count + 1);
		} else {
			firstCharCountMap.put(firstChar, 1.0);
		}
	}

	public double getSentenceCount() {
		return sentenceCount;
	}
}
