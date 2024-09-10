package com.fathzer.java.release;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArgumentsTest {

	@Test
	void test() {
		final String path = "path";
		Arguments args = new Arguments(new String[]{path,"8",Arguments.IGNORE_MODULE_INFO,Arguments.IS_JAVA_ARG});
		assertEquals(path, args.getPath());
		assertEquals(52, args.getRealease());
		assertTrue(args.isIgnoreModuleInfo());

		args = new Arguments(new String[]{path,"52"});
		assertEquals(path, args.getPath());
		assertEquals(52, args.getRealease());
		assertFalse(args.isIgnoreModuleInfo());
		
		assertThrows(IllegalArgumentException.class, ()->new Arguments(new String[] {}));
		assertThrows(IllegalArgumentException.class, ()->new Arguments(new String[] {path}));
		assertThrows(IllegalArgumentException.class, ()->new Arguments(new String[] {path, "x"}));
		assertThrows(IllegalArgumentException.class, ()->new Arguments(new String[] {path, "1"}));
		assertThrows(IllegalArgumentException.class, ()->new Arguments(new String[] {path, "52", "-x"}));
	}

}
