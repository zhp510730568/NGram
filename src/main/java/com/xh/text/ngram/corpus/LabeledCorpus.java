package com.xh.text.ngram.corpus;

import java.io.IOException;

import com.xh.text.ngram.Iface.Corpus;

/**
 * 带标签的语料，格式为:label \t text
 * @author news
 *
 */
public class LabeledCorpus extends Corpus {
	
	private String currLine;
	
	/**
	 * 构造函数
	 * @param path 语料路径
	 * @param splitwordspath 句子分割字符文件路径
	 * @param endwordspath 句子结束字符文件路径
	 * @throws Exception
	 */
	public LabeledCorpus(String path, String splitwordspath, String endwordspath) throws Exception {
		super(path, splitwordspath, endwordspath);
	}

	@Override
	public boolean hasNext() {
		try {
			currLine = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currLine != null;
	}

	@Override
	public String next() {
		String[] lineArr = currLine.split("\t", 2);
		if(lineArr.length == 2) {
			return getNetText(lineArr[1]);
		} else {
			return "";
		}
	}
}
