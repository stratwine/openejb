/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.openejb.tomcat.loader;
import java.io.File; 
import java.util.HashSet; 
import java.util.Set;
import javax.servlet.ServletContext;
import org.apache.tomcat.JarScannerCallback; 
import org.apache.tomcat.util.scan.StandardJarScanner;

public class TomEEJarScanner extends StandardJarScanner {

	public void scan(ServletContext context, ClassLoader classLoader, JarScannerCallback callback, Set<String> jarsToIgnore) {
		Set<String> newIgnores = new HashSet<String>();
		if (jarsToIgnore != null) {
			newIgnores.addAll(jarsToIgnore);
		}
		if ("FragmentJarScannerCallback".equals(callback.getClass().getSimpleName())) {
			File openejbApp = new File(System.getProperty("openejb.war"));
			File libFolder = new File(openejbApp, "lib");
			for (File f : libFolder.listFiles()) {
				if (f.getName().toLowerCase().endsWith(".jar")) {
					newIgnores.add(f.getName());
				}
			}
		}
		
		super.scan(context, classLoader, callback, newIgnores);
	}
}
