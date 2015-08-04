package com.xh.text.ngram.util.test;

import org.junit.Test;

import com.xh.text.ngram.Iface.Corpus;
import com.xh.text.ngram.corpus.LabeledCorpus;
import com.xh.text.ngram.util.StatUtil;

public class TestStatUtil {
	@Test
	public void testStatWordCountInfo() throws Exception {
		Corpus corpus = new LabeledCorpus("D:/NLP/test/10class_1.txt", "D:/NLP/test/splitwords.txt", "D:/NLP/test/endwords.txt");
		StatUtil.statWordCountInfo(corpus);
	}
}
