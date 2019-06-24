package com.you.cvs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ProcessUtil {

	public static interface StringProcessor {
		String process(String str);
	}

	public static String exec(String command, StringProcessor processor) throws IOException {
		BufferedReader br =
				new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream(), Charset.forName("GBK")));
		String line = null;
		StringBuilder b = new StringBuilder();
		while ((line = br.readLine()) != null) {
			if ((line = processor.process(line)) != null) {
				b.append("\n").append(line);
			}
		}
		if (b.length() > 0) {
			b.deleteCharAt(0);
		}
		return b.toString();
	}

	public static String exec(String command) throws IOException {
		return exec(command, new StringProcessor() {
            @Override
            public String process(String str) {
                    return str;
            }
		});
	}
}