package com.xh.text.ngram.twogram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.xh.text.ngram.Iface.Corpus;
import com.xh.text.ngram.twogram.TwoGramInfo.WordInfo;

/**
 * 统计2元词的信息,用于新词提取
 * @author news
 *
 */
public class TwoGram {
	private Map<Character, TwoGramInfo> gramInfoDict;
	private FirstCharInfo firstCharInfo;
	private long wordNum = 0;
	private long typeNum = 0;
	private Set<Character> charSet;
	
	/**
	 * 默认构造函数
	 */
	public TwoGram() {
		firstCharInfo = new FirstCharInfo();
		gramInfoDict = new HashMap<Character, TwoGramInfo>();
		charSet = new HashSet<Character>();
	}
	
	/**
	 * 递增词例数
	 */
	private void increWordNum() {
		++wordNum;
	}
	
	/**
	 * 递增词型数
	 */
	private void increTypeNum() {
		++typeNum;
	}
	
	/**
	 * 产生2元词的统计信息
	 * @param corpus
	 */
	public void addCorpus(Corpus corpus) {
		if(corpus == null) {
			return;
		}
		String line;
		String[] sentenceArr;
		String[] strs;
		Set<String> wordSet = new HashSet<String>();
		while(corpus.hasNext()) {
			line = corpus.next();
			wordSet.clear();
			sentenceArr = line.split("\t");
			for(String sentence: sentenceArr) {
				sentence = sentence.trim();
				firstCharInfo.addNewChar(sentence.charAt(0));
				strs = sentence.split(" ");
				for(String str: strs) {
					int len = str.length();
					for(int index = 0; index <= (len - 2); index++) {
						String word = str.substring(index, index + 2);
						// 词例加一
						increWordNum();
						char fChar = word.charAt(0);
						char sChar = word.charAt(1);
						TwoGramInfo gramInfo = gramInfoDict.get(fChar);
						if(gramInfo != null) {
							gramInfo.incrFreq();
							
							if(!charSet.contains(fChar)) {
								gramInfo.incrTextCount();
							}
							
							Map<Character, WordInfo> subChars = gramInfo.getSubChars();
							WordInfo wordInfo = subChars.get(sChar);
							if(wordInfo != null) {
								wordInfo.incrFreq();
								if(!wordSet.contains(word)) {
									wordInfo.incrTextCount();
								}
							} else {
								wordInfo = gramInfo.new WordInfo(1d, 1d);
								subChars.put(sChar, wordInfo);
							}
						} else {
							// 词型数加1
							increTypeNum();
							
							gramInfo = new TwoGramInfo();
							
							Map<Character, WordInfo> subChars = new HashMap<Character, WordInfo>();
							WordInfo wordInfo = gramInfo.new WordInfo(1d, 1d);
							subChars.put(sChar, wordInfo);
							
							gramInfo.setSubChars(subChars);
							gramInfo.incrTextCount();
							gramInfo.incrFreq();
							
							gramInfoDict.put(fChar, gramInfo);
						}
						
						charSet.add(fChar);
						wordSet.add(word);
	 				}
				}
			}
		}
	}
	
	/**
	 * 计算以指定字符开头的二元词语的平均值
	 * @param fChar 首字符
	 * @return 次数平均值
	 */
	public double calcMeanFreq(char fChar) {
		double meanCount = 0.0d;
		if(!gramInfoDict.containsKey(fChar)) {
			return meanCount;
		}
		
		TwoGramInfo gramInfo = gramInfoDict.get(fChar);
		double prefFreq = gramInfo.getFreq();
		Map<Character, WordInfo> subCharsInfo = gramInfo.getSubChars();
		
		Iterator<Entry<Character, WordInfo>> iter1 = subCharsInfo.entrySet().iterator();
		while(iter1.hasNext()) {
			Entry<Character, WordInfo> entry = iter1.next();
			WordInfo wordInfo = entry.getValue();
			meanCount += wordInfo.getFreq() * wordInfo.getFreq() / prefFreq;
		}
		
		System.out.println("mean count:" + meanCount);
		
		Iterator<Entry<Character, WordInfo>> iter2 = subCharsInfo.entrySet().iterator();
		while(iter2.hasNext()) {
			Entry<Character, WordInfo> entry = iter2.next();
			WordInfo wordInfo = entry.getValue();
			if(wordInfo.getFreq() >= meanCount) {
				System.out.println(fChar + entry.getKey().toString() + ", count:" + wordInfo.getFreq());
			}
		}
		
		return meanCount;
	}
}
