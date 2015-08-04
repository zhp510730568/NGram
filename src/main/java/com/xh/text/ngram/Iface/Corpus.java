package com.xh.text.ngram.Iface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.xh.text.ngram.util.Word;
import com.xh.text.ngram.util.Trie;

/**
 * 父接口
 * @author news
 *
 */
public class Corpus implements Iterator<String>, AutoCloseable{
	
	protected BufferedReader br;
	// 保存句子分隔符
	private Trie splitwordsTrie;
	// 保存句子结束符
	private Trie endwordsTrie;
	
	/**
	 * 构造函数
	 * @param path 语料路径
	 * @param stopwordPath 停止词路径
	 * @throws Exception
	 */
	public Corpus(String path, String splitwordspath, String endwordspath) {
	    try {
			this.br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e1) {
			System.err.println("读取语料文件错误");
		}
	    if(splitwordspath != null) {
	    	try {
				this.splitwordsTrie = Trie.getInstance(splitwordspath);
			} catch (Exception e) {
				System.err.println("加载停止词文件错误");
			}
	    }
	    if(endwordspath != null) {
	    	try {
				this.endwordsTrie = Trie.getInstance(endwordspath);
			} catch (Exception e) {
				System.err.println("加载停止词文件错误");
			}
	    }
	}
	
	/**
	 * 继承方法
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 继承方法
	 */
	@Override
	public String next() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 继承方法
	 */
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 继承方法
	 */
	@Override
	public void close() throws IOException {
		if(br != null) {
			br.close();
		}
	}
	
	/**
	 * 把文本分割点转为可识别符号
	 * @param line
	 * @return
	 */
	public String getNetText(String line) {
		if(this.splitwordsTrie == null) {
			return line;
		}
		if(this.endwordsTrie == null) {
			return line;
		}
		String netString = "";
		int len = line.length();
		int pos = 0;
		int subStart = 0;
		int subEnd = 0;
		String subStr = "";
		while(pos < len) {
			Word endword = this.endwordsTrie.matchParOfStr(line, pos);
			if(endword != null) {
				subEnd = pos;
				int wordlen = endword.getWordName().length();
				pos += wordlen;
				subStr = line.substring(subStart, subEnd).trim();
				if(!subStr.equals("")) {
					netString += "	" + subStr;
				}
				subStart = pos;
				continue;
			} else {
				Word splitword = this.splitwordsTrie.matchParOfStr(line, pos);
				if(splitword != null) {
					subEnd = pos;
					int wordlen = splitword.getWordName().length();
					pos += wordlen;
					subStr = line.substring(subStart, subEnd).trim();
					if(!subStr.equals("")) {
						netString += " " + subStr;
					}
					subStart = pos;
					continue;
				}
			}
			++pos;
		}
		subEnd = len;
		
		subStr = line.substring(subStart, subEnd).trim();
		if(!subStr.equals("")) {
			netString += " " + subStr;
		}
		
		return netString;
	}	
}
