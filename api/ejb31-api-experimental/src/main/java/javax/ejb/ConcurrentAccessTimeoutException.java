/**
 *
 * Copyright 2006 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

//
// This source code implements specifications defined by the Java
// Community Process. In order to remain compliant with the specification
// DO NOT add / change / or delete method signatures!
//
package javax.ejb;

/**
 * @version $Revision$ $Date$
 */
public class ConcurrentAccessTimeoutException extends javax.ejb.ConcurrentAccessException {

    public ConcurrentAccessTimeoutException() {
    }

    public ConcurrentAccessTimeoutException(String string) {
        super(string);
    }

    public ConcurrentAccessTimeoutException(String string, Exception exception) {
        super(string, exception);
    }
}