package com.fathzer.java.release;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Check {
	private static final String CLASS_SUFFIX = ".class";

	public static void main(String[] args) {
		try {
			final Arguments arguments = new Arguments(args);
			new Check().check(new File(arguments.getPath()), arguments.getRealease(), arguments.isIgnoreModuleInfo() ? s->s.endsWith("-info.class") : s -> false);
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void check(File jar, int maxRelease, Predicate<String> ignored) throws IOException {
		try (ZipInputStream zip = new ZipInputStream(new FileInputStream(jar))) {
			final DataInputStream data = new DataInputStream(zip);
			for (ZipEntry entry = zip.getNextEntry(); entry!=null; entry = zip.getNextEntry()) {
				if (!entry.isDirectory() && entry.getName().endsWith(CLASS_SUFFIX) && !ignored.test(entry.getName())) {
					if (zip.skip(6)!=6) {
						throw new IOException("End of file reached before major class version");
					}
					final int release = data.readUnsignedShort();
					if (release>maxRelease) {
						throw new IllegalArgumentException("The major class version of "+entry.getName()+" is "+release);
					}
				}
			}
		}
	}
}
