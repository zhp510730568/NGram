package com.xh.text.ngram.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.xh.text.ngram.Iface.Corpus;

/**
 * 统计工具类，统计词信息
 * @author news
 *
 */
public class StatUtil {
	public static Map<Integer, Double> statWordCountInfo(Corpus corpus) throws IOException {
		double totalCount = 0.0;
		Map<Integer, Double> countMap = new HashMap<Integer, Double>();
		String line = "";
		while(corpus.hasNext()) {
			line = corpus.next().trim();
			if(!line.equals("")) {
				for(String word: line.split(" ")) {
					++totalCount;
					int wordLen = word.length();
					Double count = countMap.get(wordLen);
					if(count == null) {
						countMap.put(wordLen, 1.0d);
					} else {
						countMap.put(wordLen, count + 1);
					}
				}
			}
		}
		
		Iterator<Entry<Integer, Double>> iter = countMap.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<Integer, Double> entry = iter.next();
			int len = entry.getKey();
			double count = entry.getValue();
			System.out.println("Length:" + len + ", count:" + count + ", ratio:" + count / totalCount);
		}
		
		
		return countMap;
	}
}
