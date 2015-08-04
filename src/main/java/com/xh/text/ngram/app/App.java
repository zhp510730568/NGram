package com.xh.text.ngram.app;

import com.xh.text.ngram.Iface.Corpus;
import com.xh.text.ngram.corpus.LabeledCorpus;
import com.xh.text.ngram.twogram.TwoGram;

public class App {
    public static void main( String[] args ) throws Exception {
    	Corpus corpus = new LabeledCorpus("D:/NLP/test/10class_1.txt", "D:/NLP/test/splitwords.txt", "D:/NLP/test/endwords.txt");
    	TwoGram gram = new TwoGram();
    	gram.addCorpus(corpus);
    	System.out.println(Math.pow(2, 32));
    }
}
