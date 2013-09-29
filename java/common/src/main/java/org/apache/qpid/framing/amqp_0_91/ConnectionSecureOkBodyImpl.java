/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

/*
 * This file is auto-generated by Qpid Gentools v.0.1 - do not modify.
 * Supported AMQP version:
 *   0-91
 */

package org.apache.qpid.framing.amqp_0_91;

import org.apache.qpid.codec.MarkableDataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.qpid.framing.*;
import org.apache.qpid.AMQException;

public class ConnectionSecureOkBodyImpl extends AMQMethodBody_0_91 implements ConnectionSecureOkBody
{
    private static final AMQMethodBodyInstanceFactory FACTORY_INSTANCE = new AMQMethodBodyInstanceFactory()
    {
        public AMQMethodBody newInstance(MarkableDataInput in, long size) throws AMQFrameDecodingException, IOException
        {
            return new ConnectionSecureOkBodyImpl(in);
        }
    };

    public static AMQMethodBodyInstanceFactory getFactory()
    {
        return FACTORY_INSTANCE;
    }

    public static final int CLASS_ID =  10;
    public static final int METHOD_ID = 21;

    // Fields declared in specification
    private final byte[] _response; // [response]

    // Constructor
    public ConnectionSecureOkBodyImpl(MarkableDataInput buffer) throws AMQFrameDecodingException, IOException
    {
        _response = readBytes( buffer );
    }

    public ConnectionSecureOkBodyImpl(
                                byte[] response
                            )
    {
        _response = response;
    }

    public int getClazz()
    {
        return CLASS_ID;
    }

    public int getMethod()
    {
        return METHOD_ID;
    }

    public final byte[] getResponse()
    {
        return _response;
    }

    protected int getBodySize()
    {
        int size = 0;
        size += getSizeOf( _response );
        return size;
    }

    public void writeMethodPayload(DataOutput buffer) throws IOException
    {
        writeBytes( buffer, _response );
    }

    public boolean execute(MethodDispatcher dispatcher, int channelId) throws AMQException
	{
    return ((MethodDispatcher_0_91)dispatcher).dispatchConnectionSecureOk(this, channelId);
	}

    public String toString()
    {
        StringBuilder buf = new StringBuilder("[ConnectionSecureOkBodyImpl: ");
        buf.append( "response=" );
        buf.append(  getResponse() == null  ? "null" : java.util.Arrays.toString( getResponse() ) );
        buf.append("]");
        return buf.toString();
    }

}
