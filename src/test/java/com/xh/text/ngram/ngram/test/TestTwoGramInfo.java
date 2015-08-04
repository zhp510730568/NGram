package com.xh.text.ngram.ngram.test;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.xh.text.ngram.Iface.Corpus;
import com.xh.text.ngram.corpus.LabeledCorpus;
import com.xh.text.ngram.twogram.TwoGram;

public class TestTwoGramInfo {
	//@Test
	public void testTwoGram() throws Exception {
		TwoGram twoGram = new TwoGram();
		try(Corpus corpus1 = new LabeledCorpus("D:/NLP/test/10class_1.txt", "D:/NLP/test/splitwords.txt", "D:/NLP/test/endwords.txt");) {
			twoGram.addCorpus(corpus1);
		}
		try(Corpus corpus2 = new LabeledCorpus("D:/NLP/test/10class_1.txt", "D:/NLP/test/splitwords.txt", "D:/NLP/test/endwords.txt");) {
			twoGram.addCorpus(corpus2);
		}
		try(Corpus corpus2 = new LabeledCorpus("D:/NLP/test/10class_1.txt", "D:/NLP/test/splitwords.txt", "D:/NLP/test/endwords.txt");) {
			twoGram.addCorpus(corpus2);
		}
		System.out.println(twoGram.calcMeanFreq('球'));
	}
	
	@Test
	public void testList() {
		List<Object> list = new LinkedList<Object>();
		list.add("abcd");

		list.add("1234");
		System.out.println(list);
	}
}
