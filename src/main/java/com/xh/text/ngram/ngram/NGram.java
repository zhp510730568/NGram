package com.xh.text.ngram.ngram;

import java.util.HashMap;
import java.util.Map;

import com.xh.text.ngram.Iface.Corpus;

/**
 * NGram语法模型
 * @author news
 *
 */
public class NGram {
	private Map<String, Double> wordDict;
	private int N = 0;
	private long wordNum = 0;
	private long typeNum = 0;
	
	/**
	 * 构造函数
	 * @param N 元数
	 */
	public NGram(int N) {
		this.N = N;
		wordDict = new HashMap<String, Double>();
	}
	
	/**
	 * 返回2元词信息
	 * @return wordDict属性
	 */
	public Map<String, Double> getWordDict() {
		return wordDict;
	}
	
	/**
	 * 产生N元语法模型
	 * @param corpus
	 */
	public void genNGram(Corpus corpus) {
		String line;
		String[] lineArr;
		while(corpus.hasNext()) {
			line = corpus.next();
			lineArr = line.split("\\s+");
			for(String sentence: lineArr) {
				int len = sentence.length();
				for(int index = 0; index <= (len - this.N); index++) {
					String word = sentence.substring(index, index + this.N);
					Double count = wordDict.get(word);
					if(count != null) {
						wordDict.put(word, count + 1);
					} else {
						wordDict.put(word, 1d);
					}
				}
			}
		}
	}
}
