/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.openejb.client;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class JNDIRequest implements ClusterableRequest {

    private transient int requestMethod = -1;
    private transient String requestString;
    private transient String moduleId;
    private transient int serverHash;

    public JNDIRequest() {
    }

    public JNDIRequest(int requestMethod, String requestString) {
        this.requestMethod = requestMethod;
        this.requestString = requestString;
    }

    public byte getRequestType() {
        return RequestMethodConstants.JNDI_REQUEST;
    }

    public int getRequestMethod() {
        return requestMethod;
    }

    public String getRequestString() {
        return requestString;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public void setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setRequestString(String requestString) {
        this.requestString = requestString;
    }

    public void setServerHash(int serverHash) {
        this.serverHash = serverHash;
    }

    public int getServerHash() {
        return serverHash;
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        byte version = in.readByte(); // future use

        requestMethod = in.readByte();
        requestString = in.readUTF();
        moduleId = (String) in.readObject();
        serverHash = in.readInt();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        // write out the version of the serialized data for future use
        out.writeByte(1);

        out.writeByte((byte) requestMethod);
        out.writeUTF(requestString);
        out.writeObject(moduleId);
        out.writeInt(serverHash);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(100);

        switch(requestMethod){
            case RequestMethodConstants.JNDI_LOOKUP: sb.append("JNDI_LOOKUP:"); break;
            case RequestMethodConstants.JNDI_LIST: sb.append("JNDI_LIST:"); break;
            case RequestMethodConstants.JNDI_LIST_BINDINGS: sb.append("JNDI_LIST_BINDINGS:"); break;
        }
        sb.append(this.moduleId).append(":");
        sb.append(this.requestString);
        return sb.toString();
    }

}

